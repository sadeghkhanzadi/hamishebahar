package com.hamishebahar.security.controllers;

import com.hamishebahar.security.commonts.Dto.ResultsServiceDto;
import com.hamishebahar.security.commonts.Dto.UsersDto;
import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import com.hamishebahar.security.jwt.JwtAuth;
import com.hamishebahar.security.jwt.JwtUtils;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.hamishebahar.security.commonts.Constans.UriConstants.*;

@RestController
public class UserController {

    private final UsersService usersService;

    private final AuthenticationManager manager;

    private final JwtUtils jwtUtils;

    @Autowired
    public UserController(UsersService usersService, AuthenticationManager manager, JwtUtils jwtUtils) {
        this.usersService = usersService;
        this.manager = manager;
        this.jwtUtils = jwtUtils;
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
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        response.addHeader("Authorization", jwtUtils.generateToken(jwtAuth.getUsername()));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
