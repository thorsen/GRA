
package userinterfaces;

import RA.Descripcion;
import RA.SerieRA2;
import RA.TipoRA;
import general.Auxiliares;
import general.ComboBoxObject;
import general.IVExtendido;
import general.MensajeApp;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DescripcionRAGUI2 extends JDialog {
    private ArrayList<Integer> modoSalida;
  
    public DescripcionRAGUI2(java.awt.Frame parent) {
        super(parent, true);

        initComponents();       
    }
  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpPrincipal = new javax.swing.JPanel();
        jpClave = new javax.swing.JPanel();
        jlAsunto = new javax.swing.JLabel();
        jcbAsunto = new javax.swing.JComboBox();
        jtpDatos = new javax.swing.JTabbedPane();
        jpNoAcus = new javax.swing.JPanel();
        jlNoAcusPosibles = new javax.swing.JLabel();
        jspNoAcusPosibles = new javax.swing.JScrollPane();
        jtNoAcusPosibles = new javax.swing.JTable();
        jbNoAcusAnade = new javax.swing.JButton();
        jbNoAcusQuita = new javax.swing.JButton();
        jlNoAcusInsertar = new javax.swing.JLabel();
        jspNoAcusInsertar = new javax.swing.JScrollPane();
        jtNoAcusInsertar = new javax.swing.JTable();
        jpSPL = new javax.swing.JPanel();
        jlSPLPosibles = new javax.swing.JLabel();
        jspSPLPosibles = new javax.swing.JScrollPane();
        jtSPLPosibles = new javax.swing.JTable();
        jbSPLAnade = new javax.swing.JButton();
        jbSPLQuita = new javax.swing.JButton();
        jLSPLInsertar = new javax.swing.JLabel();
        jspSPLInsertar = new javax.swing.JScrollPane();
        jtSPLInsertar = new javax.swing.JTable();
        jpOCT = new javax.swing.JPanel();
        jlOCTPosibles = new javax.swing.JLabel();
        jspOCTPosibles = new javax.swing.JScrollPane();
        jtOCTPosibles = new javax.swing.JTable();
        jbOCTAnade = new javax.swing.JButton();
        jbOCTQuita = new javax.swing.JButton();
        jlOCTInsertar = new javax.swing.JLabel();
        jspOCTInsertar = new javax.swing.JScrollPane();
        jtOCTInsertar = new javax.swing.JTable();
        jpFFT = new javax.swing.JPanel();
        jpFFTLim = new javax.swing.JPanel();
        jlFrecIni = new javax.swing.JLabel();
        jtfFrecIni = new javax.swing.JTextField();
        jlFrecFin = new javax.swing.JLabel();
        jtfFrecFin = new javax.swing.JTextField();
        jpFrecResolucion = new javax.swing.JPanel();
        jlFrecInf = new javax.swing.JLabel();
        jtfFrecInf = new javax.swing.JTextField();
        jlFrecSup = new javax.swing.JLabel();
        jtfFrecSup = new javax.swing.JTextField();
        jbAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DESCRIPCIÓN");
        setBackground(new java.awt.Color(175, 30, 30));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(255, 0, 0));
        setLocationByPlatform(true);
        setName("Cliente"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jpPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        jpClave.setBackground(new java.awt.Color(255, 255, 255));
        jpClave.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));

        jlAsunto.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlAsunto.setText("Asunto:");

        jcbAsunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioAsunto(evt);
            }
        });

        javax.swing.GroupLayout jpClaveLayout = new javax.swing.GroupLayout(jpClave);
        jpClave.setLayout(jpClaveLayout);
        jpClaveLayout.setHorizontalGroup(
            jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpClaveLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlAsunto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jcbAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpClaveLayout.setVerticalGroup(
            jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpClaveLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlAsunto)
                    .addComponent(jcbAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtpDatos.setPreferredSize(new java.awt.Dimension(75, 28));

        jpNoAcus.setBackground(new java.awt.Color(255, 255, 255));
        jpNoAcus.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlNoAcusPosibles.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlNoAcusPosibles.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlNoAcusPosibles.setText("SERIES POSIBLES");

        jtNoAcusPosibles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descripción"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtNoAcusPosibles.getTableHeader().setReorderingAllowed(false);
        jtNoAcusPosibles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtNoAcusPosiblesMouseClicked(evt);
            }
        });
        jspNoAcusPosibles.setViewportView(jtNoAcusPosibles);

        jbNoAcusAnade.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jbNoAcusAnade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anadirNoAcus(evt);
            }
        });

        jbNoAcusQuita.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jbNoAcusQuita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitarNoAcus(evt);
            }
        });

        jlNoAcusInsertar.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlNoAcusInsertar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlNoAcusInsertar.setText("SERIES A INSERTAR");

        jtNoAcusInsertar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descripción"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtNoAcusInsertar.getTableHeader().setReorderingAllowed(false);
        jtNoAcusInsertar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtNoAcusInsertarMouseClicked(evt);
            }
        });
        jspNoAcusInsertar.setViewportView(jtNoAcusInsertar);

        javax.swing.GroupLayout jpNoAcusLayout = new javax.swing.GroupLayout(jpNoAcus);
        jpNoAcus.setLayout(jpNoAcusLayout);
        jpNoAcusLayout.setHorizontalGroup(
            jpNoAcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpNoAcusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpNoAcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jlNoAcusPosibles, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jspNoAcusPosibles, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpNoAcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbNoAcusAnade, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbNoAcusQuita, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpNoAcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlNoAcusInsertar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jspNoAcusInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jpNoAcusLayout.setVerticalGroup(
            jpNoAcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpNoAcusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpNoAcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpNoAcusLayout.createSequentialGroup()
                        .addComponent(jlNoAcusPosibles)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpNoAcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jspNoAcusPosibles, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                            .addGroup(jpNoAcusLayout.createSequentialGroup()
                                .addComponent(jbNoAcusAnade, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbNoAcusQuita, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jpNoAcusLayout.createSequentialGroup()
                        .addComponent(jlNoAcusInsertar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jspNoAcusInsertar, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jtpDatos.addTab("", jpNoAcus);

        jpSPL.setBackground(new java.awt.Color(255, 255, 255));
        jpSPL.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlSPLPosibles.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlSPLPosibles.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlSPLPosibles.setText("SERIES POSIBLES");

        jtSPLPosibles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descripción"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtSPLPosibles.getTableHeader().setReorderingAllowed(false);
        jtSPLPosibles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtSPLPosiblesMouseClicked(evt);
            }
        });
        jspSPLPosibles.setViewportView(jtSPLPosibles);

        jbSPLAnade.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jbSPLAnade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anadirSPL(evt);
            }
        });

        jbSPLQuita.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jbSPLQuita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitarSPL(evt);
            }
        });

        jLSPLInsertar.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLSPLInsertar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLSPLInsertar.setText("SERIES A INSERTAR");

        jtSPLInsertar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descripción"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtSPLInsertar.getTableHeader().setReorderingAllowed(false);
        jtSPLInsertar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtSPLInsertarMouseClicked(evt);
            }
        });
        jspSPLInsertar.setViewportView(jtSPLInsertar);

        javax.swing.GroupLayout jpSPLLayout = new javax.swing.GroupLayout(jpSPL);
        jpSPL.setLayout(jpSPLLayout);
        jpSPLLayout.setHorizontalGroup(
            jpSPLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSPLLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpSPLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jlSPLPosibles, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jspSPLPosibles, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpSPLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbSPLAnade, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbSPLQuita, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpSPLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLSPLInsertar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jspSPLInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jpSPLLayout.setVerticalGroup(
            jpSPLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSPLLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpSPLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpSPLLayout.createSequentialGroup()
                        .addComponent(jlSPLPosibles)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpSPLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jspSPLPosibles, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                            .addGroup(jpSPLLayout.createSequentialGroup()
                                .addComponent(jbSPLAnade, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbSPLQuita, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jpSPLLayout.createSequentialGroup()
                        .addComponent(jLSPLInsertar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jspSPLInsertar, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jtpDatos.addTab("", jpSPL);

        jpOCT.setBackground(new java.awt.Color(255, 255, 255));
        jpOCT.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlOCTPosibles.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlOCTPosibles.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlOCTPosibles.setText("SERIES POSIBLES");

        jtOCTPosibles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descripción"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtOCTPosibles.getTableHeader().setReorderingAllowed(false);
        jtOCTPosibles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtOCTPosiblesMouseClicked(evt);
            }
        });
        jspOCTPosibles.setViewportView(jtOCTPosibles);

        jbOCTAnade.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jbOCTAnade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anadirOCT(evt);
            }
        });

        jbOCTQuita.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jbOCTQuita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitarOCT(evt);
            }
        });

        jlOCTInsertar.setFont(new java.awt.Font("Tahoma", 1, 12));
        jlOCTInsertar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlOCTInsertar.setText("SERIES A INSERTAR");

        jtOCTInsertar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descripción"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtOCTInsertar.getTableHeader().setReorderingAllowed(false);
        jtOCTInsertar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtOCTInsertarMouseClicked(evt);
            }
        });
        jspOCTInsertar.setViewportView(jtOCTInsertar);

        javax.swing.GroupLayout jpOCTLayout = new javax.swing.GroupLayout(jpOCT);
        jpOCT.setLayout(jpOCTLayout);
        jpOCTLayout.setHorizontalGroup(
            jpOCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpOCTLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpOCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jlOCTPosibles, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jspOCTPosibles, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpOCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbOCTAnade, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbOCTQuita, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpOCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlOCTInsertar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jspOCTInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jpOCTLayout.setVerticalGroup(
            jpOCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpOCTLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpOCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpOCTLayout.createSequentialGroup()
                        .addComponent(jlOCTPosibles)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpOCTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jspOCTPosibles, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                            .addGroup(jpOCTLayout.createSequentialGroup()
                                .addComponent(jbOCTAnade, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbOCTQuita, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jpOCTLayout.createSequentialGroup()
                        .addComponent(jlOCTInsertar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jspOCTInsertar, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jtpDatos.addTab("", jpOCT);

        jpFFT.setBackground(new java.awt.Color(255, 255, 255));
        jpFFT.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jpFFTLim.setBackground(new java.awt.Color(255, 255, 255));

        jlFrecIni.setText("Frecuencia de Inicio:");

        jlFrecFin.setText("Frecuencia de Final:");

        jpFrecResolucion.setBackground(new java.awt.Color(255, 255, 255));
        jpFrecResolucion.setBorder(javax.swing.BorderFactory.createTitledBorder("Resolución de frecuencia"));

        jlFrecInf.setText("Frecuecias hasta 2000 Hz:");

        jlFrecSup.setText("Frecuecias entre 2000 y 5000 Hz:");

        javax.swing.GroupLayout jpFrecResolucionLayout = new javax.swing.GroupLayout(jpFrecResolucion);
        jpFrecResolucion.setLayout(jpFrecResolucionLayout);
        jpFrecResolucionLayout.setHorizontalGroup(
            jpFrecResolucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFrecResolucionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpFrecResolucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlFrecSup)
                    .addComponent(jlFrecInf))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpFrecResolucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtfFrecInf, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                    .addComponent(jtfFrecSup, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpFrecResolucionLayout.setVerticalGroup(
            jpFrecResolucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFrecResolucionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpFrecResolucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlFrecInf)
                    .addComponent(jtfFrecInf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpFrecResolucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlFrecSup)
                    .addComponent(jtfFrecSup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpFFTLimLayout = new javax.swing.GroupLayout(jpFFTLim);
        jpFFTLim.setLayout(jpFFTLimLayout);
        jpFFTLimLayout.setHorizontalGroup(
            jpFFTLimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFFTLimLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpFFTLimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpFrecResolucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpFFTLimLayout.createSequentialGroup()
                        .addGroup(jpFFTLimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlFrecIni)
                            .addComponent(jlFrecFin))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpFFTLimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jtfFrecIni, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                            .addComponent(jtfFrecFin, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpFFTLimLayout.setVerticalGroup(
            jpFFTLimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFFTLimLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpFFTLimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlFrecIni)
                    .addComponent(jtfFrecIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpFFTLimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlFrecFin)
                    .addComponent(jtfFrecFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpFrecResolucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpFFTLayout = new javax.swing.GroupLayout(jpFFT);
        jpFFT.setLayout(jpFFTLayout);
        jpFFTLayout.setHorizontalGroup(
            jpFFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 463, Short.MAX_VALUE)
            .addGroup(jpFFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpFFTLayout.createSequentialGroup()
                    .addGap(0, 83, Short.MAX_VALUE)
                    .addComponent(jpFFTLim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 83, Short.MAX_VALUE)))
        );
        jpFFTLayout.setVerticalGroup(
            jpFFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 407, Short.MAX_VALUE)
            .addGroup(jpFFTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpFFTLayout.createSequentialGroup()
                    .addGap(0, 117, Short.MAX_VALUE)
                    .addComponent(jpFFTLim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 118, Short.MAX_VALUE)))
        );

        jtpDatos.addTab("", jpFFT);

        jbAceptar.setBackground(new java.awt.Color(255, 255, 255));
        jbAceptar.setText("ACEPTAR");
        jbAceptar.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jbAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptar(evt);
            }
        });

        javax.swing.GroupLayout jpPrincipalLayout = new javax.swing.GroupLayout(jpPrincipal);
        jpPrincipal.setLayout(jpPrincipalLayout);
        jpPrincipalLayout.setHorizontalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jpClave, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtpDatos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpPrincipalLayout.setVerticalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtpDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbAceptar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-500)/2, (screenSize.height-584)/2, 500, 584);
    }// </editor-fold>//GEN-END:initComponents

