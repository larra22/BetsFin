package gui;

import java.beans.PropertyChangeEvent;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;

public class MetodosGUImplementatio  {
	private static final String ETIQUETAS ="Etiquetas";


	public void crearTablaEventos() {
		// TODO Auto-generated method stub
		
	}

	public void crearCalendarios(PropertyChangeEvent propertychangeevent, JCalendar jCalendar2, Calendar calendarAnt, Calendar calendarAct,
			Vector<Date> datesWithEventsCurrentMonth, JTable tableEvents, DefaultTableModel tableModelEvents, JLabel jLabelEvents, 
			JLabel jLabelQueries, String[] columnNamesEvents) {
		if (propertychangeevent.getPropertyName().equals("locale")) {
			jCalendar2.setLocale((Locale) propertychangeevent.getNewValue());
		} else if (propertychangeevent.getPropertyName().equals("calendar")) {
			calendarAnt = (Calendar) propertychangeevent.getOldValue();
			calendarAct = (Calendar) propertychangeevent.getNewValue();
			DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar2.getLocale());
			Date firstDay = UtilDate.trim(new Date(jCalendar2.getCalendar().getTime().getTime()));

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

				jCalendar2.setCalendar(calendarAct);

				BLFacade facade = MainGUI.getBusinessLogic();

				datesWithEventsCurrentMonth = facade.getEventsMonth(jCalendar2.getDate());
			}

			CreateQuestionGUI.paintDaysWithEvents(jCalendar2, datesWithEventsCurrentMonth);

			try {
				tableModelEvents.setDataVector(null, columnNamesEvents);
				tableModelEvents.setColumnCount(3); // another column added to allocate ev objects

				BLFacade facade = MainGUI.getBusinessLogic();

				Vector<domain.Event> events = facade.getEvents(firstDay);

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

}
