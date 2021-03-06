package hrms.hrms.business.concretes;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.hrms.business.abstracts.DepartmentService;
import hrms.hrms.core.utilies.result.DataResult;
import hrms.hrms.core.utilies.result.ErrorResult;
import hrms.hrms.core.utilies.result.Result;
import hrms.hrms.core.utilies.result.SuccessDataResult;
import hrms.hrms.core.utilies.result.SuccessResult;
import hrms.hrms.dataAcces.abstracts.DepartmentDao;
import hrms.hrms.entities.concretes.Department;

@Service
public class DepartmentManager implements DepartmentService{
	
	private DepartmentDao departmentDao;
	
	@Autowired
	public DepartmentManager(DepartmentDao departmentDao) {
		super();
		this.departmentDao = departmentDao;
	}

	@Override
	public DataResult<List<Department>> getAll() {
		return new SuccessDataResult<List<Department>>(this.departmentDao.findAll(),"Departments listed..");
	}

	@Override
	public Result add(Department department) {
		
		if(this.departmentDao.getByTitle(department.getTitle()) != null) {
			return new ErrorResult("There is department..");
		}
		this.departmentDao.save(department);
		return new SuccessResult("Department added..");
	}

	@Override
	public DataResult<Department> getByTitle(String title) {
		return new SuccessDataResult<Department>(this.departmentDao.getByTitle(title),"Departments listed..");
	}

	@Override
	public DataResult<Department> getByJobTitleId(int jobTitleId) {
		return new SuccessDataResult<Department>(this.departmentDao.getByJobTitleId(jobTitleId),"Departments listed..");
	}
	
	
}
