package kdohyeon.boilerplate.exception;

import lombok.Getter;

import static kdohyeon.boilerplate.exception.ErrorCode.USER_ALREADY_EXIST;
import static kdohyeon.boilerplate.exception.ErrorCode.USER_DOES_NOT_EXIST;

@Getter
public class UserException extends RuntimeException {
    private final ErrorCode errorCode;

    public UserException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public static class UserDoesNotExistException extends UserException {
        public UserDoesNotExistException() {
            super(USER_DOES_NOT_EXIST);
        }
    }

    public static class UserAlreadyExistException extends UserException {
        public UserAlreadyExistException() {
            super(USER_ALREADY_EXIST);
        }
    }
}
