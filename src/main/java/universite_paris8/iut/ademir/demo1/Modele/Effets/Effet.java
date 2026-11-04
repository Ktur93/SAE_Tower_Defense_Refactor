package universite_paris8.iut.ademir.demo1.Modele.Effets;

import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public abstract class Effet {
    // protected pr que ce soit accessible dans ses sous classes
    protected boolean actif;
    protected int compteur;

    public Effet() {
        actif = false;
        compteur = 0;
    }

    public boolean isActif() {
        return actif;
    }

    public int getCompteur() {
        return compteur;
    }

    public void activer() {
        actif = true;
    }

    // Chaque effet définit sa propre action donc on met abstarct
    public abstract void appliquer(Monstre monstre);
}
