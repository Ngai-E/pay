package com.ngai.payment.services;

import com.ngai.payment.model.dto.MobilePaymentRequest;
import com.ngai.payment.model.dto.PaymentResponse;
import com.ngai.payment.services.custom.ApiException;
import com.ngai.payment.services.custom.Messaging;
import com.ngai.payment.services.payment.core.contract.IPayment;
import com.ngai.payment.services.payment.core.impl.MobilePaymentImpl;
import com.ngai.payment.utils.ErrorCodes;
import com.ngai.payment.utils.Parameters;
import com.ngai.payment.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PaymentService extends Messaging {

    @Autowired
    PaymentDriversFactoryService paymentDriversFactoryService;

    public PaymentResponse handlePayment(MobilePaymentRequest request) {
        if (request == null || Utility.isNullOrEmpty(request.getPaymentCode()))
            throw new ApiException(Parameters.INVALID_REQUEST, HttpStatus.BAD_REQUEST);

        MobilePaymentImpl paymentDriver = paymentDriversFactoryService.getMobilePaymentProcessByPaymenCode(request.getPaymentCode());

        if (paymentDriver == null)
            throw new ApiException(Parameters.PAYMENT_N0T_SUPPORTED, HttpStatus.BAD_REQUEST);

        paymentDriver.makePayment(request);

        if (paymentDriver.getContext().hasError()) {
            throw new ApiException(paymentDriver.getContext().getError(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        super.setErrCode(Parameters.SUCCESS);
        super.setErrCode(ErrorCodes.SUCCESS_CODE);

        return paymentDriver.buildPaymentResponse();
    }

    public void handleStatusCheck(String origTransacationRef) {

    }

}
