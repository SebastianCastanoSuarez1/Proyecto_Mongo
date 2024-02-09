package controller;

import io.IO;

public class Controller {

	public Controller() {

		Integer opcion;
		boolean fin = false;

		PacienteController pacCon = new PacienteController();
		do {
			IO.println("1. menu paciente 0 salir");
			opcion = IO.readInt();
			switch (opcion) {
			case 1:
				pacCon.menu();
				break;
			case 0:
				fin = true;
				break;
			}
		} while (fin);

	}
}
