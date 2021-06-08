package hrms.hrms.business.configration;

import org.springframework.context.annotation.Bean;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;

public class AppConfigration {
	@Bean
	
	  public Cloudinary cloudinaryService(){
		return new Cloudinary(
              ObjectUtils.asMap(
                      "cloud_name","defal1az2",
                      "api_key", "872453487958628",
                      "api_secret","Cy_M_GwtxuTyw9_MCxYecOg1j3g"
              )
			);
	}
}
