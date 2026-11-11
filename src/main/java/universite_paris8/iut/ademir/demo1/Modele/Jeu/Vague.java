package universite_paris8.iut.ademir.demo1.Modele.Jeu;

import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.*;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Decorateurs.MonstreResistant;


import java.util.ArrayList;

public class Vague {

    private ArrayList<Monstre> monstresVague;
    private int indiceMonstre;
    private long dernierSpawn;
    private long delaiSpawn;


    public Vague () {
        this.monstresVague = new ArrayList<>();
        this.indiceMonstre = 0;
        this.dernierSpawn = 0;
        this.delaiSpawn = 40;
    }


    //mpd nouveaucode
    public void creeVague1(ArrayList<Position> chemin1) {
        this.monstresVague.clear();

        for (int i = 0; i < 4; i++) {
            monstresVague.add(new Zombie(chemin1));
        }

        //mpd ancien code
        //         monstresVague.add(new ZombieResistant(chemin1));
        monstresVague.add(new MonstreResistant(new Zombie(chemin1)));
    }

    //mpd nouveaucode
    public void creeVague2(ArrayList<Position> chemin1, ArrayList<Position> chemin2) {
        this.monstresVague.clear();

        for (int i = 0; i < 9; i++) {
            monstresVague.add(new Zombie(chemin1));
            monstresVague.add(new Araignee(chemin2));
        }

        //mpd ancien code
        //         monstresVague.add(new ZombieResistant(chemin1));

        monstresVague.add(new MonstreResistant(new Zombie(chemin1)));
        //         monstresVague.add(new AraigneeResistante(chemin2));
        monstresVague.add(new MonstreResistant(new Araignee(chemin2)));
    }

    //mpd nouveaucode
    public void creeVague3(ArrayList<Position> chemin1, ArrayList<Position> chemin2) {
        this.monstresVague.clear();

        for (int i = 0; i < 14; i++) {
            monstresVague.add(new Zombie(chemin1));
            monstresVague.add(new Araignee(chemin2));
            monstresVague.add(new Squelette(chemin1));
        }

        //mpd ancien code
        //         monstresVague.add(new ZombieResistant(chemin1));
        monstresVague.add(new MonstreResistant(new Zombie(chemin1)));
        //         monstresVague.add(new AraigneeResistante(chemin2));
        monstresVague.add(new MonstreResistant(new Araignee(chemin2)));
        //         monstresVague.add(new SqueletteResistant(chemin1));
        monstresVague.add(new MonstreResistant(new Squelette(chemin1)));
    }

    //mpd nouveaucode
    public void creeVague4(ArrayList<Position> chemin1, ArrayList<Position> chemin2,
                           ArrayList<Position> chemin3) {
        this.monstresVague.clear();

        for (int i = 0; i < 14; i++) {
            monstresVague.add(new Zombie(chemin1));
            monstresVague.add(new Araignee(chemin2));
            monstresVague.add(new Squelette(chemin3));
            monstresVague.add(new Pillager(chemin2));
        }

        //mpd ancien code
        //         monstresVague.add(new ZombieResistant(chemin1));
        monstresVague.add(new MonstreResistant(new Zombie(chemin1)));
        //         monstresVague.add(new AraigneeResistante(chemin2));
        monstresVague.add(new MonstreResistant(new Araignee(chemin2)));
        //         monstresVague.add(new SqueletteResistant(chemin3));
        monstresVague.add(new MonstreResistant(new Squelette(chemin3)));
        //         monstresVague.add(new PillagerResistant(chemin2));
        monstresVague.add(new MonstreResistant(new Pillager(chemin2)));
        //         monstresVague.add(new ChauveSourisResistante(chemin1));
        monstresVague.add(new MonstreResistant(new ChauveSouris(chemin1)));
    }

    //mpd nouveaucode
    public void creeVague5(ArrayList<Position> chemin1, ArrayList<Position> chemin2,
                           ArrayList<Position> chemin3) {
        this.monstresVague.clear();

        for (int i = 0; i < 18; i++) {
            monstresVague.add(new Zombie(chemin1));
            monstresVague.add(new Araignee(chemin2));
            monstresVague.add(new Squelette(chemin3));
            monstresVague.add(new Pillager(chemin3));
        }

        //mpd ancien code
        //         monstresVague.add(new ZombieResistant(chemin1));
        monstresVague.add(new MonstreResistant(new Zombie(chemin1)));
        //         monstresVague.add(new AraigneeResistante(chemin2));
        monstresVague.add(new MonstreResistant(new Araignee(chemin2)));
        //         monstresVague.add(new SqueletteResistant(chemin3));
        monstresVague.add(new MonstreResistant(new Squelette(chemin3)));
        //         monstresVague.add(new PillagerResistant(chemin3));
        monstresVague.add(new MonstreResistant(new Pillager(chemin3)));

        //         monstresVague.add(new ZombieResistant(chemin1));
        monstresVague.add(new MonstreResistant(new Zombie(chemin1)));
        //         monstresVague.add(new AraigneeResistante(chemin2));
        monstresVague.add(new MonstreResistant(new Araignee(chemin2)));
        //         monstresVague.add(new SqueletteResistant(chemin3));
        monstresVague.add(new MonstreResistant(new Squelette(chemin3)));
        //         monstresVague.add(new PillagerResistant(chemin3));
        monstresVague.add(new MonstreResistant(new Pillager(chemin3)));

        monstresVague.add(new Boss(chemin1));
    }

    public void mettreAJourVague(int compteur, ObservableList<Monstre> monstresPartie) {
        if (indiceMonstre >= monstresVague.size()) {

        } else if (indiceMonstre == 0 || compteur - dernierSpawn >= delaiSpawn) {
            monstresPartie.add(monstresVague.get(indiceMonstre));
            indiceMonstre++;
            dernierSpawn = compteur;
        }
    }

    public boolean tousLesMonstresEnvoyes() {
        return indiceMonstre >= monstresVague.size();
    }

    public void setIndiceMonstreZero() {
        this.indiceMonstre = 0;
    }
}
