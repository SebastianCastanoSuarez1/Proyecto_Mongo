package view.paciente;

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
	JLabel lblDNI;
	JFormattedTextField formattedDni;
	JButton btnComprobar;
	JComboBox<String> comboBoxAlergenos;
	JLabel lblAlergenos;
	JTextField textFieldAlergenos;
	JLabel lblElFormatoEs;
	JLabel lblQuieresAadirEnfermedades;
	JComboBox<String> comboBoxEnfermedades;
	JComboBox<String> comboBoxMedicamentos;
	VentanaEnfermedades ven;
	JButton btnAceptarEnfermedades;
	JLabel lblMedicamentos;
	JTextField textFieldMedicamentos;
	JButton btnAceptar;
	JButton btnCancelar;
	private JLabel lblMensaje;

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
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 380);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

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
						comboBoxAlergenos.setVisible(true);
						comboBoxEnfermedades.setVisible(true);
						comboBoxMedicamentos.setVisible(true);
						
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
			formattedDni.setBounds(123, 33, 177, 20);
			contentPane.add(formattedDni);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		lblAlergenos = new JLabel("Quieres añadir alergenos?,");
		lblAlergenos.setVisible(false);
		lblAlergenos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAlergenos.setBounds(10, 97, 184, 20);
		contentPane.add(lblAlergenos);

		comboBoxAlergenos = new JComboBox<String>();
		comboBoxAlergenos.setVisible(false);
		comboBoxAlergenos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String opcion = comboBoxAlergenos.getSelectedItem().toString();
				switch (opcion) {
				case "Si":
					textFieldAlergenos.setVisible(true);
					break;
				case "No":
					textFieldAlergenos.setVisible(false);
					break;
					
				}
			}

			
			
		});
		comboBoxAlergenos.setModel(new DefaultComboBoxModel<String>(new String[] { "Si", "No" }));
		comboBoxAlergenos.setBounds(204, 98, 50, 22);
		contentPane.add(comboBoxAlergenos);

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
		
		comboBoxEnfermedades = new JComboBox<String>();
		comboBoxEnfermedades.setVisible(false);
		comboBoxEnfermedades.setModel(new DefaultComboBoxModel<String>(new String[] { "Si", "No" }));
		comboBoxEnfermedades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String opcion = comboBoxEnfermedades.getSelectedItem().toString();
				switch (opcion) {
				case "Si":
					btnAceptarEnfermedades.setVisible(true);
					btnAceptarEnfermedades.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(btnAceptarEnfermedades == e.getSource()) {
								ven= new VentanaEnfermedades();
								ven.setVisible(true);
							}
						}
					});
					break;
				case "No":
					btnAceptarEnfermedades.setVisible(false);
					break;
				}
			}

			
			
		});
		comboBoxEnfermedades.setBounds(239, 155, 50, 22);
		contentPane.add(comboBoxEnfermedades);
		
		btnAceptarEnfermedades = new JButton("Aceptar");
		btnAceptarEnfermedades.setVisible(false);
		btnAceptarEnfermedades.setBounds(328, 155, 89, 23);
		contentPane.add(btnAceptarEnfermedades);

		
		lblMedicamentos = new JLabel("Quieres añadir medicamentos ");
		lblMedicamentos.setVisible(false);
		lblMedicamentos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMedicamentos.setBounds(10, 205, 217, 14);
		contentPane.add(lblMedicamentos);
		
		comboBoxMedicamentos = new JComboBox<String>();
		comboBoxMedicamentos.setVisible(false);
		comboBoxMedicamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String opcion = comboBoxMedicamentos.getSelectedItem().toString();
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
		comboBoxMedicamentos.setModel(new DefaultComboBoxModel<String>(new String[] { "Si", "No" }));
		comboBoxMedicamentos.setBounds(239, 203, 50, 20);
		contentPane.add(comboBoxMedicamentos);
		
		textFieldMedicamentos = new JTextField();
		textFieldMedicamentos.setVisible(false);
		textFieldMedicamentos.setBounds(299, 204, 155, 20);
		contentPane.add(textFieldMedicamentos);
		textFieldMedicamentos.setColumns(10);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Optional<Document> pacientes = controllerInterfaz.findByDni(formattedDni.getText());
		        String alergenos = textFieldAlergenos.getText();
		        String medicamentos = textFieldMedicamentos.getText();
		        String[] historialMedicoAlergenos = alergenos.split(" "); // Dividir en palabras, no en caracteres
		        String[] historialMedicoMedicamentos = medicamentos.split(" ");
		        Document enfermedades = ven.crearDocumentosEnfermedades();
		        // Dividir en palabras, no en caracteres
		        if(btnAceptar == e.getSource()) {
		            Boolean anadido = controllerInterfaz.anadirVariables(pacientes, historialMedicoAlergenos, historialMedicoMedicamentos,enfermedades);
		            if(anadido == true) {
						lblMensaje.setText("El paciente ha sido actualizado con exito");
						lblMensaje.setForeground(Color.GREEN);
					}else {
						lblMensaje.setText("El paciente no ha sido actualizado con exito");
						lblMensaje.setForeground(Color.RED);
					}
		        }
		    }
		});
		btnAceptar.setBounds(277, 264, 89, 23);
		contentPane.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnCancelar == e.getSource()) {
					VentanaOpcionAnadirPaciente voa = new VentanaOpcionAnadirPaciente();
					voa.setVisible(true);
					dispose();
				}
			}
		});
		btnCancelar.setBounds(138, 264, 89, 23);
		contentPane.add(btnCancelar);
		
		lblMensaje = new JLabel("");
		lblMensaje.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMensaje.setBounds(10, 312, 465, 21);
		contentPane.add(lblMensaje);

	}
}
