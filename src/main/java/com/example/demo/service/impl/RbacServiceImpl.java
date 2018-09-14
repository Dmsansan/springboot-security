package com.example.demo.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.example.demo.service.RbacService;

@Component("rbacService")
public class RbacServiceImpl implements RbacService{
	private AntPathMatcher AntPathMatcher = new AntPathMatcher();
	
	@Override
	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
		// TODO Auto-generated method stub
		Object principal = authentication.getPrincipal();
		boolean hasPermission = false;
		if(principal instanceof UserDetails) {
			String userName = ((UserDetails) principal).getUsername();//登录的用户名
			Set<String> urls = new HashSet<>();//用户具备的系统资源集合，从数据库读取
			
			urls.add("/whoim");
			for(String url : urls) {
				if(AntPathMatcher.match(url, request.getRequestURI())) {
					hasPermission = true;
					break;
				}
			}
		}
		
		return hasPermission;
	}
	
}
