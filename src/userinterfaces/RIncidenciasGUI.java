package userinterfaces;

import RA.AsuntoRA;
import general.Auxiliares;
import RA.Incidencia;
import RA.TipoRA;
import general.ComboBoxObject;
import general.JSpinnerDate;
import general.MensajeApp;
import general.TratFechas;
import java.awt.Frame;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RIncidenciasGUI extends JDialog {

    public RIncidenciasGUI(Frame parent) {
        super(parent, true);

        initComponents();
}
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgAbsRel = new javax.swing.ButtonGroup();
        bgRel = new javax.swing.ButtonGroup();
        jpPrincipal = new javax.swing.JPanel();
        jpClave = new javax.swing.JPanel();
        jlAsunto = new javax.swing.JLabel();
        jcbAsunto = new javax.swing.JComboBox();
        jlSite = new javax.swing.JLabel();
        jcbSite = new javax.swing.JComboBox();
        jbContinuar = new javax.swing.JButton();
        jrbLimAbs = new javax.swing.JRadioButton();
        jpLimAbs = new javax.swing.JPanel();
        jlFechaD = new javax.swing.JLabel();
        jsFechaD = new general.JSpinnerDate();
        jlFechaH = new javax.swing.JLabel();
        jsFechaH = new general.JSpinnerDate();
        jrbLimRel = new javax.swing.JRadioButton();
        jpLimRel = new javax.swing.JPanel();
        jlIniMedida = new javax.swing.JLabel();
        jsFechaIniMedida = new general.JSpinnerDate();
        jrbRelIniFin = new javax.swing.JRadioButton();
        jpRelIniFin = new javax.swing.JPanel();
        jlFechaRelIni = new javax.swing.JLabel();
        jsFechaRelIni = new general.JSpinnerDate(JSpinnerDate.MS_SIMPLE);
        jlFechaRelFin = new javax.swing.JLabel();
        jsFechaRelFin = new general.JSpinnerDate(JSpinnerDate.MS_SIMPLE);
        jrbRelPerc = new javax.swing.JRadioButton();
        jpRelPerc = new javax.swing.JPanel();
        jlFechaRelPerc = new javax.swing.JLabel();
        jsFechaRelPerc = new general.JSpinnerDate(JSpinnerDate.MS_SIMPLE);
        jlFechaRelDur = new javax.swing.JLabel();
        jsFechaRelDur = new general.JSpinnerDate(JSpinnerDate.MS_SIMPLE);
        jcbAplicar = new javax.swing.JCheckBox();
        jpDatos = new javax.swing.JPanel();
        jlObs = new javax.swing.JLabel();
        jspObs = new javax.swing.JScrollPane();
        jtaObs = new javax.swing.JTextArea();
        jbAceptar = new javax.swing.JButton();
        jbCancelar = new javax.swing.JButton();
        jbEliminar = new javax.swing.JButton();
        jpRegistros = new javax.swing.JPanel();
        jlTitRegistros = new javax.swing.JLabel();
        jspRegistros = new javax.swing.JScrollPane();
        jtRegistros = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("INCIDENCIAS");
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
        jpClave.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlAsunto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlAsunto.setText("  Asunto: ");

        jcbAsunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioAsunto(evt);
            }
        });

        jlSite.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlSite.setText("  Site: ");

        jcbSite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioSite(evt);
            }
        });

        jbContinuar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/next.jpg"))); // NOI18N
        jbContinuar.setMaximumSize(new java.awt.Dimension(70, 70));
        jbContinuar.setMinimumSize(new java.awt.Dimension(70, 70));
        jbContinuar.setPreferredSize(new java.awt.Dimension(70, 70));
        jbContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continuar(evt);
            }
        });

        jrbLimAbs.setBackground(new java.awt.Color(255, 255, 255));
        bgAbsRel.add(jrbLimAbs);
        jrbLimAbs.setSelected(true);
        jrbLimAbs.setText("Límites absolutos");
        jrbLimAbs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioTipoLim(evt);
            }
        });

        jpLimAbs.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlFechaD.setText("Inicio");

        jlFechaH.setText("Fin");

        javax.swing.GroupLayout jpLimAbsLayout = new javax.swing.GroupLayout(jpLimAbs);
        jpLimAbs.setLayout(jpLimAbsLayout);
        jpLimAbsLayout.setHorizontalGroup(
            jpLimAbsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLimAbsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpLimAbsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlFechaD)
                    .addComponent(jsFechaD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpLimAbsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jsFechaH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlFechaH))
                .addContainerGap(131, Short.MAX_VALUE))
        );
        jpLimAbsLayout.setVerticalGroup(
            jpLimAbsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLimAbsLayout.createSequentialGroup()
                .addGroup(jpLimAbsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlFechaD)
                    .addComponent(jlFechaH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpLimAbsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jsFechaD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jsFechaH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jrbLimRel.setBackground(new java.awt.Color(255, 255, 255));
        bgAbsRel.add(jrbLimRel);
        jrbLimRel.setText("Límites relativos");
        jrbLimRel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioTipoLim(evt);
            }
        });

        jpLimRel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlIniMedida.setText("Inicio de la medida");

        bgRel.add(jrbRelIniFin);
        jrbRelIniFin.setSelected(true);
        jrbRelIniFin.setText("Inicio - Fin");
        jrbRelIniFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioTipoLimRel(evt);
            }
        });

        jpRelIniFin.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlFechaRelIni.setText("Inicio:");

        jlFechaRelFin.setText("Fin:");

        javax.swing.GroupLayout jpRelIniFinLayout = new javax.swing.GroupLayout(jpRelIniFin);
        jpRelIniFin.setLayout(jpRelIniFinLayout);
        jpRelIniFinLayout.setHorizontalGroup(
            jpRelIniFinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRelIniFinLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlFechaRelIni)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jsFechaRelIni, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlFechaRelFin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jsFechaRelFin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpRelIniFinLayout.setVerticalGroup(
            jpRelIniFinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpRelIniFinLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpRelIniFinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlFechaRelIni)
                    .addComponent(jlFechaRelFin)
                    .addComponent(jsFechaRelIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jsFechaRelFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bgRel.add(jrbRelPerc);
        jrbRelPerc.setText("Percepción - Duración");
        jrbRelPerc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioTipoLimRel(evt);
            }
        });

        jpRelPerc.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlFechaRelPerc.setText("Percepción:");

        jlFechaRelDur.setText("Duración");
        jlFechaRelDur.setToolTipText("<html>Se extenderá desde <b><i>Percepcion</i> - <i>Duración</i> / 2</b> hasta <b><i>Percepcion</i> + <i>Duración</i> / 2</b></html>");

        javax.swing.GroupLayout jpRelPercLayout = new javax.swing.GroupLayout(jpRelPerc);
        jpRelPerc.setLayout(jpRelPercLayout);
        jpRelPercLayout.setHorizontalGroup(
            jpRelPercLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRelPercLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlFechaRelPerc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jsFechaRelPerc, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlFechaRelDur)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jsFechaRelDur, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpRelPercLayout.setVerticalGroup(
            jpRelPercLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpRelPercLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpRelPercLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlFechaRelPerc)
                    .addComponent(jlFechaRelDur)
                    .addComponent(jsFechaRelPerc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jsFechaRelDur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpLimRelLayout = new javax.swing.GroupLayout(jpLimRel);
        jpLimRel.setLayout(jpLimRelLayout);
        jpLimRelLayout.setHorizontalGroup(
            jpLimRelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLimRelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpLimRelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlIniMedida)
                    .addGroup(jpLimRelLayout.createSequentialGroup()
                        .addComponent(jsFechaIniMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jpLimRelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jrbRelIniFin)
                            .addComponent(jrbRelPerc)
                            .addGroup(jpLimRelLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jpLimRelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jpRelIniFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jpRelPerc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpLimRelLayout.setVerticalGroup(
            jpLimRelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLimRelLayout.createSequentialGroup()
                .addComponent(jlIniMedida)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpLimRelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jsFechaIniMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jrbRelIniFin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpRelIniFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jrbRelPerc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpRelPerc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jcbAplicar.setBackground(new java.awt.Color(255, 255, 255));
        jcbAplicar.setText("Aplicar también a OCT");

        javax.swing.GroupLayout jpClaveLayout = new javax.swing.GroupLayout(jpClave);
        jpClave.setLayout(jpClaveLayout);
        jpClaveLayout.setHorizontalGroup(
            jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpClaveLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jrbLimRel)
                        .addGroup(jpClaveLayout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addComponent(jpLimRel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpClaveLayout.createSequentialGroup()
                            .addComponent(jlAsunto)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jcbAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jpLimAbs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpClaveLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jrbLimAbs)
                                .addGap(373, 373, 373)))))
                .addGap(18, 18, 18)
                .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpClaveLayout.createSequentialGroup()
                            .addComponent(jlSite)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jcbSite, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jbContinuar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jcbAplicar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpClaveLayout.setVerticalGroup(
            jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpClaveLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlAsunto)
                    .addComponent(jcbAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbSite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlSite))
                .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpClaveLayout.createSequentialGroup()
                        .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jrbLimAbs)
                            .addComponent(jcbAplicar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpLimAbs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jrbLimRel)
                        .addGap(3, 3, 3)
                        .addComponent(jpLimRel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpClaveLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jpDatos.setBackground(new java.awt.Color(255, 255, 255));
        jpDatos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpDatos.setEnabled(false);

        jlObs.setText("Observación:");

        jtaObs.setColumns(20);
        jtaObs.setRows(5);
        jspObs.setViewportView(jtaObs);

        jbAceptar.setBackground(new java.awt.Color(255, 255, 255));
        jbAceptar.setText("ACEPTAR");
        jbAceptar.setAlignmentX(0.5F);
        jbAceptar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbAceptar.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jbAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptar(evt);
            }
        });

        jbCancelar.setBackground(new java.awt.Color(255, 255, 255));
        jbCancelar.setText("CANCELAR");
        jbCancelar.setAlignmentX(0.5F);
        jbCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbCancelar.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelar(evt);
            }
        });

        jbEliminar.setBackground(new java.awt.Color(255, 255, 255));
        jbEliminar.setText("ELIMINAR");
        jbEliminar.setAlignmentX(0.5F);
        jbEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbEliminar.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jbEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminar(evt);
            }
        });

        javax.swing.GroupLayout jpDatosLayout = new javax.swing.GroupLayout(jpDatos);
        jpDatos.setLayout(jpDatosLayout);
        jpDatosLayout.setHorizontalGroup(
            jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlObs)
                    .addGroup(jpDatosLayout.createSequentialGroup()
                        .addComponent(jspObs, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jbCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jbAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpDatosLayout.setVerticalGroup(
            jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpDatosLayout.createSequentialGroup()
                        .addComponent(jbAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpDatosLayout.createSequentialGroup()
                        .addComponent(jlObs)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jspObs, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpRegistros.setBackground(new java.awt.Color(255, 255, 255));
        jpRegistros.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlTitRegistros.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlTitRegistros.setText("INCIDENCIAS ALMACENADAS");

        jtRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtRegistros.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jtRegistros.setRowSelectionAllowed(false);
        jtRegistros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtRegistrosMouseClicked(evt);
            }
        });
        jspRegistros.setViewportView(jtRegistros);
        jtRegistros.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout jpRegistrosLayout = new javax.swing.GroupLayout(jpRegistros);
        jpRegistros.setLayout(jpRegistrosLayout);
        jpRegistrosLayout.setHorizontalGroup(
            jpRegistrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRegistrosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlTitRegistros)
                .addGap(720, 720, 720))
            .addGroup(jpRegistrosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jspRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpRegistrosLayout.setVerticalGroup(
            jpRegistrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRegistrosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlTitRegistros)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspRegistros, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jpPrincipalLayout = new javax.swing.GroupLayout(jpPrincipal);
        jpPrincipal.setLayout(jpPrincipalLayout);
        jpPrincipalLayout.setHorizontalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpClave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPrincipalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jpRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 694, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpPrincipalLayout.setVerticalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(730, 770));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void jtRegistrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtRegistrosMouseClicked
    if (evt.getClickCount() == 2 && !evt.isConsumed()) {
        evt.consume();
        
        DefaultTableModel dtmRegistros = (DefaultTableModel) this.jtRegistros.getModel();

        int fila = jtRegistros.getSelectedRow();
        Integer idAsunto = new Integer(dtmRegistros.getValueAt(fila, 0).toString());
        Integer idSite = new Integer(dtmRegistros.getValueAt(fila, 1).toString());
        String fechaDesde = dtmRegistros.getValueAt(fila, 2).toString();
        String fechaHasta = dtmRegistros.getValueAt(fila, 3).toString();
        String observacion = dtmRegistros.getValueAt(fila, 4).toString();

        seleccionaAsunto(idAsunto);
        seleccionaSite(idSite);
        this.jsFechaD.setText(fechaDesde.toString());
        this.jsFechaH.setText(fechaHasta.toString());
        this.jtaObs.setText(observacion);
        this.jtaObs.setCaretPosition(0);

        habilitaDatos();
    }
}//GEN-LAST:event_jtRegistrosMouseClicked

