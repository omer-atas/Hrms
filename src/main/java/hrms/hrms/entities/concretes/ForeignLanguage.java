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

@Table(name="foreign_language")
@AllArgsConstructor
@NoArgsConstructor
public class ForeignLanguage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="foreign_language_id")
	private int foreignLanguageId;
	
	@Column(name="language_name")
	@NotBlank(message = "Dil alanı boş bırakılamaz..")
	@NotNull
	private String languageName;
	
	@Column(name="language_level")
	@NotBlank(message = "Dil seviyesi alanı boş bırakılamaz..")
	@NotNull
	private int languageLevel;
	
	@Column(name="create_date_foreign_language")
	@NotBlank(message = "Oluşturulma tarihi alanı boş bırakılamaz..")
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "2000-01-01")
	private Date foreignLanguageCreateDate;
	
	
	@ManyToOne()
	@JoinColumn(name = "cv_id")
	private CurriculumVitae curriculumVitaeForeignLanguage;
	
}
