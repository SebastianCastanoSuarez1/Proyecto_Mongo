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
	private JTextField textFieldEscribir;
	private JButton btnCancelar;
	VentanaPrincipal vp;
	JScrollPane scrollPaneMostrar;
	JTextArea textAreaMostrar;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblMensaje = new JLabel("Introduzca el DNI\r\n");
		lblMensaje.setVisible(false);
		lblMensaje.setBounds(235, 31, 125, 13);
		contentPane.add(lblMensaje);

		textFieldEscribir = new JTextField();
		textFieldEscribir.setVisible(false);
		textFieldEscribir.setBounds(235, 54, 167, 23);
		contentPane.add(textFieldEscribir);
		textFieldEscribir.setColumns(10);

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
					textFieldEscribir.setVisible(true);
					textFieldEscribir.setText("");
					textAreaMostrar.setText("");
					textFieldEscribir.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String nombre = textFieldEscribir.getText();
							if (!nombre.equals("")) {
								lblMensaje.setVisible(false);
								textFieldEscribir.setVisible(false);
								
								textAreaMostrar.setText(controllerInterfaz.findPacienteByDNombre(nombre));

							}else {
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
				textFieldEscribir.setVisible(true);
				textFieldEscribir.setText("");
				textAreaMostrar.setText("");
				textFieldEscribir.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String dni = textFieldEscribir.getText();
						if (dni.matches("^\\d{8}[A-Z]$")) {
							lblMensaje.setVisible(false);
							textFieldEscribir.setVisible(false);
							Optional<Document> pacientes = controllerInterfaz.findByDni(dni);
							if(pacientes.isPresent()) {
								
								textAreaMostrar.setText(controllerInterfaz.mostrar(pacientes));
							}else {
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
				vp = new VentanaPrincipal();
				vp.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(20, 208, 149, 21);
		contentPane.add(btnCancelar);
		
		scrollPaneMostrar = new JScrollPane();
		scrollPaneMostrar.setBounds(207, 11, 227, 239);
		contentPane.add(scrollPaneMostrar);
		
		textAreaMostrar = new JTextArea();
		scrollPaneMostrar.setViewportView(textAreaMostrar);

	}
}
