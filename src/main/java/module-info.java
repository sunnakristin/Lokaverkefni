module hi.verkefni5.goldrush {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens hi.verkefni5.vidmot to javafx.fxml;
    exports hi.verkefni5.vidmot;
}