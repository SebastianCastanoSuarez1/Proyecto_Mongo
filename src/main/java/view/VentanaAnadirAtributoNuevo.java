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



public class VentanaAnadirAtributoNuevo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombreAtributo;
	private JTextField textFieldValorAtributo;
	JLabel lblAtributoANadir;
	JLabel lblValor;
	JButton btnCancelar;
	JButton btnAceptar;
	private final Controller_Interfaz controllerInterfaz = new Controller_Interfaz();
	private JLabel lblDNI;
	private JFormattedTextField formattedDni;
	private JButton btnComprobar;
	MaskFormatter mascara;
	private JTextField textFieldMensaje;
	VentanaOpcionAnadirPaciente voa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAnadirAtributoNuevo frame = new VentanaAnadirAtributoNuevo();
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
	public VentanaAnadirAtributoNuevo() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 511, 377);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblAtributoANadir = new JLabel("Introduzca el nombre del nuevo atributo");
		lblAtributoANadir.setVisible(false);
		lblAtributoANadir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAtributoANadir.setBounds(0, 96, 279, 41);
		contentPane.add(lblAtributoANadir);
		
		textFieldNombreAtributo = new JTextField();
		textFieldNombreAtributo.setVisible(false);
		textFieldNombreAtributo.setColumns(10);
		textFieldNombreAtributo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String atributo = textFieldNombreAtributo.getText();
				if(!atributo.equals("")) {
					lblValor.setVisible(true);
					textFieldValorAtributo.setVisible(true);
				}else {
					lblValor.setVisible(false);
					textFieldValorAtributo.setVisible(false);
				}
			}
		});
		textFieldNombreAtributo.setBounds(261, 103, 206, 32);
		contentPane.add(textFieldNombreAtributo);
		
		lblValor = new JLabel("Introduzca el valor del nuevo atributo");
		lblValor.setVisible(false);
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValor.setBounds(0, 163, 243, 32);
		contentPane.add(lblValor);
		
		textFieldValorAtributo = new JTextField();
		textFieldValorAtributo.setVisible(false);
		textFieldValorAtributo.setColumns(10);
		textFieldValorAtributo.setBounds(261, 166, 206, 32);
		contentPane.add(textFieldValorAtributo);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnCancelar == e.getSource()) {
					voa = new VentanaOpcionAnadirPaciente();
					voa.setVisible(true);
					dispose();
				}
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCancelar.setBounds(91, 247, 92, 27);
		contentPane.add(btnCancelar);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnAceptar == e.getSource()) {
					Optional<Document> pacientes = controllerInterfaz.findByDni(formattedDni.getText().toString()) ;
					if(pacientes.isPresent()) {
						Boolean anadido =controllerInterfaz.actualizarPaciente(pacientes,textFieldNombreAtributo.getText(), textFieldValorAtributo.getText());
						textFieldMensaje.setText(anadido ? "El paciente ha sido actualizado correctamente" : "El paciente no se ha actualizado");

					}else {
						textFieldMensaje.setText("No existe el paciente con el DNI " + formattedDni.getText().toString());
					}
				}
			}
		});
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAceptar.setBounds(287, 247, 92, 27);
		contentPane.add(btnAceptar);
		
		lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDNI.setBounds(10, 25, 55, 40);
		contentPane.add(lblDNI);
		
		btnComprobar = new JButton("Comprobar");
		btnComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnComprobar == e.getSource()) {
					Optional<Document> pacientes = controllerInterfaz.findByDni(formattedDni.getText());
					
					if(pacientes.isPresent()) {
						lblAtributoANadir.setVisible(true);
						textFieldNombreAtributo.setVisible(true);
					}else{
						String mensaje = "El paciente con DNI " + formattedDni.getText() + " no existe "; 
						JOptionPane.showMessageDialog(null, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnComprobar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnComprobar.setBounds(281, 34, 98, 27);
		contentPane.add(btnComprobar);
		
		try {
            mascara = new MaskFormatter("########?");
            mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            formattedDni = new JFormattedTextField(mascara);
    		formattedDni.setBounds(91, 36, 148, 26);
    		contentPane.add(formattedDni);
    		
    		textFieldMensaje = new JTextField();
    		textFieldMensaje.setBounds(10, 311, 457, 19);
    		contentPane.add(textFieldMensaje);
    		textFieldMensaje.setColumns(10);
    		
    		
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
	}
}
