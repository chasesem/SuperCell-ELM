package com.supercell.elmm.Aspect;

import org.apache.commons.codec.digest.DigestUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoginAspect {
	@Around("execution (* com.supercell.elmm.service.*.login(..))")
	public Object loginArroud(ProceedingJoinPoint p){
		Object[] args = p.getArgs();
		try {
			args[1]=DigestUtils.md5Hex((String)args[1]);
			Object object = p.proceed(args);
			return object;
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}