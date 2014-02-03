package calculos;

import RA.AsuntoRA;
import RA.NormaRA;
import general.AjusteTablasDinamico;
import general.Auxiliares;
import general.DatosIncertidumbre;
import general.MensajeApp;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class DatosIncertidumbreGUI extends JDialog {
    private String tipoTabla;
    private Integer idAsunto;
    
    LinkedHashMap<Integer, LinkedHashMap<Object, DatosIncertidumbre>> datosIncertidumbre; //<Bin, <SubCalse incertidumbre, incertidumbres>>
    
    private Integer idNorma;
    private ArrayList<Integer> modoSalida;
    
    public DatosIncertidumbreGUI(java.awt.Frame parent, String tipoTabla, Integer idAsunto, LinkedHashMap<Integer, LinkedHashMap<Object, DatosIncertidumbre>> datosIncertidumbre, Integer idNorma, ArrayList<Integer> modoSalida) {
        super(parent, true);

        initComponents();

        this.tipoTabla = tipoTabla;
        this.idAsunto = idAsunto;
        
        this.datosIncertidumbre = datosIncertidumbre;
        
        this.modoSalida = modoSalida; //variable para control de llamadas entre diálogos

		this.idNorma = idNorma;

        try {
            this.jtfAsunto.setText(AsuntoRA.getAsuntoPorId(idAsunto).getNombreCompleto());
        } catch (SQLException e) {
            MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
        } catch (NoSuchFieldException e) {
            MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
        }           
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpPrincipal = new javax.swing.JPanel();
        jpClave = new javax.swing.JPanel();
        jlAsunto = new javax.swing.JLabel();
        jtfAsunto = new javax.swing.JTextField();
        jpDatos = new javax.swing.JPanel();
        jpIncertidumbres = new javax.swing.JPanel();
        jspIncertidumbres = new javax.swing.JScrollPane();
        jtIncertidumbres = new general.JTableExport();
        jlTitIncertidumbres = new javax.swing.JLabel();
        jbAnt = new javax.swing.JButton();
        jbSig = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocationByPlatform(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jpPrincipal.setBackground(new java.awt.Color(255, 255, 255));

        jpClave.setBackground(new java.awt.Color(255, 255, 255));
        jpClave.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlAsunto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlAsunto.setText("  Asunto: ");

        jtfAsunto.setEditable(false);
        jtfAsunto.setBackground(new java.awt.Color(204, 204, 204));
        jtfAsunto.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jpClaveLayout = new javax.swing.GroupLayout(jpClave);
        jpClave.setLayout(jpClaveLayout);
        jpClaveLayout.setHorizontalGroup(
            jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpClaveLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlAsunto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jtfAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpClaveLayout.setVerticalGroup(
            jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpClaveLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpClaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlAsunto)
                    .addComponent(jtfAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpDatos.setBackground(new java.awt.Color(255, 255, 255));
        jpDatos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jpIncertidumbres.setBackground(new java.awt.Color(255, 255, 255));

        jtIncertidumbres.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jtIncertidumbres.setToolTipText("Incertidumbres");
        jtIncertidumbres.getTableHeader().setReorderingAllowed(false);
        jspIncertidumbres.setViewportView(jtIncertidumbres);

        javax.swing.GroupLayout jpIncertidumbresLayout = new javax.swing.GroupLayout(jpIncertidumbres);
        jpIncertidumbres.setLayout(jpIncertidumbresLayout);
        jpIncertidumbresLayout.setHorizontalGroup(
            jpIncertidumbresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jspIncertidumbres, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jpIncertidumbresLayout.setVerticalGroup(
            jpIncertidumbresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jspIncertidumbres, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
        );

        jlTitIncertidumbres.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlTitIncertidumbres.setForeground(new java.awt.Color(102, 102, 102));
        jlTitIncertidumbres.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTitIncertidumbres.setText("INCERTIDUMBRES");
        jlTitIncertidumbres.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jpDatosLayout = new javax.swing.GroupLayout(jpDatos);
        jpDatos.setLayout(jpDatosLayout);
        jpDatosLayout.setHorizontalGroup(
            jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlTitIncertidumbres, javax.swing.GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
                    .addComponent(jpIncertidumbres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpDatosLayout.setVerticalGroup(
            jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlTitIncertidumbres, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpIncertidumbres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jbAnt.setBackground(new java.awt.Color(255, 255, 255));
        jbAnt.setText("<<");
        jbAnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anterior(evt);
            }
        });

        jbSig.setBackground(new java.awt.Color(255, 255, 255));
        jbSig.setText("  >>");
        jbSig.setAlignmentX(0.5F);
        jbSig.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbSig.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jbSig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguiente(evt);
            }
        });

        javax.swing.GroupLayout jpPrincipalLayout = new javax.swing.GroupLayout(jpPrincipal);
        jpPrincipal.setLayout(jpPrincipalLayout);
        jpPrincipalLayout.setHorizontalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpDatos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpClave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpPrincipalLayout.createSequentialGroup()
                        .addComponent(jbAnt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbSig, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jpPrincipalLayout.setVerticalGroup(
            jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbAnt)
                    .addComponent(jbSig))
                .addContainerGap())
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

        setSize(new java.awt.Dimension(804, 712));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void crearColsDatos() throws SQLException {
        Object[] columns;
        
        Integer keyMax = null;
        int maxPos = -1;
        
        Iterator it = this.datosIncertidumbre.entrySet().iterator();
        Entry<Integer, LinkedHashMap<Object, DatosIncertidumbre>> entry;
        
        while (it.hasNext()) {
            entry = (Entry<Integer, LinkedHashMap<Object, DatosIncertidumbre>>) it.next();
            
            if (entry.getValue().size() > maxPos) {
                maxPos = entry.getValue().size();
                keyMax = entry.getKey();
            }
        }
        
        columns = (!this.idNorma.equals(NormaRA.ID_NORMA_IEC_3_0) || this.tipoTabla.contentEquals(Auxiliares.TIPO_FFT)) ? new Object[3 * maxPos + 1] : new Object[maxPos + 1];
        columns[0] = "Bin";
        
        it = this.datosIncertidumbre.get(keyMax).keySet().iterator();
        
        int pos = 1;
        Object col;
		if (!this.idNorma.equals(NormaRA.ID_NORMA_IEC_3_0) || this.tipoTabla.contentEquals(Auxiliares.TIPO_FFT)) {
			while (it.hasNext()) {
				col = it.next();
				columns[pos++] = "<html>" + col + " - U<sub>A</sub></html>";
				if (!this.idNorma.equals(NormaRA.ID_NORMA_IEC_3_0))
					columns[pos++] = "<html>" + col + " - U<sub>B9</sub></html>";
				else
					columns[pos++] = "<html>" + col + " - U<sub>B</sub></html>";
				columns[pos++] = "<html>" + col + " - U<sub>C</sub></html>";
			}
		} else {
			if (this.tipoTabla.contentEquals(Auxiliares.TIPO_OCT)) {
				while (it.hasNext()) {
					col = it.next();
					columns[pos++] = "<html>" + col + " - u<sub>c,i,k</sub></html>";
				}
			} else if (this.tipoTabla.contentEquals(Auxiliares.TIPO_SPL)) {
				while (it.hasNext()) {
					col = it.next();
					columns[pos++] = "<html>" + col + " - u<sub>L<sub>WA,k</sub></sub></html>";
				}
			}
		}
         
        this.jtIncertidumbres.setModel(new javax.swing.table.DefaultTableModel(new Object[][]{}, columns) {
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				//return canEdit[columnIndex];
				return false;
			}
        });

        this.jtIncertidumbres.getTableHeader().setReorderingAllowed(false);
    }
    
    private void rellenarDatos(JProgressBar jpb) throws SQLException, NoSuchFieldException, Exception {
        crearColsDatos();
        
        DefaultTableModel dtmIncertidumbre = (DefaultTableModel) this.jtIncertidumbres.getModel();
        dtmIncertidumbre.setRowCount(0);

        Iterator itBin = this.datosIncertidumbre.entrySet().iterator();
        Entry<Integer, LinkedHashMap<Object, DatosIncertidumbre>> entryBin;
        Iterator itSubClase;
        Entry<Object, DatosIncertidumbre> entrySubClase;
        ArrayList<Object> fila;
        int nDatos = this.datosIncertidumbre.size();
  
        while (itBin.hasNext()) {
            entryBin = (Entry<Integer, LinkedHashMap<Object, DatosIncertidumbre>>) itBin.next();

            itSubClase = entryBin.getValue().entrySet().iterator();
            
            fila = new ArrayList<Object>();
            
            fila.add(entryBin.getKey());
            
			if (!this.idNorma.equals(NormaRA.ID_NORMA_IEC_3_0) || this.tipoTabla.contentEquals(Auxiliares.TIPO_FFT)) {
				while (itSubClase.hasNext()) {
					entrySubClase = (Entry<Object, DatosIncertidumbre>) itSubClase.next();
					
					fila.add(entrySubClase.getValue().getUA());
					if (!this.idNorma.equals(NormaRA.ID_NORMA_IEC_3_0))
						fila.add(entrySubClase.getValue().getUB9());
					else
						fila.add(Math.sqrt(entrySubClase.getValue().getSumaCuadraticaIncert(this.idNorma)));
					fila.add(entrySubClase.getValue().getUC());
				}
			} else {
				while (itSubClase.hasNext()) {
					entrySubClase = (Entry<Object, DatosIncertidumbre>) itSubClase.next();
					
					fila.add(entrySubClase.getValue().getUCorreccionRF());
				}
			}
            
            dtmIncertidumbre.addRow(fila.toArray());
            Auxiliares.incPorcentajeProgress(jpb, 1.0/nDatos);
        }

        AjusteTablasDinamico atdIncertidumbres = new AjusteTablasDinamico(this.jtIncertidumbres, AjusteTablasDinamico.ESPACIADO_COL_MED);
        atdIncertidumbres.maximizaSiEsPosible();
        Auxiliares.centrarTabla(this.jtIncertidumbres);
    }

private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    boolean error = false;
    
    try {
        //Establecemos el título
        this.setTitle("SEGUIMIENTO / " + this.tipoTabla.toUpperCase() + " - INCERTIDUMBRES");

        //Rellenamos los datos
        Auxiliares.bloqueaDialog(this, true);
        JProgressBar jpb = Auxiliares.muestraProgress(this, 100 * 1000);

        this.rellenarDatos(jpb);

        Auxiliares.ocultaProgress(jpb);
        Auxiliares.bloqueaDialog(this, false);

        this.update(this.getGraphics());
    } catch (SQLException e) {
        error = true;
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    } catch (NoSuchFieldException e) {
        error = true;
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
    } catch (NumberFormatException e) {
        error = true;
        MensajeApp.muestraError(this, e, "Fallo tratando número");
    } catch (Exception e) {
        error = true;
        MensajeApp.muestraError(this, e, "Fallo realizando acción");
    } finally {
        if (error) {
            Auxiliares.salirDialogo(this, null, this.modoSalida, Auxiliares.MODO_ERROR);
        }
    }
}//GEN-LAST:event_formWindowOpened

