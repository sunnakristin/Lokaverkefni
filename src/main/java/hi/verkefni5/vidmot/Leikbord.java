package hi.verkefni5.vidmot;

import hi.verkefni5.vinnsla.Leikur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class Leikbord extends Pane {

    @FXML
    private Grafari fxGrafari;

    private Leikur leikur;

    private ObservableList<Gull> gullListi = FXCollections.observableArrayList();

    /**
     * Smiður fyrir Leikbord, les inn fxml skrá
     */
    public Leikbord() {
        FXML_lestur.lesa(this, "leikbord-view.fxml");
        this.leikur = new Leikur();
    }

    /**
     * @param leikur setur leikinn
     */
    public void setLeikur(Leikur leikur){
        this.leikur = leikur;
    }

    /**
     * Færir Grafarann í ákveðna átt en passar að hann fari ekki út fyrir mörk leikborðsins
     * stillir staðsetningu Grafarans út frá tilgreindri stefnu.
     * @param stefna í hvaða átt Grafarinn á að fara.
     */
    public void moveGrafari(Stefna stefna) {
        String stada = leikur.getKlukka().timiProperty().getValue().toString();
        if(!stada.equals("0")) {
            final double step = 10;
            switch (stefna) {
                case UP:
                    if (fxGrafari.getY() - step >= 0) {
                        fxGrafari.setY(fxGrafari.getY() - step);
                    }
                    break;
                case NIDUR:
                    if (fxGrafari.getY() + fxGrafari.getHeight() + step <= this.getHeight()) {
                        fxGrafari.setY(fxGrafari.getY() + step);
                    }
                    break;
                case HAEGRI:
                    if (fxGrafari.getX() + fxGrafari.getWidth() + step <= this.getWidth()) {
                        fxGrafari.setX(fxGrafari.getX() + step);
                    }
                    break;
                case VINSTRI:
                    if (fxGrafari.getX() - step >= 0) {
                        fxGrafari.setX(fxGrafari.getX() - step);
                    }
                    break;
            }
            erGrefurGull();
        }
    }

    /**
     * kallar á aðferðina framleidaGull()
     * til þess að fá Gull inná leikborðið
     */
    public void meiraGull(){
        String stada = leikur.getKlukka().timiProperty().getValue().toString();
        if(!stada.equals("0")) {
            framleidaGull();
        }
    }

    /**
     * framleiðir Gull
     */
  public void framleidaGull(){
        Gull g = new Gull();
        gullListi.add(g);
        this.getChildren().add(g);
    }

    /**
     * þegar Grafarinn skarast á Gull þá hverfur Gullið og Stig notanda hækka
     */
    public void erGrefurGull(){
        ObservableList<Gull> grafaGull = FXCollections.observableArrayList();
        for (Gull gull : gullListi) {
            if (fxGrafari.getBoundsInParent().intersects(gull.getBoundsInParent())) {
                grafaGull.add(gull);
            }
        }
        for (Gull gull : grafaGull) {
            gullListi.remove(gull);
            this.getChildren().remove(gull);
            leikur.haekkaStigin();
        }
    }

    /**
     * Hreynsar leikborðið
     */
    public void clear(){
        getChildren().removeIf(node -> node instanceof Gull);
        gullListi.clear();
    }
}


