package hi.verkefni5.vidmot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GoldApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(GoldApplication.class.getResource("goldrush-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        ((GoldController) fxmlLoader.getController()).orvatakkar();
        stage.setTitle("Goldrush!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}