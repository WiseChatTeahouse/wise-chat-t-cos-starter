package chat.wise.file.cos.common.exception;

/**
 * @Author siberia.hu
 * @Package chat.wise.file.cos.common.exception
 * @Date 2024/6/18 21:37
 */
public class TCOSException extends RuntimeException {
    public TCOSException() {
    }

    public TCOSException(String message) {
        super(message);
    }

    public TCOSException(String message, Throwable cause) {
        super(message, cause);
    }

    public TCOSException(Throwable cause) {
        super(cause);
    }

    public TCOSException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
