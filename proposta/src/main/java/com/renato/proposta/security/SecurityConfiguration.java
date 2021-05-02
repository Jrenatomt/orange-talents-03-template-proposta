package com.renato.proposta.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@Profile("dev")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests(authorizeRequests -> authorizeRequests
				.antMatchers(HttpMethod.GET, "/api/propostas/**").hasAuthority("SCOPE_propostas:read")
				.antMatchers(HttpMethod.GET, "/api/cartoes/**").hasAuthority("SCOPE_cartoes:read")
				.antMatchers(HttpMethod.POST, "/api/cartoes/**").hasAuthority("SCOPE_cartoes:write")
				.antMatchers(HttpMethod.POST, "/api/propostas/**").hasAuthority("SCOPE_propostas:write")
				.anyRequest().authenticated())
				.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
		        .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}
