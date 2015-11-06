/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Programador
 */
public class Metodos {
    
    public static LocalDate stringToDate(String data) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(data, formato);
    }
    
    public static String dateToString(LocalDate data)
    {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formato);        
    }
    
    
    public static double stringToDouble(String valor) {
        valor = valor.replace(".", "").replace(',', '.');
        return Double.parseDouble(valor);
    }

    public static String doubleBrasil(double valor) {
        return String.format("%.2f", valor);
        //return Double.toString(valor).replace(".", ",");        
    }

    
    /**
     * Retorna true se um valor double for reamente um double e se for maior ou igual a "min"
     * @param valor
     * @param min
     * @return 
     */
    public static boolean doubleValido(String valor, double min) {
        try {
            if (stringToDouble(valor) >= min) {
                return true;
            } else {
                return false;
            }
        } catch (Exception erro) {
            return false;
        }
    }

    
     /**
     * Retorna true se um valor inteiro for reamente um inteiro e se for maior ou igual a "min"
     * @param valor
     * @param min
     * @return 
     */
    public static boolean integerValido(String valor, int min) {
        try {
            if ( Integer.parseInt(valor) >= min) {
                return true;
            } else {
                return false;
            }
        } catch (Exception erro) {
            return false;
        }
    }

    
    
    public static String stringNotNull (String valor)
    {
        if (valor == null)
            return "";
        else
            return valor;
    }

}
