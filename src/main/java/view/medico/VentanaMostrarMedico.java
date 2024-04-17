package view.medico;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import org.bson.Document;

import controller.MedicoController_Interfaz;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Optional;
import java.awt.event.ActionEvent;

public class VentanaMostrarMedico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JFormattedTextField formattedDni;
	MaskFormatter mascara;
	private JTextField textFieldNombre;
	private JTextField textFieldAtributo;
	private JTextField textFieldValor;
	JButton btnMostrarTodo;
	JButton btnDNI;
	JButton btnNombre;
	JButton btnAtributo;
	JButton btnCancelar;
	JLabel lblMensaje;
	JScrollPane scrollPaneMostrar;
	VentanaPrincipalMedico vpm;
	JTextArea textAreaMostrar;
	MedicoController_Interfaz controllerMedico = new MedicoController_Interfaz();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMostrarMedico frame = new VentanaMostrarMedico();
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
	public VentanaMostrarMedico() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 542, 441);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnMostrarTodo = new JButton("Mostrar todo");
		btnMostrarTodo.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnMostrarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnMostrarTodo == e.getSource()) {
					textAreaMostrar.setText("");
					textAreaMostrar.setText(controllerMedico.getAllMedicos());

				}
			}
		});
		btnMostrarTodo.setBounds(30, 58, 149, 23);
		contentPane.add(btnMostrarTodo);
		
		btnDNI = new JButton("Mostrar por DNI");
		btnDNI.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnDNI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMensaje.setText("Introduzca el DNI");
				lblMensaje.setVisible(true);
				formattedDni.setVisible(true);
				formattedDni.setText("");
				textAreaMostrar.setText("");
				formattedDni.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String dni = formattedDni.getText();
						if (dni.matches("^\\d{8}[A-Z]$")) {
							lblMensaje.setVisible(false);
							formattedDni.setVisible(false);
							Optional<Document> medico = controllerMedico.findByDni(dni);

							if (medico.isPresent()) {
								textAreaMostrar.setText(controllerMedico.mostrar(medico));
							} else {
								textAreaMostrar.setText("El medico con DNI " + dni + " no existe");
							}
						} else {
							textAreaMostrar.setText("El dni debe tener este formato :00000000A ");
						}
					}
				});

			}
		});
		btnDNI.setBounds(30, 114, 151, 21);
		contentPane.add(btnDNI);
		
		btnNombre = new JButton("Mostrar por nombre");
		btnNombre.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMensaje.setText("Introduzca el nombre");
				lblMensaje.setVisible(true);
				formattedDni.setVisible(false);
				textFieldNombre.setVisible(true);
				textFieldNombre.setText("");
				textAreaMostrar.setText("");
				textFieldNombre.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String nombre = textFieldNombre.getText();
						if (!nombre.equals("")) {
							textAreaMostrar.setText(controllerMedico.findMedicoByNombre(nombre));

						} else {
							textAreaMostrar.setText("El nombre debe de tener caracteres");
						}
					}
				});
			}
		});
		btnNombre.setBounds(28, 169, 151, 21);
		contentPane.add(btnNombre);
		
		btnAtributo = new JButton("Mostrar por atributo");
		btnAtributo.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnAtributo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMensaje.setText("Introduzca el nombre del atributo");
				lblMensaje.setVisible(true);
				textFieldAtributo.setText("");
				formattedDni.setVisible(false);
				textFieldNombre.setVisible(false);
				textFieldValor.setVisible(false);
				textAreaMostrar.setText("");
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
										
										textAreaMostrar.setText(controllerMedico.findMedicoByAttribute(atributo, valor));
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
		btnAtributo.setBounds(30, 219, 151, 21);
		contentPane.add(btnAtributo);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vpm = new VentanaPrincipalMedico();
				vpm.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(28, 276, 151, 21);
		contentPane.add(btnCancelar);
		
		lblMensaje = new JLabel("Introduzca el DNI");
		lblMensaje.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMensaje.setBounds(260, 10, 185, 23);
		contentPane.add(lblMensaje);
		
		try {
			mascara = new MaskFormatter("########?");
			mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			formattedDni = new JFormattedTextField(mascara);
			formattedDni.setVisible(false);
			formattedDni.setBounds(252, 46, 209, 19);
			contentPane.add(formattedDni);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		textFieldNombre = new JTextField();
		textFieldNombre.setVisible(false);
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(254, 46, 207, 19);
		contentPane.add(textFieldNombre);
		
		textFieldAtributo = new JTextField();
		textFieldAtributo.setVisible(false);
		textFieldAtributo.setColumns(10);
		textFieldAtributo.setBounds(254, 46, 207, 19);
		contentPane.add(textFieldAtributo);
		
		textFieldValor = new JTextField();
		textFieldValor.setVisible(false);
		textFieldValor.setColumns(10);
		textFieldValor.setBounds(255, 46, 206, 19);
		contentPane.add(textFieldValor);
		
		scrollPaneMostrar = new JScrollPane();
		scrollPaneMostrar.setBounds(228, 87, 278, 307);
		contentPane.add(scrollPaneMostrar);
		
		textAreaMostrar = new JTextArea();
		scrollPaneMostrar.setViewportView(textAreaMostrar);
	}
}
