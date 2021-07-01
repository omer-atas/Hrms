package hrms.hrms.business.concretes;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.hrms.business.abstracts.CurriculumVitaeService;
import hrms.hrms.core.utilies.result.DataResult;
import hrms.hrms.core.utilies.result.ErrorResult;
import hrms.hrms.core.utilies.result.Result;
import hrms.hrms.core.utilies.result.SuccessDataResult;
import hrms.hrms.core.utilies.result.SuccessResult;
import hrms.hrms.dataAcces.abstracts.CandidatesDao;
import hrms.hrms.dataAcces.abstracts.CurriculumVitaeDao;
import hrms.hrms.dataAcces.abstracts.VerificationCodeDao;
import hrms.hrms.entities.concretes.CurriculumVitae;

@Service
public class CurriculumVitaeManager implements CurriculumVitaeService{
	
	private CurriculumVitaeDao curriculumVitaeDao;
	private CandidatesDao candidatesDao;
	
	@Autowired
	public CurriculumVitaeManager(CurriculumVitaeDao curriculumVitaeDao,
										CandidatesDao candidatesDao) {
		super();
		this.curriculumVitaeDao = curriculumVitaeDao;
		this.candidatesDao = candidatesDao;
	}

	@Override
	public Result add(CurriculumVitae curriculumVitae) {
		
		int candidatesId = curriculumVitae.getCandidates().getCandidateId();
		
		if(candidatesId == 0) {
			return new ErrorResult("Bu özgeçmişin kime ait olduğunu giriniz..");
		}else if(this.candidatesDao.getByCandidateId(candidatesId) == null) {
			return new ErrorResult("Böyle bir iş arayan bulunmamaktadır..");
		}
		this.curriculumVitaeDao.save(curriculumVitae);
		return new SuccessResult("CurriculumVitae added..");
	}

	@Override
	public DataResult<List<CurriculumVitae>> getAll() {
		return new SuccessDataResult<List<CurriculumVitae>>(this.curriculumVitaeDao.findAll(),"CurriculumVitae listed..");
	}

	@Override
	public DataResult<CurriculumVitae> getByCvId(int cvId) {
		return new SuccessDataResult<CurriculumVitae>
		(this.curriculumVitaeDao.getByCvId(cvId),"Data listelendi..");
	}


}