private void limpiarCampos() {
    ((DefaultTableModel)this.jtNoAcusPosibles.getModel()).setRowCount(0);
    ((DefaultTableModel)this.jtNoAcusInsertar.getModel()).setRowCount(0);
    ((DefaultTableModel)this.jtSPLPosibles.getModel()).setRowCount(0);
    ((DefaultTableModel)this.jtSPLInsertar.getModel()).setRowCount(0);
    ((DefaultTableModel)this.jtOCTPosibles.getModel()).setRowCount(0);
    ((DefaultTableModel)this.jtOCTInsertar.getModel()).setRowCount(0);
    this.jtfFrecIni.setText(null);
    this.jtfFrecFin.setText(null);
    this.jtfFrecInf.setText(null);
    this.jtfFrecSup.setText(null);
    
    this.jbNoAcusAnade.setEnabled(false);
    this.jbNoAcusQuita.setEnabled(false);
    this.jbSPLAnade.setEnabled(false);
    this.jbSPLQuita.setEnabled(false);
    this.jbOCTAnade.setEnabled(false);
    this.jbOCTQuita.setEnabled(false);
    
    this.jbAceptar.setEnabled(false);
}

private void cargarPosibles() throws SQLException, NoSuchFieldException {
    ArrayList<String> descCodigosNoAcusPos = SerieRA2.getDescCodigosPorTipo(TipoRA.getTiposRA(null, Auxiliares.TIPO_GEN, null, null, null).get(0).getIdTipoRA());
    ArrayList<String> descCodigosSPLPos = SerieRA2.getDescCodigosPorTipo(TipoRA.getTiposRA(null, Auxiliares.TIPO_SPL, null, null, null).get(0).getIdTipoRA());
    ArrayList<String> descCodigosOCTPos = SerieRA2.getDescCodigosPorTipo(TipoRA.getTiposRA(null, Auxiliares.TIPO_OCT, null, null, null).get(0).getIdTipoRA());

    cargarDescCodigosTabla(descCodigosNoAcusPos, this.jtNoAcusPosibles, null);
    cargarDescCodigosTabla(descCodigosSPLPos, this.jtSPLPosibles, null);
    cargarDescCodigosTabla(descCodigosOCTPos, this.jtOCTPosibles, null);
}

