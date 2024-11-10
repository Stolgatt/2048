package eu.telecomnancy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JeuTest {

    private Jeu jeu;

    @BeforeEach
    public void setUp() {
        // Initialisation d'un nouveau jeu avec une grille de 4x4 et un objectif de 2048
        jeu = new Jeu(4, 2048);
    }

    @Test
    public void testInitialisationPlateau() {
        // Vérifie que toutes les cases sont initialisées à 0
        for (int i = 0; i < jeu.getTaille(); i++) {
            for (int j = 0; j < jeu.getTaille(); j++) {
                assertEquals(0, jeu.getCase(i, j), "Toutes les cases doivent être vides au début");
            }
        }
        assertEquals(0, jeu.getScore(), "Le score initial doit être 0");
    }

    @Test
    public void testNouveauJeu() {
        jeu.nouveau();
        // Vérifie que le nombre de parties jouées est 1
        assertEquals(1, jeu.NbJouees(), "Le nombre de parties jouées doit être 1 après un nouveau jeu");
        // Vérifie que la partie a commencé
        assertTrue(jeu.getDebutee(), "La partie doit être en cours après un nouveau jeu");

        // Vérifie qu'il y a deux cases non vides (les cases placées au début)
        int casesNonVides = 0;
        for (int i = 0; i < jeu.getTaille(); i++) {
            for (int j = 0; j < jeu.getTaille(); j++) {
                if (jeu.getCase(i, j) != 0) {
                    casesNonVides++;
                }
            }
        }
        assertEquals(2, casesNonVides, "Il doit y avoir exactement 2 cases non vides après un nouveau jeu");
    }

    @Test
    public void testPlacerCaseAleatoire() {
        jeu.placerCaseAleatoire();
        int casesNonVides = 0;
        for (int i = 0; i < jeu.getTaille(); i++) {
            for (int j = 0; j < jeu.getTaille(); j++) {
                if (jeu.getCase(i, j) != 0) {
                    casesNonVides++;
                }
            }
        }
        assertTrue(casesNonVides > 0, "Il doit y avoir au moins une case non vide après avoir placé une case aléatoire");
    }

    @Test
    public void testDeplacerGauche() {
        // Configuration d'un état initial avec des valeurs connues
        jeu.setCase(0, 0, 2);
        jeu.setCase(0, 1, 2);
        jeu.setCase(0, 2, 4);
        jeu.setCase(0, 3, 0);

        // Effectuer le déplacement vers la gauche
        boolean moved = jeu.deplacerGauche();

        // Vérifier que le déplacement a été effectué
        assertTrue(moved, "Le déplacement vers la gauche doit être effectué");
        assertEquals(4, jeu.getCase(0, 0), "La première case doit contenir 4 après fusion");
        assertEquals(4, jeu.getCase(0, 1), "La deuxième case doit contenir 4");
        assertEquals(0, jeu.getCase(0, 2), "La troisième case doit être vide");
        assertEquals(0, jeu.getCase(0, 3), "La quatrième case doit être vide");
    }

    @Test
    public void testDeplacerDroite() {
        // Configuration d'un état initial avec des valeurs connues
        jeu.setCase(0, 0, 0);
        jeu.setCase(0, 1, 2);
        jeu.setCase(0, 2, 2);
        jeu.setCase(0, 3, 4);

        // Effectuer le déplacement vers la droite
        boolean moved = jeu.deplacerDroite();

        // Vérifier que le déplacement a été effectué
        assertTrue(moved, "Le déplacement vers la droite doit être effectué");
        assertEquals(0, jeu.getCase(0, 0), "La première case doit être vide");
        assertEquals(0, jeu.getCase(0, 1), "La deuxième case doit être vide");
        assertEquals(4, jeu.getCase(0, 2), "La troisième case doit contenir 4 après fusion");
        assertEquals(4, jeu.getCase(0, 3), "La quatrième case doit contenir 4");
    }

    @Test
    public void testDeplacerHaut() {
        // Configuration d'un état initial avec des valeurs connues
        jeu.setCase(0, 0, 2);
        jeu.setCase(1, 0, 2);
        jeu.setCase(2, 0, 4);
        jeu.setCase(3, 0, 0);

        // Effectuer le déplacement vers le haut
        boolean moved = jeu.deplacerHaut();

        // Vérifier que le déplacement a été effectué
        assertTrue(moved, "Le déplacement vers le haut doit être effectué");
        assertEquals(4, jeu.getCase(0, 0), "La première case doit contenir 4 après fusion");
        assertEquals(4, jeu.getCase(1, 0), "La deuxième case doit contenir 4");
        assertEquals(0, jeu.getCase(2, 0), "La troisième case doit être vide");
        assertEquals(0, jeu.getCase(3, 0), "La quatrième case doit être vide");
    }

    @Test
    public void testDeplacerBas() {
        // Configuration d'un état initial avec des valeurs connues
        jeu.setCase(0, 0, 0);
        jeu.setCase(1, 0, 2);
        jeu.setCase(2, 0, 2);
        jeu.setCase(3, 0, 4);

        // Effectuer le déplacement vers le bas
        boolean moved = jeu.deplacerBas();

        // Vérifier que le déplacement a été effectué
        assertTrue(moved, "Le déplacement vers le bas doit être effectué");
        assertEquals(0, jeu.getCase(0, 0), "La première case doit être vide");
        assertEquals(0, jeu.getCase(1, 0), "La deuxième case doit être vide");
        assertEquals(4, jeu.getCase(2, 0), "La troisième case doit contenir 4 après fusion");
        assertEquals(4, jeu.getCase(3, 0), "La quatrième case doit contenir 4");
    }

    @Test
    public void testPasDeMouvement() {
        // Configuration d'un état où aucun mouvement n'est possible
        jeu.setCase(0, 0, 2);
        jeu.setCase(0, 1, 4);
        jeu.setCase(0, 2, 8);
        jeu.setCase(0, 3, 16);

        // Effectuer le déplacement vers la gauche
        boolean moved = jeu.deplacerGauche();

        // Vérifier que le déplacement n'a pas été effectué
        assertFalse(moved, "Aucun déplacement ne doit être possible");
        assertEquals(2, jeu.getCase(0, 0), "La première case doit rester 2");
        assertEquals(4, jeu.getCase(0, 1), "La deuxième case doit rester 4");
        assertEquals(8, jeu.getCase(0, 2), "La troisième case doit rester 8");
        assertEquals(16, jeu.getCase(0, 3), "La quatrième case doit rester 16");
    }

    @Test
    public void testDeplacerEtFusionnerLigne_DeplacementSimple() {
        int[] ligne = {0, 2, 0, 4};
        assertTrue(jeu.deplacerEtFusionnerLigne(ligne));
        assertArrayEquals(new int[]{2, 4, 0, 0}, ligne);
    }

    @Test
    public void testDeplacerEtFusionnerLigne_Fusion() {
        int[] ligne = {2, 2, 0, 4};
        assertTrue(jeu.deplacerEtFusionnerLigne(ligne));
        assertArrayEquals(new int[]{4, 4, 0, 0}, ligne);
    }

    @Test
    public void testDeplacerEtFusionnerLigne_FusionMultiple() {
        int[] ligne = {2, 2, 4, 4};
        assertTrue(jeu.deplacerEtFusionnerLigne(ligne));
        assertArrayEquals(new int[]{4, 8, 0, 0}, ligne);
    }

    @Test
    public void testDeplacerEtFusionnerLigne_AucuneFusion() {
        int[] ligne = {2, 4, 8, 16};
        assertFalse(jeu.deplacerEtFusionnerLigne(ligne));
        assertArrayEquals(new int[]{2, 4, 8, 16}, ligne);
    }

    @Test
    public void testDeplacerEtFusionnerLigne_FusionUnique() {
        int[] ligne = {4, 0, 4, 0};
        assertTrue(jeu.deplacerEtFusionnerLigne(ligne));
        assertArrayEquals(new int[]{8, 0, 0, 0}, ligne);
    }

    // Test de la méthode verifierVictoire
    @Test
    public void testVerifierVictoire() {
        jeu.setCase(0, 0, jeu.getObjectif()); // Simule la case de victoire
        assertTrue(jeu.verifierVictoire());
    }

    // Test de la méthode verifierDefaite
    @Test
    public void testVerifierDefaite() {
        // Remplissez le plateau avec des valeurs non fusionnables
        jeu.setCase(0, 0, 2);
        jeu.setCase(0, 1, 4);
        jeu.setCase(0, 2, 8);
        jeu.setCase(0, 3, 16);
        jeu.setCase(1, 0, 32);
        jeu.setCase(1, 1, 64);
        jeu.setCase(1, 2, 128);
        jeu.setCase(1, 3, 256);
        jeu.setCase(2, 0, 512);
        jeu.setCase(2, 1, 1024);
        jeu.setCase(2, 2, 2048);
        jeu.setCase(2, 3, 4096);
        jeu.setCase(3, 0, 8192);
        jeu.setCase(3, 1, 16384);
        jeu.setCase(3, 2, 32768);
        jeu.setCase(3, 3, 65536);

        assertTrue(jeu.verifierDefaite());
    }

    @Test
    public void testVerifierDefaite_CasIntermediaire() {
        jeu.setCase(0, 0, 2);
        jeu.setCase(0, 1, 4);
        jeu.setCase(0, 2, 8);
        jeu.setCase(0, 3, 2); // Mouvement encore possible
        assertFalse(jeu.verifierDefaite());
    }

    // Test de la méthode peutFusionner
    @Test
    public void testPeutFusionner_PossibiliteDeFusion() {
        jeu.setCase(0, 0, 2);
        jeu.setCase(0, 1, 2); // Cas où une fusion est possible
        assertTrue(jeu.peutFusionner());
    }

    @Test
    public void testExisteCaseVide_CaseVidePresente() {
        jeu.setCase(0, 0, 0); // Simule une case vide
        assertTrue(jeu.existeCaseVide());
    }

    @Test
    public void testExisteCaseVide_PlateauPlein() {
        for (int i = 0; i < jeu.getTaille(); i++) {
            for (int j = 0; j < jeu.getTaille(); j++) {
                jeu.setCase(i, j, 2);
            }
        }
        assertFalse(jeu.existeCaseVide());
    }

    // Test des méthodes getColonne et setColonne
    @Test
    public void testGetColonne() {
        jeu.setCase(0, 1, 2);
        jeu.setCase(1, 1, 4);
        jeu.setCase(2, 1, 8);
        jeu.setCase(3, 1, 16);

        int[] colonne = jeu.getColonne(1);
        assertArrayEquals(new int[]{2, 4, 8, 16}, colonne);
    }

    @Test
    public void testSetColonne() {
        int[] colonne = {2, 4, 8, 16};
        jeu.setColonne(1, colonne);

        assertEquals(2, jeu.getCase(0, 1));
        assertEquals(4, jeu.getCase(1, 1));
        assertEquals(8, jeu.getCase(2, 1));
        assertEquals(16, jeu.getCase(3, 1));
    }

    // Test de la méthode inverser
    @Test
    public void testInverser() {
        int[] ligne = {1, 2, 3, 4};
        int[] inverse = jeu.inverser(ligne);
        assertArrayEquals(new int[]{4, 3, 2, 1}, inverse);
    }

    @Test
    public void testInverser_AvecZeros() {
        int[] ligne = {0, 1, 0, 2};
        int[] inverse = jeu.inverser(ligne);
        assertArrayEquals(new int[]{2, 0, 1, 0}, inverse);
    }

    @Test
    public void testVerifierEtatJeu_Victoire() {
        jeu.setCase(0, 0, jeu.getObjectif());
        jeu.verifierEtatJeu();
        assertTrue(!jeu.getDebutee() && jeu.afficherEtatJeu() == 0);
    }

    @Test
    public void testVerifierEtatJeu_Defaite() {
        // Créer un plateau complet sans possibilité de fusion
        jeu.setCase(0, 0, 2);
        jeu.setCase(0, 1, 4);
        jeu.setCase(0, 2, 8);
        jeu.setCase(0, 3, 16);
        jeu.setCase(1, 0, 32);
        jeu.setCase(1, 1, 64);
        jeu.setCase(1, 2, 128);
        jeu.setCase(1, 3, 256);
        jeu.setCase(2, 0, 512);
        jeu.setCase(2, 1, 1024);
        jeu.setCase(2, 2, 2);
        jeu.setCase(2, 3, 4);
        jeu.setCase(3, 0, 8);
        jeu.setCase(3, 1, 16);
        jeu.setCase(3, 2, 32);
        jeu.setCase(3, 3, 64);

        jeu.verifierEtatJeu();

        assertTrue(jeu.afficherEtatJeu() == 1);
    }

    @Test
    public void testVerifierEtatJeu_JeuEnCours() {
        jeu.setCase(0, 0, 2);
        jeu.setCase(0, 1, 4);
        jeu.setCase(0, 2, 8);
        jeu.setCase(0, 3, 0); // Mouvement encore possible
        jeu.verifierEtatJeu();
        assertTrue(jeu.afficherEtatJeu() == -1);
    }
}
