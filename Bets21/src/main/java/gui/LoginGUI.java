package gui;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.AnadirUsuarioParameter;
import domain.Usuario;
import exceptions.WrongUserOrPassword;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.*;
import java.awt.Font;

public class LoginGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombreUsuario;
	private JPasswordField passwordField;

	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();
	private final JLabel jLabelNombreUsuario = new JLabel(
			ResourceBundle.getBundle("Etiquetas").getString("NombreUsuario"));
	private final JLabel jLabelContrasena = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Contraseña"));
	private final JButton jButtonIniciarSesion = new JButton(
			ResourceBundle.getBundle("Etiquetas").getString("IniciarSesion"));

	private JButton jButtonBack = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back"));

	public LoginGUI() {
		try {
			Login_unidad();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	private void Login_unidad() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 409, 291);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtNombreUsuario = new JTextField();
		txtNombreUsuario.setBounds(156, 61, 135, 19);
		contentPane.add(txtNombreUsuario);
		txtNombreUsuario.setColumns(10);

		jLabelNombreUsuario.setBounds(35, 61, 111, 19);
		contentPane.add(jLabelNombreUsuario);

		jLabelContrasena.setBounds(35, 93, 96, 19);
		contentPane.add(jLabelContrasena);

		passwordField = new JPasswordField();
		passwordField.setBounds(156, 93, 135, 19);
		contentPane.add(passwordField);
		jButtonIniciarSesion.setFont(new Font("Times New Roman", Font.PLAIN, 13));

		jButtonIniciarSesion.setBounds(69, 151, 157, 26);
		contentPane.add(jButtonIniciarSesion);
		jButtonIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonLogin_actionPerformed(e);
			}
		});
		jButtonBack.setFont(new Font("Times New Roman", Font.PLAIN, 13));

		jButtonBack.setBounds(10, 10, 96, 26);
		getContentPane().add(jButtonBack);

		jButtonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonBack_actionPerformed(e);
			}
		});

		contentPane.setFocusable(true);

		// Eventos

		passwordField.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// JOptionPane.showMessageDialog(contentPane, "Has pulsado Enter")
					Login();
				}
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
				}
			}

			public void keyReleased(KeyEvent e) {
				// Aqui tambien puedes insertar el codigo
			}

		});

		jLabelMsg.setBounds(new Rectangle(0, 187, 391, 20));
		jLabelMsg.setForeground(Color.red);

		jLabelError.setBounds(new Rectangle(0, 217, 391, 20));
		jLabelError.setForeground(Color.red);

		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jLabelError, null);

	}

	private void Login() {
		try {
			String nombre = txtNombreUsuario.getText();
			String password = new String(passwordField.getPassword());

			BLFacade facade = MainGUI.getBusinessLogic();

			if (facade.esAdmin(nombre, password)) {
				this.setVisible(false);
				JFrame a = new AdminGUI();
				a.setVisible(true);
			} else if (facade.esSubdito(nombre, password)) {
				this.setVisible(false);
				JFrame a = new TrabajadoresGUI();
				a.setVisible(true);

			} else {
				Usuario user = facade.comprobar(nombre, password);
			
				this.setVisible(false);
				JFrame a = new UsuarioGUI(user,user.getInfo());
				a.setVisible(true);
			}

		} catch (WrongUserOrPassword e1) {
			jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("UserPassWordIsWrong"));
			txtNombreUsuario.setText("");
			passwordField.setText("");
			e1.printStackTrace();
		}

	}

	private void jButtonLogin_actionPerformed(ActionEvent e) {
		try {
			String nombre = txtNombreUsuario.getText();
			String password = new String(passwordField.getPassword());

			BLFacade facade = MainGUI.getBusinessLogic();

			if (facade.esAdmin(nombre, password)) {
				this.setVisible(false);
				JFrame a = new AdminGUI();
				a.setVisible(true);
			} else if (facade.esSubdito(nombre, password)) {
				this.setVisible(false);
				JFrame a = new TrabajadoresGUI();
				a.setVisible(true);

			} else {
				Usuario user = facade.comprobar(nombre, password);
				if (!user.isBloqueado()) {
					this.setVisible(false);
					JFrame a = new UsuarioGUI(user, user.getInfo());
					a.setVisible(true);
				} else {
					jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("UserPassWordIsWrong"));
				}
			}

		} catch (WrongUserOrPassword e1) {
			jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("UserPassWordIsWrong"));
			txtNombreUsuario.setText("");
			passwordField.setText("");
			e1.printStackTrace();
		}
	}

	private void jButtonBack_actionPerformed(ActionEvent e) {
		this.setVisible(false);
		JFrame a = new MainGUI();
		a.setVisible(true);
	}
}
