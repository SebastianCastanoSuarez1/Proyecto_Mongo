package view;

import java.util.List;

import org.bson.Document;

import io.IO;
import model.Paciente;

public class PacienteView {
	public Document addPaciente() {
		Document paciente, hisorialMedico = null;
		paciente = anadirVariablesObligatorias();
		anadirVariables(hisorialMedico);
		paciente.append("Historial_Medico", hisorialMedico);
		return paciente;
	}

	private void anadirVariables(Document hisorialMedico) {
		List<Document> enfermedades = null;
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
				List<String> medicamentos = IO.readList(); // Suponiendo que tienes un método para leer listas
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
		dni = IO.readNombre();
		IO.println("Ingrese el nombre paciente:");
		nombre = IO.readNombre();
		IO.println("Ingrese el apellidos paciente:");
		apellidos = IO.readNombre();
		IO.println("Ingrese el fecha de nacimiento paciente:");
		fechaNacimiento = IO.readFecha();
		IO.println("Ingrese el sexo paciente:");
		sexo = IO.readSexo();
		IO.println("Ingrese el lugar de nacimiento paciente:");
		lugarNacimiento = IO.readFecha();
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

	private Document atributoNuevo() {
		Document paciente = null;
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
		return paciente;
	}

	private void buscarPorValor() {
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
		String atributoBusqueda = IO.readString();
	}
	// TODO si puedes ve haciendo tanto el de buscarValor, y mira si puedes hacer lo
	// del atributo, pero con listas y con lo de enfermedades que era un objeto
	// dentro de otro
	

}
