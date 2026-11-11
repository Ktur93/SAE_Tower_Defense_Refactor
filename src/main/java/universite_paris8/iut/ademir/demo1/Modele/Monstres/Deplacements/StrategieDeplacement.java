package universite_paris8.iut.ademir.demo1.Modele.Monstres.Deplacements;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public interface StrategieDeplacement {

    void avancer(Monstre monstre);

    boolean estArrive();

    Position getPosition(Monstre monstre);
}