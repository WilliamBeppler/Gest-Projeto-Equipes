package br.com.gestaoprojetos.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classe utilitária para funções de segurança, como a codificação de senhas.
 */
public class SegurancaUtil {

    /**
     * Gera um hash SHA-256 para uma dada string (senha).
     * Este método garante que a mesma senha irá gerar sempre o mesmo hash.
     *
     * @param senha A senha em texto plano.
     * @return A representação da senha em hash SHA-256.
     */
    public static String gerarHashSHA256(String senha) {
        try {
            // MessageDigest é a classe do Java que nos permite criar hashes
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Aplica o hash à senha (convertida para bytes)
            byte[] hashCodificado = digest.digest(senha.getBytes(StandardCharsets.UTF_8));

            // Converte o array de bytes do hash para uma representação em hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashCodificado) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            // Esta exceção aconteceria se o algoritmo "SHA-256" não existisse,
            // o que é praticamente impossível num Java moderno.
            System.err.println("Erro ao gerar hash: " + e.getMessage());
            return null;
        }
    }
}

