package view;

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

import repositories.PacientesRepositories.PacienteRepositoryImpl;

public class VentanaModificarPaciente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JComboBox<String> comboBoxAtributo;
	private JLabel lblValor;
	JFormattedTextField formattedDni;
	private JButton btnComprobar;
	private final PacienteRepositoryImpl pacienteRepositoryImpl = new PacienteRepositoryImpl();
	MaskFormatter mascara;
	JLabel lblDNI;
	JLabel lblTexto;
	private JTextField textFieldMensaje;
	private JButton btnCancelar;
	private JButton btnAceptar;
	VentanaPrincipal vp;

	private JTextField textFieldValorAtributo;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaModificarPaciente frame = new VentanaModificarPaciente();
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
	public VentanaModificarPaciente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTexto = new JLabel("Que atributo quiere modificar");
		lblTexto.setVisible(false);
		lblTexto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTexto.setBounds(10, 60, 235, 30);
		contentPane.add(lblTexto);
		
		comboBoxAtributo = new JComboBox<String>();
		comboBoxAtributo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblValor.setVisible(true);
				textFieldValorAtributo.setVisible(true);
			}
		});
		comboBoxAtributo.setVisible(false);
		comboBoxAtributo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxAtributo.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Dni", "Nombre", "Apellidos", "Fecha_Nacimiento",
						"Sexo", "Lugar_Nacimiento", "Altura", "Peso", "Grupo_Sanguineo", "Enfemedad", "Tipo" }));

		comboBoxAtributo.setBounds(255, 65, 136, 21);
		contentPane.add(comboBoxAtributo);
		
		lblValor = new JLabel("Introduzca el nuevo valor del atributo\r\n");
		lblValor.setVisible(false);
		lblValor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblValor.setBounds(10, 100, 286, 21);
		contentPane.add(lblValor);
		
		lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDNI.setBounds(10, 10, 55, 40);
		contentPane.add(lblDNI);
		
		try {
            mascara = new MaskFormatter("########?");
            mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            formattedDni = new JFormattedTextField(mascara);
    		formattedDni.setBounds(81, 21, 148, 26);
    		contentPane.add(formattedDni);
    		
    		
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
		btnComprobar = new JButton("Comprobar");
		btnComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnComprobar == e.getSource()) {
					Optional<Document> pacientes = pacienteRepositoryImpl.findById(formattedDni.getText());
					
					if(pacientes.isPresent()) {
						lblTexto.setVisible(true);
						comboBoxAtributo.setVisible(true);
					}else{
						String mensaje = "El paciente con DNI " + formattedDni.getText() + " no existe "; 
						JOptionPane.showMessageDialog(null, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnComprobar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnComprobar.setBounds(268, 19, 98, 27);
		contentPane.add(btnComprobar);
		
		textFieldMensaje = new JTextField();
		textFieldMensaje.setBounds(10, 234, 416, 19);
		contentPane.add(textFieldMensaje);
		textFieldMensaje.setColumns(10);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp = new VentanaPrincipal();
				vp.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(81, 181, 85, 21);
		contentPane.add(btnCancelar);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnAceptar == e.getSource()) {
					String atributo = comboBoxAtributo.getSelectedItem().toString();
					
			 		switch (atributo) {
					case "Dni":
						valorAtributoNuevo(atributo);
						break;
					case "Nombre":
						valorAtributoNuevo(atributo);
						break;
					case "Apellidos":
						valorAtributoNuevo(atributo);
						break;
					case "Fecha_Nacimiento":
						valorAtributoNuevo(atributo);
						break;
					case "Sexo":
						valorAtributoNuevo(atributo);
						break;
					case "Lugar_Nacimiento":
						valorAtributoNuevo(atributo);
						break;
					case "Altura":
						valorAtributoNuevo(atributo);
						break;
					case "Peso":
						valorAtributoNuevo(atributo);
						break;
					case "Grupo_Sanguineo":
						valorAtributoNuevo(atributo);
						break;
					case "Enfermedad":
						valorAtributoNuevo(atributo);
						break;
					case "Tipo":
						valorAtributoNuevo(atributo);
						break;
					default:
						break;
					}
				}
			}
			protected void valorAtributoNuevo(String atributo) {
				Optional<Document> pacientes;
				lblValor.setVisible(true);
				textFieldValorAtributo.setVisible(true);
				pacientes = pacienteRepositoryImpl.findById(formattedDni.getText());
				Boolean actualizado = pacienteRepositoryImpl.update(pacientes, atributo, textFieldValorAtributo.getText().toString());
				textFieldMensaje.setText(actualizado ? "El paciente ha sido actualizado correctamente" : "El paciente no se ha actualizado");
			}
		});
		btnAceptar.setBounds(233, 181, 98, 21);
		contentPane.add(btnAceptar);
		
		textFieldValorAtributo = new JTextField();
		textFieldValorAtributo.setVisible(false);
		textFieldValorAtributo.setBounds(10, 131, 258, 19);
		contentPane.add(textFieldValorAtributo);
		textFieldValorAtributo.setColumns(10);
		
		
	}
}