private void ocultarFilaDesc(String descCodigo, DefaultTableModel dtm) {
    int nFilas = dtm.getRowCount();
    
    for (int i = 0; i < nFilas; i++) {
        if (descCodigo.contentEquals((String) dtm.getValueAt(i, 0))) {
            dtm.removeRow(i);
            break;
        }
    }
}

private void anadirCampoOculta(String descCodigo, DefaultTableModel dtmAnadir, DefaultTableModel dtmOcultar) {
    if (descCodigo != null) {
        if (dtmAnadir != null)
            dtmAnadir.addRow(new Object[]{descCodigo});

        if (dtmOcultar != null)
            ocultarFilaDesc(descCodigo, dtmOcultar);
    }
}

private void cargarDescCodigosTabla(ArrayList<String> descCodigos, JTable jtAnadir, JTable jtOcultar) throws SQLException, NoSuchFieldException {
    if (descCodigos != null) {
        int nCodigos = descCodigos.size();
        
        DefaultTableModel dtmAnadir = jtAnadir != null ? (DefaultTableModel) jtAnadir.getModel() : null;
        DefaultTableModel dtmOcultar = jtOcultar != null ? (DefaultTableModel) jtOcultar.getModel() : null;
        
        for (int i = 0; i < nCodigos; i++) {
            anadirCampoOculta(descCodigos.get(i), dtmAnadir, dtmOcultar);
        }
        
        if (jtAnadir != null) 
            Auxiliares.centrarTabla(jtAnadir);
        
        if (jtOcultar != null)
            Auxiliares.centrarTabla(jtOcultar);
    }
}

