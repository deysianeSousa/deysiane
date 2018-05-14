package br.com.deysiane.deysiane.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;


@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue()
	@Column(name="cd_id")
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password ;
	private LocalDateTime creationDate;
	private LocalDateTime lastLogin;
	private LocalDateTime alterationDate;

	@OneToMany(mappedBy= "user", fetch= FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Phone> phones;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public LocalDateTime getAlterationDate() {
		return alterationDate;
	}

	public void setAlterationDate(LocalDateTime alterationDate) {
		this.alterationDate = alterationDate;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

}
