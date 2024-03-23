package groomthon.studymate.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import groomthon.studymate.entity.tag.Frequency;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    MENTEE( "멘티"),
    MENTOR( "멘토");



    private final String title;

    public String getTitle(){
        return title;
    }

    @JsonValue
    public String toJson() {
        return title;
    }

    @JsonCreator
    public static Role fromJson(String value) {
        for (Role role : Role.values()) {
            if (role.title.equals(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid Frequency value: " + value);
    }
}