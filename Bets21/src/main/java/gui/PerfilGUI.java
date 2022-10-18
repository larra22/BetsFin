package gui;

import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.AnadirUsuarioParameter;
import domain.Usuario;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PerfilGUI extends JFrame {

	private BLFacade facade = MainGUI.getBusinessLogic();
	private JPanel contentPane;
	private domain.Usuario user;

	private final JLabel jLabelNom = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Nombre"));
	private final JLabel jLabelNombre = new JLabel();
	private final JLabel jLabelApellidos = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Apellidos")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel jLabelApellido1 = new JLabel(); // $NON-NLS-1$ //$NON-NLS-2$
	private final JLabel jLabelApellido2 = new JLabel(); // $NON-NLS-1$ //$NON-NLS-2$
	private final JLabel jLabelTel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Telefono")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel jLabelTelefono = new JLabel(); // $NON-NLS-1$ //$NON-NLS-2$
	private final JLabel jLabelCorreoE = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Correo")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel jLabelCorreo = new JLabel(); // $NON-NLS-1$ //$NON-NLS-2$
	private final JLabel jLabelDescDNI = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Dni")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel jLabelDni = new JLabel(); // $NON-NLS-1$ //$NON-NLS-2$
	private final JLabel jLabelUsuario = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("NombreUsuario")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel jLabelUser = new JLabel(); // $NON-NLS-1$ //$NON-NLS-2$
	private final JLabel jLabelMiMonedero = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SaldoDisp")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel jLabelMonedero = new JLabel(); // $NON-NLS-1$ //$NON-NLS-2$
	private final JLabel jLabelPerfil = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Perfil")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JButton jButtonBack = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back"));

	public PerfilGUI(domain.Usuario user, domain.AnadirUsuarioParameter info) {
		try {
			Perfil(user,info);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	private void Perfil(Usuario user, AnadirUsuarioParameter info) {
		this.user = user;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		jLabelNom.setFont(new Font("Tahoma", Font.BOLD, 12));

		jLabelNom.setBounds(10, 78, 70, 13);
		contentPane.add(jLabelNom);

		jLabelNombre.setText(info.getNombre());
		jLabelNombre.setBounds(90, 78, 87, 13);
		contentPane.add(jLabelNombre);
		jLabelApellidos.setFont(new Font("Tahoma", Font.BOLD, 12));
		jLabelApellidos.setBounds(10, 101, 70, 13);

		contentPane.add(jLabelApellidos);
		jLabelApellido1.setBounds(92, 101, 90, 13);
		jLabelApellido1.setText(info.getApellido1());

		contentPane.add(jLabelApellido1);
		jLabelApellido2.setBounds(185, 101, 116, 13);
		jLabelApellido2.setText(info.getApellido2());

		contentPane.add(jLabelApellido2);
		jLabelTel.setFont(new Font("Tahoma", Font.BOLD, 12));
		jLabelTel.setBounds(10, 124, 70, 13);

		contentPane.add(jLabelTel);
		jLabelTelefono.setText(String.valueOf(info.getTelefono()));
		jLabelTelefono.setBounds(92, 124, 45, 13);

		contentPane.add(jLabelTelefono);
		jLabelCorreoE.setFont(new Font("Tahoma", Font.BOLD, 12));

		jLabelCorreoE.setBounds(10, 147, 70, 13);
		contentPane.add(jLabelCorreoE);

		jLabelCorreo.setText(user.getCorreo());
		jLabelCorreo.setBounds(92, 147, 116, 13);
		contentPane.add(jLabelCorreo);
		jLabelDescDNI.setFont(new Font("Tahoma", Font.BOLD, 12));
		jLabelDescDNI.setBounds(10, 170, 70, 13);

		contentPane.add(jLabelDescDNI);
		jLabelDni.setText(info.getdNI());
		jLabelDni.setBounds(92, 170, 105, 13);

		contentPane.add(jLabelDni);
		jLabelUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		jLabelUsuario.setBounds(10, 55, 90, 13);

		contentPane.add(jLabelUsuario);
		jLabelUser.setText(user.getNombreUsuario());
		jLabelUser.setBounds(110, 55, 87, 13);

		contentPane.add(jLabelUser);
		jLabelMiMonedero.setFont(new Font("Tahoma", Font.BOLD, 14));
		jLabelMiMonedero.setBounds(10, 216, 127, 13);

		contentPane.add(jLabelMiMonedero);
		jLabelMonedero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jLabelMonedero.setText(String.valueOf(facade.getMiMonedero(user)));
		jLabelMonedero.setBounds(154, 216, 87, 13);
		contentPane.add(jLabelMonedero);
		jLabelPerfil.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		jLabelPerfil.setBounds(10, 10, 167, 31);

		contentPane.add(jLabelPerfil);

		jButtonBack.setFont(new Font("Tahoma", Font.PLAIN, 10));

		jButtonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonBack_actionPerformed(e);
			}
		});
		jButtonBack.setBounds(347, 0, 89, 28);
		getContentPane().add(jButtonBack);
	}

	private void jButtonBack_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
