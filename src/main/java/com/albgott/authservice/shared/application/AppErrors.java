package com.albgott.authservice.shared.application;

public enum AppErrors {
    BUSINESSNAME_USED("businessName.on_use"),
    BUSINESSNAME_WRONG_FORMAT("businessName.wrong_format"),
    BUSINESSNAME_REQUIRED("businessName.required"),
    BUSINESSNAME_NOT_FOUND("businessName.not_found"),

    ACCOUNTNAME_NOT_FOUND("accountName.not_found"),
    ACCOUNTNAME_REQUIRED("accountName.required"),
    ACCOUNTNAME_WRONG_FORMAT("accountName.wrong_format"),

    EMAIL_NOT_FOUND("email.not_found"),
    EMAIL_WRONG_FORMAT("email.wrong_format"),
    EMAIL_REQUIRED("email.required"),
    EMAIL_ON_USE("email.on_use"),

    PASSWORD_REQUIRED("password.required"),
    PASSWORD_WRONG_FORMAT("password.wrong_format"),

    USER_NOT_FOUND("user.not_found"),
    USER_NOT_VERIFIED("user.not_verified"),

    BUSINESSID_REQUIRED("businessId.required"),
    ACCOUNTID_REQUIRED("accountId.required");




    private final String errorCode;

    AppErrors(String errorCode) {
        this.errorCode = errorCode;
    }

    public String errorCode() {
        return errorCode;
    }

}
