package com.hamishebahar.security.controllers;

import com.hamishebahar.security.commonts.Dto.ResultsServiceDto;
import com.hamishebahar.security.commonts.Dto.UsersDto;
import com.hamishebahar.security.commonts.bundel.BundleManager;
import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import com.hamishebahar.security.commonts.otp.OTPService;
import com.hamishebahar.security.externalservices.impl.SmsService;
import com.hamishebahar.security.jwt.JwtAuth;
import com.hamishebahar.security.jwt.JwtUtils;
import com.hamishebahar.security.jwt.OtpRequest;
import com.hamishebahar.security.users.entity.Users;
import com.hamishebahar.security.users.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.security.Principal;

import static com.hamishebahar.security.commonts.Constans.UriConstants.*;

@RestController
@CrossOrigin
public class UserController {

    private final UsersService usersService;

    private final AuthenticationManager manager;

    private final JwtUtils jwtUtils;

    private final OTPService otpService;

    private final SmsService smsService;

    @Autowired
    public UserController(UsersService usersService, AuthenticationManager manager, JwtUtils jwtUtils, OTPService otpService, SmsService smsService) {
        this.usersService = usersService;
        this.manager = manager;
        this.jwtUtils = jwtUtils;
        this.otpService = otpService;
        this.smsService = smsService;
    }

    @GetMapping(USER_ADMIN_FIND_WITH_FILTER)
    @PreAuthorize(value = "hasAuthority('OP_ACCESS_ADMIN')")
    public ResponseEntity<ResultsServiceDto> findUserWithFilter(@RequestParam(value = "id", required = false) Long id,
                                                                @RequestParam(value = "name", required = false) String name,
                                                                @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                                                                @RequestParam(value = "nationalCode", required = false) String nationalCode,
                                                                @PageableDefault Pageable pageable,
                                                                HttpServletResponse response,
                                                                HttpServletRequest request) throws HamisheBaharException {
        ResultsServiceDto resultsVO = usersService.findUserWithFilter(id, name, phoneNumber, nationalCode , pageable);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }


    @GetMapping(USER_FIND)
    @PreAuthorize(value = "hasAuthority('OP_ACCESS_USER')")
    @PostAuthorize(value = "returnObject.body.result.email == authentication.name")
    public @ResponseBody
    ResponseEntity<ResultsServiceDto> findUser(@PathVariable("id") Long id) throws HamisheBaharException {
        ResultsServiceDto resultsVO = usersService.findUser(id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }


    @PutMapping(USER_UPDATE)
    @PreAuthorize(value = "hasAnyAuthority('OP_EDIT_USER')")
    public ResponseEntity<ResultsServiceDto> updateUser(@PathVariable("id") Long id, @RequestBody UsersDto dto) throws HamisheBaharException {
        ResultsServiceDto resultsVO = usersService.updateUser(dto , id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    @PutMapping(USER_ADMIN_UPDATE)
    @PreAuthorize(value = "hasAnyAuthority('OP_ADMIN_EDIT_USER')")
    public ResponseEntity<ResultsServiceDto> updateAdminUser(@PathVariable("id") Long id, @RequestBody UsersDto dto) throws HamisheBaharException {
        ResultsServiceDto resultsVO = usersService.updateAdminUser(dto,id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    @DeleteMapping(USER_ADMIN_DELETE_WITH_ID)
    @PreAuthorize(value = "hasAnyAuthority('OP_ADMIN_DELETE_USER')")
    public ResponseEntity<ResultsServiceDto> deleteAdminUser(@PathVariable("id") Long id) throws HamisheBaharException {
        ResultsServiceDto resultsVO = usersService.deleteAdminUser(id);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    @PostMapping(USER_REGISTER) //All Access
    public ResponseEntity<ResultsServiceDto> registerUser(@RequestBody UsersDto users) throws HamisheBaharException {
        ResultsServiceDto resultsVO = usersService.registerUser(users);
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    @PostMapping(LOGIN_JWT)
    public @ResponseBody
    ResponseEntity<?> jwtLogin(@RequestBody JwtAuth jwtAuth, HttpServletResponse response) {

        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(jwtAuth.getUsername(), jwtAuth.getPassword()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String Authorization = jwtUtils.generateToken(jwtAuth.getUsername());
        response.addHeader("Authorization", Authorization);
        return ResponseEntity.status(HttpStatus.OK).body(Authorization);
    }

    @PostMapping(LOGIN_OTP)
    public @ResponseBody
    ResponseEntity<?> login(@RequestBody JwtAuth jwtAuth) {
        try {
            Authentication authentication =  manager.authenticate(new UsernamePasswordAuthenticationToken(jwtAuth.getUsername(), jwtAuth.getPassword()));
            Users user = (Users) authentication.getPrincipal();

            String otp = otpService.generateOTP(user.getPhoneNumber());
            user.setOtp(otp);
            user.setOtpVerified(false);
            user.setPassword(null);
            usersService.updateAdminUser(user.convertToDto(),user.getId());

            smsService.Send(user.getPhoneNumber(),otp,otp);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.OK).body("send otp success");
    }

    @PostMapping(LOGIN_VERIFY_OTP)
    public @ResponseBody
    ResponseEntity<?> verifyOtp(@RequestBody OtpRequest request, HttpServletResponse response) throws HamisheBaharException {
        UsersDto user = usersService.findByUsername(request.getUsername());
        if (user == null){
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.user.not.found" , request.getUsername()));
        }

        if (otpService.validateOTP(user.getPhoneNumber(), request.getOtpCode())) {
            user.setOtpVerified(true);
            user.setPassword(null);
            usersService.updateAdminUser(user,user.getId());

            otpService.clearOTP(user.getPhoneNumber());
            // در اینجا می‌توان JWT یا Session ایجاد کرد
            String Authorization = jwtUtils.generateToken(request.getUsername());
            response.addHeader("Authorization", Authorization);
            return ResponseEntity.status(HttpStatus.OK).body(Authorization);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Otp Not Valid");
    }

    @GetMapping(USER_FIND_WITH_TOKEN)
    @PreAuthorize("authentication.name == #principal.name")
    public ResponseEntity<?> findUser(Principal principal){
        return ResponseEntity.status(HttpStatus.OK).body(principal);
    }

    //find All role
    @GetMapping(FIND_ROLE)
    @PreAuthorize(value = "hasAuthority('OP_ACCESS_ADMIN')")
    public ResponseEntity<?> findRoles(HttpServletResponse response,
                                       HttpServletRequest request) throws HamisheBaharException
    {
        ResultsServiceDto resultsVO = usersService.findAllRoles();
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }

    //find All Permission
    @GetMapping(FIND_PERMISSION)
    @PreAuthorize(value = "hasAuthority('OP_ACCESS_ADMIN')")
    public ResponseEntity<?> findPermission(HttpServletResponse response,
                                            HttpServletRequest request) throws HamisheBaharException
    {
        ResultsServiceDto resultsVO = usersService.findAllPermission();
        return ResponseEntity.status(resultsVO.getStatus()).body(resultsVO);
    }
}
