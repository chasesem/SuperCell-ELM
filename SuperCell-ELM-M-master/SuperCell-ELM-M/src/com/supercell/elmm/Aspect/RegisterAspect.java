package com.supercell.elmm.Aspect;

import org.apache.commons.codec.digest.DigestUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.supercell.elmm.entity.Merchant;

@Component
@Aspect
public class RegisterAspect {
	@Around("execution (* com.supercell.elmm.service.*.register(..))")
	public Object loginArroud(ProceedingJoinPoint p){
		Object[] args = p.getArgs();
		Merchant merchant = (Merchant) args[0];
//		System.out.println(args[0]);
//		System.out.println(args[1]);
		try {
			merchant.setPassword(DigestUtils.md5Hex(merchant.getPassword()));
			args[0] = merchant;
			Object object = p.proceed(args);
			return object;
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
