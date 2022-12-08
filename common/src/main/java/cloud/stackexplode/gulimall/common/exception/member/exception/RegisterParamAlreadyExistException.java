package cloud.stackexplode.gulimall.common.exception.member.exception;


public class RegisterParamAlreadyExistException extends RuntimeException {
    private static final String MSG_SUFFIX = "% 已存在";

    public RegisterParamAlreadyExistException(String paramName) {
        super(String.format(MSG_SUFFIX, paramName));
    }
}

