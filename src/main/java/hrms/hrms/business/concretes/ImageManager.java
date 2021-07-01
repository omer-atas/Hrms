package hrms.hrms.business.concretes;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import hrms.hrms.business.abstracts.ImageService;
import hrms.hrms.core.adapters.abstracts.CloudinaryService;
import hrms.hrms.core.utilies.result.DataResult;
import hrms.hrms.core.utilies.result.ErrorResult;
import hrms.hrms.core.utilies.result.Result;
import hrms.hrms.core.utilies.result.SuccessDataResult;
import hrms.hrms.core.utilies.result.SuccessResult;
import hrms.hrms.dataAcces.abstracts.CurriculumVitaeDao;
import hrms.hrms.dataAcces.abstracts.ImageDao;
import hrms.hrms.entities.concretes.Image;

@Service
public class ImageManager implements ImageService {
	
	private ImageDao imageDao;
	private CloudinaryService cloudinaryService;
	private CurriculumVitaeDao curriculumVitaeDao;
	
	@Autowired
	public ImageManager(ImageDao imageDao,CloudinaryService cloudinaryService,CurriculumVitaeDao curriculumVitaeDao) {
		super();
		this.imageDao = imageDao;
		this.cloudinaryService = cloudinaryService;
		this.curriculumVitaeDao = curriculumVitaeDao;
	}

	@Override
	public DataResult<List<Image>> getAll() {
		return new SuccessDataResult<List<Image>>(this.imageDao.findAll(),"Images listed..");
	}

	
	@Override
	public Result add(Image image, MultipartFile imageFile) {
		
		int curriculumVitaeId = image.getCurriculumVitaeImage().getCvId();
		
		if(curriculumVitaeId == 0) {
			return new ErrorResult("Fotoğrafın hangi özgeçmişe ait olduğunu giriniz..");
		}else if(this.curriculumVitaeDao.getByCvId(curriculumVitaeId) == null) {
			return new ErrorResult("Böyle bir özgeçmiş bulunmamaktadır..");
		}
		
		@SuppressWarnings("unchecked")
        Map<String, String> uploader = (Map<String, String>) 
                cloudinaryService.save(imageFile).getData(); 
        String imageUrl= uploader.get("url");
        image.setImage_url(imageUrl);
        this.imageDao.save(image);
        return new SuccessResult("Kayıt Başarılı");
	}

	@Override
	public DataResult<Image> getByImageId(int imageId) {
		return new SuccessDataResult<Image>(this.imageDao.getByImageId(imageId));
	}

}
