package universite_paris8.iut.ademir.demo1.Modele.Monstres.MonstresResistants;

import java.util.ArrayList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Pillager;

public class PillagerResistant extends Pillager {

    public PillagerResistant(ArrayList<Position> chemin) {
        super(chemin);
    }

    @Override
    public void recevoirDegatsPoison(int degats) {
        recevoirDegats(degats);
    }

    @Override
    public void recevoirDegatsGlace(int degats) {
        recevoirDegats(degats);
    }
}