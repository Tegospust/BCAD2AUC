/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ounis.bcad2auc;

import com.ounis.convtools.ConvTools;
import com.ounis.ftools.FTools;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import com.ounis.frames.BaseFrame;
import com.ounis.romanorumnumero.RomanorumNumero;
import com.ounis.romanorumnumero.RomanorumNumeroConvertException;
/**
 *
 * @author AndroidDev
 */
public class MainFrameAlt extends BaseFrame implements ActionListener {

    
    
    private ArrayList<YearDescription> yearDescList;
    
    
    private int convertYear(int aYear) {
        return CONST.AB_URBE_CONDITA_YEAR + aYear;
    }
    
    
//    private String yearDescriptions(int aYear) {
//        String result;
//        
//        switch(aYear) {
//            case 1453:  result = "Upadek Cesarstwa wschodniorzymskiego.\nKonstantynopol zdobyty przez Mehmeda II";
//                        break;
//            
//            case 1204:  result = "Drugie zdobycie Konstantynopola podczas IV. wyprawy krzyżowej";
//                        break;
//            case 1203:  result = "Pierwsze zdobycie Konstantynopola podczas IV. wyprawy krzyżowej";
//                        break;
//            case 476 :  result = "Upadek Cesarstwa zachodniorzymskiego";
//                        break;
// 
//            case 9  :   result = "Bitwa w Lesie Teutoburskim";
//                        break;
//                        
//            case -43 :  result = "Marek Tulliusz Cyceron zostaje zamordowany ";
//                        break;
//                        
//            case -44 :  result = "Gaiusz Juliusz Cezar zostaje zamordowany";
//                        break;
//                        
//            case -100 : result = "Narodziny Gaiusza Juliusza Cezara";
//                        break;
//            case -106 : result = "Narodziny Marka Tulliusza Cycerona";
//                        break;
//            case -264 : result = "Początek I Wojny Punickiej";
//                        break;
//                        
//            case -753 : result = "Romulus i Remus dają początek Rzymowi";
//                        break;
//                        
//                        
//            default :   result = "- nic nadzwyczajnego się nie wydarzyło";
//        }
//        
//        return result;
//    }
    
    
    private void setAUCYearText(int aYear) {
       lblAUCYear.setText(String.format("%d %s", convertYear(aYear), CONST.AB_URBE_CONDITA)); 
    }
    
    private void setGregYearText(int aYear) {
        lblGregYear.setText(String.format("%d %s", Math.abs(aYear), (aYear < 0)?CONST.BC_DESC:CONST.AD_DESC));
    }
    
    
    private void loadFileEvents(ArrayList<YearDescription> arrList, String filename) {
        
        if (FTools.fileExistsEx(filename)) {
            BufferedReader eventFile = null;
            boolean ioSuccess = true;
            try {
                eventFile = new BufferedReader(
                              new InputStreamReader(new FileInputStream(filename)));

            } catch (FileNotFoundException e) {
                ioSuccess = false;
            }

            if (ioSuccess) {
                int line_num = 0;
                String buffer = "";
                    try {
                        while ((buffer = eventFile.readLine()) != null) {
                            // pomijaj wiersze z komentarzami
                            line_num++;
                            if (!buffer.contains("***")) {
                                String[] line = buffer.split("=");
    //                            if (ConvTools.isInt(line[0])) {
    //                                YearDescription yd = new YearDescription(Integer.valueOf(line[0]),line[1]);
    //                                arrList.add(yd);
                                try {
                                    YearDescription yd = new YearDescription(ConvTools.str2Int(line[0]),line[1]);
                                    arrList.add(yd);
                                }
                                catch (Exception e) {
                                    JOptionPane.showMessageDialog(null, String.format("Coś poszło nie tak podczas wczytywania wydarzeń z pliku: %s\n"+
                                    "Opis błędu: %s\nNumer wiersza: %d\n"+
                                    "Zawartość: %s"        ,filename,e, line_num,buffer));
//                                    continue;
                                }
                            }                                
                        }
                }
                catch (Exception e) {
                    JOptionPane.showMessageDialog(null, String.format("Błąd podczas próby odczytu pliku z opisami: %s",filename));
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, String.format("Błąd otwarcia pliku z opisami: %s",filename));
            }
        }
//        else 
//            JOptionPane.showMessageDialog(this, "Brak pliku z opisami wydarzeń."); 
     }
    
    
    
    private String getDescByYear(ArrayList<YearDescription> arrList, int aYear) {
        String result = CONST.NO_DESC_4_YEAR;

        for (YearDescription yd: arrList) {
            if (aYear == yd.getYear()) {
                result = yd.getDescription();
                break;
            }
        }
        return result;
    }
    
    /**
     * Creates new form MainFrameAlt
     */
    public MainFrameAlt() {
        initComponents();
        
        
//        this.setTitle(CONST.FRAME_TITLE);
//        this.setResizable(false);
//        centerFrame();
        
        taDescription.setEditable(false);
        
        sldYear.setValue(-CONST.AB_URBE_CONDITA_YEAR);
        
        
        yearDescList = new ArrayList<>();

        loadFileEvents(yearDescList, CONST.EVENT_FILE_NAME);
        if (!yearDescList.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                    String.format("Wczytanych linii opisów: %d",yearDescList.size()));
        }
        else 
            JOptionPane.showMessageDialog(this, "Brak pliku z opisami wydarzeń."); 

        
        teTestDirInput.setVisible(false);
        
