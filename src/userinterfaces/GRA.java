package userinterfaces;


import RA.Global;
import calculos.CalculoAnguloLibre;
import calculos.DatosLimitesGUI;
import general.LoginRA;
import general.MensajeApp;
import java.awt.Toolkit;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.*;
import javax.swing.UIManager;

public class GRA extends JFrame {
    private LoginRA login;
    
    public LoginRA getLogin() {
        return login;
    }

    public void setLogin(LoginRA login) {
        this.login = login;
    }
    
    public static final String HELLO_MESSAGE = "OnlyOne is already running";
    public static final int PORT = 7779;
    private static JFrame frame;
    private static JTextField textField;
      
    // Inicia la pantalla GRA
    public GRA() {
		initComponents();   
	}
		
    // Lanza la primera parte de la aplicación (INICIO)
    public static void main(String args[]) throws ClassNotFoundException, InstantiationException {
		try {
			Global.cargaConfiguracionGlobal();

			frame = new JFrame("INICIO");
			textField = new JTextField("Iniciando...", 40);
			textField.setEditable(false);
			frame.getContentPane().add(textField);
			frame.pack();
			frame.setIconImage(new ImageIcon(RA.Global.RUTA_IMAGENES + "GRA.png").getImage());
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(false);
			new Thread() {
				@Override
				public void run() {
					listen();
				}
			}.start();
		} catch (IOException ex) {
			MensajeApp.muestraError(null, ex, "Error leyendo fichero de configuración global.");
			System.exit(0);
		}
    }
    
    public static void listen() {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(PORT);
        } catch (IOException e) {
            Socket s = null;
            try {
                s = new Socket("127.0.0.1", PORT);
                DataInputStream dis = new DataInputStream(s.getInputStream());
                String message = dis.readUTF();
                if(HELLO_MESSAGE.equals(message)) {
                    textField.setText("LA APLICACIÓN YA SE ESTÁ EJECUTANDO");
                    frame.setVisible(true);
                } else {
                    textField.setText("AlGO SE ESTÁ EJECUTANDO");
                    frame.setVisible(true);
                }
            } catch(IOException ioe) {
                textField.setText("No se ha podido crear el ServerSocket ni conectar al puerto indicado...");
                frame.setVisible(true);
            } finally {
                try {
                    if(s != null)
                        s.close();
                } catch(IOException ex) {
					MensajeApp.muestraError(null, ex, null);
				}
            }
            return;
        }
        
        new Thread() {
            @Override
            public void run() {
                frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                frame.dispose();
                ejecutarAplicacion();
            }
        }.start();

