import java.io.File;

import data.FileData;

public class XOREncryption {

	/*
	 * XORing is a very primitive encryption method Take the bytes of an input
	 * stream and XOR them bytewise with a known byte sequence (the key) The XOR
	 * operator is ^ A String could be used as a key Calling the program again with
	 * the same key results in the original file The program should work with binary
	 * files, so use a FileInputStream and FileOutputStream Input file name, output
	 * file name and key are passed as command line parameters
	 */

	public static FileData fd;

	public static void main(String[] args) {
		if (args.length < 3) {
			throw new IllegalArgumentException(FileData.NEWLINE + "Not enough information given! arg[0] = key; arg[1] = input; arg[2] = output;");
		} else if (args.length > 3) {
			throw new IllegalArgumentException(FileData.NEWLINE + "Too many information given! arg[0] = key; arg[1] = input; arg[2] = output;");
		}

		String key = args[0];
		String input = args[1];
		String output = args[2];

		fd = new FileData(new File(FileData.DESKTOP + input));
		fd.setKey(key);

		fd.xor(key, FileData.DESKTOP + input, FileData.DESKTOP + output);
	}

}
