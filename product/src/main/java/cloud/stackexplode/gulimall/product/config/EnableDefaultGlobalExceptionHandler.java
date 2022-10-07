package cloud.stackexplode.gulimall.product.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ExceptionHandleConfig.class)
public @interface EnableDefaultGlobalExceptionHandler {}
