package com.supercell.elmm.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.supercell.elmm.global.Global;
import com.supercell.elmm.service.MerchantService;
import com.supercell.elmm.util.JSONUtil;
import com.supercell.elmm.util.WebServiceUtil;
import com.supercell.elmm.vo.MerchantState;


/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter extends OncePerRequestFilter  {
	private String notLoginPage="/view/notLogin.jsp";
	private String merchantStateDestination="http://10.222.232.30:8080/SuperCell-ELM-A/merchantmanage/getSimpleMerchantState.do/";
	

	private MerchantService service;
	private Client client;
	
	private Map<String, String> initParamMap;
	private Set<String> ignoreURIs;
	private Set<String> ignoreExts;
	
	public void initFilter(FilterConfig fConfig){
		initParamMap = new HashMap<String, String>();
		ignoreURIs = new HashSet<String>();
		for(Enumeration e=fConfig.getInitParameterNames();e.hasMoreElements();){
			String name = (String) e.nextElement();
			initParamMap.put(name, fConfig.getInitParameter(name));
//			initSet.add(fConfig.getInitParameter(name));
			//System.out.println("参数:"+name+":"+ fConfig.getInitParameter(name));
		}
		String ignores = fConfig.getInitParameter("ignore");
		for(String ig : ignores.split(",")){
            ignoreURIs.add(ig.trim());
		}
	}
	
	public boolean isNoNeedRedirect(String path) throws IOException{
		for (String key:initParamMap.keySet()) {
			if (path.contains(initParamMap.get(key))) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		initFilter(this.getFilterConfig());
		 HttpServletRequest req = request;
		    HttpServletResponse resp = response;
		    String path=request.getServletPath();
		    HttpSession session = req.getSession();
	        String userName = (String) session.getAttribute(Global.CURRENT_MERCHANT);
	        System.out.println("Path:"+path);
	        
	        for(String ignoreURI : ignoreURIs){
	            if(path.contains(ignoreURI)){
	            	filterChain.doFilter(request, response);
	                return ;
	            }
	        }
	        
	        if(userName == null){
					response.sendRedirect("/SuperCell-ELM-M/view/login.jsp");
//					filterChain.doFilter(request, response);
					return;
	        }else {
	        	WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getFilterConfig().getServletContext());
	        	service = wac.getBean(MerchantService.class);
	        	client = wac.getBean(Client.class);
	        	String phoneNumber =(String) request.getSession().getAttribute(Global.CURRENT_MERCHANT);
				int merchantId = service.findMerchantIDByPhoneNumber(phoneNumber);
				String destination = merchantStateDestination+merchantId;
				String value = (String) WebServiceUtil.getValue(client, destination, String.class);
				MerchantState state = (MerchantState) JSONUtil.jsonToObject(value, MerchantState.class);
				if (state.getMerchantState()==MerchantState.BLACK_MERCHANT_STATE) {
					String loginFailTips = Global.stateMap.get(state.getMerchantState()).getDescription();
					request.setAttribute("loginFailTips", loginFailTips);
					System.out.println(notLoginPage);
					request.getRequestDispatcher(notLoginPage).forward(request, response);
//					filterChain.doFilter(request, response);
					return;
				}
				filterChain.doFilter(request, response);
	        	return;
			}
	}
}
