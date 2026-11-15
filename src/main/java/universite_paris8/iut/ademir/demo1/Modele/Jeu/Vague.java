package universite_paris8.iut.ademir.demo1.Modele.Jeu;

import javafx.collections.ObservableList;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Monstre;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.FabriqueMonstre;

import java.util.ArrayList;

public class Vague {

    private ArrayList<Monstre> monstresVague;
    private int indiceMonstre;
    private long dernierSpawn;
    private long delaiSpawn;

    public Vague() {
        this.monstresVague = new ArrayList<>();
        this.indiceMonstre = 0;
        this.dernierSpawn = 0;
        this.delaiSpawn = 40;
    }

    public void creeVague1(ArrayList<Position> chemin1) {
        creeVague1(chemin1, 1);
    }

    public void creeVague1(ArrayList<Position> chemin1, int niveau) {
        this.monstresVague.clear();

        for (int i = 0; i < 4; i++) {
            monstresVague.add(FabriqueMonstre.creerZombie(chemin1));
        }

        monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerZombie(chemin1)));

        if (niveau >= 2) {
            monstresVague.add(FabriqueMonstre.creerRapide(FabriqueMonstre.creerZombie(chemin1)));
        }
        if (niveau >= 3) {
            monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerZombie(chemin1)));
        }
        if (niveau >= 4) {
            monstresVague.add(FabriqueMonstre.creerSoigneur(FabriqueMonstre.creerZombie(chemin1)));
        }
        if (niveau >= 5) {
            monstresVague.add(FabriqueMonstre.creerRapide(FabriqueMonstre.creerZombie(chemin1)));
            monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerZombie(chemin1)));
        }
    }

    public void creeVague2(ArrayList<Position> chemin1, ArrayList<Position> chemin2) {
        creeVague2(chemin1, chemin2, 1);
    }

    public void creeVague2(ArrayList<Position> chemin1, ArrayList<Position> chemin2, int niveau) {
        this.monstresVague.clear();

        for (int i = 0; i < 9; i++) {
            monstresVague.add(FabriqueMonstre.creerZombie(chemin1));
            monstresVague.add(FabriqueMonstre.creerAraignee(chemin2));
        }

        monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerZombie(chemin1)));
        monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerAraignee(chemin2)));

        if (niveau >= 2) {
            monstresVague.add(FabriqueMonstre.creerRapide(FabriqueMonstre.creerZombie(chemin1)));
            monstresVague.add(FabriqueMonstre.creerRapide(FabriqueMonstre.creerAraignee(chemin2)));
        }
        if (niveau >= 3) {
            monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerZombie(chemin1)));
            monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerAraignee(chemin2)));
        }
        if (niveau >= 4) {
            monstresVague.add(FabriqueMonstre.creerSoigneur(FabriqueMonstre.creerZombie(chemin1)));
            monstresVague.add(FabriqueMonstre.creerSoigneur(FabriqueMonstre.creerAraignee(chemin2)));
        }
        if (niveau >= 5) {
            monstresVague.add(FabriqueMonstre.creerRapide(FabriqueMonstre.creerZombie(chemin1)));
            monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerZombie(chemin1)));
        }
    }

    public void creeVague3(ArrayList<Position> chemin1, ArrayList<Position> chemin2) {
        creeVague3(chemin1, chemin2, 1);
    }

    public void creeVague3(ArrayList<Position> chemin1, ArrayList<Position> chemin2, int niveau) {
        this.monstresVague.clear();

        for (int i = 0; i < 14; i++) {
            monstresVague.add(FabriqueMonstre.creerZombie(chemin1));
            monstresVague.add(FabriqueMonstre.creerAraignee(chemin2));
            monstresVague.add(FabriqueMonstre.creerSquelette(chemin1));
        }

        monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerZombie(chemin1)));
        monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerAraignee(chemin2)));
        monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerSquelette(chemin1)));

        if (niveau >= 2) {
            monstresVague.add(FabriqueMonstre.creerRapide(FabriqueMonstre.creerZombie(chemin1)));
            monstresVague.add(FabriqueMonstre.creerRapide(FabriqueMonstre.creerAraignee(chemin2)));
            monstresVague.add(FabriqueMonstre.creerRapide(FabriqueMonstre.creerSquelette(chemin1)));
        }
        if (niveau >= 3) {
            monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerZombie(chemin1)));
            monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerAraignee(chemin2)));
            monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerSquelette(chemin1)));
        }
        if (niveau >= 4) {
            monstresVague.add(FabriqueMonstre.creerSoigneur(FabriqueMonstre.creerZombie(chemin1)));
            monstresVague.add(FabriqueMonstre.creerSoigneur(FabriqueMonstre.creerAraignee(chemin2)));
            monstresVague.add(FabriqueMonstre.creerSoigneur(FabriqueMonstre.creerSquelette(chemin1)));
        }
        if (niveau >= 5) {
            monstresVague.add(FabriqueMonstre.creerRapide(FabriqueMonstre.creerZombie(chemin1)));
            monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerZombie(chemin1)));
        }
    }

    public void creeVague4(ArrayList<Position> chemin1, ArrayList<Position> chemin2, ArrayList<Position> chemin3) {
        creeVague4(chemin1, chemin2, chemin3, 1);
    }

    public void creeVague4(ArrayList<Position> chemin1, ArrayList<Position> chemin2, ArrayList<Position> chemin3, int niveau) {
        this.monstresVague.clear();

        for (int i = 0; i < 14; i++) {
            monstresVague.add(FabriqueMonstre.creerZombie(chemin1));
            monstresVague.add(FabriqueMonstre.creerAraignee(chemin2));
            monstresVague.add(FabriqueMonstre.creerSquelette(chemin3));
            monstresVague.add(FabriqueMonstre.creerPillager(chemin2));
        }

        monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerZombie(chemin1)));
        monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerAraignee(chemin2)));
        monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerSquelette(chemin3)));
        monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerPillager(chemin2)));
        monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerChauveSouris(chemin1)));

        if (niveau >= 2) {
            monstresVague.add(FabriqueMonstre.creerRapide(FabriqueMonstre.creerZombie(chemin1)));
            monstresVague.add(FabriqueMonstre.creerRapide(FabriqueMonstre.creerAraignee(chemin2)));
            monstresVague.add(FabriqueMonstre.creerRapide(FabriqueMonstre.creerSquelette(chemin3)));
            monstresVague.add(FabriqueMonstre.creerRapide(FabriqueMonstre.creerPillager(chemin2)));
        }
        if (niveau >= 3) {
            monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerZombie(chemin1)));
            monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerAraignee(chemin2)));
            monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerSquelette(chemin3)));
            monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerPillager(chemin2)));
        }
        if (niveau >= 4) {
            monstresVague.add(FabriqueMonstre.creerSoigneur(FabriqueMonstre.creerZombie(chemin1)));
            monstresVague.add(FabriqueMonstre.creerSoigneur(FabriqueMonstre.creerAraignee(chemin2)));
            monstresVague.add(FabriqueMonstre.creerSoigneur(FabriqueMonstre.creerSquelette(chemin3)));
            monstresVague.add(FabriqueMonstre.creerSoigneur(FabriqueMonstre.creerPillager(chemin2)));
        }
        if (niveau >= 5) {
            monstresVague.add(FabriqueMonstre.creerRapide(FabriqueMonstre.creerZombie(chemin1)));
            monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerZombie(chemin1)));
        }
    }

    public void creeVague5(ArrayList<Position> chemin1, ArrayList<Position> chemin2, ArrayList<Position> chemin3) {
        creeVague5(chemin1, chemin2, chemin3, 1);
    }

    public void creeVague5(ArrayList<Position> chemin1, ArrayList<Position> chemin2, ArrayList<Position> chemin3, int niveau) {
        this.monstresVague.clear();

        for (int i = 0; i < 18; i++) {
            monstresVague.add(FabriqueMonstre.creerZombie(chemin1));
            monstresVague.add(FabriqueMonstre.creerAraignee(chemin2));
            monstresVague.add(FabriqueMonstre.creerSquelette(chemin3));
            monstresVague.add(FabriqueMonstre.creerPillager(chemin3));
        }

        monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerZombie(chemin1)));
        monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerAraignee(chemin2)));
        monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerSquelette(chemin3)));
        monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerPillager(chemin3)));
        monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerZombie(chemin1)));
        monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerAraignee(chemin2)));
        monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerSquelette(chemin3)));
        monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerPillager(chemin3)));

        if (niveau >= 2) {
            monstresVague.add(FabriqueMonstre.creerRapide(FabriqueMonstre.creerZombie(chemin1)));
            monstresVague.add(FabriqueMonstre.creerRapide(FabriqueMonstre.creerAraignee(chemin2)));
            monstresVague.add(FabriqueMonstre.creerRapide(FabriqueMonstre.creerSquelette(chemin3)));
            monstresVague.add(FabriqueMonstre.creerRapide(FabriqueMonstre.creerPillager(chemin3)));
            monstresVague.add(FabriqueMonstre.creerRapide(FabriqueMonstre.creerChauveSouris(chemin1)));
        }
        if (niveau >= 3) {
            monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerZombie(chemin1)));
            monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerAraignee(chemin2)));
            monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerSquelette(chemin3)));
            monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerPillager(chemin3)));
            monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerChauveSouris(chemin1)));
        }
        if (niveau >= 4) {
            monstresVague.add(FabriqueMonstre.creerSoigneur(FabriqueMonstre.creerZombie(chemin1)));
            monstresVague.add(FabriqueMonstre.creerSoigneur(FabriqueMonstre.creerAraignee(chemin2)));
            monstresVague.add(FabriqueMonstre.creerSoigneur(FabriqueMonstre.creerSquelette(chemin3)));
            monstresVague.add(FabriqueMonstre.creerSoigneur(FabriqueMonstre.creerPillager(chemin3)));
            monstresVague.add(FabriqueMonstre.creerSoigneur(FabriqueMonstre.creerChauveSouris(chemin1)));
        }
        if (niveau >= 5) {
            monstresVague.add(FabriqueMonstre.creerRapide(FabriqueMonstre.creerZombie(chemin1)));
            monstresVague.add(FabriqueMonstre.creerResistant(FabriqueMonstre.creerZombie(chemin1)));
        }

        monstresVague.add(FabriqueMonstre.creerBoss(chemin1));
    }

    public void mettreAJourVague(int compteur, ObservableList<Monstre> monstres) {
        if (this.indiceMonstre < this.monstresVague.size()) {
            if (this.indiceMonstre == 0 || compteur - dernierSpawn >= delaiSpawn) {
                monstres.add(this.monstresVague.get(this.indiceMonstre));
                this.indiceMonstre++;
                this.dernierSpawn = compteur;
            }
        }
    }

    public boolean tousLesMonstresEnvoyes() {
        return this.indiceMonstre >= this.monstresVague.size();
    }

    public void setIndiceMonstreZero() {
        this.indiceMonstre = 0;
    }

    public void ajouterMonstre(Monstre monstre) {
        monstresVague.add(monstre);
    }
}
