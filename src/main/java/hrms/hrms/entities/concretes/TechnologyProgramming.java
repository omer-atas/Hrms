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

@Table(name="technology_programming")
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyProgramming {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="technology_programming_id")
	private int technologyProgrammingId;
	
	@Column(name="technology_programming_name")
	@NotBlank(message = "Teknoloji ve programlama alanı boş bırakılamaz..")
	@NotNull
	private String technologyProgrammingName;
	
	@Column(name="create_date_foreign_language")
	@NotBlank(message = "Teknoloji ve programlama alanının oluşturulma tarihi boş bırakılamaz..")
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "2000-01-01")
	private Date createDateForeignLanguage;
	
	
	@ManyToOne()
	@JoinColumn(name = "cv_id")
	private CurriculumVitae curriculumVitaeTechnologyProgramming;
	
}
