package es.sidelab.virtuasthenics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public UserRepositoryAuthenticationProvider authenticationProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/*
		 * PUBLIC PAGES
		 */
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/inicio").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/loginError").permitAll();
        http.authorizeRequests().antMatchers("/logout").permitAll();
        http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
		
		/*
		 * PRIVATE PAGES (all other pages)
		 */
		//http.authorizeRequests().anyRequest().authenticated();
        
        /*
        http.authorizeRequests().antMatchers("/clases").authenticated();
        http.authorizeRequests().antMatchers("/entrenamientos").authenticated();
        http.authorizeRequests().antMatchers("/dietas").authenticated();
		*/
		
		/*
		 * LOGIN FORM
		 */
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/inicio");
        http.formLogin().failureUrl("/loginError");
		
		/*
		 * LOGOUT
		 */
        http.logout().logoutUrl("/shutdown");
        http.logout().logoutSuccessUrl("/logout");

		/*
		 * CSRF
		 */
        http.csrf().ignoringAntMatchers("/h2-console/**");
		//http.csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * Database authentication provider.
		 */
		auth.authenticationProvider(authenticationProvider);
		/*
		 * USERS
		 */
		//auth.inMemoryAuthentication().withUser("user").password("pass").roles("USER");
		
		/*
		 * ADMIN
		 */
		//auth.inMemoryAuthentication().withUser("admin").password("adminpass").roles("USER", "ADMIN");
	}

}