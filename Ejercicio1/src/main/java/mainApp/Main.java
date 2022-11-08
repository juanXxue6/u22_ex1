package mainApp;

import controllers.Controlador;
import models.DB_model;
import vista.VistaCliente;
import vista.VistaGestion;
import vista.VistaMostrar;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DB_model model = new DB_model("jdbc:mysql://192.168.1.190:3306","remote","");

		VistaGestion vg = new VistaGestion();
		VistaCliente vc = new VistaCliente();
		VistaMostrar vm = new VistaMostrar();
		Controlador controller = new Controlador(model,vm,vg,vc);
		controller.iniciarVistaCliente();

	}

}