private void actualizarBotones() {
    this.jbNoAcusAnade.setEnabled(this.jtNoAcusPosibles.getRowCount() > 0);
    this.jbNoAcusQuita.setEnabled(this.jtNoAcusInsertar.getRowCount() > 0);
    this.jbSPLAnade.setEnabled(this.jtSPLPosibles.getRowCount() > 0);
    this.jbSPLQuita.setEnabled(this.jtSPLInsertar.getRowCount() > 0);
    this.jbOCTAnade.setEnabled(this.jtOCTPosibles.getRowCount() > 0);
    this.jbOCTQuita.setEnabled(this.jtOCTInsertar.getRowCount() > 0);
    
    this.jbAceptar.setEnabled(true);
}

private void cambioAsunto(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambioAsunto
    try {
        Auxiliares.bloqueaDialog(this, true);
        
        limpiarCampos();
        cargarPosibles();

        Integer idAsunto = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbAsunto);

        if (idAsunto != null) {
            ArrayList<String> descCodigosNoAcus = SerieRA2.getDescCodigosPorIdAsuntoTipo(idAsunto, TipoRA.ID_TIPO_GEN);
            ArrayList<String> descCodigosSPL = SerieRA2.getDescCodigosPorIdAsuntoTipo(idAsunto, TipoRA.ID_TIPO_SPL);
            ArrayList<String> descCodigosOCT = SerieRA2.getDescCodigosPorIdAsuntoTipo(idAsunto, TipoRA.ID_TIPO_OCT);
            
            //Carga de códigos en las tablas
            cargarDescCodigosTabla(descCodigosNoAcus, this.jtNoAcusInsertar, this.jtNoAcusPosibles);
            cargarDescCodigosTabla(descCodigosSPL, this.jtSPLInsertar, this.jtSPLPosibles);
            cargarDescCodigosTabla(descCodigosOCT, this.jtOCTInsertar, this.jtOCTPosibles);
            
            //Caso especial FFT
            ArrayList<SerieRA2> seriesFFT = SerieRA2.getSeriesRA2PorTipo(TipoRA.ID_TIPO_FFT);
            int nSeriesFFT = seriesFFT != null ? seriesFFT.size() : 0;
            
            SerieRA2 serie;
            Descripcion descFFT;
            for (int i = 0; i < nSeriesFFT; i++) {
                serie = seriesFFT.get(i);
                
                descFFT = Descripcion.getDescripcion(idAsunto, serie.getIdSerie());
                
                if (descFFT != null && descFFT.getValor() != null) {
                    if (serie.getDescripcion().contains("Ini"))
                        this.jtfFrecIni.setText(descFFT.getValor().toString());
                    else if (serie.getDescripcion().contains("Fin"))
                        this.jtfFrecFin.setText(descFFT.getValor().toString());
                    else if (serie.getDescripcion().contains("Inf"))
                        this.jtfFrecInf.setText(descFFT.getValor().toString());
                    else if (serie.getDescripcion().contains("Sup"))
                        this.jtfFrecSup.setText(descFFT.getValor().toString());
                }
            }
            
            actualizarBotones();
        }
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
    } finally {
        Auxiliares.bloqueaDialog(this, false);
    }
}//GEN-LAST:event_cambioAsunto

