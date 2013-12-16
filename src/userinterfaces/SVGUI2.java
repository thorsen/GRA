package userinterfaces;

import RA.Login;
import RA.Parque;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class SVGUI2 extends JDialog {
       
    public SVGUI2(SVGUI1 D,int p) {
        super(D, true);
        this.D0=D;
        this.panel=p;
        initComponents();    
        this.jTextField1.setText(this.D0.jComboBox1.getSelectedItem().toString());     
    } 
  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton6 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        jButton6.setText("OK");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SECTOR VALIDO");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(255, 0, 0));
        setIconImage(new ImageIcon("\\\\B2Solar\\Datos\\Curva\\Imagenes\\GRA.png").getImage());
        setLocationByPlatform(true);
        setName("Cliente"); // NOI18N
        setResizable(false);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("PARQUE");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel2.setText("Sector válido para cada posición de aerogenerador :  ");

        jTextField1.setBackground(new java.awt.Color(204, 204, 204));
        jTextField1.setEditable(false);
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel3.setForeground(new java.awt.Color(255, 51, 0));
        jLabel3.setText("NO CONSIDERA LA TORRE !  ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                        .addGap(26, 26, 26))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(222, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setText(">>");
        jButton3.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Siguiente(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("\\\\B2Solar\\Datos\\Curva\\Imagenes\\resultset_previous.png" )));
        jButton1.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Atras(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("\\\\B2Solar\\Datos\\Curva\\Imagenes\\resultset_next.png" )));
        jButton2.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Next(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("\\\\B2Solar\\Datos\\Curva\\Imagenes\\camera.png" )));
        jButton7.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Guardar(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        if ((panel*9+7)<this.D0.matrizaeros.size()){
            this.jPanel21=createPanel(this.D0.objetos.get(panel*9+7)[0],this.D0.matrizaeros.get(panel*9+7).get(1));
        }
        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel21.setPreferredSize(new Dimension(100,150));
        jPanel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Ampliar8(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Mano8(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                QMano8(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 209, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 137, Short.MAX_VALUE)
        );

        if ((panel*9+5)<this.D0.matrizaeros.size()){
            this.jPanel19=createPanel(this.D0.objetos.get(panel*9+5)[0],this.D0.matrizaeros.get(panel*9+5).get(1));
        }
        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel19.setPreferredSize(new Dimension(100,150));
        jPanel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Ampliar6(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Mano6(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                QMano6(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 209, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 137, Short.MAX_VALUE)
        );

        if ((panel*9+2)<this.D0.matrizaeros.size()){
            this.jPanel16=createPanel(this.D0.objetos.get(panel*9+2)[0],this.D0.matrizaeros.get(panel*9+2).get(1));
        }
        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel16.setPreferredSize(new Dimension(100,150));
        jPanel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Ampliar3(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Mano3(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                QMano3(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 209, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 137, Short.MAX_VALUE)
        );

        if ((panel*9+3)<this.D0.matrizaeros.size()){
            this.jPanel17=createPanel(this.D0.objetos.get(panel*9+3)[0],this.D0.matrizaeros.get(panel*9+3).get(1));
        }
        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel17.setPreferredSize(new Dimension(100,150));
        jPanel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Ampliar4(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Mano4(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                QMano4(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 209, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 137, Short.MAX_VALUE)
        );

        if ((panel*9+8)<this.D0.matrizaeros.size()){    
            this.jPanel22=createPanel(this.D0.objetos.get(panel*9+8)[0],this.D0.matrizaeros.get(panel*9+8).get(1));
        }
        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel22.setPreferredSize(new Dimension(100,150));
        jPanel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Ampliar9(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Mano9(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                QMano9(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 209, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 137, Short.MAX_VALUE)
        );

        if ((panel*9+6)<this.D0.matrizaeros.size()){
            this.jPanel20=createPanel(this.D0.objetos.get(panel*9+6)[0],this.D0.matrizaeros.get(panel*9+6).get(1));
        }
        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel20.setPreferredSize(new Dimension(100,150));
        jPanel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Ampliar7(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Mano7(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                QMano7(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 209, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 137, Short.MAX_VALUE)
        );

        if ((panel*9+4)<this.D0.matrizaeros.size()){
            this.jPanel18=createPanel(this.D0.objetos.get(panel*9+4)[0],this.D0.matrizaeros.get(panel*9+4).get(1));
        }
        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel18.setPreferredSize(new Dimension(100,150));
        jPanel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Ampliar5(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Mano5(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                QMano5(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 209, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 137, Short.MAX_VALUE)
        );

        if ((panel*9+1)<this.D0.matrizaeros.size()){
            this.jPanel15=createPanel(this.D0.objetos.get(panel*9+1)[0],this.D0.matrizaeros.get(panel*9+1).get(1));
        }
        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel15.setPreferredSize(new Dimension(100,150));
        jPanel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Ampliar2(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Mano2(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                QMano2(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 209, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 137, Short.MAX_VALUE)
        );

        if ((panel*9)<this.D0.matrizaeros.size()){
            this.jPanel14=createPanel(this.D0.objetos.get(panel*9)[0],this.D0.matrizaeros.get(panel*9).get(1));
        }

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel14.setPreferredSize(new Dimension(100,150));
        jPanel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Ampliar1(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Mano1(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                QMano1(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 209, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 137, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButton8.setBackground(new java.awt.Color(255, 255, 255));
        jButton8.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("\\\\B2Solar\\Datos\\Curva\\Imagenes\\resultset_last.png" )));
        jButton8.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Ultima(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(255, 255, 255));
        jButton9.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("\\\\B2Solar\\Datos\\Curva\\Imagenes\\resultset_first.png" )));
        jButton9.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Primera(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(255, 255, 255));
        jButton10.setText("TORRES");
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VerTorres(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(175, 175, 175)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(jButton10)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                .addContainerGap())
        );

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setText("<<");
        jButton4.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Anterior(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setText("CANCELAR");
        jButton5.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Salir(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-723)/2, (screenSize.height-663)/2, 723, 663);
    }// </editor-fold>//GEN-END:initComponents

private void Atras(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Atras
    panel=panel-1;
    this.dispose();
    new SVGUI2((SVGUI1)this.getParent(),panel).setVisible(true);      
}//GEN-LAST:event_Atras

private void Next(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Next
    panel=panel+1;
    this.dispose();
    new SVGUI2((SVGUI1)this.getParent(),panel).setVisible(true);      
}//GEN-LAST:event_Next

private void Siguiente(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Siguiente
   this.setVisible(false);
   new SVGUI3(this).setVisible(true);        
}//GEN-LAST:event_Siguiente

private void Anterior(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Anterior
    dispose();
    this.D0.setVisible(true);
}//GEN-LAST:event_Anterior

private void Salir(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Salir
    dispose();
}//GEN-LAST:event_Salir

private void Guardar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Guardar
try{
    Parque PE=new Parque();
    String nombre_parque=PE.getNombre(this.D0.parque);
    String u=Login.getUsuario();
    JFileChooser fileChooser = new JFileChooser("Z:\\"+u+"\\PRIVADA\\CALIDAD\\ASUNTOS"); 
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
    int seleccion =fileChooser.showSaveDialog(this);
    String ruta="";
    if (seleccion == JFileChooser.APPROVE_OPTION){
        File fichero = fileChooser.getSelectedFile();
        ruta=fichero.getPath()+"\\"+nombre_parque+"-EstudiosPrevios.png";
    }
    File f=new File(ruta);
    JComponent C=this.jPanel1;
    int width=(int)C.getSize().getWidth();
    int height=(int)C.getSize().getHeight();
    BufferedImage image = new BufferedImage(width,height, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = image.createGraphics();
    C.paint(g2d);
    g2d.dispose();
    RenderedImage rendImage=image;
    ImageIO.write(rendImage, "png", f);
} catch (SQLException e){
   e.printStackTrace();
   JOptionPane.showMessageDialog(this, "Fallo al consultar la base de datos", "ERROR", JOptionPane.ERROR_MESSAGE);         
} catch (Exception e){
   e.printStackTrace();
   JOptionPane.showMessageDialog(this, "Fallo al guardar imagen", "ERROR", JOptionPane.ERROR_MESSAGE);         
}
}//GEN-LAST:event_Guardar

private void Ultima(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ultima
    if (this.D0.objetos.size()%9==0){
        panel=this.D0.objetos.size()/9;
    }else{
        panel=(this.D0.objetos.size()/9)+1;
    }  
    new SVGUI2((SVGUI1)this.getParent(),panel).setVisible(true);
    this.dispose();  
}//GEN-LAST:event_Ultima

private void Primera(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Primera
    panel=0;
    new SVGUI2((SVGUI1)this.getParent(),panel).setVisible(true);
    this.dispose();  
}//GEN-LAST:event_Primera

private void Ampliar1(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ampliar1
    new SVGUIAG(this,panel,0).setVisible(true);
}//GEN-LAST:event_Ampliar1

private void QMano1(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QMano1
    Component root = SwingUtilities.getRoot(this);  
    root.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );
}//GEN-LAST:event_QMano1

private void Mano1(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Mano1
    Component root = SwingUtilities.getRoot(this);  
    root.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
}//GEN-LAST:event_Mano1

private void Ampliar2(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ampliar2
    new SVGUIAG(this,panel,1).setVisible(true);
}//GEN-LAST:event_Ampliar2

private void Mano2(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Mano2
    Component root = SwingUtilities.getRoot(this);  
    root.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
}//GEN-LAST:event_Mano2

private void QMano2(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QMano2
    Component root = SwingUtilities.getRoot(this);  
    root.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );
}//GEN-LAST:event_QMano2

private void Ampliar3(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ampliar3
    new SVGUIAG(this,panel,2).setVisible(true);
}//GEN-LAST:event_Ampliar3

private void Mano3(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Mano3
    Component root = SwingUtilities.getRoot(this);  
    root.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
}//GEN-LAST:event_Mano3

private void QMano3(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QMano3
    Component root = SwingUtilities.getRoot(this);  
    root.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );
}//GEN-LAST:event_QMano3

private void Ampliar4(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ampliar4
    new SVGUIAG(this,panel,3).setVisible(true);
}//GEN-LAST:event_Ampliar4

private void Mano4(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Mano4
    Component root = SwingUtilities.getRoot(this);  
    root.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
}//GEN-LAST:event_Mano4

private void QMano4(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QMano4
    Component root = SwingUtilities.getRoot(this);  
    root.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );
}//GEN-LAST:event_QMano4

private void Ampliar5(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ampliar5
    new SVGUIAG(this,panel,4).setVisible(true);
}//GEN-LAST:event_Ampliar5

private void Mano5(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Mano5
    Component root = SwingUtilities.getRoot(this);  
    root.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
}//GEN-LAST:event_Mano5

private void QMano5(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QMano5
    Component root = SwingUtilities.getRoot(this);  
    root.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );
}//GEN-LAST:event_QMano5

private void Ampliar6(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ampliar6
    new SVGUIAG(this,panel,5).setVisible(true);
}//GEN-LAST:event_Ampliar6

private void Mano6(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Mano6
    Component root = SwingUtilities.getRoot(this);  
    root.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
}//GEN-LAST:event_Mano6

private void QMano6(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QMano6
    Component root = SwingUtilities.getRoot(this);  
    root.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );
}//GEN-LAST:event_QMano6

