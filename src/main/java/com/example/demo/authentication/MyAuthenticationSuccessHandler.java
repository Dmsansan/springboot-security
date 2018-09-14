package com.example.demo.authentication;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 登陆成功处理逻辑类
 * @author sisansan
 *
 */
@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	
	@Autowired
    private ObjectMapper objectMapper;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
                throws IOException, ServletException {            
          //什么都不做的话，那就直接调用父类的方法
//          super.onAuthenticationSuccess(request, response, authentication);  
          
          //返回json数据
//          Map<String,String> map=new HashMap<>();
//          map.put("code", "200");
//          map.put("msg", "登录成功");
//          response.setContentType("application/json;charset=UTF-8");
//          response.getWriter().write(objectMapper.writeValueAsString(map));
          
          
    	  //页面跳转
          new DefaultRedirectStrategy().sendRedirect(request, response, "/whoim");
          
    }
	
}
