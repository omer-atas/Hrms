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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@Entity

@Table(name="job_adverts")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","candidates"})
public class JobAdverts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="job_advert_id")
	private int jobAdvertsId;
	
	
	@Column(name="is_job_advert_active")
	@NotBlank(message = "İş ilanının durumu boş bırakılamaz..")
	@NotNull
	private boolean isJobAdvertsActive;
	
	@Column(name="job_definition")
	@NotBlank(message = "İş ilanının tanımı boş bırakılamaz..")
	@NotNull
	private String jobDefinition;
	
	@Column(name="min_salary")
	private double minSalary;
	
	@Column(name="max_salary")
	private double maxSalary;
	
	@Column(name="open_position_count")
	@NotBlank(message = "İş ilanının pozisyon adedi boş bırakılamaz..")
	@NotNull
	private int openPositionCount;
	
	@Column(name="job_advert_create_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "2000-01-01")
	private Date jobAdvertCreateDate;
	
	@Column(name="deadline")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "2000-01-01")
	private Date deadline;
	
	@ManyToOne()
	@JoinColumn(name = "employer_id")
	private Employers employersJobAdverts;
	
	@ManyToOne()
	@JoinColumn(name = "job_position_id")
	private Department departmentJobAdverts;
	
	@ManyToOne()
	@JoinColumn(name = "city_id")
	private City cityJobAdverts;
	
}
