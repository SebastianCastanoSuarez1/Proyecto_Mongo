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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import com.mongodb.client.result.DeleteResult;

import controller.Controller_Interfaz;

public class VentanaEliminarPaciente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Controller_Interfaz controllerInterfaz = new Controller_Interfaz();

	JFormattedTextField formattedDNI;
	JLabel lblDNI;
	MaskFormatter mascara;
	JButton btnEliminar;
	JButton btnCancelar;
	private JTextField textFieldMensaje;
	VentanaPrincipal vp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEliminarPaciente frame = new VentanaEliminarPaciente();
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
	public VentanaEliminarPaciente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblDNI = new JLabel("Introduzca DNI\r\n");
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDNI.setBounds(24, 65, 126, 31);
		contentPane.add(lblDNI);

		try {
			mascara = new MaskFormatter("########?");
			mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			formattedDNI = new JFormattedTextField(mascara);
			formattedDNI.setBounds(160, 67, 144, 32);
			contentPane.add(formattedDNI);

			btnEliminar = new JButton("Eliminar\r\n");
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (btnEliminar == e.getSource()) {
						String dni = formattedDNI.getText().toString();

						DeleteResult eliminado = controllerInterfaz.eliminarPaciente(dni);
						if (eliminado.getDeletedCount() > 0) {
							textFieldMensaje.setText("Paciente con dni: " + dni + " ha sido eliminado");
						} else {
							textFieldMensaje.setText("Paciente con dni:" + dni + " no ha sido eliminado");
						}

					}
				}
			});
			btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnEliminar.setBounds(220, 169, 119, 39);
			contentPane.add(btnEliminar);

			btnCancelar = new JButton("Cancelar\r\n");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					vp = new VentanaPrincipal();
					vp.setVisible(true);
					dispose();
				}
			});
			btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnCancelar.setBounds(49, 169, 119, 39);
			contentPane.add(btnCancelar);

			textFieldMensaje = new JTextField();
			textFieldMensaje.setBounds(0, 234, 436, 19);
			contentPane.add(textFieldMensaje);
			textFieldMensaje.setColumns(10);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
