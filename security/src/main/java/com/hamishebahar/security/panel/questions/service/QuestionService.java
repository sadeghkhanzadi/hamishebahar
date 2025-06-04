package com.hamishebahar.security.panel.questions.service;

import com.hamishebahar.security.commonts.Dto.QuestionDto;
import com.hamishebahar.security.commonts.Dto.ResultsServiceDto;
import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import com.hamishebahar.security.config.ConfigProperties;
import com.hamishebahar.security.panel.questions.entity.Questions;
import com.hamishebahar.security.panel.questions.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final ConfigProperties messageBundle;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, ConfigProperties messageBundle) {
        this.questionRepository = questionRepository;
        this.messageBundle = messageBundle;
    }

    public ResultsServiceDto insertQuestion(QuestionDto dto) throws HamisheBaharException {
        if (dto.getId() != null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    messageBundle.getArgumentValue("error.parameter.not.valid", "**id**"));
        }
        if (dto.getText() == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    messageBundle.getArgumentValue("error.parameter.is.null",null));
        }
        try {
            QuestionDto questionDto = questionRepository.save(dto.convertToEntity()).convertToDto();
            return new ResultsServiceDto.Builder().Status(HttpStatus.OK).Result(questionDto).build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    messageBundle.getArgumentValue("error.server", null));
        }
    }

    public ResultsServiceDto editQuestion(QuestionDto dto, Long id) throws HamisheBaharException {
        if (dto.getId() == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    messageBundle.getArgumentValue("error.parameter.not.valid", "**id**"));
        }
        if (id == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    messageBundle.getArgumentValue("error.parameter.is.null",null));
        }
        if (!dto.getId().equals(id)) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    messageBundle.getArgumentValue("error.parameter.not.valid", "**id**"));
        }
        if (!isExists(id)) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    messageBundle.getArgumentValue("error.entity.is.not.exists", String.valueOf(id)));
        }
        QuestionDto vo = getOne(id);
        QuestionDto questionDto = null;
        if (vo != null) {
            if (dto.getId() == null || dto.getText() == null) {
                throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                        messageBundle.getArgumentValue("error.parameter.is.null",null));
            }
            try {
                dto = dto.updaterFields(vo);
                questionDto = questionRepository.save(dto.convertToEntity()).convertToDto();
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        messageBundle.getArgumentValue("error.server", null));
            }
        }
        return new ResultsServiceDto.Builder().Status(HttpStatus.OK).Result(questionDto).build();
    }

    public ResultsServiceDto deleteQuestion(Long id) throws HamisheBaharException {
        ResultsServiceDto resultsServiceDto = new ResultsServiceDto.Builder()
                .Result(null)
                .Status(HttpStatus.BAD_REQUEST)
                .build();
        if (id == null) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    messageBundle.getArgumentValue("error.parameter.is.null",null));
        }
        if (!isExists(id)) {
            throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                    messageBundle.getArgumentValue("error.entity.is.not.exists", String.valueOf(id)));
        }
        QuestionDto dto = getOne(id);
        if (dto != null) {
            try {
                questionRepository.deleteById(dto.getId());
                resultsServiceDto = new ResultsServiceDto.Builder()
                        .Result("id = " + id + " physical deleted.")
                        .Status(HttpStatus.OK)
                        .build();
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        messageBundle.getArgumentValue("error.server", null));
            }
        }
        return resultsServiceDto;
    }

    public ResultsServiceDto findOne(Long id) throws HamisheBaharException {
        try {
            QuestionDto questionDto = questionRepository.getById(id).convertToDto();
            return new ResultsServiceDto.Builder().Result(questionDto).Status(HttpStatus.OK).build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    messageBundle.getArgumentValue("error.server", null));
        }
    }

    public ResultsServiceDto findByFilter(Long id) throws HamisheBaharException {
        try {
            ResultsServiceDto resultsServiceDto;
            if (id != null) {
                resultsServiceDto = findOne(id);
            } else {
                resultsServiceDto = findAll();
            }
            return resultsServiceDto;
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    messageBundle.getArgumentValue("error.server", null));
        }
    }

    public QuestionDto getOne(Long id) throws HamisheBaharException {
        try {
            return questionRepository.getById(id).convertToDto();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    messageBundle.getArgumentValue("error.server", null));
        }
    }

    public ResultsServiceDto findAll() throws HamisheBaharException {
        try {
            return new ResultsServiceDto.Builder().Result(
                            questionRepository.findAll()
                                    .stream()
                                    .map(Questions::convertToDto)
                    )
                    .Status(HttpStatus.OK)
                    .build();
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    messageBundle.getArgumentValue("error.server", null));
        }
    }

    public Boolean isExists(Long id) throws HamisheBaharException {
        if (id != null) {
            try {
                return this.questionRepository.existsById(id);
            } catch (Exception e) {
                throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                        messageBundle.getArgumentValue("error.server", null));
            }
        }
        throw new HamisheBaharException(HamisheBaharException.INVALID_REQUEST_PARAMETER,
                messageBundle.getArgumentValue("error.parameter.is.null",null));
    }

    public Boolean isExists() throws HamisheBaharException {
        try {
            return (this.questionRepository.count() > 0);
        } catch (Exception e) {
            throw new HamisheBaharException(HamisheBaharException.DATABASE_EXCEPTION,
                    messageBundle.getArgumentValue("error.server", null));
        }
    }
}
