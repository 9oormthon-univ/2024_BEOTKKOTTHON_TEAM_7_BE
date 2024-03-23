package groomthon.studymate.service;

import groomthon.studymate.config.S3Uploader;
import groomthon.studymate.entity.Mentor;
import groomthon.studymate.entity.MentorImage;
import groomthon.studymate.entity.User;
import groomthon.studymate.repository.MentorImageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class MentorImageService {
    private final S3Uploader s3Uploader;

    private final MentorImageRepository mentorImageRepository;


    @Transactional
    public void createImage(MultipartFile file, User user) {
        String url = "";
        if(!file.isEmpty()) {
            url = s3Uploader.uploadFileToS3(file, "static/team-image");
            mentorImageRepository.save(new MentorImage(url,user));
            log.info(url+" 저장됨");
        }
    }
}