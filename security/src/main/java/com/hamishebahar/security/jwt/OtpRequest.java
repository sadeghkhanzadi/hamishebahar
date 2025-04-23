package com.hamishebahar.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ================================================================
 * Project Name: hamishebahar
 * Class: OtpRequest
 * Created by: Asus on 3/19/2025
 * Description: This class does ...
 * ================================================================
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpRequest {
    private String username;
    private String otpCode;
}
