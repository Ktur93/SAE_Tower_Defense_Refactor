package universite_paris8.iut.ademir.demo1.Modele.Effets;

import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public class EffetPoison extends Effet {
    private boolean actif;
    private int compteur;

    public boolean isActif() {
        return actif;
    }

    public int getCompteur() {
        return compteur;
    }

    public EffetPoison() {
        this.actif = false;
        this.compteur = 0;
    }

    public void activer() {
        actif = true;
    }

    public void appliquer(Monstre monstre) {

        if(this.actif){
            if (this.compteur%45==0) {
                monstre.setPv(monstre.getPv() - 10);
            }
            this.compteur++;
        }
    }
}
