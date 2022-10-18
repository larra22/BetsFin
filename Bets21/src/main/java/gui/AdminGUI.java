package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Event;

import javax.swing.JButton;
import java.util.ResourceBundle;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class AdminGUI extends JFrame {

	private JPanel contentPane;
	
	private static final String ETIQUETAS = "Etiquetas";

	private final JButton jButtonClose = new JButton(ResourceBundle.getBundle(ETIQUETAS).getString("Close"));
	private final JButton jButtonLogout = new JButton(ResourceBundle.getBundle(ETIQUETAS).getString("LogOut"));
	private final JButton jButtonContratar = new JButton(ResourceBundle.getBundle(ETIQUETAS).getString("Contratar"));
	private final JButton jButtonCrearPregunta = new JButton(
			ResourceBundle.getBundle(ETIQUETAS).getString("CreateQuestion"));
	private final JButton jButtonCrearEvento = new JButton(
			ResourceBundle.getBundle(ETIQUETAS).getString("CreateEvent"));
	private final JButton jButtonCrearPronos = new JButton(
			ResourceBundle.getBundle(ETIQUETAS).getString("CreateForecast"));
	private final JButton jButtonCerrarApuesta = new JButton(
			ResourceBundle.getBundle(ETIQUETAS).getString("CloseBet"));
	private final JButton jButtonEstadisticas = new JButton(
			ResourceBundle.getBundle(ETIQUETAS).getString("Estadisticas")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JButton jButtonBlock = new JButton(ResourceBundle.getBundle(ETIQUETAS).getString("Bloquear")); //$NON-NLS-1$ //$NON-NLS-2$

	public AdminGUI() {
		try {
			Admin();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	private void Admin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 548, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		jButtonContratar.setBounds(28, 51, 220, 45);

		jButtonContratar.addActionListener(this::btnNewButton_actionPerformed);
		contentPane.setLayout(null);
		contentPane.add(jButtonContratar);
		jButtonClose.setBounds(316, 275, 139, 28);

		jButtonClose.addActionListener(this::btnNewButton_3_actionPerformed);
		contentPane.add(jButtonClose);
		jButtonCrearPregunta.setBounds(274, 51, 220, 45);

		jButtonCrearPregunta.addActionListener(this::btnNewButton_1_actionPerformed);
		contentPane.add(jButtonCrearPregunta);
		jButtonCrearEvento.setBounds(28, 106, 220, 45);

		jButtonCrearEvento.addActionListener(this::btnNewButton_2_actionPerformed);
		contentPane.add(jButtonCrearEvento);
		jButtonCrearPronos.setBounds(274, 106, 220, 45);

		jButtonCrearPronos.addActionListener(this::btnNewButton_4_actionPerformed);
		contentPane.add(jButtonCrearPronos);
		jButtonLogout.setBounds(69, 275, 139, 28);

		jButtonLogout.addActionListener(this::jButtonLogout_actionPerformed);

		contentPane.add(jButtonLogout);
		jButtonCerrarApuesta.addActionListener(this::jButtonCerrarApuesta_actionPerformed);

		jButtonCerrarApuesta.setBounds(28, 161, 220, 45);
		contentPane.add(jButtonCerrarApuesta);
		jButtonEstadisticas.addActionListener(this::jButtonEstadisticas_actionPerformed);

		jButtonEstadisticas.setBounds(274, 161, 220, 45);
		contentPane.add(jButtonEstadisticas);
		jButtonBlock.addActionListener(this::jButtonBlock_actionPerformed);
		jButtonBlock.setBounds(151, 216, 220, 39);

		contentPane.add(jButtonBlock);
	}

	private void jButtonCerrarApuesta_actionPerformed(ActionEvent e) {
		JFrame a = new CerrarApuestaGUI();
		a.setVisible(true);
	}

	private void btnNewButton_actionPerformed(ActionEvent e) {
		JFrame a = new AnadirTrabajadorGUI();
		a.setVisible(true);
	}

	private void btnNewButton_1_actionPerformed(ActionEvent e) {
		JFrame a = new CreateQuestionGUI(new Vector<Event>());
		a.setVisible(true);
	}

	private void btnNewButton_2_actionPerformed(ActionEvent e) {
		JFrame a = new CreateEventGUI();
		a.setVisible(true);
	}

	private void btnNewButton_3_actionPerformed(ActionEvent e) {
		this.setVisible(false);
		System.exit(0);
	}

	private void btnNewButton_4_actionPerformed(ActionEvent e) {
		JFrame a = new CreatePronosticoGUI();
		a.setVisible(true);
	}

	private void jButtonEstadisticas_actionPerformed(ActionEvent e) {
		JFrame a = new EstadisticaGUI();
		a.setVisible(true);
	}

	private void jButtonBlock_actionPerformed(ActionEvent e) {
		JFrame a = new BloquearGUI();
		a.setVisible(true);
	}

	private void jButtonLogout_actionPerformed(ActionEvent e) {
		this.setVisible(false);
		JFrame a = new MainGUI();
		a.setVisible(true);
	}
}
