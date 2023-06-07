package rs.ac.ni.pmf.rwa.geodistance.rest.config;

import javax.servlet.ServletException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration
{
	@Bean
	public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception
	{
		http
			.authorizeHttpRequests(requests -> requests
				.antMatchers("/logout", "/doc", "/", "/geo-distance").permitAll()
				.antMatchers(HttpMethod.GET, "/locations").authenticated()
				.antMatchers(HttpMethod.POST, "/locations").hasAnyAuthority("ADMIN", "USER")
				.antMatchers(HttpMethod.PUT, "/locations/*").hasAnyAuthority("ADMIN", "USER")
				.antMatchers(HttpMethod.DELETE, "/locations/*").hasAnyAuthority("ADMIN")
				.anyRequest().authenticated()
			);

		http.httpBasic(Customizer.withDefaults());
//		http.formLogin(Customizer.withDefaults());
		http.logout(logout -> logout
			.logoutUrl("/logout")
			.addLogoutHandler(((request, response, authentication) -> {
				try
				{
					request.logout();
				}
				catch (ServletException e)
				{
					throw new RuntimeException(e);
				}
			})));

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService getUserDetailsService(PasswordEncoder passwordEncoder)
	{
		UserDetails user = User.builder()
			.username("user")
			.password(passwordEncoder.encode("user"))
			.authorities("USER")
			.build();

		UserDetails guest =
			User.builder()
				.username("guest")
				.password(passwordEncoder.encode("guest"))
				.authorities("GUEST")
				.build();

		UserDetails admin =
			User.builder()
				.username("admin")
				.password(passwordEncoder.encode("admin"))
				.authorities("ADMIN")
				.build();

		return new InMemoryUserDetailsManager(user, guest, admin);
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer()
	{
		return web -> web.ignoring().antMatchers("/swagger-ui/*", "/v3/api-docs*/**");
	}

	//	public static void main(String[] args)
	//	{
	//		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	//		System.out.println(passwordEncoder.encode("user"));
	//	}
}
