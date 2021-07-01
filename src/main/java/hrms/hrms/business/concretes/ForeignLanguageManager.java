package hrms.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.hrms.business.abstracts.ForeignLanguageService;
import hrms.hrms.core.utilies.result.DataResult;
import hrms.hrms.core.utilies.result.ErrorResult;
import hrms.hrms.core.utilies.result.Result;
import hrms.hrms.core.utilies.result.SuccessDataResult;
import hrms.hrms.core.utilies.result.SuccessResult;
import hrms.hrms.dataAcces.abstracts.CurriculumVitaeDao;
import hrms.hrms.dataAcces.abstracts.ForeignLanguageDao;
import hrms.hrms.entities.concretes.ForeignLanguage;
@Service
public class ForeignLanguageManager implements ForeignLanguageService{
	
	private ForeignLanguageDao foreignLanguageDao;
	private CurriculumVitaeDao curriculumVitaeDao;
	
	@Autowired
	public ForeignLanguageManager(ForeignLanguageDao foreignLanguageDao,CurriculumVitaeDao curriculumVitaeDao) {
		super();
		this.foreignLanguageDao = foreignLanguageDao;
		this.curriculumVitaeDao = curriculumVitaeDao;
	}

	@Override
	public DataResult<List<ForeignLanguage>> getAll() {
		return new SuccessDataResult<List<ForeignLanguage>>(this.foreignLanguageDao.findAll(),"Data listelendi..");
	}

	@Override
	public Result add(ForeignLanguage foreignLanguage) {
		
		int curriculumVitaeId = foreignLanguage.getCurriculumVitaeForeignLanguage().getCvId();
		
		if(foreignLanguage.getLanguageLevel() > 5 && foreignLanguage.getLanguageLevel() <= 5 ) {
			return new SuccessResult("Foreign language doesn't add..");
		}else if(curriculumVitaeId == 0) {
			return new ErrorResult("Dil bilgisinin hangi özgeçmişe ait olduğunu giriniz..");
		}else if(this.curriculumVitaeDao.getByCvId(curriculumVitaeId) == null) {
			return new ErrorResult("Böyle bir özgeçmiş bulunmamaktadır..");
		}
		
		this.foreignLanguageDao.save(foreignLanguage);
		return new SuccessResult("ForeignLanguage added..");
	}

}
