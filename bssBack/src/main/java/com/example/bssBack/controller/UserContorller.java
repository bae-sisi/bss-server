package com.example.bssBack.controller;

import com.example.bssBack.entity.User;
import com.example.bssBack.service.UserService;
import com.example.bssBack.utility.Security;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserContorller {

    private UserService userService;

    @Autowired
    public UserContorller(UserService userService){
        this.userService = userService;
    }


    @GetMapping("/auth/get/user")
    public ResponseEntity getUser(){
        User user = (User) userService.loadUserById(Security.getCurrentSid());

        HashMap<String, Object> result = new HashMap<>();
        result.put("sid", user.getSid());
        result.put("username", user.getUsername());
        result.put("email", user.getEmail());
        result.put("id", user.getId());

        System.out.println(result);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path = "/login")
    public ResponseEntity login(final HttpServletRequest req,
                                final HttpServletResponse res,
                                @RequestBody Map<String, String> request) throws Exception {

        try {
            String token = userService.tryLogin(request.get("sid"), request.get("password"));
            Cookie tokenCookie = createTokenCookie(token, 168 * 60 * 60);
            res.addCookie(tokenCookie);

            HashMap<String, Object> result = new HashMap<>();
            result.put("result", "로그인에 성공하였습니다.");
            return new ResponseEntity(result, HttpStatus.OK);
        }
        catch(Exception e) {
            Cookie tokenCookie = createTokenCookie(null, 0);
            res.addCookie(tokenCookie);

            HashMap<String, Object> result = new HashMap<>();
            result.put("result", "아이디 또는 비밀번호가 잘못되었습니다.");
            return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/logout")
    public ResponseEntity logout(final HttpServletRequest req, final HttpServletResponse res) {
        Cookie tokenCookie = createTokenCookie(null, 0);
        res.addCookie(tokenCookie);

        HashMap<String, Object> result = new HashMap<>();
        result.put("result", "로그아웃에 성공하였습니다.");
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping(path = "/auth/currentuser")
    public ResponseEntity getCurrentUserData() {
        HashMap<String, Object> result = new HashMap<>();

        String sid = Security.getCurrentSid();

        result.put("sid", sid);
        result.put("Authorities", Security.getCurrentUserRole());

        try {
            User currentUser = (User)userService.loadUserById(sid);
            result.put("role", currentUser.getRole());
            result.put("email", currentUser.getEmail());
            result.put("sid", currentUser.getSid());
            result.put("username", currentUser.getUsername());
        } catch (Exception e){
            // 로그인되지 않았거나 오류난 경우
        }

        return new ResponseEntity(result, HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity createUser(@RequestParam("sid") String sid,
                                     @RequestParam("username") String username,
                                     @RequestParam("password") String password,
                                     @RequestParam("email") String email) {
        if( sid != null && !sid.isBlank() &&
                username != null && !username.isBlank() &&
                password != null && !password.isBlank() &&
                email != null && !email.isBlank()) {

            try {
                userService.create(
                        sid,
                        username,
                        password,
                        email
                );

                HashMap<String, Object> result = new HashMap<>();
                result.put("result", "회원가입에 성공하였습니다.");
                return new ResponseEntity(result, HttpStatus.CREATED);
            }
            catch (Exception e) {
                HashMap<String, Object> result = new HashMap<>();
                result.put("result", "회원가입에 실패하였습니다.");
                return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
            }
        }
        else {
            HashMap<String, Object> result = new HashMap<>();
            result.put("result", "회원가입에 실패하였습니다.");
            return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/auth/user/change/password/{sid}")
    public ResponseEntity UpdateUserINFO(@PathVariable("sid") String sid, @RequestBody Map<String, String> body){
        System.out.printf(sid);
        System.out.println(Security.getCurrentSid());
        if (!sid.isBlank() && sid.equals(Security.getCurrentSid()) &&
                body.get("password") != null && !body.get("password").isBlank()) {

            System.out.println(body);

            try {
                userService.change(
                        sid,
                        body.get("password"),
                        body.get("newPassword")
                );

                HashMap<String, Object> result = new HashMap<>();
                result.put("result", "회원 정보 수정에 성공하였습니다.");
                return new ResponseEntity(result, HttpStatus.ACCEPTED);
            }
            catch (Exception e) {
                HashMap<String, Object> result = new HashMap<>();
                result.put("result", "회원 정보 수정에 실패하였습니다.");
                return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
            }
        }
        else {
            HashMap<String, Object> result = new HashMap<>();
            result.put("result", "비밀번호 또는 아이디가 일치하지 않습니다.");
            return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/auth/user/change/info/{sid}")
    public ResponseEntity updateUser(@PathVariable("sid") String sid,
                                     @RequestBody Map<String, String> body) {
        System.out.printf(sid);
        System.out.println(Security.getCurrentSid());
        if (!sid.isBlank() && sid.equals(Security.getCurrentSid())) {

            System.out.println(body);

            try {
                userService.update(
                        sid,
                        body.get("username"),
                        body.get("email")
                );

                HashMap<String, Object> result = new HashMap<>();
                result.put("result", "회원 정보 수정에 성공하였습니다.");
                return new ResponseEntity(result, HttpStatus.ACCEPTED);
            }
            catch (Exception e) {
                HashMap<String, Object> result = new HashMap<>();
                result.put("result", "회원 정보 수정에 실패하였습니다.");
                return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
            }
        }
        else {
            HashMap<String, Object> result = new HashMap<>();
            result.put("result", "아이디가 일치하지 않습니다.");
            return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/checkforduplicate")
    @ResponseBody
    public ResponseEntity CanUseAsUsername(@RequestBody Map<String, String> body) {
        if (body.get("sid") != null && !body.get("sid").isBlank() &&
                userService.canUseAsSid(body.get("sid"))) {
            HashMap<String, Object> result = new HashMap<>();
            result.put("result", true);
            return new ResponseEntity(result, HttpStatus.OK);
        }
        else {
            HashMap<String, Object> result = new HashMap<>();
            result.put("result", false);
            return new ResponseEntity(result, HttpStatus.OK);
        }
    }

    private Cookie createTokenCookie(String token, int age) {
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(age);
        cookie.setPath("/");
        return cookie;
    }

}
