package groomthon.studymate.controller;

import groomthon.studymate.config.auth.jwt.Token;
import groomthon.studymate.dto.IdTokenRequestDto;
import groomthon.studymate.dto.StudyQueryDto;
import groomthon.studymate.dto.StudyResponseDto;
import groomthon.studymate.dto.UserDto;
import groomthon.studymate.entity.User;
import groomthon.studymate.repository.StudyRepository;
import groomthon.studymate.repository.UserRepository;
import groomthon.studymate.service.AccountService;
import groomthon.studymate.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final StudyRepository studyRepository;
    private final UserService userService;
    private final AccountService accountService;
    @GetMapping("/test")
    public String test(){
        return "test입니다";
    }

    @GetMapping("/mytest/findbyemail")
    public String findUser(@RequestParam(name = "email") String email){
        return userService.findByEmail(email).getEmail();
    }

//    @GetMapping("/oauth/google")
//    public void googleCallback(@RequestParam String code){
//        System.out.println(code);
//    }


//    @PostMapping("/login/oauth2/code")
//    public String googleCall(@RequestBody String str){
//        System.out.println(str);
//        return str;
//    }


    @PostMapping("/login/oauth2/code/google")
    public ResponseEntity LoginWithGoogleOauth2(@RequestBody IdTokenRequestDto requestBody, HttpServletResponse response) {
        //내가 수정함

        log.info("loginwithgoogleoauth2 ->> 호출됨");
        UserDto userDto=accountService.accessTokenToUserDto(requestBody);
        Token authToken = accountService.loginOAuthGoogle(userDto); //유저 DTO로 유저 정보 저장하고 토큰 만드는놈
        final ResponseCookie cookie = ResponseCookie
                .from("AUTH-TOKEN", authToken.getToken())
                .httpOnly(true)
                .maxAge(7 * 24 * 3600)//7일
                .path("/")
                .secure(false)
                .build();
        final ResponseCookie refreshCookie = ResponseCookie
                .from("REFRESH-TOKEN", authToken.getRefreshToken())
                .httpOnly(true)
                .maxAge(90 * 24 * 3600)//90일
                .path("/")
                .secure(false)
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());
        log.info("loginwithgoogleoauth2 ->> 토큰 반환됨");
        return ResponseEntity.ok().build();
    }



}
