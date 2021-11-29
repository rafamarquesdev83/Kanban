package br.com.kanbanservice.util;

import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

public class TokenUtil {

	private static final int SENHA_TOKEN_LEN = 6;
	
	private static final String[] HASH = {
            "A","B","C","D","E","F","G","H","K","M","N","P","R","S","T","X","Z",
            "2","3","4","5","6","7","8","9"
    };

	public static String tokenSessao() {
		return UUID.randomUUID().toString().replaceAll("\\-","");
	}

	public static String gerarNovaSenha() {
		return token(SENHA_TOKEN_LEN);
	}

	private static String token(int tokenLen) {
		Random random = new Random();
		StringBuilder builder = new StringBuilder();
		IntStream.range(0, tokenLen).forEach( i -> {
			int num = random.nextInt(HASH.length);
			builder.append(HASH[num]);
		});
		return builder.toString();
	}
}
