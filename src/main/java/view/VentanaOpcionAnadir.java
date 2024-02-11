package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaOpcionAnadir extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel lblNewLabel;
	JButton btnAnadirPaciente;
	JButton btnAnadirAtributo;
	JButton btnAnadirHistorialMedico;
	VentanaAgregarPaciente vap;
	VentanaAnadirAtributoNuevo van;
	VentanaPrincipal vp;
	private JButton btnSalir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaOpcionAnadir frame = new VentanaOpcionAnadir();
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
	public VentanaOpcionAnadir() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 473, 331);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Que desea añadir");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(120, 25, 178, 34);
		contentPane.add(lblNewLabel);
		
		btnAnadirPaciente = new JButton("Añadir nuevo paciente");
		btnAnadirPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vap = new VentanaAgregarPaciente();
				vap.setVisible(true);
			}
		});
		btnAnadirPaciente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAnadirPaciente.setBounds(21, 95, 171, 34);
		contentPane.add(btnAnadirPaciente);
		
		btnAnadirAtributo = new JButton("Añadir atributo nuevo");
		btnAnadirAtributo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnAnadirAtributo == e.getSource()) {
					van = new VentanaAnadirAtributoNuevo();
					van.setVisible(true);
					dispose();
				}
			}
		});
		btnAnadirAtributo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAnadirAtributo.setBounds(21, 161, 171, 34);
		contentPane.add(btnAnadirAtributo);
		
		btnAnadirHistorialMedico = new JButton("Añadir historial medico\r\n");
		btnAnadirHistorialMedico.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAnadirHistorialMedico.setBounds(239, 95, 171, 34);
		contentPane.add(btnAnadirHistorialMedico);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnSalir == e.getSource()) {
					vp = new VentanaPrincipal();
					vp.setVisible(true);
					dispose();
				}
			}
		});
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSalir.setBounds(239, 166, 171, 26);
		contentPane.add(btnSalir);
	}
}
