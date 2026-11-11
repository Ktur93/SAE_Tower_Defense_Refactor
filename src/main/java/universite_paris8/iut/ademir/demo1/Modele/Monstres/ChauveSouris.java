package universite_paris8.iut.ademir.demo1.Modele.Monstres;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import java.util.ArrayList;

public class ChauveSouris extends Monstre {
    public ChauveSouris(ArrayList<Position> chemin) {
        super(60, 4, 4, 25, 1, chemin);
        if (chemin != null && !chemin.isEmpty()) {
            this.setDeplacement(new DeplacementVolant(chemin.get(chemin.size() - 1)));
        }
    }
}
