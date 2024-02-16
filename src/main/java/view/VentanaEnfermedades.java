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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import org.bson.Document;

import controller.Controller_Interfaz;

public class VentanaEnfermedades extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblEnfermedad;
	private JTextField textFieldEnfermedad;
	private JLabel lblFecha;
	private JLabel lblDetalles;
	JComboBox<String> comboBoxDetalles;
	MaskFormatter mascara;
	JFormattedTextField formattedFecha;
	private JButton btnCancelar;
	private JButton btnAcepatr;
	private JLabel lblTratamiento;
	private JTextField textFieldTratamiento;
	private JLabel lblMedicamentos;
	private JTextField textFieldMedicamentos;
	private JTextField textFieldinforme;
	private JLabel lblInforme;
	private final Controller_Interfaz controllerInterfaz = new Controller_Interfaz();
	VentanaAñadirHistorialMedico vah = new VentanaAñadirHistorialMedico();
	private JTextField textFieldMensaje;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEnfermedades frame = new VentanaEnfermedades();
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
	public VentanaEnfermedades() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 347);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblEnfermedad = new JLabel("Enfermedad\r\n");
		lblEnfermedad.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEnfermedad.setBounds(10, 11, 112, 20);
		contentPane.add(lblEnfermedad);

		textFieldEnfermedad = new JTextField();
		textFieldEnfermedad.setColumns(10);
		textFieldEnfermedad.setBounds(132, 13, 143, 20);
		contentPane.add(textFieldEnfermedad);

		lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFecha.setBounds(10, 55, 77, 20);
		contentPane.add(lblFecha);

		lblDetalles = new JLabel("Detalles");
		lblDetalles.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDetalles.setBounds(10, 98, 77, 14);
		contentPane.add(lblDetalles);

		comboBoxDetalles = new JComboBox<String>();
		comboBoxDetalles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String opcion = comboBoxDetalles.getSelectedItem().toString();
				switch (opcion) {
				case "Si":
					lblTratamiento.setVisible(true);
					textFieldTratamiento.setVisible(true);
					lblMedicamentos.setVisible(true);
					textFieldMedicamentos.setVisible(true);
					textFieldinforme.setVisible(true);
					lblInforme.setVisible(true);

					break;
				case "No":
					break;
				}
			}
		});
		comboBoxDetalles.setModel(new DefaultComboBoxModel<String>(new String[] { "Si", "No" }));
		comboBoxDetalles.setBounds(126, 96, 53, 22);
		contentPane.add(comboBoxDetalles);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(91, 247, 89, 23);
		contentPane.add(btnCancelar);

		btnAcepatr = new JButton("Aceptar");
		btnAcepatr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnAcepatr == e.getSource()) {
					crearDocumentosEnfermedades();
					dispose();
					

				}
			}

		});
		btnAcepatr.setBounds(255, 247, 89, 23);
		contentPane.add(btnAcepatr);

		lblTratamiento = new JLabel("Tratamiento");
		lblTratamiento.setVisible(false);
		lblTratamiento.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTratamiento.setBounds(10, 141, 112, 14);
		contentPane.add(lblTratamiento);

		textFieldTratamiento = new JTextField();
		textFieldTratamiento.setVisible(false);
		textFieldTratamiento.setColumns(10);
		textFieldTratamiento.setBounds(132, 140, 134, 20);
		contentPane.add(textFieldTratamiento);

		lblMedicamentos = new JLabel("Medicamentos");
		lblMedicamentos.setVisible(false);
		lblMedicamentos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMedicamentos.setBounds(10, 172, 112, 14);
		contentPane.add(lblMedicamentos);

		textFieldMedicamentos = new JTextField();
		textFieldMedicamentos.setVisible(false);
		textFieldMedicamentos.setColumns(10);
		textFieldMedicamentos.setBounds(132, 171, 134, 20);
		contentPane.add(textFieldMedicamentos);

		textFieldinforme = new JTextField();
		textFieldinforme.setVisible(false);
		textFieldinforme.setColumns(10);
		textFieldinforme.setBounds(132, 202, 134, 20);
		contentPane.add(textFieldinforme);

		lblInforme = new JLabel("Informe");
		lblInforme.setVisible(false);
		lblInforme.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblInforme.setBounds(10, 205, 94, 14);
		contentPane.add(lblInforme);
		try {
			mascara = new MaskFormatter("##/##/####");
			mascara.setValidCharacters("0123456789");
			formattedFecha = new JFormattedTextField(mascara);
			formattedFecha.setBounds(137, 57, 138, 19);
			contentPane.add(formattedFecha);
			

		} catch (ParseException e) {
			e.printStackTrace();
		}
		textFieldMensaje = new JTextField();
		textFieldMensaje.setBounds(1, 277, 453, 20);
		contentPane.add(textFieldMensaje);
		textFieldMensaje.setColumns(10);

	}

	public Document crearDocumentosEnfermedades() {
		Optional<Document> pacientes = controllerInterfaz.findByDni(vah.formattedDni.getText());

		String enfermedad = textFieldEnfermedad.getText();
		String fecha = formattedFecha.getText().toString();

		String medicamentos = textFieldMedicamentos.getText();
		String tratamiento = textFieldTratamiento.getText();
		String informe = textFieldinforme.getText();
		String[] historialMedicoMedicamentos = medicamentos.split(" ");
		Document enfermedades = controllerInterfaz.crearDocumentoEnfermedades(pacientes, enfermedad, fecha,
				historialMedicoMedicamentos, tratamiento, informe);
		return enfermedades;
	}
}
