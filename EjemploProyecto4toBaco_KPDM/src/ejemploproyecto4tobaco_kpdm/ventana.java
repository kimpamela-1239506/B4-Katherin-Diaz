package ejemploproyecto4tobaco_kpdm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ventana extends JFrame {

    //creando variables universales
    JPanel panelInicioSesion, panelNuevoUsuario, panelClientes;
    JTextField txtUsuario;
    JPasswordField txtContra;
    usuario misUsuarios[] = new usuario[10];

    public ventana() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        misUsuarios[0] = new usuario("admin", "123");
        misUsuarios[1] = new usuario("otro", "321");
    }

    public void iniciarComponentes() {
        panelInicioSesion = new JPanel();
        panelInicioSesion.setLayout(null);
        this.getContentPane().add(panelInicioSesion);
        componentesInicioSesion();

        panelNuevoUsuario = new JPanel();
        panelNuevoUsuario.setLayout(null);
        this.getContentPane().add(panelNuevoUsuario);
        panelNuevoUsuario.setVisible(false);
        
        panelClientes = new JPanel();
        panelClientes.setLayout(null);
        this.getContentPane().add(panelClientes);
        panelClientes.setVisible(false);
    }

    public void componentesInicioSesion() { 
        this.setTitle("Inicio sesion");// Componentes de Iinicio usuario
        JLabel lblUsuario = new JLabel("Enter your username");
        lblUsuario.setBounds(95, 15, 150, 15);//dimensiones
        panelInicioSesion.add(lblUsuario);//añadir a la ventana

        JLabel lblcontra = new JLabel("Enter your password");
        lblcontra.setBounds(95, 75, 150, 15);
        panelInicioSesion.add(lblcontra);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(250, 15, 150, 25);
        panelInicioSesion.add(txtUsuario);

        txtContra = new JPasswordField();
        txtContra.setBounds(250, 75, 150, 25);
        panelInicioSesion.add(txtContra);

        JButton btnIniciar = new JButton("Log in");
        btnIniciar.setBounds(100, 150, 100, 30);
        panelInicioSesion.add(btnIniciar);

        ActionListener iniciarSesion = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText();
                String contra = txtContra.getText();
                buscarUsuario(usuario, contra);
            }
        };
        btnIniciar.addActionListener(iniciarSesion);

        // -----------------> boton de Nuevo Usuario
        JButton btnNuevoUsuario = new JButton("Sign in");
        btnNuevoUsuario.setBounds(250, 150, 100, 30);
        panelInicioSesion.add(btnNuevoUsuario);
        ActionListener nuevoUsuario = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                componentesNuevoUsuario();
                panelInicioSesion.setVisible(false);
                panelNuevoUsuario.setVisible(true);
            }

        };
        btnNuevoUsuario.addActionListener(nuevoUsuario);

        panelInicioSesion.repaint();
    }

    public void buscarUsuario(String usuario, String contra) {
        boolean encontrado = false;//se vuelve falso para cuando se encuentre cambie
        String nombre = "";
        for (int i = 0; i <= misUsuarios.length - 1; i++) {
            if (misUsuarios[i] != null) {//si es diferente a null
                if (misUsuarios[i].getNombre().equals(usuario) && misUsuarios[i].getcontra().equals(contra)) {
                    encontrado = true;//si se encuentra se convierte en true
                    nombre = misUsuarios[i].getNombre();
                    break;
                }
            }
        }
        if (encontrado == true) { //si el valor es verdadero
            JOptionPane.showMessageDialog(null, " WELCOME " + nombre + "!");
        } else {
            JOptionPane.showMessageDialog(null, " ¡INCORRECT DATA! ");
        }
    }

    public void componentesNuevoUsuario() { // se crea el componente para registrar
        this.setTitle("Crear nueva cuenta");
        JLabel newName = new JLabel("Enter username: ");
        newName.setBounds(80, 25, 170, 20);
        panelNuevoUsuario.add(newName);

        JLabel newPass = new JLabel("Enter your password: ");
        newPass.setBounds(80, 75, 150, 15);
        panelNuevoUsuario.add(newPass);

        JLabel confirmPass = new JLabel("Confirm your password: ");
        confirmPass.setBounds(80, 125, 150, 15);
        panelNuevoUsuario.add(confirmPass);

        //cuadros de texto para ingresar datos
        JTextField txtname = new JTextField();
        txtname.setBounds(250, 25, 150, 25);
        panelNuevoUsuario.add(txtname);

        JPasswordField txtPass = new JPasswordField();
        txtPass.setBounds(250, 75, 150, 25);
        panelNuevoUsuario.add(txtPass);

        JPasswordField txtConfirmPass = new JPasswordField();
        txtConfirmPass.setBounds(250, 125, 150, 25);
        panelNuevoUsuario.add(txtConfirmPass);

        JButton btnReturn = new JButton("Return");// boton de volver a Inicio
        btnReturn.setBounds(80, 185, 100, 30);
        panelNuevoUsuario.add(btnReturn);
        ActionListener Return = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                componentesInicioSesion();
                panelInicioSesion.setVisible(true);
                panelNuevoUsuario.setVisible(false);
            }
        };
        btnReturn.addActionListener(Return);

        JButton btnsave = new JButton("Save");// boton de volver a Inicio
        btnsave.setBounds(200, 185, 100, 30);
        panelNuevoUsuario.add(btnsave);
        ActionListener Save = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtname.getText();
                String contra = txtPass.getText();
                if (guardarUsuario(nombre, contra)) {
                    txtname.setText("");
                    txtPass.setText("");
                }
            }
        };
        btnsave.addActionListener(Save);
    }

    public boolean guardarUsuario(String nombre, String contra) {
        boolean guardado = false;
        if (nombre.equals("") || contra.equals("")) {
            JOptionPane.showMessageDialog(null, "You must fill out all fields");
        } else {
            if (comprobarDuplicadoUsuario(nombre)) {
                
                JOptionPane.showMessageDialog(null, "Existing user");
            } else {
                
                boolean vacio = false;
                int posicion = -1;
                for (int i = 0; i <= misUsuarios.length - 1; i++) {
                    if (misUsuarios[i] == null) {
                        vacio = true;
                        posicion = i;
                        break;
                    }
                }
                if (vacio) {
                    misUsuarios[posicion] = new usuario(nombre, contra);
                    JOptionPane.showMessageDialog(null, "Stored user 😊 !");
                    guardado = true;
                } else {
                    JOptionPane.showMessageDialog(null, "You can no longer save more users");
                }
            }
        }
        return guardado;
    }

    public boolean comprobarDuplicadoUsuario(String nombre) {
        boolean duplicado = false;
        for (int i = 0; i <= misUsuarios.length - 1; i++) {
            if (misUsuarios[i] != null) {
                if (misUsuarios[i].getNombre().equals(nombre)) {
                    duplicado = true;
                    break;
                }
                
            }
        }
        return duplicado;
    }
    
    //-------------- Componentes para mostrar los clientes
    
    
}
