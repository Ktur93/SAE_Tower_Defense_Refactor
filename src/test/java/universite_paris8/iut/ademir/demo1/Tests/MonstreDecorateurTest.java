// Tests de regression des capacites combinees et de l etat partage.
package universite_paris8.iut.ademir.demo1.Tests;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import universite_paris8.iut.ademir.demo1.Modele.Cartes.Position;
import universite_paris8.iut.ademir.demo1.Modele.Jeu.Vague;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.*;
import universite_paris8.iut.ademir.demo1.Modele.Monstres.Decorateurs.*;

import static org.junit.jupiter.api.Assertions.*;

public class MonstreDecorateurTest {

    // Fournit un chemin assez long pour observer les effets sans atteindre le portail.
    private ArrayList<Position> chemin() {
        return new ArrayList<>(List.of(new Position(0, 0), new Position(100, 0)));
    }

    // Compose les trois capacites dans chacun des six ordres possibles.
    private Monstre combiner(Monstre base, int[] ordre) {
        Monstre resultat = base;
        for (int capacite : ordre) {
            switch (capacite) {
                case 0: resultat = new MonstreRapide(resultat); break;
                case 1: resultat = new MonstreResistant(resultat); break;
                default: resultat = new MonstreSoigneur(resultat); break;
            }
        }
        return resultat;
    }

    // L affichage et le portail doivent manipuler le meme monstre et les memes proprietes.
    @Test
    public void conserveEtatIdentiteEtProprietesJavaFx() {
        Monstre base = new Zombie(chemin());
        Monstre decore = new MonstreSoigneur(new MonstreRapide(new MonstreResistant(base)));
        assertSame(base, decore.getMonstreDeBase());
        assertSame(base.pvProperty(), decore.pvProperty());
        assertSame(base.xProperty(), decore.xProperty());
        assertSame(base.yProperty(), decore.yProperty());
        assertSame(base.glaceProperty(), decore.glaceProperty());
        assertSame(base.feuProperty(), decore.feuProperty());
        assertEquals(base.getMonstreID(), decore.getMonstreID());
        decore.setPv(25);
        assertEquals(25, base.getPv());
        decore.setRecompense(0);
        assertEquals(0, base.getRecompense());
        decore.avancer(decore);
        assertTrue(base.getX() > 0);
        decore.setPv(0);
        assertTrue(decore.estMort());
    }

    // Envelopper un monstre ne doit pas consommer de nouveaux identifiants.
    @Test
    public void neCreePasDIdentifiantsSupplementaires() {
        Monstre base = new Zombie(chemin());
        new MonstreSoigneur(new MonstreRapide(new MonstreResistant(base)));
        Monstre suivant = new Zombie(chemin());
        int premier = Integer.parseInt(base.getMonstreID().substring(7));
        int second = Integer.parseInt(suivant.getMonstreID().substring(7));
        assertEquals(premier + 1, second);
    }

    // Verifie le seuil exact, le multiplicateur stable et le retour apres soin.
    @Test
    public void vitesseChangeAuSeuilEtRevientApresSoin() {
        Monstre rapide = new MonstreRapide(new Zombie(chemin()));
        ObservableList<Monstre> monstres = FXCollections.observableArrayList(rapide);
        rapide.setPv(51);
        rapide.agir(monstres);
        assertEquals(3.5, rapide.getVitesse(), 0.0001);
        rapide.setPv(50);
        rapide.agir(monstres);
        rapide.agir(monstres);
        assertEquals(5.25, rapide.getVitesse(), 0.0001);
        rapide.setPv(60);
        rapide.agir(monstres);
        assertEquals(3.5, rapide.getVitesse(), 0.0001);
    }

    // Le resistant subit les impacts directs sans gel ni degats periodiques de poison.
    @Test
    public void resistanceBloqueGelEtPoisonMaisPasImpacts() {
        Monstre resistant = new MonstreResistant(new Zombie(chemin()));
        resistant.recevoirDegatsGlace(7);
        resistant.recevoirDegatsPoison(8);
        for (int i = 0; i < 100; i++) resistant.avancer(resistant);
        assertEquals(85, resistant.getPv());
        assertFalse(resistant.estGlace());
        assertEquals(3.5, resistant.getVitesse(), 0.0001);
    }

