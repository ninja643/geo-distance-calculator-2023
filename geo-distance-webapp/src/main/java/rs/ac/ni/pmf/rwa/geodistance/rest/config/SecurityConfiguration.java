package rs.ac.ni.pmf.rwa.geodistance.rest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import rs.ac.ni.pmf.rwa.geodistance.rest.dto.ErrorCode;
import rs.ac.ni.pmf.rwa.geodistance.rest.dto.ErrorDTO;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration
{
	private static final String ADMIN = "ADMIN";
	private static final String USER = "USER";
	private static final String GUEST = "GUEST";

	private final ObjectMapper objectMapper;

	@Bean
	public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception
	{
		http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeRequests(registry -> registry
						.antMatchers("/doc", "/", "/geo-distance").permitAll()
						.antMatchers(HttpMethod.GET, "/locations", "/locations/**").authenticated()
						.antMatchers(HttpMethod.POST, "/locations").hasAnyAuthority(USER, ADMIN)
						.antMatchers(HttpMethod.PUT, "/locations/*").hasAnyAuthority(USER, ADMIN)
						.antMatchers(HttpMethod.DELETE, "/locations/*").hasAuthority(ADMIN)
						.anyRequest().denyAll()
				)
				.exceptionHandling(exceptions -> exceptions
						.accessDeniedHandler((request, response, accessDeniedException) -> {
							response.setStatus(HttpStatus.FORBIDDEN.value());
							response.setContentType(MediaType.APPLICATION_JSON_VALUE);

							final ErrorDTO errorDTO = ErrorDTO.builder()
									.code(ErrorCode.UNAUTHORIZED_ACCESS)
									.details(
											"You don't have rights to access '" + request.getServletPath() + "'. " + accessDeniedException.getMessage())
									.build();

							response.getWriter().println(objectMapper.writeValueAsString(errorDTO));
						}))
				.httpBasic(httpCustomizer -> httpCustomizer
						.authenticationEntryPoint((request, response, authException) -> {
							response.setStatus(HttpStatus.UNAUTHORIZED.value());
							response.setContentType(MediaType.APPLICATION_JSON_VALUE);

							final ErrorDTO errorDTO = ErrorDTO.builder()
									.code(ErrorCode.BAD_LOGIN)
									.details("Bad username and/or password. " + authException.getMessage())
									.build();

							response.getWriter().println(objectMapper.writeValueAsString(errorDTO));
						}))
		;


		return http.build();
	}

	@Bean
	public UserDetailsService getUserDetailsService(PasswordEncoder passwordEncoder)
	{
		UserDetails user =
				User.builder()
						.username("user")
						.password(passwordEncoder.encode("user"))
						.disabled(false)
						.accountExpired(false)
						.accountLocked(false)
						.authorities(USER, GUEST)
						.build();
		System.out.println(user);

		UserDetails guest =
				User.builder()
						.username("guest")
						.password(passwordEncoder.encode("guest"))
						.roles(GUEST)
						.build();

		UserDetails admin =
				User.builder()
						.username("admin")
						.password(passwordEncoder.encode("admin"))
						.roles(ADMIN)
						.build();


		return new InMemoryUserDetailsManager(user, guest, admin);
	}

//	@Bean
//	public WebSecurityCustomizer webSecurityCustomizer()
//	{
//		return web -> web.ignoring().antMatchers("/swagger-ui/*", "/v3/api-docs*/**");
//	}

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

}
