package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.bson.Document;
import com.mongodb.client.result.DeleteResult;
import model.Paciente;
import repositories.MedicoRepository.MedicoRepositoryImpl;


public class MedicoController_Interfaz {
	private final MedicoRepositoryImpl  medicoRepositoryImpl = new MedicoRepositoryImpl();
	
	public Optional<Document> findByDni(String dni){
		Optional<Document> medico = medicoRepositoryImpl.findById(dni);
		
		return medico;
		
	}
	
	public String getAllMedicos() {
		List<Document> medicos = medicoRepositoryImpl.findAll();
		if (medicos.isEmpty()) {
			return "No se encontraron pacientes.";
		} else {
			return medicoRepositoryImpl.mostrarMedicos(medicos);
		}
	}
	
	public String findMedicoByNombre(String nombre) {
		List<Document> medico = medicoRepositoryImpl.findByNombre(nombre);
		return medicoRepositoryImpl.mostrarMedicos(medico);
	}
	
	public Optional<Document> comprobarDni(String dni){
		Optional<Document> medicos= medicoRepositoryImpl.findById(dni);
		return medicos;
	}
	
	public String valorAtributoNuevo(String dni,String atributo, String valor) {
		Optional<Document> medicos;
		
		medicos = medicoRepositoryImpl.findById(dni);
		Boolean actualizado = medicoRepositoryImpl.update(medicos, atributo, valor);
	    
		return actualizado ? "El paciente ha sido actualizado correctamente" : "El paciente no se ha actualizado";
	}
	
	public Document anadirMedicoNuevo(String dni, String nombre, String apellidos, String especialidad,
			String anio_experiencia) {
		Document paciente;
		paciente = new Paciente().append("Dni", dni).append("Nombre", nombre).append("Apellidos", apellidos)
				.append("Especialidad", especialidad).append("Año_Experiencia", anio_experiencia);
				

		return paciente;
		
	}
	
	public Boolean salvarMedico(Document medico) {
		return medicoRepositoryImpl.save(medico);
	}
	
	public String findMedicoByAttribute(String atributo, String valor) {
		List<Document> medicos = medicoRepositoryImpl.findByAttribute(atributo, valor);
		return medicoRepositoryImpl.mostrarMedicos(medicos);
	}
	
	public String mostrar(List<Document> medicos) {
		String ensenar = medicoRepositoryImpl.mostrarMedicos(medicos);
		return ensenar;
	}

	public String mostrar(String mensaje) {
		return mensaje;
	}
	
	public Boolean crearPacientesCargo(Optional<Document> medicos, String[] dni_Paciente) {
		Document contenido = new Document();
		Document pacientes_Cargo = new Document();
		List<String> DniPacientes_List = Arrays.asList(dni_Paciente);
		
		contenido.append("Dni_Pacientes", DniPacientes_List);
		pacientes_Cargo.append("Pacientes_Cargo", contenido);
		return medicoRepositoryImpl.updatePacientesCargo(medicos, pacientes_Cargo);
	}
	
	public Boolean actualizarMedico(Optional<Document> medicos, String nombreAtributo, String valorAtributo) {
		return medicoRepositoryImpl.update(medicos, nombreAtributo, valorAtributo);
	}
	
	public DeleteResult eliminarMedico(String dni) {
		return medicoRepositoryImpl.delete(dni);
	}
	
	public Boolean anadirLista(Optional<Document> pacientes, String atributo, String[] lista) {

		List<String> list = Arrays.asList(lista); // Convertir array en lista
		return medicoRepositoryImpl.update(pacientes, atributo, list);
	}
	
	public Boolean anadirComponente(String dni, String atributoComponente, String[] atributo, String[] valores,
			String[] atributoLista, ArrayList<String[]> listas) {
		Optional<Document> paciente = medicoRepositoryImpl.findById(dni);
		Document componente = new Document();
		anadirElementosComponente(atributo, valores, componente);
		anadirListaComponente(atributoLista, listas, componente);
		Boolean anadido = medicoRepositoryImpl.update(paciente, atributoComponente, componente);
		return anadido;
	}

	public Boolean anadirComponente(String dni, String atributoComponente, String[] atributo, String[] valores) {
		Optional<Document> paciente = medicoRepositoryImpl.findById(dni);
		Document componente = new Document();
		anadirElementosComponente(atributo, valores, componente);
		Boolean anadido = medicoRepositoryImpl.update(paciente, atributoComponente, componente);
		return anadido;
	}

	public Boolean anadirComponente(String dni, String atributoComponente, String[] atributoLista,
			ArrayList<String[]> listas) {
		Optional<Document> paciente = medicoRepositoryImpl.findById(dni);
		Document componente = new Document();
		anadirListaComponente(atributoLista, listas, componente);
		Boolean anadido = medicoRepositoryImpl.update(paciente, atributoComponente, componente);
		return anadido;
	}

	private void anadirElementosComponente(String[] atributo, String[] valores, Document paciente) {
		for (int i = 0; i < atributo.length; i++) {
			paciente.append(atributo[i], valores[i]);
		}
	}

	private void anadirListaComponente(String[] atributoLista, ArrayList<String[]> listas, Document paciente) {
	    for (int i = 0; i < listas.size(); i++) {
	        String[] listaActual = listas.get(i); // Obtener la lista actual del índice i
	        for (int j = 0; j < listaActual.length; j++) {
	            paciente.append(atributoLista[j], listaActual[j]); // Agregar el atributo y su valor correspondiente al documento
	        }
	    }
	}
	
}	

