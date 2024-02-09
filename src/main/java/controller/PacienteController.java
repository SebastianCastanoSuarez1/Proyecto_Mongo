package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.Document;

import repositories.PacientesRepositories.PacienteRepositoryImpl;
import view.PacienteView;

public class PacienteController {
	private final PacienteRepositoryImpl pacienteRepositoryImpl = new PacienteRepositoryImpl();
	private final PacienteView pacienteView = new PacienteView();

	public void menu() {
		boolean fin = false;
		Integer opcion;

		do {
			opcion = pacienteView.getOpcion();
			switch (opcion) {
			case 1:
				mostrarPacienteByDni();
				break;
			case 2:
				addPaciente();
				break;
			case 3:
				getAllPacientes();
				break;
			case 4:
				deletePaciente();
				break;
			case 5:
				updatePaciente();
				break;
			case 6:
				buscarPorNombre();
				break;
			case 7:
				anadirDatoExtra();
				break;
			case 0:
				fin = true;
				break;
			default:
				System.out.println("Solo números entre 1 y 7");
			}
		} while (fin == false);
	}

	private void mostrarPacienteByDni() {
		pacienteView.mostrar(findByDni());
	}

	private void getAllPacientes() {
		List<Document> pacientes = pacienteRepositoryImpl.findAll();
		pacienteView.mostrar(pacientes.toString());
	}

	private Optional<Document> findByDni() {
		String dni = pacienteView.findByDNI();
		Optional<Document> paciente = pacienteRepositoryImpl.findById(dni);
		return paciente;
	}

	private void addPaciente() {
		Document nuevoPaciente = pacienteView.addPaciente();
		Boolean guardado = pacienteRepositoryImpl.save(nuevoPaciente);
		pacienteView.mostrar(guardado ? "El paciente ha sido guardado correctamente" : "El paciente no se ha guardado");
	}

	private void deletePaciente() {
		String dni = pacienteView.findByDNI();
		Boolean borrado = pacienteRepositoryImpl.delete(dni);
		pacienteView.mostrar(borrado ? "El paciente ha sido borrado correctamente" : "El paciente no se ha borrado");
	}

	private void updatePaciente() {
		Optional<Document> paciente = findByDni();
		ArrayList<ArrayList<String>> recogerDatos = pacienteView.update();
		ArrayList<String> atributos = recogerDatos.get(0);
		ArrayList<String> valores = recogerDatos.get(1);
		for (int i = 0; i < atributos.size(); i++) {
			Boolean actualizado = pacienteRepositoryImpl.update(paciente, atributos.get(i), valores.get(i));
			pacienteView.mostrar(
					actualizado ? "El paciente ha sido actualizado correctamente" : "El paciente no se ha actualizado");
		}
	}

	private void buscarPorNombre() {
	    String nombre = pacienteView.findByNombre();
	    List<Document> pacientes = pacienteRepositoryImpl.findByNombre(nombre);
	    boolean vacio = pacientes.isEmpty();
	    pacienteView.mostrar(vacio ? "No se encontraron pacientes con el nombre: " + nombre : pacientes.toString());
	}

	private void anadirDatoExtra() {
		Optional<Document> paciente = findByDni();
		ArrayList<ArrayList<String>> recogerDatos = pacienteView.update();
		ArrayList<String> atributos = recogerDatos.get(0);
		ArrayList<String> valores = recogerDatos.get(1);
		Boolean actualizado = pacienteRepositoryImpl.update(paciente, atributos.get(0), valores.get(0));
		pacienteView.mostrar(
				actualizado ? "El paciente ha sido actualizado correctamente" : "El paciente no se ha actualizado");
	}

//	private String mostrarPrettyListaNombres(String nombre, List<Document> pacientes) {
//		StringBuilder prettyJson = new StringBuilder();
//		prettyJson.append("Pacientes con el nombre ").append(nombre).append(":\n");
//		for (Document paciente : pacientes) {
//			prettyJson.append(pacienteView.pretty(paciente.toJson())).append("\n");
//		}
//		return prettyJson.toString();
//	}

}
