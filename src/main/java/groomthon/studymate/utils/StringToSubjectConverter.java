package groomthon.studymate.utils;

import groomthon.studymate.entity.tag.Frequency;
import groomthon.studymate.entity.tag.Subject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToSubjectConverter implements Converter<String, Subject> {
    @Override
    public Subject convert(String source) {
        try {
            switch (source){
                case "어학":
                    return Subject.LANGUAGE;
                case "자격증":
                    return Subject.CERTIFICATE;
                case "전공":
                    return Subject.MAJOR;
                case "기타":
                    return Subject.ETC;
                default:
                    return null;
            }
        } catch (IllegalArgumentException e) {
            // 만약 변환이 실패하면 적절한 에러 처리를 수행할 수 있습니다.
            return null; // 예시로 null을 반환하도록 하였습니다. 실제로는 예외 처리를 하거나 기본값을 반환하는 등의 처리가 필요합니다.
        }
    }

}
