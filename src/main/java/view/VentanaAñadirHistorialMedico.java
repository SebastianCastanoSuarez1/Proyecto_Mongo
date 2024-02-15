package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Optional;

import javax.swing.DefaultComboBoxModel;
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
import java.awt.Color;
import javax.swing.JComboBox;

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
				if(btnComprobar == e.getSource()) {
					Optional<Document> pacientes = controllerInterfaz.findByDni(formattedDni.getText());
					
					if(pacientes.isPresent()) {
					
					}else{
						String mensaje = "El paciente con DNI " + formattedDni.getText() + " no existe "; 
						JOptionPane.showMessageDialog(null, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnComprobar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnComprobar.setBounds(344, 29, 97, 25);
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
		lblAlergenos = new JLabel("Quieres añadir alergenos?");
		lblAlergenos.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAlergenos.setBounds(10, 97, 183, 20);
		contentPane.add(lblAlergenos);
		
		comboBoxSiNo = new JComboBox<String>();
		comboBoxSiNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String opcion = comboBoxSiNo.getSelectedItem().toString();
				switch(opcion) {
				case "Si":
					textFieldAlergenos.setVisible(true);
					Document alegergenos = textFieldAlergenos.getText().toString();
					break;
				case "No":
					textFieldAlergenos.setVisible(false);
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
		
	}
}
