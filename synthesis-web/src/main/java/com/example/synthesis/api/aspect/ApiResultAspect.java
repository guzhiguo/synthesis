package com.example.synthesis.api.aspect;

import com.example.synthesis.controller.response.ApiResult;
import com.example.synthesis.error.SystemErrorEnum;
import com.example.synthesis.error.TribeError;
import com.example.synthesis.error.TribeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author: bill
 * @create: 2020/03/13 18:28
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class ApiResultAspect {

    @Around("@annotation(com.example.synthesis.api.annotation.ApiResultAn)")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        Object[] args = proceedingJoinPoint.getArgs();
        String clazzName = proceedingJoinPoint.getTarget().getClass().getSimpleName();
        String methodName = proceedingJoinPoint.getSignature().getName();

        ApiResult result = null;
        log.info("ApiResultAspect-{0}#{1},args{2}", clazzName, methodName, StringUtils.join(args));

        try {
            result = (ApiResult) proceedingJoinPoint.proceed();
            log.info("doAroundAdvice ApiResultAspect,result:{0}", result);
        } catch (TribeException e) {
            log.error(e.getError().getMessage());
            result = new ApiResult();
            TribeError tribeError = new TribeError();
            tribeError.setCode(e.getError().getCode());
            tribeError.setMessage(e.getError().getMessage());
            tribeError.setView(e.getError().getView());
            result.setError(tribeError);
        } catch (Throwable throwable) {
            log.error(throwable.getMessage());
            result = new ApiResult();
            TribeError tribeError = new TribeError();
            tribeError.setCode(SystemErrorEnum.SYSTEM_ERROR.getError().getCode());
            tribeError.setMessage(SystemErrorEnum.SYSTEM_ERROR.getError().getMessage());
            tribeError.setView(SystemErrorEnum.SYSTEM_ERROR.getError().getView());
            result.setError(tribeError);
        }
        return result;
    }


}
