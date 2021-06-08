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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@Entity

@Table(name="schools")
@AllArgsConstructor
@NoArgsConstructor
public class Schools {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="school_id")
	private int schoolId;

	@Column(name="school_name")
	private String schoolName;
	
	@Column(name="chapter")
	private String chapter;
	
	@Column(name="start_year")
	private Date startYear;
	
	@Column(name="graduation_year")
	private Date graduationYear;
	
	@Column(name="create_date_schools")
	private Date createDateYear;
	
	@Column(name="graduation_state")
	private String graduationState;
	
	@ManyToOne()
	@JoinColumn(name = "cv_id")
	private CurriculumVitae curriculumVitaeSchools;
}
