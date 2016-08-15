package com.supercell.elmm.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.supercell.elmm.global.Global;
import com.supercell.elmm.service.MerchantService;
import com.supercell.elmm.util.JSONUtil;
import com.supercell.elmm.util.WebServiceUtil;
import com.supercell.elmm.vo.MerchantState;

public class LoginInterceptor implements HandlerInterceptor{
	@Value("${ROOT_PATH}")
	private String rootPath;
	@Value("${FOWARD_WAY}")
	private String forwardWay;
	@Value("${NOT_LOGIN_PAGE}")
	private String notLoginPage;
	@Value("${LOGIN_PAGE_WHOLE_NAME}")
	private String loginPageWholeName;
	@Value("${MerchantStateWebservice}")
	private String merchantStateDestination;
	private MerchantService service;
	@Resource(name="merchantService")
	public void setService(MerchantService service) {
		this.service = service;
	}
	
	private Client client;
	@Resource(name="jerseyClient")
	public void setClient(Client client) {
		this.client = client;
	}
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		
		if (request.getSession().getAttribute(Global.CURRENT_MERCHANT)!=null) {
			String phoneNumber =(String) request.getSession().getAttribute(Global.CURRENT_MERCHANT);
			int merchantId = service.findMerchantIDByPhoneNumber(phoneNumber);
			String destination = merchantStateDestination+merchantId;
			String value = (String) WebServiceUtil.getValue(client, destination, String.class);
			MerchantState state = (MerchantState) JSONUtil.jsonToObject(value, MerchantState.class);
			if (state.getMerchantState()==MerchantState.BLACK_MERCHANT_STATE) {
				String loginFailTips = Global.stateMap.get(state.getMerchantState()).getDescription();
				request.setAttribute("loginFailTips", loginFailTips);
				System.out.println(forwardWay+rootPath+notLoginPage);
				request.getRequestDispatcher(notLoginPage).forward(request, response);
				return false;
			}
			return true;
		}else {
			response.sendRedirect(rootPath+loginPageWholeName);
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
