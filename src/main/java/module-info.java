module universite_paris8.iut.ademir.demo1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires jdk.compiler;
    requires javafx.base;
    requires universite_paris8.iut.ademir.demo1;

    opens universite_paris8.iut.ademir.demo1 to javafx.fxml;
    exports universite_paris8.iut.ademir.demo1;
    exports universite_paris8.iut.ademir.demo1.Controller;
    opens universite_paris8.iut.ademir.demo1.Controller to javafx.fxml;
}