import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Alejandro Bajo Pérez
 */
public class VentanaPrincipal {

	private BufferedImage canvas = null;
	private ManejadorFichero manejador;
	private Font fuenteHerramientas;
	// Ventana
	private JFrame ventana;
	private JTextArea textoUsuario;
	private Font fuente;
	// Barra de Herramientas
	private JMenuBar bar;
	private JMenu menu;
	private JMenuItem cargar, guardar, guardarComo, salir, copiar, pegar, cargarDiccionario;
	// Barra Editar
	private JMenu menuEditar;
	private JMenuItem reemplazar;
	// JToolBar
	private JToolBar toolBar;
	private JTextField textoBuscador;
	private JButton bGuardar, bCargar, bGuardarComo, bCopiar, bPegar, bBuscar;
	// Buscar
	private int fin = -1;
	private String palabraAnterior = "";
	// Diccionario
	private ManejadorDiccionario manejadorDiccionario;
	private ArrayList<String> diccionario;
	private JButton bCorregir;

	/**
	 * Constructor
	 */
	public VentanaPrincipal() {
		this.ventana = new JFrame();
		this.ventana.setBounds(500, 300, 750, 600);
		this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Inicializa los componentes
	 */
	public void inicializarComponentes() {
		this.manejador = new ManejadorFichero();
		this.fuenteHerramientas = new Font("TimesRoman", Font.PLAIN, 16);
		// Ventana
		this.fuente = new Font("TimesRoman", Font.PLAIN, 19);
		this.ventana.setLayout(new BorderLayout());
		this.textoBuscador = new JTextField();
		this.textoBuscador.setMaximumSize(new Dimension(80000, 40));
		// Lienzo
		this.textoUsuario = new JTextArea("Carga un fichero para comenzar...");
		this.textoUsuario.setEnabled(false);
		this.textoUsuario.setFont(fuente);
		this.textoUsuario.setLineWrap(true); // Para que salte de línea el texto
		this.textoUsuario.setWrapStyleWord(true); // Para que haga el salto de línea con palabras enteras
		// Barra de Herramientas
		this.bar = new JMenuBar();
		this.menu = new JMenu("Archivo");
		this.cargar = new JMenuItem("Cargar...");
		this.guardar = new JMenuItem("Guardar");
		this.guardarComo = new JMenuItem("Guardar Como...");
		this.guardar.setEnabled(false);
		this.guardarComo.setEnabled(false);
		this.cargarDiccionario = new JMenuItem("Cargar Diccionario");
		this.salir = new JMenuItem("Salir");
		// Barra Editar
		this.menuEditar = new JMenu("Editar");
		this.reemplazar = new JMenuItem("Reemplazar");
		this.reemplazar.setEnabled(false);
		this.copiar = new JMenuItem("Copiar");
		this.pegar = new JMenuItem("Pegar");
		// ToolBar
		this.toolBar = new JToolBar(); // El constructor por defecto crea una barra horizontal
		this.toolBar.setFloatable(false); // Para que no podamos mover la barra
		// Guardar
		this.bGuardar = new JButton("Guardar");
		try {
			this.canvas = ImageIO.read(new File("imagenes//filesave.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.bGuardar.setEnabled(false);
		this.bGuardar.setFont(fuenteHerramientas);
		this.bGuardar.setIcon(new ImageIcon(canvas.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		// Guardar Como
		this.bGuardarComo = new JButton("Guardar Como");
		try {
			this.canvas = ImageIO.read(new File("imagenes//SaveAs.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.bGuardarComo.setEnabled(false);
		this.bGuardarComo.setFont(fuenteHerramientas);
		this.bGuardarComo.setIcon(new ImageIcon(canvas.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		// Cargar
		this.bCargar = new JButton("Cargar");
		try {
			this.canvas = ImageIO.read(new File("imagenes//fileopen.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.bCargar.setFont(fuenteHerramientas);
		this.bCargar.setIcon(new ImageIcon(canvas.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		// Copiar
		this.bCopiar = new JButton("Copiar");
		try {
			this.canvas = ImageIO.read(new File("imagenes//copy.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.bCopiar.setIcon(new ImageIcon(canvas.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		// Pegar
		this.bPegar = new JButton("Pegar");
		try {
			this.canvas = ImageIO.read(new File("imagenes//paste.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.bPegar.setIcon(new ImageIcon(canvas.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		// Buscar
		this.bBuscar = new JButton();
		try {
			this.canvas = ImageIO.read(new File("imagenes//search.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.bBuscar.setIcon(new ImageIcon(canvas.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		// Diccionario
		this.manejadorDiccionario = new ManejadorDiccionario();
		this.bCorregir = new JButton("Corregir");
		try {
			this.canvas = ImageIO.read(new File("imagenes//check.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.bCorregir.setIcon(new ImageIcon(canvas.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		this.bCorregir.setEnabled(false);
	}

	/**
	 * Añade los componentes a la ventana
	 */
	public void addElements() {
		// Atajos de teclado
		this.cargar.setMnemonic(KeyEvent.VK_O);
		this.cargar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		this.guardar.setMnemonic(KeyEvent.VK_G);
		this.guardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
		this.guardarComo.setMnemonic(KeyEvent.VK_U);
		this.guardarComo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
		this.salir.setMnemonic(KeyEvent.VK_S);
		this.salir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		this.reemplazar.setMnemonic(KeyEvent.VK_R);
		this.reemplazar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		this.copiar.setMnemonic(KeyEvent.VK_C);
		this.copiar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		this.pegar.setMnemonic(KeyEvent.VK_V);
		this.pegar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		this.cargarDiccionario.setMnemonic(KeyEvent.VK_D);
		this.cargarDiccionario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		// Barra de Herramientas
		this.ventana.setJMenuBar(bar);
		this.bar.add(menu);
		this.menu.add(cargar);
		this.menu.add(cargarDiccionario);
		this.menu.addSeparator();
		this.menu.add(guardar);
		this.menu.add(guardarComo);
		this.menu.addSeparator();
		this.menu.add(salir);
		// Barra Editar
		this.bar.add(menuEditar);
		this.menuEditar.add(copiar);
		this.menuEditar.add(pegar);
		this.menuEditar.addSeparator();
		this.menuEditar.add(reemplazar);
		// JToolBar
		this.toolBar.add(bCargar);
		this.toolBar.add(bGuardar);
		this.toolBar.add(bGuardarComo);
		this.toolBar.addSeparator();
		this.toolBar.add(bCopiar);
		this.toolBar.add(bPegar);
		this.toolBar.add(bCorregir);
		this.toolBar.add(Box.createHorizontalGlue());
		this.toolBar.add(textoBuscador);
		this.toolBar.add(bBuscar);
		// Ventana
		this.ventana.add(textoUsuario);
		this.ventana.add(toolBar, BorderLayout.NORTH);
		this.ventana.add(new JScrollPane(textoUsuario)); // Scroll
	}

	/**
	 * Pone las funcionalidades a los elementos de la ventana
	 */
	public void inicializarListeners() {
		// Cargar
		this.cargar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser(manejador.getFicheroActual());
				chooser.setFileFilter(new FileNameExtensionFilter("Ficheros de texto", "txt"));
				chooser.setAcceptAllFileFilterUsed(false);
				if (chooser.showOpenDialog(ventana) == JFileChooser.APPROVE_OPTION) {
					manejador.setFicheroActual(chooser.getSelectedFile());
					try {
						textoUsuario.setText(manejador.cargarFichero());
						guardar.setEnabled(true);
						guardarComo.setEnabled(true);
						bGuardar.setEnabled(true);
						bGuardarComo.setEnabled(true);
						textoUsuario.setEnabled(true);
						reemplazar.setEnabled(true);
						textoUsuario.requestFocus();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(ventana, "No se puede cargar el fichero");
					}
				}
			}
		});
		// Cargar el diccionario
		this.cargarDiccionario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser(manejadorDiccionario.getRutaDiccionario());
				chooser.setFileFilter(new FileNameExtensionFilter("Ficheros de diccionario", "dic"));
				chooser.setAcceptAllFileFilterUsed(false);
				if (chooser.showOpenDialog(ventana) == JFileChooser.APPROVE_OPTION) {
					manejadorDiccionario.setRutaDiccionario(chooser.getSelectedFile());
					diccionario = manejadorDiccionario.cargarDiccionario();
					bCorregir.setEnabled(true);
				}
			}
		});
		// Botón Corregir
		this.bCorregir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String texto = textoUsuario.getText().toString();
				// Separo las palabras y las añado a un array
				String[] palabras = texto.split(" ");
				// Compruebo si las palabras están en el diccionario
				for (int i = 0; i < palabras.length; i++) {
					for (int j = 0; j < diccionario.size(); j++) {
						if (!palabras[i].equalsIgnoreCase(diccionario.get(j))) {
							System.out.println(palabras);
						}
					}
				}
			}
		});

		// Guardar
		this.guardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					manejador.guardarFichero(textoUsuario.getText().toString());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(ventana, "No se puede guardar el fichero");
				}
			}
		});
		// Guardar como
		this.guardarComo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser(manejador.getFicheroActual());
				chooser.setFileFilter(new FileNameExtensionFilter("Ficheros de texto", ".txt"));
				chooser.setAcceptAllFileFilterUsed(false);
				if (chooser.showSaveDialog(ventana) == JFileChooser.APPROVE_OPTION) {
					File ruta = chooser.getSelectedFile().toString().endsWith(".txt") ? chooser.getSelectedFile()
							: new File(chooser.getSelectedFile().toString() + ".txt");
					manejador.setFicheroActual(ruta);
					// Hago saltar el listener de guardar
					guardar.doClick();
				}
			}
		});
		// Salir
		this.salir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		// Botón cargar
		this.bCargar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargar.doClick();
			}
		});
		// Botón guardar
		this.bGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guardar.doClick();
			}
		});
		// Botón guardar como
		this.bGuardarComo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guardarComo.doClick();
			}
		});
		// Botón reemplazar
		this.reemplazar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				reemplazar.setEnabled(false);
				DialogoReemplazar dialog = new DialogoReemplazar(ventana, VentanaPrincipal.this);
				dialog.setVisible(true);
			}
		});
		// Botón copiar
		this.bCopiar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textoUsuario.copy();
			}
		});
		// Botón pegar
		this.bPegar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textoUsuario.paste();
			}
		});
		// Copiar
		this.copiar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bCopiar.doClick();
			}
		});
		// Pegar
		this.pegar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bPegar.doClick();
			}
		});
		// Botón buscar
		this.bBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int pos = -1;
				String palabra = textoBuscador.getText().toString();
				// Muevo el cursor al principio del fichero si no encuentra más palabras
				if (!palabra.equals("")) {
					if (!palabraAnterior.equals(palabra)) {
						fin = -1;
					}
				}
				// Saco la posición
				pos = textoUsuario.getText().toString().indexOf(palabra, fin);
				fin = pos + 1;
				// Seleciono la palabra
				if (pos >= 0) {
					textoUsuario.requestFocus();
					textoUsuario.setCaretPosition(pos);
					textoUsuario.moveCaretPosition(pos + palabra.length());
				} else {
					JOptionPane.showMessageDialog(ventana, "Has recorrido todo el fichero");
				}
				palabraAnterior = palabra;
			}
		});
	}

	public String getTextoUsuario() {
		return textoUsuario.getText().toString();
	}

	public void setTextoUsuario(String cadena) {
		this.textoUsuario.setText(cadena);
	}

	/**
	 * Método que sirve para activa el item reemplazar
	 */
	public void activarReemplazar() {
		this.reemplazar.setEnabled(true);
	}

	/**
	 * Llama a los otros métodos para construir la ventana
	 */
	public void inicializar() {
		this.ventana.setVisible(true);
		inicializarComponentes();
		addElements();
		inicializarListeners();
	}
}