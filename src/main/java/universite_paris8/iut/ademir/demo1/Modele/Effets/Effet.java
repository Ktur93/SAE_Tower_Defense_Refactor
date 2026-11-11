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

    public void desactiver() {
        actif = false;
        compteur = 0;
    }

    // Chaque effet définit sa propre action donc on met abstarct
    public void appliquer(Monstre monstre) {
        if (actif) {
            appliquerEffet(monstre);
            this.compteur++;
        }
    }

    protected abstract void appliquerEffet(Monstre monstre);


    public void arreter(Monstre monstre) {

    }
}