private void anadirLinkedHashMapNomVar(LinkedHashMap<String, Double> nomVars, JTable jt) {
    int nFilas = jt.getRowCount();
    
    for (int i = 0; i < nFilas; i++) {
        nomVars.put((String) jt.getValueAt(i, 0), null);
    }
}

private void aceptar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptar
    try {
        //Todos los campos deben de ser válidos 
        if (Auxiliares.validaCampos(this) && JOptionPane.showConfirmDialog(this, "Este proceso eliminará los datos almacenados con anterioridad\n¿Desea continuar?", "Aviso", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.OK_OPTION) {
            Auxiliares.bloqueaDialog(this, true);
            Integer idAsunto = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbAsunto);

            LinkedHashMap<String, Double> nomVarNoAcusticas = new LinkedHashMap<String, Double>();
            LinkedHashMap<String, Double> nomVarAcusticas = new LinkedHashMap<String, Double>();

            anadirLinkedHashMapNomVar(nomVarNoAcusticas, this.jtNoAcusInsertar);
            anadirLinkedHashMapNomVar(nomVarAcusticas, this.jtSPLInsertar);
            anadirLinkedHashMapNomVar(nomVarAcusticas, this.jtOCTInsertar);
            
            //Caso especial FFT
            ArrayList<SerieRA2> seriesFFT = SerieRA2.getSeriesRA2PorTipo(TipoRA.ID_TIPO_FFT);
            int nSeriesFFT = seriesFFT != null ? seriesFFT.size() : 0;
            
            SerieRA2 serie;
            Double valor;
            for (int i = 0; i < nSeriesFFT; i++) {
                serie = seriesFFT.get(i);
                valor = null;
                
                if (serie.getDescripcion().contains("Ini") && !this.jtfFrecIni.getText().isEmpty())
                    valor = Double.parseDouble(this.jtfFrecIni.getText());
                else if (serie.getDescripcion().contains("Fin") && !this.jtfFrecFin.getText().isEmpty())
                    valor = Double.parseDouble(this.jtfFrecFin.getText());
                else if (serie.getDescripcion().contains("Inf") && !this.jtfFrecInf.getText().isEmpty())
                    valor = Double.parseDouble(this.jtfFrecInf.getText());
                else if (serie.getDescripcion().contains("Sup") && !this.jtfFrecSup.getText().isEmpty())
                    valor = Double.parseDouble(this.jtfFrecSup.getText());
                
                nomVarAcusticas.put(serie.getDescripcion(), valor);
            }

            Descripcion.insertDescripcionCreateDatos(idAsunto, nomVarNoAcusticas, nomVarAcusticas, null);
            
            MensajeApp.muestraInfo(this, Auxiliares.PROCESS_OK);
        } else
            MensajeApp.muestraInfo(this, Auxiliares.PROCESS_CANCELED);
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
    } catch (IOException e) {
        MensajeApp.muestraError(this, e, "Fallo accediendo a fichero");
    } finally {
        Auxiliares.bloqueaDialog(this, false);
    }
}//GEN-LAST:event_aceptar

