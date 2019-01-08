import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class FileData {

	public static final String DESKTOP = System.getProperty("user.home") + "/Desktop/";
	public static final String NEWLINE = System.getProperty("line.separator");
	private File file;
	private String key = "finlife";

	/**
	 * Constructor for class: FileData.java
	 */
	public FileData(File file) {
		setFile(file);
	}

	/*
	 * ****************** * METHODS * ******************
	 */

	/**
	 * Encrypts / Decrypts the given file and puts the text into the output file
	 * 
	 * @param path
	 */
	public void xor(String key, String input, String output) {
		int c, k;
		StringReader sr = new StringReader(key);
		try (FileInputStream in = new FileInputStream(input); FileOutputStream out = new FileOutputStream(output)) {
			sr.mark(key.length() + 1);

			while ((c = in.read()) != -1) {
				if ((k = sr.read()) == -1) {
					sr.reset();
					k = sr.read();
				}
				out.write(c ^ k);
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getLinesString() {
		String resultString = "";
		for (String s : getLines()) {
			resultString += s;
		}
		return resultString;
	}

	/**
	 * Reads the file and saves all the lines from the text file into an ArrayList
	 * 
	 * @return
	 */
	public ArrayList<String> getLines() {
		ArrayList<String> lines = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(getFile()));
			String line = reader.readLine();
			while (line != null) {
				lines.add(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("The file was not found!");
		} catch (IOException e) {
			throw new IllegalArgumentException("An error occured while reading the file!");
		}
		return lines;
	}

	/*
	 * ****************** * SETTER - GETTER * ******************
	 */

	/**
	 * Returns the File file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * Set the File file to the given value
	 */
	public void setFile(File file) {
		if (file == null)
			throw new IllegalArgumentException("File does not exist!");
		this.file = file;
	}

	/**
	 * Returns the String key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Set the String key to the given value
	 */
	public void setKey(String key) {
		if (key.length() <= 0 || key == null)
			throw new IllegalArgumentException("String key is not valid!");
		this.key = key;
	}
}
