package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import org.bson.Document;

import controller.Controller_Interfaz;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class VentanaComponentesListasValores extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	MaskFormatter mascara;
	JLabel lblAtributos;
	JButton btnComprobar;
	JFormattedTextField formattedDni;
	JLabel lblDNI;
	private final Controller_Interfaz controllerInterfaz = new Controller_Interfaz();
	private JTextField textFieldAtributos;
	private JTextField textFieldValores;
	JLabel lblValores;
	JButton btnCancelar;
	JButton btnAceptar;
	VentanaAnadirComponente vac;
	private JTextField textFieldMensaje;
	private JTextField textFieldAtributoPrincipal;
	JLabel lblAtributoPrincipal;
	JTextArea textAreaListaValores;
	JLabel lblListaValores;
	private ArrayList<String[]> listaDeListas = new ArrayList<>();
	private JLabel lblListaAtributos;
	private JTextField textFieldListaAtributos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaComponentesListasValores frame = new VentanaComponentesListasValores();
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
	public VentanaComponentesListasValores() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 511);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblAtributos = new JLabel("Introduzca los atributos que quiera");
		lblAtributos.setVisible(false);
		lblAtributos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAtributos.setBounds(10, 145, 240, 27);
		contentPane.add(lblAtributos);
		
		btnComprobar = new JButton("Comprobar");
		btnComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnComprobar == e.getSource()) {
					Optional<Document> pacientes = controllerInterfaz.findByDni(formattedDni.getText());
					
					if(pacientes.isPresent()) {
						lblAtributoPrincipal.setVisible(true);
						textFieldAtributoPrincipal.setVisible(true);
					}else{
						String mensaje = "El paciente con DNI " + formattedDni.getText() + " no existe "; 
						JOptionPane.showMessageDialog(null, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnComprobar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnComprobar.setBounds(292, 30, 98, 27);
		contentPane.add(btnComprobar);
		
		try {
            mascara = new MaskFormatter("########?");
            mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            formattedDni = new JFormattedTextField(mascara);
    		formattedDni.setBounds(102, 32, 148, 26);
    		contentPane.add(formattedDni);
		} catch (ParseException e) {
            e.printStackTrace();
        }
		 lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDNI.setBounds(37, 18, 55, 40);
		contentPane.add(lblDNI);
		
		textFieldAtributos = new JTextField();
		textFieldAtributos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
    				String atributo = textFieldAtributos.getText().toString();
    				if(!atributo.equals("")) {
    					lblValores.setVisible(true);
    					textFieldValores.setVisible(true);
    				}else {
    					lblValores.setVisible(false);
    					textFieldValores.setVisible(false);
    				}
    			}
		});
		textFieldAtributos.setVisible(false);
		textFieldAtributos.setBounds(260, 148, 173, 25);
		contentPane.add(textFieldAtributos);
		textFieldAtributos.setColumns(10);
		
		textFieldValores = new JTextField();
		textFieldValores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String valores = textFieldValores.getText();
				if(!valores.matches(" ")) {
					lblListaAtributos.setVisible(true);
					textFieldListaAtributos.setVisible(true);
				}else {
					lblListaAtributos.setVisible(false);
					textFieldListaAtributos.setVisible(false);
				}
			}
		});
		textFieldValores.setVisible(false);
		textFieldValores.setColumns(10);
		textFieldValores.setBounds(260, 205, 173, 25);
		contentPane.add(textFieldValores);
		
		lblValores = new JLabel("Introduzca los valores que quiera");
		lblValores.setVisible(false);
		lblValores.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValores.setBounds(10, 202, 240, 27);
		contentPane.add(lblValores);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vac = new VentanaAnadirComponente();
				vac.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setBounds(118, 401, 102, 34);
		contentPane.add(btnCancelar);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dni = formattedDni.getText().toString();
				String atributoPrincipal = textFieldAtributoPrincipal.getText();
				String atributo = textFieldAtributos.getText();
				String valor = textFieldValores.getText();
				String lista = textAreaListaValores.getText();
				String atributoLista = textFieldListaAtributos.getText();
				String[] palabras = lista.split(",");
				for (int i = 0; i < palabras.length; i++) {
					listaDeListas.add(palabras[i].split(" "));
				}
				String[] atributosLista = atributoLista.split(" ");
				String[] atributos = atributo.split(" ");
				String [] listaValores = valor.split(" ");
				String[] listaAtributos = atributos;
				listaAtributos = atributosLista;
				
				
				Boolean anadido = controllerInterfaz.anadirComponente(dni, atributoPrincipal, atributos,listaValores,listaAtributos ,listaDeListas);
				textFieldMensaje.setText(anadido ? "El paciente ha sido actualizado correctamente" : "El paciente no se ha actualizado");

			} 
		});
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAceptar.setBounds(268, 401, 98, 34);
		contentPane.add(btnAceptar);
		
		textFieldMensaje = new JTextField();
		textFieldMensaje.setColumns(10);
		textFieldMensaje.setBounds(10, 445, 464, 19);
		contentPane.add(textFieldMensaje);
		
		lblAtributoPrincipal = new JLabel("Introduzca el atributo principal");
		lblAtributoPrincipal.setVisible(false);
		lblAtributoPrincipal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAtributoPrincipal.setBounds(10, 98, 219, 18);
		contentPane.add(lblAtributoPrincipal);
		
		textFieldAtributoPrincipal = new JTextField();
		textFieldAtributoPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String atributo = textFieldAtributoPrincipal.getText().toString();
				if(!atributo.equals("")) {
					lblAtributos.setVisible(true);
					textFieldAtributos.setVisible(true);
				}else {
					lblAtributos.setVisible(false);
					textFieldAtributos.setVisible(false);
				}
			}
		});
		textFieldAtributoPrincipal.setVisible(false);
		textFieldAtributoPrincipal.setBounds(260, 96, 173, 27);
		contentPane.add(textFieldAtributoPrincipal);
		textFieldAtributoPrincipal.setColumns(10);
		
		lblListaValores = new JLabel("Introduzca la lista de valores");
		lblListaValores.setVisible(false);
		lblListaValores.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblListaValores.setBounds(10, 331, 210, 27);
		contentPane.add(lblListaValores);
		
		textAreaListaValores = new JTextArea();
		textAreaListaValores.setVisible(false);
		textAreaListaValores.setBounds(253, 303, 210, 88);
		contentPane.add(textAreaListaValores);
		
		lblListaAtributos = new JLabel("Introduzca la lista de atributos");
		lblListaAtributos.setVisible(false);
		lblListaAtributos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblListaAtributos.setBounds(10, 257, 210, 27);
		contentPane.add(lblListaAtributos);
		
		textFieldListaAtributos = new JTextField();
		textFieldListaAtributos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String valores = textFieldListaAtributos.getText();
				if(!valores.matches(" ")) {
					lblListaValores.setVisible(true);
					textAreaListaValores.setVisible(true);
				}else {
					lblListaValores.setVisible(false);
					textAreaListaValores.setVisible(false);
				}
			}
		});
		textFieldListaAtributos.setVisible(false);
		textFieldListaAtributos.setBounds(260, 259, 190, 27);
		contentPane.add(textFieldListaAtributos);
		textFieldListaAtributos.setColumns(10);
	}
}