package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Question;
import exceptions.PronosticoAlreadyExists;

import javax.swing.JTextField;

public class CreatePronosticoGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static final String ETIQUETAS = "Etiquetas";

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle(ETIQUETAS).getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle(ETIQUETAS).getString("Queries"));
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle(ETIQUETAS).getString("Events"));

	private final JButton jButtonClose = new JButton(ResourceBundle.getBundle(ETIQUETAS).getString("Close"));

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarAnt = null;
	private Calendar calendarAct = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();

	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();

	private JTable tableEvents = new JTable();
	private JTable tableQueries = new JTable();

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;

	private String[] columnNamesEvents = new String[] { ResourceBundle.getBundle(ETIQUETAS).getString("EventN"),
			ResourceBundle.getBundle(ETIQUETAS).getString("Event"),

	};
	private String[] columnNamesQueries = new String[] { ResourceBundle.getBundle(ETIQUETAS).getString("QueryN"),
			ResourceBundle.getBundle(ETIQUETAS).getString("Query")

	};
	private final JLabel lblNewLabel = new JLabel(
			ResourceBundle.getBundle(ETIQUETAS).getString("CreatePronosticoGUI.lblNewLabel.text")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JButton jButtonSave = new JButton(ResourceBundle.getBundle(ETIQUETAS).getString("Save")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JButton jButtonBack = new JButton(ResourceBundle.getBundle(ETIQUETAS).getString("Back")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel JLabelGanancia = new JLabel(
			ResourceBundle.getBundle(ETIQUETAS).getString("CreatePronosticoGUI.JLabelGanancia.text")); //$NON-NLS-1$ //$NON-NLS-2$

	Question preg = null;

	private JTextField textField;
	private JLabel JLabelError = new JLabel();
	private JLabel JLabelMsg = new JLabel();
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	/**
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { CreatePronosticoGui frame = new
	 * CreatePronosticoGui(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 **/
	public CreatePronosticoGUI() {
		try {
			CreatePronostico();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	private void CreatePronostico() {
		/**
		 * setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setBounds(100, 100, 450,
		 * 300); contentPane = new JPanel(); contentPane.setBorder(new EmptyBorder(5, 5,
		 * 5, 5)); contentPane.setLayout(new BorderLayout(0, 0));
		 * setContentPane(contentPane);
		 **/

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle(ETIQUETAS).getString("QueryQueries"));

		textField = new JTextField();
		textField.setBounds(126, 360, 225, 19);
		textField.setColumns(10);

		JLabelGanancia.setBounds(379, 363, 81, 13);
		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelQueries.setBounds(40, 210, 406, 14);
		jLabelEvents.setBounds(295, 19, 259, 16);

		this.getContentPane().add(JLabelGanancia);
		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);

		jButtonClose.setBounds(new Rectangle(454, 423, 130, 30));

		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
			}
		});

		this.getContentPane().add(jButtonClose, null);

		jCalendar1.setBounds(new Rectangle(40, 50, 225, 150));

		BLFacade facade = MainGUI.getBusinessLogic();
		datesWithEventsCurrentMonth = facade.getEventsMonth(jCalendar1.getDate());
		CreateQuestionGUI.paintDaysWithEvents(jCalendar1, datesWithEventsCurrentMonth);

		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {

				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
					Date firstDay = UtilDate.trim(new Date(jCalendar1.getCalendar().getTime().getTime()));

					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);

					if (monthAct != monthAnt) {
						if (monthAct == monthAnt + 2) {
							// Si en JCalendar está 30 de enero y se avanza al mes siguiente, devolvería 2
							// de marzo (se toma como equivalente a 30 de febrero)
							// Con este código se dejará como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt + 1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}

						jCalendar1.setCalendar(calendarAct);

						BLFacade facade = MainGUI.getBusinessLogic();

						datesWithEventsCurrentMonth = facade.getEventsMonth(jCalendar1.getDate());
					}

					CreateQuestionGUI.paintDaysWithEvents(jCalendar1, datesWithEventsCurrentMonth);

					try {
						tableModelEvents.setDataVector(null, columnNamesEvents);
						tableModelEvents.setColumnCount(3); // another column added to allocate ev objects

						BLFacade facade = MainGUI.getBusinessLogic();

						List<domain.Event> events = facade.getEvents(firstDay);

						if (events.isEmpty())
							jLabelEvents.setText(ResourceBundle.getBundle(ETIQUETAS).getString("NoEvents") + ": "
									+ dateformat1.format(calendarAct.getTime()));
						else
							jLabelEvents.setText(ResourceBundle.getBundle(ETIQUETAS).getString("Events") + ": "
									+ dateformat1.format(calendarAct.getTime()));
						for (domain.Event ev : events) {
							Vector<Object> row = new Vector<Object>();

							System.out.println("Events " + ev);

							row.add(ev.getEventNumber());
							row.add(ev.getDescription());
							row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
							tableModelEvents.addRow(row);
						}
						tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
						tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
						tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); // not
																												// shown
																												// in
																												// JTable
					} catch (Exception e1) {

						jLabelQueries.setText(e1.getMessage());
					}

				}
			}
		});

		this.getContentPane().add(jCalendar1, null);

		scrollPaneEvents.setBounds(new Rectangle(292, 50, 346, 150));
		scrollPaneQueries.setBounds(new Rectangle(40, 234, 406, 116));

		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tableEvents.getSelectedRow();
				domain.Event event = (domain.Event) tableModelEvents.getValueAt(i, 2); // obtain ev object
				List<Question> queries = event.getQuestions();

				tableModelQueries.setDataVector(null, columnNamesQueries);

				if (queries.isEmpty())
					jLabelQueries.setText(ResourceBundle.getBundle(ETIQUETAS).getString("NoQueries") + ": "
							+ event.getDescription());
				else
					jLabelQueries.setText(ResourceBundle.getBundle(ETIQUETAS).getString("SelectedEvent") + " "
							+ event.getDescription());

				for (domain.Question q : queries) {
					Vector<Object> row = new Vector<Object>();

					row.add(q.getQuestionNumber());
					row.add(q);
					tableModelQueries.addRow(row);
				}
				tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
				tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
			}
		});

		/**
		 * tableQueries.addMouseListener(new MouseAdapter() { public void
		 * mouseClicked(MouseEvent e) { //int j=tableQueries.getSelectedRow();
		 * //domain.Question question= (domain.Question)tableModelQueries.getValueAt(j,
		 * 1); } });
		 **/

		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);

		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);

		this.getContentPane().add(scrollPaneEvents, null);
		this.getContentPane().add(scrollPaneQueries, null);
		lblNewLabel.setBounds(28, 363, 100, 13);

		getContentPane().add(lblNewLabel);

		getContentPane().add(textField);
		jButtonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_actionPerformend(e);
			}
		});
		jButtonSave.setBounds(260, 423, 130, 30);

		getContentPane().add(jButtonSave);
		jButtonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_1_actionPerformed(e);
			}
		});
		jButtonBack.setBounds(69, 423, 130, 30);

		getContentPane().add(jButtonBack);
		JLabelError.setBounds(40, 400, 301, 13);
		JLabelError.setForeground(Color.red);

		getContentPane().add(JLabelError);
		JLabelMsg.setBounds(345, 400, 331, 13);
		JLabelMsg.setForeground(Color.red);

		getContentPane().add(JLabelMsg);

		textField_1 = new JTextField();
		textField_1.setBackground(new Color(255, 255, 255));
		textField_1.setText("");
		textField_1.setBounds(454, 360, 130, 19);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

	}

	private void btnNewButton_1_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
		System.exit(0);
	}

	private void btnNewButton_actionPerformend(ActionEvent e) {
		int j = tableQueries.getSelectedRow();
		domain.Question question = (domain.Question) tableModelQueries.getValueAt(j, 1);

		try {
			JLabelError.setText("");
			JLabelMsg.setText("");
			String solucion = textField.getText();
			float ganancia = Float.parseFloat(textField_1.getText());

			if (ganancia <= 0)
				JLabelError.setText(ResourceBundle.getBundle(ETIQUETAS).getString("ErrorNumber"));
			else {

				if (solucion.length() != 0 && textField_1.getText().length() != 0) {
					BLFacade facade = MainGUI.getBusinessLogic();
					facade.anadirPronostico(question, solucion, ganancia);
					JLabelMsg.setText(ResourceBundle.getBundle(ETIQUETAS).getString("Done"));
				} else {
					JLabelError.setText(ResourceBundle.getBundle(ETIQUETAS).getString("ErrorGeneral"));
				}
			}

		} catch (PronosticoAlreadyExists e1) {
			JLabelError.setText(ResourceBundle.getBundle(ETIQUETAS).getString("PronosticoAlreadyExists"));
		} catch (NumberFormatException e2) {
			JLabelError.setText(ResourceBundle.getBundle(ETIQUETAS).getString("ErrorGeneral"));
			e2.printStackTrace();
		}
	}
}
