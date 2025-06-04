package com.hamishebahar.security.externalservices.impl;

import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import com.hamishebahar.security.config.ConfigProperties;
import com.kavenegar.sdk.KavenegarApi;
import com.kavenegar.sdk.excepctions.ApiException;
import com.kavenegar.sdk.excepctions.HttpException;
import com.kavenegar.sdk.models.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ================================================================
 * Project Name: hamishebahar
 * Class: Kavenegar
 * Created by: Asus on 3/19/2025
 * Description: This class does ...
 * ================================================================
 */
@Slf4j
@Service
public class KavenegarImpl implements SmsService {
    private final ConfigProperties properties;
    private static KavenegarApi api;
    private String api_key;
    private String senderCellNumber;

    @Autowired
    public KavenegarImpl(ConfigProperties properties) {
        this.properties = properties;
        api_key = properties.getArgumentValue("sms.api.kavenegar.api.key");
        api = new KavenegarApi(api_key);
        senderCellNumber = properties.getArgumentValue("sms.api.kavenegar.sender");
    }

    @Override
    public void Send(String receptor, Object... args) throws HamisheBaharException {
        try {
            String message = properties.getArgumentValueWithBundle(
                    "smstemplate",
                    "sms.template.otp",
                    args);
            log.debug("Call send sms " + "sender : " + senderCellNumber +
                    " receptor : " + receptor +
                    " message : " + message);

            SendResult Result = api.send(senderCellNumber, receptor, message);
            log.debug("Result Call send sms " +
                    " messageId : " + Result.getMessageId() +
                    " message  : " + Result.getMessage() +
                    " status  : " + Result.getStatus() +
                    " statusText  : " + Result.getStatusText() +
                    " sender  : " + Result.getSender() +
                    " receptor  : " + Result.getReceptor() +
                    " date  : " + Result.getDate() +
                    " cost  : " + Result.getCost());
        } catch (HttpException ex) {
            log.error("HttpException  : " + ex.getMessage());
            throw new HamisheBaharException(ex.getCode() , ex.getMessage() , ex.getCause());
        } catch (ApiException e) {
            log.error("ApiException : " + e.getMessage());
            throw new HamisheBaharException(e.getCode().getValue() , e.getMessage() , e.getCause());
        }
    }

    @Override
    public void SendArray(List<String> receptors, List<String> senders, List<String> messages) {
        try {
            log.debug("Call Send Sms List" + "sender : " + senderCellNumber +
                    " receptor : " + (!receptors.isEmpty() ? receptors.get(0) : null) +
                    " message : " + (!messages.isEmpty() ? messages.get(0) : null));
            List<SendResult> Results = api.sendArray(senders, receptors, messages);
            log.debug("Result Call Sms List " +
                    " messageId : " + (!Results.isEmpty() ? Results.get(0).getMessageId() : null) +
                    " status  : " + (!Results.isEmpty() ? Results.get(0).getStatus() : null) +
                    " statusText  : " + (!Results.isEmpty() ? Results.get(0).getStatusText() : null));
        } catch (HttpException ex) {
            log.error("HttpException  : " + ex.getMessage());
        } catch (ApiException ex) {
            log.error("ApiException : " + ex.getMessage());
        }
    }

    @Override
    public void Status(Long messageId) {
        try {
            log.debug("Call Status " + "messageId : " + messageId);
            StatusResult Result = api.status(messageId);
            log.debug("Result Call Status " +
                    " messageId : " + Result.getMessageId() +
                    " status  : " + Result.getStatus() +
                    " statusText  : " + Result.getStatusText());
        } catch (HttpException ex) {
            log.error("HttpException  : " + ex.getMessage());
        } catch (ApiException ex) {
            log.error("ApiException : " + ex.getMessage());
        }
    }

    @Override
    public void StatusLocalMessageId(Long localId) {
        try {
            log.debug("Call Status Local MessageId" + "localId : " + localId);
            StatusLocalMessageIdResult Result = api.statusLocalMessageId(localId);
            log.debug("Result Call Status Local MessageId " +
                    " messageId : " + Result.getMessageId() +
                    " localid  : " + Result.getLocalId() +
                    " status  : " + Result.getStatus() +
                    " statustext  : " + Result.getStatusText());
        } catch (HttpException ex) {
            log.error("HttpException  : " + ex.getMessage());
        } catch (ApiException ex) {
            log.error("ApiException : " + ex.getMessage());
        }
    }

