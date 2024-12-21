package kdohyeon.boilerplate.controller;

import kdohyeon.boilerplate.exception.ErrorCode;

public record NetplixApiResponse<T>(
        boolean success,
        String code,
        String message,
        T data
) {
    public static final String CODE_SUCCEED = "SUCCEED";

    public static <T> NetplixApiResponse<T> ok(T data) {
        return new NetplixApiResponse<>(true, CODE_SUCCEED, null, data);
    }

    public static <T> NetplixApiResponse<T> fail(ErrorCode errorCode, String message) {
        return new NetplixApiResponse<>(false, errorCode.getCode(), message, null);
    }
}