private void cambioAsunto(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambioAsunto
    try {
        // Calculo el numero de sites para ese asunto y relleno el combobox con ellos
        Integer idAsunto = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbAsunto);
        
        Auxiliares.cargaSites(idAsunto, this.jcbSite);

		rellenarTabla();
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
    }
}//GEN-LAST:event_cambioAsunto

private void cambioSite(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambioSite
	Integer idSite = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbSite);

	try {
		if (idSite != null && idSite > 0) {
			TipoRA tipoRA = TipoRA.getTipoRAPorIdSite(idSite);

			if (tipoRA.getSufijo().contentEquals(Auxiliares.TIPO_SPL)) {
				this.jcbAplicar.setVisible(true);
				this.jcbAplicar.setSelected(true);
			} else {
				this.jcbAplicar.setVisible(false);
				this.jcbAplicar.setSelected(false);
			}
		} else {
			this.jcbAplicar.setVisible(false);
			this.jcbAplicar.setSelected(false);
		}
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
	}
	
    rellenarTabla();
}//GEN-LAST:event_cambioSite

private void aceptar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptar
    try {
        int numFilas;

        if (jtaObs.getText().trim().length() == 0) {
            if (JOptionPane.showConfirmDialog(this, "La observación no contiene texto. ¿Desea continuar?", "Aviso",
                    JOptionPane.WARNING_MESSAGE) == JOptionPane.CANCEL_OPTION) {
                jtaObs.requestFocus();
                return;
            }
        }

        inciNueva = getIncidenciaActual(false);

        numFilas = Incidencia.insertOrUpdateIncidencia(inciVieja, inciNueva, null);

		if (this.jcbAplicar.isSelected()) {
			Incidencia inciViejaAux = getIncidenciaAnterior(true);
			Incidencia inciNuevaAux = getIncidenciaActual(true);

			numFilas += Incidencia.insertOrUpdateIncidencia(inciViejaAux, inciNuevaAux, null);
		}

        if (numFilas > 0) {
            MensajeApp.muestraInfo(this, numFilas + " fila(s) se ha(n) modificado");

            habilitaClave(false);
        } else {
            MensajeApp.muestraWarning(this, "Ninguna fila modificada");

            jtaObs.requestFocus();
        }
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al insertar en la base de datos");
    }
}//GEN-LAST:event_aceptar

