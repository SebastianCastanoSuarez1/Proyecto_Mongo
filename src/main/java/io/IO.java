package io;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <p>
 * Clase estática para leer de teclado con comprobación de tipo de datos y
 * escribir en pantalla.
 * </p>
 * 
 * <p>
 * <b>USO EDUCATIVO</b>
 * </p>
 * 
 * <p>
 * Los tipos de dato que maneja son:
 * </p>
 * 
 * <ul>
 * <li>entero (int)
 * <li>decimal (double)
 * <li>caracter (char)
 * <li>byte
 * <li>short
 * <li>int
 * <li>long
 * <li>float
 * <li>double
 * <li>boolean (true, false)
 * <li>char
 * <li>String (admite tira vacía)
 * </ul>
 * 
 * @author Amadeo
 * @version 1.0
 * @since 2018-07-01
 */
public class IO {

	private static Scanner sc = new Scanner(System.in);

	/**
	 * Constructor
	 */
	IO() {
		sc.useDelimiter("\n");
	}

	/**
	 * Muestra un objeto
	 * 
	 * @param o objeto
	 */
	static public void print(Object o) {
		System.out.print(o);
	}

	/**
	 * Muestra un objeto y salta de l�nea
	 * 
	 * @param o objeto
	 */
	static public void println(Object o) {
		System.out.println(o);
	}

	/**
	 * Lee un valor de tipo byte
	 * 
	 * @return
	 */
	static public byte readByte() {
		while (true) {
			try {
				return Byte.parseByte(sc.nextLine());
			} catch (Exception e) {
				System.err.print("ERROR: No es de tipo byte ? ");
			}
		}
	}

	/**
	 * Lee un valor de tipo short
	 * 
	 * @return
	 */
	static public short readShort() {
		while (true) {
			try {
				return Short.parseShort(sc.nextLine());
			} catch (Exception e) {
				System.err.print("ERROR: No es de tipo short ? ");
			}
		}
	}

	/**
	 * Lee un valor de tipo int
	 * 
	 * @return
	 */
	static public int readInt() {
		while (true) {
			try {
				return Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.err.print("ERROR: No es de tipo int ? ");
			}
		}
	}

	/**
	 * Lee un valor de tipo long
	 * 
	 * @return
	 */
	static public long readLong() {
		while (true) {
			try {
				return Long.parseLong(sc.nextLine());
			} catch (Exception e) {
				System.err.print("ERROR: No es de tipo long ? ");
			}
		}
	}

	/**
	 * Lee un valor de tipo float
	 * 
	 * @return
	 */
	static public float readFloat() {
		while (true) {
			try {
				return Float.parseFloat(sc.nextLine());
			} catch (Exception e) {
				System.err.print("ERROR: No es de tipo float ? ");
			}
		}
	}

	/**
	 * Lee un valor de tipo double
	 * 
	 * @return
	 */
	static public double readDouble() {
		while (true) {
			try {
				return Double.parseDouble(sc.nextLine());
			} catch (Exception e) {
				System.err.print("ERROR: No es de tipo double ? ");
			}
		}
	}

	/**
	 * Lee un valor de tipo boolean
	 * 
	 * @return
	 */
	static public boolean readBoolean() {
		while (true) {
			switch (sc.nextLine()) {
			case "true":
				return true;
			case "false":
				return false;
			default:
				System.err.print("ERROR: No es de tipo boolean (true o false) ? ");
			}
		}
	}

	/**
	 * Lee un valor de tipo char
	 * 
	 * @return
	 */
	static public char readChar() {
		while (true) {
			String s = sc.nextLine();
			if (s.length() == 1) {
				return s.toCharArray()[0];
			}
			System.err.print("ERROR: No es de tipo char ? ");
		}
	}

	/**
	 * Lee un valor de tipo String
	 * 
	 * @return
	 */
	static public String readString() {
		return sc.nextLine();
	}

	/*
	 * Métodos para idioma español
	 *
	 * static public void escribir(Object o) { println(o); } static public void
	 * mostrar(Object o) { println(o); } static public int leerEntero() { return
	 * readInt(); } static public double leerDecimal() { return readDouble(); }
	 * static public char leerCaracter() { return readChar(); }
	 */
	public static Double readSalario() {
		while (true) {
			try {
				double salario;
				salario = Double.parseDouble(sc.nextLine());
				if (salario >= 0) {
					return salario;
				} else {
					throw new Exception("El salario debe ser mayor a 0");
				}
			} catch (Exception e) {
				System.err.print("ERROR: No es de tipo double ? ");
			}
		}
	}

	static public String readNombre() {
		while (true) {
			try {
				String nombre;
				nombre = sc.nextLine();
				if (nombre.length() > 0) {
					return nombre;
				} else {
					throw new Exception("El nombre debe tner caracteres");
				}
			} catch (Exception e) {
				System.err.print("ERROR: No hay caracteres");
			}
		}
	}

	static public List<String> readList() {
		List<String> list = new ArrayList<String>();
		String cadena;
		System.out.println("Ingrese los elementos de la lista (escriba 'fin' para terminar):");
		while (true) {
			try {
				cadena = sc.nextLine();
				if (cadena.equalsIgnoreCase("fin")) {
					break;
				} else if (cadena.length() > 0) {
					list.add(cadena);
				} else {
					throw new Exception("El elemento debe tener caracteres");
				}
			} catch (Exception e) {
				System.err.println("ERROR: No hay caracteres");
			}
		}
		return list;
	}

	static public String readSexo() {
		while (true) {
			try {
				String sexo;
				sexo = sc.nextLine();
				if (sexo.equalsIgnoreCase("Masculino") || sexo.equalsIgnoreCase("Femenino")) {
					sexo = sexo.substring(0, 1).toUpperCase() + sexo.substring(1, sexo.length() - 1).toLowerCase();
					return sexo;
				} else {
					throw new Exception("el sexo debe ser masculino y femenino");
				}
			} catch (Exception e) {
				System.err.print("ERROR: No hay caracteres");
			}
		}
	}

	static public String readFecha() {
		while (true) {
			try {
				String fecha;
				String regex = "^(0[1-9]|1[0-9]|2[0-9]|3[01])/(0[1-9]|1[012])/((19|20)\\d\\d)$";
				fecha = sc.nextLine();
				if (fecha.matches(regex)) {
					return fecha;
				} else {
					throw new Exception("la fecha debe tener este fomrato 00/00/0000");
				}
			} catch (Exception e) {
				System.err.print("ERROR: No tiene formato");
			}
		}
	}

	static public String readGrupoSanguineo() {
		while (true) {
			try {
				String Grupo_Sanguineo;
				String regex = "^(?i)(0|[AB]|AB)[+-]$";
				Grupo_Sanguineo = sc.nextLine();
				if (Grupo_Sanguineo.matches(regex)) {
					return Grupo_Sanguineo.toUpperCase();
				} else {
					throw new Exception("El grupo sanguineo debe ser 0+/-, ");
				}
			} catch (Exception e) {
				System.err.print("ERROR: No tiene formato");
			}
		}
	}

	static public String readDni() {
		while (true) {
			try {
				String nombre;
				String regex = "^\\d{8}[A-Z]$";
				nombre = sc.nextLine();
				if (nombre.matches(regex)) {
					return nombre;
				} else {
					throw new Exception("El dni debe tener este formato :00000000A");
				}
			} catch (Exception e) {
				System.err.print("ERROR: No hay caracteres");
			}
		}
	}
}