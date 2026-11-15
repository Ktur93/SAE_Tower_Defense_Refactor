package universite_paris8.iut.ademir.demo1.Modele.Monstres;

import java.util.ArrayList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Deplacements.DeplacementVolant;

public class ChauveSouris extends MonstreBase {

    //mpd nouveaucode
    public ChauveSouris(ArrayList<Position> chemin) {
        super(70, 4, 4, 10, 1, chemin);

        Position portail = chemin.get(chemin.size() - 1);

        this.setDeplacement(new DeplacementVolant(portail));
    }
}