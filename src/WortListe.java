import java.io.Serializable;

/**
 * @version 2022-09-20
 * @author Markus Stuppnig
 * @apiNote Dies ist ein Objekt, welches eine Liste an WortEinträgen als
 *          Attribut hat, und Zugriffsmethoden zur Verfügung stellt.
 */
public class WortListe implements Serializable {

	// Das Array an WortEinträgen
	private WortEintrag[] woerter = {};

	/**
	 * @param wort Das Wort, das hinzugefügt werden soll
	 * @param url  Die URL des Wortes Diese Methode fügt ein neues Objekt des Typs
	 *             WortEintrag zum Attribut woerter hinzu.
	 */
	public void addWord(final String wort, final String url) {

		int length = this.woerter == null ? 1 : this.woerter.length + 1;

		WortEintrag[] neu = new WortEintrag[length];

		for (int i = 0; i < length - 1; i++)
			neu[i] = this.woerter[i];

		neu[neu.length - 1] = new WortEintrag(wort, url);

		this.woerter = neu;
	}

	/**
	 * @param wort Das Wort, das entfernt werden soll
	 * @return Wenn das Wort aus dem Array entfernt werden konnte, wird true
	 *         zurückgegeben. Diese Methode entfernt ein Objekt des Typs WortEintrag
	 *         vom Attribut woerter sofern dieses vorhanden ist.
	 */
	public boolean removeWord(final String wort) {
		for (WortEintrag old : this.woerter)
			if (old.getWort().equals(wort)) {
				WortEintrag[] neu = new WortEintrag[this.woerter.length - 1];

				int j = 0;
				for (WortEintrag element : this.woerter)
					if (!element.getWort().equals(wort)) {
						neu[j] = element;
						j++;
					}

				this.woerter = neu;

				return true;
			}
		return false;
	}

	@Override
	public String toString() {
		String toString = "";

		for (WortEintrag str : this.woerter)
			toString += str.toString() + "\n";

		return toString.substring(0, toString.length() - 2);
	}

	/**
	 * @param index Position des zu suchenden Wortes
	 * @return Es wird der WortEintrag, der auf dem angegeben Index des Arrays
	 *         woerter ist zurückgegeben.
	 */
	public WortEintrag getWordOnPosition(final int index) {
		if (index > this.woerter.length - 1)
			throw new IllegalArgumentException("So viele Wörter gibt es nicht!");
		if (index < 0)
			throw new IllegalArgumentException("Invalider Index!");
		return this.woerter[index];
	}

	/**
	 * @return Das Array Diese Methode returnt das Attribut woerter.
	 */
	public WortEintrag[] getWoerter() {
		return this.woerter;
	}

	/**
	 * @param woerter Das neue Array in WortEinträgen Dies ist eine direkte
	 *                möglichkeit, das Array des Objekts auf ein anderes Array zu
	 *                setzen.
	 */
	public void setWoerter(final WortEintrag[] woerter) {
		this.woerter = woerter;
	}
}