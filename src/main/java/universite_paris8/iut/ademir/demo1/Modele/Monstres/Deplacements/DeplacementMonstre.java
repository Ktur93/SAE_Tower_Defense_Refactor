package universite_paris8.iut.ademir.demo1.Modele.Monstres.Deplacements;

import java.util.ArrayList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public class DeplacementMonstre implements StrategieDeplacement {
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

            deplacerHorizontalement(monstre, cibleX);
            deplacerVerticalement(monstre, cibleY);

            // quand la case est atteinte on passe a la suivante
            if (monstre.getX() == cibleX && monstre.getY() == cibleY) {
                indiceChemin++;
            }
        }
    }

    private void deplacerHorizontalement(Monstre monstre, double cibleX) {
        if (monstre.getX() < cibleX) {
            monstre.setX(monstre.getX() + monstre.getVitesse());

            if (monstre.getX() > cibleX) {
                monstre.setX(cibleX);
            }
        } else if (monstre.getX() > cibleX) {
            monstre.setX(monstre.getX() - monstre.getVitesse());

            if (monstre.getX() < cibleX) {
                monstre.setX(cibleX);
            }
        }
    }

    private void deplacerVerticalement(Monstre monstre, double cibleY) {
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
    }

    public boolean estArrive() {
        return indiceChemin >= chemin.size() - 1;
    }

    @Override
    public Position getPosition(Monstre monstre) {
        return chemin.get(indiceChemin);
    }
}