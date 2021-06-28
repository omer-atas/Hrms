package hrms.hrms.business.concretes;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.hrms.business.abstracts.EmployeesService;
import hrms.hrms.core.utilies.result.DataResult;
import hrms.hrms.core.utilies.result.ErrorResult;
import hrms.hrms.core.utilies.result.Result;
import hrms.hrms.core.utilies.result.SuccessDataResult;
import hrms.hrms.core.utilies.result.SuccessResult;
import hrms.hrms.dataAcces.abstracts.EmployeeDao;
import hrms.hrms.entities.concretes.Employees;

@Service
public class EmployeesManager implements EmployeesService{
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	private EmployeeDao employeesDao;
	
	@Autowired
	public EmployeesManager(EmployeeDao employeesDao) {
		super();
		this.employeesDao = employeesDao;
	}

	@Override
	public DataResult<List<Employees>> getAll() {
		return new SuccessDataResult<List<Employees>>(this.employeesDao.findAll(),"Employers listed..");
	}

	@Override
	public Result add(Employees employees) {
		if(!validateEmail(employees.getEmail())) {
			return new ErrorResult("Don't in email format.");
		}else {
			if(!checkEmail(employees.getEmail())) {
				return new ErrorResult("Email registered.");
			}
			
			this.employeesDao.save(employees);
			return new SuccessResult("Employers added..");
		}
		
	}

	@Override
	public DataResult<Employees> getByEmail(String email) {
		return new SuccessDataResult<Employees>(this.employeesDao.getByEmail(email),"Employers listed..");
	}
	
	public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
	}

	private boolean checkEmail(String email) {
		if(this.employeesDao.getByEmail(email) != null) {
			return false;
		}
		
		return true;
	}

}
