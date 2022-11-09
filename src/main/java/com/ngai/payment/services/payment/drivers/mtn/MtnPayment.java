/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ngai.payment.services.payment.drivers.mtn;


import com.ngai.payment.model.dto.MobilePaymentRequest;
import com.ngai.payment.model.dto.PaymentResponse;
import com.ngai.payment.model.repository.TTraceRepository;
import com.ngai.payment.model.repository.TTraceStatusRepository;
import com.ngai.payment.services.payment.core.PaymentContext;
import com.ngai.payment.services.payment.core.impl.MobilePaymentImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ngai
 */
@Component
public class MtnPayment  extends MobilePaymentImpl {

    @Autowired
    public MtnPayment(TTraceRepository tTraceRepository, TTraceStatusRepository tTraceStatusRepository) {
        super(tTraceStatusRepository, tTraceRepository);
    }

    //<editor-fold defaulstate="collapsed" desc="[ OVERRIDE METHODS]">
    @Override
    public PaymentContext buildPaymentContext(MobilePaymentRequest request) {
        return null;
    }


    //</editor-fold>

    @Override
    public PaymentResponse buildPaymentResponse(PaymentContext paymentContext) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void proceedExternal(PaymentContext paymentContext) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}