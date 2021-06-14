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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
	
	
	@Column(name="is_job_adverts_active")
	private boolean isJobAdvertsActive;
	
	@Column(name="job_definition")
	private String jobDefinition;
	
	@Column(name="min_salary")
	private double minSalary;
	
	@Column(name="max_salary")
	private double maxSalary;
	
	@Column(name="open_position_count")
	private int openPositionCount;
	
	@Column(name="job_advert_create_date")
	private Date jobAdvertCreateDate;
	
	@Column(name="deadline")
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
