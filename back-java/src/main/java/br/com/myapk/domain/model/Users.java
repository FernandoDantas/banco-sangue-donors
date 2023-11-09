package br.com.myapk.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Users {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "users_id")
	private Long id;
	
	@Column(name = "users_email", nullable = false)
	private String userEmail;

	@Column(name = "crypted_pass", nullable = false)
	private String cryptedPass;
	
	@Column(name = "status_", nullable = false)
	private String status;
	
	@Column(name = "users_name")
	private String userName;
	
	@Column(name = "photo_url")
	private String photo;
	
	@ManyToMany
	@JoinTable(name = "user_grouping", joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "grouping_id"))
	private Set<Grouping> groupings = new HashSet<>();

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tenant", referencedColumnName = "tenant_id")
	private Tenant tenant;

	public boolean removeGroup(Grouping grouping) {
		return getGroupings().remove(grouping);
	}
	
	public boolean addGroup(Grouping grouping) {
		return getGroupings().add(grouping);
	}
	
	public boolean isNew() {
		return getId() == null;
	}
	
}
