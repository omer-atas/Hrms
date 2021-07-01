package hrms.hrms.entities.concretes;

import java.sql.Date;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@Entity

@Table(name="job_experience")
@AllArgsConstructor
@NoArgsConstructor
public class JobExperience {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="job_experience_id")
	private int jobExperienceId;
	
	
	@Column(name="start_year")
	@NotBlank(message = "İş tecrübesi alanı boş bırakılamaz..")
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "2000-01-01")
	private Date startYear;
	
	@Column(name="leave_year")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "2000-01-01")
	private Date leaveYear;
	
	@Column(name="create_date_job_experience")
	@NotBlank(message = "Oluşturulma tarihi boş bırakılamaz..")
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "2000-01-01")
	private Date createDateYear;
	
	@Column(name="workplace_name")
	private String workplaceName;
	
	
	@ManyToOne()
	@JoinColumn(name = "cv_id")
	private CurriculumVitae curriculumVitaeJobExperience;
	
	@ManyToOne()
	@JoinColumn(name = "place_of_business_position_id")
	private Department departmentJobExperience;
	
}
