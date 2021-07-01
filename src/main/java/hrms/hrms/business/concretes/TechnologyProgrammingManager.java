package hrms.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.hrms.business.abstracts.TechnologyProgrammingService;
import hrms.hrms.core.utilies.result.DataResult;
import hrms.hrms.core.utilies.result.ErrorResult;
import hrms.hrms.core.utilies.result.Result;
import hrms.hrms.core.utilies.result.SuccessDataResult;
import hrms.hrms.core.utilies.result.SuccessResult;
import hrms.hrms.dataAcces.abstracts.CurriculumVitaeDao;
import hrms.hrms.dataAcces.abstracts.TechnologyProgrammingDao;
import hrms.hrms.entities.concretes.TechnologyProgramming;

@Service
public class TechnologyProgrammingManager implements TechnologyProgrammingService {

	private TechnologyProgrammingDao technologyProgrammingDao;
	private CurriculumVitaeDao curriculumVitaeDao;

	@Autowired
	public TechnologyProgrammingManager(TechnologyProgrammingDao technologyProgrammingDao,
								CurriculumVitaeDao curriculumVitaeDao) {
		super();
		this.technologyProgrammingDao = technologyProgrammingDao;
		this.curriculumVitaeDao = curriculumVitaeDao;
	}

	@Override
	public Result add(TechnologyProgramming technologyProgramming) {
		
		int curriculumVitaeId = technologyProgramming.getCurriculumVitaeTechnologyProgramming().getCvId();
		
		if(curriculumVitaeId == 0) {
			return new ErrorResult("Adayın sahip olduğu niteliklerin/yeteneklerin hangi özgeçmişe ait olduğunu giriniz..");
		}else if(this.curriculumVitaeDao.getByCvId(curriculumVitaeId) == null) {
			return new ErrorResult("Böyle bir özgeçmiş bulunmamaktadır..");
		}
		
		this.technologyProgrammingDao.save(technologyProgramming);
		return new SuccessResult("TechnologyProgramming added..");
	}

	@Override
	public DataResult<List<TechnologyProgramming>> getAll() {
		return new SuccessDataResult<List<TechnologyProgramming>>(this.technologyProgrammingDao.findAll(),"Data listelendi..");
	}
	
}
