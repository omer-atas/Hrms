package hrms.hrms.entities.concretes;

import java.util.List;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@Entity


@Table(name="employees_users")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","employeeConfirms"})
public class Employees{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="employee_id")
	private int employeeId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@OneToMany(mappedBy = "employees")
	private List<EmployeeConfirms> employeeConfirms;
}
