import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * @author Alejandro Bajo Pérez
 */

public class DialogoReemplazar extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel tituloOrigen, tituloNuevo;
	private JTextField origen, nuevo;
	private JButton reemplazar;
	private VentanaPrincipal ventana;
	private Font fuente;

	public DialogoReemplazar(JFrame posicion, VentanaPrincipal ventana) {
		super();
		this.ventana = ventana;
		setBounds((int) posicion.getLocationOnScreen().getX(), (int) posicion.getLocationOnScreen().getY(), 250, 300);
		anadirElementos();
		inicializarListeners();
	}

	/**
	 * Inicializa los componentes y los añade a la ventana
	 */
	private void anadirElementos() {
		// Inicializar componentes
		this.setLayout(new GridLayout(5, 1, 1, 4));
		this.fuente = new Font("TimesRoman", Font.PLAIN, 18);
		this.tituloOrigen = new JLabel("Texto origen: ");
		this.tituloNuevo = new JLabel("Texto nuevo: ");
		this.reemplazar = new JButton("Reemplazar");
		this.nuevo = new JTextField();
		this.origen = new JTextField();
		// ---------------------------------------------------
		// Añadir elementos
		// ----------------------------------------------------
		// Titulo Origen
		this.tituloOrigen.setFont(fuente);
		this.tituloOrigen.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(tituloOrigen);
		// Origen
		this.origen.setFont(fuente);
		this.origen.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(origen);
		// Titulo Nuevo
		this.tituloNuevo.setFont(fuente);
		this.tituloNuevo.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(tituloNuevo);
		// Nuevo
		this.nuevo.setFont(fuente);
		this.nuevo.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(nuevo);
		// Botón
		this.reemplazar.setFont(fuente);
		this.reemplazar.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(reemplazar);
	}

	/**
	 * Pone las funcionalidades a los elementos de la ventana
	 */
	public void inicializarListeners() {
		this.reemplazar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!origen.getText().toString().equals("")) {
					String texto = ventana.getTextoUsuario();
					ventana.setTextoUsuario(
							texto.replaceAll(Pattern.quote(origen.getText().toString()), nuevo.getText().toString()));
				}
			}
		});
		// Al cerrar la ventana
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ventana.activarReemplazar();
			}
		});
	}
}