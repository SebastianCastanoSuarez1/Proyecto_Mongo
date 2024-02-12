package controller;

import java.util.List;
import java.util.Optional;

import org.bson.Document;

import model.Paciente;
import repositories.PacientesRepositories.PacienteRepositoryImpl;
import view.PacienteView;

public class Controller_Interfaz {
	private final PacienteRepositoryImpl pacienteRepositoryImpl = new PacienteRepositoryImpl();
	private final PacienteView pacienteView = new PacienteView();
	
	public Optional<Document> findByDni(String dni) {
		Optional<Document> paciente = pacienteRepositoryImpl.findById(dni);
		return paciente;
	}
	
	public String getAllPacientes() {
		List<Document> pacientes = pacienteRepositoryImpl.findAll();
		if (pacientes.isEmpty()) {
			return "No se encontraron pacientes.";
		} else {
			return pacienteView.mostrarPacientes(pacientes);
		}
		
	}
	
	public String findPacienteByDNombre(String nombre) {
		List<Document> pacientes = pacienteRepositoryImpl.findByNombre(nombre);
		return pacienteView.mostrarPacientes(pacientes);
	}
	
	public Optional<Document> comprobarDni(String dni){
		Optional<Document> pacientes = pacienteRepositoryImpl.findById(dni);
		return pacientes;
	}
	public String valorAtributoNuevo(String dni,String atributo, String valor) {
		Optional<Document> pacientes;
		
		pacientes = pacienteRepositoryImpl.findById(dni);
		Boolean actualizado = pacienteRepositoryImpl.update(pacientes, atributo, valor);
		return actualizado ? "El paciente ha sido actualizado correctamente" : "El paciente no se ha actualizado";
	}
	
	public Document anadirPacientenuevo(String dni, String nombre, String apellidos
			, String fechaNacimiento, String sexo, String lugarNacimiento, 
			String altura, String peso, String grupoSanguineo, String enfermedad, String tipo) {
		Document paciente;
		paciente = new Paciente().append("Dni", dni).append("Nombre", nombre).append("Apellidos", apellidos)
				.append("Fecha_Nacimiento", fechaNacimiento).append("Sexo", sexo)
				.append("Lugar_Nacimiento", lugarNacimiento).append("Altura", altura).append("Peso", peso)
				.append("Grupo_Sanguineo", grupoSanguineo).append("Enfermedad", enfermedad).append("Tipo", tipo);
		
		return paciente;
	}
	
	public Boolean salvarPaciente(Document paciente) {
		return pacienteRepositoryImpl.save(paciente);
	}
	
	

	public String mostrar(Optional<Document> paciente) {
		return paciente.orElse(null) + "";
	}

	public String mostrar(String mensaje) {
		return mensaje;
	}
	
	public Boolean actualizarPaciente(Optional<Document> pacientes, String nombreAtributo, String valorAtributo) {
		return pacienteRepositoryImpl.update(pacientes,nombreAtributo, valorAtributo);
	}

	public Boolean eliminarPaciente(String dni) {
		return pacienteRepositoryImpl.delete(dni);
	}

}
