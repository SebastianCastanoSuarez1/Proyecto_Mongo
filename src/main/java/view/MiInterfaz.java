package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class MiInterfaz extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton boton;
    private JTextField campoTexto;

    public MiInterfaz() {
        setLayout(new FlowLayout());

        boton = new JButton("Guardar");
        add(boton);

        campoTexto = new JTextField(10);
        add(campoTexto);

        event e = new event();
        boton.addActionListener(e);
    }

    public class event implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String[] atributo = {"atributo1", "atributo2"};
            String[] valores = {campoTexto.getText(), "valor2"};
            String[] atributoLista = {"atributoLista1"};
            ArrayList<String[]> listas = new ArrayList<String[]>();
            listas.add(new String[]{"lista1", "lista2"});

            // Aquí es donde llamarías a tus métodos
            // Controller_Interfaz.anadirElementosComponente(atributo, valores, paciente);
            // Controller_Interfaz.anadirListaComponente(atributoLista, listas, paciente);
        }
    }

    public static void main(String args[]) {
        MiInterfaz interfaz = new MiInterfaz();
        interfaz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        interfaz.setSize(300, 200);
        interfaz.setVisible(true);
    }
}