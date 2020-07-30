package com.ee.demo.iconfig;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
 
 
@EnableWebMvc
@Configuration
@ComponentScan("com.ee.demo")
public class WebConfig {
	
	@Bean public CommonsMultipartResolver multipartResolver() { 
		CommonsMultipartResolver multipart = new CommonsMultipartResolver();
		multipart.setMaxUploadSize(50 * 1024 * 1024); return multipart;}
	
	@Bean @Order(0) public MultipartFilter multipartFilter() { 
		MultipartFilter multipartFilter = new MultipartFilter(); 
		multipartFilter.setMultipartResolverBeanName("multipartResolver"); 
		return multipartFilter; 
		}
	
	@Bean
	public MultipartConfigElement multipartConfigElement() {

	     MultipartConfigFactory factory = new MultipartConfigFactory();

	     //factory.setMaxFileSize("9000MB");

	     //factory.setMaxRequestSize("9000MB");

	     return factory.createMultipartConfig();

	} 
	 
	 @Bean
	    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
	        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
	        validatorFactoryBean.setValidationMessageSource(messageSource);
	        return validatorFactoryBean;
	    }
  

}
//#00000001 Ends

