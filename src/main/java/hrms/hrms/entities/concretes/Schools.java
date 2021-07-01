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

@Table(name="schools")
@AllArgsConstructor
@NoArgsConstructor
public class Schools {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="school_id")
	private int schoolId;

	@Column(name="school_name")
	@NotBlank(message = "Okul adı boş bırakılamaz..")
	@NotNull
	private String schoolName;
	
	@Column(name="chapter")
	@NotBlank(message = "Okuduğu bölüm alanı boş bırakılamaz..")
	@NotNull
	private String chapter;
	
	@Column(name="start_year")
	@NotBlank(message = "Okula başlama tarihi alanı boş bırakılamaz..")
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "2000-01-01")
	private Date startYear;
	
	@Column(name="graduation_year")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "2000-01-01")
	private Date graduationYear;
	
	@Column(name="create_date_schools")
	@NotBlank(message = "Oluşturulma tarihi alanı boş bırakılamaz..")
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "2000-01-01")
	private Date createDateYear;
	
	@Column(name="graduation_state")
	private boolean graduationState;
	
	
	@ManyToOne()
	@JoinColumn(name = "cv_id")
	private CurriculumVitae curriculumVitaeSchools;
	
}
