package hrms.hrms.entities.concretes;

import java.util.List;



import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job_titles")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","jobAdverts","jobExperience"})
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="job_title_id")
	private int jobTitleId;
	
	@Column(name="title")
	private String title;
	
	@JsonIgnore
	@OneToMany(mappedBy = "departmentJobAdverts")
	private List<JobAdverts> jobAdverts;
	
	@JsonIgnore
	@OneToMany(mappedBy = "departmentJobExperience")
	private List<JobExperience> jobExperience;
}
