package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import org.bson.Document;

import controller.Controller_Interfaz;

public class VentanaComponentesLista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	MaskFormatter mascara;
	JLabel lblDNI;
	JButton btnComprobar;
	JFormattedTextField formattedDni;
	private final Controller_Interfaz controllerInterfaz = new Controller_Interfaz();
	private JLabel lblNombreAtributo;
	private JTextField textFieldNombreAtributo;
	JLabel lblLista;
	JButton btnCancelar;
	JButton btnAceptar;
	VentanaAnadirComponente vac;
	private JTextField textFieldMensaje;
	JLabel lblAtributoPrincipal;
	private ArrayList<String[]> listaDeListas = new ArrayList<>();

	private TextArea textArea_Lista_de_listas;
	private JTextField textFieldAtributoPrincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaComponentesLista frame = new VentanaComponentesLista();
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
	public VentanaComponentesLista() {
		setResizable(false);
		setBackground(new Color(230, 230, 250));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 382);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDNI.setBounds(24, 10, 55, 40);
		contentPane.add(lblDNI);

		btnComprobar = new JButton("Comprobar");
		btnComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnComprobar == e.getSource()) {
					Optional<Document> pacientes = controllerInterfaz.findByDni(formattedDni.getText());

					if (pacientes.isPresent()) {
						lblAtributoPrincipal.setVisible(true);
						textFieldAtributoPrincipal.setVisible(true);
					} else {
						String mensaje = "El paciente con DNI " + formattedDni.getText() + " no existe ";
						JOptionPane.showMessageDialog(null, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnComprobar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnComprobar.setBounds(297, 19, 98, 27);
		contentPane.add(btnComprobar);

		try {
			mascara = new MaskFormatter("########?");
			mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			formattedDni = new JFormattedTextField(mascara);
			formattedDni.setBounds(91, 21, 148, 26);
			contentPane.add(formattedDni);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		lblNombreAtributo = new JLabel("Introduzca la lista de atributos");
		lblNombreAtributo.setVisible(false);
		lblNombreAtributo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombreAtributo.setBounds(10, 134, 213, 27);
		contentPane.add(lblNombreAtributo);

		textFieldNombreAtributo = new JTextField();
		textFieldNombreAtributo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String atributo = textFieldNombreAtributo.getText().toString();
				if (atributo.length() > 0) {
					lblLista.setVisible(true);
					textArea_Lista_de_listas.setVisible(true);
				} else {
					lblLista.setVisible(false);
					textArea_Lista_de_listas.setVisible(false);
				}

			}
		});
		textFieldNombreAtributo.setVisible(false);
		textFieldNombreAtributo.setBounds(233, 133, 182, 34);
		contentPane.add(textFieldNombreAtributo);
		textFieldNombreAtributo.setColumns(10);

		lblLista = new JLabel("Introduzca la lista de los valores\r\n");
		lblLista.setVisible(false);
		lblLista.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLista.setBounds(10, 198, 213, 27);
		contentPane.add(lblLista);

		btnCancelar = new JButton("Cancelar\r\n");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vac = new VentanaAnadirComponente();
				vac.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setBounds(91, 248, 103, 34);
		contentPane.add(btnCancelar);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String dni = formattedDni.getText().toString();
				String textoIngresado = textArea_Lista_de_listas.getText();
				String[] palabras = textoIngresado.split(",");
				ArrayList<String[]> listas = new ArrayList<String[]>();
				for (int i = 0; i < palabras.length; i++) {
					listas.add(palabras[i].split(" "));
				}
				String atributoLista[] = textFieldNombreAtributo.getText().toString().split(" ");
				String atributoPrincipal = textFieldAtributoPrincipal.getText();
				Boolean anadido = controllerInterfaz.anadirComponente(dni, atributoPrincipal, atributoLista, listas);
				textFieldMensaje.setText(
						anadido ? "El paciente ha sido actualizado correctamente" : "El paciente no se ha actualizado");

			}
		});
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAceptar.setBounds(246, 248, 103, 34);
		contentPane.add(btnAceptar);

		textFieldMensaje = new JTextField();
		textFieldMensaje.setBounds(10, 303, 497, 19);
		contentPane.add(textFieldMensaje);
		textFieldMensaje.setColumns(10);

		textArea_Lista_de_listas = new TextArea();
		textArea_Lista_de_listas.setVisible(false);
		textArea_Lista_de_listas.setBounds(229, 183, 206, 59);
		contentPane.add(textArea_Lista_de_listas);

		lblAtributoPrincipal = new JLabel("Introduzca el nombre del atributo principal");
		lblAtributoPrincipal.setVisible(false);
		lblAtributoPrincipal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAtributoPrincipal.setBounds(10, 84, 279, 27);
		contentPane.add(lblAtributoPrincipal);

		textFieldAtributoPrincipal = new JTextField();
		textFieldAtributoPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String atributoPrincipal = textFieldAtributoPrincipal.getText();
				if (!atributoPrincipal.matches(" ")) {
					lblNombreAtributo.setVisible(true);
					textFieldNombreAtributo.setVisible(true);
				} else {
					lblNombreAtributo.setVisible(false);
					textFieldNombreAtributo.setVisible(false);
				}
			}
		});
		textFieldAtributoPrincipal.setVisible(false);
		textFieldAtributoPrincipal.setBounds(285, 82, 150, 27);
		contentPane.add(textFieldAtributoPrincipal);
		textFieldAtributoPrincipal.setColumns(10);

	}
}