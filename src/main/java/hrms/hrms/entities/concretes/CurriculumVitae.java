package hrms.hrms.entities.concretes;

import java.sql.Date;
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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cv_candidates_users")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","candidatesCv","foreignLanguage","schools","technologyProgramming","jobExperiences"})
public class CurriculumVitae {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cv_id")
	private int cvId;
	
	@Column(name="photo")
	private String photo;
	
	@Column(name="github_link")
	private String githubLink;
	
	@Column(name="linked_link")
	private String linkedLink;
	
	@Column(name="description")
	private String description;
	
	@Column(name="create_date_cv")
	private Date createDateCv;
	
	@Column(name="is_active")
	private boolean isActive;
	
	@Column(name="last_update_date")
	private Date lastUpdateDate;
	
	@ManyToOne()
	@JoinColumn(name = "candidate_id")
	private Candidates candidatesCv;
	
	@OneToMany(mappedBy = "curriculumVitaeTechnologyProgramming")
	private List<TechnologyProgramming> technologyProgramming;
	
	@OneToMany(mappedBy = "curriculumVitaeJobExperience")
	private List<JobExperience> jobExperiences;
	
	@OneToMany(mappedBy = "curriculumVitaeSchools")
	private List<Schools> schools;
	
	@OneToMany(mappedBy = "curriculumVitaeForeignLanguage")
	private List<ForeignLanguage> foreignLanguage;
}
