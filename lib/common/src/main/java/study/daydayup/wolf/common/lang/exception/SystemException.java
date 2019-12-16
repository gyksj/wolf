package study.daydayup.wolf.common.lang.exception;

import lombok.Getter;

@Getter
public class SystemException extends BaseException {
    public SystemException() {
        super(500, "system exception");
    }

    public SystemException(String message) {
        super(500, message);
    }

    public SystemException(long code, String message) {
        super(code, message);
    }
}
