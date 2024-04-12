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

public class VentanaOpcionAnadirMedico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel lblNewLabel;
	JButton btnAadirNuevoMedico;
	JButton btnAnadirAtributo;
	JButton btnAadirPacientesA;
	JButton btnSalir;
	JButton btnAadirComponete;
	JButton btnAnadirLista;
	VentanaPrincipalMedico vpm;
	VentanaAnadirMedico vam;
	VentanaAnadirAtributoNuevoMedico vaanm;
	VentanaAnadirPacientesCargo vapc;
	VentanaAnadirComponenteMedico vacm;
	VentanaAnadirListaMedico valm;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaOpcionAnadirMedico frame = new VentanaOpcionAnadirMedico();
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
	public VentanaOpcionAnadirMedico() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 351);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Que desea añadir");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(160, 10, 199, 39);
		contentPane.add(lblNewLabel);
		
		btnAadirNuevoMedico = new JButton("Añadir nuevo medico\r\n");
		btnAadirNuevoMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vam = new VentanaAnadirMedico();
				vam.setVisible(true);
				dispose();
			}
		});
		btnAadirNuevoMedico.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAadirNuevoMedico.setBounds(33, 66, 171, 34);
		contentPane.add(btnAadirNuevoMedico);
		
		btnAnadirAtributo = new JButton("Añadir atributo nuevo");
		btnAnadirAtributo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vaanm = new VentanaAnadirAtributoNuevoMedico();
				vaanm.setVisible(true);
				dispose();
			}
		});
		btnAnadirAtributo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAnadirAtributo.setBounds(33, 132, 171, 34);
		contentPane.add(btnAnadirAtributo);
		
		btnAadirPacientesA = new JButton("Añadir pacientes a cargo\r\n\r\n");
		btnAadirPacientesA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vapc = new VentanaAnadirPacientesCargo();
				vapc.setVisible(true);
				dispose();
			}
		});
		btnAadirPacientesA.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAadirPacientesA.setBounds(251, 66, 171, 34);
		contentPane.add(btnAadirPacientesA);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vpm = new VentanaPrincipalMedico();
				vpm.setVisible(true);
				dispose();
			}
		});
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSalir.setBounds(251, 197, 171, 34);
		contentPane.add(btnSalir);
		
		btnAadirComponete = new JButton("Añadir Componente");
		btnAadirComponete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vacm = new VentanaAnadirComponenteMedico();
				vacm.setVisible(true);
				dispose();
			}
		});
		btnAadirComponete.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAadirComponete.setBounds(251, 132, 171, 34);
		contentPane.add(btnAadirComponete);
		
		btnAnadirLista = new JButton("Añadir lista");
		btnAnadirLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				valm = new VentanaAnadirListaMedico();
				valm.setVisible(true);
				dispose();
			}
		});
		btnAnadirLista.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAnadirLista.setBounds(33, 197, 171, 34);
		contentPane.add(btnAnadirLista);
	}

}
