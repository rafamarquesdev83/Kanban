package br.com.kanbanservice.util;

public final class JwtConstantes {

	public static final String AUTH_LOGIN_URL = "/autentica";

	
	public static final String JWT_SECRET = "sfdsdfffsdfsdfdssfsdfsdfsdsfdgdfgfdhgsfghgfshfshhgsdduhiuathrwiuthiuaewfireyubvaeuyrfvbuaewybcrufbvuyegruysfgayufdgasuwfsadyfyusdbfysud";

	
	public static final String TOKEN_HEADER = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String TOKEN_TYPE = "JWT";
	public static final String TOKEN_ISSUER = "secure-api";
	public static final String TOKEN_AUDIENCE = "secure-app";

	private JwtConstantes() {
		throw new IllegalStateException("Nao e possivel instanciar uma classe estatica");
	}
}


