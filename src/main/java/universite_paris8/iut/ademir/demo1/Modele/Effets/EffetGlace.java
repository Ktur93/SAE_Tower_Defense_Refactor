package universite_paris8.iut.ademir.demo1.Modele.Effets;

import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;

public class EffetGlace extends EffetAvecDuree {

    private static final double VITESSE_GLACE = 2;
    private static final int DUREE_GLACE = 600;

    public EffetGlace() {
        super();
    }

    @Override
    protected void appliquerEffet(Monstre monstre) {
        monstre.setVitesse(VITESSE_GLACE);

        if (this.compteur != 0 && this.compteur % DUREE_GLACE == 0) {
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
