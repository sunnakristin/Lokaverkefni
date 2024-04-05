package hi.verkefni5.vidmot;

import javafx.collections.ObservableList;
import javafx.scene.shape.Rectangle;

public class Gull extends Rectangle {
    /**
     * smiður fyrir gull
     * les inn viðeigandi fxml skrá
     */
    public Gull() {
        FXML_lestur.lesa(this, "gull-view.fxml");
        this.setWidth(30);
        this.setHeight(30);
        setX(Math.random() * 500);
        setY(Math.random() * 250);
    }



}
