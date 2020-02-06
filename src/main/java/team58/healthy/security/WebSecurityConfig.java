package team58.healthy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import team58.healthy.security.auth.RestAuthenticationEntryPoint;
import team58.healthy.security.auth.TokenAuthenticationFilter;
import team58.healthy.service.UserService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    };

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private UserService jwtExtendedUserDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(jwtExtendedUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Autowired
    TokenUtils tokenUtils;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
                .authorizeRequests().antMatchers("/auth/**").permitAll().and()
                .authorizeRequests().antMatchers("/api/checkups/confirm/token:{token}/request:{id}/hall:{hid}").permitAll().and()
                .authorizeRequests().antMatchers("/api/checkups/cancel/token:{token}/request:{id}/hall:{hid}").permitAll().and()
                .authorizeRequests().antMatchers("/api/registration/verify/user:{token}/request:{requestId}").permitAll().and()
                .authorizeRequests().antMatchers("/api/registration/new").permitAll()
                .anyRequest().authenticated().and()
                .cors().and()
                .addFilterBefore(new TokenAuthenticationFilter(tokenUtils, jwtExtendedUserDetailsService),
                        BasicAuthenticationFilter.class);

        httpSecurity.csrf().disable();
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers(HttpMethod.POST, "auth/login");
        webSecurity.ignoring().antMatchers(HttpMethod.GET, "/api/checkups/confirm/token:{token}/request:{id}/hall:{hid}");
        webSecurity.ignoring().antMatchers(HttpMethod.GET, "/api/checkups/cancel/token:{token}/request:{id}/hall:{hid}");
        webSecurity.ignoring().antMatchers(HttpMethod.PUT, "/api/registration/verify/user:{token}/request:{requestId}");
        webSecurity.ignoring().antMatchers(HttpMethod.POST, "api/registration/new");
    }
}
