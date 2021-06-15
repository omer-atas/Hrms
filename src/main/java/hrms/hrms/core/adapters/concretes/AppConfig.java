package hrms.hrms.core.adapters.concretes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import hrms.hrms.core.adapters.abstracts.CloudinaryService;

@Configuration
public class AppConfig {
	
	@Bean
    public Cloudinary cloudinaryUser(){
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "defal1az2",
                "api_key", "872453487958628",
                "api_secret", "Cy_M_GwtxuTyw9_MCxYecOg1j3g"));
    }

    @Bean
    public CloudinaryService cloudinaryService(){
        return new CloudinaryManager(cloudinaryUser());
    }

}