private void cancelar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelar
    habilitaClave(false);
}//GEN-LAST:event_cancelar

private void continuar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continuar
    if (this.jcbAsunto.getSelectedIndex() == 0 ||
            this.jcbSite.getSelectedIndex() == 0 ||
			(this.jrbLimAbs.isSelected() &&
				this.jsFechaD.getText().length() == 0 ||
				this.jsFechaH.getText().length() == 0) ||
			(this.jrbLimRel.isSelected() &&
				this.jsFechaIniMedida.getText().length() == 0 ||
				(this.jrbRelIniFin.isSelected() &&
					this.jsFechaRelIni.getText().length() == 0 ||
					this.jsFechaRelFin.getText().length() == 0) ||
				(this.jrbRelPerc.isSelected() &&
					this.jsFechaRelPerc.getText().length() == 0 ||
					this.jsFechaRelDur.getText().length() == 0))) {
        MensajeApp.muestraWarning(this, "Deben rellenarse todos los campos clave");
        return;
    }

    try {
        Long fechaDesde = null;
		Long fechaHasta = null;

		if (this.jrbLimAbs.isSelected()) {
			fechaDesde = this.jsFechaD.getTimeInMillis();
			fechaHasta = this.jsFechaH.getTimeInMillis();
		} else {
			Long fechaIniMed = this.jsFechaIniMedida.getTimeInMillis();

			if (this.jrbRelIniFin.isSelected()) {
				fechaDesde = fechaIniMed + this.jsFechaRelIni.getTimeInMillis();
				fechaHasta = fechaIniMed + this.jsFechaRelFin.getTimeInMillis();
			} else {
				Long percep = this.jsFechaRelPerc.getTimeInMillis();
				Long duracion = this.jsFechaRelDur.getTimeInMillis();

				fechaDesde = fechaIniMed + percep - duracion / 2;
				fechaHasta = fechaIniMed + percep + duracion / 2;
			}
		}

        if (fechaDesde.compareTo(fechaHasta) > 0) {
            MensajeApp.muestraWarning(this, "La fecha de inicio no puede ser mayor que la de fin");
            return;
        }

		this.jsFechaD.setTimeInMillis(fechaDesde);
		this.jsFechaH.setTimeInMillis(fechaHasta);

        habilitaDatos();
    } catch (IllegalArgumentException e) {
        MensajeApp.muestraError(this, e, "Fallo en el formato de fechas");
    }
}//GEN-LAST:event_continuar

