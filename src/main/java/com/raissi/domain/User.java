package com.raissi.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="user_table")
/*
@javax.persistence.SequenceGenerator(
    name="user_seq",
    sequenceName="user_id_sequence"
)*/

/*
	@TypeDef(
        name="encryptedString", 
        typeClass=EncryptedStringType.class, 
        parameters={@Parameter(name="encryptorRegisteredName",
                               value="hibernateStringEncryptor")}
	)
 */

public class User implements Serializable{

	private static final long serialVersionUID = 3571343460175211199L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Generator user_seq
    @Column(name = "user_id")
	private Long userId;
	@Column(name = "login", nullable = false)
	private String login;
	@Column(name = "password", nullable = false)
	//@Type(type="encryptedString")
	private String password;
	@Column(name = "firstname", nullable = false)
	private String firstName;
	@Column(name = "lastname", nullable = false)
	private String lastName;
	@Column(name = "address")
	private String address;
	@Column(name = "email", nullable = false)
	private String email;
	@Column(name = "role", nullable = false)
	private String role = "USER";
	
	@ManyToOne( cascade = {CascadeType.MERGE})
    @JoinColumn(name="cv_id")
	private Resume resume;
	
	public User() {
		super();
	}
	
	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	@Transient
	private Set<String> roles;
	
	public User(Long userId, String login, String password, String firstName, String lastName) {
		super();
		this.userId = userId;
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Boolean hasRole(String role){
		if(role != null){
			return role.equals(this.role);
		}
		return false;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
}
