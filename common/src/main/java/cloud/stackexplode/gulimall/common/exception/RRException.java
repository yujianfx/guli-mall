/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * <p>https://www.renren.io
 *
 * <p>版权所有，侵权必究！
 */
package cloud.stackexplode.gulimall.common.exception;

/**
 * 自定义异常
 *
 * @author Mark sunlightcs@gmail.com
 */
public class RRException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;

    /**
     * Instantiates a new Rr exception.
     *
     * @param msg the msg
     */
public RRException(String msg) {
        super(msg);
        this.msg = msg;
    }

    /**
     * Instantiates a new Rr exception.
     *
     * @param msg the msg
     * @param e the e
     */
public RRException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    /**
     * Instantiates a new Rr exception.
     *
     * @param msg the msg
     * @param code the code
     */
public RRException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    /**
     * Instantiates a new Rr exception.
     *
     * @param msg the msg
     * @param code the code
     * @param e the e
     */
public RRException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    /**
     * Gets msg.
     *
     * @return the msg
     */
public String getMsg() {
        return msg;
    }

    /**
     * Sets msg.
     *
     * @param msg the msg
     */
public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
public int getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
public void setCode(int code) {
        this.code = code;
    }
}
