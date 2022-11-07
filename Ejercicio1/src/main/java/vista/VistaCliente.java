package vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VistaCliente extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public JButton gestionaBD,muestraBD;
	public JLabel escoge;
	
	public VistaCliente() {
		getContentPane().setLayout(null);
		
		
		
		escoge= new JLabel("Escoge una opción");
		escoge.setBounds(171, 75, 95, 20);
		gestionaBD= new JButton("Gestiona BD");
		gestionaBD.setBounds(87, 106, 95, 49);
		muestraBD= new JButton("Muestra BD");
		muestraBD.setBounds(256, 106, 95, 49);

		
		getContentPane().add(escoge);
		getContentPane().add(gestionaBD);
		getContentPane().add(muestraBD);	
		
	}
	
}
