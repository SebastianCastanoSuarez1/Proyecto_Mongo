package view.medico;

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
import controller.MedicoController_Interfaz;

public class VentanaAnadirPacientesCargo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldDniPacientes;
	JLabel lblDNI;
	JFormattedTextField formattedDni;
	JButton btnComprobar;
	JLabel lblDniPacientes;
	JButton btnCancelar;
	JButton btnAceptar;
	JLabel lblMensaje;
	MaskFormatter mascara;
	VentanaOpcionAnadirMedico voam;
	MedicoController_Interfaz medicoController = new MedicoController_Interfaz();
	Controller_Interfaz pacienteController = new Controller_Interfaz();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAnadirPacientesCargo frame = new VentanaAnadirPacientesCargo();
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
	public VentanaAnadirPacientesCargo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 513, 345);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDNI.setBounds(57, 37, 34, 21);
		contentPane.add(lblDNI);
		
		try {
			mascara = new MaskFormatter("########?");
			mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			formattedDni = new JFormattedTextField(mascara);
			formattedDni.setBounds(131, 41, 177, 20);
			contentPane.add(formattedDni);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		btnComprobar = new JButton("Comprobar");
		btnComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Optional<Document> medicos = medicoController.findByDni(formattedDni.getText());

				if (medicos.isPresent()) {
					lblDniPacientes.setVisible(true);
					textFieldDniPacientes.setVisible(true);
					
				} else {
					String mensaje = "El medico con DNI " + formattedDni.getText() + " no existe ";
					JOptionPane.showMessageDialog(null, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnComprobar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnComprobar.setBounds(335, 37, 113, 25);
		contentPane.add(btnComprobar);
		
		lblDniPacientes = new JLabel("Introduzca los DNI de los pacientes\r\n");
		lblDniPacientes.setVisible(false);
		lblDniPacientes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDniPacientes.setBounds(29, 120, 210, 21);
		contentPane.add(lblDniPacientes);
		
		textFieldDniPacientes = new JTextField();
		textFieldDniPacientes.setVisible(false);
		textFieldDniPacientes.setBounds(269, 120, 150, 21);
		contentPane.add(textFieldDniPacientes);
		textFieldDniPacientes.setColumns(10);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				voam = new VentanaOpcionAnadirMedico();
				voam.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(91, 214, 95, 28);
		contentPane.add(btnCancelar);
		
		btnAceptar = new JButton("Aceptar\r\n");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Optional<Document> medico = medicoController.findByDni(formattedDni.getText());
				String dniMedico = formattedDni.getText();
		        String pacientes = textFieldDniPacientes.getText();
		        String[] pacientesCargo = pacientes.split(" "); 
		        pacienteController.anadirDniMedico(pacientesCargo, dniMedico);

		        
		        if(btnAceptar == e.getSource()) {
		            Boolean anadido = medicoController.crearPacientesCargo(medico, pacientesCargo);
		           
		            if(anadido == true) {
						lblMensaje.setText("El medico ha sido actualizado con exito");
						lblMensaje.setForeground(Color.GREEN);
					}else {
						lblMensaje.setText("El medico no ha sido actualizado con exito");
						lblMensaje.setForeground(Color.RED);
					}

		        }
			}
		});
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAceptar.setBounds(268, 214, 95, 28);
		contentPane.add(btnAceptar);
		
		lblMensaje = new JLabel("\r\n");
		lblMensaje.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMensaje.setBounds(91, 270, 290, 28);
		contentPane.add(lblMensaje);
	}
}
