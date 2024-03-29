package com.solidit.myhome.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;


@Entity
@Data
public class User {

	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String username;
	    private String password;
	    private Boolean enabled;

	    @ManyToMany
	    @JoinTable(
	            name = "user_role",
	            joinColumns = @JoinColumn(name = "user_id"),
	            inverseJoinColumns = @JoinColumn(name = "role_id"))
	    private List<Role> roles = new ArrayList<>();
	    
//	    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//	    @JsonIgnore
	    private List<Board> boards = new ArrayList<>();
}