private void Ampliar7(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ampliar7
    new SVGUIAG(this,panel,6).setVisible(true);
}//GEN-LAST:event_Ampliar7

private void Mano7(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Mano7
    Component root = SwingUtilities.getRoot(this);  
    root.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
}//GEN-LAST:event_Mano7

private void QMano7(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QMano7
    Component root = SwingUtilities.getRoot(this);  
    root.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );
}//GEN-LAST:event_QMano7

private void Ampliar8(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ampliar8
    new SVGUIAG(this,panel,7).setVisible(true);
}//GEN-LAST:event_Ampliar8

private void Mano8(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Mano8
    Component root = SwingUtilities.getRoot(this);  
    root.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
}//GEN-LAST:event_Mano8

private void QMano8(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QMano8
    Component root = SwingUtilities.getRoot(this);  
    root.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );
}//GEN-LAST:event_QMano8

private void Ampliar9(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Ampliar9
    new SVGUIAG(this,panel,8).setVisible(true);
}//GEN-LAST:event_Ampliar9

private void Mano9(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Mano9
    Component root = SwingUtilities.getRoot(this);  
    root.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
}//GEN-LAST:event_Mano9

private void QMano9(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QMano9
    Component root = SwingUtilities.getRoot(this);  
    root.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );
}//GEN-LAST:event_QMano9

