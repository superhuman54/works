package kr.co.tabling.works.config;

import kr.co.tabling.works.security.oauth2.CustomOAuth2UserService;
import kr.co.tabling.works.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import kr.co.tabling.works.security.oauth2.OAuth2AuthenticationFailureHandler;
import kr.co.tabling.works.security.oauth2.OAuth2AuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class WebConfiguration {


    @Configuration
    @Order(2)
    public static class AppSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/admin/**")
                    .csrf()
                        .disable()
                    .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and()
                    .authorizeRequests()
                        .antMatchers("/webjars/**").permitAll()
                        .anyRequest().hasRole("USER")
                        .and()
                    .httpBasic()
                        .realmName("APP SECURITY");

        }
    }

    @Configuration
    @Order(1)
    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private CustomOAuth2UserService customOAuth2UserService;

        @Autowired
        private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

        @Autowired
        private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;


        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/**")
                    .csrf()
                        .disable()
                    .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and()
                        .authorizeRequests()
                            .antMatchers("/",
                                    "/assets/**",
                                    "/css/**",
                                    "/js/**",
                                    "/webjars/**").permitAll()
                            .antMatchers("/auth/**", "/oauth2/**")
                                .permitAll()
                            .anyRequest()
                                .authenticated()
                            .and()
                    .oauth2Login()
                        .authorizationEndpoint()
                            .baseUri("/oauth2/authorize") // OAuth2AuthorizationRequestRedirectFilter
                            .authorizationRequestRepository(cookieAuthorizationRequestRepository())
                            .and()
                        .redirectionEndpoint() // OAuth2LoginAuthenticationFilter
                            .baseUri("/oauth2/callback/*")
                            .and()
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService)
                            .and()
                        .successHandler(oAuth2AuthenticationSuccessHandler)
                        .failureHandler(oAuth2AuthenticationFailureHandler);
        }

        @Bean
        public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
            return new HttpCookieOAuth2AuthorizationRequestRepository();
        }

    }

}
