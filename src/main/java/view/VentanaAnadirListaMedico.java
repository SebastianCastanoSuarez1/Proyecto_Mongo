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

import controller.MedicoController_Interfaz;

public class VentanaAnadirListaMedico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	JLabel lblDNI;
	JFormattedTextField formattedDni;
	JButton btnComprobar;
	JLabel lblNombre;
	JButton btnCancelar;
	JButton btnAceptar;
	VentanaOpcionAnadirMedico voam;
	MedicoController_Interfaz controllerMedico = new MedicoController_Interfaz();
	MaskFormatter mascara;
	private JLabel lbLista;
	
	private JTextField textFieldLista;
	private JTextField textFieldNombre;
	private JLabel lblMensaje;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAnadirListaMedico frame = new VentanaAnadirListaMedico();
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
	public VentanaAnadirListaMedico() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 471, 328);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDNI.setBounds(24, 10, 55, 40);
		contentPane.add(lblDNI);
		
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
		btnComprobar.setBounds(297, 19, 98, 27);
		contentPane.add(btnComprobar);
		
		try {
            mascara = new MaskFormatter("########?");
            mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            formattedDni = new JFormattedTextField(mascara);
    		formattedDni.setBounds(91, 21, 148, 26);
    		contentPane.add(formattedDni);
    		
    		lblNombre = new JLabel("Introduzca el nombre del atributo");
    		lblNombre.setVisible(false);
    		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
    		lblNombre.setBounds(10, 76, 213, 27);
    		contentPane.add(lblNombre);
    		
    		textFieldLista = new JTextField();
    		textFieldLista.setVisible(false);
    		textFieldLista.setColumns(10);
    		textFieldLista.setBounds(233, 136, 182, 27);
    		contentPane.add(textFieldLista);
    		
    		btnCancelar = new JButton("Cancelar\r\n");
    		btnCancelar.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				voam = new VentanaOpcionAnadirMedico();
    				voam.setVisible(true);
    				dispose();
    			}
    		});
    		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
    		btnCancelar.setBounds(98, 196, 103, 34);
    		contentPane.add(btnCancelar);
    		
    		btnAceptar = new JButton("Aceptar");
    		btnAceptar.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				
					Optional<Document> medicos = controllerMedico.findByDni(formattedDni.getText());
					String atributo = textFieldNombre.getText();
    				String listas = textFieldLista.getText();
    				String [] lista = listas.split(" ");
    				Boolean anadido = controllerMedico.anadirLista(medicos, atributo, lista);
					
					if(anadido == true) {
						lblMensaje.setText("El paciente ha sido actualizado con exito");
						lblMensaje.setForeground(Color.GREEN);
					}else {
						lblMensaje.setText("El paciente no ha sido actualizado con exito");
						lblMensaje.setForeground(Color.RED);
					}
					}
    		});
    		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 14));
    		btnAceptar.setBounds(245, 196, 103, 34);
    		contentPane.add(btnAceptar);
    		
    		lbLista = new JLabel("Introduzca la lista de los valores\r\n");
    		lbLista.setVisible(false);
    		lbLista.setFont(new Font("Tahoma", Font.PLAIN, 14));
    		lbLista.setBounds(10, 140, 213, 15);
    		contentPane.add(lbLista);
    		
    		textFieldNombre = new JTextField();
    		textFieldNombre.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				String atributo = textFieldNombre.getText().toString();
    				if(!atributo.equals("")) {
    					lbLista.setVisible(true);
    					textFieldLista.setVisible(true);
    				}else {
    					lbLista.setVisible(false);
    					textFieldLista.setVisible(false);
    				}
    			}
    		});
    		textFieldNombre.setVisible(false);
    		textFieldNombre.setColumns(10);
    		textFieldNombre.setBounds(233, 76, 182, 27);
    		contentPane.add(textFieldNombre);
    		
    		lblMensaje = new JLabel("\r\n");
    		lblMensaje.setFont(new Font("Tahoma", Font.PLAIN, 13));
    		lblMensaje.setBounds(41, 256, 377, 25);
    		contentPane.add(lblMensaje);
    		
    		

    		
    		
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
	}

}