private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    try {
        Auxiliares.bloqueaDialog(this, true);
        Auxiliares.cargaAsuntos(this.jcbAsunto);

        //Establecemos iconos de los botones
        this.jbNoAcusQuita.setIcon(Auxiliares.ICONO_PREV);
        this.jbNoAcusAnade.setIcon(Auxiliares.ICONO_NEXT);
        this.jbSPLQuita.setIcon(Auxiliares.ICONO_PREV);
        this.jbSPLAnade.setIcon(Auxiliares.ICONO_NEXT);
        this.jbOCTQuita.setIcon(Auxiliares.ICONO_PREV);
        this.jbOCTAnade.setIcon(Auxiliares.ICONO_NEXT);
        
        //Añadimos los verificadores de los campos de entrada
        this.jtfFrecIni.setInputVerifier(new IVExtendido(this, IVExtendido.TIPO_DOUBLE, true, false));
        this.jtfFrecFin.setInputVerifier(new IVExtendido(this, IVExtendido.TIPO_DOUBLE, true, false));
        this.jtfFrecInf.setInputVerifier(new IVExtendido(this, IVExtendido.TIPO_DOUBLE, true, false));
        this.jtfFrecSup.setInputVerifier(new IVExtendido(this, IVExtendido.TIPO_DOUBLE, true, false));

        //Establecemos las pestañas de JTabbedPane
        Auxiliares.setTitulosJTabbedPane(this.jtpDatos, new String[]{"NO ACÚSTICAS", "SPL", "1/3 OCTAVA", "FFT"});

        //Maximizamos las pestañas
        Auxiliares.maximizaTitulosJTabbedPane(this.jtpDatos);
    } catch (SQLException e) {//GEN-LAST:event_formWindowOpened
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
    } finally {
        Auxiliares.bloqueaDialog(this, false);
    }
}                                 

