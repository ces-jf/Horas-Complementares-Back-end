package br.edu.uniacademia.ativcompl.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.edu.uniacademia.ativcompl.domain.enums.ProfileEnum;

@Entity
@Table(name = "tb_user_types")
public class UserType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String type;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "userTypeList")
	private List<User> user = new ArrayList<>();
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "tb_profiles")
	private Set<Integer> profiles = new HashSet<>();
	
	public UserType() {}


	public UserType(Long id, String type) {
		super();
		this.id = id;
		this.type = type;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	
	public Set<ProfileEnum> getProfiles(){
		return profiles.stream().map(p -> ProfileEnum.toEnum(p)).collect(Collectors.toSet());
	}
	
	public void addProfile(ProfileEnum profile) {
		profiles.add(profile.getCod());
	}

	public List<User> getUsers() {
		return user;
	}


	public void setUsers(List<User> user) {
		this.user = user;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		UserType other = (UserType) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
		
}

