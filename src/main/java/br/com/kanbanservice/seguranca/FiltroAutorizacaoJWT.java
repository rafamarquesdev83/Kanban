package br.com.kanbanservice.seguranca;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.kanbanservice.util.JwtConstantes;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;


@Component
public class FiltroAutorizacaoJWT extends OncePerRequestFilter {

	private static final Logger log = LoggerFactory.getLogger(FiltroAutorizacaoJWT.class);


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		var authentication = getAuthentication(request);
		if (authentication == null) {
			filterChain.doFilter(request, response);
			return;
		}

		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		var token = request.getHeader(JwtConstantes.TOKEN_HEADER);
		if (StringUtils.isNotEmpty(token)) {
			try {
				var signingKey = JwtConstantes.JWT_SECRET.getBytes();

				var parsedToken = Jwts.parser()
						.setSigningKey(signingKey)
						.parseClaimsJws(token.replace("Bearer ", ""));

				var username = parsedToken
						.getBody()
						.getSubject();

				

				if (StringUtils.isNotEmpty(username)) {
					
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null, null);
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					return usernamePasswordAuthenticationToken;
				}
			} catch (ExpiredJwtException exception) {
				log.warn("Request to parse expired JWT : {} failed : {}", token, exception.getMessage());
			} catch (UnsupportedJwtException exception) {
				log.warn("Request to parse unsupported JWT : {} failed : {}", token, exception.getMessage());
			} catch (MalformedJwtException exception) {
				log.warn("Request to parse invalid JWT : {} failed : {}", token, exception.getMessage());
			} catch (SignatureException exception) {
				log.warn("Request to parse JWT with invalid signature : {} failed : {}", token, exception.getMessage());
			} catch (IllegalArgumentException exception) {
				log.warn("Request to parse empty or null JWT : {} failed : {}", token, exception.getMessage());
			}
		}

		return null;
	}


}
