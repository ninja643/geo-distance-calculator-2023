package rs.ac.ni.pmf.rwa.geodistance.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
		System.out.println("Configuring filter chain");
		return http
				.authorizeHttpRequests(requests -> requests
						.antMatchers("/doc", "/", "/geo-distance").permitAll()
						.antMatchers(HttpMethod.GET, "/locations").authenticated()
						.antMatchers(HttpMethod.POST, "/locations").hasAnyAuthority("ADMIN", "USER")
						.antMatchers(HttpMethod.PUT, "/locations/*").hasAnyAuthority("ADMIN", "USER")
						.antMatchers(HttpMethod.DELETE, "/locations/*").hasAnyAuthority("ADMIN")
						.anyRequest().authenticated()
				)
				.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService getUserDetailsService(PasswordEncoder passwordEncoder)
	{
		UserDetails user =
				User.builder()
						.passwordEncoder(passwordEncoder::encode)
						.username("user")
						.password(passwordEncoder.encode("user"))
						.disabled(false)
						.accountExpired(false)
						.accountLocked(false)
						.roles("USER", "GUEST")
						.build();
		System.out.println(user);

		UserDetails guest =
				User.builder()
						.username("guest")
						.password(passwordEncoder.encode("guest"))
						.roles("GUEST")
						.build();

		UserDetails admin =
				User.builder()
						.username("admin")
						.password(passwordEncoder.encode("admin"))
						.roles("ADMIN")
						.build();


		return new InMemoryUserDetailsManager(user, guest, admin);
	}
}
