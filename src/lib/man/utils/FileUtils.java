package lib.man.utils;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Read and write object
 *
 */
public class FileUtils {

	public static final String STORAGE_DIR = System.getProperty("user.dir") + "/src/lib/man/dataaccess/storage/";

	public static void writeObject(Object ob, String type) {
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;
		
		try {
			
			fout = new FileOutputStream(STORAGE_DIR + type);
			oos = new ObjectOutputStream(fout);
			oos.writeObject(ob);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fout != null) {
				try {
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (oos != null) {
				try {
					oos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static Object readObject(String fileName) throws Exception {

		FileInputStream fin = null;
		ObjectInputStream ois = null;
		Object result = null;
		
		try {
			
			fin = new FileInputStream(STORAGE_DIR + fileName);
			ois = new ObjectInputStream(fin);
			result = ois.readObject();

		} catch (EOFException e) {
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			if (fin != null) {
				try {
					fin.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if (ois != null) {
				try {
					ois.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
