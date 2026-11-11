package universite_paris8.iut.ademir.demo1.Modele.Monstres;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;

public interface StrategieDeplacement {

    void avancer(Monstre monstre);

    boolean estArrive();

    Position getPosition(Monstre monstre);
}