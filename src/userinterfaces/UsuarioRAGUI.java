
package userinterfaces;

import general.IVExtendido;
import general.LoginRA;
import general.MensajeApp;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.*;

public class UsuarioRAGUI extends JDialog {
     
public UsuarioRAGUI(java.awt.Frame parent) {
    super(parent, true);
    initComponents();    
}  
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton6 = new javax.swing.JButton();
        jpEntrada = new javax.swing.JPanel();
        jpDatos = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfUsuario = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jpfPassword = new javax.swing.JPasswordField();
        jLabel38 = new javax.swing.JLabel();
        jbSalir = new javax.swing.JButton();
        jbAceptar = new javax.swing.JButton();

        jButton6.setText("OK");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("ACCESO USUARIO");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(255, 0, 0));
        setIconImage(new ImageIcon("\\\\B2solar\\Datos\\Curva\\Imagenes\\key.png").getImage());
        setLocationByPlatform(true);
        setName("Cliente"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jpEntrada.setBackground(new java.awt.Color(255, 255, 255));

        jpDatos.setBackground(new java.awt.Color(255, 255, 255));
        jpDatos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Usuario:");

        jtfUsuario.setText("Ruth");
        jtfUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfUsuarioKeyPressed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(51, 51, 255));
        jLabel37.setText("*");

        jLabel2.setText("Contraseña:");

        jpfPassword.setText("Ru8865No");
        jpfPassword.setToolTipText("");
        jpfPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jpfPasswordKeyPressed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(51, 51, 255));
        jLabel38.setText("*");

        javax.swing.GroupLayout jpDatosLayout = new javax.swing.GroupLayout(jpDatos);
        jpDatos.setLayout(jpDatosLayout);
        jpDatosLayout.setHorizontalGroup(
            jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpDatosLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(32, 32, 32))
                    .addGroup(jpDatosLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(12, 12, 12)))
                .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpfPassword)
                    .addComponent(jtfUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37)
                    .addComponent(jLabel38))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jpDatosLayout.setVerticalGroup(
            jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDatosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel38)
                    .addComponent(jpfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78))
        );

        jbSalir.setText("SALIR");
        jbSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salir(evt);
            }
        });

        jbAceptar.setText("ACEPTAR");
        jbAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptar(evt);
            }
        });

        javax.swing.GroupLayout jpEntradaLayout = new javax.swing.GroupLayout(jpEntrada);
        jpEntrada.setLayout(jpEntradaLayout);
        jpEntradaLayout.setHorizontalGroup(
            jpEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpEntradaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jpEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jpDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpEntradaLayout.createSequentialGroup()
                        .addComponent(jbSalir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
                        .addComponent(jbAceptar)))
                .addContainerGap())
        );
        jpEntradaLayout.setVerticalGroup(
            jpEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpEntradaLayout.createSequentialGroup()
                .addGroup(jpEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpEntradaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jpDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpEntradaLayout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addGroup(jpEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbSalir)
                            .addComponent(jbAceptar))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(334, 167));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void validarEntrada() {
    try {
        LoginRA login = new LoginRA(this.jtfUsuario.getText(), String.valueOf(this.jpfPassword.getPassword()));

        if (login.getIdResponsable() != null) {
            ((GRA) this.getParent()).setLogin(login);
            dispose();
        } else {
            MensajeApp.muestraError(this, null, "Usuario/Contraseña incorrectos");
        }                        
    } catch (SQLException e) {
        MensajeApp.muestraError(this, e, "Fallo al consultar la base de datos");
    } catch (NoSuchFieldException e) {
        MensajeApp.muestraError(this, e, "Fallo añadiendo campo");
    }
}    
    
private void aceptar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptar
    validarEntrada();
        }//GEN-LAST:event_aceptar

private void salir(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salir
    dispose();        
   ((GRA)this.getOwner()).Salir();
}//GEN-LAST:event_salir

private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    //Añadimos los verificadores de los campos de entrada
    this.jtfUsuario.setInputVerifier(new IVExtendido(this, IVExtendido.TIPO_STRING, false, true));
    this.jpfPassword.setInputVerifier(new IVExtendido(this, IVExtendido.TIPO_STRING, false, true));
}//GEN-LAST:event_formWindowOpened

private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
    if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
        validarEntrada();
    }
}//GEN-LAST:event_formKeyPressed

private void jtfUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfUsuarioKeyPressed
    if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
        validarEntrada();
    }
}//GEN-LAST:event_jtfUsuarioKeyPressed

private void jpfPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jpfPasswordKeyPressed
    if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
        validarEntrada();
    }
}//GEN-LAST:event_jpfPasswordKeyPressed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JButton jbAceptar;
    private javax.swing.JButton jbSalir;
    private javax.swing.JPanel jpDatos;
    private javax.swing.JPanel jpEntrada;
    private javax.swing.JPasswordField jpfPassword;
    private javax.swing.JTextField jtfUsuario;
    // End of variables declaration//GEN-END:variables
    
}
