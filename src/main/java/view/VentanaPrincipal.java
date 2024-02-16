package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JButton btnAgregar;
	JButton btnEliminarPaciente;
	JButton btnModificar;
	JButton btnMostrar;
	VentanaOpcionAnadir voa;
	VentanaMostrarPaciente vmp;
	VentanaModificarPaciente vmop;
	VentanaEliminarPaciente vep;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
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
	public VentanaPrincipal() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnAgregar = new JButton("Agregar paciente");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnAgregar == e.getSource()) {
					voa = new VentanaOpcionAnadir();
					voa.setVisible(true);
					dispose();
				}
			}
		});
		btnAgregar.setBounds(48, 91, 136, 23);
		contentPane.add(btnAgregar);
		
		btnEliminarPaciente = new JButton("Eliminar paciente");
		btnEliminarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnEliminarPaciente == e.getSource()) {
					vep = new VentanaEliminarPaciente();
					vep.setVisible(true);
					dispose();

				}
			}
		});
		btnEliminarPaciente.setBounds(236, 91, 148, 23);
		contentPane.add(btnEliminarPaciente);
		
		btnModificar = new JButton("Modificar paciente");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnModificar == e.getSource()) {
					vmop = new VentanaModificarPaciente();
					vmop.setVisible(true);
					dispose();

				}
			}
		});
		btnModificar.setBounds(236, 168, 148, 23);
		contentPane.add(btnModificar);
		
		btnMostrar = new JButton("Mostrar paciente");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnMostrar == e.getSource()) {
					vmp = new VentanaMostrarPaciente();
					vmp.setVisible(true);
					dispose();

				}
			}
		});
		btnMostrar.setBounds(48, 168, 136, 23);
		contentPane.add(btnMostrar);
		
		JLabel lblNewLabel = new JLabel("Clinica DABAS\r\n");
		lblNewLabel.setBackground(new Color(255, 255, 128));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(145, 10, 148, 45);
		contentPane.add(lblNewLabel);
	}
}
