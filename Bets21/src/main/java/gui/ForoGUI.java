package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import businessLogic.BLFacade;
import domain.Mensaje;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;
import java.awt.Label;

public class ForoGUI extends JFrame {


	private domain.Usuario user;
	private final JButton botonEnviar = new JButton("Enviar");
	private final JTextArea textArea = new JTextArea();
	private JLabel jLabelAvisos = new JLabel(" ");

	private JTextPane textPane = new JTextPane();
	private JScrollPane scrollPane = new JScrollPane(textPane);
	private JScrollPane scrollPane2 = new JScrollPane(textArea);

	private SimpleAttributeSet attributeSet = new SimpleAttributeSet();

	private final JButton buton = new JButton("Buscar");
	private JButton corazon = new JButton("");

	public ForoGUI(domain.Usuario user) {
		try {
			Foro(user);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	private void Foro(domain.Usuario user) {
		JPanel contentPane;
		JTextField filtro;
		this.user = user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1253, 514);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		botonEnviar.setBounds(523, 441, 89, 23);

		botonEnviar.addActionListener(this::boton_enviar);
		contentPane.setLayout(null);
		contentPane.add(botonEnviar);
		textArea.setLineWrap(true);
		textArea.setBounds(78, 294, 654, 50);
		jLabelAvisos.setBounds(23, 350, 325, 29);
		contentPane.add(jLabelAvisos);

		BLFacade facade = MainGUI.getBusinessLogic();
		List<domain.Mensaje> mensajes = facade.getMensajesAll();

		if (mensajes.isEmpty()) {
			jLabelAvisos.setText("No hay mensajes de momento, foro vacio. Se el primero en comentar");
		} else {
			jLabelAvisos.setText("Disfruta de la experiencia de nuestro foro!");

		}
		scrollPane.setBounds(78, 11, 1126, 328);

		scrollPane2.setBounds(106, 390, 987, 40);

		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		scrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		textPane.setSize(639, 272);
		textPane.setLocation(78, 11);

		this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		this.getContentPane().add(scrollPane2);

		textPane.setEditable(false);

		Label label = new Label("Filtrar por Usuario:");
		label.setBounds(514, 350, 98, 22);
		contentPane.add(label);

		filtro = new JTextField();
		filtro.setBounds(624, 354, 86, 20);
		contentPane.add(filtro);
		filtro.setColumns(10);
		buton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!label.getText().isBlank()) {
					List<domain.Mensaje> menta = facade.filtrarPorUsuario(label.getText());
					if (menta.isEmpty()) {
						jLabelAvisos.setText("El usuario que busca no existe o no ha publicado ningun comentario");

					} else {
						for (domain.Mensaje me : menta) {
							enviarChat(me);
						}
					}
				}
			}
		});
		buton.setBounds(755, 353, 89, 23);

		contentPane.add(buton);
		textPane.setCharacterAttributes(attributeSet, true);
		for (domain.Mensaje m : mensajes) {
			enviarChat(m);
		}

		textPane.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent evt) {
				Component c = (Component) evt.getSource();
				c.toString();

				textPane.repaint();
			}

		});
		textPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});

		// IDEAS PARA AnADIR:
		// 1. BOTON PARA RESPONDER A LAS PREGUNTAS Y QUE SE VEAN LAS RESPUESTAS DADAS EN
		// ORDEN
		// 2. Filtrar mensajes
		// 3. PONER FOTO DE USUARIO???
		//

	}

	private void boton_enviar(ActionEvent e) {
		String texto = textArea.getText();
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();

		if (texto.isBlank()) {
			jLabelAvisos.setText("TIENES QUE ESCRIBIR ALGO; CUADRO VACIO");
		} else {
			BLFacade facade = MainGUI.getBusinessLogic();
			Mensaje m = facade.guardarMensaje(user, texto, date);
			enviarChat(m);
			textArea.setText("");
			textArea.repaint();

		}
	}

	private void nuevaLinea(JTextPane text) {

		try {
			text.getStyledDocument().insertString(text.getStyledDocument().getLength(),
					System.getProperty("line.separator"), null);
			text.getStyledDocument().insertString(text.getStyledDocument().getLength(),
					System.getProperty("line.separator"), null);

		} catch (BadLocationException ex) {
			Logger.getLogger(ForoGUI.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	// Metodo para texto en negrita
	private void textoNegritaMensaje(SimpleAttributeSet attrs, JTextPane text, String string) {
		StyleConstants.setBold(attrs, true);

		try {
			text.getStyledDocument().insertString(text.getStyledDocument().getLength(), string, attrs);
		} catch (BadLocationException ex) {
			Logger.getLogger(ForoGUI.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	private void textoCursivaCabecera(SimpleAttributeSet attrs, JTextPane text, String string) {
		StyleConstants.setItalic(attrs, true);
		StyleConstants.setBold(attrs, false);

		try {
			text.getStyledDocument().insertString(text.getStyledDocument().getLength(), string, attrs);
		} catch (BadLocationException ex) {
			Logger.getLogger(ForoGUI.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void enviarChat(Mensaje m) {

		// CABECERA
		String cab = "From: " + m.getdueno().getNombreUsuario() + "             To: ALL " + "     "
				+ m.fechaBonita(m.getDate());
		textoCursivaCabecera(attributeSet, textPane, cab);
		nuevaLinea(textPane);
		// CUERPO
		textoNegritaMensaje(attributeSet, textPane, m.getTexto());
		nuevaLinea(textPane);

		if (m.isDado()) {
			corazon.setBackground(Color.WHITE);
			corazon.setIcon(new ImageIcon("C:\\Users\\nerea\\Pictures\\corazon.png"));
			corazon.setBorder(null);
			textPane.insertComponent(corazon);

		} else {

			corazon.setBackground(Color.WHITE);
			corazon.setIcon(new ImageIcon("C:\\Users\\nerea\\Pictures\\cor.png"));
			corazon.setBorder(null);
			textPane.insertComponent(corazon);
		}
		corazon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = MainGUI.getBusinessLogic();
				facade.dadoMegusta(m);
				corazon.setIcon(new ImageIcon("C:\\Users\\nerea\\Pictures\\corazon.png"));

			}
		});
		String numero = m.getMegustas() + "";
		textoCursivaCabecera(attributeSet, textPane, numero);
		nuevaLinea(textPane);

	}
}