        try {
            Socket s = null;
            while((s = ss.accept()) != null) {
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                dos.writeUTF(HELLO_MESSAGE);
                s.close();
            }
        } catch(IOException ex) {
			MensajeApp.muestraError(null, ex, null);
        }
    }

    // Lanza el resto de la aplicación 
    private static void ejecutarAplicacion() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");

			java.awt.EventQueue.invokeLater(new Runnable() {
               public void run() {
                    GRA pc=new GRA();
                    pc.setVisible(true);
                    pc.setExtendedState(GRA.MAXIMIZED_BOTH); 
               }
            });
		} catch (ClassNotFoundException ex) {
			MensajeApp.muestraError(null, ex, null);
		} catch (InstantiationException ex) {
			MensajeApp.muestraError(null, ex, null);
		} catch (IllegalAccessException ex) {
			MensajeApp.muestraError(null, ex, null);
		} catch (UnsupportedLookAndFeelException ex) {
			MensajeApp.muestraError(null, ex, null);
		}
   }


    public void Salir(){
        System.exit(0);
    }


    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Menu1 = new javax.swing.JMenuBar();
        Nuevo = new javax.swing.JMenu();
        Menu2 = new javax.swing.JMenu();
        jMenuItem158 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem96 = new javax.swing.JMenuItem();
        Datos1 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu34 = new javax.swing.JMenu();
        Datos5 = new javax.swing.JMenuItem();
        Datos6 = new javax.swing.JMenuItem();
        jmAbrir = new javax.swing.JMenu();
        jmiAsunto = new javax.swing.JMenuItem();
        jmiConfiguracion = new javax.swing.JMenuItem();
        jmiParque = new javax.swing.JMenuItem();
        jmiAerogenerador = new javax.swing.JMenuItem();
        jmiCliente = new javax.swing.JMenuItem();
        jmInsercion = new javax.swing.JMenu();
        jMenuItem88 = new javax.swing.JMenuItem();
        Salir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem81 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jmCalcular = new javax.swing.JMenu();
        jmiCalcGenerico = new javax.swing.JMenuItem();
        jmiSectorValido = new javax.swing.JMenuItem();
        jmiCalcAngulos = new javax.swing.JMenuItem();
        jMenu29 = new javax.swing.JMenu();
        jMenuItem75 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("  GRA.01");
        setBackground(new java.awt.Color(51, 51, 51));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(51, 51, 51));
        setIconImage(new ImageIcon(RA.Global.RUTA_IMAGENES + "GRA.png").getImage());
        setName("Pantalla Principal"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                CerrarVentana(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                AbrirVentana(evt);
            }
        });

        Panel.setBackground(new java.awt.Color(255, 255, 255));
        Panel.setAutoscrolls(true);
        Panel.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        Panel.setDoubleBuffered(false);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 2, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "logo gra final.jpg" )));
        jLabel1.setAlignmentX(0.5F);

        javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
        Panel.setLayout(PanelLayout);
        PanelLayout.setHorizontalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1444, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 809, Short.MAX_VALUE)
        );

        Menu1.setBackground(new java.awt.Color(255, 255, 255));
        Menu1.setAutoscrolls(true);
        Menu1.setFocusable(false);

        Nuevo.setText("Archivo ");
        Nuevo.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        Menu2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "page_white.png")));
        Menu2.setText("Nuevo ...           ");
        Menu2.setBorderPainted(true);
        Menu2.setContentAreaFilled(false);

        jMenuItem158.setText("Asunto");
        jMenuItem158.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NAsuntoRA(evt);
            }
        });
        Menu2.add(jMenuItem158);

        jMenuItem7.setText("Descripción");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NDescripcion(evt);
            }
        });
        Menu2.add(jMenuItem7);

        jMenuItem6.setText("Configuración");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NConfiguracion(evt);
            }
        });
        Menu2.add(jMenuItem6);

        jMenuItem4.setText("Curva de Potencia");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NCP(evt);
            }
        });
        Menu2.add(jMenuItem4);

        jMenuItem96.setText("Datos");
        jMenuItem96.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NDatos(evt);
            }
        });
        Menu2.add(jMenuItem96);

        Datos1.setText("Modelo de Turbina");
        Datos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NAero(evt);
            }
        });
        Menu2.add(Datos1);

        jMenuItem14.setText("Parque Eólico");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NParque(evt);
            }
        });
        Menu2.add(jMenuItem14);

        jMenu34.setText("Cliente");

        Datos5.setText("Empresa");
        Datos5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NCliente(evt);
            }
        });
        jMenu34.add(Datos5);

        Datos6.setText("Contacto");
        Datos6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NContacto(evt);
            }
        });
        jMenu34.add(Datos6);

        Menu2.add(jMenu34);

        Nuevo.add(Menu2);

        jmAbrir.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "OpenFolder.gif" )));
        jmAbrir.setText("Abrir");
        jmAbrir.setBorderPainted(true);
        jmAbrir.setContentAreaFilled(false);

        jmiAsunto.setText("Asunto");
        jmiAsunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VAsunto(evt);
            }
        });
        jmAbrir.add(jmiAsunto);

        jmiConfiguracion.setText("Configuración");
        jmiConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirConfiguracion(evt);
            }
        });
        jmAbrir.add(jmiConfiguracion);

        jmiParque.setText("Parque Eólico");
        jmiParque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VParque(evt);
            }
        });
        jmAbrir.add(jmiParque);

        jmiAerogenerador.setText("Aerogenerador");
        jmiAerogenerador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VAero(evt);
            }
        });
        jmAbrir.add(jmiAerogenerador);

        jmiCliente.setText("Cliente / Usuarios Web");
        jmiCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VCliente(evt);
            }
        });
        jmAbrir.add(jmiCliente);

        jmInsercion.setText("Inserción");

        jMenuItem88.setText("Histórico fichero / datos");
        jMenuItem88.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Insercion(evt);
            }
        });
        jmInsercion.add(jMenuItem88);

        jmAbrir.add(jmInsercion);

        Nuevo.add(jmAbrir);

        Salir.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "Exit.png" )));
        Salir.setText("Salir");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Salir(evt);
            }
        });
        Nuevo.add(Salir);

        Menu1.add(Nuevo);

        jMenu2.setText("Editar ");

        jMenuItem26.setText("Asunto");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UAsunto(evt);
            }
        });
        jMenu2.add(jMenuItem26);

        Menu1.add(jMenu2);

        jMenu1.setText(" Ver ");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem81.setText("Datos");
        jMenuItem81.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EVariableSCB(evt);
            }
        });
        jMenu1.add(jMenuItem81);

        Menu1.add(jMenu1);

        jMenu3.setText("Revisar");

        jMenuItem8.setText("Incidencias");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRevIncidencias(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        Menu1.add(jMenu3);

        jmCalcular.setText("Calcular ");

        jmiCalcGenerico.setText("Genérico");
        jmiCalcGenerico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recogidaDatos(evt);
            }
        });
        jmCalcular.add(jmiCalcGenerico);

        jmiSectorValido.setText("Sector Válido por Estelas");
        jmiSectorValido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcSectorValido(evt);
            }
        });
        jmCalcular.add(jmiSectorValido);

        jmiCalcAngulos.setText("Cálculo Ángulo(s) Micro(s)-Aero");
        jmiCalcAngulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcAnguloMicroAero(evt);
            }
        });
        jmCalcular.add(jmiCalcAngulos);

        Menu1.add(jmCalcular);

        jMenu29.setText("Web");

        jMenuItem75.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(RA.Global.RUTA_IMAGENES + "logotipo.gif")));
        jMenuItem75.setText("Acceso clientes");
        jMenuItem75.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Web(evt);
            }
        });
        jMenu29.add(jMenuItem75);

        Menu1.add(jMenu29);

        setJMenuBar(Menu1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1462, 864));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

 
    private void Salir(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Salir
        System.exit(0);
}//GEN-LAST:event_Salir