    @Override
    public void Select(Long messageId) {
        try {
            log.debug("Call Select by MessageId" + "messageId : " + messageId);
            SendResult Result = api.select(messageId);
            log.debug("Result Call Select by MessageId " +
                    " messageId : " + Result.getMessageId() +
                    " message  : " + Result.getMessage() +
                    " status  : " + Result.getStatus() +
                    " statusText  : " + Result.getStatusText() +
                    " sender  : " + Result.getSender() +
                    " receptor  : " + Result.getReceptor() +
                    " date  : " + Result.getDate() +
                    " cost  : " + Result.getCost());
        } catch (HttpException ex) {
            log.error("HttpException  : " + ex.getMessage());
        } catch (ApiException ex) {
            log.error("ApiException : " + ex.getMessage());
        }
    }

    @Override
    public void SelectOutbox(Long startDate, Long endDate) {
        try {
            log.debug("Call SelectOutbox " + "sender : " + senderCellNumber +
                    " startDate : " + startDate +
                    " endDate : " + endDate);
            if (startDate == null || endDate == null) {
                return;
            }
            List<SendResult> Results = api.selectOutbox(startDate, endDate, senderCellNumber);
            log.debug("Result Call SelectOutbox " +
                    " messageId : " + (!Results.isEmpty() ? Results.get(0).getMessageId() : null) +
                    " status  : " + (!Results.isEmpty() ? Results.get(0).getStatus() : null) +
                    " statusText  : " + (!Results.isEmpty() ? Results.get(0).getStatusText() : null) +
                    " sender  : " + (!Results.isEmpty() ? Results.get(0).getSender() : null) +
                    " receptor  : " + (!Results.isEmpty() ? Results.get(0).getReceptor() : null) +
                    " date  : " + (!Results.isEmpty() ? Results.get(0).getDate() : null) +
                    " cost  : " + (!Results.isEmpty() ? Results.get(0).getCost() : null));
        } catch (HttpException ex) {
            log.error("HttpException  : " + ex.getMessage());
        } catch (ApiException ex) {
            log.error("ApiException : " + ex.getMessage());
        }
    }

    @Override
    public void LatestOutbox(Long pageSize) {
        try {
            pageSize = pageSize != null ? pageSize : 100L;
            log.debug("Call LatestOutbox " + "sender : " + senderCellNumber +
                    " pagesize : " + pageSize);
            List<SendResult> Results = api.latestOutbox(pageSize, senderCellNumber);
            log.debug("Result Call LatestOutbox " +
                    " messageId : " + (!Results.isEmpty() ? Results.get(0).getMessageId() : null) +
                    " status  : " + (!Results.isEmpty() ? Results.get(0).getStatus() : null) +
                    " statusText  : " + (!Results.isEmpty() ? Results.get(0).getStatusText() : null) +
                    " sender  : " + (!Results.isEmpty() ? Results.get(0).getSender() : null) +
                    " receptor  : " + (!Results.isEmpty() ? Results.get(0).getReceptor() : null) +
                    " date  : " + (!Results.isEmpty() ? Results.get(0).getDate() : null) +
                    " cost  : " + (!Results.isEmpty() ? Results.get(0).getCost() : null));
        } catch (HttpException ex) {
            log.error("HttpException  : " + ex.getMessage());
        } catch (ApiException ex) {
            log.error("ApiException : " + ex.getMessage());
        }
    }

    @Override
    public void CountOutbox(Long startDate, Long endDate, Integer status) {
        try {
            log.debug("Call CountOutbox " + "sender : " + senderCellNumber +
                    " startDate : " + startDate +
                    " endDate : " + endDate +
                    " status : " + status);
            if (startDate == null || endDate == null) {
                return;
            }
            status = status != null ? status : 10;
            CountOutboxResult Result = api.countOutbox(startDate, endDate, status);
            log.debug("Result Call CountOutbox " +
                    " startDate : " + Result.getStartDate() +
                    " endDate  : " + Result.getEndDate() +
                    " sumCount  : " + Result.getSumCount() +
                    " sumPart  : " + Result.getSumPart() +
                    " sumCost  : " + Result.getCost());
        } catch (HttpException ex) {
            log.error("HttpException  : " + ex.getMessage());
        } catch (ApiException ex) {
            log.error("ApiException : " + ex.getMessage());
        }
    }

