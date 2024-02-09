package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import io.IO;
import model.Paciente;

public class PacienteView {
	final List<String> opciones = List.of("1. Mostrar Paciente por DNI", "2. Añadir paciente",
			"3. Mostrar todos los Pacientes", "4. Eliminar paciente", "5. Actualizar Paciente", "6. Buscar por nombre",
			"7. Atributo nuevo", "0. Salir");

	public int getOpcion() {
		IO.println("Opciones: ");
		for (String opcion : opciones) {
			IO.println(opcion);
		}
		return IO.readInt();
	}

	public Document addPaciente() {
		Document paciente = anadirVariablesObligatorias();
		Document hisorialMedico = new Document();
		anadirVariables(hisorialMedico);
		paciente.append("Historial_Medico", hisorialMedico);
		IO.println("Quieres añadir algun atributo nuevo?(s/n)");
		char opcionAtributoNuevo = IO.readChar();
		if (opcionAtributoNuevo == 's') {
			atributoNuevo(paciente);
		}
		return paciente;
	}

	private void anadirVariables(Document hisorialMedico) {
		List<Document> enfermedades = new ArrayList<>();
		IO.println("¿Desea ingresar el historial médico? (s/n)");
		char opcionHistorialMedico = IO.readChar();
		if (opcionHistorialMedico == 's') {
			anadirAlergenos(hisorialMedico);
			anadirMedicamentosRecientes(hisorialMedico);
			anadirEnfermedades(hisorialMedico, enfermedades);
		}

	}

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

	private void anadirMedicamentosRecientes(Document hisorialMedico) {
		List<String> medicamentosRecientes;
		IO.println("¿Desea ingresar al historial médico los medicamentos recientes? (s/n)");
		char opcionMedicamentosRecientes = IO.readChar();
		if (opcionMedicamentosRecientes == 's') {
			medicamentosRecientes = IO.readList();
			hisorialMedico.append("Medicamentos_Recientes", medicamentosRecientes);

		}
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

	private Document anadirVariablesObligatorias() {
		String dni = "", nombre = "", apellidos = "", fechaNacimiento = "", sexo = "", lugarNacimiento = "",
				grupo_Sanguineo = "", enfermedad = "", tipo = "";
		int altura, peso;
		Document paciente;
		IO.println("Ingrese el dni del nuevo paciente:");
		dni = IO.readDni();
		IO.println("Ingrese el nombre paciente:");
		nombre = IO.readNombre();
		IO.println("Ingrese el apellidos paciente:");
		apellidos = IO.readNombre();
		IO.println("Ingrese el fecha de nacimiento paciente:");
		fechaNacimiento = IO.readFecha();
		IO.println("Ingrese el sexo paciente:");
		sexo = IO.readSexo();
		IO.println("Ingrese el lugar de nacimiento paciente:");
		lugarNacimiento = IO.readString();
		IO.println("Ingrese la Altura paciente:");
		altura = IO.readInt();
		IO.println("Ingrese el peso paciente:");
		peso = IO.readInt();
		IO.println("Ingrese el grupo sanguineo paciente:");
		grupo_Sanguineo = IO.readNombre();
		IO.println("Ingrese la enfermedad paciente:");
		enfermedad = IO.readNombre();
		IO.println("Ingrese el tipo de enfermedad del paciente:");
		tipo = IO.readNombre();
		paciente = new Paciente().append("Nombre", nombre).append("DNI", dni).append("Apellidos", apellidos)
				.append("Fecha de Nacimiento", fechaNacimiento).append("Sexo", sexo)
				.append("Lugar de Nacimiento", lugarNacimiento).append("Altura", altura).append("Peso", peso)
				.append("Grupo Sanguineo", grupo_Sanguineo).append("Enfermedad", enfermedad).append("Tipo", tipo);
		return paciente;
	}

	private void atributoNuevo(Document paciente) {
		do {
			IO.println("¿Desea ingresar un atributo-valor nuevo? (s/n)");
			char opcionNuevoAtributoValor = IO.readChar();
			if (opcionNuevoAtributoValor == 's') {
				IO.println("Ingrese el nombre del nuevo atributo:");
				String Atributo = IO.readString();
				IO.println("Ingrese el valor del nuevo atributo:");
				String Valor = IO.readString();
				IO.println("Nuevo atributo ingresado: " + Atributo + " con valor: " + Valor);
				paciente.append(Atributo, Valor);
			} else {
				break;
			}
		} while (true);
	}

	public String[] buscarPorValor() {
		String[] busqueda = new String[2];
		IO.println("Ingrese el nombre del atributo por el cual desea buscar:");
		IO.println(" Dni");
		IO.println(" Nombre");
		IO.println(" Apellidos");
		IO.println(" Fecha_Nacimiento");
		IO.println(" Sexo");
		IO.println(" Lugar_Nacimiento");
		IO.println(" Altura");
		IO.println(" Peso");
		IO.println(" Grupo_Sanguineo");
		IO.println(" Historial_Medico");
		IO.println(" Enfermedad");
		IO.println(" Tipo");
		busqueda[0] = IO.readString();
		IO.println("Ingrese el valor del atributo:");
		busqueda[1] = IO.readString();
		return busqueda;
	}

	public String findByDNI() {
		IO.print("dni del paciente");
		return IO.readDni();
	}

	public String findByNombre() {
		IO.print("nombre del paciente");
		return IO.readNombre();
	}

	public String pretty(String json) {
		JsonElement je = JsonParser.parseString(json);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(je);
	}

	public void mostrarPacientes(List<Document> pacientes) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		for (Document doc : pacientes) {
			String json = gson.toJson(doc);
			System.out.println(pretty(json));
		}
	}

	public ArrayList<ArrayList<String>> update() {
		String valor, atributo;
		ArrayList<String> atributos = new ArrayList<>();
		ArrayList<String> valores = new ArrayList<>();
		ArrayList<ArrayList<String>> devolver = new ArrayList<>();
		do {
			System.out.println("Introduce el nombre del atributo que quieres actualizar (o 'salir' para terminar):");
			atributo = IO.readString();

			if (!atributo.equals("salir")) {
				System.out.println("Introduce el nuevo valor para " + atributo + ":");
				valor = IO.readString();
				atributos.add(atributo);
				valores.add(valor);
			}
		} while (!atributo.equals("salir"));
		devolver.add(atributos);
		devolver.add(valores);
		return devolver;
	}

	public void mostrar(String mensaje) {
		IO.println(mensaje);
	}

	public void mostrar(Optional<Document> depart) {
		IO.println(depart.orElse(null));
	}

}
