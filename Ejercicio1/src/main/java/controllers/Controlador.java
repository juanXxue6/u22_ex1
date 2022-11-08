package controllers;


import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import models.DB_model;
import vista.VistaCliente;
import vista.VistaGestion;
import vista.VistaMostrar;


public class Controlador implements ActionListener {

	private DB_model modelo;

	private VistaGestion vistaGestion;
	private VistaCliente vistaCliente;
	private VistaMostrar vistaMostrar;
	private String subQueryWhere = "";
	private String sbQuerySelect = "";
	ResultSet resultSet;
	
	String[]datos = new String[5];
	String[] campos = {"nombre","apellido","direccion","dni","fecha"};
	boolean isInsertAlredy = false;

	
	
	public Controlador(DB_model modelo, VistaMostrar vistaMostrar, VistaGestion vistaGestion , VistaCliente vistaCliente) {
		this.modelo = modelo;
		this.vistaCliente = vistaCliente;
		this.vistaGestion = vistaGestion;
		this.vistaMostrar = vistaMostrar;
		
		//listener de los botones de la vista cliente

		iniciarServicios();
		
	}
	
	
	private void iniciarServicios() {
		vistaCliente.gestionaBD.addActionListener(this);
		vistaCliente.muestraBD.addActionListener(this);
		
		//listener de los botones de la vista Gestion
		vistaGestion.bguarda.addActionListener(this);
		vistaGestion.batras.addActionListener(this);
		
		//listener de los botones de la vista Mostrar
		vistaMostrar.bapellido.addActionListener(this);
		//vistaMostrar.bdireccion.addActionListener(this);
		vistaMostrar.bdni.addActionListener(this);
		vistaMostrar.bnombre.addActionListener(this);
		vistaMostrar.bmuestratodo.addActionListener(this);
		vistaMostrar.bfecha.addActionListener(this);
		vistaMostrar.batras.addActionListener(this);
		
		modelo.startConnection();
		modelo.useDB("ud22_1");
	}

	public void iniciarVistaCliente() {

		vistaCliente.setTitle("Vista cliente");
		vistaCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vistaCliente.setLocationRelativeTo(null);
		vistaCliente.setVisible(true);
		
	}
	
	public void iniciarVistaMostrar() {
		vistaMostrar.setTitle("Vista cliente");
		//vistaCliente.pack();
		vistaMostrar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vistaMostrar.setLocationRelativeTo(null);
		vistaMostrar.setVisible(true);


	}
	
	public void iniciarVistaGestion() {
		vistaGestion.setTitle("Vista cliente");
		//vistaCliente.pack();
		vistaGestion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vistaGestion.setLocationRelativeTo(null);
		vistaGestion.setVisible(true);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		

		
		//botones de vista cliente
		if(vistaCliente.gestionaBD == e.getSource()) {
			iniciarVistaGestion();
			System.out.println("a");
		}else if(vistaCliente.muestraBD == e.getSource()) {
			iniciarVistaMostrar();
			System.out.println("v");
			
			//boton vista gestion
		}else if(vistaGestion.bguarda == e.getSource()) {
			
			
			datos[0] = vistaGestion.tnombre.getText();
			datos[1] = vistaGestion.tapellido.getText();
			datos[2] = vistaGestion.tdireccion.getText();
			datos[3] = vistaGestion.tdni.getText();
			datos[4] = "STR_TO_DATE('"+vistaGestion.tfecha.getText()+"', '%d/%m/%Y')";
			

			subQueryWhere = " where " +  campos[3] +  " = " + datos[3];
			resultSet = modelo.getData("cliente", "dni", subQueryWhere);
			

			try {
				isInsertAlredy = resultSet.next();
				System.out.println(isInsertAlredy);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
				try {
					if(!isInsertAlredy) {
						try {
							//System.out.println(exist.next());
						modelo.insertData(datos, "cliente");
						JOptionPane.showMessageDialog(null, "Datos insertados correctamente");
						}catch (Exception ex) {
							JOptionPane.showMessageDialog(null,"error al insertar:\n " + ex, "error de insercion", JOptionPane.ERROR_MESSAGE, null);
						}
					}
					else {
						try {

							modelo.updateData("cliente", "dni", datos[3], campos, datos);
							JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null,"error al actualizar:\n  " + ex, "error de insercion", JOptionPane.ERROR_MESSAGE, null);

						}

					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			
			
			
			
		}else if(vistaGestion.batras == e.getSource()) {
			iniciarVistaCliente();
			
			//botones de vista mostrar
		}else if(vistaMostrar.bnombre == e.getSource()) {
			
			showData(campos[0],null);
		} 
		
		else if(vistaMostrar.bapellido == e.getSource()) {
			showData(campos[1],null);
			
			
		} else if(vistaMostrar.bdireccion == e.getSource()) {
			showData(campos[2],null);
		
		}else if(vistaMostrar.bdni == e.getSource()) {
			
			showData(campos[3],null);
		}else if(vistaMostrar.bmuestratodo == e.getSource()) {
			
			showData(null, null);	
		}
		
		else if(vistaMostrar.bfecha == e.getSource()) {
			showData(campos[4],null);
		}
		
		else if(vistaMostrar.batras == e.getSource()) {
			iniciarVistaCliente();
		}

		
	}
	
	private void showData(String subConsultaSelect, String subConsultaWhere) {
		try {
			String enseñarLbl = "";
			
		resultSet = modelo.getData("cliente", subConsultaSelect, subConsultaWhere);
		System.out.println(resultSet.next());
		ResultSetMetaData rsmd = resultSet.getMetaData();			
		int columnsNumber = rsmd.getColumnCount();
		while (resultSet.next()) {
		    for (int i = 1; i <= columnsNumber; i++) {
		        if (i > 1) System.out.print(" | ");
		        String columnValue = resultSet.getString(i);
		        System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
		        enseñarLbl += rsmd.getColumnName(i) + ": " + columnValue + " | ";
		        
		        
		    }
	        enseñarLbl += "\n";
		    System.out.println("");
		}
		vistaMostrar.lresultado.setText(enseñarLbl);
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
