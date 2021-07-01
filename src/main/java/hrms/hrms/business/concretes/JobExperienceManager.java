package hrms.hrms.business.concretes;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import hrms.hrms.business.abstracts.JobExperienceService;
import hrms.hrms.core.utilies.result.DataResult;
import hrms.hrms.core.utilies.result.ErrorResult;
import hrms.hrms.core.utilies.result.Result;
import hrms.hrms.core.utilies.result.SuccessDataResult;
import hrms.hrms.core.utilies.result.SuccessResult;
import hrms.hrms.dataAcces.abstracts.CurriculumVitaeDao;
import hrms.hrms.dataAcces.abstracts.JobExperienceDao;
import hrms.hrms.entities.concretes.JobExperience;
import hrms.hrms.entities.concretes.Schools;

@Service
public class JobExperienceManager implements JobExperienceService {

	private JobExperienceDao jobExperienceDao;
	private CurriculumVitaeDao curriculumVitaeDao;
	
	@Autowired
	public JobExperienceManager(JobExperienceDao jobExperienceDao,CurriculumVitaeDao curriculumVitaeDao) {
		super();
		this.jobExperienceDao = jobExperienceDao;
		this.curriculumVitaeDao = curriculumVitaeDao;
	}

	@Override
	public Result add(JobExperience jobExperience) {
		
		int curriculumVitaeId = jobExperience.getCurriculumVitaeJobExperience().getCvId();
		
		if(curriculumVitaeId == 0) {
			return new ErrorResult("İş tecrübesinin hangi özgeçmişe ait olduğunu giriniz..");
		}else if(this.curriculumVitaeDao.getByCvId(curriculumVitaeId) == null) {
			return new ErrorResult("Böyle bir özgeçmiş bulunmamaktadır..");
		}
		
		this.jobExperienceDao.save(jobExperience);
		return new SuccessResult("JobExperience added..");
	}

	@Override
	public DataResult<List<JobExperience>> getAll() {
		return new SuccessDataResult<List<JobExperience>>(this.jobExperienceDao.findAll(),"Data listelendi..");
	}

	@Override
	public DataResult<List<JobExperience>> getAllSorted() {
		
		Schools schools = new Schools();
		
		if(schools.getGraduationYear() == null) {
			Sort sort = Sort.by(Sort.Direction.ASC,"workplaceName");
			return new SuccessDataResult<List<JobExperience>>(this.jobExperienceDao.findAll(sort),"Başarılı");
		}else {
			Sort sort = Sort.by(Sort.Direction.DESC,"leaveYear");
			return new SuccessDataResult<List<JobExperience>>(this.jobExperienceDao.findAll(sort),"Başarılı");
		}
	}

}
