package universite_paris8.iut.ademir.demo1.Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Carte;
import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;

import static org.junit.jupiter.api.Assertions.*;

public class CarteTest {

    @BeforeEach
    public void preparerCarte() {
        Carte.getInstance().caseBloquer();
    }

    @Test
    public void caseHorsCarteNestPasMarchable() {
        // On recupere la carte partagee
        Carte carte = Carte.getInstance();

        // Les cases hors limite ne doivent pas etre marchable
        assertFalse(carte.estMarchable(-1, 0));
        assertFalse(carte.estMarchable(0, -1));
        assertFalse(carte.estMarchable(100, 0));
        assertFalse(carte.estMarchable(0, 100));
    }

    @Test
    public void caseDeDepartEstMarchable() {
        // On recupere la carte partagee
        Carte carte = Carte.getInstance();

        // La case de depart de la premiere entree doit etre marchable
        assertTrue(carte.estMarchable(0, 7));
    }

    @Test
    public void caseVideNestPasMarchable() {
        // On recupere la carte partagee
        Carte carte = Carte.getInstance();

        // Une case vide ne doit pas etre marchable
        assertFalse(carte.estMarchable(0, 0));
    }

    @Test
    public void trouverArriveeRetourneUnePosition() {
        // On recupere la carte partagee
        Carte carte = Carte.getInstance();

        // On recupere la position d arrivee
        Position arrivee = carte.trouverArrivee();

        // l'arrivée doit existé
        assertNotNull(arrivee);
    }

    @Test
    public void caseDebloquerTransformeUneCaseAchetable() {
        // On recupere la carte partagee
        Carte carte = Carte.getInstance();

        // La case 14,2 = 20 dans la carte, donc elle est achetable
        Position position = new Position(14, 2);

        // Au debut ce n'est pas encore une case tour
        assertFalse(carte.estCaseTour(14, 2));

        // On debloque la case
        boolean resultat = carte.caseDebloquer(position);

        // Le deblocage doit marcher et la case devient une case tour
        assertTrue(resultat);
        assertTrue(carte.estCaseTour(14, 2));
    }

    @Test
    public void caseBloquerRebloqueLesCasesAchetees() {
        // On recupere la carte partagee
        Carte carte = Carte.getInstance();

        // On debloque une case achetable
        Position position = new Position(14, 2);
        carte.caseDebloquer(position);

        // La case est devenue une case tour
        assertTrue(carte.estCaseTour(14, 2));

        //on rebloque les cases achetees
        carte.caseBloquer();

        // La case ne doit plus etre une case tour
        assertFalse(carte.estCaseTour(14, 2));
    }
    @Test
    public void getInstanceRetourneToujoursLaMemeCarte() {
        assertSame(Carte.getInstance(), Carte.getInstance());
    }

    @Test
    public void modificationsSontPartageesEntreLesAcces() {
        Carte premiere = Carte.getInstance();
        Carte seconde = Carte.getInstance();

        assertFalse(seconde.estCaseTour(14, 2));
        assertTrue(premiere.caseDebloquer(new Position(14, 2)));
        assertTrue(seconde.estCaseTour(14, 2));
    }
}