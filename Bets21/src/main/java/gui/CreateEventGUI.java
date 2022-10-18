package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Event;
import domain.Seleccion;
import exceptions.EventAlreadyExists;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class CreateEventGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	DefaultComboBoxModel<Event> modelEvents = new DefaultComboBoxModel<Event>();
	private JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle(ETIQUETAS).getString("EventDate"));
	private static final String ETIQUETAS = "Etiquetas";
	
	private JCalendar jCalendar = new JCalendar();
	private Calendar calendarAct = null;
	private Calendar calendarAnt = null;

	private JScrollPane scrollPaneEvents = new JScrollPane();

	private JButton jButtonCreate = new JButton(ResourceBundle.getBundle(ETIQUETAS).getString("CreateQuery"));
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle(ETIQUETAS).getString("Close"));
	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();

	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();
	private final JButton jButtonBack = new JButton(ResourceBundle.getBundle(ETIQUETAS).getString("Back")); //$NON-NLS-1$ //$NON-NLS-2$

	private final JLabel jLabelGender = new JLabel(ResourceBundle.getBundle(ETIQUETAS).getString("Gender"));
	private final JRadioButton jRadioButtonMasculino = new JRadioButton(
			ResourceBundle.getBundle(ETIQUETAS).getString("Masculin"));
	private final JRadioButton jRadioButtonFemenino = new JRadioButton(
			ResourceBundle.getBundle(ETIQUETAS).getString("Femenin"));

	private final ButtonGroup buttonGroup = new ButtonGroup();

	private final JComboBox<String> comboBoxDeporte = new JComboBox<String>();
	private DefaultComboBoxModel<String> deporte = new DefaultComboBoxModel<String>();

	private final JComboBox<String> comboBoxSeleccion = new JComboBox<String>();
	private DefaultComboBoxModel<String> seleccion = new DefaultComboBoxModel<String>();

	public CreateEventGUI() {
		try {
			CreateEvent();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	private void CreateEvent() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Teams:");
		lblNewLabel.setBounds(10, 284, 45, 13);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(50, 281, 207, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(602, 447));
		this.setTitle(ResourceBundle.getBundle(ETIQUETAS).getString("CreateEvent"));

		jCalendar.setBounds(new Rectangle(40, 73, 225, 150));
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));

		jButtonCreate.setBounds(new Rectangle(40, 350, 130, 30));
		jButtonCreate.setEnabled(true);

		jButtonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCreate_actionPerformed(e);
			}
		});
		jButtonClose.setBounds(new Rectangle(215, 350, 130, 30));
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});

		jLabelMsg.setBounds(new Rectangle(10, 320, 247, 20));
		jLabelMsg.setForeground(Color.red);

		jLabelError.setBounds(new Rectangle(40, 245, 365, 20));
		jLabelError.setForeground(Color.red);

		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jLabelError, null);

		this.getContentPane().add(jButtonClose, null);
		this.getContentPane().add(jButtonCreate, null);

		this.getContentPane().add(jCalendar, null);

		BLFacade facade = MainGUI.getBusinessLogic();
		datesWithEventsCurrentMonth = facade.getEventsMonth(jCalendar.getDate());
		paintDaysWithEvents(jCalendar, datesWithEventsCurrentMonth);

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelEventDate.setBounds(40, 49, 140, 25);
		getContentPane().add(jLabelEventDate);
		jButtonBack.addActionListener(this::btnNewButton_actionPerformed);
		jButtonBack.setBounds(10, 10, 85, 21);

		contentPane.add(jButtonBack);

		jLabelGender.setBounds(333, 73, 219, 13);
		getContentPane().add(jLabelGender);

		buttonGroup.add(jRadioButtonMasculino);
		jRadioButtonMasculino.setBounds(333, 92, 110, 21);
		getContentPane().add(jRadioButtonMasculino);

		buttonGroup.add(jRadioButtonFemenino);
		jRadioButtonFemenino.setBounds(442, 92, 110, 21);
		getContentPane().add(jRadioButtonFemenino);

		comboBoxDeporte.setModel(deporte);
		deporte.addElement("Futbol");
		deporte.addElement("Baloncesto");
		deporte.addElement("Balonmano");
		deporte.addElement("Tenis");
		deporte.addElement("Hockey");
		deporte.addElement("E-Sports");
		comboBoxDeporte.setBounds(333, 182, 219, 21);
		contentPane.add(comboBoxDeporte);

		comboBoxSeleccion.setModel(seleccion);
		comboBoxSeleccion.setBounds(333, 244, 219, 21);
		contentPane.add(comboBoxSeleccion);

		// Code for JCalendar
		this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar.setLocale((Locale) propertychangeevent.getNewValue());

				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					System.out.println("calendarAnt: " + calendarAnt.getTime());
					System.out.println("calendarAct: " + calendarAct.getTime());
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar.getLocale());

					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);
					if (monthAct != monthAnt) {
						if (monthAct == monthAnt + 2) {
							// Si en JCalendar estÃ¡ 30 de enero y se avanza al mes siguiente, devolverÃ­a 2
							// de marzo (se toma como equivalente a 30 de febrero)
							// Con este cÃ³digo se dejarÃ¡ como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt + 1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}

						jCalendar.setCalendar(calendarAct);

						BLFacade facade = MainGUI.getBusinessLogic();

						datesWithEventsCurrentMonth = facade.getEventsMonth(jCalendar.getDate());
					}
					paintDaysWithEvents(jCalendar, datesWithEventsCurrentMonth);
					Date firstDay = UtilDate.trim(calendarAct.getTime());

				}
			}
		});

		comboBoxDeporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxDeporte_actionPerformed(e);
			}
		});

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

	private void btnNewButton_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

	public static void paintDaysWithEvents(JCalendar jCalendar, Vector<Date> datesWithEventsCurrentMonth) {
		// For each day with events in current month, the background color for that day
		// is changed.

		Calendar calendar = jCalendar.getCalendar();

		int month = calendar.get(Calendar.MONTH);
		int today = calendar.get(Calendar.DAY_OF_MONTH);
		int year = calendar.get(Calendar.YEAR);

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int offset = calendar.get(Calendar.DAY_OF_WEEK);

		if (Locale.getDefault().equals(new Locale("es")))
			offset += 4;
		else
			offset += 5;

		for (Date d : datesWithEventsCurrentMonth) {

			calendar.setTime(d);
			System.out.println(d);

			// Obtain the component of the day in the panel of the DayChooser of the
			// JCalendar.
			// The component is located after the decorator buttons of "Sun", "Mon",... or
			// "Lun", "Mar"...,
			// the empty days before day 1 of month, and all the days previous to each day.
			// That number of components is calculated with "offset" and is different in
			// English and Spanish
//			    		  Component o=(Component) jCalendar.getDayChooser().getDayPanel().getComponent(i+offset);; 
			Component o = (Component) jCalendar.getDayChooser().getDayPanel()
					.getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
			o.setBackground(Color.CYAN);
		}

		calendar.set(Calendar.DAY_OF_MONTH, today);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);

	}

	private void jButtonCreate_actionPerformed(ActionEvent e) {
		try {
			jLabelError.setText("");
			jLabelMsg.setText("");

			String teams = textField.getText();
			String dep = null;
			dep = (String) comboBoxDeporte.getSelectedItem();
			String sel = null;
			sel = (String) comboBoxSeleccion.getSelectedItem();

			Calendar calendar = jCalendar.getCalendar();
			int month = calendar.get(Calendar.MONTH);
			int today = calendar.get(Calendar.DAY_OF_MONTH);
			int year = calendar.get(Calendar.YEAR);

			Date fecha = newDate(year, month, today);

			if (teams.length() != 0 && dep != null && sel != null) {
				BLFacade facade = MainGUI.getBusinessLogic();
				if (jRadioButtonMasculino.isSelected()) {
					Seleccion seleccion = facade.anadirSeleccion(dep, "M", sel);
					facade.anadirEvent(teams, fecha, seleccion);
					jLabelMsg.setText(ResourceBundle.getBundle(ETIQUETAS).getString("Done"));
				} else if (jRadioButtonFemenino.isSelected()) {
					Seleccion seleccion = facade.anadirSeleccion(dep, "F", sel);
					facade.anadirEvent(teams, fecha, seleccion);
					jLabelMsg.setText(ResourceBundle.getBundle(ETIQUETAS).getString("Done"));
				} else {
					jLabelError.setText(ResourceBundle.getBundle(ETIQUETAS).getString("ErrorGeneral"));
				}
			} else {
				jLabelError.setText(ResourceBundle.getBundle(ETIQUETAS).getString("ErrorGeneral"));
			}

		} catch (EventAlreadyExists e1) {
			jLabelMsg.setText(ResourceBundle.getBundle(ETIQUETAS).getString("EventAlreadyExists"));
			e1.printStackTrace();
		}

	}

	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
		System.exit(0);
	}

	private static Date newDate(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}
}
