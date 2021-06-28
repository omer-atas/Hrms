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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@Entity

@Table(name="candidates_users")

@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","curriculumVitaes"})
public class Candidates{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="candidate_id")
	private int candidateId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="identity_number")
	private String identityNumber;
	
	@Column(name="birth_year")
	private int birthYear;
		
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@ManyToOne()
	@JoinColumn(name = "verification_code_id_candidates")
	private VerificationCode VerificationCodeCandidates;
	
	
	@OneToMany(mappedBy = "candidates")
	private List<CurriculumVitae> curriculumVitaes;
	
}
