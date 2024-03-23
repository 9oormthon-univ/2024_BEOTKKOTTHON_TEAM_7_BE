package groomthon.studymate.service;

import groomthon.studymate.config.S3Uploader;
import groomthon.studymate.entity.MentorImage;
import groomthon.studymate.entity.Mentoring;
import groomthon.studymate.entity.MentoringImage;
import groomthon.studymate.entity.User;
import groomthon.studymate.repository.MentorImageRepository;
import groomthon.studymate.repository.MentoringImageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class MentoringImageService {
    private final MentoringImageRepository mentoringImageRepository;

    private final S3Uploader s3Uploader;



    @Transactional
    public void createImage(MultipartFile file, Mentoring mentoring) {
        String url = "";
        if(!file.isEmpty()) {
            url = s3Uploader.uploadFileToS3(file, "static/team-image");
            mentoringImageRepository.save(new MentoringImage(url,mentoring));
            log.info(url+" 저장됨");
        }
    }
}
