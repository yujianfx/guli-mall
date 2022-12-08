package cloud.stackexplode.gulimall.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * The type R.
 */

public class R<T> {
    private static final long serialVersionUID = 1L;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StatusCode code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private Long timeStamps;

    public static <T> R<T> ok() {
        return new <T>R<T>().setCode(StatusCode.SUCCESS).setMsg("success").setTimeStamps(System.currentTimeMillis());
    }

    public static <T> R<T> ok(T data) {
        return new R<T>().setCode(StatusCode.SUCCESS).setMsg("success").setData(data).setTimeStamps(System.currentTimeMillis());
    }

    public static <T> R<T> ok(String msg, T data) {
        return new R<T>().setCode(StatusCode.SUCCESS).setMsg(msg).setData(data).setTimeStamps(System.currentTimeMillis());
    }

    public static <T> R<T> ok(StatusCode code, String msg, T data) {
        return new R<T>().setCode(code).setMsg(msg).setData(data).setTimeStamps(System.currentTimeMillis());
    }

    public static <T> R<T> error() {
        return new R<T>().setCode(StatusCode.ERROR).setMsg("error").setTimeStamps(System.currentTimeMillis());
    }

    public static <T> R<T> error(String msg) {
        return new R<T>().setCode(StatusCode.ERROR).setMsg(msg).setTimeStamps(System.currentTimeMillis());
    }

    public static <T> R<T> error(StatusCode code, String msg) {
        return new R<T>().setCode(code).setMsg(msg).setTimeStamps(System.currentTimeMillis());
    }

    public static <T> R<T> error(StatusCode code, String msg, T data) {
        return new R<T>().setCode(code).setMsg(msg).setData(data).setTimeStamps(System.currentTimeMillis());
    }

    public String getMsg() {
        return msg;
    }

    public R<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public StatusCode getCode() {
        return code;
    }

    public R<T> setCode(StatusCode code) {
        this.code = code;
        return this;
    }

    public T getData() {
        return data;
    }

    public R<T> setData(T data) {
        this.data = data;
        return this;
    }

    public Long getTimeStamps() {
        return timeStamps;
    }

    public R<T> setTimeStamps(Long timeStamps) {
        this.timeStamps = timeStamps;
        return this;
    }

    public R() {
        this.msg = "success";
        this.code = StatusCode.SUCCESS;
        this.timeStamps = System.currentTimeMillis();

    }

    public R(String msg, StatusCode code, T data, Long timeStamps) {
        this.msg = msg;
        this.code = code;
        this.data = data;
        this.timeStamps = timeStamps;
    }
}
