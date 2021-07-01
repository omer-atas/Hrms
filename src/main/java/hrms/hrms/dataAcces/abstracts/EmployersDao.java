package hrms.hrms.dataAcces.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import hrms.hrms.entities.concretes.Employers;

public interface EmployersDao extends JpaRepository<Employers, Integer > {
	
	Employers getByEmployersId(int employersId);
	
	Employers getByEmail(String email);
	
	Employers getByWebAddress(String webAddress);
}
