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
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cv_candidates_users")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","foreignLanguage","schools","images","technologyProgramming","jobExperiences"})
public class CurriculumVitae {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cv_id")
	private int cvId;
	
	@Column(name="github_link")
	private String githubLink;
	
	@Column(name="linked_link")
	private String linkedLink;
	
	@Column(name="description")
	private String description;
	
	@Column(name="create_date_cv")
	@NotBlank(message = "Oluşturulma tarihi alanı boş bırakılamaz..")
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "2000-01-01")
	private Date createDateCv;
	
	@Column(name="is_active")
	@NotBlank(message = "Özgeçmisin durumu alanı boş bırakılamaz..")
	@NotNull
	private boolean isActive;
	
	@Column(name="last_update_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "2000-01-01")
	private Date lastUpdateDate;
	
	@ManyToOne()
	@JoinColumn(name = "candidate_id_cv")
	private Candidates candidates;
	
	@JsonIgnore
	@OneToMany(mappedBy = "curriculumVitaeTechnologyProgramming")
	private List<TechnologyProgramming> technologyProgramming;
	
	@JsonIgnore
	@OneToMany(mappedBy = "curriculumVitaeSchools")
	private List<Schools> schools;
	
	@JsonIgnore
	@OneToMany(mappedBy = "curriculumVitaeJobExperience")
	private List<JobExperience> jobExperiences;
	
	@JsonIgnore
	@OneToMany(mappedBy = "curriculumVitaeForeignLanguage")
	private List<ForeignLanguage> foreignLanguage;
	
	@JsonIgnore
	@OneToMany(mappedBy = "curriculumVitaeImage")
	private List<Image> images;
}
