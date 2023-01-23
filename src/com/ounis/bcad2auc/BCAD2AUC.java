/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


// https://dzone.com/articles/how-build-fat-jar-using
// jak zrobić FAT jar czyli paczkę zawierająca wszystkie biblioteki

package com.ounis.bcad2auc;

import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author AndroidDev
 */
public class BCAD2AUC {

    /**
     * @param args the command line arguments
     */
    
    public static void runMainFrameAlt() {
            java.awt.EventQueue.invokeLater(new Runnable() {
               @Override
               public void run() {
                    MainFrameAlt mainFrame = new MainFrameAlt();
                    mainFrame.setVisible(true);
                    mainFrame.centerFrame();
                    mainFrame.setTitle(CONST.FRAME_TITLE);
                    mainFrame.setResizable(false);
                    
                    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               }
             });        
    }
    
    public static void main(String[] args) {
        // TODO code application logic here

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrameAlt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrameAlt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrameAlt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrameAlt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }        
        
//         java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MainFrameAlt().setVisible(true);

                System.out.println("Wybierz tryb aplikacji:\n"+
                                    "1. BC AD 2 AUC (formularz)\n"+
                                    "2. konwersja liczb arabskich na rzymskie (cmd line)"
                        
                );
                System.out.print(">");
                
                Scanner scr = new Scanner(System.in);
                String key = scr.next();
                
                if ("1".equals(key)) {
                   runMainFrameAlt();
                }
                if (key.equals("2"))
                    AgesConv.main(args);
            }
//        });
//    }
    
}
