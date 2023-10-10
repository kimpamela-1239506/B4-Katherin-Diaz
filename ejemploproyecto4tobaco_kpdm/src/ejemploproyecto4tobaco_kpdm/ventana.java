package ejemploproyecto4tobaco_kpdm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class ventana extends JFrame {

//creando variables universales
        JPanel panelInicioSesion, panelNuevoUsuario, panelClientes;
        JTextField txtUsuario;
        JPasswordField txtContra;
        usuario misUsuarios[] = new usuario[10];
        cliente misClientes[] = new cliente[20];
        
        public ventana() {
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setVisible(true);
            misUsuarios[0] = new usuario("admin", "123");//usuario 1
            misUsuarios[1] = new usuario("otro", "321");//usuario 2
// ------------------------ Clientes en existencia --------------------------
            misClientes[0] = new cliente("741", "Roberto", 24, 'M');
            misClientes[1] = new cliente("742", "Carlos", 39, 'M');
            misClientes[2] = new cliente("743", "Diego", 30, 'M');
            misClientes[3] = new cliente("744", "Ian", 22, 'M');
            misClientes[4] = new cliente("745", "Quevin", 22, 'M');
            misClientes[5] = new cliente("746", "Dayana", 27, 'F');
        }

        public void componentesInicioSesion() {
            panelInicioSesion = new JPanel(); //------ crear pane
            panelInicioSesion.setLayout(null);
            this.getContentPane().add(panelInicioSesion);
            this.setTitle("Inicio sesion");// Componentes de Iinicio usuario
            JLabel lblUsuario = new JLabel("Enter your username");// ingresar nombre
            lblUsuario.setBounds(95, 15, 150, 15);//dimensiones
            panelInicioSesion.add(lblUsuario);//añadir a la ventana
            JLabel lblcontra = new JLabel("Enter your password");//ingresar contaseña
            lblcontra.setBounds(95, 75, 150, 15);
            panelInicioSesion.add(lblcontra);
            txtUsuario = new JTextField();
            txtUsuario.setBounds(250, 15, 150, 25);
            panelInicioSesion.add(txtUsuario);
            txtContra = new JPasswordField();
            txtContra.setBounds(250, 75, 150, 25);
            panelInicioSesion.add(txtContra);
            JButton btnIniciar = new JButton("Log in");// ingresar
            btnIniciar.setBounds(100, 150, 100, 30);
            panelInicioSesion.add(btnIniciar);
            ActionListener iniciarSesion = (ActionEvent e) -> {
                String usuario = txtUsuario.getText();
                String contra = txtContra.getText();
                if (buscarUsuario(usuario, contra)) {
                    panelInicioSesion.setVisible(false);
                    componentesClientes();
                }
            };
            btnIniciar.addActionListener(iniciarSesion);

// -----------------> boton de Nuevo Usuario
            JButton btnNuevoUsuario = new JButton("Sign in");//registrar
            btnNuevoUsuario.setBounds(250, 150, 100, 30);
            panelInicioSesion.add(btnNuevoUsuario);
            ActionListener nuevoUsuario = (ActionEvent e) -> {
                componentesNuevoUsuario();
                panelInicioSesion.setVisible(false);
                panelNuevoUsuario.setVisible(true);
                componentesNuevoUsuario();
            };
            btnNuevoUsuario.addActionListener(nuevoUsuario);
            panelInicioSesion.repaint();
        }
        
        public boolean buscarUsuario(String usuario, String contra) {
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

            return encontrado;

        }

        public void componentesNuevoUsuario() { // se crea el componente para registrar

            panelNuevoUsuario = new JPanel();

            panelNuevoUsuario.setLayout(null);

            this.getContentPane().add(panelNuevoUsuario);

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

            ActionListener Return = (ActionEvent e) -> {

                componentesInicioSesion();

                panelInicioSesion.setVisible(true);

                panelNuevoUsuario.setVisible(false);

            };

            btnReturn.addActionListener(Return);

            JButton btnsave = new JButton("Save");// boton de volver a Inicio

            btnsave.setBounds(200, 185, 100, 30);

            panelNuevoUsuario.add(btnsave);

            ActionListener Save = (ActionEvent e) -> {

                String nombre = txtname.getText();

                String contra = txtPass.getText();

                if (guardarUsuario(nombre, contra)) {

                    txtname.setText("");

                    txtPass.setText("");

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

//--------------------------- Componentes para mostrar los clientes------------
        public void componentesClientes() {

            panelClientes = new JPanel();

            panelClientes.setLayout(null);

            this.getContentPane().add(panelClientes);

            this.setTitle("Dashboard by customers");//Customer control panel

            JLabel lblClientes = new JLabel("Stored clients");//clientes almacenados

            lblClientes.setBounds(10, 10, 150, 15);

            panelClientes.add(lblClientes);

            DefaultTableModel datos = new DefaultTableModel();//crear tabla con DefaultTableModel

            datos.addColumn("Nit");

            datos.addColumn("Nombre");

            datos.addColumn("Edad");

            datos.addColumn("Genéro");

// ------------------------------------------> cliente de prueba
            String prueba1[] = {"1", "Uno", "1", "F"};

            datos.addRow(prueba1);

            for (int i = 0; i < misClientes.length - 1; i++) {

                if (misClientes[i] != null) {

                    String temporal[] = {misClientes[i].getNit(), misClientes[i].getNombre(), String.valueOf(misClientes[i].getEdad()), String.valueOf(misClientes[i].getGenero())};

                    datos.addRow(temporal);

                }

            }

//creando la tabla de clientes
            JTable tablaClientes = new JTable(datos);

//Creando Scoll para tabla o barra de desplazamiento
            JScrollPane barraClientes = new JScrollPane(tablaClientes);

            barraClientes.setBounds(25, 40, 350, 250);

            panelClientes.add(barraClientes);

            DefaultPieDataset generoGrafico = new DefaultPieDataset();

            generoGrafico.setValue("Masculino", 7);

            generoGrafico.setValue("Femenino", 10);

            JFreeChart graficoCircular = ChartFactory.createPieChart("Generos", generoGrafico, true, true, false);

            ChartPanel panelCircular = new ChartPanel(graficoCircular);

            this.setSize(850, 400);

            panelCircular.setBounds(400, 20, 300, 250);

            panelClientes.add(panelCircular);

        }
    }
