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

@Table(name="employee_confirms")

@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","employers"})
public class EmployeeConfirms {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="employee_confirm_id")
	private int employeeConfirmId;
	
	@Column(name="is_confirmed")
	@NotBlank(message = "Onayın durumu alanı boş bırakılamaz..")
	@NotNull
	private boolean isConfirmed;
	
	@Column(name="confirmed_date")
	@NotBlank(message = "Oluşturulma tarihi alanı boş bırakılamaz..")
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "2000-01-01")
	private Date confirmedDate;
	
	@ManyToOne()
	@JoinColumn(name = "employee_id")
	private Employees employees;
	
	@JsonIgnore
	@OneToMany(mappedBy = "employeeConfirmsEmployers")
	private List<Employers> employers;
	
}