private void AbrirVentana(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_AbrirVentana
   //new UsuarioGUI(this).setVisible(true);
	   new UsuarioRAGUI(this).setVisible(true);
}//GEN-LAST:event_AbrirVentana

private void CerrarVentana(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_CerrarVentana
    System.exit(0);
}//GEN-LAST:event_CerrarVentana
 
private void NParque(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NParque
    new PEGUI(this).setVisible(true);
}//GEN-LAST:event_NParque

private void VAsunto(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VAsunto
    new VAsuntoGUI(this).setVisible(true);
}//GEN-LAST:event_VAsunto

private void NAero(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NAero
    new AerogeneradorGUI(this).setVisible(true);
}//GEN-LAST:event_NAero

private void NCliente(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NCliente
 new ClienteGUI(this).setVisible(true);
}//GEN-LAST:event_NCliente

private void NContacto(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NContacto
    new ContactoGUI(this).setVisible(true);
}//GEN-LAST:event_NContacto

private void Web(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Web
	String url = "http://www.barlovento-recursos.com/User/Sys/Salir.php";
	try {
		Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
	} catch (IOException ex) {
		MensajeApp.muestraError(null, ex, null);
	}
}//GEN-LAST:event_Web

private void EVariableSCB(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EVariableSCB
  new VDatosGUI(this,Id).setVisible(true);
}//GEN-LAST:event_EVariableSCB

