package view.medico;

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

import controller.MedicoController_Interfaz;

public class VentanaComponentesListaMedico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	MaskFormatter mascara;
	JLabel lblDNI;
	JButton btnComprobar;
	JFormattedTextField formattedDni;
	private final MedicoController_Interfaz medicoInterfaz = new MedicoController_Interfaz();
	private JLabel lblNombreAtributo;
	private JTextField textFieldNombreAtributo;
	JLabel lblLista;
	JButton btnCancelar;
	JButton btnAceptar;
	VentanaAnadirComponenteMedico vac;
	JLabel lblAtributoPrincipal;
	private ArrayList<String[]> listaDeListas = new ArrayList<>();
	JLabel lblMensaje;
	private TextArea textArea_Lista_de_listas;
	private JTextField textFieldAtributoPrincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaComponentesListaMedico frame = new VentanaComponentesListaMedico();
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
	public VentanaComponentesListaMedico() {
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
		lblDNI.setBounds(69, 10, 55, 40);
		contentPane.add(lblDNI);

		btnComprobar = new JButton("Comprobar");
		btnComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnComprobar == e.getSource()) {
					Optional<Document> medicos= medicoInterfaz.findByDni(formattedDni.getText());

					if (medicos.isPresent()) {
						lblAtributoPrincipal.setVisible(true);
						textFieldAtributoPrincipal.setVisible(true);
					} else {
						String mensaje = "El medico con DNI " + formattedDni.getText() + " no existe ";
						JOptionPane.showMessageDialog(null, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnComprobar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnComprobar.setBounds(337, 19, 98, 27);
		contentPane.add(btnComprobar);

		try {
			mascara = new MaskFormatter("########?");
			mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			formattedDni = new JFormattedTextField(mascara);
			formattedDni.setBounds(157, 21, 148, 26);
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
				vac = new VentanaAnadirComponenteMedico();
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
				for (int i = 0; i < palabras.length; i++) {
					listaDeListas.add(palabras[i].split(" "));
				}
				String atributoLista[] = textFieldNombreAtributo.getText().toString().split(" ");
				String atributoPrincipal = textFieldAtributoPrincipal.getText();
				Boolean anadido = medicoInterfaz.anadirComponente(dni, atributoPrincipal, atributoLista, listaDeListas);
				if(anadido == true) {
					lblMensaje.setText("El medico ha sido actualizado con exito");
					lblMensaje.setForeground(Color.GREEN);
				}else {
					lblMensaje.setText("El medico no ha sido actualizado con exito");
					lblMensaje.setForeground(Color.RED);
				}

			}
		});
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAceptar.setBounds(246, 248, 103, 34);
		contentPane.add(btnAceptar);

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
		
		lblMensaje = new JLabel("");
		lblMensaje.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMensaje.setBounds(31, 311, 431, 22);
		contentPane.add(lblMensaje);

	}
}