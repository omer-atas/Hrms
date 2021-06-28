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
import hrms.hrms.dataAcces.abstracts.EmployersDao;
import hrms.hrms.entities.concretes.Employers;

@Service
public class EmployersManager implements EmployersService{
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	private static final String URL_REGEX =
            "^((((https?|ftps?|gopher|telnet|nntp)://)|(mailto:|news:))" +
            "(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:@&=+$,A-Za-z0-9])+)" +
            "([).!';/?:,][[:blank:]])?$";
 
    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);
	
	private EmployersDao employersDao;
	
	@Autowired
	public EmployersManager(EmployersDao employersDao) {
		this.employersDao = employersDao;
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
		if(!validateEmail(employers.getEmail()) &&
				employers.getPhoneNumber().length() != 11 && 
				(validatePhoneNumber(employers.getPhoneNumber()) == null) ) {
			return new ErrorResult("Mistake");
		}else{
			if(!checkEmail(employers.getEmail()) && !checkWebAdress(employers.getWebAddress()) ) {
				return new ErrorResult("Email or web adress registered.");
			}
			if (urlValidator(employers.getWebAddress()) == false) {
				return new ErrorResult("The URL  isn't valid ");
	        }
			if(webAdressControl(employers.getWebAddress(), employers.getEmail()) == false) {
				return new ErrorResult("Web site ve domain aynı değil ");
			}
		}
		
		this.employersDao.save(employers);
		return new SuccessResult("Employers added..");
		
	}


	@Override
	public DataResult<Employers> getByEmail(String email) {
		return new SuccessDataResult<Employers>(this.employersDao.getByEmail(email),"Employers listed..");
	}
	
	private static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
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

}
