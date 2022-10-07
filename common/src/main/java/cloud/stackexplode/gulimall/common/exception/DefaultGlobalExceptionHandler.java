package cloud.stackexplode.gulimall.common.exception;

import cloud.stackexplode.gulimall.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * The type Default global exception handler.
 */
@Slf4j
@RestControllerAdvice
public class DefaultGlobalExceptionHandler {
  /**
   * Handle all r.
   *
   * @param e the e
   * @return the r
   */
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public R handleAll(Exception e) {
    e.printStackTrace();
    return R.error(e.getMessage());
  }
}
