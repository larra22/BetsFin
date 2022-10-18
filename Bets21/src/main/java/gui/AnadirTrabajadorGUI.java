package gui;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import exceptions.UserAlreadyExists;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class AnadirTrabajadorGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	
	private static final String ETIQUETAS = "Etiquetas";

	private final JButton jButtonClose = new JButton(ResourceBundle.getBundle(ETIQUETAS).getString("Close"));
	private final JButton jButtonBack = new JButton(ResourceBundle.getBundle(ETIQUETAS).getString("Back"));

	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();

	/**
	 * Launch the application.
	 */
	/**
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { A�adirTrabajadorGUI frame = new
	 * A�adirTrabajadorGUI(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 **/

	public AnadirTrabajadorGUI() {
		try {
			AnadirTrabajador();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	private void AnadirTrabajador() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton jButtonContratar = new JButton("Contratar");
		jButtonContratar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonContratar_actionPerfomed(e);
			}
		});
		jButtonContratar.setBounds(10, 158, 421, 48);
		contentPane.add(jButtonContratar);

		JLabel lblNewLabel = new JLabel("Usuario trabajador:");
		lblNewLabel.setBounds(10, 69, 154, 22);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a trabajador:");
		lblNewLabel_1.setBounds(10, 101, 154, 22);
		contentPane.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(194, 71, 177, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(194, 103, 177, 19);
		contentPane.add(passwordField);

		jLabelMsg.setBounds(new Rectangle(66, 219, 305, 20));
		jLabelMsg.setForeground(Color.red);

		jLabelError.setBounds(new Rectangle(66, 233, 305, 20));
		jLabelError.setForeground(Color.red);

		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jLabelError, null);

		jButtonClose.setBounds(new Rectangle(296, 10, 130, 30));
		jButtonClose.addActionListener(this::jButtonClose_actionPerformed);

		this.getContentPane().add(jButtonClose, null);

		JButton jButtonBack = new JButton("Atr\u00E1s");
		jButtonBack.addActionListener(this::btnNewButton_1_actionPerformed);
		jButtonBack.setBounds(10, 10, 110, 30);
		contentPane.add(jButtonBack);
	}

	private void btnNewButton_1_actionPerformed(ActionEvent e) {
		this.setVisible(false);
		JFrame a = new AdminGUI();
		a.setVisible(true);
	}

	private void jButtonContratar_actionPerfomed(ActionEvent e) {
		try {
			jLabelError.setText("");
			jLabelMsg.setText("");

			String nomUser = textField.getText();
			String password = new String(passwordField.getPassword());

			if (nomUser.length() != 0 && password.length() != 0) {
				BLFacade facade = MainGUI.getBusinessLogic();
				facade.contratarSubdito(nomUser, password);
			} else {
				jLabelError.setText(ResourceBundle.getBundle(ETIQUETAS).getString("ErrorGeneral"));
			}

		} catch (UserAlreadyExists e1) {
			jLabelMsg.setText(ResourceBundle.getBundle(ETIQUETAS).getString("UserAlreadyExists"));
			e1.printStackTrace();
		}
	}

	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
		System.exit(0);
	}

}
