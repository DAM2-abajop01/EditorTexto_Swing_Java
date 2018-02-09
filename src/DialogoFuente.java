
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 * @author Alejandro Bajo Pérez
 * @author Ismael Martín Ramírez
 */

public class DialogoFuente extends JDialog {

	private static final long serialVersionUID = 1L;
	private VentanaPrincipal frame;
	private String fuente;
	private int fontEstilo;
	private JSlider sliderTamanio;
	private JPanel panelFuente, panelEstilo, panelTamanio;
	private JButton btnReemplazar;
	private JTextField editTamanio;
	private JTextPane jTextPane;
	ButtonGroup grupoFuente, grupoEstilo;
	JRadioButton[] radiosFuentes = { new JRadioButton("Verdana"), new JRadioButton("Arial"),
			new JRadioButton("Monospaced"), new JRadioButton("Times New Roman"), new JRadioButton("Comic Sans MS") };
	JRadioButton[] radiosEstilos = { new JRadioButton("Normal"), new JRadioButton("Cursiva"),
			new JRadioButton("Negrita") };

	public DialogoFuente(VentanaPrincipal frame) {
		super();
		this.frame = frame;

		setBounds(100, 100, 500, 600);
		this.setTitle("Seleccionador de Fuente");

		anadirComponentes();
		anadirListened();
	}

	public void anadirComponentes() {
		setLayout(new GridLayout(1, 2));
		JPanel panelIzquierda = new JPanel();
		panelIzquierda.setLayout(new GridLayout(4, 1));

		// Fuentes
		panelFuente = new JPanel();
		panelFuente.setLayout(new GridLayout(radiosFuentes.length, 1));
		panelFuente.setBorder(BorderFactory.createTitledBorder("Fuente"));
		grupoFuente = new ButtonGroup();
		for (int i = 0; i < radiosFuentes.length; i++) {
			grupoFuente.add(radiosFuentes[i]);
			panelFuente.add(radiosFuentes[i]);
		}
		panelIzquierda.add(panelFuente);

		panelEstilo = new JPanel();
		panelEstilo.setLayout(new GridLayout(radiosEstilos.length, 1));
		panelEstilo.setBorder(BorderFactory.createTitledBorder("Fuente"));
		grupoEstilo = new ButtonGroup();
		for (int i = 0; i < radiosEstilos.length; i++) {
			grupoEstilo.add(radiosEstilos[i]);
			panelEstilo.add(radiosEstilos[i]);
		}
		panelIzquierda.add(panelEstilo);

		panelTamanio = new JPanel();
		panelTamanio.setLayout(new GridLayout(2, 1));
		panelTamanio.setBorder(BorderFactory.createTitledBorder("Tamaño"));
		sliderTamanio = new JSlider(JSlider.HORIZONTAL, 0, 50, 12);
		sliderTamanio.setMajorTickSpacing(10);
		sliderTamanio.setMinorTickSpacing(2);
		sliderTamanio.setPaintTicks(true);
		sliderTamanio.setPaintLabels(true);
		panelTamanio.add(sliderTamanio);
		editTamanio = new JTextField("12");
		editTamanio.setEditable(true);
		editTamanio.setHorizontalAlignment(JTextField.CENTER);
		panelTamanio.add(editTamanio);
		panelIzquierda.add(panelTamanio);

		btnReemplazar = new JButton("Hacer");
		panelIzquierda.add(btnReemplazar);
		add(panelIzquierda);

		jTextPane = new JTextPane();

		add(new JScrollPane(jTextPane));

	}

	public void anadirListened() {

		btnReemplazar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int inicio = frame.getTextArea().getSelectionStart();
				int fin = frame.getTextArea().getSelectionEnd();

				SimpleAttributeSet data = new SimpleAttributeSet();

				for (int i = 0; i < radiosFuentes.length; i++) {
					if (radiosFuentes[i].isSelected()) {
						fuente = radiosFuentes[i].getText();
					}
				}
				if (radiosEstilos[0].isSelected()) {
					fontEstilo = Font.PLAIN;
				}
				if (radiosEstilos[1].isSelected()) {
					fontEstilo = Font.ITALIC;
					StyleConstants.setItalic(data, true);
				}
				if (radiosEstilos[2].isSelected()) {
					fontEstilo = Font.BOLD;
					StyleConstants.setBold(data, true);
				}

				StyleConstants.setUnderline(data, true);
				StyleConstants.setForeground(data, Color.RED);
				jTextPane.setText(frame.getTextoUsuario());
				jTextPane.getStyledDocument().setCharacterAttributes(inicio + 1, fin - 1, data, false);

				System.out.println("DialogoFuente ->\nPosicion Selecionada: " + inicio + " / " + fin);
				frame.getTextArea().setFont(new Font(fuente, fontEstilo, Integer.parseInt(editTamanio.getText())));
				jTextPane.setFont(new Font(fuente, fontEstilo, Integer.parseInt(editTamanio.getText())));
			}
		});

		// Detector del slided
		sliderTamanio.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				editTamanio.setText(String.valueOf(sliderTamanio.getValue()));
			}
		}); // Al cerrar la ventana
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.activarFuente();
			}
		});
	}
}