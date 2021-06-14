package hrms.hrms.core.dataAcces;


import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import hrms.hrms.entities.concretes.Candidates;
import hrms.hrms.entities.concretes.Employees;
import hrms.hrms.entities.concretes.Employers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","candidates","employers","employees"})
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private int id;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@OneToMany(mappedBy = "userEmployer")
	private List<Employers> employers;
	
	@OneToMany(mappedBy = "userCandidate")
	private List<Candidates> candidates;
	
	@OneToMany(mappedBy = "userEmployee")
	private List<Employees> employees;

}
