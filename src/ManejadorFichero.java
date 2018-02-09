import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Alejandro Bajo Pérez
 * @author Ismael Martín Ramírez
 */

public class ManejadorFichero {

	private File ficheroActual;
	File ficheroReciente;
	String rutaFicheroReciente = "src" + File.separator + "recientes" + File.separator + "recientes.txt";

	/**
	 * Constructor
	 */
	public ManejadorFichero() {
		// El home del usuario
		this.ficheroActual = new File(System.getProperty("user.home"));
		ficheroReciente = new File(rutaFicheroReciente);

	}

	/**
	 * Método que sirve para cargar el fichero actual.
	 * 
	 * @return Una cadena que contiene todo el texto del fichero.
	 * @throws IOException
	 */
	public String cargarFichero() throws IOException {
		String archivo = "";
		// Abro flujos
		BufferedReader br = new BufferedReader(new FileReader(ficheroActual));
		// Leo el fichero
		String linea;
		while ((linea = br.readLine()) != null) {
			archivo += linea + "\n";
		}
		// Cierro flujos
		br.close();
		return archivo;
	}

	/**
	 * Método que sirve para guardar el texto en el ficheroActual.
	 * 
	 * @param texto
	 *            Es una cadena que contiene el texto a guardar.
	 * @throws IOException
	 */
	public void guardarFichero(String texto) throws IOException {
		// Abro flujos
		BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroActual));
		// Escribo el fichero
		bw.write(texto);
		// Cierro flujos
		bw.close();
	}

	public File getFicheroActual() {
		return ficheroActual;
	}

	public void setFicheroActual(File ficheroActual) {
		this.ficheroActual = ficheroActual;
	}

	/**
	 * Guardar fichero de archivos abiertos recientemente
	 * 
	 * @param contenido
	 * @throws IOException
	 */
	public void guardarFicheroRecientes(String contenido) throws IOException {
		ArrayList<String> rutas = new ArrayList<>();
		rutas = obtenerLineasDelFichero();
		BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroReciente));
		for (int i = 0; i < rutas.size(); i++) {
			bw.write(rutas.get(i) + "\n");
		}
		bw.write(contenido + "\n");
		bw.close();
	}

	public ArrayList<String> obtenerLineasDelFichero() throws IOException {
		ArrayList<String> rutas = new ArrayList<>();
		if (ficheroReciente.exists()) {
			BufferedReader br = new BufferedReader(new FileReader(ficheroReciente));
			String linea = br.readLine();
			while ((linea) != null) {
				
				//Evitar repetipos
			if(!rutas.contains(linea)) {
					rutas.add(linea);
			}				
				linea = br.readLine();
			}
			br.close();
		}
		return rutas;
	}
}