private void pasaFilaSeleccionada(JTable jtOrigen, JTable jtDestino) {
    int fila = jtOrigen.getSelectedRow();
    
    if (fila != -1) { //Hay alguna fila seleccionada
        ((DefaultTableModel) jtDestino.getModel()).addRow(new Object[]{jtOrigen.getValueAt(fila, 0)});
        ((DefaultTableModel) jtOrigen.getModel()).removeRow(fila);
        
        Auxiliares.centrarTabla(jtOrigen);
        Auxiliares.centrarTabla(jtDestino);

        Auxiliares.muestraFila(jtOrigen, fila - 1);
        Auxiliares.muestraFila(jtDestino, jtDestino.getRowCount() - 1);

        actualizarBotones();
    }
}

private void anadirOCT(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anadirOCT
    pasaFilaSeleccionada(this.jtOCTPosibles, this.jtOCTInsertar);
}//GEN-LAST:event_anadirOCT

private void quitarOCT(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitarOCT
    pasaFilaSeleccionada(this.jtOCTInsertar, this.jtOCTPosibles);
}//GEN-LAST:event_quitarOCT

private void anadirSPL(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anadirSPL
    pasaFilaSeleccionada(this.jtSPLPosibles, this.jtSPLInsertar);
}//GEN-LAST:event_anadirSPL

private void quitarSPL(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitarSPL
    pasaFilaSeleccionada(this.jtSPLInsertar, this.jtSPLPosibles);
}//GEN-LAST:event_quitarSPL

private void anadirNoAcus(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anadirNoAcus
    pasaFilaSeleccionada(this.jtNoAcusPosibles, this.jtNoAcusInsertar);
}//GEN-LAST:event_anadirNoAcus

private void quitarNoAcus(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitarNoAcus
    pasaFilaSeleccionada(this.jtNoAcusInsertar, this.jtNoAcusPosibles);
}//GEN-LAST:event_quitarNoAcus

private void dobleClickTabla(java.awt.event.MouseEvent evt, JTable jtOrigen, JTable jtDestino) {
    if (evt.getClickCount() == 2 && !evt.isConsumed()) {
        evt.consume();
        
        Integer idAsunto = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbAsunto);
        
        if (idAsunto != null)
            pasaFilaSeleccionada(jtOrigen, jtDestino);
    }
}

