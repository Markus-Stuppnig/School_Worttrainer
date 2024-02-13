import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @version 2022-11-29
 * @author Markus Stuppnig
 * @apiNote Diese Klasse ist für das Laden und Speichern an WortListe Objekten
 *          in Files zuständig.
 */
public class ObjectStream {

	//Standard File Name
	private static final String standardFileName = "Worttrainer.txt";

	/**
	 * Speichert den angegebenen WortTrainer unter dem angegebenen File.
	 * @param wortTrainer Das Objekt das abgespeichert werden muss.
	 * @param fileName Der Pfad des Files, welches gespeichert wird.
	 */
	public static void save(WortTrainer wortTrainer, String fileName) {
		try (FileOutputStream fos = new FileOutputStream(fileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(wortTrainer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Speichert die Serializable unter dem standardFileName ab.
	 * @param wortTrainer Das Objekt das abgespeichert werden muss.
	 */
	public static void save(WortTrainer wortTrainer) {
		ObjectStream.save(wortTrainer, ObjectStream.standardFileName);
	}

	/**
	 * Lädt die Serializable aus dem angegeben File.
	 * @param fileName Der Pfad des Files, welches geladen wird.
	 * @return Der geladene WortTrainer.
	 */
	public static WortTrainer load(String fileName) {
		try (FileInputStream fis = new FileInputStream(fileName); ObjectInputStream ois = new ObjectInputStream(fis)) {
			WortTrainer wortTrainer = (WortTrainer) ois.readObject();
			return wortTrainer;
		} catch (IOException | ClassNotFoundException e) {
			if(e instanceof FileNotFoundException) {
				System.out.println("Die Datei existiert nicht " + fileName);
			}
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Lädt die Serializable aus dem standardFileName File.
	 * @return Der geladene WortTrainer.
	 */
	public static WortTrainer load() {
		return ObjectStream.load(ObjectStream.standardFileName);
	}
}