package com.beaconfire.week9day4housing.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.beaconfire.week9day4housing.Filter.JwtFilter;




@Configuration
public class FilterConfig {

	@Autowired
	private JwtFilter jwtFilter;

	@Bean
	public FilterRegistrationBean<JwtFilter> UserFilter() {
		final FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(jwtFilter);
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}

//	@Bean
//	public FilterRegistrationBean<AdminFilter> AdminFilter() {
//		final FilterRegistrationBean<AdminFilter> registrationBean = new FilterRegistrationBean<>();
//		registrationBean.setFilter(new AdminFilter());
//		registrationBean.addUrlPatterns("/admin/*");
//		return registrationBean;
//	}
}
