package com.moviewiki.api.user.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/img/**", "/js/**", "/css/**", "/images/**", "/fonts/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
           http.authorizeRequests()
                   .antMatchers("/", "/join").permitAll()
                   .antMatchers("/admin/**").hasRole("ADMIN")
                   .antMatchers("/member/**","/member_template/**").hasRole("MEMBER")
                   .antMatchers("/**").permitAll()
               .and()
                   .formLogin()
                       .loginPage("/login")
                       .defaultSuccessUrl("/loginSuccess")
                       .failureUrl("/loginFail")
                   .permitAll()
               .and()
                   .logout()	// 로그아웃 설정
                       .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                       .logoutSuccessUrl("/")
                       .invalidateHttpSession(true)
               .and()
                   .exceptionHandling()
                       .accessDeniedPage("/denied");
           http.csrf().disable();  // csrf 미적용
    }
}
