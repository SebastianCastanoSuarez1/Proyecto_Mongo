package view;

import java.util.List;
import java.util.Optional;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import model.Paciente;

public class PacienteView {

	public Document anadirPacientenuevo(String dni, String nombre, String apellidos, String fechaNacimiento,
			String sexo, String lugarNacimiento, String altura, String peso, String grupoSanguineo, String enfermedad,
			String tipo) {
		Document paciente;
		paciente = new Paciente().append("Dni", dni).append("Nombre", nombre).append("Apellidos", apellidos)
				.append("Fecha_Nacimiento", fechaNacimiento).append("Sexo", sexo)
				.append("Lugar_Nacimiento", lugarNacimiento).append("Altura", altura).append("Peso", peso)
				.append("Grupo_Sanguineo", grupoSanguineo).append("Enfermedad", enfermedad).append("Tipo", tipo);
		return paciente;
	}

	public String pretty(String json) {
		JsonElement je = JsonParser.parseString(json);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(je);
	}

	public String mostrarPacientes(List<Document> pacientes) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String pretty = "";
		if (pacientes.isEmpty()) {

		} else {
			for (Document doc : pacientes) {
				String json = gson.toJson(doc);

				pretty += pretty(json) + "\n";
			}
		}
		return pretty;
	}

	public String mostrar(Optional<Document> pacientes) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String pretty = "";
		if (pacientes.isEmpty()) {

		} else {
			Document doc1 = pacientes.get();
			String json = gson.toJson(doc1);
			pretty += pretty(json) + "\n";
		}
		return pretty;
	}
}