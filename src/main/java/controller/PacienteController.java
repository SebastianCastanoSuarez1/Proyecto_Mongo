package controller;

import java.util.List;
import java.util.Optional;

import org.bson.Document;

import repositories.PacientesRepositories.PacienteRepositoryImpl;
import view.PacienteView;

public class PacienteController {
	private PacienteRepositoryImpl pacienteRepositoryImpl;
	private PacienteView pacienteView;
	private String dni = "Dni", nombre = "Nombre", apellidos = "Apellido", fechaNacimiento = "Fecha_Nacimiento",
			sexo = "Sexo", lugarNacimiento = "Lugar_Nacimiento", altura = "Altura", peso = "Peso",
			grupo_Sanguineo = "Grupo_Sanguineo", enfermedad = "Enfermedad", tipo = "Tipo";

	public void menu() {
		boolean fin = false;
		Integer opcion;
	}

	private void mostrarPacienteByDni() {
		pacienteView.mostrar(findByDni());
	}

	private void getAllPacientes() {
		pacienteRepositoryImpl = new PacienteRepositoryImpl();
		List<Document> pacientes = pacienteRepositoryImpl.findAll();
		String prettyJson;
		pacienteView = new PacienteView();
		for (Document paciente : pacientes) {
			prettyJson = pacienteView.pretty(paciente.toJson());
			System.out.println(prettyJson);
		}
	}

	public Optional<Document> findByDni() {
		String dni = pacienteView.findByDNI();
		Optional<Document> paciente = pacienteRepositoryImpl.findById(dni).or(null);
		return paciente;
	}

	public void addPaciente() {
		Document nuevoPaciente = pacienteView.addPaciente();
		pacienteRepositoryImpl = new PacienteRepositoryImpl();
		Boolean guardado = pacienteRepositoryImpl.save(nuevoPaciente);
		pacienteView.mostrar(guardado ? "El paciente ha sido guardado correctamente" : "El paciente no se ha guardado");
	}

	public void deletePaciente() {
		String dni = pacienteView.findByDNI();
		Boolean borrado = pacienteRepositoryImpl.delete(dni);
		pacienteView.mostrar(borrado ? "El paciente ha sido borrado correctamente" : "El paciente no se ha borrado");
	}

	public void buscarPorNombre() {
		String nombre = pacienteView.findByNombre();
		List<Document> pacientes = pacienteRepositoryImpl.findByNombre(nombre);
		boolean vacio = pacientes.isEmpty();
		pacienteView.mostrar(vacio ? mostrarPrettyListaNombres(nombre, pacientes)
				: "No se encontraron pacientes con el nombre: " + nombre);
		if (pacientes.isEmpty()) {
			System.out.println("No se encontraron pacientes con el nombre: " + nombre);
		} else {
			mostrarPrettyListaNombres(nombre, pacientes);
		}
	}

	private String mostrarPrettyListaNombres(String nombre, List<Document> pacientes) {
		StringBuilder prettyJson = new StringBuilder();
		prettyJson.append("Pacientes con el nombre ").append(nombre).append(":\n");
		for (Document paciente : pacientes) {
			prettyJson.append(pacienteView.pretty(paciente.toJson())).append("\n");
		}
		return prettyJson.toString();
	}

}
