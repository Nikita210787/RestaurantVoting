package ru.restaurant_voting.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.restaurant_voting.model.Role;
import ru.restaurant_voting.model.User;
import ru.restaurant_voting.repository.UserRepository;
import ru.restaurant_voting.web.AuthUser;

import java.util.Optional;

@Configuration
@EnableWebSecurity
@Slf4j
@AllArgsConstructor
public class WebSecurityConfig {

    public static final PasswordEncoder PASSWORD_ENCODER = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    private final UserRepository userRepository;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/v1/api/user/profile/register/**").permitAll()
                .antMatchers("/v1/api/admin/**").hasRole(Role.ADMIN.name())    //recommendations for the project "api/admin/**").hasRole(Role.ADMIN.name())"
                .antMatchers("/v1/api/user/**").hasAnyRole(Role.ADMIN.name(),Role.USER.name())                     //https://stackoverflow.com/questions/35890540/when-to-use-spring-securitys-antmatcher
                .and().httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable();
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(
                        login -> {
                            log.debug("Authenticating '{}'", login);
                            Optional<User> optionalUser = userRepository.getByLogin(login);
                            return new AuthUser(optionalUser.orElseThrow(
                                    () -> new UsernameNotFoundException("User '" + login + "' was not found")));
                        })
                .passwordEncoder(PASSWORD_ENCODER);
    }

    @Bean
    // https://stackoverflow.com/a/70176629/548473
    public UserDetailsService userDetailsServiceBean(AuthenticationManagerBuilder auth) {
        return auth.getDefaultUserDetailsService();
    }
}