    @Override
    public void Cancel(Long messageId) {
        try {
            log.debug("Call Cancel : " + "messageId" + messageId);
            if (messageId == null) {
                return;
            }
            StatusResult Result = api.cancel(messageId);
            log.debug("Result Call Cancel " +
                    " messageId : " + Result.getMessageId() +
                    " status  : " + Result.getStatus() +
                    " statusText  : " + Result.getStatusText());
        } catch (HttpException ex) {
            log.error("HttpException  : " + ex.getMessage());
        } catch (ApiException ex) {
            log.error("ApiException : " + ex.getMessage());
        }
    }

    @Override
    public void Receive(String lineNumber, Integer isRead) {
        try {
            log.debug("Call Receive " + "lineNumber : " + lineNumber + " isRead : " + isRead);
            if (lineNumber == null || isRead == null) {
                return;
            }
            List<ReceiveResult> Results = api.receive(lineNumber, isRead);
            log.debug("Result Call Receive " +
                    " messageId : " + (!Results.isEmpty() ? Results.get(0).getMessageId() : null) +
                    " message  : " + (!Results.isEmpty() ? Results.get(0).getMessage() : null) +
                    " sender  : " + (!Results.isEmpty() ? Results.get(0).getSender() : null) +
                    " receptor  : " + (!Results.isEmpty() ? Results.get(0).getReceptor() : null) +
                    " date  : " + (!Results.isEmpty() ? Results.get(0).getDate() : null));
        } catch (HttpException ex) {
            log.error("HttpException  : " + ex.getMessage());
        } catch (ApiException ex) {
            log.error("ApiException : " + ex.getMessage());
        }
    }

    @Override
    public void CountInbox(Long startDate, Long endDate, String lineNumber, Integer isRead) {
        try {
            log.debug("Call CountInbox " + "lineNumber : " + lineNumber +
                    " isRead : " + isRead +
                    " startDate : " + startDate +
                    " endDate : " + endDate);
            if (startDate == null || endDate == null || lineNumber == null || isRead == null) {
                return;
            }
            CountInboxResult Result = api.countInbox(startDate, endDate, lineNumber, isRead);
            log.debug("Result Call CountInbox " +
                    " Result Call CountInbox " +
                    " startdate : " + Result.getStartDate() +
                    " enddate : " + Result.getEndDate() +
                    " sumcount : " + Result.getSumCount());
        } catch (HttpException ex) {
            log.error("HttpException  : " + ex.getMessage());
        } catch (ApiException ex) {
            log.error("ApiException : " + ex.getMessage());
        }
    }

    @Override
    public void CountPostalCode(Long postalCode) {
        try {
            log.debug("Call CountPostalCode " + "postalCode : " + postalCode);
            if (postalCode == null) {
                return;
            }
            List<CountPostalCodeResult> Results = api.countPostalCode(postalCode);
            log.debug("Result Call CountPostalCode " +
                    " section  : " + (!Results.isEmpty() ? Results.get(0).getSection() : null) +
                    " value  : " + (!Results.isEmpty() ? Results.get(0).getValue() : null));
        } catch (HttpException ex) {
            log.error("HttpException  : " + ex.getMessage());
        } catch (ApiException ex) {
            log.error("ApiException : " + ex.getMessage());
        }

    }

    @Override
    public void AccountInfo() {
        try {
            log.debug("Call Sms Provider AccountInfo ");
            AccountInfoResult Result = api.accountInfo();
            System.out.println("remainCredit : " + Result.getRemainCredit());
            System.out.println("expireDate : " + Result.getExpireDate());
            System.out.println("type : " + Result.getType());

            log.debug("Result Sms Provider AccountInfo " +
                    " remainCredit  : " + Result.getRemainCredit() +
                    " expireDate  : " + Result.getExpireDate() +
                    " type  : " + Result.getType());
        } catch (HttpException ex) {
            log.error("HttpException  : " + ex.getMessage());
        } catch (ApiException ex) {
            log.error("ApiException : " + ex.getMessage());
        }
    }
}
