package com.hamishebahar.security.externalservices.impl;

import com.hamishebahar.security.commonts.exeption.HamisheBaharException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SmsService {
    void Send(String receptor, Object... args) throws HamisheBaharException;

    void SendArray(List<String> receptors, List<String> senders, List<String> messages);

    void Status(Long messageId);

    void StatusLocalMessageId(Long localId);

    void Select(Long massageId);

    void SelectOutbox(Long startDate, Long endDate);
    void LatestOutbox(Long pageSize);

    void CountOutbox(Long startDate, Long endDate, Integer status);

    void Cancel(Long messageId);

    void Receive(String lineNumber , Integer isRead);

    void CountInbox(Long startDate, Long endDate, String lineNumber, Integer isRead);

    void CountPostalCode(Long postalCode);

    void AccountInfo();
}
