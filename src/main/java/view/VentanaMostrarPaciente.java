package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.bson.Document;

import repositories.PacientesRepositories.PacienteRepositoryImpl;
import java.awt.Color;
import javax.swing.JScrollBar;

public class VentanaMostrarPaciente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final PacienteRepositoryImpl pacienteRepositoryImpl = new PacienteRepositoryImpl();
	private final PacienteView pacienteView = new PacienteView();
	JButton btnMostrarTodo;
	JButton btnMostrarPorNombre;
	JButton btnMostrarPorDni;
	JLabel lblMensaje;
	private JTextField textFieldMostrar;
	private JTextField textFieldEscribir;
	private JButton btnCancelar;
	VentanaPrincipal vp;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaMostrarPaciente frame = new VentanaMostrarPaciente();
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
	public VentanaMostrarPaciente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblMensaje = new JLabel("Introduzca el nombre");
		lblMensaje.setVisible(false);
		lblMensaje.setBounds(229, 31, 137, 13);
		contentPane.add(lblMensaje);
		
		textFieldEscribir = new JTextField();
		textFieldEscribir.setVisible(false);
		textFieldEscribir.setBounds(229, 54, 176, 23);
		contentPane.add(textFieldEscribir);
		textFieldEscribir.setColumns(10);
		
		textFieldMostrar = new JTextField();
		textFieldMostrar.setBounds(192, 10, 234, 243);
		contentPane.add(textFieldMostrar);
		textFieldMostrar.setColumns(10);
		
		btnMostrarTodo = new JButton("Mostrar todo");
		btnMostrarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnMostrarTodo == e.getSource()) {
						textFieldMostrar.setText("");
						List<Document> pacientes = pacienteRepositoryImpl.findAll();
						textFieldMostrar.setText(pacienteView.mostrarPacientes(pacientes));
				}
			}
		});
		btnMostrarTodo.setBounds(20, 43, 149, 23);
		contentPane.add(btnMostrarTodo);
		
		btnMostrarPorNombre = new JButton("Mostrar por nombre\r\n");
		btnMostrarPorNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnMostrarPorNombre == e.getSource()) {
					lblMensaje.setText("Introduzca el nombre");
					lblMensaje.setVisible(true);
					textFieldEscribir.setVisible(true);
					textFieldEscribir.setText("");
					textFieldMostrar.setText("");
					
				}
			}
		});
		btnMostrarPorNombre.setBounds(20, 160, 149, 23);
		contentPane.add(btnMostrarPorNombre);
		textFieldEscribir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = textFieldEscribir.getText();
				if(!nombre.equals("")) {
					lblMensaje.setVisible(false);
					textFieldEscribir.setVisible(false);
					List<Document> pacientes = pacienteRepositoryImpl.findByNombre(nombre);
					textFieldMostrar.setText(pacienteView.mostrarPacientes(pacientes));
				}
			}
		});
		
		btnMostrarPorDni = new JButton("Mostrar por DNI\r\n");
		btnMostrarPorDni.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        lblMensaje.setText("Introduzca el DNI");
		        lblMensaje.setVisible(true);
		        textFieldEscribir.setVisible(true);
		        textFieldEscribir.setText("");
		        textFieldMostrar.setText("");
		        
		    }
		});
		btnMostrarPorDni.setBounds(20, 101, 149, 23);
		contentPane.add(btnMostrarPorDni);

		textFieldEscribir.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String dni = textFieldEscribir.getText();
		        if (dni.matches("^\\d{8}[A-Z]$")) {
		            lblMensaje.setVisible(false);
		            textFieldEscribir.setVisible(false);
		            Optional<Document> pacientes = pacienteRepositoryImpl.findById(dni);
		            textFieldMostrar.setText(pacienteView.mostrar(pacientes));
		        } else {
		            textFieldMostrar.setText("El dni debe tener este formato :00000000A ");
		        }
		    }
		});
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(192, 10, 17, 243);
		contentPane.add(scrollBar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vp = new VentanaPrincipal();
				vp.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(20, 208, 149, 21);
		contentPane.add(btnCancelar);
		
		
	}
}
