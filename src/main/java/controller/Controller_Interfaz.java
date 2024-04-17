package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.Document;

import com.mongodb.client.result.DeleteResult;

import model.Paciente;
import repositories.PacientesRepositories.PacienteRepositoryImpl;
import view.paciente.PacienteView;

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

	public Optional<Document> comprobarDni(String dni) {
		
		Optional<Document> pacientes = pacienteRepositoryImpl.findById(dni);
		return pacientes;
	}

	public String anadirDniMedico(String[] dniPacientes,String dni) {
	    Boolean actualizado = false;
	    for (int i = 0; i < dniPacientes.length; i++) {
	        Optional<Document> paciente = pacienteRepositoryImpl.findById(dniPacientes[i]);
	        if (paciente.isPresent()) {
	            actualizado = pacienteRepositoryImpl.update(paciente, "Dni_Medico", dni);
	            if (!actualizado) {
	                return "El paciente con DNI " + dniPacientes[i] + " no se ha actualizado";
	            }
	        } else {
	            return "El paciente con DNI " + dniPacientes[i] + " no se ha encontrado";
	        }
	    }
	    return "Todos los pacientes han sido actualizados correctamente";
	}

	public String valorAtributoNuevo(String dni, String atributo, String valor) {
		Optional<Document> pacientes;

		pacientes = pacienteRepositoryImpl.findById(dni);
		Boolean actualizado = pacienteRepositoryImpl.update(pacientes, atributo, valor);
		return actualizado ? "El paciente ha sido actualizado correctamente" : "El paciente no se ha actualizado";
	}

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

	public Boolean salvarPaciente(Document paciente) {
		return pacienteRepositoryImpl.save(paciente);
	}

	public String findPacienteByAttribute(String atributo, String valor) {
		List<Document> pacientes = pacienteRepositoryImpl.findByAttribute(atributo, valor);
		return pacienteView.mostrarPacientes(pacientes);
	}

	public String mostrar(Optional<Document> pacientes) {
		String ensenar = pacienteView.mostrar(pacientes);
		return ensenar;
	}

	public String mostrar(String mensaje) {
		return mensaje;
	}

	public Boolean anadirVariables(Optional<Document> pacientes, String[] alergeno, String[] medicamento,
			Document enfermedades) {
		Document contenido = new Document();
		Document historialMedico = new Document();
		List<String> alergenosList = Arrays.asList(alergeno); // Convertir array en lista
		List<String> medicamentosList = Arrays.asList(medicamento); // Convertir array en lista
		contenido.append("Alergenos", alergenosList);
		contenido.append("Medicamentos", medicamentosList);
		contenido.append("Enfermedades", enfermedades);
		historialMedico.append("Historial_Medico", contenido);
		return pacienteRepositoryImpl.updateHistorialMedico(pacientes, historialMedico);
	}

	public Document crearDocumentoEnfermedades(Optional<Document> pacientes, String enfermedad, String fecha,
			String[] historialMedicoMedicamentos, String tratamiento, String informe) {
		Document enfermedades = new Document();
		Document detalles = new Document();

		enfermedades.append("Enfermedad", enfermedad);
		enfermedades.append("Fecha", fecha);

		List<String> medicamentosList = Arrays.asList(historialMedicoMedicamentos);

		detalles.append("Tratamiento", tratamiento);
		detalles.append("Medicamentos", medicamentosList);
		detalles.append("Informe", informe);
		enfermedades.append("Detalles", detalles);
		return enfermedades;
	}

	public Boolean agregarEnfermedad(Optional<Document> pacientes, Document enfermedades) {

		return pacienteRepositoryImpl.updateHistorialMedico(pacientes, enfermedades);

	}

	public Boolean anadirMedicamentosRecientes(Optional<Document> pacientes, String[] medicamento) {
		Document medicamentos = new Document();
		List<String> alergenosList = Arrays.asList(medicamento); // Convertir array en lista
		medicamentos.append("Alergenos", alergenosList);
		return pacienteRepositoryImpl.updateHistorialMedico(pacientes, medicamentos);
	}

	public Boolean anadirAlergenos(Optional<Document> pacientes, String[] alergeno) {
		Document alergenos = new Document();
		List<String> alergenosList = Arrays.asList(alergeno); // Convertir array en lista
		alergenos.append("Alergenos", alergenosList);
		return pacienteRepositoryImpl.updateHistorialMedico(pacientes, alergenos);
	}

	public Boolean actualizarPaciente(Optional<Document> pacientes, String nombreAtributo, String valorAtributo) {
		return pacienteRepositoryImpl.update(pacientes, nombreAtributo, valorAtributo);
	}

	public DeleteResult eliminarPaciente(String dni) {
		return pacienteRepositoryImpl.delete(dni);
	}

	public Boolean anadirLista(Optional<Document> pacientes, String atributo, String[] lista) {
		List<String> alergenosList = Arrays.asList(lista); // Convertir array en lista
		return pacienteRepositoryImpl.update(pacientes, atributo, alergenosList);
	}

	public Boolean anadirComponente(String dni, String atributoComponente, String[] atributo, String[] valores,
			String[] atributoLista, ArrayList<String[]> listas) {
		Optional<Document> paciente = pacienteRepositoryImpl.findById(dni);
		Document componente = new Document();
		anadirElementosComponente(atributo, valores, componente);
		anadirListaComponente(atributoLista, listas, componente);
		Boolean anadido = pacienteRepositoryImpl.update(paciente, atributoComponente, componente);
		return anadido;
	}

	public Boolean anadirComponente(String dni, String atributoComponente, String[] atributo, String[] valores) {
		Optional<Document> paciente = pacienteRepositoryImpl.findById(dni);
		Document componente = new Document();
		anadirElementosComponente(atributo, valores, componente);
		Boolean anadido = pacienteRepositoryImpl.update(paciente, atributoComponente, componente);
		return anadido;
	}

	public Boolean anadirComponente(String dni, String atributoComponente, String[] atributoLista,
			ArrayList<String[]> listas) {
		Optional<Document> paciente = pacienteRepositoryImpl.findById(dni);
		Document componente = new Document();
		anadirListaComponente(atributoLista, listas, componente);
		Boolean anadido = pacienteRepositoryImpl.update(paciente, atributoComponente, componente);
		return anadido;
	}

	private void anadirElementosComponente(String[] atributo, String[] valores, Document paciente) {
		for (int i = 0; i < atributo.length; i++) {
			paciente.append(atributo[i], valores[i]);
		}
	}

	private void anadirListaComponente(String[] atributoLista, ArrayList<String[]> listas, Document paciente) {
	    for (int i = 0; i < listas.size(); i++) { // Corregir el límite del bucle
	        String[] listaActual = listas.get(i); // Obtener la lista actual del índice i
	        for (int j = 0; j < atributoLista.length && j < listaActual.length; j++) {
	            paciente.append(atributoLista[j], Arrays.asList(listaActual));
	        }
	    }
	}

}
