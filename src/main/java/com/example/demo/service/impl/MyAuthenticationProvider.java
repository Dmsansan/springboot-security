package com.example.demo.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.example.demo.entity.UserInfo;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider{
	
	/**
        * 注入我们自己定义的用户信息获取对象
     */
    @Autowired
    private UserDetailsService userDetailService;
    
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String username = authentication.getName();
		String password = String.valueOf(authentication.getPrincipal());
		UserInfo userInfo = (UserInfo) userDetailService.loadUserByUsername(username);
		if(userInfo == null) {
			throw new BadCredentialsException("用户不存在！");
		}
		if(!userInfo.getPassword().equals("123456")) {
			throw new BadCredentialsException("密码不正确！");
		}
		Collection<? extends GrantedAuthority> authorities = userInfo.getAuthorities();
		return new UsernamePasswordAuthenticationToken(userInfo, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

}