private void jtNoAcusPosiblesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtNoAcusPosiblesMouseClicked
    dobleClickTabla(evt, this.jtNoAcusPosibles, this.jtNoAcusInsertar);
}//GEN-LAST:event_jtNoAcusPosiblesMouseClicked

private void jtNoAcusInsertarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtNoAcusInsertarMouseClicked
    dobleClickTabla(evt, this.jtNoAcusInsertar, this.jtNoAcusPosibles);
}//GEN-LAST:event_jtNoAcusInsertarMouseClicked

private void jtSPLInsertarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtSPLInsertarMouseClicked
    dobleClickTabla(evt, this.jtSPLInsertar, this.jtSPLPosibles);
}//GEN-LAST:event_jtSPLInsertarMouseClicked

private void jtOCTPosiblesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtOCTPosiblesMouseClicked
    dobleClickTabla(evt, this.jtOCTPosibles, this.jtOCTInsertar);
}//GEN-LAST:event_jtOCTPosiblesMouseClicked

private void jtOCTInsertarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtOCTInsertarMouseClicked
    dobleClickTabla(evt, this.jtOCTInsertar, this.jtOCTPosibles);
}//GEN-LAST:event_jtOCTInsertarMouseClicked

private void jtSPLPosiblesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtSPLPosiblesMouseClicked
    dobleClickTabla(evt, this.jtSPLPosibles, this.jtSPLInsertar);
}//GEN-LAST:event_jtSPLPosiblesMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLSPLInsertar;
    private javax.swing.JButton jbAceptar;
    private javax.swing.JButton jbNoAcusAnade;
    private javax.swing.JButton jbNoAcusQuita;
    private javax.swing.JButton jbOCTAnade;
    private javax.swing.JButton jbOCTQuita;
    private javax.swing.JButton jbSPLAnade;
    private javax.swing.JButton jbSPLQuita;
    private javax.swing.JComboBox jcbAsunto;
    private javax.swing.JLabel jlAsunto;
    private javax.swing.JLabel jlFrecFin;
    private javax.swing.JLabel jlFrecInf;
    private javax.swing.JLabel jlFrecIni;
    private javax.swing.JLabel jlFrecSup;
    private javax.swing.JLabel jlNoAcusInsertar;
    private javax.swing.JLabel jlNoAcusPosibles;
    private javax.swing.JLabel jlOCTInsertar;
    private javax.swing.JLabel jlOCTPosibles;
    private javax.swing.JLabel jlSPLPosibles;
    private javax.swing.JPanel jpClave;
    private javax.swing.JPanel jpFFT;
    private javax.swing.JPanel jpFFTLim;
    private javax.swing.JPanel jpFrecResolucion;
    private javax.swing.JPanel jpNoAcus;
    private javax.swing.JPanel jpOCT;
    private javax.swing.JPanel jpPrincipal;
    private javax.swing.JPanel jpSPL;
    private javax.swing.JScrollPane jspNoAcusInsertar;
    private javax.swing.JScrollPane jspNoAcusPosibles;
    private javax.swing.JScrollPane jspOCTInsertar;
    private javax.swing.JScrollPane jspOCTPosibles;
    private javax.swing.JScrollPane jspSPLInsertar;
    private javax.swing.JScrollPane jspSPLPosibles;
    private javax.swing.JTable jtNoAcusInsertar;
    private javax.swing.JTable jtNoAcusPosibles;
    private javax.swing.JTable jtOCTInsertar;
    private javax.swing.JTable jtOCTPosibles;
    private javax.swing.JTable jtSPLInsertar;
    private javax.swing.JTable jtSPLPosibles;
    private javax.swing.JTextField jtfFrecFin;
    private javax.swing.JTextField jtfFrecInf;
    private javax.swing.JTextField jtfFrecIni;
    private javax.swing.JTextField jtfFrecSup;
    private javax.swing.JTabbedPane jtpDatos;
    // End of variables declaration//GEN-END:variables
}
