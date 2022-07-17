package com.ngai.payment.services.payment.core.contract;

public interface IWithdrawal {
    /**
     * This method sends money the customer's external
     */
    public void makeDeposit();

    /**
     * checks the status of an previously made transaction
     */
    public void checkStatus(String origTransacationRef);
}
