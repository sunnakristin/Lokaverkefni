package hi.verkefni5.vinnsla;

import hi.verkefni5.vidmot.Klukka;
import javafx.beans.property.SimpleIntegerProperty;

public class Leikur {
    private Klukka klukka;

    private SimpleIntegerProperty stig = new SimpleIntegerProperty(0);

    /**
     * smiður fyrir Leikur
     */
    public Leikur(){
        this.klukka = new Klukka(10);
    }

    /**
     * @return skilar klukkunni
     */
    public Klukka getKlukka() {
        return klukka;
    }

    public void setKlukka(Klukka klukka) {
        this.klukka = klukka;
    }

    /**
     * setur stigin
     * @return
     */
    public final int getStig() { return stig.get(); }

    /**
     * setur stign með inntaki
     * @param value
     */
    public final void setStig(int value) { stig.set(value); }
    public SimpleIntegerProperty stigProperty() { return stig; }

    /**
     * hækkar stigin
     */
    public void haekkaStigin() {
        setStig(getStig() + 1);
    }
}

