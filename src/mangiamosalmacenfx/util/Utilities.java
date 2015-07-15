/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mangiamosalmacenfx.util;

import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Héctor Manuel Rodríguez Méndez
 */
public class Utilities {
    
    
    public static String clearPrice(String cdn){
        StringTokenizer token = new StringTokenizer(cdn,"$,");
        int numIngre = token.countTokens();
        String tmp = "";
        while(token.hasMoreTokens())
            tmp += token.nextToken();
        return tmp;
    }
    
    
    /**
     * Convierte una cadena en un double
     * @param cadena cadena a convertir
     * @return regresa -1 si hubo algun error
     */
    public static double convertDouble(String cadena){
        try{
            double num = Double.parseDouble(cadena);
            return num;
        }
        catch(NumberFormatException | NullPointerException e){
            return -1.0;
        }
    }
    
    /**
     * Convierte una cadena a entero
     * @param cadena cadena a convertir
     * @return Devuelve -1 si hay algun 
     * error
     */
    public static int convertInt(String cadena){
        try{
            int num = Integer.parseInt(cadena);
            return num;
        }
        catch(NumberFormatException | NullPointerException e){
            return -1;
        }
    } 
    
    /**
     * Convierte una cadena a entero
     * @param cadena cadena a convertir
     * @return Devuelve -1 si hay algun 
     * error
     */
    public static long toPhone(String cadena){
        try{
            long num = Long.parseLong(cadena);
            return num;
        }
        catch(NumberFormatException | NullPointerException e){
            return -1;
        }
    }
    
    /**
     * Formatea un double a representacion monetaria
     * @param aValor precio a formatear
     * @return Devuelve un cadena formateado con el precio
     */
    public static String fomotMoney(double aValor){
        NumberFormat format = NumberFormat.getCurrencyInstance();
        return format.format(aValor);
    }
    
    /**
     * Devuelve la Fecha actual del sistema
     * @return Timestamp con la fecha
     */
    public static Timestamp getCurrentTime(){
        long time = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(time);
        return timestamp;
    }
    
    /**
     * Formatea una cadena a formato para mexico
     * @param timestamp
     * @return 
     */
    public static String formatTime(Timestamp timestamp){
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        return format.format(timestamp);
    }
    
    /**
     * Formatea una cadena a formato para mexico
     * @param timestamp
     * @return 
     */
    public static String formatTimeShow(Timestamp timestamp){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
        return format.format(timestamp);
    }
    
    /**
     * Convierte una cadena a date
     * @param str cade a convertir
     * @return Devuelve un Date si tuvo exito o 
     * null en caso contrario
     */
    public static Date stringToDate(String str){
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            Date date = format.parse(str);
            return date;
        } catch (ParseException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Valida que la fila seleccionada sea valida
     * @param row numero de la fila
     * @return sila fila es mayor a -1 devuelve true
     * sino false
     */
    public static boolean validateRow(int row){
        if(row > -1)
            return true;
        else
            return false;
    }
    
    
}
