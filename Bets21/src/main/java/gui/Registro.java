package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Rectangle;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.AnadirUsuarioParameter;
import domain.Usuario;
import exceptions.UserAlreadyExists;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Registro extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JPasswordField passwordField;

	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();
	private JLabel lblNewLabel_9;
	private JTextField textField_8;

	/**
	 * Launch the application.
	 */
	/**
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Registro frame = new Registro();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } } });
	 * }
	 **/
	public Registro() {
		try {
			Registro_unidad();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	private void Registro_unidad() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 532, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(24, 21, 82, 13);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Apellido1:");
		lblNewLabel_1.setBounds(24, 61, 82, 13);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Apellido2:");
		lblNewLabel_2.setBounds(277, 61, 82, 13);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("N\u00BA Telefono:");
		lblNewLabel_3.setBounds(277, 137, 82, 13);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Correo:");
		lblNewLabel_4.setBounds(24, 101, 82, 13);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("DNI:");
		lblNewLabel_5.setBounds(277, 101, 71, 13);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Fecha nacimiento:");
		lblNewLabel_6.setBounds(24, 137, 103, 13);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Nombre usuario:");
		lblNewLabel_7.setBounds(24, 243, 103, 13);
		contentPane.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Contrase\u00F1a:");
		lblNewLabel_8.setBounds(24, 266, 82, 13);
		contentPane.add(lblNewLabel_8);

		textField = new JTextField();
		textField.setBounds(132, 18, 120, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(132, 58, 120, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(358, 58, 136, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(132, 98, 120, 19);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(358, 98, 136, 19);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setBounds(132, 134, 120, 19);
		contentPane.add(textField_5);
		textField_5.setColumns(10);

		textField_6 = new JTextField();
		textField_6.setBounds(358, 134, 136, 19);
		contentPane.add(textField_6);
		textField_6.setColumns(10);

		textField_7 = new JTextField();
		textField_7.setBounds(132, 240, 186, 19);
		contentPane.add(textField_7);
		textField_7.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(132, 263, 186, 19);
		contentPane.add(passwordField);

		JButton btnNewButton = new JButton("Registrarse");
		btnNewButton.setBounds(176, 339, 154, 40);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonRegister_actionPerformed(e);
			}
		});

		jLabelMsg.setBounds(new Rectangle(28, 301, 403, 20));
		jLabelMsg.setForeground(Color.red);

		jLabelError.setBounds(new Rectangle(24, 320, 407, 20));
		jLabelError.setForeground(Color.red);

		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jLabelError, null);

		lblNewLabel_9 = new JLabel("N\u00FAmero tarjeta cr\u00E9dito:");
		lblNewLabel_9.setBounds(24, 171, 136, 13);
		contentPane.add(lblNewLabel_9);

		textField_8 = new JTextField();
		textField_8.setBounds(168, 168, 326, 19);
		contentPane.add(textField_8);
		textField_8.setColumns(10);
	}

	private void jButtonRegister_actionPerformed(ActionEvent e) {
		try {
			jLabelError.setText("");
			jLabelMsg.setText("");

			String nombre = textField.getText();
			String apellido1 = textField_1.getText();
			String apellido2 = textField_2.getText();
			int telefono = Integer.parseInt(textField_6.getText());
			String correo = textField_3.getText();
			String dni = textField_4.getText();
			String nombreUsuario = textField_7.getText();
			String password = new String(passwordField.getPassword());
			int numTarjeta = Integer.parseInt(textField_8.getText());

			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaNacimiento = formato.parse(textField_5.getText());

			if (nombre.length() != 0 && apellido1.length() != 0 && apellido2.length() != 0 && correo.length() != 0
					&& dni.length() != 0 && nombreUsuario.length() != 0 && password.length() != 0) {

				BLFacade facade = MainGUI.getBusinessLogic();
				AnadirUsuarioParameter info = facade.crearInfoUsuario(nombre, apellido1, apellido2, telefono, fechaNacimiento, dni);
				Usuario user = facade.anadirUsuario(  info, nombreUsuario,
						 password,correo,  numTarjeta);

				// HAY QUE CAMBIARLOOOO
				this.setVisible(false);
				JFrame a = new UsuarioGUI(user,info);
				a.setVisible(true);
			} else {
				jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorGeneral"));
			}

		} catch (UserAlreadyExists e1) {
			jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("UserAlreadyExists"));
			e1.printStackTrace();
		} catch (ParseException e2) {
			jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorGeneral"));
			e2.printStackTrace();
		} catch (NumberFormatException e3) {
			jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorGeneral"));
			e3.printStackTrace();
		}

	}
}
