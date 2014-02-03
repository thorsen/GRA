package userinterfaces;

import RA.AsuntoRA;
import general.Auxiliares;
import RA.DatosRA2;
import RA.Descripcion;
import RA.LineaConfiguracion;
import RA.SerieRA2;
import RA.TipoRA;
import RA.Variable;
import general.ComboBoxObject;
import general.MensajeApp;
import general.TratFechas;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class DatosRAGUI extends JDialog {

	private static boolean EDITABLE_POND_A = true;
    
    public DatosRAGUI(java.awt.Frame parent) {
        super(parent, true);

        initComponents();
    }
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgVelocidad = new javax.swing.ButtonGroup();
        jpGral = new javax.swing.JPanel();
        jpDatos = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jbSelRuta = new javax.swing.JButton();
        jspFicheros = new javax.swing.JScrollPane();
        jtFicheros = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jtfFechaDesde = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtfFechaHasta = new javax.swing.JTextField();
        jpProgress = new javax.swing.JPanel();
        jpbProg = new javax.swing.JProgressBar();
        jlProg = new javax.swing.JLabel();
        jrbMetrosSeg = new javax.swing.JRadioButton();
        jrbMetrosSegSB = new javax.swing.JRadioButton();
        jrbRevPorMin = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jbCancelar = new javax.swing.JButton();
        jbInsertar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jpClave = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jcbAsunto = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jcbSite = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DATOS ENSAYO RUIDO");
        setBackground(new java.awt.Color(175, 30, 30));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(255, 0, 0));
        setIconImage(new ImageIcon(RA.Global.RUTA_IMAGENES + "GRA.png").getImage());
        setLocationByPlatform(true);
        setName("Cliente"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jpGral.setBackground(new java.awt.Color(255, 255, 255));

        jpDatos.setBackground(new java.awt.Color(255, 255, 255));
        jpDatos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 51, 255)));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Selector de Mediciones ");

        jbSelRuta.setBackground(new java.awt.Color(255, 255, 255));
        jbSelRuta.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "OpenFolder.gif" )));
        jbSelRuta.setToolTipText("Seleccionar fichero de datos");
        jbSelRuta.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jbSelRuta.setPreferredSize(new java.awt.Dimension(65, 20));
        jbSelRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarRuta(evt);
            }
        });

        jtFicheros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fichero", "Pond A?", "RF?", "Ok?", "Path"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                boolean res = false;

                if (columnIndex != 1)
                    res = canEdit [columnIndex];
                else
                    res = EDITABLE_POND_A;

                return res;
            }
        });
        jtFicheros.getTableHeader().setReorderingAllowed(false);
        jspFicheros.setViewportView(jtFicheros);
        if (jtFicheros.getColumnModel().getColumnCount() > 0) {
            jtFicheros.getColumnModel().getColumn(0).setMinWidth(200);
            jtFicheros.getColumnModel().getColumn(0).setPreferredWidth(200);
            jtFicheros.getColumnModel().getColumn(0).setMaxWidth(500);
            jtFicheros.getColumnModel().getColumn(1).setMinWidth(50);
            jtFicheros.getColumnModel().getColumn(1).setPreferredWidth(50);
            jtFicheros.getColumnModel().getColumn(1).setMaxWidth(50);
            jtFicheros.getColumnModel().getColumn(2).setMinWidth(50);
            jtFicheros.getColumnModel().getColumn(2).setPreferredWidth(50);
            jtFicheros.getColumnModel().getColumn(2).setMaxWidth(50);
            jtFicheros.getColumnModel().getColumn(3).setMinWidth(50);
            jtFicheros.getColumnModel().getColumn(3).setPreferredWidth(50);
            jtFicheros.getColumnModel().getColumn(3).setMaxWidth(50);
            jtFicheros.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel5.setText("  Fecha de Inicio:");

        jtfFechaDesde.setEditable(false);
        jtfFechaDesde.setBackground(new java.awt.Color(204, 204, 204));
        jtfFechaDesde.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel6.setText("  Último dato:");

        jtfFechaHasta.setEditable(false);
        jtfFechaHasta.setBackground(new java.awt.Color(204, 204, 204));
        jtfFechaHasta.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jpProgress.setBackground(new java.awt.Color(255, 255, 255));
        jpProgress.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jpbProg.setBackground(new java.awt.Color(255, 255, 255));

        jlProg.setBackground(new java.awt.Color(255, 255, 255));
        jlProg.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlProg.setText("Progreso de la inserción »");
        jlProg.setFocusable(false);

        javax.swing.GroupLayout jpProgressLayout = new javax.swing.GroupLayout(jpProgress);
        jpProgress.setLayout(jpProgressLayout);
        jpProgressLayout.setHorizontalGroup(
            jpProgressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpProgressLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpProgressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpbProg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlProg, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8))
        );
        jpProgressLayout.setVerticalGroup(
            jpProgressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpProgressLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlProg)
                .addGap(11, 11, 11)
                .addComponent(jpbProg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jrbMetrosSeg.setBackground(new java.awt.Color(255, 255, 255));
        bgVelocidad.add(jrbMetrosSeg);
        jrbMetrosSeg.setText("m/s");

        jrbMetrosSegSB.setBackground(new java.awt.Color(255, 255, 255));
        bgVelocidad.add(jrbMetrosSegSB);
        jrbMetrosSegSB.setSelected(true);
        jrbMetrosSegSB.setText("m/s (SoundBook)");

        jrbRevPorMin.setBackground(new java.awt.Color(255, 255, 255));
        bgVelocidad.add(jrbRevPorMin);
        jrbRevPorMin.setText("rpm");

        jLabel4.setText("Velocidad:");

        javax.swing.GroupLayout jpDatosLayout = new javax.swing.GroupLayout(jpDatos);
        jpDatos.setLayout(jpDatosLayout);
        jpDatosLayout.setHorizontalGroup(
            jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDatosLayout.createSequentialGroup()
                        .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jtfFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(48, 48, 48)
                        .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpDatosLayout.createSequentialGroup()
                                .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jtfFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(48, 48, 48))
                            .addGroup(jpDatosLayout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jrbMetrosSeg)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jrbMetrosSegSB)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jrbRevPorMin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jbSelRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jpProgress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jspFicheros, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jpDatosLayout.setVerticalGroup(
            jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpDatosLayout.createSequentialGroup()
                        .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpDatosLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpDatosLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(40, 40, 40)
                        .addComponent(jLabel3))
                    .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jrbMetrosSeg)
                        .addComponent(jrbMetrosSegSB)
                        .addComponent(jrbRevPorMin)
                        .addComponent(jLabel4))
                    .addComponent(jbSelRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspFicheros, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpProgress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jbCancelar.setBackground(new java.awt.Color(255, 255, 255));
        jbCancelar.setText("CANCELAR");
        jbCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelar(evt);
            }
        });

        jbInsertar.setBackground(new java.awt.Color(255, 255, 255));
        jbInsertar.setText("INSERTAR");
        jbInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertar(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("MEDICIONES DEL ENSAYO DE RUIDO ACÚSTICO");

        jpClave.setBackground(new java.awt.Color(255, 255, 255));
        jpClave.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Asunto:");

        jcbAsunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioAsunto(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Site:");

        jcbSite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioSite(evt);
            }
        });

        javax.swing.GroupLayout jpClaveLayout = new javax.swing.GroupLayout(jpClave);
        jpClave.setLayout(jpClaveLayout);
        jpClaveLayout.setHorizontalGroup(
            jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpClaveLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jcbAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbSite, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jpClaveLayout.setVerticalGroup(
            jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpClaveLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbSite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpGralLayout = new javax.swing.GroupLayout(jpGral);
        jpGral.setLayout(jpGralLayout);
        jpGralLayout.setHorizontalGroup(
            jpGralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpGralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpGralLayout.createSequentialGroup()
                        .addGroup(jpGralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jpGralLayout.createSequentialGroup()
                                .addComponent(jbCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jbInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jpClave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpGralLayout.setVerticalGroup(
            jpGralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpGralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(11, 11, 11)
                .addComponent(jpClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jpDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpGralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbCancelar)
                    .addComponent(jbInsertar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpDatos.getAccessibleContext().setAccessibleName("DATOS_CP");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpGral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpGral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(592, 586));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelar
        habilitaClave();
}//GEN-LAST:event_cancelar
        
private void limpiarCampos() {
    // Fechas
    this.jtfFechaDesde.setText("");
    this.jtfFechaHasta.setText("");
    
    this.dtmFicheros.setRowCount(0);
    this.jpbProg.setValue(0);
}

private void actualizaFechas(String tipoTabla, Integer idAsunto) throws SQLException  {
    this.jtfFechaDesde.setText(TratFechas.toStringFecha(DatosRA2.getFechaIniDatos(tipoTabla, idAsunto)));
    this.jtfFechaHasta.setText(TratFechas.toStringFecha(DatosRA2.getFechaFinDatos(tipoTabla, idAsunto)));
}

private void cambioAsunto(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambioAsunto
    try {
        //Cargamos los Sites para el asunto seleccionado
        Integer idAsunto = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbAsunto);
        
        Auxiliares.cargaSites(idAsunto, this.jcbSite);
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
    }
}//GEN-LAST:event_cambioAsunto

private void cambioSite(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambioSite
    try {
        Integer idSite = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbSite);

        //Cargamos las fechas
        if (idSite != null) {
			Integer idAsunto = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbAsunto);
        
            TipoRA tipoRA = TipoRA.getTipoRAPorIdSite(idSite);
            
            ArrayList<String> codigos = SerieRA2.getCodigosPorIdAsuntoTipo(idAsunto, tipoRA.getIdTipoRA());
    
            if (codigos == null || codigos.isEmpty()) {
                MensajeApp.muestraWarning(this, "Antes de insertar datos debe insertar una descripción al ensayo");
                
                this.jcbSite.setSelectedIndex(0);
            } else {
                actualizaFechas(tipoRA.getSufijo(), idAsunto);

				EDITABLE_POND_A = !tipoRA.getSufijo().equals(Auxiliares.TIPO_SPL);

                habilitaDatos();
            }
        }
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
    }
}//GEN-LAST:event_cambioSite

private void insertar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertar
    try {
        this.jpbProg.setValue(0);
        
        Integer tipoVel;
        
        String ficError = "";
        Integer idAsunto = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbAsunto);
        Integer idSite = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbSite);
        String tipoTabla = TipoRA.getTipoRAPorIdSite(idSite).getSufijo();

        Auxiliares.bloqueaDialog(this, true);

        int nFicheros = this.dtmFicheros.getRowCount();
        
        //Cogemos la descripción para el asunto para poder comprobar si están todos los campos y si no existe configuración para alguno
        Integer idSerie;
        SerieRA2 serie;
        Variable variable;
        String nomVariable;
        
        ArrayList<Descripcion> descripciones = Descripcion.getDescripciones(idAsunto, null, null, null, null, null);
        int nDescripciones = descripciones.size();
        ArrayList<SerieRA2> seriesDesc = new ArrayList<SerieRA2>();
        ArrayList<String> nomVariablesDesc = new ArrayList<String>();
        
        for (int i = 0; i < nDescripciones; i++) {
            idSerie = descripciones.get(i).getIdSerie();
            serie = SerieRA2.getSerieRA2PorId(idSerie);
            seriesDesc.add(serie);
            
            variable = serie != null && serie.getIdVariable() != null ? Variable.getVariablePorId(serie.getIdVariable()) : null;
            nomVariable = variable != null ? variable.getNomVariable() : "No Definida";

            nomVariablesDesc.add(nomVariable);
        }
        
        ArrayList<LineaConfiguracion> lineasConfTot = LineaConfiguracion.getLineasConfPorAsuntoSiteConf(idAsunto, idSite, null);
        
        if (this.jrbMetrosSeg.isSelected())
            tipoVel = DatosRA2.VEL_M_S;
        else if (this.jrbRevPorMin.isSelected())
            tipoVel = DatosRA2.VEL_RPM;
        else
            tipoVel = DatosRA2.VEL_M_S_SB;
        
        for (int i = 0; i < nFicheros; i++) {
            this.jpbProg.setValue((int) Math.ceil(100.0*(2*i + 1)/(2*nFicheros)));
            if (i > 0)
                Auxiliares.muestraFila(this.jtFicheros, i - 1);
            update(this.getGraphics());
            
            //Marcamos la importación como válida si se he realizado correctamente
            if (DatosRA2.anadeDatosFic(tipoTabla, idAsunto, idSite, (Boolean) this.dtmFicheros.getValueAt(i, 1), (Boolean) this.dtmFicheros.getValueAt(i, 2), tipoVel, (String) this.dtmFicheros.getValueAt(i, 4), Auxiliares.obtenerLogin(this).getIdResponsable(), seriesDesc, nomVariablesDesc, lineasConfTot)) {
                this.dtmFicheros.setValueAt(true, i, 3);
            } else
                ficError += (String) this.dtmFicheros.getValueAt(i, 0) + ", ";

            this.jpbProg.setValue((int) Math.ceil(100.0*(2*i+2)/(2*nFicheros)));
            update(this.getGraphics());
        }
        
        //Actualizamos las fecha por si han cambiado
        actualizaFechas(tipoTabla, idAsunto);

        if (ficError.length() > 0)
            ficError = "<br><b>Error importando: </b>" + ficError.substring(0, ficError.length()-2);
        
        MensajeApp.muestraInfo(this, "Proceso de inserción finalizado" + ficError);
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
    } catch (IOException e) {
        MensajeApp.muestraError(this, e, "Fallo accediendo al fichero");
    } catch (ParserConfigurationException e) {
        MensajeApp.muestraError(this, e, "Fallo en la configuración del parser");
    } catch (TransformerException e) {
        MensajeApp.muestraError(this, e, "Fallo en la transformación");
    } finally {
        Auxiliares.bloqueaDialog(this, false);
    }
}//GEN-LAST:event_insertar