private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    try {
        //Traemos las cabeceras de la tabla
        this.jtRegistros.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, Incidencia.getCamposTabla()) {

            //boolean[] canEdit = new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true};
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                //return canEdit[columnIndex];
                return false;
            }
        });
        this.jtRegistros.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        this.jtRegistros.getTableHeader().setReorderingAllowed(false);

        //Carga inicial de Asuntos
        Auxiliares.cargaAsuntosTipo(this.jcbAsunto, AsuntoRA.TIPO_ASUNTO_RA);
        
        habilitaClave(true);
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
    }
}//GEN-LAST:event_formWindowOpened

private void eliminar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminar
    try {
        int numFilas;

        if (JOptionPane.showConfirmDialog(this, "Se eliminará el registro actual. ¿Desea continuar?", "Aviso",
                JOptionPane.WARNING_MESSAGE) == JOptionPane.CANCEL_OPTION) {
            jtaObs.requestFocus();
            return;
        }

        Incidencia incidencia = getIncidenciaActual(false);

        numFilas = Incidencia.deleteIncidencias(incidencia, null);

		if (this.jcbAplicar.isSelected()) {
			incidencia = getIncidenciaActual(true);

			numFilas += Incidencia.deleteIncidencias(incidencia, null);
		}

        if (numFilas > 0) {
            MensajeApp.muestraInfo(this, numFilas + " fila(s) se ha(n) eliminado");

            habilitaClave(true);
        } else {
            MensajeApp.muestraWarning(this, "Ninguna fila eliminada");

            jtaObs.requestFocus();
        }
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al insertar en la base de datos");
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo añaaaaaadiendo campo");
    }
}//GEN-LAST:event_eliminar

    private void cambioTipoLim(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambioTipoLim
		Auxiliares.setEnabledCamposPanel(this.jpLimAbs, this.jrbLimAbs.isSelected());
		Auxiliares.setEnabledCamposPanel(this.jpLimRel, this.jrbLimRel.isSelected());

		if (this.jrbLimRel.isSelected())
			cambioTipoLimRel(null);
    }//GEN-LAST:event_cambioTipoLim

    private void cambioTipoLimRel(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambioTipoLimRel
		Auxiliares.setEnabledCamposPanel(this.jpRelIniFin, this.jrbRelIniFin.isSelected());
		Auxiliares.setEnabledCamposPanel(this.jpRelPerc, this.jrbRelPerc.isSelected());
    }//GEN-LAST:event_cambioTipoLimRel
    
    private void habilitaClave(boolean restablecerCombos) {
        Auxiliares.setEnabledCamposPanel(this.jpClave, true);
        Auxiliares.setEnabledCamposPanel(this.jpDatos, false);

		this.jcbAplicar.setVisible(false);
		this.jcbAplicar.setSelected(false);
        
		if (restablecerCombos) {
			this.jcbAsunto.requestFocus();
			this.jcbAsunto.setSelectedIndex(0);
		}

		limpiarCampos();
        
        this.inciVieja = null;

		cambioTipoLim(null);
        
        rellenarTabla();
    }

    private Incidencia getIncidenciaActual(boolean selSiteOCT)
    {
        Integer idAsunto = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbAsunto);
        Integer idSite = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbSite);
        Long fechaDesde = this.jsFechaD.getTimeInMillis();
        Long fechaHasta = this.jsFechaH.getTimeInMillis();
        String observacion = this.jtaObs.getText();

		if (selSiteOCT)
			idSite = TipoRA.ID_TIPO_OCT;
        
        return (new Incidencia(idAsunto, idSite, fechaDesde, fechaHasta, observacion));
        
    }

    private Incidencia getIncidenciaAnterior(boolean selSiteOCT) {
		Incidencia res = null;
		
		try {
			Integer idAsunto = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbAsunto);
			Integer idSite = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbSite);
			Long fechaDesde = this.jsFechaD.getTimeInMillis();
			Long fechaHasta = this.jsFechaH.getTimeInMillis();

			if (selSiteOCT)
				idSite = TipoRA.ID_TIPO_OCT;
			
			ArrayList<Incidencia> incidencias = Incidencia.getIncidencias(idAsunto, idSite, fechaDesde, fechaHasta, null, null, true);

			if (incidencias != null && incidencias.size() > 0)
				res = incidencias.get(0);
        } catch (SQLException e) {
            MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
        } catch (NoSuchFieldException e) {
            MensajeApp.muestraError(this, e, "Campo no encontrado");
		}
		
		return res;
    }
    
    private void habilitaDatos(){
        Auxiliares.setEnabledCamposPanel(this.jpClave, false);
        Auxiliares.setEnabledCamposPanel(this.jpDatos, true);
        
        this.jtaObs.requestFocus();
        
        this.inciVieja = getIncidenciaAnterior(false);
    }    

    private void rellenarTabla() {
        try {
            Integer idAsunto = null;
            Integer idSite = null;
            
            //Vaciamos la tabla antes de rellenar
            DefaultTableModel dtmRegistros = (DefaultTableModel) this.jtRegistros.getModel();
            dtmRegistros.setRowCount(0);

            if (this.jcbAsunto.getSelectedIndex() != 0) {
                idAsunto = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbAsunto);
                
                idSite = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbSite);
				ArrayList<Incidencia> datosIncidencias = Incidencia.getIncidencias(idAsunto, idSite, null, null, null, null, false);

				// Relleno la tabla
				if (datosIncidencias != null) {
					int nDatosIncidencias = datosIncidencias.size();

					for (int i = 0; i < nDatosIncidencias; i++) {
						dtmRegistros.addRow(datosIncidencias.get(i).toObject());
					}
				}
            }
        } catch (SQLException e) {
            MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
        } catch (NoSuchFieldException e) {
            MensajeApp.muestraError(this, e, "Campo no encontrado");
        }
    }

