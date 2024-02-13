import java.io.Serializable;

/**
 * @version 2022-09-09
 * @author Markus Stuppnig
 * @apiNote Dies ist ein Objekt, welches ein Wort und eine URl welche auf das
 *          Wort verweist als Attribute hat.
 */
public class WortEintrag implements Serializable {

    // Die beiden privaten Attribute
    private String wort;
    private String url;

    /**
     * @param wort Das Wort
     * @param url  Die URL Der Konstruktor, er nutzt die beiden Methoden setUrl()
     *             und setWort() um die Attribute zu setzen, dass diese auch der
     *             Korrektheit entsprechen.
     */
    public WortEintrag(final String wort, final String url) {
		this.setWort(wort);
		this.setUrl(url);
    }

    /**
     * @param url Der String der auf URL-Sinnhaftigkeit überprüft wird
     * @return Ob der übergebene Parameter eine valide URL ist
     */
    private static boolean checkURL(final String url) {

		if (!url.startsWith("http://") && !url.startsWith("https://") || url.split("://").length != 2
			|| url.contains(" "))
		    return false;
	
		String sub = url.split("://")[1];
	
		return sub.contains(".") && sub.lastIndexOf(".") != 0 && sub.lastIndexOf(".") != sub.length() - 1;
    }

    @Override
    public String toString() {
    	return this.wort + ";" + this.url;
    }

    /**
     * @return Das Attribut "wort"
     */
    public String getWort() {
    	return this.wort;
    }

    /**
     * @param wort Der String, auf den das Attribut des Objekts gesetzt werden soll
     *             Diese Methode überprüft den Parameter auf Sinnhaftigkeit, und
     *             setzt anschließend das Attribut auf diesen, oder spuckt eine
     *             Exception aus.
     */
    public void setWort(final String wort) {
		if ((wort == null) || (wort.length() < 2))
		    throw new IllegalArgumentException("Das Wort muss mindestens 2 Buchstaben haben!");
		this.wort = wort;
    }

    /**
     * @return Das Attribut "url"
     */
    public String getUrl() {
    	return this.url;
    }

    /**
     * @param url Der String, auf den das Attribut des Objekts gesetzt werden soll
     *            Diese Methode überprüft den Parameter mit Hilde der Methode
     *            checkURL() auf Sinnhaftigkeit, und setzt anschließend das Attribut
     *            auf diesen, oder spuckt eine Exception aus.
     */
    public void setUrl(final String url) {
		if ((url == null) || !WortEintrag.checkURL(url))
		    throw new IllegalArgumentException("Dies ist keine korrekte URL!");
		this.url = url;
    }
}