private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    try {
        this.dtmFicheros = (DefaultTableModel) this.jtFicheros.getModel();
        //Ocultamos la columna de path de la tabla
        this.jtFicheros.removeColumn(this.jtFicheros.getColumn("Path"));
        
        //Carga inicial de Asuntos
        Auxiliares.cargaAsuntosTipo(this.jcbAsunto, AsuntoRA.TIPO_ASUNTO_RA);
        
        habilitaClave();
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
    }
}//GEN-LAST:event_formWindowOpened

private void seleccionarRuta(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarRuta
        JFileChooser fc = new JFileChooser(Auxiliares.dameRutaDefecto(this)); 
        fc.setMultiSelectionEnabled(true);
        
        int seleccion = fc.showOpenDialog(this);              
        
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File[] ficheros = fc.getSelectedFiles();           
            int n = ficheros.length;

			Integer idSite = (Integer) ComboBoxObject.getClaveSelCombo(this.jcbSite);
			boolean esSPL = false;
			try {
				esSPL = TipoRA.getTipoRAPorIdSite(idSite).getSufijo().equals(Auxiliares.TIPO_SPL);
			} catch (SQLException e) {
				MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
			} catch (NoSuchFieldException e) {
				MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
			}
            
            dtmFicheros.setRowCount(0);
            
            for (int i = 0; i < n; i++)
                dtmFicheros.addRow(new Object[]{ficheros[i].getName(), ficheros[i].getName().toUpperCase().contains("PONDA") || esSPL, ficheros[i].getName().toUpperCase().contains("RF"), false, ficheros[i].getPath()});
        }
}//GEN-LAST:event_seleccionarRuta
   
