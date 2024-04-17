package view.paciente;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;

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

public class VentanaAgregarPaciente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel lblDNI;
	JLabel lblNombre;
	JLabel lblApellidos;
	JLabel lblFechaDeNacimiento;
	JLabel lblSexo;
	JLabel lblLugarDeNacimiento;
	JLabel lblAltura;
	MaskFormatter mascara;
	JFormattedTextField formattedDni;
	private JTextField textFieldNombre;
	private JTextField textFieldApellidos;
	JLabel lblPeso;
	JLabel lblGrupoSanguineo;
	JLabel lblEnfermedad;
	JLabel lblTipo;
	private JFormattedTextField formattedFechaNacimiento;
	JComboBox<String> comboBoxSexo;
	private JTextField textFieldLugarNacimiento;
	private JTextField textFieldAltura;
	private JTextField textFieldPeso;
	private JComboBox<String> comboBoxGrupoSanguineo;
	private JComboBox<String> comboBoxEnfermedad;
	private JComboBox<String> comboBoxTipo;
	JButton btnAceptar;
	JButton btnCancelar;

	private final Controller_Interfaz controllerInterfaz = new Controller_Interfaz();
	VentanaOpcionAnadirPaciente voa;
	VentanaPrincipalPaciente vp;
	private JLabel lblMensaje;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAgregarPaciente frame = new VentanaAgregarPaciente();
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
	public VentanaAgregarPaciente() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 353);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblDNI = new JLabel("DNI\r\n");
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDNI.setBounds(10, 10, 42, 21);
		contentPane.add(lblDNI);

		lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombre.setBounds(234, 10, 60, 21);
		contentPane.add(lblNombre);

		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblApellidos.setBounds(10, 41, 60, 21);
		contentPane.add(lblApellidos);

		lblFechaDeNacimiento = new JLabel("Fecha_Nacimiento");
		lblFechaDeNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFechaDeNacimiento.setBounds(234, 41, 126, 21);
		contentPane.add(lblFechaDeNacimiento);

		lblSexo = new JLabel("Sexo");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSexo.setBounds(10, 72, 89, 21);
		contentPane.add(lblSexo);

		lblLugarDeNacimiento = new JLabel("Lugar_Nacimiento");
		lblLugarDeNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLugarDeNacimiento.setBounds(234, 72, 126, 21);
		contentPane.add(lblLugarDeNacimiento);

		lblAltura = new JLabel("Altura");
		lblAltura.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAltura.setBounds(10, 103, 60, 21);
		contentPane.add(lblAltura);

		lblPeso = new JLabel("Peso\r\n");
		lblPeso.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPeso.setBounds(234, 103, 49, 21);
		contentPane.add(lblPeso);

		lblGrupoSanguineo = new JLabel("Grupo_Sanguineo");
		lblGrupoSanguineo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGrupoSanguineo.setBounds(10, 134, 138, 21);
		contentPane.add(lblGrupoSanguineo);

		lblEnfermedad = new JLabel("Enfermedad");
		lblEnfermedad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEnfermedad.setBounds(234, 134, 89, 21);
		contentPane.add(lblEnfermedad);

		lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTipo.setBounds(234, 165, 49, 21);
		contentPane.add(lblTipo);

		try {
			mascara = new MaskFormatter("########?");
			mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			formattedDni = new JFormattedTextField(mascara);
			formattedDni.setBounds(62, 12, 138, 19);
			contentPane.add(formattedDni);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(304, 12, 140, 19);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		textFieldApellidos = new JTextField();
		textFieldApellidos.setColumns(10);
		textFieldApellidos.setBounds(80, 43, 140, 19);
		contentPane.add(textFieldApellidos);

		try {
			mascara = new MaskFormatter("##/##/####");
			mascara.setValidCharacters("0123456789");
			formattedFechaNacimiento = new JFormattedTextField(mascara);
			formattedFechaNacimiento.setBounds(365, 43, 138, 19);
			contentPane.add(formattedFechaNacimiento);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		comboBoxSexo = new JComboBox<String>();
		comboBoxSexo.setModel(new DefaultComboBoxModel<String>(new String[] { "", "Masculino", "Femenino" }));
		comboBoxSexo.setBounds(62, 72, 95, 21);
		contentPane.add(comboBoxSexo);

		textFieldLugarNacimiento = new JTextField();
		textFieldLugarNacimiento.setBounds(369, 74, 134, 19);
		contentPane.add(textFieldLugarNacimiento);
		textFieldLugarNacimiento.setColumns(10);

		textFieldAltura = new JTextField();
		textFieldAltura.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		textFieldAltura.setColumns(10);
		textFieldAltura.setBounds(62, 103, 134, 19);
		contentPane.add(textFieldAltura);

		textFieldPeso = new JTextField();
		textFieldPeso.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		textFieldPeso.setColumns(10);

		textFieldPeso.setBounds(286, 103, 134, 19);
		contentPane.add(textFieldPeso);

		comboBoxGrupoSanguineo = new JComboBox<String>();
		comboBoxGrupoSanguineo.setModel(new DefaultComboBoxModel<String>(
				new String[] { "", "A+", "A-", "B+", "B-", "AB+", "AB-", "0+", "0-" }));
		comboBoxGrupoSanguineo.setBounds(125, 135, 95, 21);
		contentPane.add(comboBoxGrupoSanguineo);

		comboBoxEnfermedad = new JComboBox<String>();
		comboBoxEnfermedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enfermedad = comboBoxEnfermedad.getSelectedItem().toString();
				switch (enfermedad) {
				case "Mental":
					comboBoxTipo.setModel(new DefaultComboBoxModel<String>(new String[] { "Depresion", "Ansiedad",
							"Tourette", "Estres postraumatico", "Trastorno obsesivo-compulsivo", "Ictus" }));
					break;
				case "Hormonal":
					comboBoxTipo.setModel(new DefaultComboBoxModel<String>(new String[] { "Diabetes", "Hipertiroidismo",
							"Hipotiroidismo", "Síndrome del ovario poliquístico", "Enfermedad de Addison",
							"Enfermedad de Graves" }));
					break;
				case "Respiratoria":
					comboBoxTipo.setModel(new DefaultComboBoxModel<String>(new String[] { "Tuberculosis", "Asma",
							"Asbestosis", "Bronquiolitis", "Bronquitis", "Enfisema", "Empiema" }));
					break;
				case "Cancer":
					comboBoxTipo.setModel(new DefaultComboBoxModel<String>(new String[] { "Pulmon", "Piel", "Prostata",
							"Higado", "Pancreas", "Mama", "Celebral", "Oseo" }));
					break;
				case "Dermatologica":
					comboBoxTipo.setModel(new DefaultComboBoxModel<String>(new String[] { "Dermatitis atopica",
							"Psoriasis", "Acne", "Onicomicosis", "Vitíligo", "Queratosis actínica" }));
					break;
				case "Digestiva":
					comboBoxTipo.setModel(new DefaultComboBoxModel<String>(
							new String[] { "Dispepsia", "Enfermedad por reflujo gastroesofágico", "Enfermedad celiaca",
									"Síndrome del intestino irritable", "Colitis ulcerosa", "Gastritis", "Ulceras" }));
					break;
				case "Oftalmologica":
					comboBoxTipo.setModel(
							new DefaultComboBoxModel<String>(new String[] { "Ambliopía", "Astigmatismo", "Cataratas",
									"Daltonismo", "Retinopatía diabética", "Síndrome del ojo seco", "Miodesopsias" }));
					break;
				case "Cardiocirculatoria":
					comboBoxTipo.setModel(new DefaultComboBoxModel<String>(
							new String[] { "Anemia", "Hemofilia", "Leucemia", "Infarto de miocardio", "Angina de pecho",
									"Insuficiencia cardiaca", "Trastornos del ritmo cardiaco" }));
					break;
				default:
					break;

				}
			}
		});
		comboBoxEnfermedad.setModel(new DefaultComboBoxModel<String>(new String[] { "", "Mental", "Hormonal",
				"Respiratoria", "Cancer", "Dermatologica", "Digestiva", "Oftalmologica", "Cardiocirculatoria" }));
		comboBoxEnfermedad.setBounds(312, 135, 119, 21);
		contentPane.add(comboBoxEnfermedad);

		comboBoxTipo = new JComboBox<String>();

		comboBoxTipo.setBounds(316, 165, 115, 21);
		contentPane.add(comboBoxTipo);
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anadirPaciente(e);
			}

			private void anadirPaciente(ActionEvent e) {
				if (btnAceptar == e.getSource()) {

					Document paciente = controllerInterfaz.anadirPacientenuevo(formattedDni.getText(),
							textFieldNombre.getText(), textFieldApellidos.getText(), formattedFechaNacimiento.getText(),
							comboBoxSexo.getSelectedItem().toString(), textFieldLugarNacimiento.getText(),
							textFieldAltura.getText(), textFieldPeso.getText(),
							comboBoxGrupoSanguineo.getSelectedItem().toString(),
							comboBoxEnfermedad.getSelectedItem().toString(), comboBoxTipo.getSelectedItem().toString());
					Boolean anadido = controllerInterfaz.salvarPaciente(paciente);
					if(anadido == true) {
						lblMensaje.setText("El paciente ha sido añadido con exito");
						lblMensaje.setForeground(Color.GREEN);
					}else {
						lblMensaje.setText("El paciente no ha sido añadido con exito");
						lblMensaje.setForeground(Color.RED);
					}

				}
			}
		});
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAceptar.setBounds(283, 217, 126, 34);
		contentPane.add(btnAceptar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				voa = new VentanaOpcionAnadirPaciente();
				voa.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancelar.setBounds(80, 217, 126, 34);
		contentPane.add(btnCancelar);
		
		lblMensaje = new JLabel("");
		lblMensaje.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMensaje.setBounds(26, 285, 465, 23);
		contentPane.add(lblMensaje);

	}
}
