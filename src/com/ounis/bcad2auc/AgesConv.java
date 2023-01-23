/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ounis.bcad2auc;

import com.ounis.romanorumnumero.RomanorumNumero;
import com.ounis.romanorumnumero.RomanorumNumeroConvertException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author AndroidDev
 */
public class AgesConv {

//    public static final String R_1_I = "I";
//    public static final String R_5_V = "V";
//    public static final String R_10_X = "X";
//    public static final String R_50_L = "L";
//    public static final String R_100_C = "C";
//    public static final String R_500_D = "D";
//    public static final String R_1000_M = "M";
//    
//    
//    public static final String ROMAN_ZERO = "nihil";
//    
//    public static final String[] UNITIES  = {R_1_I, R_1_I+R_1_I, R_1_I+R_1_I+R_1_I, R_1_I+R_5_V, R_5_V, 
//                                            R_5_V+R_1_I,R_5_V+R_1_I+R_1_I,R_5_V+R_1_I+R_1_I+R_1_I,R_1_I+R_10_X};
//    public static final String[] DOZENS = {R_10_X,R_10_X+R_10_X,R_10_X+R_10_X+R_10_X, R_10_X+R_50_L, R_50_L,
//                                            R_50_L+R_10_X, R_50_L+R_10_X+R_10_X,R_50_L+R_10_X+R_10_X+R_10_X, R_10_X+R_100_C};
//    public static final String[] HUNDREDS = {R_100_C, R_100_C+R_100_C,R_100_C+R_100_C+R_100_C,R_100_C+R_500_D,R_500_D,
//                                                R_500_D+R_100_C,R_500_D+R_100_C+R_100_C, R_500_D+R_100_C+R_100_C+R_100_C,R_100_C+R_1000_M};
//    public static final String[] THOUSANDS = {R_1000_M, R_1000_M+R_1000_M, R_1000_M+R_1000_M+R_1000_M, R_1000_M+R_1000_M+R_1000_M+R_1000_M,
//                                                R_1000_M+R_1000_M+R_1000_M+R_1000_M+R_1000_M,null,null,null,null};
//    
//    public static final String[][] ROMAN_DIGITS = { 
//                            UNITIES,
//                            DOZENS,
//                            HUNDREDS,
//                            THOUSANDS
//                                              };
//                        {R_1000_M, R_1000_M+R_1000_M, R_1000_M+R_1000_M+R_1000_M, R_1000_M+R_1000_M+R_1000_M+R_1000_M,
//                        null,null,null,null,null},
//                        
//                        {R_100_C, R_100_C+R_100_C,R_100_C+R_100_C+R_100_C,R_100_C+R_500_D,R_500_D,
//                        R_500_D+R_100_C,R_500_D+R_100_C+R_100_C, R_500_D+R_100_C+R_100_C+R_100_C,R_100_C+R_1000_M},
//
//                        {R_10_X,R_10_X+R_10_X,R_10_X+R_10_X+R_10_X, R_10_X+R_50_L, R_50_L,
//                        R_50_L+R_10_X, R_50_L+R_10_X+R_10_X,R_50_L+R_10_X+R_10_X+R_10_X, R_10_X+R_100_C},
//            
//                        {R_1_I, R_1_I+R_1_I, R_1_I+R_1_I+R_1_I, R_1_I+R_5_V, R_5_V, 
//                        R_5_V+R_1_I,R_5_V+R_1_I+R_1_I,R_5_V+R_1_I+R_1_I+R_1_I,R_1_I+R_10_X}
            

    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        
//        int val = 0;
//
//        val = (524 / 100) + 1;

//        for (String v: HUNDREDS)
//            System.out.println(v+" ");

//        String arabic_value = "2020";
//
Scanner scnr = new Scanner(System.in);
while (true) {
    System.out.print("\nType arabic number (q - quit): ");
    int number = 0;
    
    try {
        number = scnr.nextInt();
    }
    catch (InputMismatchException ime) {
        System.out.println("We finish...");
        break;
    }
    
    RomanorumNumero romanNumber = new RomanorumNumero(number, false);
    
    try {
        System.out.printf("Arabic number: %d\nRoman number: %s\n", number, romanNumber.getRomanNumber());
    }
    catch (RomanorumNumeroConvertException e) {
        System.out.printf("Oops, something went wrong\n - %s \n",e.getMessage());
    }
}

//        arabic_value = scnr.next();
//
//        StringBuffer sb = new StringBuffer(arabic_value);
//        arabic_value = sb.reverse().toString();
//
//
//        if (!arabic_value.equals("0")) {
//
//
//            String digits[] = arabic_value.split("");
//
//            String roman_v = "";
//
//             for (int x = 0;x <= digits.length-1;x++) {
//                 int ind = Math.abs(x - digits.length+1);
//                 if (digits[x].equals("0")) continue;
//                 String buff = ROMAN_DIGITS[ind][Integer.valueOf(digits[x])-1];
//                    roman_v += (buff != null)?buff:"--";
//             }
//
//            System.out.printf("\nArabic value: %s\nRoman value: %s\n", arabic_value,roman_v);
//        }
//        else
//            System.out.println(ROMAN_ZERO);
//


// tabela rzymskich liczb od 1 do 5000 do sprawdzenia poprawności konwersji
// https://www.tuomas.salste.net/doc/roman/numeri-romani-1-5000.html

//        for (int x = 0;x <= digits.length-1;x++) {
//
//            if (digits[x].equals("0")) continue;
//
//            int ind = Math.abs(x - digits.length+1);
//
//            if (ind == 3) {
//                    roman_v += THOUSANDS[Integer.valueOf(digits[x])-1];
//
//            }
//            if (ind == 2) {
//                    roman_v += HUNDREDS[Integer.valueOf(digits[x])-1];
//
//            }
//            if (ind == 1) {
//                    roman_v += DOZENS[Integer.valueOf(digits[x])-1];
//
//            }
//            if (ind == 0) {
//                    roman_v += UNITIES[Integer.valueOf(digits[x])-1];
//
//            }

//            int ind = x - digits.length;
//            if (Math.abs(ind) == 4) {
//                String d = digits[x];
//                roman_v += TOUSENDS[Integer.valueOf(digits[x])];
//
//            }
//        }


//        for (int i = digits.length-1;i>-1;i--) {
//            //System.out.println(digits[i]);
//            if ((digits.length-1 - i) == 0)
//                roman_v += UNITIES[Integer.valueOf(digits[i])-1];
//
//            if ((digits.length-1 - i) == 1)
//                roman_v += DOZENS[Integer.valueOf(digits[i])-1];
//
//            if ((digits.length-1 - i) == 2)
//                roman_v += HUNDREDS[Integer.valueOf(digits[i])-1];
//
//            if ((digits.length-1 - i) == 3)
//                roman_v += TOUSENDS[Integer.valueOf(digits[i])-1];
//        }




//System.out.println(ROMAN_DIGITS[3][2]);
    }
    
    /**
     * @param args the command line arguments
     */
    
}
