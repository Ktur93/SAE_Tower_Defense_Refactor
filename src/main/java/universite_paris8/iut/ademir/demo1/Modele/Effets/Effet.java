package universite_paris8.iut.ademir.demo1.Modele.Effets;

import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public abstract class Effet {
    private boolean actif;
    private int compteur;

    public Effet() {
        this.actif = false;
        this.compteur = 0;
    }

    public abstract void appliquer(Monstre monstre);

    public abstract boolean isActif();

    public abstract void activer();
}
