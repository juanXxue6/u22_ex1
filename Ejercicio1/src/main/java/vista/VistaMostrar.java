package vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class VistaMostrar extends JFrame {
	private static final long serialVersionUID = 1L;
	public JLabel lresultado;
	public JButton bmuestratodo,bnombre,bapellido,bdireccion,bdni,bfecha;
	
	public VistaMostrar() {
		getContentPane().setLayout(null);
		
		lresultado=new JLabel("Resultados");
		lresultado.setHorizontalAlignment(SwingConstants.CENTER);
		lresultado.setBounds(10,11, 414,117);
		
		bmuestratodo= new JButton("Muestra Todo");
		bmuestratodo.setBounds(29, 139, 117, 50);
		
		bnombre= new JButton("Muestra Nombres");
		bnombre.setBounds(156, 139, 117, 50);
		
		bapellido= new JButton("Muestra Apellidos");
		bapellido.setBounds(283, 139, 117, 50);
		
		bdni= new JButton("Muestra DNI");
		bdni.setBounds(108, 200, 117, 50);
		
		bfecha= new JButton("Muestra Fecha");
		bfecha.setBounds(235, 200, 117, 50);
		
		getContentPane().add(lresultado);
		getContentPane().add(bmuestratodo);
		getContentPane().add(bnombre);
		getContentPane().add(bapellido);
		getContentPane().add(bdni);
		getContentPane().add(bfecha);
		
	}

}
