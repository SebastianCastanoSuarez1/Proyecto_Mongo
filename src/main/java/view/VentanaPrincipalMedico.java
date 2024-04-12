package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipalMedico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	VentanaEliminarMedico vem;
	VentanaOpcionAnadirMedico voam;
	VentanaMostrarMedico vmm;
	JButton btnAgregarMedico;
	JButton btnMostrarMedico;
	JButton btnModificarMedico;
	JButton btnEliminarMedico;
	JLabel lblTitulo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipalMedico frame = new VentanaPrincipalMedico();
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
	public VentanaPrincipalMedico() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 323);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnAgregarMedico = new JButton("Agregar medico");
		btnAgregarMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				voam = new VentanaOpcionAnadirMedico();
				voam.setVisible(true);
				dispose();
			}
		});
		btnAgregarMedico.setBounds(67, 83, 127, 27);
		contentPane.add(btnAgregarMedico);
		
		btnMostrarMedico = new JButton("Mostrar medico");
		btnMostrarMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vmm = new VentanaMostrarMedico();
				vmm.setVisible(true);
				dispose();
			}
		});
		btnMostrarMedico.setBounds(67, 187, 127, 24);
		contentPane.add(btnMostrarMedico);
		
		btnModificarMedico = new JButton("Modificar medico");
		btnModificarMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModificarMedico.setBounds(245, 187, 133, 27);
		contentPane.add(btnModificarMedico);
		
		btnEliminarMedico = new JButton("Eliminar medico");
		btnEliminarMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vem = new VentanaEliminarMedico();
				vem.setVisible(true);
				dispose();
			}
		});
		btnEliminarMedico.setBounds(251, 83, 127, 27);
		contentPane.add(btnEliminarMedico);
		
		lblTitulo = new JLabel("Clinica DABAS\r\n");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitulo.setBounds(177, 28, 114, 27);
		contentPane.add(lblTitulo);
	}

}
