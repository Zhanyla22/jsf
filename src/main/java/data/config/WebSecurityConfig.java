package data.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvcSecurity
@EnableWebMvc
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/login.xhtml")
                .and()
                .authorizeRequests()
                .antMatchers("/login.xhtml")
                .permitAll()
                .antMatchers("/admin.xhtml")
                .hasRole("ADMIN")
                .antMatchers("/hello.xhtml")
                .hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin()
                .loginPage("/login.xhtml")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("main.xhtml")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/accessDenied.xhtml");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
