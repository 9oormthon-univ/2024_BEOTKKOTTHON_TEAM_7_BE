package groomthon.studymate.service;

import groomthon.studymate.config.S3Uploader;
import groomthon.studymate.entity.Done;
import groomthon.studymate.entity.Image;
import groomthon.studymate.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {
    private final S3Uploader s3Uploader;

    private final ImageRepository imageRepository;


    @Transactional
    public void createImage( MultipartFile file,Done done) {
        String url = "";
        if(!file.isEmpty()) {
            url = s3Uploader.uploadFileToS3(file, "static/team-image");
            imageRepository.save(new Image(url,done));
            log.info(url+" 저장됨");
        }

    }


}
