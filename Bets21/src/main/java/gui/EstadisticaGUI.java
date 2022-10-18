package gui;

import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Event;
import domain.Seleccion;
import domain.Usuario;

import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;

public class EstadisticaGUI extends JFrame {

	private BLFacade facade = MainGUI.getBusinessLogic();

	private final JLabel jLabelUserMasApostado = new JLabel(
			ResourceBundle.getBundle("Etiquetas").getString("UMasApostado"));
	private final JLabel jLabelUMayorApuesta = new JLabel(
			ResourceBundle.getBundle("Etiquetas").getString("UMayorApuesta"));
	private final JLabel jLabelEMasApostados = new JLabel(
			ResourceBundle.getBundle("Etiquetas").getString("EMasApostados"));
	private final JLabel jLabelSMasApostadas = new JLabel(
			ResourceBundle.getBundle("Etiquetas").getString("SMasApostadas"));
	private final JLabel jLabelGanancias = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Ganancias"));
	private final JLabel jLabelYear = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Year"));
	private final JLabel jLabelListaNegra = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ListaNegra"));

	private final JButton jButtonBack = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back"));

	private final JComboBox jComboBoxEMasApostados = new JComboBox();
	private DefaultComboBoxModel<String> eventosMasApostados = new DefaultComboBoxModel<String>();

	private final JComboBox jComboBoxSMasApostadas = new JComboBox();
	private DefaultComboBoxModel<String> selMasApostadas = new DefaultComboBoxModel<String>();

	private final JComboBox jComboBoxListaN = new JComboBox();
	private DefaultComboBoxModel<String> listaN = new DefaultComboBoxModel<String>();

	private JPanel contentPane;
	private final JLabel jLabelEstadistica = new JLabel(
			ResourceBundle.getBundle("Etiquetas").getString("Estadisticas")); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Launch the application.
	 */
	public EstadisticaGUI() {
		try {
			Estadistica();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	private void Estadistica() {
		Date dt = new Date();
		int year = dt.getYear() + 1900;

		domain.Estadistica esta = facade.crearEstadistica(year);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 515, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		jLabelUserMasApostado.setBounds(10, 108, 191, 13);
		contentPane.add(jLabelUserMasApostado);

		JLabel jLabelSolMasApostado = new JLabel("");
		jLabelSolMasApostado.setText(facade.usuarioMasApostado().getNombreUsuario() + ", "
				+ facade.usuarioMasApostado().getInfo().getNombre() + ", " + facade.usuarioMasApostado().getInfo().getdNI());
		jLabelSolMasApostado.setBounds(211, 108, 280, 13);
		contentPane.add(jLabelSolMasApostado);

		jLabelUMayorApuesta.setBounds(10, 131, 191, 13);
		contentPane.add(jLabelUMayorApuesta);

		JLabel jLabelSolMayorApuesta = new JLabel("");
		jLabelSolMayorApuesta.setText(facade.usuarioMayorApuesta().getNombreUsuario() + ", "
				+ facade.usuarioMayorApuesta().getInfo().getNombre() + ", " + facade.usuarioMayorApuesta().getInfo().getdNI());
		jLabelSolMayorApuesta.setBounds(211, 131, 280, 13);
		contentPane.add(jLabelSolMayorApuesta);

		jLabelEMasApostados.setBounds(10, 157, 163, 13);
		contentPane.add(jLabelEMasApostados);

		jComboBoxEMasApostados.setModel(eventosMasApostados);
		List<Event> eventos = facade.eventosMasApostados();
		for (int i = 0; i < eventos.size(); i++) {
			eventosMasApostados.addElement(eventos.get(i).getEventNumber() + " " + eventos.get(i).getEventDate() + " "
					+ eventos.get(i).getDescription());
		}
		jComboBoxEMasApostados.setBounds(165, 153, 307, 20);
		contentPane.add(jComboBoxEMasApostados);

		jLabelSMasApostadas.setBounds(10, 190, 160, 13);
		contentPane.add(jLabelSMasApostadas);

		jComboBoxSMasApostadas.setModel(selMasApostadas);
		List<Seleccion> sel = facade.seleccionesMasApostadas();
		for (int i = 0; i < sel.size(); i++) {
			selMasApostadas.addElement(
					sel.get(i).getDeporte() + ", " + sel.get(i).getGenero() + ", " + sel.get(i).getSeleccion());
		}
		jComboBoxSMasApostadas.setBounds(165, 186, 307, 21);
		contentPane.add(jComboBoxSMasApostadas);

		jLabelGanancias.setBounds(10, 85, 83, 13);
		contentPane.add(jLabelGanancias);

		JLabel jLabelSolGanancias = new JLabel("");
		jLabelSolGanancias.setText(String.valueOf(facade.conseguirGanancias(year)));
		jLabelSolGanancias.setBounds(106, 85, 67, 13);
		contentPane.add(jLabelSolGanancias);

		jLabelYear.setBounds(10, 62, 67, 13);
		contentPane.add(jLabelYear);

		JLabel jLabelSolYear = new JLabel("");
		jLabelSolYear.setText(String.valueOf(year));
		jLabelSolYear.setBounds(106, 62, 67, 13);
		contentPane.add(jLabelSolYear);

		jLabelListaNegra.setBounds(10, 226, 126, 13);
		contentPane.add(jLabelListaNegra);

		jComboBoxListaN.setModel(listaN);
		List<Usuario> ln = facade.getListaNegra(year);
		for (int i = 0; i < ln.size(); i++) {
			listaN.addElement(ln.get(i).getNombreUsuario() + ", " + ln.get(i).getInfo().getNombre() + ", " + ln.get(i).getInfo().getdNI());
		}
		jComboBoxListaN.setBounds(165, 222, 307, 21);
		contentPane.add(jComboBoxListaN);
		jLabelEstadistica.setFont(new Font("Times New Roman", Font.BOLD, 18));
		jLabelEstadistica.setBounds(10, 10, 233, 42);

		contentPane.add(jLabelEstadistica);

		jButtonBack.setFont(new Font("Tahoma", Font.PLAIN, 10));

		jButtonBack.addActionListener(this::jButtonBack_actionPerformed);
		jButtonBack.setBounds(10, 284, 89, 28);
		getContentPane().add(jButtonBack);
	}

	private void jButtonBack_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

}
