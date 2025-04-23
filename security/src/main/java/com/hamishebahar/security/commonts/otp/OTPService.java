package com.hamishebahar.security.commonts.otp;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Service
public class OTPService {

    private final Map<String, String> otpStorage = new HashMap<>();
    private final SecureRandom random = new SecureRandom();

    public String generateOTP(String username) {
        String otp = String.valueOf(100000 + random.nextInt(900000));
        otpStorage.put(username, otp);
        return otp;
    }

    public boolean validateOTP(String username, String inputOtp) {
        String storedOtp = otpStorage.get(username);
        return storedOtp != null && storedOtp.equals(inputOtp);
    }

    public void clearOTP(String username) {
        otpStorage.remove(username);
    }
}