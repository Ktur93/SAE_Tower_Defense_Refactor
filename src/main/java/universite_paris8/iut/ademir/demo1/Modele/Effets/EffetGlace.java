package universite_paris8.iut.ademir.demo1.Modele.Effets;

import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public class EffetGlace extends Effet {
    private boolean actif;
    private int compteur;

    public EffetGlace() {
        this.actif = false;
        this.compteur = 0;
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

    public void appliquer(Monstre monstre) {
        if (this.actif) {
            monstre.setVitesse(2);
            if(this.compteur != 0 && this.compteur%600 == 0){
                monstre.setGlace(false);
                monstre.setVitesse(monstre.getVitesseNormal());
                System.out.println("fin de effet Glace");
                this.actif = false;
            }

            this.compteur++;
        }
    }
}
