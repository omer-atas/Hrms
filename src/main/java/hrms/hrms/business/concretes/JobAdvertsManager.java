package hrms.hrms.business.concretes;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import hrms.hrms.business.abstracts.JobAdvertsService;
import hrms.hrms.core.utilies.result.DataResult;
import hrms.hrms.core.utilies.result.ErrorResult;
import hrms.hrms.core.utilies.result.Result;
import hrms.hrms.core.utilies.result.SuccessDataResult;
import hrms.hrms.core.utilies.result.SuccessResult;
import hrms.hrms.dataAcces.abstracts.CityDao;
import hrms.hrms.dataAcces.abstracts.DepartmentDao;
import hrms.hrms.dataAcces.abstracts.EmployersDao;
import hrms.hrms.dataAcces.abstracts.JobAdvertsDao;
import hrms.hrms.entities.concretes.JobAdverts;

@Service
public class JobAdvertsManager implements JobAdvertsService{

	private JobAdvertsDao jobAdvertsDao;
	private EmployersDao employerDao;
	private DepartmentDao departmentDao;
	private CityDao cityDao;
	
	@Autowired
	public JobAdvertsManager(JobAdvertsDao jobAdvertsDao,EmployersDao employerDao,
										DepartmentDao departmentDao,CityDao cityDao) {
		super();
		this.jobAdvertsDao = jobAdvertsDao;
		this.employerDao = employerDao;
		this.departmentDao = departmentDao;
		this.cityDao = cityDao;
	}

	@Override
	public Result add(JobAdverts jobAdverts) {
		
		int employerId = jobAdverts.getEmployersJobAdverts().getEmployersId();
		int jobTitleId = jobAdverts.getDepartmentJobAdverts().getJobTitleId();
		int cityId 	   = jobAdverts.getCityJobAdverts().getCityId();
		
		if(employerId == 0) {
			return new ErrorResult("Bu ilanı hangi işverenin oluşturduğunu giriniz..");
		}else if(this.employerDao.getByEmployersId(employerId) == null) {
			return new ErrorResult("Böyle bir işveren bulunmamaktadır..");
		}else if(jobTitleId == 0) {
			return new ErrorResult("Bu ilanın iş pozisyonunu giriniz..");
		}else if(this.departmentDao.getByJobTitleId(jobTitleId) == null) {
			return new ErrorResult("Böyle bir iş pozisyonu bulunmamaktadır..");
		}else if(cityId == 0) {
			return new ErrorResult("Bu ilanın lokasyonunu giriniz..");
		}else if(this.cityDao.getByCityId(cityId) == null) {
			return new ErrorResult("Böyle bir lokaasyon yeri bulunmamaktadır..");
		}
		
		this.jobAdvertsDao.save(jobAdverts);
		return new SuccessResult("JobAdverts added..");
	}

	@Override
	public DataResult<List<JobAdverts>> getAll(){
		return new SuccessDataResult<List<JobAdverts>>(this.jobAdvertsDao.findAll(),"Data listelendi..");
	}

	@Override
	public DataResult<List<JobAdverts>> getByIsActive(boolean isActive) {
		return new SuccessDataResult<List<JobAdverts>>(this.jobAdvertsDao.getByIsActive(isActive),"Başarılı");
	}

	@Override
	public DataResult<List<JobAdverts>> getAllSortedDate(boolean isActive) {
		Sort sort = Sort.by(Sort.Direction.ASC,"deadline");
		return new SuccessDataResult<List<JobAdverts>>(this.jobAdvertsDao.findAll(sort),"Başarılı");
	}

	@Override
	public DataResult<List<JobAdverts>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<JobAdverts>>(this.jobAdvertsDao.findAll(pageable).getContent(),"Başarılı");
	}

	@Override
	public DataResult<List<JobAdverts>> getByCompanyIsActiveJobAdverts(boolean isActive, int employersId) {
		return new SuccessDataResult<List<JobAdverts>>(this.jobAdvertsDao.getByCompanyIsActiveJobAdverts(isActive,employersId),"Başarılı");
	}

	@Override
	public Result changeJobAdvertsStatus(int jobAdvertsId,int employersID) {
		JobAdverts jobAdvertToChangeActive = this.jobAdvertsDao.findById(employersID).get();
		jobAdvertToChangeActive.setJobAdvertsActive(!jobAdvertToChangeActive.isJobAdvertsActive());
		this.jobAdvertsDao.save(jobAdvertToChangeActive);
		return new SuccessResult("Başarılı");
	}

	
}
