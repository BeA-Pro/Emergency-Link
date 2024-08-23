package com.emergency_link.emergency_link.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

//    private final UserInfoRepository userInfoRepository;
//    private final JwtUtil jwtUtil;
//
//    @Autowired
//    public LoginController(UserInfoRepository userInfoRepository, JwtUtil jwtUtil) {
//        this.userInfoRepository = userInfoRepository;
//        this.jwtUtil = jwtUtil;
//    }
//
//
//    @PostMapping("/login")
//    public ResponseEntity<Map<String, String>> login(@RequestBody LoginData loginData){
//        System.out.println(loginData.getUserId()+" "+loginData.getPwd());
//        Optional<UserInfo> userInfo = userInfoRepository.findByUserIdAndUserPwd(loginData.getUserId(), loginData.getPwd());
//        Map<String, String> responseBody = new HashMap<>();
//        if(userInfo.isPresent()){
//            System.out.println("exist");
//            String token = jwtUtil.generateToken(userInfo.get().getUserId(), 1);
//
//            responseBody.put("status", "success");
//            responseBody.put("token", token);
//
//            return ResponseEntity.ok().body(responseBody);
//        }
//        System.out.println("Not exist");
//
//        responseBody.put("status", "fail");
//        responseBody.put("message", "Login fail");
//
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
//    }
}
