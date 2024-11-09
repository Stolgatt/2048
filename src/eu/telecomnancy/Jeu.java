package eu.telecomnancy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jeu {
    private int[][] cases;
    private int objectif;
    private final ArrayList<Observateur> obs = new ArrayList<Observateur>();
    private int nbGagnees = 0;
    private int nbJouees = 0;
    private Boolean debutee = false;
    private Boolean fourCreated = false;
    private Boolean victoire = false;
    private Boolean defaite = false;
    private Boolean changeTaille = false;
    private int maxScore;
    private int score;

    public Jeu(int taille, int objectif) {
        this.cases = new int[taille][taille];
        this.objectif = objectif;
        initializePlateau();
    }

    public void initializePlateau() {
        int taille = this.getTaille();
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                cases[i][j] = 0;
            }
        }
        nbJouees = nbGagnees = score = maxScore = 0;
        debutee = fourCreated = victoire = defaite = false;
        notifierObservateur();
    }

    public void nouveau(){
        int maxScoreLocal = maxScore;
        int nbJoueesLocal = nbJouees;
        int nbGagnesLocal = nbGagnees;
        initializePlateau();
        placerCaseAleatoire();
        placerCaseAleatoire();
        this.nbJouees = nbJoueesLocal+1;
        this.nbGagnees = nbGagnesLocal;
        debutee = true;
        maxScore = maxScoreLocal;
        notifierObservateur();
    }

    public void placerCaseAleatoire() {
        List<int[]> casesVides = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                if (cases[i][j] == 0) {
                    casesVides.add(new int[]{i, j});
                }
            }
        }
        if (!casesVides.isEmpty()) {
            int[] position = casesVides.get(random.nextInt(casesVides.size()));
            int value = (!fourCreated || random.nextDouble() < 0.8) ? 2 : 4;
            cases[position[0]][position[1]] = value;
        }
    }

    public void deplacer(String direction) {
        boolean moved = switch (direction) {
            case "gauche" -> deplacerGauche();
            case "droite" -> deplacerDroite();
            case "haut" -> deplacerHaut();
            case "bas" -> deplacerBas();
            default -> false;
        };
        if (moved) { // Ajouter une nouvelle case si le plateau a changé
            placerCaseAleatoire();
            placerCaseAleatoire();
            verifierEtatJeu();
            notifierObservateur();
        }
    }
    public boolean deplacerGauche() {
        boolean moved = false;
        for (int[] ligne : cases) {
            moved |= deplacerEtFusionnerLigne(ligne);
        }
        return moved;
    }
    public boolean deplacerDroite() {
        boolean moved = false;
        for (int i = 0; i < cases.length; i++) {
            int[] ligne = inverser(cases[i]);
            moved |= deplacerEtFusionnerLigne(ligne);
            cases[i] = inverser(ligne);
        }
        return moved;
    }
    public boolean deplacerHaut() {
        boolean moved = false;
        for (int j = 0; j < cases[0].length; j++) {
            int[] colonne = getColonne(j);
            moved |= deplacerEtFusionnerLigne(colonne);
            setColonne(j, colonne);
        }
        return moved;
    }
    public boolean deplacerBas() {
        boolean moved = false;
        for (int j = 0; j < cases[0].length; j++) {
            int[] colonne = inverser(getColonne(j));
            moved |= deplacerEtFusionnerLigne(colonne);
            setColonne(j, inverser(colonne));
        }
        return moved;
    }

    public boolean deplacerEtFusionnerLigne(int[] ligne) {
        boolean moved = false;

        // Étape 1 : Décaler toutes les cases vers le début du tableau
        int[] nouvelleLigne = new int[ligne.length];
        int pos = 0;
        for (int valeur : ligne) {
            if (valeur != 0) {
                nouvelleLigne[pos++] = valeur;
            }
        }

        // Étape 2 : Fusionner les cases adjacentes égales
        for (int k = 0; k < nouvelleLigne.length - 1; k++) {
            if (nouvelleLigne[k] != 0 && nouvelleLigne[k] == nouvelleLigne[k + 1]) {
                if (nouvelleLigne[k] == 2){
                    fourCreated = true;
                }
                nouvelleLigne[k] *= 2; // Fusion
                nouvelleLigne[k + 1] = 0; // Supprimer la case fusionnée
                moved = true;
            }
        }

        // Étape 3 : Décaler de nouveau après fusion
        pos = 0;
        int[] ligneFinale = new int[ligne.length];
        for (int valeur : nouvelleLigne) {
            if (valeur != 0) {
                ligneFinale[pos++] = valeur;
            }
        }

        // Copier le résultat dans la ligne initiale
        for (int k = 0; k < ligne.length; k++) {
            if (ligne[k] != ligneFinale[k]) {
                moved = true;
            }
            ligne[k] = ligneFinale[k];
        }

        return moved;
    }

    public int[] getColonne(int index) {
        int[] colonne = new int[cases.length];
        for (int i = 0; i < cases.length; i++) {
            colonne[i] = cases[i][index];
        }
        return colonne;
    }

    public void setColonne(int index, int[] colonne) {
        for (int i = 0; i < cases.length; i++) {
            cases[i][index] = colonne[i];
        }
    }

    public int[] inverser(int[] ligne) {
        int[] inverse = new int[ligne.length];
        for (int i = 0; i < ligne.length; i++) {
            inverse[i] = ligne[ligne.length - 1 - i];
        }
        return inverse;
    }

    public boolean verifierVictoire() {
        for (int[] aCase : cases) {
            for (int i : aCase) {
                if (i == objectif) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean verifierDefaite() {
        return !(existeCaseVide() || peutFusionner());
    }

    public boolean existeCaseVide() {
        for (int[] aCase : cases) {
            for (int i : aCase) {
                if (i == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean peutFusionner() {
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                int valeur = cases[i][j];

                if ((i < cases.length - 1 && cases[i + 1][j] == valeur) || // Bas
                        (j < cases[i].length - 1 && cases[i][j + 1] == valeur) || // Droite
                        (i > 0 && cases[i - 1][j] == valeur) || // Haut
                        (j > 0 && cases[i][j - 1] == valeur)) { // Gauche
                    return true;
                }
            }
        }
        return false;
    }

    public void verifierEtatJeu() {
        if (verifierVictoire()) {
            debutee = false;
            victoire = true;
            nbGagnees++;
        } else if (verifierDefaite()) {
            defaite = true;
            debutee = false;
        }
        score += calculerScore();
        maxScore = Math.max(maxScore, score);
    }
    public int calculerScore() {
        int totalScore = 0;
        for (int[] row : cases) {
            for (int cell : row) totalScore += cell;
        }
        return totalScore;
    }

    public int afficherEtatJeu(){
        if (victoire) return 0; //0 = victoire
        else if (defaite) return 1; //1 = défaite
        else return -1; //-1 = pas d'affichage à faire
    }

    public int getCase(int i, int j){
        return cases[i][j];
    }

    public int NbGagnees(){
        return nbGagnees;
    }

    public int NbJouees(){
        return this.nbJouees;
    }

    public void setTaille(int taille){
        this.cases = new int[taille][taille];
        this.changeTaille = true;
        initializePlateau();
    }
    public void setObjectif(int objectif){
        this.objectif = objectif;
        notifierObservateur();
    }
    public int getObjectif(){
        return this.objectif;
    }
    public int getTaille(){
        return this.cases.length;
    }
    public Boolean getDebutee(){
        return this.debutee;
    }
    public Boolean getChangeTaille(){
        return this.changeTaille;
    }
    public void resetChangeTaille(){
        this.changeTaille = false;
    }
    public int getScore(){
        return this.score;
    }
    public int getMaxScore(){
        return this.maxScore;
    }

    public void ajouterObservateur(Observateur obs){
        this.obs.add(obs);
    }
    public void notifierObservateur(){
        for (Observateur obs : this.obs) {
            obs.reagir();
        }
    }
    public void setCase(int i, int j, int valeur) {
        cases[i][j] = valeur;
    }

}


