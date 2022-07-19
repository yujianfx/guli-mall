/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * <p>https://www.renren.io
 *
 * <p>版权所有，侵权必究！
 */
package top.wangudiercai.gulimall.common.validator;

import org.apache.commons.lang.StringUtils;
import top.wangudiercai.gulimall.common.exception.RRException;

/**
 * 数据校验
 *
 * @author Mark sunlightcs@gmail.com
 */
public abstract class Assert {

  public static void isBlank(String str, String message) {
    if (StringUtils.isBlank(str)) {
      throw new RRException(message);
    }
  }

  public static void isNull(Object object, String message) {
    if (object == null) {
      throw new RRException(message);
    }
  }
}
