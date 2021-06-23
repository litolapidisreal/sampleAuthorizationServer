package com.sampleAuth.server.security;

import com.sampleAuth.server.jwt.JwtAuthenticationEntryPoint;
import com.sampleAuth.server.jwt.JwtFilterRequest;
import com.sampleAuth.server.jwt.JwtSecurityCheck;
import com.sampleAuth.server.service.UserLoginService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserLoginService userLoginService;
    private final JwtFilterRequest jwtFilterRequest;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public ApplicationSecurityConfig(BCryptPasswordEncoder passwordEncoder,
                                     UserLoginService userLoginService,
                                     JwtFilterRequest jwtFilterRequest,
                                     JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.passwordEncoder = passwordEncoder;
        this.userLoginService = userLoginService;
        this.jwtFilterRequest = jwtFilterRequest;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/register/signUp","/authenticate", "/h2-ui/**").permitAll()
//                .antMatchers(HttpMethod.POST,)
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);

        http.addFilterAfter(jwtFilterRequest, JwtSecurityCheck.class);

        http.headers().frameOptions().sameOrigin().cacheControl();;
    }

    /**
     * for Access in Controller Level
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }


/**For DB Based Encryption for Login
     *NOTE: Needs seperate passwordEncode
     * password encryption
     */

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider =
                new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userLoginService);
        return daoAuthenticationProvider;
    }
}
