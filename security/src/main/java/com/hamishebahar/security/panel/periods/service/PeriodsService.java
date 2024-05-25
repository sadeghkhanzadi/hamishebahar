package com.hamishebahar.security.panel.periods.service;

import com.hamishebahar.security.commonts.Dto.PeriodsDto;
import com.hamishebahar.security.commonts.Dto.UsersDto;
import com.hamishebahar.security.commonts.bundel.BundleManager;
import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import com.hamishebahar.security.panel.periods.repository.PeriodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeriodsService {
    private final PeriodsRepository periodsRepository;

    @Autowired
    public PeriodsService(PeriodsRepository periodsRepository) {
        this.periodsRepository = periodsRepository;
    }

    public List<PeriodsDto> findPeriods(List<Long> periodIds) throws HamisheBaharException {
        try {
            List<PeriodsDto> periods = new ArrayList<>();
            for (Long id : periodIds){
                periods.add(findOneById(id));
            }
            return periods;
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }

    public PeriodsDto findOneById(Long id) throws HamisheBaharException {
        try {
            PeriodsDto periods = null;
            if (id != null) {
                periods = periodsRepository.getOne(id).convertToDto();
            }
            return periods;
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    BundleManager.wrapKey("error.server"));
        }
    }
}
