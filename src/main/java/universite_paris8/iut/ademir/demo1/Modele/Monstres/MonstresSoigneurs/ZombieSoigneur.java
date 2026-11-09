package universite_paris8.iut.ademir.demo1.Modele.Monstres;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;

public class ZombieSoigneur extends Zombie {

    private int compteurSoin;

    public ZombieSoigneur(ArrayList<Position> chemin) {
        super(chemin);
        this.compteurSoin = 0;
    }

    @Override
    public void agir(ObservableList<Monstre> monstres) {
        compteurSoin++;

        // Environ 3 secondes
        if (compteurSoin >= 180) {
            soignerMonstres(monstres);
            compteurSoin = 0;
        }
    }

    public void soignerMonstres(ObservableList<Monstre> monstres) {
        for (Monstre monstre : monstres) {

            // Le soigneur ne se soigne pas et ne soigne pas les morts.
            if (monstre != this && !monstre.estMort()) {

                int x = getPosition().getX() - monstre.getPosition().getX();
                int y = getPosition().getY() - monstre.getPosition().getY();

                int distance = x * x + y * y;

                // Le soigneur agit dans un rayon de 3 cases.
                if (distance <= 3 * 3) {
                    int nouveauxPv = monstre.getPv() + 10;

                    // On évite de dépasser les PV maximum du monstre.
                    if (nouveauxPv > monstre.getPvMax()) {
                        nouveauxPv = monstre.getPvMax();
                    }

                    monstre.setPv(nouveauxPv);
                }
            }
        }
    }
}