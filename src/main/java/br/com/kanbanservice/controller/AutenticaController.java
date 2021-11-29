package br.com.kanbanservice.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kanbanservice.repository.UsuarioRepository;
import br.com.kanbanservice.service.model.Usuario;
import br.com.kanbanservice.util.AES;
import br.com.kanbanservice.util.JwtConstantes;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@RestController
@RequestMapping("/autentica")
public class AutenticaController {

	@Autowired
	UsuarioRepository usuarioRepository;

	@PostMapping("/login")
	public  ResponseEntity<String> login(String email, String senha) throws Exception {

		Usuario usuario = usuarioRepository.findByEmailAndSenha(email, AES.encrypt(senha));

		if(usuario != null) {

			var signingKey = JwtConstantes.JWT_SECRET.getBytes();

			Integer idUsuario = getIdUsuario(usuario);

			String jwtToken = Jwts.builder()
					.setSubject(String.valueOf(idUsuario))
					.setIssuedAt(new Date())
					.setAudience(JwtConstantes.TOKEN_AUDIENCE)
					.setExpiration(
							Date.from(
									LocalDateTime.now().plusMinutes(15L)
									.atZone(ZoneId.systemDefault())
									.toInstant()))
					.signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
					.compact();

			return ResponseEntity.status(HttpStatus.OK).body(jwtToken);

		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	private Integer getIdUsuario(Usuario usuario) throws Exception {
		if(usuario != null) {
			return usuario.getId();
		}
		throw new Exception("Usuario nao encontrado");
	}
}