private void VerTorres(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VerTorres
    new SVGUITR(this).setVisible(true);
}//GEN-LAST:event_VerTorres
  
 // Crear el Panel
 public JPanel createPanel(String objeto,ArrayList<String[]> limites) {
     JFreeChart chart = createChart(objeto,limites);
     ChartPanel chartPanel = new ChartPanel(chart);
     chartPanel.setPreferredSize(new java.awt.Dimension(100,150));
     return chartPanel;
 }
 
 private JFreeChart createChart(String objeto,ArrayList<String[]> limites) {
        CategoryDataset dataset=createDataset(limites);
        SpiderWebPlot plot = new SpiderWebPlot(dataset);
        plot.setInteriorGap(0.1);
       
        plot.setOutlineVisible(false);
        plot.setHeadPercent(0.0005);
        plot.setSeriesOutlinePaint(Color.BLUE);
        plot.setSeriesPaint(Color.BLUE);

        plot.setToolTipGenerator(new StandardCategoryToolTipGenerator());
        plot.setAxisLinePaint(Color.WHITE);
        plot.setLabelPaint(Color.WHITE);
       
       JFreeChart chart = new JFreeChart(objeto, new Font("Arial", Font.BOLD, 16), plot, false);
       chart.setBackgroundPaint(Color.WHITE);
       return chart;
 }

 private CategoryDataset createDataset(ArrayList<String[]> limites) {
        // Creo una categoria para cada sector de 0 a 360
        String[] category = new String[360];
        for (int i=0;i<360;i++){
            category[i]="C"+i;
        }
        // Me creo el vector de validos de cada grado
        int[] valido=Valido(limites);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i=0;i<360;i++){
            dataset.addValue(valido[i], "", category[i]);
        }
        return dataset;
 }

public int[] Valido(ArrayList<String[]> limites){
    int[] valido=new int[360];
    for (int grado=0;grado<360;grado++){
        valido[grado]=1;
        for (int j=0;j<limites.size();j++){
            double linf=Double.parseDouble(limites.get(j)[0]);
            double lsup=Double.parseDouble(limites.get(j)[1]);
            if (linf<lsup){
                if ((grado>=linf) && (grado<=lsup)){
                    valido[grado]=0;
                }
            } else{ // paso por cero
                if (((grado>=linf) && (grado<=360)) || ((grado>=0) && (grado<=lsup))){
                    valido[grado]=0;
                }
            }
        }
    }
    return valido;
}
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    public javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
    public SVGUI1 D0;
    private int panel;    
}   
