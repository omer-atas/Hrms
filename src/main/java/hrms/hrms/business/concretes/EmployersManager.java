package hrms.hrms.business.concretes;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.hrms.business.abstracts.EmployersService;
import hrms.hrms.core.utilies.result.DataResult;
import hrms.hrms.core.utilies.result.ErrorResult;
import hrms.hrms.core.utilies.result.Result;
import hrms.hrms.core.utilies.result.SuccessDataResult;
import hrms.hrms.core.utilies.result.SuccessResult;
import hrms.hrms.dataAcces.abstracts.EmployeeConfirmsDao;
import hrms.hrms.dataAcces.abstracts.EmployersDao;
import hrms.hrms.dataAcces.abstracts.VerificationCodeDao;
import hrms.hrms.entities.concretes.Employers;

@Service
public class EmployersManager implements EmployersService{
	
	private static final String VALID_EMAIL_ADDRESS_REGEX = 
			"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
	
	private static final String URL_REGEX =
            "^((((https?|ftps?|gopher|telnet|nntp)://)|(mailto:|news:))" +
            "(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:@&=+$,A-Za-z0-9])+)" +
            "([).!';/?:,][[:blank:]])?$";
 
    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);
	
	private EmployersDao employersDao;
	private VerificationCodeDao verificationCodeDao;
	private EmployeeConfirmsDao employeeConfirmsDao;
	
	@Autowired
	public EmployersManager(EmployersDao employersDao,VerificationCodeDao verificationCodeDao,
									EmployeeConfirmsDao employeeConfirmsDao) {
		this.employersDao = employersDao;
		this.verificationCodeDao = verificationCodeDao;
		this.employeeConfirmsDao = employeeConfirmsDao;
	}


	@Override
	public DataResult<List<Employers>> getAll() {
		return new SuccessDataResult<List<Employers>>(this.employersDao.findAll(),"Employers listed..");
	}

	private static boolean webAdressControl(String webadress, String email) {
		String emailSplit= email.split("@")[1];
		
		if (webadress.contains(emailSplit)) 
			return true;
		return false;
	}

	@Override
	public Result add(Employers employers) {
		
		int verificationCodeId = employers.getVerificationCodeEmployers().getVerificationCodeId();
		int employeeConfirmId = employers.getEmployeeConfirmsEmployers().getEmployeeConfirmId();
		
		if(employers.getPhoneNumber().length() != 11 && 
				(validatePhoneNumber(employers.getPhoneNumber()) == null) ) {
			return new ErrorResult("Mistake");
		}else{
			if(!checkEmail(employers.getEmail()) && !checkWebAdress(employers.getWebAddress()) ) {
				return new ErrorResult("Email or web adress registered.");
			}
			if (urlValidator(employers.getWebAddress()) == false) {
				return new ErrorResult("The URL  isn't valid ");
	        }else if(!validateEmail(employers.getEmail())) {
				return new ErrorResult("Hata");
			}
			if(webAdressControl(employers.getWebAddress(), employers.getEmail()) == false) {
				return new ErrorResult("Web site ve domain aynı değil ");
			}
			if(employers.getEmployeeConfirmsEmployers().getEmployeeConfirmId() == 0 &&
					employers.getVerificationCodeEmployers().getVerificationCodeId() == 0) {
				return new ErrorResult("Employerin doğrulanıp doğrulanmadığını ve ya sistem yöneticisi onayı olup olmadığını anlamak için gerekli id yi giriniz.. ");
				
			}
			
		}
		
		if(verificationCodeId == 0) {
			return new ErrorResult("Bu iş verenin doğrulama kodunu giriniz..");
		}else if(this.verificationCodeDao.getByVerificationCodeId(verificationCodeId) == null) {
			return new ErrorResult("Böyle bir doğrulama kodu bulunmamaktadır..");
		}else if(employeeConfirmId == 0) {
			return new ErrorResult("İş verenin sistem yöneticisi onayı olup olmadığını anlamak için gerekli kısmı/kimliği giriniz.");
		}else if(this.employeeConfirmsDao.getByEmployeeConfirmId(employeeConfirmId) == null) {
			return new ErrorResult("Böyle bir sistem yöneticisi onayıkodu bulunmamaktadır..");
		}
		if((employers.getCompanyName() == null) && (employers.getEmail()==null) && 
				(employers.getPhoneNumber()==null) && (employers.getWebAddress()==null) &&
				(employers.getPassword()==null)) {
			return new ErrorResult("Hiçbir alan boş bırakılamaz...");
		}
		this.employersDao.save(employers);
		return new SuccessResult("Employers added..");
		
	}


	@Override
	public DataResult<Employers> getByEmail(String email) {
		return new SuccessDataResult<Employers>(this.employersDao.getByEmail(email),"Employers listed..");
	}
	
	public static boolean validateEmail(String emailStr) {
		Pattern pattern = Pattern.compile(VALID_EMAIL_ADDRESS_REGEX,
				Pattern.CASE_INSENSITIVE);
				return pattern.matcher(emailStr).find();
	}


	public static boolean urlValidator(String webAddress)
    {
        if (webAddress == null) {
            return false;
        }else {
            Matcher matcher = URL_PATTERN.matcher(webAddress);
            return matcher.matches();
        }
    }

	private Result validatePhoneNumber(String phoneNumber) {
		String patterns = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
				+ "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
				+ "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
		Pattern pattern=Pattern.compile(patterns);
		Matcher matcher=pattern.matcher(phoneNumber);
		if(!matcher.matches()) {
			return new ErrorResult("Telefon Numarası Geçersiz");
		}
		return new SuccessResult();
	}
	
	private boolean checkEmail(String email) {
		if(this.employersDao.getByEmail(email) != null) {
			return false;
		}
		
		return true;
	}
	
	private boolean checkWebAdress(String webAdress) {
		if(this.employersDao.getByWebAddress(webAdress) != null) {
			return false;
		}
		
		return true;
	}

	@Override
	public DataResult<Employers> getByWebAddress(String webAdress) {
		return new SuccessDataResult<Employers>(this.employersDao.getByWebAddress(webAdress),"Employers listed..");
	}


	@Override
	public DataResult<Employers> getByEmployersId(int employersId) {
		return new SuccessDataResult<Employers>(this.employersDao.getByEmployersId(employersId), "Employers listed..");
	}

}
