package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import org.bson.Document;

import controller.MedicoController_Interfaz;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.text.ParseException;
import java.util.Optional;

import javax.swing.JFormattedTextField;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAnadirAtributoNuevoMedico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldValor;
	MaskFormatter mascara;
	JLabel lblDNI;
	JFormattedTextField formattedDni;
	JLabel lblNombre;
	JLabel lblIntroduzcaElValor;
	JButton btnCancelar;
	JButton btnAceptar;
	JLabel lblMensaje;
	JButton btnComprobar;
	MedicoController_Interfaz controllerMedico = new MedicoController_Interfaz();
	VentanaOpcionAnadirMedico voam;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAnadirAtributoNuevoMedico frame = new VentanaAnadirAtributoNuevoMedico();
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
	public VentanaAnadirAtributoNuevoMedico() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 545, 389);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDNI.setBounds(42, 38, 55, 40);
		contentPane.add(lblDNI);
		
		try {
            mascara = new MaskFormatter("########?");
            mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            formattedDni = new JFormattedTextField(mascara);
    		formattedDni.setBounds(107, 49, 148, 26);
    		contentPane.add(formattedDni);
    		
    		
    	} catch (ParseException e) {
            e.printStackTrace();
        }
		
		lblNombre = new JLabel("Introduzca el nombre del nuevo atributo\r\n");
		lblNombre.setVisible(false);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(42, 109, 288, 40);
		contentPane.add(lblNombre);
		
		lblIntroduzcaElValor = new JLabel("Introduzca el valor del nuevo atributo");
		lblIntroduzcaElValor.setVisible(false);
		lblIntroduzcaElValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIntroduzcaElValor.setBounds(42, 175, 246, 40);
		contentPane.add(lblIntroduzcaElValor);
		
		textFieldNombre = new JTextField();
		textFieldNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String atributo = textFieldNombre.getText();
				if(!atributo.equals("")) {
					lblIntroduzcaElValor.setVisible(true);
					textFieldValor.setVisible(true);
				}else {
					lblIntroduzcaElValor.setVisible(false);
					textFieldValor.setVisible(false);
				}
			}
		});
		textFieldNombre.setVisible(false);
		textFieldNombre.setBounds(352, 115, 141, 26);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldValor = new JTextField();
		textFieldValor.setVisible(false);
		textFieldValor.setColumns(10);
		textFieldValor.setBounds(352, 184, 148, 27);
		contentPane.add(textFieldValor);
		
		btnCancelar = new JButton("Cancelar\r\n");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				voam = new VentanaOpcionAnadirMedico();
				voam.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelar.setBounds(82, 269, 98, 26);
		contentPane.add(btnCancelar);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnAceptar == e.getSource()) {
					Optional<Document> medicos = controllerMedico.findByDni(formattedDni.getText().toString()) ;
					if(medicos.isPresent()) {
						Boolean anadido =controllerMedico.actualizarMedico(medicos,textFieldNombre.getText(), textFieldValor.getText());
						if(anadido == true) {
							lblMensaje.setText("El paciente ha sido actualizado con exito");
							lblMensaje.setForeground(Color.GREEN);
						}else {
							lblMensaje.setText("El paciente no ha sido actualizado con exito");
							lblMensaje.setForeground(Color.RED);
						}
					}else {
						lblMensaje.setText("No existe el paciente con el DNI " + formattedDni.getText().toString());
						lblMensaje.setForeground(Color.RED);
					}
				}
			}
		});
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAceptar.setBounds(274, 269, 115, 26);
		contentPane.add(btnAceptar);
		
		lblMensaje = new JLabel("");
		lblMensaje.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMensaje.setBounds(41, 316, 329, 26);
		contentPane.add(lblMensaje);
		
		btnComprobar = new JButton("Comprobar");
		btnComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnComprobar == e.getSource()) {
					Optional<Document> pacientes = controllerMedico.findByDni(formattedDni.getText());
					
					if(pacientes.isPresent()) {
						lblNombre.setVisible(true);
						textFieldNombre.setVisible(true);
					}else{
						String mensaje = "El paciente con DNI " + formattedDni.getText() + " no existe "; 
						JOptionPane.showMessageDialog(null, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnComprobar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnComprobar.setBounds(305, 47, 98, 27);
		contentPane.add(btnComprobar);
	}
}
