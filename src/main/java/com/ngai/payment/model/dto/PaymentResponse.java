/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ngai.payment.model.dto;

import lombok.Data;

/**
 *
 * @author Ngai
 */
@Data
public class PaymentResponse {
    private String paymentRef;
    private String status;
}
