package hrms.hrms.business.concretes;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.hrms.business.abstracts.EmployeeConfirmsService;
import hrms.hrms.core.utilies.result.DataResult;
import hrms.hrms.core.utilies.result.ErrorResult;
import hrms.hrms.core.utilies.result.Result;
import hrms.hrms.core.utilies.result.SuccessDataResult;
import hrms.hrms.core.utilies.result.SuccessResult;
import hrms.hrms.dataAcces.abstracts.EmployeeConfirmsDao;
import hrms.hrms.dataAcces.abstracts.EmployeeDao;
import hrms.hrms.entities.concretes.EmployeeConfirms;

@Service
public class EmployeeConfirmsManager implements EmployeeConfirmsService{
	
	private EmployeeConfirmsDao employeeConfirmsDao;
	private EmployeeDao employeeDao;
	
	@Autowired
	public EmployeeConfirmsManager(EmployeeConfirmsDao employeeConfirmsDao,
															EmployeeDao employeeDao) {
		super();
		this.employeeConfirmsDao = employeeConfirmsDao;
		this.employeeDao = employeeDao;
	}

	@Override
	public DataResult<List<EmployeeConfirms>> getAll() {
		return new SuccessDataResult<List<EmployeeConfirms>>(this.employeeConfirmsDao.findAll(),"EmployeeConfirms listed...");
	}

	@Override
	public Result add(EmployeeConfirms employeeConfirms) {
		
		int employeeId = employeeConfirms.getEmployees().getEmployeeId();
		if(employeeId == 0) {
			return new ErrorResult("Onayın hangi sistem yöneticisinin yaptığını seçiniz..");
		}else if(this.employeeDao.getByEmployeeId(employeeId) == null) {
			return new ErrorResult("Böyle bir sistem yöneticisi bulunmamaktadır..");
		}
		this.employeeConfirmsDao.save(employeeConfirms);
		return new SuccessResult("ForeignLanguage added..");
	}

	@Override
	public DataResult<EmployeeConfirms> getByEmployeeConfirmId(int employeeConfirmId) {
		return new SuccessDataResult<EmployeeConfirms>(this.employeeConfirmsDao.getByEmployeeConfirmId(employeeConfirmId), "EmployeeConfirms listed...");
	}

}
