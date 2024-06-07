package io.github.Marcky404.Biblioteca.utils;

import io.github.Marcky404.Biblioteca.domain.enums.MensagemErro;
import io.github.Marcky404.Biblioteca.domain.enums.TipoTelefone;

import java.util.Arrays;

public class Utils {

    private Utils(){}
    public static String mascararCpf(String cpf) {

        String cpfFormatado = cpf.replaceAll("\\D", "");

        StringBuilder sb = new StringBuilder();

        sb.append(cpfFormatado, 0, 3).append(".");
        sb.append(cpfFormatado, 3, 6).append(".");
        sb.append(cpfFormatado, 6, 9).append("-");
        sb.append(cpfFormatado, 9, 11);

        return sb.toString();
    }

    public static Integer validarDdd(Integer ddd) {

        Integer[] codigosDDD = {
                11, 12, 13, 14, 15, 16, 17, 18, 19,
                21, 22, 24, 27, 28, 31, 32, 33, 34,
                35, 37, 38, 41, 42, 43, 44, 45, 46,
                47, 48, 49, 51, 53, 54, 55, 61, 62,
                64, 63, 65, 66, 67, 68, 69, 71, 73,
                74, 75, 77, 79, 81, 82, 83, 84, 85,
                86, 87, 88, 89, 91, 92, 93, 94, 95,
                96, 97, 98, 99};

        return Arrays.stream(codigosDDD).filter(d -> d == ddd).findFirst().orElseThrow(MensagemErro.DDD_INVALIDO::asBusinessException);

    }

    public static String validarNumero(String numero, TipoTelefone tipoTelefone) {
        String numeroFormatado = numero.replaceAll("\\D", "");

        if (tipoTelefone.equals(TipoTelefone.CELULAR) && !numeroFormatado.substring(0, 1).equals("9")) {
            throw MensagemErro.TELEFONE_INVALIDO.asBusinessException();
        }
        String numeroComHifen = formatarNumero(numeroFormatado);

        return numeroComHifen;

    }

    public static String formatarNumero(String numero) {
        StringBuilder sb = new StringBuilder();

        if (numero.length() == 9) {
            sb.append(numero.substring(0, 5)).append("-");
            sb.append(numero.substring(5, 9));

            return sb.toString();
        }
        sb.append(numero.substring(0, 4)).append("-");
        sb.append(numero.substring(4, 8));

        return sb.toString();
    }
}
