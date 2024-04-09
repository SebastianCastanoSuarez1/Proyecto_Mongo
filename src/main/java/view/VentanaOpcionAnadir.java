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
	VentanaPrincipalPaciente vp;
	private JButton btnSalir;
	private JButton btnAadirComponete;
	VentanaLista vl;

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
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 473, 331);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNewLabel = new JLabel("Que desea añadir");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(131, 22, 178, 34);
		contentPane.add(lblNewLabel);

		btnAnadirPaciente = new JButton("Añadir nuevo paciente");
		btnAnadirPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vap = new VentanaAgregarPaciente();
				vap.setVisible(true);
				dispose();
			}
		});
		btnAnadirPaciente.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAnadirPaciente.setBounds(21, 95, 171, 34);
		contentPane.add(btnAnadirPaciente);

		btnAnadirAtributo = new JButton("Añadir atributo nuevo");
		btnAnadirAtributo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnAnadirAtributo == e.getSource()) {
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
		btnAnadirHistorialMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnAnadirHistorialMedico == e.getSource()) {
					VentanaAñadirHistorialMedico va = new VentanaAñadirHistorialMedico();
					va.setVisible(true);
					dispose();
				}
			}
		});
		btnAnadirHistorialMedico.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAnadirHistorialMedico.setBounds(239, 95, 171, 34);
		contentPane.add(btnAnadirHistorialMedico);

		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnSalir == e.getSource()) {
					vp = new VentanaPrincipalPaciente();
					vp.setVisible(true);
					dispose();
				}
			}
		});
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSalir.setBounds(239, 226, 171, 34);
		contentPane.add(btnSalir);

		btnAadirComponete = new JButton("Añadir Componente");
		btnAadirComponete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAnadirComponente vae = new VentanaAnadirComponente();
				vae.setVisible(true);
				dispose();
			}
		});
		btnAadirComponete.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAadirComponete.setBounds(239, 161, 171, 34);
		contentPane.add(btnAadirComponete);
		
		JButton btnAnadirLista = new JButton("Añadir lista");
		btnAnadirLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vl = new VentanaLista();
				vl.setVisible(true);
				dispose();
			}
		});
		btnAnadirLista.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAnadirLista.setBounds(21, 226, 171, 34);
		contentPane.add(btnAnadirLista);
	}
}
