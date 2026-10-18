package universite_paris8.iut.ademir.demo1.Modele.Monstres;

public class EffetPoison {
    private boolean actif;
    private int compteur;

    public EffetPoison() {
        this.actif = false;
        this.compteur = 0;
    }

    public void activer() {
        actif = true;
    }

    public void appliquer(Monstre monstre) {
        // A faire : deplacer ici les degats reguliers du poison
        // et appelé cette methode depuis Monstre.avancer().
    }
}