private void habilitaClave() {
        Auxiliares.setEnabledCamposPanel(this.jpClave, true);
        Auxiliares.setEnabledCamposPanel(this.jpDatos, false);
        
        jbCancelar.setEnabled(false);
        jbInsertar.setEnabled(false);
        
        this.jcbAsunto.requestFocus();
        this.jcbAsunto.setSelectedIndex(0);
        
        limpiarCampos();
    }
    
    private void habilitaDatos(){
        Auxiliares.setEnabledCamposPanel(this.jpClave, false);
        Auxiliares.setEnabledCamposPanel(this.jpDatos, true);
        
        jbCancelar.setEnabled(true);
        jbInsertar.setEnabled(true);
        
        this.jbSelRuta.requestFocus();
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgVelocidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JButton jbCancelar;
    private javax.swing.JButton jbInsertar;
    private javax.swing.JButton jbSelRuta;
    private javax.swing.JComboBox jcbAsunto;
    private javax.swing.JComboBox jcbSite;
    private javax.swing.JLabel jlProg;
    private javax.swing.JPanel jpClave;
    private javax.swing.JPanel jpDatos;
    private javax.swing.JPanel jpGral;
    private javax.swing.JPanel jpProgress;
    private javax.swing.JProgressBar jpbProg;
    private javax.swing.JRadioButton jrbMetrosSeg;
    private javax.swing.JRadioButton jrbMetrosSegSB;
    private javax.swing.JRadioButton jrbRevPorMin;
    private javax.swing.JScrollPane jspFicheros;
    private javax.swing.JTable jtFicheros;
    private javax.swing.JTextField jtfFechaDesde;
    private javax.swing.JTextField jtfFechaHasta;
    // End of variables declaration//GEN-END:variables
    
    // Tablas
    private DefaultTableModel dtmFicheros;
}
