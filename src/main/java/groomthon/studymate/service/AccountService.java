package groomthon.studymate.service;


import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import groomthon.studymate.config.auth.jwt.Token;
import groomthon.studymate.config.auth.jwt.TokenService;
import groomthon.studymate.dto.IdTokenRequestDto;
import groomthon.studymate.dto.UserDto;
import groomthon.studymate.entity.Role;
import groomthon.studymate.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {
    NetHttpTransport transport = new NetHttpTransport();
    JsonFactory jsonFactory = new JacksonFactory();
    private GoogleIdTokenVerifier verifier= new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
            .setAudience(Collections.singletonList("214256759740-8tgs7481av48a022sarg2o38mvl1u303.apps.googleusercontent.com"))// 여기 수정함!!!!!
            .build();
    private final UserService userService;
    private final TokenService tokenService;

    //엑세스토큰으로 UserDto 만들고 Dto로 User생성해서 jwt 생성
    public Token loginOAuthGoogle(UserDto userDto) {

        User resultUser = createUser(userDto); // 이놈이 db에 유저 저장하는거다.
        return tokenService.generateToken(resultUser.getEmail(), "USER");//토큰 만드는놈
    }
    //엑세스 토큰 Dto 변환 과정 분리하려고 만든 메서드
    public UserDto accessTokenToUserDto(IdTokenRequestDto requestBody){
        UserDto userDto = verifyIDToken(requestBody.getIdToken());
        if (userDto == null) {
            throw new IllegalArgumentException();
        }
        return userDto;
    }


    public UserDto verifyIDToken(String idToken) {//엑세스 토큰 디코딩해서 UserDto 반환
        try {
            GoogleIdToken idTokenObj = verifier.verify(idToken);// 여기로 안넘어 오는것같음
            if (idTokenObj == null) {
                log.info("verifyIDToken ->> idToken : 생성안됨");
                return null;
            }
            GoogleIdToken.Payload payload = idTokenObj.getPayload();
            String firstName = (String) payload.get("given_name");
            String lastName = (String) payload.get("family_name");
            String email = payload.getEmail();
            String pictureUrl = (String) payload.get("picture");


            return new UserDto().builder().email(email).name(firstName+lastName).picture(pictureUrl).role(Role.MENTEE).build();


        } catch (GeneralSecurityException | IOException e) {
            return null;
        }
    }

    //DB에 유저 생성
    public User createUser(UserDto userDto){
        User foundUser = userService.findByEmail(userDto.getEmail());
        if(foundUser ==null){
            User temp=userService.createUser(userDto);
            return temp;
        }
        return foundUser;

    }

}
