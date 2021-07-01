package hrms.hrms.dataAcces.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import hrms.hrms.entities.concretes.Candidates;

public interface CandidatesDao extends JpaRepository<Candidates , Integer>{
	
	Candidates getByIdentityNumber(String identityNumber);
	
	Candidates getByEmail(String email);
	
	Candidates getByCandidateId(int candidateId);
	
}
