package com.hamishebahar.security.commonts.utils;

import com.hamishebahar.security.commonts.Dto.PeriodsDto;
import com.hamishebahar.security.commonts.Dto.UsersDto;
import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import com.hamishebahar.security.panel.periods.entity.Periods;
import com.hamishebahar.security.panel.periods.service.PeriodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PeriodsUtils {
    private final PeriodsService periodsService;

    @Autowired
    public PeriodsUtils(PeriodsService periodsService) {
        this.periodsService = periodsService;
    }

    public List<PeriodsDto> findPeriods(List<PeriodsDto> periods) throws HamisheBaharException {
        return periodsService.findPeriods(
                periods.stream().map(PeriodsDto::getId).collect(Collectors.toList())
        );
    }
}
