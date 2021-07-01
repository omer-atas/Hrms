package hrms.hrms.business.concretes;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import hrms.hrms.business.abstracts.SchoolsService;
import hrms.hrms.core.utilies.result.DataResult;
import hrms.hrms.core.utilies.result.ErrorResult;
import hrms.hrms.core.utilies.result.Result;
import hrms.hrms.core.utilies.result.SuccessDataResult;
import hrms.hrms.core.utilies.result.SuccessResult;
import hrms.hrms.dataAcces.abstracts.CurriculumVitaeDao;
import hrms.hrms.dataAcces.abstracts.SchoolsDao;
import hrms.hrms.entities.concretes.Schools;

@Service
public class SchoolsManager implements SchoolsService {

	private SchoolsDao schoolDao;
	private CurriculumVitaeDao curriculumVitaeDao;
	
	@Autowired
	public SchoolsManager(SchoolsDao schoolDao,CurriculumVitaeDao curriculumVitaeDao) {
		super();
		this.schoolDao = schoolDao;
		this.curriculumVitaeDao = curriculumVitaeDao;
	}

	@Override
	public DataResult<List<Schools>> getAll() {
		return new SuccessDataResult<List<Schools>>(this.schoolDao.findAll(),"Data listelendi..");
	}

	@Override
	public Result add(Schools schools) {
		
		int curriculumVitaeId = schools.getCurriculumVitaeSchools().getCvId();
		
		if(curriculumVitaeId == 0) {
			return new ErrorResult("Okulun bilgisinin hangi özgeçmişe ait olduğunu giriniz..");
		}else if(this.curriculumVitaeDao.getByCvId(curriculumVitaeId) == null) {
			return new ErrorResult("Böyle bir özgeçmiş bulunmamaktadır..");
		}
		
		this.schoolDao.save(schools);
		return new SuccessResult("TechnologyProgramming added..");
	}

	@Override
	public DataResult<List<Schools>> getAllSorted() {
		
		Schools schools = new Schools();
		
		if(schools.getGraduationYear() == null) {
			Sort sort = Sort.by(Sort.Direction.ASC,"schoolName");
			return new SuccessDataResult<List<Schools>>(this.schoolDao.findAll(sort),"Başarılı");
		}else {
			Sort sort = Sort.by(Sort.Direction.DESC,"graduationYear");
			return new SuccessDataResult<List<Schools>>(this.schoolDao.findAll(sort),"Başarılı");
		}
		
		
	}

}
