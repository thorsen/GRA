package general;

public class RecogidaDatos extends javax.swing.JPanel {
	
	public RecogidaDatos() {
		initComponents();
	}

	public RecogidaDatos(String texto, String campoRecoger) {
		initComponents();

		if (texto == null)
			this.jlTexto.setVisible(false);
		else
			this.jlTexto.setText(texto);
		
		if (campoRecoger == null) {
			this.jlRecogidaDatos.setVisible(false);
			this.jtfRecogidaDatos.setVisible(false);
		} else
			this.jlRecogidaDatos.setText(campoRecoger);

		this.revalidate();
	}

	public String getCampoRecoger() {
		return this.jtfRecogidaDatos.getText();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlTexto = new javax.swing.JLabel();
        jlRecogidaDatos = new javax.swing.JLabel();
        jtfRecogidaDatos = new javax.swing.JTextField();

        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                formAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jlTexto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTexto.setText("TextoAclaratorio");

        jlRecogidaDatos.setText("Etiqueta:");

        jtfRecogidaDatos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlRecogidaDatos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfRecogidaDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jlTexto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlTexto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlRecogidaDatos)
                    .addComponent(jtfRecogidaDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
		if (this.jtfRecogidaDatos.isVisible())
			this.jtfRecogidaDatos.requestFocus();
    }//GEN-LAST:event_formAncestorAdded


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jlRecogidaDatos;
    private javax.swing.JLabel jlTexto;
    private javax.swing.JTextField jtfRecogidaDatos;
    // End of variables declaration//GEN-END:variables
}
