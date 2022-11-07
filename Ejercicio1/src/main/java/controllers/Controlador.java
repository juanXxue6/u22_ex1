package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.DB_model;

public class Controlador implements ActionListener {

	private DB_model modelo;
	
	
	public Controlador(DB_model modelo) {
		this.modelo = modelo;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
