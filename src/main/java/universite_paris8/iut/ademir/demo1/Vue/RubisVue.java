//obs nouveau code
// Le label observe les rubis : aucun rafraichissement dans la boucle du jeu.
package universite_paris8.iut.ademir.demo1.Vue;

import javafx.scene.control.Label;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Partie;

public class RubisVue {

    public RubisVue(Partie partie, Label labelRubis) {
        labelRubis.textProperty().bind(partie.rubisProperty().asString("Rubis : %d"));
    }
}
