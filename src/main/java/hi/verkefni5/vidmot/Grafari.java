package hi.verkefni5.vidmot;

import javafx.scene.shape.Rectangle;

public class Grafari extends Rectangle {

    /**
     * smiður fyrir Grafari
     * les in viðeigandi fxml skrá
     */
    public Grafari(){
        FXML_lestur.lesa(this, "grafari-view.fxml");
        this.setWidth(50);
        this.setHeight(50);
        setX(5);
        setY(10);
    }
}
