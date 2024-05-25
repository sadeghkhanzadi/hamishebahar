package com.hamishebahar.security.commonts.utils;

import com.hamishebahar.security.commonts.Dto.MediasDto;
import com.hamishebahar.security.commonts.Dto.UsersDto;
import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import com.hamishebahar.security.users.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsersUtils {
    private final UsersService usersService;

    @Autowired
    public UsersUtils(UsersService usersService) {
        this.usersService = usersService;
    }

    public List<UsersDto> findUsers(List<UsersDto> users) throws HamisheBaharException {
        return usersService.findUsers(
                users.stream().map(UsersDto::getId).collect(Collectors.toList())
        );
    }
}
