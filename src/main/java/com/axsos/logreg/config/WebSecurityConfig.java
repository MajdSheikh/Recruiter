package  com.axsos.logreg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
public class WebSecurityConfig {
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.
	            authorizeRequests()
	                .antMatchers("/admin/**").hasRole("ADMIN") 
//	                .antMatchers("/css/**", "/js/**","/","/Regpage","/loginpage").permitAll()
	                .anyRequest().permitAll();
//	                .and()
//	                .antMatchers("/admin").hasRole("ADMIN");
//	            	.formLogin()
//	                .loginPage("/loginpage");
//	                .permitAll()
//	                .and()
//	                .logout()
//	                .permitAll();
		
		return http.build();
	}
}