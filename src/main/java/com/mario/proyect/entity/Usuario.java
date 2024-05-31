package com.mario.proyect.entity;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails{
    
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "correo_electronico")
	private String email;
	private String usuario;
	private String password;

	@ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;
	
	public String getUsuario() {
		return usuario;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		ArrayList<SimpleGrantedAuthority> permisos = new ArrayList<SimpleGrantedAuthority>();
		SimpleGrantedAuthority permiso;
		if(usuario.compareTo("mario")==0) {
			permiso = new SimpleGrantedAuthority("ADMIN");
		}
		else if(usuario.compareTo("javi")==0) {
			permiso = new SimpleGrantedAuthority("AUTORIZADO");
		}
		else {
			permiso = new SimpleGrantedAuthority("USER");
		}
		permisos.add(permiso);
		
		return permisos;
	}
	
	@Override
	public String getUsername() {
		return usuario;
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
		return "Usuario [email=" + email + ", usuario=" + usuario + ", password=" + password + "]";
	}
}
