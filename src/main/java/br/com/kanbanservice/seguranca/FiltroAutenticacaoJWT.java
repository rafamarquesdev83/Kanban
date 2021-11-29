package br.com.kanbanservice.seguranca;

import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.kanbanservice.util.JwtConstantes;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;



public class FiltroAutenticacaoJWT extends UsernamePasswordAuthenticationFilter{


	private final AuthenticationManager authenticationManager;

	public FiltroAutenticacaoJWT (AuthenticationManager authenticationManager) {

		this.authenticationManager = authenticationManager;

		setFilterProcessesUrl(JwtConstantes.AUTH_LOGIN_URL);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		var username = request.getParameter("usuario");
		var password = request.getParameter("senha");
		var authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

		return authenticationManager.authenticate(authenticationToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain, Authentication authentication) {
		var user = ((User) authentication.getPrincipal());

		var roles = user.getAuthorities()
				.stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		var signingKey = JwtConstantes.JWT_SECRET.getBytes();

		var token = Jwts.builder()
				.signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
				.setHeaderParam("typ", JwtConstantes.TOKEN_TYPE)
				.setIssuer(JwtConstantes.TOKEN_ISSUER)
				.setAudience(JwtConstantes.TOKEN_AUDIENCE)
				.setSubject(user.getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + 864000000))
				.claim("rol", roles)
				.compact();

		response.addHeader(JwtConstantes.TOKEN_HEADER, JwtConstantes.TOKEN_PREFIX + token);
	}




}
