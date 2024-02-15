package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.Document;

import io.IO;
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
	
//	public void anadirVariables(Document hisorialMedico) {
//		List<Document> enfermedades = new ArrayList<>();
//		IO.println("¿Desea ingresar el historial médico? (s/n)");
//		char opcionHistorialMedico = IO.readChar();
//		if (opcionHistorialMedico == 's') {
//			anadirAlergenos(hisorialMedico);
//			anadirMedicamentosRecientes(hisorialMedico);
//			anadirEnfermedades(hisorialMedico, enfermedades);
//		}
//
//	}

	private void anadirEnfermedades(Document hisorialMedico, List<Document> enfermedades) {
		IO.println("¿Desea ingresar al historial médico las enfermedades que ha tenido en su vida? (s/n)");
		char opcionEnfermedades = IO.readChar();
		if (opcionEnfermedades == 's') {
			String input;
			System.out.println("Ingrese el número de enfermedades del paciente:");
			int numEnfermedades = IO.readInt();
			for (int i = 0; i < numEnfermedades; i++) {
				Document enfermedad = new Document();
				System.out.println("Ingrese la enfermedad " + (i + 1) + ":");
				input = IO.readNombre();
				enfermedad.append("Enfermedad", input);
				System.out.println("Ingrese la fecha de la enfermedad " + (i + 1) + ":");
				input = IO.readFecha();
				enfermedad.append("Fecha", input);
				System.out.println("Ingrese los detalles de la enfermedad " + (i + 1) + ":");
				Document detalles = new Document();
				System.out.println("Ingrese el tratamiento:");
				input = IO.readNombre();
				detalles.append("Tratamiento", input);
				System.out.println("Ingrese los medicamentos:");
				List<String> medicamentos = IO.readList();
				detalles.append("Medicamentos", medicamentos);
				System.out.println("Ingrese el informe:");
				input = IO.readNombre();
				detalles.append("Informe", input);
				enfermedad.append("Detalles", detalles);
				enfermedades.add(enfermedad);
			}
			hisorialMedico.append("Enfermedades", enfermedades);
		}
	}

	private Boolean anadirMedicamentosRecientes(Optional<Document> pacientes, String atributo, String valor) {
		return pacienteRepositoryImpl.update(pacientes,atributo, valor);


		
	}

	private void anadirAlergenos(Document hisorialMedico) {
		List<String> alergenos;
		IO.println("¿Desea ingresar al historial médico alergias? (s/n)");
		char opcionAlergias = IO.readChar();
		if (opcionAlergias == 's') {
			alergenos = IO.readList();
			hisorialMedico.append("Alergenos", alergenos);
		}
	}
	public Boolean actualizarPaciente(Optional<Document> pacientes, String nombreAtributo, String valorAtributo) {
		return pacienteRepositoryImpl.update(pacientes,nombreAtributo, valorAtributo);
	}

	public Boolean eliminarPaciente(String dni) {
		return pacienteRepositoryImpl.delete(dni);
	}

}
