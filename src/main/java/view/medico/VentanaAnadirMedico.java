package view.medico;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import org.bson.Document;

import controller.MedicoController_Interfaz;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class VentanaAnadirMedico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JFormattedTextField formattedDni;
	private JTextField textFieldNombre;
	private JTextField textFieldApellidos;
	private JTextField textFieldEspecialidad;
	private JTextField textField;
	JLabel lblDNI;
	JLabel lblNombre;
	JLabel lblApellidos;
	JLabel lblEspecialidad;
	JLabel lblAnioExperiencia;
	JButton btnCancelar;
	JButton btnAceptar;
	JLabel lblMensaje;
	MaskFormatter mascara;
	VentanaOpcionAnadirMedico vpm;
	MedicoController_Interfaz medico = new MedicoController_Interfaz();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAnadirMedico frame = new VentanaAnadirMedico();
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
	public VentanaAnadirMedico() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 498, 382);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDNI.setBounds(31, 29, 52, 24);
		contentPane.add(lblDNI);
		
		try {
			mascara = new MaskFormatter("########?");
			mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			formattedDni = new JFormattedTextField(mascara);
			formattedDni.setBounds(77, 33, 138, 19);
			contentPane.add(formattedDni);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		lblNombre = new JLabel("Nombre\r\n");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNombre.setBounds(230, 29, 63, 24);
		contentPane.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(303, 29, 117, 24);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblApellidos.setBounds(31, 93, 63, 20);
		contentPane.add(lblApellidos);
		
		textFieldApellidos = new JTextField();
		textFieldApellidos.setBounds(104, 93, 111, 21);
		contentPane.add(textFieldApellidos);
		textFieldApellidos.setColumns(10);
		
		lblEspecialidad = new JLabel("Especialidad");
		lblEspecialidad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEspecialidad.setBounds(245, 98, 79, 15);
		contentPane.add(lblEspecialidad);
		
		textFieldEspecialidad = new JTextField();
		textFieldEspecialidad.setBounds(334, 95, 111, 19);
		contentPane.add(textFieldEspecialidad);
		textFieldEspecialidad.setColumns(10);
		
		lblAnioExperiencia = new JLabel("Años de experiencia\r\n\r\n");
		lblAnioExperiencia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAnioExperiencia.setBounds(104, 160, 127, 24);
		contentPane.add(lblAnioExperiencia);
		
		textField = new JTextField();
		textField.setBounds(245, 164, 117, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vpm = new VentanaOpcionAnadirMedico();
				vpm.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancelar.setBounds(90, 240, 85, 21);
		contentPane.add(btnCancelar);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String dni = formattedDni.getText();
				String nombre = textFieldNombre.getText();
				String apellidos = textFieldApellidos.getText();
				String especialidad = textFieldEspecialidad.getText();
				String anio_experiencia = textField.getText();
				Document medicos = medico.anadirMedicoNuevo(dni, nombre, apellidos, especialidad, anio_experiencia);
				Boolean anadido = medico.salvarMedico(medicos);
				if(anadido == true) {
					lblMensaje.setText("El medico ha sido añadido con exito");
					lblMensaje.setForeground(Color.GREEN);
				}else {
					lblMensaje.setText("El medico no ha sido añadido con exito");
					lblMensaje.setForeground(Color.RED);
				}
			}
		});
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAceptar.setBounds(260, 241, 85, 21);
		contentPane.add(btnAceptar);
		
		lblMensaje = new JLabel("");
		lblMensaje.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMensaje.setBounds(104, 298, 296, 24);
		contentPane.add(lblMensaje);
	}
}
