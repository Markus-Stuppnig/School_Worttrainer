import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @version 2022-10-25
 * @author Markus Stuppnig
 * @apiNote Diese Klasse ist für das Laden und Speichern an WortListe Objekten
 *          in Files zuständig.
 */
public class FileManager {

    // Der WortTrainer um den es geht
    private final WortTrainer wortTrainer;

    /*
     * Eine Standard Variable, welche für immer und alle gleich ist (static final)
     */
    private static final String standardFileName = "Worttrainer.txt";

    // Constructor erstellt das Objekt mit angegebenen WortTrainer
    public FileManager(final WortTrainer wortTrainer) {
    	this.wortTrainer = wortTrainer;
    }

    // Diese Methode speichert das WortEintrag Array
    // wortTrainer.getList().getWoerter() unter dem angegebenen Pfad.
    public void save(final String path) throws IOException {
		try (FileWriter outputStream = new FileWriter(new File(path))) {
		    for (WortEintrag wort : this.wortTrainer.getList().getWoerter())
			outputStream.write(wort.toString() + "\n");
	
		    outputStream.write("*--stats--*");
		    outputStream.write("\naked_words:" + this.wortTrainer.getAskedWords());
		    outputStream.write("\nright_words:" + this.wortTrainer.getRightWords());
		    outputStream.write("\ncurrent_word:" + this.wortTrainer.getCurrentWord().toString());
		}
    }

    // Diese Methode ruft die save(path : String) Methode mit dem Standard Pfad auf.
    public void save() throws IOException {
    	this.save(standardFileName);
    }

    // Diese Methode speichert das WortEintrag Array welches unter dem angegebenen
    // Pfad gespeichert ist in den WortTrainer.
    public void load(final String path) throws FileNotFoundException {
		try (Scanner inputStream = new Scanner(new File(path))) {
	
		    WortListe list = new WortListe();
		    String line;
		    boolean stats = false;
	
		    while (inputStream.hasNextLine()) {
	
			line = inputStream.nextLine();
	
			if (line.contains(";") && !stats) {
			    list.addWord(line.split(";")[0], line.split(";")[1]);
			    continue;
			}
	
			if ("*--stats--*".equals(line)) {
			    stats = true;
			    continue;
			}
	
			if (line.startsWith("aked_words:")) {
			    this.wortTrainer.setAskedWords(Integer.parseInt(line.split(":")[1]));
			    continue;
			}
			if (line.startsWith("right_words:")) {
			    this.wortTrainer.setRightWords(Integer.parseInt(line.split(":")[1]));
			    continue;
			}
			if (line.startsWith("current_word:")) {
			    this.wortTrainer.setCurrentWord(new WortEintrag(line.substring(line.indexOf(":") + 1).split(";")[0],
				    line.substring(line.indexOf(":")).split(";")[1]));
			    continue;
			}
	
			throw new IllegalArgumentException("File does not contain valid WortEintrag.");
		    }
	
		    this.wortTrainer.setList(list);
		    this.wortTrainer.getWrongWords();
		}
    }

    // Diese Methode ruft die load(path : String) Methode mit dem Standard Pfad auf.
    public void load() throws FileNotFoundException {
    	this.load(standardFileName);
    }
}