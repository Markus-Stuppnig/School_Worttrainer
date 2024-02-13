import java.io.Serializable;

/**
 * @version 2022-11-29
 * @author Markus Stuppnig
 * @apiNote Dies ist ein Objekt, welches eine WortListe als Attribut hat, und
 *          diese nutzt umgewisse Funktionen anzubieten. Diese Klass enth�lt die
 *          main Methode.
 */
public class WortTrainer implements Serializable {

    // Das Objekt WortListe mit dem Array der WortEintr�ge
    private WortListe list;

    // Der FileManager
    //public final FileManager file;

    // Der momentan ausgew�hlte WortEintrag
    private WortEintrag current;

    // Diese Methoden sind zur Notierung des K�nnens des Users
    private int askedWords;
    private int rightWords;
    private int wrongWords;

    /**
     * @param liste Der Constructor wird eine WortListe �bergeben, er setzt sein
     *              Attribut list dann auf diese.
     */
    public WortTrainer(final WortListe list) {
		this.list = list;
	
		this.askedWords = 0;
		this.rightWords = 0;
		this.wrongWords = 0;
	
		this.getRandomWord();
	
		//this.file = new FileManager(this);
    }

    public void setList(final WortListe list) {
    	this.list = list;
    }

    public WortListe getList() {
    	return this.list;
    }

    /**
     * @return Diese Methode gibt ein zuf�lliges Objekt des Arrays mit dem Typen
     *         WortEintrag zur�ck. Und current wird auf dieses gesetzt.
     */
    public WortEintrag getRandomWord() {
		int index = (int) (Math.random() * ((this.list.getWoerter().length - 1) + 1));
		this.setCurrentWord(this.list.getWordOnPosition(index));
		return this.current;
    }

    /**
     * @return Das momentan ausgew�hlte Wort wird zur�ckgegeben.
     */
    public WortEintrag getCurrentWord() {
    	return this.current;
    }

    /**
     * Das momentan ausgew�hlte Wort wird gesetzt.
     */
    public void setCurrentWord(final WortEintrag current) {
    	this.current = current;
    }

    /**
     * @param check Der String der mit dem momentan ausgew�hlten Wort verglichen
     *              wird
     * @return Diese Methode gibt zur�ck, ob der String-parameter check mit dem Wort
     *         des momentan ausgew�hlten WortEintrags �bereinstimmt.
     */
    public boolean check(final String check) {
		this.askedWords++;
		if (check.equals(this.getCurrentWord().getWort())) {
		    this.rightWords++;
		    return true;
		}
		this.wrongWords++;
		return false;
    }

    /**
     * @param check Der String der mit dem momentan ausgew�hlten Wort verglichen
     *              wird
     * @return Diese Methode gibt zur�ck, ob der String-parameter check mit dem Wort
     *         des momentan ausgew�hlten WortEintrags �bereinstimmt. Hierbei spielt
     *         Gro�- und Kleinschreibung keine Rolle.
     */
    public boolean checkIgnoreCase(final String check) {
		this.askedWords++;
		if (check.equalsIgnoreCase(this.getCurrentWord().getWort())) {
		    this.rightWords++;
		    return true;
		}
		this.wrongWords++;
		return false;
    }

    /**
     * @return Die Anzahl an gefragten Woertern wird zur�ckgegeben.
     */
    public int getAskedWords() {
    	return this.askedWords;
    }

    /**
     * Asked words kann ge�ndert werden
     */
    public void setAskedWords(final int askedWords) {
    	this.askedWords = askedWords;
    }

    /**
     * @return Die Anzahl an richtigen Woertern wird zur�ckgegeben.
     */
    public int getRightWords() {
    	return this.rightWords;
    }

    /**
     * Right words kann ge�ndert werden
     */
    public void setRightWords(final int rightWords) {
    	this.rightWords = rightWords;
    }

    /**
     * @return Die Anzahl an falschen Woertern wird berechnet und zur�ckgegeben.
     */
    public int getWrongWords() {
    	this.wrongWords = this.getAskedWords() - this.getRightWords();
	return this.wrongWords;
    }

    /**
     * @param args Konsolenargumente Das ist die main Methode, sie �berpr�ft den
     *             Rest des Codes auf funktionalit�t.
     */
    public static void main(final String[] args) {

		WortListe liste1 = new WortListe();
		WortListe liste2 = new WortListe();
	
		liste1.addWord("Apfel",
			"https://www.google.com/imgres?imgurl=https%3A%2F%2Fimages.ichkoche.at%2Fdata%2Fimage%2Fvariations%2F496x384%2F1%2Fapfel-img-9270.jpg&imgrefurl=https%3A%2F%2Fwww.ichkoche.at%2Fapfel-artikel-4&tbnid=4BY--tMvhqWKZM&vet=12ahUKEwjw2P2wgZD6AhUwuqQKHX90CIgQMygFegUIARClAg..i&docid=LhZlVw7DyniO-M&w=496&h=384&q=apfel&ved=2ahUKEwjw2P2wgZD6AhUwuqQKHX90CIgQMygFegUIARClAg");
		liste1.addWord("Hase", "https://www.servus.com/a/t/fakten-hasen");
	
		liste2.addWord("Maus", "https://www.servus.com/a/t/fakten-hasen");
		liste2.addWord("Tisch", "https://www.kare.at/p/tisch-curve-180x90cm");
	
		WortTrainer trainer = new WortTrainer(liste1);
	
		System.out.println("Liste 1:\n" + trainer.getList().toString());
	
		trainer.setAskedWords(10);
		trainer.setRightWords(8);

		ObjectStream.save(trainer, "File.txt");
	
		trainer.setList(liste2);
	
		System.out.println("Liste 2:\n" + trainer.getList().toString());
	
		trainer = ObjectStream.load("File.txt");
	
		System.out.println("Liste 3:\n" + trainer.getList().toString());
		System.out.println(trainer.getAskedWords() + "\n" + trainer.getRightWords() + "\n" + trainer.getWrongWords()
			+ "\n" + trainer.getCurrentWord().toString());
	}
}