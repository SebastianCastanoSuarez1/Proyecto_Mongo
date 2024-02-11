package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

public class VentanaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	VentanaPrincipal vp;
	JPasswordField passwordField;
	JLabel lblContraseña;
	JButton btnNewAceptar;
	JButton btnSalir;
	JMenuBar menuBar;
	JMenu mnNewMenu;
	JMenuItem mntmNewMenuItem;
	JLabel lblUsuario;
	JFormattedTextField formattedUsuario;
	String usuario = "11111111H";

	MaskFormatter mascara;
	Boolean esAdmin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin frame = new VentanaLogin();
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
	public VentanaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 445, 439);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsuario.setBounds(45, 91, 79, 14);
		contentPane.add(lblUsuario);

		passwordField = new JPasswordField();
		passwordField.setBounds(162, 175, 144, 32);
		contentPane.add(passwordField);

		lblContraseña = new JLabel("Contraseña");
		lblContraseña.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblContraseña.setBounds(31, 180, 82, 25);
		contentPane.add(lblContraseña);

		 try {
	            mascara = new MaskFormatter("##.###.###-?");
	            mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
	            formattedUsuario = new JFormattedTextField(mascara);
	            formattedUsuario.setBounds(162, 88, 144, 32);
	    		contentPane.add(formattedUsuario);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
		

		

		btnNewAceptar = new JButton("Aceptar");
		btnNewAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String contraseña = new String(passwordField.getPassword());
                String ausuario = quitarPuntosGuion(formattedUsuario.getText());
                contraseña = quitarPuntosGuion(contraseña);
				
				if (btnNewAceptar == e.getSource()) {
					if (ausuario.equals(usuario) && contraseña.equals(usuario)) {
						vp = new VentanaPrincipal();
						vp.setVisible(true);
						
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "Usuario o contrseña incorrecto",
								"No puede elegir un vuelo", JOptionPane.ERROR_MESSAGE);

					}

				}
			}
		});
		btnNewAceptar.setBounds(133, 306, 89, 23);
		contentPane.add(btnNewAceptar);

		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(253, 306, 89, 23);
		contentPane.add(btnSalir);

	}
	  private static String quitarPuntosGuion(String original) {
	        return original.replaceAll("[.\\-]", "");
	    }


}
