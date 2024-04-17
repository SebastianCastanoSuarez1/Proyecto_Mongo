package view.medico;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import com.mongodb.client.result.DeleteResult;

import controller.MedicoController_Interfaz;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;

public class VentanaEliminarMedico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	MedicoController_Interfaz medico = new MedicoController_Interfaz();
	JFormattedTextField formattedDNI;
	JLabel lblDNI;
	MaskFormatter mascara;
	JButton btnEliminar;
	JButton btnCancelar;
	VentanaPrincipalMedico vpm;
	private JLabel lblMensaje;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEliminarMedico frame = new VentanaEliminarMedico();
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
	public VentanaEliminarMedico() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 462, 316);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblDNI = new JLabel("Introduzca DNI del medico\r\n\r\n");
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDNI.setBounds(31, 58, 218, 31);
		contentPane.add(lblDNI);
		
		try {
			mascara = new MaskFormatter("########?");
			mascara.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			formattedDNI = new JFormattedTextField(mascara);
			formattedDNI.setBounds(235, 60, 144, 32);
			contentPane.add(formattedDNI);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		btnEliminar = new JButton("Eliminar\r\n");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnEliminar == e.getSource()) {
					String dni = formattedDNI.getText().toString();

					DeleteResult eliminado = medico.eliminarMedico(dni);
					if (eliminado.getDeletedCount() > 0) {
						lblMensaje.setText("Medico con dni: " + dni + " ha sido eliminado");
						lblMensaje.setForeground(Color.GREEN);
					} else {
						lblMensaje.setText("Medico con dni:" + dni + " no ha sido eliminado");
						lblMensaje.setForeground(Color.RED);

					}

				}
			}
		});
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEliminar.setBounds(259, 162, 119, 39);
		contentPane.add(btnEliminar);
		
		btnCancelar = new JButton("Cancelar\r\n");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vpm = new VentanaPrincipalMedico();
				vpm.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancelar.setBounds(87, 162, 112, 39);
		contentPane.add(btnCancelar);
		
		lblMensaje = new JLabel("\r\n");
		lblMensaje.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMensaje.setBounds(64, 236, 315, 19);
		contentPane.add(lblMensaje);
	}

}
