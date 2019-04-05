package br.com.casadocodigo.loja.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;
import br.com.casadocodigo.loja.validation.UsuarioValidation;

@Controller
@Transactional
public class UsuarioController {

	@Autowired
	private UsuarioDAO dao;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new UsuarioValidation());
	}

	@RequestMapping("/usuarios")
	public ModelAndView listar() {
		List<Usuario> lista = dao.listar();
		ModelAndView modelAndView = new ModelAndView("usuarios/lista");
		modelAndView.addObject("usuarios", lista);
		return modelAndView;
	}

	@RequestMapping("/usuarios/form")
	public ModelAndView form(Usuario usuario) {
		return new ModelAndView("usuarios/form");
	}

	@RequestMapping(value = "/usuarios", method = RequestMethod.POST)
	public ModelAndView gravar(@Valid Usuario usuario, BindingResult result, RedirectAttributes rediredtAttributes) {

		if (result.hasErrors()) {
			System.out.println("ERRO NO BIND");
			return form(usuario);
		}

		if (dao.existe(usuario.getEmail())) {
			rediredtAttributes.addFlashAttribute("message", "Esse email já é Cadastrado!");
			return new ModelAndView("redirect:/usuarios/form");
		}

		List<Role> roles = new ArrayList<>();
		Role role = new Role();
		role.setNome("ROLE_USER");
		roles.add(role);
		usuario.setRoles(roles);

		dao.gravar(usuario);
		rediredtAttributes.addFlashAttribute("message", "Usuario Cadastrado com Sucesso!");
		return new ModelAndView("redirect:/usuarios");
	}

	@RequestMapping(value = "/usuarios/roles")
	public ModelAndView roles(@RequestParam("email") String email) {
		Usuario usuario = dao.loadUserByUsername(email);
		ModelAndView modelAndView = new ModelAndView("/usuarios/roles");
		modelAndView.addObject("usuario", usuario);
		return modelAndView;
	}

	@RequestMapping(value = "/usuarios/roles", method = RequestMethod.POST)
	public String alterar(String email, HttpServletRequest request, RedirectAttributes rediredtAttributes) {

		String[] array = request.getParameterValues("role");
		Usuario usuario = dao.loadUserByUsername(email);

		List<Role> listaRole = new ArrayList<>();
		for (int i = 0; i < array.length; i++) {
			Role role = new Role();
			role.setNome(array[i]);
			listaRole.add(role);
		}

		usuario.setRoles(listaRole);

		dao.alterar(usuario);

		rediredtAttributes.addFlashAttribute("message", "Permissões alteradas com sucesso!");
		return "redirect:/usuarios";

	}

}
