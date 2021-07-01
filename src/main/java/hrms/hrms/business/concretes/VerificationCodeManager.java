package hrms.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.hrms.business.abstracts.VerificationCodeService;
import hrms.hrms.core.utilies.result.DataResult;
import hrms.hrms.core.utilies.result.Result;
import hrms.hrms.core.utilies.result.SuccessDataResult;
import hrms.hrms.core.utilies.result.SuccessResult;
import hrms.hrms.dataAcces.abstracts.VerificationCodeDao;
import hrms.hrms.entities.concretes.VerificationCode;
@Service
public class VerificationCodeManager implements VerificationCodeService{
	
	private VerificationCodeDao verificationCodeDao;
	
	@Autowired
	public VerificationCodeManager(VerificationCodeDao verificationCodeDao) {
		super();
		this.verificationCodeDao = verificationCodeDao;
	}

	@Override
	public DataResult<List<VerificationCode>> getAll() {
		return new SuccessDataResult<List<VerificationCode>>(this.verificationCodeDao.findAll(),"Data listelendi..");
	}

	@Override
	public Result add(VerificationCode verificationCode) {
		this.verificationCodeDao.save(verificationCode);
		return new SuccessResult("VerificationCode added..");
	}

	@Override
	public DataResult<VerificationCode> getByVerificationCodeId(int verificationCodeId) {
		return new SuccessDataResult<VerificationCode>(this.verificationCodeDao.getByVerificationCodeId(verificationCodeId), "Data listelendi..");
	}

}
