package hrms.hrms.core.utilies.adapters.concretes;

import hrms.hrms.core.utilies.adapters.abstracts.MernisService;
import hrms.hrms.entities.concretes.Candidates;
import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

public class MernisManager implements MernisService{

	@Override
	public boolean checkIfReal(Candidates candidates) {
		KPSPublicSoapProxy client=new KPSPublicSoapProxy();
		boolean result=false;
		try {
			result = client.TCKimlikNoDogrula(
						Long.valueOf(candidates.getIdentityNumber()), 
						candidates.getFirstName(), 
						candidates.getLastName(), 
						candidates.getBirthYear());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
