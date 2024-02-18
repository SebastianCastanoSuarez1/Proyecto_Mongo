package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
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

public class VentanaComponentesValores extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	MaskFormatter mascara;
	JButton btnComprobar;
	JFormattedTextField formattedDni;
	JLabel lblDNI;
	private final Controller_Interfaz controllerInterfaz = new Controller_Interfaz();
	VentanaAnadirComponente vac;
	private JLabel lblAtributo;
	private JTextField textFieldAtributo;
	private JLabel lblValores;
	private JTextField textFieldValores;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JTextField textFieldMensaje;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaComponentesValores frame = new VentanaComponentesValores();
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
	public VentanaComponentesValores() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnComprobar = new JButton("Comprobar");
		btnComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnComprobar == e.getSource()) {
					Optional<Document> pacientes = controllerInterfaz.findByDni(formattedDni.getText());

					if (pacientes.isPresent()) {
						lblAtributo.setVisible(true);
						textFieldAtributo.setVisible(true);
					} else {
						String mensaje = "El paciente con DNI " + formattedDni.getText() + " no existe ";
						JOptionPane.showMessageDialog(null, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnComprobar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnComprobar.setBounds(270, 33, 98, 27);
		contentPane.add(btnComprobar);
		
		try {
			mascara = new MaskFormatter("########?");
			mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			formattedDni = new JFormattedTextField(mascara);
			formattedDni.setBounds(85, 35, 148, 26);
			contentPane.add(formattedDni);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDNI.setBounds(20, 24, 55, 40);
		contentPane.add(lblDNI);
		
		lblAtributo = new JLabel("Introduzca el nombre del atributo");
		lblAtributo.setVisible(false);
		lblAtributo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAtributo.setBounds(20, 77, 218, 19);
		contentPane.add(lblAtributo);
		
		textFieldAtributo = new JTextField();
		textFieldAtributo.setVisible(false);
		textFieldAtributo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String atributo = textFieldAtributo.getText();
				if(!atributo.matches(" ")) {
					lblValores.setVisible(true);
					textFieldValores.setVisible(true);
				}else {
					lblValores.setVisible(true);
					textFieldValores.setVisible(true);
				}
			}
		});
		textFieldAtributo.setBounds(245, 79, 137, 19);
		contentPane.add(textFieldAtributo);
		textFieldAtributo.setColumns(10);
		
		lblValores = new JLabel("Introduzca los valores del atributo\r\n");
		lblValores.setVisible(false);
		lblValores.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValores.setBounds(20, 131, 218, 19);
		contentPane.add(lblValores);
		
		textFieldValores = new JTextField();
		textFieldValores.setVisible(false);
		textFieldValores.setColumns(10);
		textFieldValores.setBounds(245, 133, 137, 19);
		contentPane.add(textFieldValores);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dni = formattedDni.getText();
				String atributo = textFieldAtributo.getText();
				String valores = textFieldValores.getText();
				String[] componenteAtributo = atributo.split(" ");
				String[] componenteValor = valores.split(" ");
				Boolean anadido = controllerInterfaz.anadirComponente(dni, "ListaAtributos", componenteAtributo, componenteValor);
				textFieldMensaje.setText(anadido ? "El paciente ha sido actualizado correctamente" : "El paciente no se ha actualizado");

			}
		});
		btnAceptar.setBounds(245, 190, 85, 27);
		contentPane.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vac = new VentanaAnadirComponente();
				vac.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelar.setBounds(85, 189, 95, 27);
		contentPane.add(btnCancelar);
		
		textFieldMensaje = new JTextField();
		textFieldMensaje.setColumns(10);
		textFieldMensaje.setBounds(10, 234, 408, 19);
		contentPane.add(textFieldMensaje);
	}
}
