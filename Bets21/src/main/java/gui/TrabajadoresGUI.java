package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Event;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class TrabajadoresGUI extends JFrame {

	private JPanel contentPane;

	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private final JButton jButtonLogout = new JButton(ResourceBundle.getBundle("Etiquetas").getString("LogOut"));
	private final JButton jButtonCrearPregunta = new JButton(
			ResourceBundle.getBundle("Etiquetas").getString("CreateQuestion"));
	private final JButton jButtonCrearEvento = new JButton(
			ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
	private final JButton jButtonCrearPronos = new JButton(
			ResourceBundle.getBundle("Etiquetas").getString("CreateForecast"));
	private final JButton jButtonCerrarApuesta = new JButton(
			ResourceBundle.getBundle("Etiquetas").getString("CloseBet"));

	/**
	 * Launch the application.
	 */
	/**
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { TrabajadoresGUI frame = new
	 * TrabajadoresGUI(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 **/

	public TrabajadoresGUI() {
		try {
			Trabajadores();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	private void Trabajadores() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		jButtonCrearEvento.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				btnNewButton_actionPerformed(e);
			}
		});
		jButtonCrearEvento.setBounds(49, 10, 326, 45);
		contentPane.add(jButtonCrearEvento);

		jButtonCrearPregunta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_1_actionPerformed(e);
			}
		});
		jButtonCrearPregunta.setBounds(49, 65, 326, 45);
		contentPane.add(jButtonCrearPregunta);

		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_2_actionPerformed(e);
			}
		});
		jButtonClose.setBounds(236, 235, 139, 28);
		contentPane.add(jButtonClose);

		jButtonCrearPronos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_3_actionPerformed(e);
			}
		});
		jButtonCrearPronos.setBounds(49, 120, 326, 45);
		contentPane.add(jButtonCrearPronos);

		jButtonLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonLogout_actionPerformed(e);
			}
		});

		jButtonLogout.setBounds(49, 235, 139, 28);
		contentPane.add(jButtonLogout);

		jButtonCerrarApuesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCerrarApuesta_actionPerformed(e);
			}
		});

		jButtonCerrarApuesta.setBounds(49, 175, 326, 40);
		contentPane.add(jButtonCerrarApuesta);
	}

	private void btnNewButton_actionPerformed(ActionEvent e) {
		JFrame a = new CreateEventGUI();
		a.setVisible(true);
	}

	private void btnNewButton_1_actionPerformed(ActionEvent e) {
		JFrame a = new CreateQuestionGUI(new Vector<Event>());
		a.setVisible(true);
	}

	private void btnNewButton_2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
		System.exit(0);
	}

	private void btnNewButton_3_actionPerformed(ActionEvent e) {
		JFrame a = new CreatePronosticoGUI();
		a.setVisible(true);
	}

	private void jButtonCerrarApuesta_actionPerformed(ActionEvent e) {
		JFrame a = new CerrarApuestaGUI();
		a.setVisible(true);
	}

	private void jButtonLogout_actionPerformed(ActionEvent e) {
		this.setVisible(false);
		JFrame a = new MainGUI();
		a.setVisible(true);
	}
}
