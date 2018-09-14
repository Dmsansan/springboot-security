package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.example.demo.authentication.MyAuthenticationFailHander;
import com.example.demo.authentication.MyAuthenticationSuccessHandler;
import com.example.demo.service.RbacService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
//	@Autowired
//	private AuthenticationProvider provider;
	
	@Autowired
	private  MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
	
	@Autowired
	private MyAuthenticationFailHander myAuthenticationFailHander;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
//           TODO Auto-generated method stub
//          super.configure(http);
          http
                .formLogin().loginPage("/login").loginProcessingUrl("/login/form")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailHander)
                .permitAll()  //permitAll()表示这个不需要验证 登录页面
                .and()
                .authorizeRequests()
//                .antMatchers("/index").permitAll()//所有账号都可以访问index
//                //直接配置ADMIN角色权限访问whoim
//                .antMatchers("/whoim").hasRole("ADMIN")
                .anyRequest().access("@rbacService.hasPermission(request,authentication)")//所有请求都必须认证才能确定是否访问                  
                .and()
                .csrf().disable();            
    }
	
	//默认的登录界面
//	@Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // TODO Auto-generated method stub
//        auth.authenticationProvider(provider);
////        auth
////        .inMemoryAuthentication()
////            .withUser("admin").password("123456").roles("USER")
////            .and()
////            .withUser("test").password("test123").roles("ADMIN");
//    }
}
