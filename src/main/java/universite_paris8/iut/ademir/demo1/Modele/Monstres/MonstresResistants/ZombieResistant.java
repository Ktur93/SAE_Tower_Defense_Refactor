package universite_paris8.iut.ademir.demo1.Modele.Monstres.MontresResistants;

import java.util.ArrayList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Zombie;

public class ZombieResistant extends Zombie {

    public ZombieResistant(ArrayList<Position> chemin) {
        super(chemin);
    }

    @Override
    public void recevoirDegatsPoison(int degats) {
        // Le projectile retire des PV,
        // mais l'effet poison ne s'active pas.
        recevoirDegats(degats);
    }
}