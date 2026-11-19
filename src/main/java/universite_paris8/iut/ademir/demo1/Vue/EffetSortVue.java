package universite_paris8.iut.ademir.demo1.Vue;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import universite_paris8.iut.ademir.demo1.Main;
import universite_paris8.iut.ademir.demo1.Modele.Sorts.Sort;

public class EffetSortVue {

    private Pane pane;

    public EffetSortVue(Pane pane) {
        this.pane = pane;
    }

    public void lancerAnimation(Sort sort, double x, double y, Runnable actionImpact) {
        String nomImage;
        if (sort == Sort.BOULE_FEU) {
            nomImage = "Tuiles/Bloc/boule_de_feu.gif";
        } else {
            nomImage = "Tuiles/Bloc/boule_de_neige.gif";
        }
        Image image = new Image(Main.class.getResourceAsStream(nomImage));
        ImageView boule = new ImageView(image);
        boule.setFitWidth(96);
        boule.setFitHeight(96);
        // Position de départ : au-dessus du point cliqué.
        boule.setLayoutX(x - 24);
        boule.setLayoutY(-50);
        boule.setMouseTransparent(true);
        pane.getChildren().add(boule);

        // La boule descend jusqu'à l'endroit du clic.
        TranslateTransition descente = new TranslateTransition(Duration.millis(500), boule);
        descente.setToY(y + 26);

        descente.setOnFinished(event -> {
            pane.getChildren().remove(boule);
            actionImpact.run();
        });
        descente.play();
    }
}