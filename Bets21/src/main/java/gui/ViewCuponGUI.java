package gui;

import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Usuario;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewCuponGUI extends JFrame {

	private JPanel contentPane;
	private BLFacade facade = MainGUI.getBusinessLogic();

	private domain.Usuario user;

	private final JLabel jLabelUsuario = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Usuario"));
	private final JLabel lLabelNumCupones = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("NumCupones"));
	private final JLabel jLabelSaldo = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SaldoDisp"));
	private final JLabel jLabelCupon = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("InfoCupon"));
	private final JButton jButtonBack = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back"));

	public ViewCuponGUI(domain.Usuario user) {
		try {
			ViewCupon(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	private void ViewCupon(Usuario user) {

		this.user = user;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		jLabelUsuario.setBounds(10, 91, 101, 13);
		contentPane.add(jLabelUsuario);

		lLabelNumCupones.setBounds(10, 125, 135, 13);
		contentPane.add(lLabelNumCupones);

		jLabelSaldo.setBounds(10, 162, 133, 13);
		contentPane.add(jLabelSaldo);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setText(user.getNombreUsuario());
		lblNewLabel.setBounds(121, 91, 163, 13);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setText(String.valueOf(facade.getNumCuponesConseguido(user)));
		lblNewLabel_1.setBounds(155, 125, 97, 13);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setText(String.valueOf(facade.getDineroAcumulado(user)));
		lblNewLabel_2.setBounds(153, 162, 131, 13);
		contentPane.add(lblNewLabel_2);

		jLabelCupon.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		jLabelCupon.setBounds(10, 10, 399, 57);
		contentPane.add(jLabelCupon);
		jButtonBack.setFont(new Font("Tahoma", Font.PLAIN, 10));

		jButtonBack.addActionListener(this::jButtonBack_actionPerformed);
		jButtonBack.setBounds(20, 225, 89, 28);
		getContentPane().add(jButtonBack);
	}

	private void jButtonBack_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

}