private void NDatos(java.awt.event.ActionEvent evt) {                        
   new DatosRAGUI(this).setVisible(true);
}                                        

private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed

}//GEN-LAST:event_jMenu1ActionPerformed

private void NAsuntoRA(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NAsuntoRA
    new AsuntoGUI(this,3).setVisible(true);
}//GEN-LAST:event_NAsuntoRA

private void UAsunto(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UAsunto
    new UAsuntoGUI(this, Id).setVisible(true);//GEN-LAST:event_UAsunto
}                        

private void VParque(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VParque
    new VParqueGUI(this).setVisible(true);
}//GEN-LAST:event_VParque

private void VAero(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VAero
    new VAerogeneradorGUI(this).setVisible(true);
}//GEN-LAST:event_VAero

private void VCliente(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VCliente
    new VClienteGUI(this).setVisible(true);
}//GEN-LAST:event_VCliente

private void Insercion(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Insercion
    new VFicheros(this).setVisible(true);
}//GEN-LAST:event_Insercion

private void NCP(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NCP
    new CurvaGUI(this,Id).setVisible(true);
}//GEN-LAST:event_NCP

private void NConfiguracion(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NConfiguracion
    new ConfiguracionRAGUI(this, ConfiguracionRAGUI.MODO_NUEVO).setVisible(true);
}//GEN-LAST:event_NConfiguracion

private void NDescripcion(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NDescripcion
    new DescripcionRAGUI2(this).setVisible(true);
}//GEN-LAST:event_NDescripcion

private void menuRevIncidencias(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRevIncidencias
    new RIncidenciasGUI(this).setVisible(true);
}//GEN-LAST:event_menuRevIncidencias

private void recogidaDatos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recogidaDatos
   //Pruebas con SPL
    new DatosLimitesGUI(this).setVisible(true);
}//GEN-LAST:event_recogidaDatos

private void calcSectorValido(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcSectorValido
    new SVGUI1(this, Id).setVisible(true);//GEN-LAST:event_calcSectorValido
}

private void calcAnguloMicroAero(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcAnguloMicroAero
    new CalculoAnguloLibre(this).setVisible(true);
}//GEN-LAST:event_calcAnguloMicroAero

    private void abrirConfiguracion(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirConfiguracion
		new ConfiguracionRAGUI(this, ConfiguracionRAGUI.MODO_VER).setVisible(true);
    }//GEN-LAST:event_abrirConfiguracion
       
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Datos1;
    private javax.swing.JMenuItem Datos5;
    private javax.swing.JMenuItem Datos6;
    private javax.swing.JMenuBar Menu1;
    private javax.swing.JMenu Menu2;
    private javax.swing.JMenu Nuevo;
    private javax.swing.JPanel Panel;
    private javax.swing.JMenuItem Salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu29;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu34;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem158;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem75;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem81;
    private javax.swing.JMenuItem jMenuItem88;
    private javax.swing.JMenuItem jMenuItem96;
    private javax.swing.JMenu jmAbrir;
    private javax.swing.JMenu jmCalcular;
    private javax.swing.JMenu jmInsercion;
    private javax.swing.JMenuItem jmiAerogenerador;
    private javax.swing.JMenuItem jmiAsunto;
    private javax.swing.JMenuItem jmiCalcAngulos;
    private javax.swing.JMenuItem jmiCalcGenerico;
    private javax.swing.JMenuItem jmiCliente;
    private javax.swing.JMenuItem jmiConfiguracion;
    private javax.swing.JMenuItem jmiParque;
    private javax.swing.JMenuItem jmiSectorValido;
    // End of variables declaration//GEN-END:variables
    public static int Id;
}
