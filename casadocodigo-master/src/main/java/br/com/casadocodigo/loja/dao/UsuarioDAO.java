package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Usuario;

@Repository
public class UsuarioDAO implements UserDetailsService {

	@PersistenceContext
	private EntityManager manager;

	public void gravar(Usuario usuario) {
		manager.persist(usuario);
	}

	public List<Usuario> listar() {
		return manager.createQuery("select u from Usuario u", Usuario.class).getResultList();
	}

	public Usuario loadUserByUsername(String email) {
		List<Usuario> usuarios = manager.createQuery("select u from Usuario u where email = :email", Usuario.class)
				.setParameter("email", email).getResultList();

		if (usuarios.isEmpty()) {
			throw new UsernameNotFoundException(
					"Usuario " + email + " não foi encontrado pelo método 'loadUserByUsername' ");
		}

		return usuarios.get(0);
	}

	public Boolean existe(String email) {

		Usuario usuarioEncontrado;

		try {
			usuarioEncontrado = manager.createQuery("select u from Usuario u where email = :email", Usuario.class)
					.setParameter("email", email).getSingleResult();

		} catch (NoResultException e) {
			System.out.println(">> Usuario não Existe!");
			return false;
		}

		System.out.println(">>> Usuario " + usuarioEncontrado.getNome() + " já Existe.");
		return true;
	}

	public void alterar(Usuario usuario) {
		Usuario usuario2 = manager.find(Usuario.class, usuario.getId());
		usuario2.setRoles(usuario.getRoles());
	}

}