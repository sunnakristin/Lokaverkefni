package hi.verkefni5.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MenuController {

    @FXML
    private GoldController goldController; //tengja saman controlla
    @FXML
    private MenuItem fxNyrLeikur;
    @FXML
    private ToggleGroup fxToggleG;

    /**
     * Initialize ontroller klasann.
     */
    public void initialize() {
      //
    }

    /**
     * @param goldController setur GoldController
     */
    public void setGoldController(GoldController goldController) {
        this.goldController = goldController;
    }

    /**
     * Sér um val á erfiðleikastigi sem notandi velur
     * @param actionEvent
     */
    public void onErfidleikastig(ActionEvent actionEvent) {
        if (this.goldController == null) {
            System.out.println("goldController is null when onErfidleikastig is called");
        } else {
            RadioMenuItem valinnErfidleiki = ((RadioMenuItem) fxToggleG.getSelectedToggle());
            goldController.erfidleiki(valinnErfidleiki.getId());
        }
    }

    /**
     * byrjar nýjan leik
     * @param actionEvent
     */
    public void onNyrLeikur(ActionEvent actionEvent) {
        if (goldController != null) {
            goldController.raesaKlukku();
            goldController.hefjaLeik();
        }
    }

    /**
     * skoðar erfiðleikann sem notandi valdi
     * @return
     */
    public String valinnErfidleiki() {
        RadioMenuItem selectedDifficulty = (RadioMenuItem) fxToggleG.getSelectedToggle();
        if (selectedDifficulty != null) {
            return selectedDifficulty.getId();
        }
        return null;
    }

    /**
     * birtir Alert dialog og biður um staðfestingu frá notanda
     */
    public void onHaetta() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Hætta");
        alert.setHeaderText("Staðfesting");
        alert.setContentText("Ertu viss um að þú viljir hætta?");

        ButtonType yesButton = new ButtonType("Já");
        ButtonType noButton = new ButtonType("Nei");
        alert.getButtonTypes().setAll(yesButton, noButton);

        alert.showAndWait().ifPresent(response -> {
            if (response == yesButton) {
                System.exit(0);
            }
        });
    }

    /**
     * birtir dialog með upplýsingum um forritiö
     */
    public void onUmForritid() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Um Forritið");
        alert.setHeaderText("Upplýsingar um forritið");
        alert.setContentText("Höfundur: Sunna Kristín Gísladóttir\nÁrtal: 2024\nForritið er einfalt dæmi um leik " +
                "þar sem notandi stýrir hreyfingu gullgrafara og safnar stigum með því að grafa gull. Einnig er hægt er að setja" +
                " á mismunandi erfiðleikastig til þess að leikurinn verði meira krefjandi");

        alert.showAndWait();
    }
}