package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Optional;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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


public class VentanaAñadirHistorialMedico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Controller_Interfaz controllerInterfaz = new Controller_Interfaz();
	MaskFormatter mascara;
	private JLabel lblDNI;
	private JFormattedTextField formattedDni;
	private JButton btnComprobar;
	private JTextField textFieldMensaje;
	JComboBox<String> comboBoxSiNo;
	JLabel lblAlergenos;
	private JTextField textFieldAlergenos;
	private JLabel lblElFormatoEs;
	private JLabel lblQuieresAadirEnfermedades;
	private JComboBox<String> comboBoxSiNo_1;
	JComboBox <String>comboBoxSiNo_3;
	VentanaEnfermedades ve;
	JButton btnAceptar;
	private JLabel lblMedicamentos;
	private JTextField textFieldMedicamentos;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAñadirHistorialMedico frame = new VentanaAñadirHistorialMedico();
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
	public VentanaAñadirHistorialMedico() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 380);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldMensaje = new JTextField();
		textFieldMensaje.setBounds(0, 310, 473, 20);
		contentPane.add(textFieldMensaje);
		textFieldMensaje.setColumns(10);

		lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDNI.setBounds(50, 29, 34, 21);
		contentPane.add(lblDNI);

		btnComprobar = new JButton("Comprobar");
		btnComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnComprobar == e.getSource()) {
					Optional<Document> pacientes = controllerInterfaz.findByDni(formattedDni.getText());

					if (pacientes.isPresent()) {
						lblAlergenos.setVisible(true);
						lblElFormatoEs.setVisible(true);
						lblQuieresAadirEnfermedades.setVisible(true);
						lblMedicamentos.setVisible(true);
						comboBoxSiNo.setVisible(true);
						comboBoxSiNo_1.setVisible(true);
						comboBoxSiNo_3.setVisible(true);
						
					} else {
						String mensaje = "El paciente con DNI " + formattedDni.getText() + " no existe ";
						JOptionPane.showMessageDialog(null, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnComprobar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnComprobar.setBounds(328, 29, 113, 25);
		contentPane.add(btnComprobar);

		try {
			mascara = new MaskFormatter("########?");
			mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			formattedDni = new JFormattedTextField(mascara);
			formattedDni.setBounds(131, 32, 177, 20);
			contentPane.add(formattedDni);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		lblAlergenos = new JLabel("Quieres añadir alergenos?,");
		lblAlergenos.setVisible(false);
		lblAlergenos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAlergenos.setBounds(10, 97, 184, 20);
		contentPane.add(lblAlergenos);

		comboBoxSiNo = new JComboBox<String>();
		comboBoxSiNo.setVisible(false);
		comboBoxSiNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dni = formattedDni.getText().toString();
				String opcion = comboBoxSiNo.getSelectedItem().toString();
				switch (opcion) {
				case "Si":
					
					textFieldAlergenos.setVisible(true);
					Optional<Document> pacientes = controllerInterfaz.findByDni(dni);
					String alergias = textFieldAlergenos.getText();
					String[] a = alergias.split(" ");
					controllerInterfaz.anadirAlergenos(pacientes, a);
					break;
				case "No":
					textFieldAlergenos.setVisible(false);
					break;
					
				}
			}

			
			
		});
		comboBoxSiNo.setModel(new DefaultComboBoxModel<String>(new String[] { "Si", "No" }));
		comboBoxSiNo.setBounds(204, 98, 50, 22);
		contentPane.add(comboBoxSiNo);

		textFieldAlergenos = new JTextField();
		textFieldAlergenos.setVisible(false);
		textFieldAlergenos.setBounds(277, 99, 177, 20);
		contentPane.add(textFieldAlergenos);
		textFieldAlergenos.setColumns(10);

		lblElFormatoEs = new JLabel("El formato es \" alegerno \"");
		lblElFormatoEs.setVisible(false);
		lblElFormatoEs.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblElFormatoEs.setBounds(10, 116, 184, 20);
		contentPane.add(lblElFormatoEs);
		
		lblQuieresAadirEnfermedades = new JLabel("Quieres añadir enfermedades?");
		lblQuieresAadirEnfermedades.setVisible(false);
		lblQuieresAadirEnfermedades.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQuieresAadirEnfermedades.setBounds(10, 154, 217, 20);
		contentPane.add(lblQuieresAadirEnfermedades);
		
		comboBoxSiNo_1 = new JComboBox<String>();
		comboBoxSiNo_1.setVisible(false);
		comboBoxSiNo_1.setModel(new DefaultComboBoxModel<String>(new String[] { "Si", "No" }));
		comboBoxSiNo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dni = formattedDni.getText().toString();
				String opcion = comboBoxSiNo_1.getSelectedItem().toString();
				switch (opcion) {
				case "Si":
		
					btnAceptar.setVisible(true);
					
					
				break;
				case "No":
					btnAceptar.setVisible(false);

					break;
				}
			}

			
			
		});
		comboBoxSiNo_1.setBounds(239, 155, 50, 22);
		contentPane.add(comboBoxSiNo_1);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnAceptar == e.getSource()) {
					ve = new VentanaEnfermedades();
					ve.setVisible(true);
				}
			}
		});
		btnAceptar.setVisible(false);
		btnAceptar.setBounds(328, 155, 89, 23);
		contentPane.add(btnAceptar);
		
		lblMedicamentos = new JLabel("Quieres añadir medicamentos ");
		lblMedicamentos.setVisible(false);
		lblMedicamentos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMedicamentos.setBounds(10, 205, 217, 14);
		contentPane.add(lblMedicamentos);
		
		comboBoxSiNo_3 = new JComboBox<String>();
		comboBoxSiNo_3.setVisible(false);
		comboBoxSiNo_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dni = formattedDni.getText().toString();
				String opcion = comboBoxSiNo_3.getSelectedItem().toString();
				switch (opcion) {
				case "Si":
					textFieldMedicamentos.setVisible(true);
					break;
				case "No":
					textFieldMedicamentos.setVisible(false);
					break;
				}
			}
		});
		comboBoxSiNo_3.setModel(new DefaultComboBoxModel<String>(new String[] { "Si", "No" }));
		comboBoxSiNo_3.setBounds(239, 203, 50, 20);
		contentPane.add(comboBoxSiNo_3);
		
		textFieldMedicamentos = new JTextField();
		textFieldMedicamentos.setVisible(false);
		textFieldMedicamentos.setBounds(299, 204, 155, 20);
		contentPane.add(textFieldMedicamentos);
		textFieldMedicamentos.setColumns(10);

	}
}
