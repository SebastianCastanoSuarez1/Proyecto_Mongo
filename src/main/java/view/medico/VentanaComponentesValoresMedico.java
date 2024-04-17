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

import controller.MedicoController_Interfaz;

public class VentanaComponentesValoresMedico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	MaskFormatter mascara;
	JButton btnComprobar;
	JFormattedTextField formattedDni;
	JLabel lblDNI;
	private final MedicoController_Interfaz medicoInterfaz = new MedicoController_Interfaz();
	VentanaAnadirComponenteMedico vac;
	private JLabel lblAtributo;
	private JTextField textFieldAtributo;
	private JLabel lblValores;
	private JTextField textFieldValores;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JLabel lblAtributoPrincipal;
	private JTextField textFieldAtributoPrincipal;
	private JLabel lblMensaje;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaComponentesValoresMedico frame = new VentanaComponentesValoresMedico();
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
	public VentanaComponentesValoresMedico() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 367);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnComprobar = new JButton("Comprobar");
		btnComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnComprobar == e.getSource()) {
					Optional<Document> medicos = medicoInterfaz.findByDni(formattedDni.getText());

					if (medicos.isPresent()) {
						lblAtributoPrincipal.setVisible(true);
						textFieldAtributoPrincipal.setVisible(true);
					} else {
						String mensaje = "El medico con DNI " + formattedDni.getText() + " no existe ";
						JOptionPane.showMessageDialog(null, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnComprobar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnComprobar.setBounds(294, 33, 98, 27);
		contentPane.add(btnComprobar);
		
		try {
			mascara = new MaskFormatter("########?");
			mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			formattedDni = new JFormattedTextField(mascara);
			formattedDni.setBounds(126, 35, 148, 26);
			contentPane.add(formattedDni);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDNI.setBounds(61, 24, 55, 40);
		contentPane.add(lblDNI);
		
		lblAtributo = new JLabel("Introduzca el nombre del atributo");
		lblAtributo.setVisible(false);
		lblAtributo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAtributo.setBounds(20, 139, 218, 19);
		contentPane.add(lblAtributo);
		
		textFieldAtributo = new JTextField();
		textFieldAtributo.setVisible(false);
		textFieldAtributo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String atributo = textFieldAtributo.getText();
				if(!atributo.matches(" ")) {
					lblValores.setVisible(true);
					textFieldValores.setVisible(true);
				}else {
					lblValores.setVisible(true);
					textFieldValores.setVisible(true);
				}
			}
		});
		textFieldAtributo.setBounds(245, 141, 137, 19);
		contentPane.add(textFieldAtributo);
		textFieldAtributo.setColumns(10);
		
		lblValores = new JLabel("Introduzca los valores del atributo\r\n");
		lblValores.setVisible(false);
		lblValores.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValores.setBounds(20, 181, 218, 19);
		contentPane.add(lblValores);
		
		textFieldValores = new JTextField();
		textFieldValores.setVisible(false);
		textFieldValores.setColumns(10);
		textFieldValores.setBounds(245, 183, 137, 19);
		contentPane.add(textFieldValores);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dni = formattedDni.getText();
				String atributoPrincipal = textFieldAtributoPrincipal.getText();
				String atributo = textFieldAtributo.getText();
				String valores = textFieldValores.getText();
				String[] componenteAtributo = atributo.split(" ");
				String[] componenteValor = valores.split(" ");
				Boolean anadido = medicoInterfaz.anadirComponente(dni, atributoPrincipal, componenteAtributo, componenteValor);
				if(anadido == true) {
					lblMensaje.setText("El medico ha sido añadido con exito");
					lblMensaje.setForeground(Color.GREEN);
				}else {
					lblMensaje.setText("El medico no ha sido añadido con exito");
					lblMensaje.setForeground(Color.RED);
				}

			}
		});
		btnAceptar.setBounds(246, 245, 85, 27);
		contentPane.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vac = new VentanaAnadirComponenteMedico();
				vac.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelar.setBounds(86, 244, 95, 27);
		contentPane.add(btnCancelar);
		
		lblAtributoPrincipal = new JLabel("Introduzca el atributo principal");
		lblAtributoPrincipal.setVisible(false);
		lblAtributoPrincipal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAtributoPrincipal.setBounds(20, 91, 213, 19);
		contentPane.add(lblAtributoPrincipal);
		
		textFieldAtributoPrincipal = new JTextField();
		textFieldAtributoPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String atributoPrincipal = textFieldAtributoPrincipal.getText();
				if(!atributoPrincipal.matches(" ")) {
					lblAtributo.setVisible(true);
					textFieldAtributo.setVisible(true);
				}else {
					lblAtributo.setVisible(true);
					textFieldAtributo.setVisible(true);
				}
			}
		});
		textFieldAtributoPrincipal.setVisible(false);
		textFieldAtributoPrincipal.setBounds(245, 93, 137, 19);
		contentPane.add(textFieldAtributoPrincipal);
		textFieldAtributoPrincipal.setColumns(10);
		
		lblMensaje = new JLabel("");
		lblMensaje.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMensaje.setBounds(10, 302, 443, 25);
		contentPane.add(lblMensaje);
	}
}