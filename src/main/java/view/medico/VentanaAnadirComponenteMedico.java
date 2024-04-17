package view.medico;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAnadirComponenteMedico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JButton btn_List;
	JButton btn_Valor;
	JButton btn_List_Valor;
	JLabel lblNewLabelTitle;
	JButton btnCancelar;
	VentanaOpcionAnadirMedico voam;
	VentanaComponentesListaMedico vclm;
	VentanaComponentesValoresMedico vcvm;
	VentanaComponentesListasValoresMedico vclvm;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAnadirComponenteMedico frame = new VentanaAnadirComponenteMedico();
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
	public VentanaAnadirComponenteMedico() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 363);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btn_List = new JButton("Listas");
		btn_List.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vclm = new VentanaComponentesListaMedico();
				vclm.setVisible(true);
				dispose();
			}
		});
		btn_List.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_List.setBounds(168, 134, 114, 47);
		contentPane.add(btn_List);
		
		btn_Valor = new JButton("Valores\r\n");
		btn_Valor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vcvm = new VentanaComponentesValoresMedico();
				vcvm.setVisible(true);
				dispose();
			}
		});
		btn_Valor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_Valor.setBounds(303, 134, 114, 47);
		contentPane.add(btn_Valor);
		
		btn_List_Valor = new JButton("Listas y valores");
		btn_List_Valor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vclvm = new VentanaComponentesListasValoresMedico();
				vclvm.setVisible(true);
				dispose();
			}
		});
		btn_List_Valor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_List_Valor.setBounds(43, 134, 114, 47);
		contentPane.add(btn_List_Valor);
		
		lblNewLabelTitle = new JLabel("Añadir componentes a medico\r\n");
		lblNewLabelTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabelTitle.setBounds(124, 38, 250, 40);
		contentPane.add(lblNewLabelTitle);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				voam = new VentanaOpcionAnadirMedico();
				voam.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelar.setBounds(168, 213, 114, 47);
		contentPane.add(btnCancelar);
	}
}
