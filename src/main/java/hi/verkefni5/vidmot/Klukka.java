package hi.verkefni5.vidmot;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Klukka {
    private SimpleIntegerProperty timi = new SimpleIntegerProperty();

    /**
     * Smiður fyrir klukku
     * @param startTimi leiktími
     */
    public Klukka(int startTimi){
        this.timi.set(startTimi);
    }

    /**
     * telur niður tímann
     */
    public void tic(){
        if (timi.get()>0){
            timi.set(timi.get()-1);
        }
    }

    /**
     * Skilar property fyrir núverandi tíma
     * @return
     */
    public IntegerProperty timiProperty() {
        return timi;
    }

    /**
     * setur tímann
     * @param timi tíminn
     */
    public void setTimi(int timi) {
        this.timi.set(timi);
    }

    /**
     * skilar tímanum
     * @return
     */
    public int getTimi() {
        return timi.intValue();
    }
}
