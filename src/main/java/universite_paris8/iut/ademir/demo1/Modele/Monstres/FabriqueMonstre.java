package universite_paris8.iut.ademir.demo1.Modele.Monstres;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Decorateurs.MonstreRapide;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Decorateurs.MonstreResistant;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Decorateurs.MonstreSoigneur;

import java.util.ArrayList;

public class FabriqueMonstre {

    public static Monstre creerZombie(ArrayList<Position> chemin) {
        return new Zombie(chemin);
    }

    public static Monstre creerAraignee(ArrayList<Position> chemin) {
        return new Araignee(chemin);
    }

    public static Monstre creerSquelette(ArrayList<Position> chemin) {
        return new Squelette(chemin);
    }

    public static Monstre creerPillager(ArrayList<Position> chemin) {
        return new Pillager(chemin);
    }

    public static Monstre creerBoss(ArrayList<Position> chemin) {
        return new Boss(chemin);
    }

    public static Monstre creerChauveSouris(ArrayList<Position> chemin) {
        return new ChauveSouris(chemin);
    }

    public static Monstre creerRapide(Monstre monstre) {
        return new MonstreRapide(monstre);
    }

    public static Monstre creerResistant(Monstre monstre) {
        return new MonstreResistant(monstre);
    }

    public static Monstre creerSoigneur(Monstre monstre) {
        return new MonstreSoigneur(monstre);
    }
}