private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    if (Auxiliares.esSalidaUndefined(this.modoSalida)) //Se cierra desde la propia ventana
        Auxiliares.salirDialogo(this, Auxiliares.CANCEL_PROCESS, this.modoSalida, Auxiliares.MODO_CANCEL);
    else
        this.dispose();
}//GEN-LAST:event_formWindowClosing

private void anterior(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anterior
    Auxiliares.setModoSalida(this.modoSalida, Auxiliares.MODO_ANT);
    formWindowClosing(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
}//GEN-LAST:event_anterior

private void siguiente(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguiente
    Auxiliares.setModoSalida(this.modoSalida, Auxiliares.MODO_OK);
    formWindowClosing(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
}//GEN-LAST:event_siguiente


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbAnt;
    private javax.swing.JButton jbSig;
    private javax.swing.JLabel jlAsunto;
    private javax.swing.JLabel jlTitIncertidumbres;
    private javax.swing.JPanel jpClave;
    private javax.swing.JPanel jpDatos;
    private javax.swing.JPanel jpIncertidumbres;
    private javax.swing.JPanel jpPrincipal;
    private javax.swing.JScrollPane jspIncertidumbres;
    private general.JTableExport jtIncertidumbres;
    public javax.swing.JTextField jtfAsunto;
    // End of variables declaration//GEN-END:variables
}