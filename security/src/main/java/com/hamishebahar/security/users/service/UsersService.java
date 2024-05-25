package com.hamishebahar.security.users.service;

import com.hamishebahar.security.commonts.Dto.MediasDto;
import com.hamishebahar.security.commonts.Dto.StudentDto;
import com.hamishebahar.security.commonts.Dto.UsersDto;
import com.hamishebahar.security.commonts.Dto.ResultsServiceDto;
import com.hamishebahar.security.commonts.bundel.BundleManager;
import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import com.hamishebahar.security.commonts.utils.StringUtils;
import com.hamishebahar.security.commonts.utils.VerifyObjectUtils;
import com.hamishebahar.security.users.entity.Users;
import com.hamishebahar.security.users.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UsersService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usersRepository.findByEmail(email);
    }

    public ResultsServiceDto deleteAdminUser(Long id) throws HamisheBaharException {
        ResultsServiceDto resultsServiceDto = new ResultsServiceDto.Builder()
                .Result(null)
                .Status(HttpStatus.BAD_REQUEST)
                .build();
        if (!StringUtils.hasText(String.valueOf(id))) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        if (!isExists(id)) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.entity.is.not.exists", String.valueOf(id)));
        }
        UsersDto dto = findById(id);
        if (dto != null) {
            try {
                dto.setEnabled(false);
                usersRepository.save(dto.convertToEntity());
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result("id = " + id)
                        .Status(HttpStatus.OK)
                        .build();
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        BundleManager.wrapKey("error.server"));
            }
        }
        return resultsServiceDto;
    }

    public ResultsServiceDto updateAdminUser(UsersDto dto, Long id) throws HamisheBaharException {
        if (dto.getId() == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.not.valid", "**id**"));
        }
        if (id == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        if (!dto.getId().equals(id)) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.not.valid", "**id**"));
        }
        if (!isExists(id)) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.entity.is.not.exists", String.valueOf(id)));
        }
        if (!dto.getId().equals(id) ||
                dto.getName() == null ||
                dto.getEmail() == null ||
                dto.getPhoneNumber() == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        UsersDto vo = findById(id);
        UsersDto UsersDto = null;
        if (vo != null) {
            try {
                dto = dto.updaterFields(vo);
                UsersDto = usersRepository.save(dto.convertToEntity()).convertToDto();
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        BundleManager.wrapKey("error.server"));
            }
        }
        return new ResultsServiceDto.Builder().Status(HttpStatus.OK).Result(UsersDto).build();
    }

    @PreAuthorize("#users.email != authentication.name")
    public ResultsServiceDto updateUser(UsersDto users, Long id) throws HamisheBaharException {
        if (users.getId() == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.not.valid", "**id**"));
        }
        if (id == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        if (!users.getId().equals(id)) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.not.valid", "**id**"));
        }
        if (!isExists(id)) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.entity.is.not.exists", String.valueOf(id)));
        }
        if (users.getName() == null ||
                users.getEmail() == null ||
                users.getPhoneNumber() == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        UsersDto vo = findById(id);
        UsersDto UsersDto = null;
        if (vo != null) {
            try {
                users = users.updaterFields(vo);
                UsersDto = usersRepository.save(users.convertToEntity()).convertToDto();
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        BundleManager.wrapKey("error.server"));
            }
        }
        return new ResultsServiceDto.Builder().Status(HttpStatus.OK).Result(UsersDto).build();
    }

    @Transactional
    public ResultsServiceDto registerUser(UsersDto users) throws HamisheBaharException {
        if (users.getId() != null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.not.valid", "**id**"));
        }
        if (users.getName() == null ||
                users.getEmail() == null ||
                users.getPhoneNumber() == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        try {
            if (isExists(users.getEmail(), "EMAIL")) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        BundleManager.wrapKey("error.entity.is.exists", users.getEmail()));
            }
            if (isExists(users.getPhoneNumber(), "PHONE_NUMBER")) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        BundleManager.wrapKey("error.entity.is.exists", users.getPhoneNumber()));
            }
            UsersDto usersDto = usersRepository.saveAndFlush(users.convertToEntity()).convertToDto();
            return new ResultsServiceDto.Builder().Status(HttpStatus.OK).Result(usersDto).build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    public ResultsServiceDto findUser(Long id) throws HamisheBaharException {
        if (id == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.parameter.is.null"));
        }
        if (!isExists(id)) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    BundleManager.wrapKey("error.entity.is.not.exists", String.valueOf(id)));
        }
        return new ResultsServiceDto.Builder()
                .Result(findById(id))
                .Status(HttpStatus.OK)
                .build();
    }

    public ResultsServiceDto findUserWithFilter(Long id, String name, String phoneNumber,
                                                String nationalCode, Pageable pageable) throws HamisheBaharException {
        ResultsServiceDto resultsServiceDto = new ResultsServiceDto.Builder()
                .Result(null)
                .Status(HttpStatus.BAD_REQUEST)
                .build();
        try {
            if (StringUtils.hasText(name) && StringUtils.hasText(phoneNumber) && StringUtils.hasText(nationalCode)) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(usersRepository.findAllByNameAndPhoneNumberAndNationalCode(name, phoneNumber, nationalCode, pageable)
                                .map(Users::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if (StringUtils.hasText(name) && StringUtils.hasText(phoneNumber)) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(usersRepository.findAllByNameAndPhoneNumber(name, phoneNumber, pageable)
                                .map(Users::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if (StringUtils.hasText(name) && StringUtils.hasText(nationalCode)) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(usersRepository.findAllByNameAndNationalCode(name, nationalCode, pageable)
                                .map(Users::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if (StringUtils.hasText(phoneNumber) && StringUtils.hasText(nationalCode)) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(usersRepository.findAllByPhoneNumberAndNationalCode(phoneNumber, nationalCode, pageable)
                                .map(Users::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if (StringUtils.hasText(String.valueOf(id))) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(usersRepository.findAllById(id, pageable)
                                .map(Users::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if (StringUtils.hasText(String.valueOf(name))) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(usersRepository.findAllByName(name, pageable)
                                .map(Users::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if (StringUtils.hasText(String.valueOf(phoneNumber))) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(usersRepository.findAllByPhoneNumber(phoneNumber, pageable)
                                .map(Users::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else if (StringUtils.hasText(String.valueOf(nationalCode))) {
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result(usersRepository.findAllByNationalCode(nationalCode, pageable)
                                .map(Users::convertToDto))
                        .Status(HttpStatus.OK)
                        .build();
            } else { //all of
                resultsServiceDto = findALL(pageable);
            }
            return resultsServiceDto;
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    private ResultsServiceDto findALL(Pageable pageable) throws HamisheBaharException {
        try {
            return new ResultsServiceDto.Builder().Result(
                            usersRepository.findAll(pageable)
                                    .map(Users::convertToDto)
                    )
                    .Status(HttpStatus.OK)
                    .build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }


    public List<UsersDto> findAll() {
        return usersRepository.findAll().stream().map(Users::convertToDto).collect(Collectors.toList());
    }

    public UsersDto findById(Long id) {
        return usersRepository.getOne(id).convertToDto();
    }

    @PreAuthorize("#users.email != authentication.name")
    public void deleteById(UsersDto users) {
        usersRepository.deleteById(users.getId());
    }

    public Boolean isExists(Long id) throws HamisheBaharException {
        if (id != null) {
            try {
                return this.usersRepository.existsById(id);
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        BundleManager.wrapKey("error.server"));
            }
        }
        throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                BundleManager.wrapKey("error.parameter.is.null"));
    }

    private boolean isExists(String identity, String IDENTITY_TYPE) throws HamisheBaharException {
        if (identity != null && IDENTITY_TYPE != null) {
            switch (IDENTITY_TYPE) {
                case "EMAIL":
                    try {
                        return this.usersRepository.existsUsersByEmail(identity);
                    } catch (Exception e) {
                        throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                                BundleManager.wrapKey("error.server"));
                    }
                case "PHONE_NUMBER":
                    try {
                        return this.usersRepository.existsUsersByPhoneNumber(identity);
                    } catch (Exception e) {
                        throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                                BundleManager.wrapKey("error.server"));
                    }
            }
        }
        throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                BundleManager.wrapKey("error.parameter.is.null"));
    }

    public List<UsersDto> findUsers(List<Long> usersIds) throws HamisheBaharException{
        try {
            List<UsersDto> users = new ArrayList<>();
            for (Long id : usersIds){
                users.add(findOneById(id));
            }
            return users;
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    private UsersDto findOneById(Long id) throws HamisheBaharException {
        try {
            UsersDto usersDto = null;
            if (id != null) {
                usersDto = usersRepository.getOne(id).convertToDto();
            }
            return usersDto;
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }
}
