package br.com.casadocodigo.loja.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;

	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	//@Id
	private String email;
	private String senha;
	private String nome;
	private String senha_repetida;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Usuario_Role", joinColumns = @JoinColumn(name = "email"), inverseJoinColumns = @JoinColumn(name = "role_nome"))
	private List<Role> roles = new ArrayList<>();

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	
	
	public String getSenha_repetida() {
		return senha_repetida;
	}

	public void setSenha_repetida(String senha_repetida) {
		this.senha_repetida = senha_repetida;
	}
	

	public Long getId() {
		return id;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String toString() {
		return "[email=" + email + ", senha=" + senha + ", nome=" + nome + ", senha_repetida=" + senha_repetida
				+ ", roles=" + roles + "]";
	}
	
	
	
}
