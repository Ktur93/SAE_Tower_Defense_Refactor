package universite_paris8.iut.ademir.demo1.Modele.Effets;

import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public class EffetGlace extends Effet {

    public EffetGlace() {
        super();
    }

    @Override
    public void activer() {
        super.activer();
        this.compteur = 0;
    }

    @Override
    protected void appliquerEffet(Monstre monstre) {
        monstre.setVitesse(2);

        if (this.compteur != 0 && this.compteur % 600 == 0) {
            arreter(monstre);
            System.out.println("fin de effet Glace");
        }


    }

    @Override
    public void arreter(Monstre monstre) {
        // Arrête l'effet et remet son compteur à zéro
        desactiver();

        // Le monstre n'est plus glacé
        monstre.setGlace(false);

        // Le monstre retrouve sa vitesse normale
        monstre.setVitesse(monstre.getVitesseNormal());
    }
}
