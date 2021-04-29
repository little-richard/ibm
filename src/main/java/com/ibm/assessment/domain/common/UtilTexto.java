package com.ibm.assessment.domain.common;

import org.apache.logging.log4j.util.Strings;

public class UtilTexto {

    public static String formatarSomenteNumeros(String texto) {
        if (Strings.isNotBlank(texto)) {
            return texto.replaceAll("\\D+", "");
        } else {
            return texto;
        }
    }

}