private void seleccionaAsunto(Integer idAsunto) {
    if (idAsunto != null) {
        int nAsuntos = jcbAsunto.getItemCount();

        Integer idAsuntoCombo;
        
        //Ignaramos el caso 0-Vacío
        for (int i = 1; i < nAsuntos; i++) {
            idAsuntoCombo = (Integer) ComboBoxObject.getClaveCombo(this.jcbAsunto, i);
            if (idAsuntoCombo.equals(idAsunto)) {
                jcbAsunto.setSelectedIndex(i);
                break;
            }
        }
    }
}

private void seleccionaSite(Integer idSite) {
    if (idSite != null) {
        int nSites = jcbSite.getItemCount();
        Integer idSiteItem;

        for (int i = 0; i < nSites; i++) {
            idSiteItem = (Integer) ((ComboBoxObject)this.jcbSite.getItemAt(i)).getCodigo();

            if (idSiteItem != null && idSiteItem.equals(idSite)) {
                jcbSite.setSelectedIndex(i);
                break;
            }
        }
    }
}

private void limpiarCampos() {
    this.jsFechaD.setText(TratFechas.FECHA_MIN);
    this.jsFechaH.setText(TratFechas.FECHA_MAX);

	this.jsFechaIniMedida.setText(TratFechas.FECHA_MIN);

	this.jsFechaRelIni.setText(TratFechas.MINUTOS_SIMPLE_MIN);
	this.jsFechaRelFin.setText(TratFechas.MINUTOS_SIMPLE_MIN);
	this.jsFechaRelPerc.setText(TratFechas.MINUTOS_SIMPLE_MIN);
	this.jsFechaRelDur.setText(TratFechas.MINUTOS_SIMPLE_MIN);
	
    this.jtaObs.setText("");
}

        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgAbsRel;
    private javax.swing.ButtonGroup bgRel;
    private javax.swing.JButton jbAceptar;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbContinuar;
    private javax.swing.JButton jbEliminar;
    private javax.swing.JCheckBox jcbAplicar;
    private javax.swing.JComboBox jcbAsunto;
    private javax.swing.JComboBox jcbSite;
    private javax.swing.JLabel jlAsunto;
    private javax.swing.JLabel jlFechaD;
    private javax.swing.JLabel jlFechaH;
    private javax.swing.JLabel jlFechaRelDur;
    private javax.swing.JLabel jlFechaRelFin;
    private javax.swing.JLabel jlFechaRelIni;
    private javax.swing.JLabel jlFechaRelPerc;
    private javax.swing.JLabel jlIniMedida;
    private javax.swing.JLabel jlObs;
    private javax.swing.JLabel jlSite;
    private javax.swing.JLabel jlTitRegistros;
    private javax.swing.JPanel jpClave;
    private javax.swing.JPanel jpDatos;
    private javax.swing.JPanel jpLimAbs;
    private javax.swing.JPanel jpLimRel;
    private javax.swing.JPanel jpPrincipal;
    private javax.swing.JPanel jpRegistros;
    private javax.swing.JPanel jpRelIniFin;
    private javax.swing.JPanel jpRelPerc;
    private javax.swing.JRadioButton jrbLimAbs;
    private javax.swing.JRadioButton jrbLimRel;
    private javax.swing.JRadioButton jrbRelIniFin;
    private javax.swing.JRadioButton jrbRelPerc;
    private general.JSpinnerDate jsFechaD;
    private general.JSpinnerDate jsFechaH;
    private general.JSpinnerDate jsFechaIniMedida;
    private general.JSpinnerDate jsFechaRelDur;
    private general.JSpinnerDate jsFechaRelFin;
    private general.JSpinnerDate jsFechaRelIni;
    private general.JSpinnerDate jsFechaRelPerc;
    private javax.swing.JScrollPane jspObs;
    private javax.swing.JScrollPane jspRegistros;
    private javax.swing.JTable jtRegistros;
    private javax.swing.JTextArea jtaObs;
    // End of variables declaration//GEN-END:variables
    
    // Objetos
    private Incidencia inciVieja;
    private Incidencia inciNueva;
} 

