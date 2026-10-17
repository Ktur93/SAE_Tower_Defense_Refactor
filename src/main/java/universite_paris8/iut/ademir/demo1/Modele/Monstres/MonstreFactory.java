package universite_paris8.iut.ademir.demo1.Modele.Monstres;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;

import java.util.ArrayList;

public class MonstreFactory {

    public static Monstre creerZombie(ArrayList<Position> chemin){
        return new Zombie(chemin);
    }

    public static Monstre creerAraignee(ArrayList<Position> chemin){
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


}
