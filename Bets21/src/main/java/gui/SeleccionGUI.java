package gui;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Seleccion;
import domain.Usuario;

import javax.swing.JButton;

public class SeleccionGUI extends JFrame {

	private Usuario user;

	private JPanel contentPane;

	private final JLabel jLabelGender = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Gender"));
	private final JRadioButton jRadioButtonMasculino = new JRadioButton(
			ResourceBundle.getBundle("Etiquetas").getString("Masculin"));
	private final JRadioButton jRadioButtonFemenino = new JRadioButton(
			ResourceBundle.getBundle("Etiquetas").getString("Femenin"));

	private final ButtonGroup buttonGroup = new ButtonGroup();

	private final JComboBox<String> comboBoxDeporte = new JComboBox<String>();
	private DefaultComboBoxModel<String> deporte = new DefaultComboBoxModel<String>();

	private final JComboBox<String> comboBoxSeleccion = new JComboBox<String>();
	private DefaultComboBoxModel<String> seleccion = new DefaultComboBoxModel<String>();

	private final JLabel jLabelDeporte = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Deporte")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel jLabelSeleccion = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Seleccion")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JButton jButtonBuscar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Buscar")); //$NON-NLS-1$ //$NON-NLS-2$

	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();

	public SeleccionGUI(Usuario user) {
		try {
			Seleccion(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	private void Seleccion(Usuario user) {
		this.user = user;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 290);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		jLabelMsg.setBounds(new Rectangle(10, 159, 497, 20));
		jLabelMsg.setForeground(Color.red);
		jLabelError.setBounds(new Rectangle(86, 237, 365, 20));
		jLabelError.setForeground(Color.red);

		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jLabelError, null);

		jLabelGender.setBounds(10, 37, 219, 13);
		getContentPane().add(jLabelGender);

		buttonGroup.add(jRadioButtonMasculino);
		jRadioButtonMasculino.setBounds(10, 56, 110, 21);
		getContentPane().add(jRadioButtonMasculino);

		buttonGroup.add(jRadioButtonFemenino);
		jRadioButtonFemenino.setBounds(122, 56, 110, 21);
		getContentPane().add(jRadioButtonFemenino);

		comboBoxDeporte.setModel(deporte);
		deporte.addElement("Futbol");
		deporte.addElement("Baloncesto");
		deporte.addElement("Balonmano");
		deporte.addElement("Tenis");
		deporte.addElement("Hockey");
		deporte.addElement("E-Sports");
		comboBoxDeporte.setBounds(10, 124, 219, 21);
		contentPane.add(comboBoxDeporte);

		comboBoxSeleccion.setModel(seleccion);
		comboBoxSeleccion.setBounds(288, 124, 219, 21);
		contentPane.add(comboBoxSeleccion);

		jLabelDeporte.setBounds(10, 101, 121, 13);
		contentPane.add(jLabelDeporte);

		jLabelSeleccion.setBounds(288, 101, 126, 13);
		contentPane.add(jLabelSeleccion);
		jButtonBuscar.addActionListener(this::jButtonBuscar_actionPerformed);

		jButtonBuscar.setBounds(170, 189, 197, 38);
		contentPane.add(jButtonBuscar);

		comboBoxDeporte.addActionListener(this::comboBoxDeporte_actionPerformed);

	}

	private void comboBoxDeporte_actionPerformed(ActionEvent e) {
		if (comboBoxDeporte.getSelectedItem().equals("Futbol")) {
			comboBoxSeleccion.removeAllItems();
			seleccion.addElement("Champions League");
			seleccion.addElement("Europa League");
			seleccion.addElement("Primera Divisi�n");
		} else if (comboBoxDeporte.getSelectedItem().equals("Baloncesto")) {
			comboBoxSeleccion.removeAllItems();
			seleccion.addElement("Euroliga");
			seleccion.addElement("Eurocup");
			seleccion.addElement("Copa Mundial");

		} else if (comboBoxDeporte.getSelectedItem().equals("Balonmano")) {
			comboBoxSeleccion.removeAllItems();
			seleccion.addElement("Liga ASOBAL");
			seleccion.addElement("Divisi�n de Honor Plata");
			seleccion.addElement("Primera Nacional");

		} else if (comboBoxDeporte.getSelectedItem().equals("Tenis")) {
			comboBoxSeleccion.removeAllItems();
			seleccion.addElement("Mutua Madrid Open");
			seleccion.addElement("Open Banc Sabadell");
			seleccion.addElement("Torneo El Espinar");

		} else if (comboBoxDeporte.getSelectedItem().equals("Hockey")) {
			comboBoxSeleccion.removeAllItems();
			seleccion.addElement("Copa Federaci�n FECAPA");
			seleccion.addElement("Campeonato FECAPA - Fase preliminar");
			seleccion.addElement("Euroliga");
		} else if (comboBoxDeporte.getSelectedItem().equals("E-Sports")) {
			comboBoxSeleccion.removeAllItems();
			seleccion.addElement("Overwatch League");
			seleccion.addElement("The International");
			seleccion.addElement("Campeonato mundial de LoL");
		}

	}

	private void jButtonBuscar_actionPerformed(ActionEvent e) {
		BLFacade facade = MainGUI.getBusinessLogic();
		jLabelError.setText("");
		jLabelMsg.setText("");

		String dep = null;
		dep = (String) comboBoxDeporte.getSelectedItem();
		String sel = null;
		sel = (String) comboBoxSeleccion.getSelectedItem();

		if (dep != null && sel != null) {
			if (jRadioButtonMasculino.isSelected()) {
				Seleccion seleccion = facade.anadirSeleccion(dep, "M", sel);
				this.setVisible(false);
				JFrame a = new ApostarGUI(user, seleccion);
				a.setVisible(true);

			} else if (jRadioButtonFemenino.isSelected()) {
				Seleccion seleccion = facade.anadirSeleccion(dep, "F", sel);
				this.setVisible(false);
				JFrame a = new ApostarGUI(user, seleccion);
				a.setVisible(true);

			} else {
				jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorGeneral"));
			}
		} else {
			jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorGeneral"));
		}
	}
}
