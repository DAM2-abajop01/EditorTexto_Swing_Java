import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Alejandro Bajo Pérez
 */
public class ManejadorFichero {

	private File ficheroActual;

	/**
	 * Constructor
	 */
	public ManejadorFichero() {
		// El home del usuario
		this.ficheroActual = new File(System.getProperty("user.home"));
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
}