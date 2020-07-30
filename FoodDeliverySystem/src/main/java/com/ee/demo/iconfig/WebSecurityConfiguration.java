package com.ee.demo.iconfig;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpHeaders.ORIGIN;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.ee.demo.iwebhandlers.RestAccessDeniedHandler;
import com.ee.demo.iwebhandlers.SecurityAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableTransactionManagement
@ComponentScan("com.ee.demo")
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
 
    public WebSecurityConfiguration() {
        /*
         * Ignores the default configuration, useless in our case (session management, etc..)
         */
        super(true);
    }
  
    
    @Bean
    public PlaintextPasswordEncoder passwordEncoder() {
        return new PlaintextPasswordEncoder();
    }
    


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        /* the secret key used to sign the JWT token is known exclusively by the server.
         With Nimbus JOSE implementation, it must be at least 256 characters longs.
         */
        String secret = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("secret.key"), Charset.defaultCharset());

        httpSecurity
                /*
                Filters are added just after the ExceptionTranslationFilter so that Exceptions are catch by the exceptionHandling()
                 Further information about the order of filters, see FilterComparator
                 */
                .addFilterAfter(corsFilter(), ExceptionTranslationFilter.class)
                /*
                 Exception management is handled by the authenticationEntryPoint (for exceptions related to authentications)
                 and by the AccessDeniedHandler (for exceptions related to access rights)
                */
                .exceptionHandling()
                .authenticationEntryPoint(new SecurityAuthenticationEntryPoint())
                .accessDeniedHandler(new RestAccessDeniedHandler())
                .and()
                /*
                  anonymous() consider no authentication as being anonymous instead of null in the security context.
                 */
                .anonymous()
                .and()
                /* No Http session is used to get the security context */
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .authorizeRequests()
                    /* All access to the authentication service are permitted without authentication (actually as anonymous) */
                 .antMatchers("/guard/**").permitAll()  
                
               
                    /* All the other requests need an authentication.
                     Role access is done on Methods using annotations like @PreAuthorize
                     */
                .anyRequest().authenticated()
                .and().csrf().disable();
    }


    private CorsFilter corsFilter() {
        /*
         CORS requests are managed only if headers Origin and Access-Control-Request-Method are available on OPTIONS requests
         (this filter is simply ignored in other cases).

         This filter can be used as a replacement for the @Cors annotation.
        */
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader(ORIGIN);
        config.addAllowedHeader(CONTENT_TYPE);
        config.addAllowedHeader(ACCEPT);
        config.addAllowedHeader(AUTHORIZATION);
        config.addAllowedMethod(GET);
        config.addAllowedMethod(PUT);
        config.addAllowedMethod(POST);
        config.addAllowedMethod(OPTIONS);
        config.addAllowedMethod(DELETE);
        config.addAllowedMethod(PATCH);
        config.setMaxAge(3600L);

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
//#00000001 Ends