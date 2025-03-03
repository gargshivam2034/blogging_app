package com.BlogApp.entites;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.annotation.security.RolesAllowed;
import jakarta.persistence.*;

@Entity
public class User implements UserDetails{
	
	  @Id
	  @GeneratedValue(strategy =GenerationType.AUTO)
	  private int id; 
	  private String name;
	  private String email;
	  private String password;
	  private String about;
	 
	  @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	  @JoinTable(name="user_role",joinColumns = 
	  @JoinColumn(name="user",referencedColumnName ="id"),
	  inverseJoinColumns =@JoinColumn(name="role",referencedColumnName = "id") )
	  private Set<Role>roles=new HashSet<Role>();
	
  public int getId() {
		return id;
	}
	public Set<Role> getRoles() {
	return roles;
}
public void setRoles(Set<Role> roles) {
	this.roles = roles;
}
	public User() {
	super();

}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	
	public void setAbout(String about) {
		this.about = about;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles.stream().map((role)->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	
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
	

  
  

}