        // domyślne ustawenia
        int y = sldYear.getValue();
        setAUCYearText(y);
        setGregYearText(y);
        taDescription.setText(getDescByYear(yearDescList,y));
        
        // obsługa zdarzeń na kontrolkach
        
        sldYear.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent arg0) {
                int year = sldYear.getValue();
                
                setAUCYearText(year);
                //lblAUCYear.setText(String.format("%d %s", convertYear(year), CONSTS.AB_URBE_CONDITA));
                
                setGregYearText(year);
                //lblGregYear.setText(String.format("%d %s", Math.abs(year), (year < 0)?CONSTS.BC_DESC:CONSTS.AD_DESC));
                
                taDescription.setText(getDescByYear(yearDescList,year));
                
//                                        int year = ConvTools.str2Int(val);
                int age = Math.abs((year / 100))+1;
                RomanorumNumero romanNum = new RomanorumNumero(Math.abs(age), true);
                try {
                    String msgTxt = romanNum.getRomanNumber() + ((year>=0)?" w. "+(CONST.AD_DESC).split(" ")[1]:" w. "+(CONST.BC_DESC).split(" ")[1]); 
                    //JOptionPane.showMessageDialog(null, msgTxt);
                    lblAge.setText(msgTxt);
                }
                catch (RomanorumNumeroConvertException rne) {
                    JOptionPane.showMessageDialog(null,"Błąd konwersji na liczbę rzymską\n"+rne.getMessage());
                }

            }
        });
        
        //String filename = FTools.getAppPath()+"\\rome_events.txt";
//        
//        if (!FTools.fileExistsEx(filename)) {
//            FileWriterLn file;
//            JOptionPane.showMessageDialog(this, "Brak pliku!!!");
//            try {
//                file = new FileWriterLn(filename);
//                file.writeln("*** format pliku: rok=opis wydarzenia");
//                file.writeln("*** lata p.n.e. - liczby ujemne");
//                file.flush();
//                file.close();
//            }
//            catch (Exception e) {
//                JOptionPane.showMessageDialog(this, "coś poszło nie tak");
//            }
//            
//        }
//        else
//            JOptionPane.showMessageDialog(this, "Plik gotowy do wczytania!!");
        

        sldYear.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clkCount = e.getClickCount();
                if (clkCount == 2) {
                    boolean isv = teTestDirInput.isVisible();
                    teTestDirInput.setVisible(!teTestDirInput.isVisible());
                    if (teTestDirInput.isFocusable())
                        teTestDirInput.requestFocus();
                    pack();
//                    MainFrameAlt.this.repaint();
                    
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

//        this.addMouseListener(new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//            }
//        });
        
  
          teTestDirInput.addActionListener(this);
//        teTestDirInput.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String val = teTestDirInput.getText();
//                if (ConvTools.isInt(val)) {
//                    
//                        sldYear.setValue(ConvTools.str2Int(teTestDirInput.getText()));
//                        
//                        
//                }
//                else {
//                    JOptionPane.showMessageDialog(null, "Wprowadź liczbę całkowitą");
//                    teTestDirInput.setText(CONST.TEST_DIRECT_INPUT_DEF_VALUE);
//                }
//                
//                
//            } 
//        });

    }

    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sldYear = new javax.swing.JSlider();
        lblAUCYear = new javax.swing.JLabel();
        lblGregYear = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDescription = new javax.swing.JTextArea();
        teTestDirInput = new javax.swing.JTextField();
        lblAge = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sldYear.setMaximum(1453);
        sldYear.setMinimum(-753);
        sldYear.setToolTipText("Dwuklik otwiera pole do bezpośredniego wprowadzania roku");

        lblAUCYear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblAUCYear.setForeground(new java.awt.Color(255, 51, 51));
        lblAUCYear.setText("---");

        lblGregYear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblGregYear.setText("0");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("==>");

        taDescription.setBackground(new java.awt.Color(255, 255, 204));
        taDescription.setColumns(20);
        taDescription.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        taDescription.setLineWrap(true);
        taDescription.setRows(5);
        jScrollPane1.setViewportView(taDescription);

        teTestDirInput.setText(CONST.TEST_DIRECT_INPUT_DEF_VALUE);

        lblAge.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblAge.setText("XXXXXX");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sldYear, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAge)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(teTestDirInput, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblGregYear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(35, 35, 35)
                        .addComponent(lblAUCYear, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sldYear, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGregYear)
                    .addComponent(lblAUCYear)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(lblAge)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(teTestDirInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAUCYear;
    private javax.swing.JLabel lblAge;
    private javax.swing.JLabel lblGregYear;
    private javax.swing.JSlider sldYear;
    private javax.swing.JTextArea taDescription;
    private javax.swing.JTextField teTestDirInput;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(teTestDirInput)) {
            String val = teTestDirInput.getText();
            if (ConvTools.isInt(val)) {

                    sldYear.setValue(ConvTools.str2Int(teTestDirInput.getText()));


            }
            else {
                JOptionPane.showMessageDialog(null, "Wprowadź liczbę całkowitą");
                teTestDirInput.setText(CONST.TEST_DIRECT_INPUT_DEF_VALUE);
            }
        }

//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