    // Le feu reste actif sur un resistant, pendant cinq secondes de simulation.
    @Test
    public void resistantResteSensibleAuFeu() {
        Monstre resistant = new MonstreResistant(new Zombie(chemin()));
        resistant.recevoirDegatsFeu(10, resistant);
        assertTrue(resistant.estEnFeu());
        for (int i = 0; i < 300; i++) resistant.avancer(resistant);
        assertEquals(65, resistant.getPv());
        assertFalse(resistant.estEnFeu());
    }

    // La fin naturelle du gel doit retrouver la vitesse du rapide a travers une autre couche.
    @Test
    public void finDuGelRetrouveVitesseRapide() {
        Monstre rapide = new MonstreSoigneur(new MonstreRapide(new Zombie(chemin())));
        rapide.setPv(40);
        rapide.recevoirDegatsGlace(0);
        rapide.avancer(rapide);
        assertEquals(2, rapide.getVitesse(), 0.0001);
        rapide.agir(FXCollections.observableArrayList(rapide));
        assertEquals(2, rapide.getVitesse(), 0.0001);
        for (int i = 0; i < 600; i++) rapide.avancer(rapide);
        assertFalse(rapide.estGlace());
        assertEquals(5.25, rapide.getVitesse(), 0.0001);
    }

    // Le feu doit interrompre immediatement le gel sans perdre la capacite rapide.
    @Test
    public void feuInterromptGelEtRetrouveVitesseRapide() {
        Monstre rapide = new MonstreSoigneur(new MonstreRapide(new Zombie(chemin())));
        rapide.setPv(40);
        rapide.recevoirDegatsGlace(0);
        rapide.avancer(rapide);
        rapide.recevoirDegatsFeu(3, rapide);
        assertEquals(37, rapide.getPv());
        assertFalse(rapide.estGlace());
        assertTrue(rapide.estEnFeu());
        assertEquals(5.25, rapide.getVitesse(), 0.0001);
    }

    // Verifie la cadence du soin, sa borne et l absence d autosoin avec une couche exterieure.
    @Test
    public void soinRespecteCadencePlafondEtIdentite() {
        Monstre soigneur = new MonstreRapide(new MonstreSoigneur(new Zombie(chemin())));
        Monstre cible = new Zombie(chemin());
        soigneur.setPv(40);
        cible.setPv(95);
        ObservableList<Monstre> monstres = FXCollections.observableArrayList(soigneur, cible);
        for (int i = 0; i < 179; i++) soigneur.agir(monstres);
        assertEquals(95, cible.getPv());
        soigneur.agir(monstres);
        assertEquals(100, cible.getPv());
        assertEquals(40, soigneur.getPv());
        cible.setPv(80);
        for (int i = 0; i < 180; i++) soigneur.agir(monstres);
        assertEquals(90, cible.getPv());
    }

    // Verifie la frontiere de trois cases et l exclusion des morts.
    @Test
    public void soinRespecteRayonEtExclutMorts() {
        MonstreSoigneur soigneur = new MonstreSoigneur(new Zombie(chemin()));
        Monstre proche = new ChauveSouris(chemin());
        Monstre loin = new ChauveSouris(chemin());
        Monstre mort = new Zombie(chemin());
        proche.setX(3 * 64);
        proche.setPv(30);
        loin.setX(4 * 64);
        loin.setPv(30);
        mort.setPv(0);
        soigneur.soignerMonstres(FXCollections.observableArrayList(soigneur, proche, loin, mort));
        assertEquals(40, proche.getPv());
        assertEquals(30, loin.getPv());
        assertEquals(0, mort.getPv());
    }

    // Les 30 combinaisons espece/ordre doivent conserver les trois capacites.
    @Test
    public void troisCapacitesFonctionnentDansTousLesOrdresEtSurCinqEspeces() {
        List<Function<ArrayList<Position>, Monstre>> especes = List.of(
                Zombie::new, Araignee::new, Squelette::new, Pillager::new, ChauveSouris::new);
        int[][] ordres = {{0,1,2}, {0,2,1}, {1,0,2}, {1,2,0}, {2,0,1}, {2,1,0}};
        for (Function<ArrayList<Position>, Monstre> espece : especes) {
            for (int[] ordre : ordres) {
                Monstre base = espece.apply(chemin());
                double vitesse = base.getVitesse();
                Monstre monstre = combiner(base, ordre);
                Monstre cible = new Zombie(chemin());
                cible.setPv(50);
                int pv = monstre.getPvMax() / 2;
                monstre.setPv(pv);
                monstre.recevoirDegatsGlace(0);
                monstre.recevoirDegatsPoison(0);
                ObservableList<Monstre> monstres = FXCollections.observableArrayList(monstre, cible);
                for (int i = 0; i < 180; i++) monstre.agir(monstres);
                assertEquals(60, cible.getPv());
                assertEquals(pv, monstre.getPv());
                assertEquals(vitesse * 1.5, monstre.getVitesse(), 0.0001);
                monstre.avancer(monstre);
                assertFalse(monstre.estGlace());
                assertEquals(pv, monstre.getPv());
                assertTrue(monstre.getX() > 0);
            }
        }
    }

