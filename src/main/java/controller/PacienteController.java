package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.Document;

import com.google.gson.Gson;

import repositories.PacientesRepositories.PacienteRepositoryImpl;

public class PacienteController {

	private final PacienteRepositoryImpl pacienteRepository;
	private final Gson gson;

	public PacienteController() {
		this.pacienteRepository = new PacienteRepositoryImpl();
		this.gson = new Gson();
	}

	public String getAllPacientes() {
		List<Document> pacientes = pacienteRepository.findAll();
		return gson.toJson(pacientes);
	}

	public String getPacienteByDni(String dni) {
		Optional<Document> paciente = pacienteRepository.findById(dni);
		return (String) paciente.map(document -> gson.toJson(document)).orElse("{}");
	}

	public String getPacientesByNombre(String nombre) {
		List<Document> pacientes = pacienteRepository.findAll();
		ArrayList<String> nombres = new ArrayList<>();

		for (Document document : pacientes) {
			String nombrePaciente = document.getString("Nombre");
			if (nombrePaciente != null && nombrePaciente.substring(0, nombre.length() - 1).equals(nombrePaciente)) {
				nombres.add(nombrePaciente);
			}
		}

		return new Gson().toJson(nombres);
	}

//TODO
	public String updatePaciente(String dni, String pacienteJson) {
		Optional<Document> existingPaciente = pacienteRepository.findById(dni);
		if (existingPaciente.isPresent()) {
			Document updatedPaciente = gson.fromJson(pacienteJson, Document.class);
			pacienteRepository.save(updatedPaciente);
			return "Paciente updated successfully";
		} else {
			return "Paciente not found";
		}
	}

	// TODO
	public String deletePacienteData(String dni, String dataJson) {
		Optional<Document> paciente = pacienteRepository.findById(dni);
		if (paciente.isPresent()) {
			return "Data deleted successfully";
		} else {
			return "Paciente not found";
		}
	}

	public String deletePaciente(String dni) {
		Optional<Document> paciente = pacienteRepository.findById(dni);
		if (paciente.isPresent()) {
			pacienteRepository.delete(paciente.get());
			return "Paciente deleted successfully";
		} else {
			return "Paciente not found";
		}
	}

	public String createPaciente(String pacienteJson) {
		Document newPaciente = gson.fromJson(pacienteJson, Document.class);
		boolean result = pacienteRepository.save(newPaciente);
		return result ? "Paciente created successfully" : "Failed to create Paciente";
	}

	// TODO
	public String addPacienteData(String dni, String newDataJson) {
		Optional<Document> paciente = pacienteRepository.findById(dni);
		if (paciente.isPresent()) {
			return "Data added successfully";
		} else {
			return "Paciente not found";
		}
	}
}
