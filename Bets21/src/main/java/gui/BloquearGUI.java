package gui;

import java.awt.Color;
import java.awt.Font;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Usuario;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BloquearGUI extends JFrame {

	private BLFacade facade = MainGUI.getBusinessLogic();
	private static final String ETIQUETAS = "Etiquetas";

	private JPanel contentPane;

	private JLabel JLabelMsg = new JLabel();

	private final JButton jButtonBloquear = new JButton(ResourceBundle.getBundle(ETIQUETAS).getString("Bloquear"));
	private final JButton jButtonEliminar = new JButton(ResourceBundle.getBundle(ETIQUETAS).getString("Eliminar")); //$NON-NLS-1$ //$NON-NLS-2$

	private final DefaultListModel<String> modeloNegro = new DefaultListModel<String>();
	private final JScrollPane scrollListaNegra = new JScrollPane();

	private JTable tablelistaN = new JTable();
	private DefaultTableModel tableModelListaN;
	private String[] columnNamesListaN = new String[] { ResourceBundle.getBundle(ETIQUETAS).getString("Usuario"),
			ResourceBundle.getBundle(ETIQUETAS).getString("Dni")

	};

	private final JComboBox jComboBoxListaN = new JComboBox();
	private DefaultComboBoxModel<String> listaN = new DefaultComboBoxModel<String>();

	private final JButton jButtonBack = new JButton(ResourceBundle.getBundle(ETIQUETAS).getString("Back"));

	public BloquearGUI() {
		try {
			Bloquear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	private void Bloquear() {

		Date dt = new Date();
		int year = dt.getYear() + 1900;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 318);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		jButtonBloquear.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabelMsg.setBounds(20, 41, 358, 13);
		JLabelMsg.setForeground(Color.red);
		getContentPane().add(JLabelMsg);

		jButtonBloquear.setBounds(86, 66, 105, 25);
		contentPane.add(jButtonBloquear);

		jComboBoxListaN.setModel(listaN);
		List<Usuario> ln = facade.getListaNegra(year);
		for (int i = 0; i < ln.size(); i++) {
			listaN.addElement(ln.get(i).getNombreUsuario() + ", " + ln.get(i).getInfo().getNombre() + ", " + ln.get(i).getInfo().getdNI());
		}
		jComboBoxListaN.setBounds(86, 113, 232, 21);
		contentPane.add(jComboBoxListaN);

		/**
		 * scrollListaNegra.setBounds(new Rectangle(40, 234, 406, 116));
		 * 
		 * tableModelListaN = new DefaultTableModel(null, columnNamesListaN);
		 * tableModelListaN.setColumnCount(3);
		 * 
		 * ArrayList<Usuario> ln= facade.getListaNegra();
		 * 
		 * for (int i=0; i<ln.size();i++){ Usuario u=ln.get(i); Vector<Object> row = new
		 * Vector<Object>();
		 * 
		 * row.add(u.getNombreUsuario()); row.add(u.getDNI()); row.add(u);
		 * tableModelListaN.addRow(row); }
		 * 
		 * 
		 * tablelistaN.getColumnModel().getColumn(0).setPreferredWidth(25);
		 * tablelistaN.getColumnModel().getColumn(1).setPreferredWidth(268);
		 * tablelistaN.getColumnModel().removeColumn(tablelistaN.getColumnModel().getColumn(2));
		 * 
		 * scrollListaNegra.setBounds(10, 22, 419, 113);
		 * 
		 * scrollListaNegra.setViewportView(tablelistaN);
		 * 
		 * 
		 * tablelistaN.setModel(tableModelListaN);
		 * this.getContentPane().add(scrollListaNegra, null);
		 * this.getContentPane().add(scrollListaNegra);
		 **/

		jButtonBloquear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabelMsg.setText("");

				String user = null;
				user = (String) jComboBoxListaN.getSelectedItem();
				String[] parts = user.split(",");
				String usernombre = parts[0];
				facade.bloquearUsuario(usernombre, year);
				JLabelMsg.setText(ResourceBundle.getBundle(ETIQUETAS).getString("Blocked"));

				listaN.removeAllElements();
				List<Usuario> ln = facade.getListaNegra(year);
				for (int i = 0; i < ln.size(); i++) {
					listaN.addElement(
							ln.get(i).getNombreUsuario() + ", " + ln.get(i).getInfo().getNombre() + ", " + ln.get(i).getInfo().getdNI());
				}
			}
		});

		jButtonBack.setFont(new Font("Tahoma", Font.PLAIN, 10));

		jButtonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonBack_actionPerformed(e);
			}
		});
		jButtonBack.setBounds(10, 10, 85, 21);
		getContentPane().add(jButtonBack);
		jButtonEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String user = null;
				user = (String) jComboBoxListaN.getSelectedItem();
				String[] parts = user.split(",");
				String usernombre = parts[0];
				facade.eliminarDeListaNegra(usernombre, year);
				JLabelMsg.setText(ResourceBundle.getBundle(ETIQUETAS).getString("Removed"));

				listaN.removeAllElements();
				List<Usuario> ln = facade.getListaNegra(year);
				for (int i = 0; i < ln.size(); i++) {
					listaN.addElement(
							ln.get(i).getNombreUsuario() + ", " + ln.get(i).getInfo().getNombre() + ", " + ln.get(i).getInfo().getdNI());
				}
			}
		});

		jButtonEliminar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jButtonEliminar.setBounds(213, 66, 105, 25);
		contentPane.add(jButtonEliminar);
	}

	private void jButtonBack_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
