package universite_paris8.iut.ademir.demo1.Modele.Monstres;

import java.util.ArrayList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;

public class DeplacementMonstre {
    private ArrayList<Position> chemin;
    private int indiceChemin;

    public DeplacementMonstre(ArrayList<Position> chemin) {
        this.chemin = chemin;
        this.indiceChemin = 0;
    }

    public void avancer(Monstre monstre) {
        if (!estArrive()) {
            Position posSuivante = chemin.get(indiceChemin + 1);
            double cibleX = posSuivante.getX() * 64;
            double cibleY = posSuivante.getY() * 64;

            // si il est a gauche de la cible   on le fait avancé avec sa vitesse
            if (monstre.getX() < cibleX) {
                monstre.setX(monstre.getX()+monstre.getVitesse());

                if (monstre.getX() > cibleX) {
                    monstre.setX(cibleX);
                }
            } else if (monstre.getX() > cibleX) {
                monstre.setX( monstre.getX() - monstre.getVitesse());

                if (monstre.getX() < cibleX) {
                    monstre.setX(cibleX);
                }
            }

            if (monstre.getY() > cibleY) {
                monstre.setY(monstre.getY() - monstre.getVitesse());

                if (monstre.getY() < cibleY) {
                    monstre.setY(cibleY);
                }
            } else if (monstre.getY() < cibleY) {
                monstre.setY(monstre.getY() + monstre.getVitesse());

                if (monstre.getY() > cibleY) {
                    monstre.setY(cibleY);
                }
            }


            if (monstre.getX() == posSuivante.getX() * 64 && monstre.getY() == posSuivante.getY() * 64) {
                this.indiceChemin++;
            }
        }
    }

    public boolean estArrive() {
        return indiceChemin >= chemin.size() - 1;
    }

    public Position getPosition() {
        return chemin.get(indiceChemin);
    }
}