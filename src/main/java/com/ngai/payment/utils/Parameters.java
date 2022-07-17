package com.ngai.payment.utils;

public class Parameters {
    public static final String SERVER_ERROR = "Server Error";
    public static final String INVALID_REQUEST = "Invalid request";
    public static final String PAYMENT_N0T_SUPPORTED = "Payment method not enabled at the moment try again later";
    public static final String INVALID_CREDENTIALS = "Invalid Credentials";
    public static final String SUCCESS = "SUCCESS";
    public static final String KEY_CALLBACK_BASE_URL = "PAYMENT_CALLBACK_URL";

    public enum USER_STATUS {
        active, disabled, suspended;

    }

    //db params JWT
    public static final String PARAM_JWT_TOKEN_EXPIRATION_TIME = "JWT_TOKEN_EXPIRATION_TIME";
    public static final String PARAM_JWT_ENCRYPTION_KEY = "JWT_ENCRYPTION_KEY";

    //monetbil
    public static final String KEY_MONETBILL_BASE_URL = "MONETBILL_BASE_URL";
    public static final String KEY_SECRET_URL = "MONETBILL_SERVICE_KEY";
}
