package hrms.hrms.entities.concretes;

import java.util.List;





import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//fk_employee_confirms_employee_id
@Entity

@Table(name="employers_users")

@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","jobAdverts"})
public class Employers{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="employer_id")
	private int employersId;
	
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="web_address")
	private String webAddress;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@ManyToOne()
	@JoinColumn(name = "verification_code_id_employers")
	private VerificationCode VerificationCodeEmployers;
	
	@ManyToOne()
	@JoinColumn(name = "employee_confirms_id")
	private EmployeeConfirms employeeConfirmsEmployers;
	
	@JsonIgnore
	@OneToMany(mappedBy = "employersJobAdverts")
	private List<JobAdverts> jobAdverts;
	
}
