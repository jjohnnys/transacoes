package br.com.jjohnnys.importacao_transacoes_diarias.shared.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static LocalDate stringToLocalDate(String data) {

        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataFormatada = LocalDate.parse(data, dtf);
        return dataFormatada;

    }
    
}
