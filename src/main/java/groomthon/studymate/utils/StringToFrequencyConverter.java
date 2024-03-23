package groomthon.studymate.utils;

import groomthon.studymate.entity.tag.Frequency;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToFrequencyConverter implements Converter<String, Frequency> {

    @Override
    public Frequency convert(String source) {
        try {
            switch (source){
                case "한번":
                    return Frequency.ONE;
                case "두번":
                    return Frequency.TWO;
                case "세번":
                    return Frequency.THREE;
                case "네번 이상":
                    return Frequency.FOURPLUS;
                default:
                    return null;

            }

        } catch (IllegalArgumentException e) {
            // 만약 변환이 실패하면 적절한 에러 처리를 수행할 수 있습니다.
            return null; // 예시로 null을 반환하도록 하였습니다. 실제로는 예외 처리를 하거나 기본값을 반환하는 등의 처리가 필요합니다.
        }
    }
}