package hrms.hrms.core.utilies.adapters.concretes;

import org.springframework.stereotype.Service;

import hrms.hrms.core.utilies.adapters.abstracts.EmailVerificationService;

@Service
public class EmailVerifyManager  implements EmailVerificationService{

	@Override
	public boolean verificationEmail() {
		System.out.println("Verificationed...");
		return false;
	}

}
