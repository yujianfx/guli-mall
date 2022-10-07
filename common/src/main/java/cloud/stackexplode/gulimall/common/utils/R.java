package cloud.stackexplode.gulimall.common.utils;

import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * The type R.
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new R.
     */
public R() {
        put("code", 0);
        put("msg", "success");
    }

    /**
     * Error r.
     *
     * @return the r
     */
public static R error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

    /**
     * Error r.
     *
     * @param msg the msg
     * @return the r
     */
public static R error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    /**
     * Error r.
     *
     * @param code the code
     * @param msg the msg
     * @return the r
     */
public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    /**
     * Ok r.
     *
     * @param msg the msg
     * @return the r
     */
public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    /**
     * Ok r.
     *
     * @param map the map
     * @return the r
     */
public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    /**
     * Ok r.
     *
     * @return the r
     */
public static R ok() {
        return new R();
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