    // Une chauve-souris decoree doit encore voler directement vers le portail.
    @Test
    public void conserveVolEtDestinationDeLaChauveSouris() {
        ArrayList<Position> chemin = new ArrayList<>(List.of(
                new Position(0, 0), new Position(0, 1), new Position(1, 1)));
        Monstre monstre = new MonstreSoigneur(new MonstreResistant(new ChauveSouris(chemin)));
        monstre.avancer(monstre);
        assertTrue(monstre.getX() > 0);
        assertTrue(monstre.getY() > 0);
        for (int i = 0; i < 50; i++) monstre.avancer(monstre);
        assertTrue(monstre.estADestination());
        assertEquals(64, monstre.getX(), 0.0001);
        assertEquals(64, monstre.getY(), 0.0001);
    }

    // L image represente la couche exterieure sans desactiver les capacites interieures.
    @Test
    public void apparenceSuitLeDecorateurExterieur() {
        Monstre monstre = new MonstreSoigneur(new MonstreRapide(new MonstreResistant(new Zombie(chemin()))));
        assertEquals(VarianteMonstre.SOIGNEUR, monstre.getVariante());
        assertEquals(VarianteMonstre.RAPIDE, new MonstreRapide(monstre).getVariante());
    }

    // Verifie les effectifs et l utilisation effective du decorateur dans les cinq vagues.
    @Test
    public void vaguesConserventEffectifsEtMonstresResistants() {
        for (int numero = 1; numero <= 5; numero++) {
            Vague vague = new Vague();
            switch (numero) {
                case 1: vague.creeVague1(chemin()); break;
                case 2: vague.creeVague2(chemin(), chemin()); break;
                case 3: vague.creeVague3(chemin(), chemin()); break;
                case 4: vague.creeVague4(chemin(), chemin(), chemin()); break;
                default: vague.creeVague5(chemin(), chemin(), chemin()); break;
            }
            ObservableList<Monstre> monstres = FXCollections.observableArrayList();
            for (int tick = 0; tick < 4000; tick++) vague.mettreAJourVague(tick, monstres);
            int[] effectifs = {5, 20, 45, 61, 81};
            int[] resistants = {1, 2, 3, 5, 8};
            assertEquals(effectifs[numero - 1], monstres.size());
            assertEquals(resistants[numero - 1], monstres.stream()
                    .filter(m -> m.getVariante() == VarianteMonstre.RESISTANT).count());
            assertTrue(vague.tousLesMonstresEnvoyes());
        }
    }
    // Verifie la separation entre le contrat, l etat et les enveloppes.
    @Test
    public void contratInterfaceEtEtatSepareSansConstructeurVide() {
        assertTrue(Monstre.class.isInterface());
        assertTrue(java.lang.reflect.Modifier.isAbstract(MonstreBase.class.getModifiers()));
        assertEquals(Object.class, MonstreDecorateur.class.getSuperclass());
        assertTrue(Monstre.class.isAssignableFrom(MonstreDecorateur.class));
        assertTrue(Monstre.class.isAssignableFrom(MonstreBase.class));
        assertEquals(0, Monstre.class.getDeclaredConstructors().length);
        assertTrue(java.util.Arrays.stream(MonstreBase.class.getDeclaredConstructors())
                .noneMatch(c -> c.getParameterCount() == 0));
        for (Class<?> espece : List.of(Zombie.class, Araignee.class, Squelette.class,
                Pillager.class, ChauveSouris.class, Boss.class)) {
            assertEquals(MonstreBase.class, espece.getSuperclass());
        }
    }
}
