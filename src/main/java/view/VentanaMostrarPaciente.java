package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.bson.Document;

import controller.Controller_Interfaz;

public class VentanaMostrarPaciente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Controller_Interfaz controllerInterfaz = new Controller_Interfaz();
	JButton btnMostrarTodo;
	JButton btnMostrarPorNombre;
	JButton btnMostrarPorDni;
	JLabel lblMensaje;
	private JTextField textFieldEscribirDni;
	private JButton btnCancelar;
	VentanaPrincipalPaciente vp;
	JScrollPane scrollPaneMostrar;
	JTextArea textAreaMostrar;
	private JTextField textFieldEscribirNombre;
	private JTextField textFieldAtributo;
	private JTextField textFieldValor;
	JButton btnBuscarAtributo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMostrarPaciente frame = new VentanaMostrarPaciente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaMostrarPaciente() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblMensaje = new JLabel("Introduzca el DNI\r\n");
		lblMensaje.setVisible(false);
		lblMensaje.setBounds(235, 11, 190, 13);
		contentPane.add(lblMensaje);

		textFieldEscribirDni = new JTextField();
		textFieldEscribirDni.setVisible(false);
		textFieldEscribirDni.setBounds(235, 35, 167, 23);
		contentPane.add(textFieldEscribirDni);
		textFieldEscribirDni.setColumns(10);

		btnMostrarTodo = new JButton("Mostrar todo");
		btnMostrarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnMostrarTodo == e.getSource()) {
					textAreaMostrar.setText("");
					textAreaMostrar.setText(controllerInterfaz.getAllPacientes());

				}
			}
		});
		btnMostrarTodo.setBounds(20, 43, 149, 23);
		contentPane.add(btnMostrarTodo);

		btnMostrarPorNombre = new JButton("Mostrar por nombre\r\n");
		btnMostrarPorNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnMostrarPorNombre == e.getSource()) {
					lblMensaje.setText("Introduzca el nombre");
					lblMensaje.setVisible(true);
					textFieldEscribirNombre.setVisible(true);
					textFieldEscribirNombre.setText("");
					textAreaMostrar.setText("");
					textFieldEscribirNombre.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String nombre = textFieldEscribirNombre.getText();
							if (!nombre.equals("")) {
								textAreaMostrar.setText(controllerInterfaz.findPacienteByDNombre(nombre));

							} else {
								textAreaMostrar.setText("El nombre debe de tener caracteres");
							}
						}
					});

				}
			}
		});
		btnMostrarPorNombre.setBounds(20, 160, 149, 23);
		contentPane.add(btnMostrarPorNombre);

		btnMostrarPorDni = new JButton("Mostrar por DNI\r\n");
		btnMostrarPorDni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMensaje.setText("Introduzca el DNI");
				lblMensaje.setVisible(true);
				textFieldEscribirDni.setVisible(true);
				textFieldEscribirDni.setText("");
				textAreaMostrar.setText("");
				textFieldEscribirDni.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String dni = textFieldEscribirDni.getText();
						if (dni.matches("^\\d{8}[A-Z]$")) {
							lblMensaje.setVisible(false);
							textFieldEscribirDni.setVisible(false);
							Optional<Document> pacientes = controllerInterfaz.findByDni(dni);

							if (pacientes.isPresent()) {
								textAreaMostrar.setText(controllerInterfaz.mostrar(pacientes));
							} else {
								textAreaMostrar.setText("El paciente con DNI " + dni + " no existe");
							}
						} else {
							textAreaMostrar.setText("El dni debe tener este formato :00000000A ");
						}
					}
				});

			}
		});
		btnMostrarPorDni.setBounds(20, 101, 149, 23);
		contentPane.add(btnMostrarPorDni);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp = new VentanaPrincipalPaciente();
				vp.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(20, 273, 149, 21);
		contentPane.add(btnCancelar);

		scrollPaneMostrar = new JScrollPane();
		scrollPaneMostrar.setBounds(207, 91, 296, 259);
		contentPane.add(scrollPaneMostrar);

		textAreaMostrar = new JTextArea();
		scrollPaneMostrar.setViewportView(textAreaMostrar);
		
		textFieldEscribirNombre = new JTextField();
		textFieldEscribirNombre.setVisible(false);
		textFieldEscribirNombre.setBounds(235, 36, 167, 20);
		contentPane.add(textFieldEscribirNombre);
		textFieldEscribirNombre.setColumns(10);
		
		textFieldAtributo = new JTextField();
		
		textFieldAtributo.setVisible(false);
		textFieldAtributo.setBounds(235, 36, 167, 20);
		contentPane.add(textFieldAtributo);
		textFieldAtributo.setColumns(10);
		
		textFieldValor = new JTextField();
		textFieldValor.setVisible(false);
		textFieldValor.setBounds(235, 35, 167, 23);
		contentPane.add(textFieldValor);
		textFieldValor.setColumns(10);
		
		btnBuscarAtributo = new JButton("Buscar por atributo");
		btnBuscarAtributo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMensaje.setText("Introduzca el nombre del atributo");
				lblMensaje.setVisible(true);
				textFieldAtributo.setText("");
				textAreaMostrar.setText("");
				textFieldEscribirDni.setVisible(false);
				textFieldEscribirNombre.setVisible(false);
				textFieldValor.setVisible(false);
				textFieldAtributo.setVisible(true);
				textFieldAtributo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String atributo = textFieldAtributo.getText();
						if (!atributo.matches(" ")) {
							lblMensaje.setText("Introduzca el valor del atributo");
							textFieldValor.setText("");
							textFieldValor.setVisible(true);
							textFieldAtributo.setVisible(false);
							textFieldValor.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									String valor = textFieldValor.getText();
									if(!valor.matches(" ")) {
										
										textAreaMostrar.setText(controllerInterfaz.findPacienteByAttribute(atributo, valor));
									}else {
										textAreaMostrar.setText("El valor debe tener este caracteres");
									}
									
								}
							});


						} else {
							textAreaMostrar.setText("El atributo debe tener este caracteres");
						}
					}
				});
				
			}
		});
		btnBuscarAtributo.setBounds(20, 222, 149, 21);
		contentPane.add(btnBuscarAtributo);

	}
}
