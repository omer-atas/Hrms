package hrms.hrms.business.concretes;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.hrms.business.abstracts.CandidatesService;
import hrms.hrms.core.utilies.adapters.abstracts.MernisService;
import hrms.hrms.core.utilies.result.DataResult;
import hrms.hrms.core.utilies.result.ErrorResult;
import hrms.hrms.core.utilies.result.Result;
import hrms.hrms.core.utilies.result.SuccessDataResult;
import hrms.hrms.core.utilies.result.SuccessResult;
import hrms.hrms.dataAcces.abstracts.CandidatesDao;
import hrms.hrms.dataAcces.abstracts.VerificationCodeDao;
import hrms.hrms.entities.concretes.Candidates;

@Service
public class CandidatesManager implements CandidatesService {
	
	private static final String VALID_EMAIL_ADDRESS_REGEX = 
			"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
	

	private CandidatesDao candidatesDao;
	private MernisService mernisService;
	private VerificationCodeDao verificationCodeDao;
	
	@Autowired
	public CandidatesManager(CandidatesDao candidatesDao,MernisService mernisService,
			VerificationCodeDao verificationCodeDao) {
		this.candidatesDao = candidatesDao;
		this.mernisService = mernisService;
		this.verificationCodeDao = verificationCodeDao;
	}

	@Override
	public DataResult<List<Candidates>> getAll() {
		return new SuccessDataResult<List<Candidates>>(this.candidatesDao.findAll(),"Candidates listed..");
	}

	private boolean checkIdentityNumber(String identityNumber) {
		if(this.candidatesDao.getByIdentityNumber(identityNumber) != null) {
			return false;
		}
		return true;
	}
	
	@Override
	public Result add(Candidates candidates) {
		
		int verificationCodeId = candidates.getVerificationCodeCandidates().getVerificationCodeId();
		
		if(candidates.getIdentityNumber().length() != 11 && 
				!this.mernisService.checkIfReal(candidates)) {
			
			return new ErrorResult("Not an identification number or Don't in email format.");
			
		}else {
			if(!checkIdentityNumber(candidates.getIdentityNumber()) && !checkEmail(candidates.getEmail())) {
				return new ErrorResult("There is already a candidate with this ID number or Email registered.");
			}else if(!validateEmail(candidates.getEmail())) {
				return new ErrorResult("Hata");
			}else if(verificationCodeId == 0) {
				return new ErrorResult("Bu iş arayanın doğrulama kodunu giriniz..");
			}else if(this.verificationCodeDao.getByVerificationCodeId(verificationCodeId) == null) {
				return new ErrorResult("Böyle bir doğrulama kodu bulunmamaktadır..");
			}else if((candidates.getBirthYear() == 0) && (candidates.getFirstName() == null) &&
					(candidates.getLastName() == null) && (candidates.getIdentityNumber() == null) &&
					(candidates.getEmail() == null) && (candidates.getPassword() == null)) {
				return new ErrorResult("Hiçbir alan boş bırakılamaz...");
				
			}
				
			this.candidatesDao.save(candidates);
			return new SuccessResult("Candidates added..");
		}
		
	}

	@Override
	public DataResult<Candidates> getByIdentityNumber(String identityNumber) {
		
		return new SuccessDataResult<Candidates>(this.candidatesDao.getByIdentityNumber(identityNumber),"Candidates listed..");
	}

	@Override
	public DataResult<Candidates> getByEmail(String email) {
		return new SuccessDataResult<Candidates>(this.candidatesDao.getByEmail(email),"Candidates listed..");
	}
	
	public static boolean validateEmail(String emailStr) {
		Pattern pattern = Pattern.compile(VALID_EMAIL_ADDRESS_REGEX,
				Pattern.CASE_INSENSITIVE);
				return pattern.matcher(emailStr).find();
	}

	private boolean checkEmail(String email) {
		if(this.candidatesDao.getByEmail(email) != null) {
			return false;
		}
		
		return true;
	}

	@Override
	public DataResult<Candidates> getByCandidateId(int candidateId) {
		return new SuccessDataResult<Candidates>(this.candidatesDao.getByCandidateId(candidateId),"Candidates listed..");
	}
	

}
