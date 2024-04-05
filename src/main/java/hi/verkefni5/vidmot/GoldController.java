package hi.verkefni5.vidmot;

import hi.verkefni5.vinnsla.Leikur;
import javafx.animation.KeyFrame;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.animation.Animation;
import java.util.HashMap;
import static java.lang.Integer.parseInt;

public class GoldController {

   @FXML
   private MenuController menuController; //tengja saman controlla

   @FXML
   private Leikbord fxLeikbord;

   @FXML
   private Label fxTimi;

   @FXML
   private Label fxStig;

   @FXML
   private Label fxTimiTexti;

   private Leikur leikur;

   private Timeline gullTimi;

   private static final HashMap<KeyCode, Stefna> map = new HashMap<KeyCode, Stefna>();

   /**
    * Initialize leikinn okkar, frumstillum erfiðleika, klukku og stig notanda
    * bindum saman property fyrir tímann og stig
    */
   public void initialize() {
      menuController.setGoldController(this);
      leikur = new Leikur();
      fxLeikbord.setLeikur(leikur);
      leikur.getKlukka().setTimi(45);

      fxTimi.textProperty().bind(leikur.getKlukka().timiProperty().asString()); //bindum saman

      leikur.getKlukka().timiProperty().addListener((observable, oldValue, newValue) -> {
         statusLeikur();
      });

      Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
         leikur.getKlukka().tic();
      }));

      timeline.setCycleCount(Animation.INDEFINITE);
      timeline.play();
      hefjaLeik();
      fxStig.textProperty().bind(leikur.stigProperty().asString());
   }

   /**
    * byrjum leikinn, passar að allt sé frumstillt
    */
   public void hefjaLeik() {
      fxLeikbord.clear();
      if (gullTimi != null && gullTimi.getStatus() == Animation.Status.RUNNING) {
         gullTimi.stop();
      }
      KeyFrame k = new KeyFrame(Duration.seconds(1), e -> fxLeikbord.meiraGull());
      gullTimi = new Timeline(k);
      gullTimi.setCycleCount(Animation.INDEFINITE);
      gullTimi.play();
      leikur.setStig(0);
      statusLeikur();
   }

   /**
    * Setur tímann eftir hvaða erfiðleiki er valinn
    * @param erfidleiki erfiðleikinn sem notandi valdi
    */
   public void erfidleiki(String erfidleiki) {
      switch (parseInt(erfidleiki)) {
         case 1:
            leikur.getKlukka().setTimi(45);
            hefjaLeik();
            break;
         case 2:
            leikur.getKlukka().setTimi(30);
            hefjaLeik();
            break;
         case 3:
            leikur.getKlukka().setTimi(15);
            hefjaLeik();
            break;
      }
   }

   /**
    * Uppfærir stöðu leiksins miðað við núverandi leiktíma sem lable.
    * Lætur notanda vita ef Leik er lokið þegar tíminn er 0
    */
   private void statusLeikur() {
      if (leikur.getKlukka().getTimi() == 0) {
         fxTimi.textProperty().unbind();
         fxTimi.setText("Leik Lokið");
         fxTimiTexti.setText("");
      } else {
         if (!fxTimi.textProperty().isBound()) {
            fxTimi.textProperty().bind(leikur.getKlukka().timiProperty().asString());
            fxTimiTexti.setText("Tími: ");
         }
      }
   }

   /**
    *Stillir leikinn til að bregðast við örvatakkatilvikum fyrir stýringu Grafarans .
    */
   public void orvatakkar() {
      map.put(KeyCode.UP, Stefna.UP);
      map.put(KeyCode.DOWN, Stefna.NIDUR);
      map.put(KeyCode.RIGHT, Stefna.HAEGRI);
      map.put(KeyCode.LEFT, Stefna.VINSTRI);
      fxLeikbord.requestFocus();
      fxLeikbord.getScene().addEventFilter(KeyEvent.ANY,   //KeyEvents eru sendar á Scene
              this::stefna);
   }

   /**
    * Flettir upp horninu KeyCode í map
    * @param event
    */
   public void stefna(KeyEvent event) {
      Stefna stefna = map.get(event.getCode());
      if (stefna != null) {
         fxLeikbord.moveGrafari(stefna);
      }
   }

   /**
    * ræsir Klukkuna fyrir nýjan leik og kallar á fallið erfidleiki()
    */
   public void raesaKlukku() {
      if(menuController.valinnErfidleiki() != null){
         erfidleiki(menuController.valinnErfidleiki());
      }
      else {
         erfidleiki("Auðvelt");
      }
   }
}


