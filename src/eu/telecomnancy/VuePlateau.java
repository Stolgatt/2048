package eu.telecomnancy;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class VuePlateau extends StackPane implements Observateur {
    private final Jeu jeu;
    private int taille; // Taille du plateau
    private GridPane plateau;
    private final VBox overlayPane;

    private final double maxWidth = 1720; // Largeur max pour écran 1920x1080
    private final double maxHeight = 880; // Hauteur max pour écran 1920x1080

    private double sizeCase;

    public VuePlateau(Jeu jeu) {
        this.jeu = jeu;
        this.taille = jeu.getTaille();
        this.jeu.ajouterObservateur(this);

        plateau = createPlateau();

        overlayPane = createOverlayPane();
        overlayPane.setAlignment(Pos.CENTER);

        this.getChildren().addAll(plateau, overlayPane);
        overlayPane.setVisible(false);
    }

    private VBox createOverlayPane() {
        VBox overlay = new VBox();
        overlay.setAlignment(Pos.CENTER);
        overlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);"); // Fond noir semi-transparent
        overlay.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        Label messageLabel = new Label();
        messageLabel.setTextFill(Color.WHITE);
        messageLabel.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        messageLabel.setEffect(new DropShadow(3, Color.BLACK));

        overlay.getChildren().add(messageLabel);
        return overlay;
    }

    public void afficherMessageFin(String message) {
        Label messageLabel = (Label) overlayPane.getChildren().getFirst();
        messageLabel.setText(message);
        overlayPane.setVisible(true);
    }

    private GridPane createPlateau(){
        plateau = new GridPane();

        int hgap = 5; // Espace horizontal
        int vgap = 5; // Espace vertical

        plateau.setVgap(vgap);
        plateau.setHgap(hgap);

        // TAille de chaque case carré, selon le nombre de case et la taille max h et v calculé pour écran 1920x1080
        sizeCase = Math.min(((maxHeight) - (taille - 1) * vgap), ((maxWidth) - (taille - 1) * hgap)) / taille;

        // Initialiser la grille avec des cases vides
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                StackPane cell = createCell(0);
                plateau.add(cell, j, i);
            }
        }
        plateau.setStyle("-fx-background-color: #F5DEB3;");
        return plateau;
    }

    private StackPane createCell(int value) {
        // Créer un rectangle pour la case
        Rectangle rectangle = new Rectangle(sizeCase, sizeCase);
        rectangle.setFill(getColorForValue(value));
        rectangle.setStroke(Color.DARKGRAY);

        // Créer un texte pour afficher la valeur
        Text text = new Text(value == 0 ? "" : String.valueOf(value));
        text.setFill(Color.BLACK);
        text.setStyle("-fx-font-size: 24;");

        // Créer un StackPane pour empiler le rectangle et le texte
        StackPane cell = new StackPane();
        cell.getChildren().addAll(rectangle, text);
        StackPane.setAlignment(text, Pos.CENTER); // Centrer le texte dans la case

        return cell;
    }

    private Color getColorForValue(int value) {
        return switch (value) {
            case 0 -> Color.web("#B0B0B0"); // Case vide
            case 2 -> Color.web("#eee3da");
            case 4 -> Color.web("#ece0c8");
            case 8 -> Color.web("#f3b079");
            case 16 -> Color.web("#f49462");
            case 32 -> Color.web("#f57c5f");
            case 64 -> Color.web("#f75e3e");
            case 128 -> Color.web("#ebcd70");
            case 256 -> Color.web("#ebcb5e");
            case 512 -> Color.web("#ebc74f");
            case 1024 -> Color.web("#ebc340");
            case 2048 -> Color.web("#eac02e");
            case 4096 -> Color.web("#ee666d");
            case 8192 -> Color.web("#ed4c58");
            case 16384 -> Color.web("#e14338");
            case 32768 -> Color.web("#72b4d6");
            case 65536 -> Color.web("#5ca0df");
            case 131072 -> Color.web("#007bbe");
            default -> Color.BLACK; // Valeur non reconnue
        };
    }

    public void reagir(){
        if (jeu.getChangeTaille()){
            taille = jeu.getTaille();
            plateau = createPlateau();

            this.getChildren().clear();
            this.getChildren().addAll(plateau, overlayPane);
            jeu.resetChangeTaille();
        }
        for(int i = 0; i < taille; i++){
            for(int j = 0; j < taille; j++){
                StackPane cell = (StackPane) this.plateau.getChildren().get(i * taille + j);
                Rectangle rectangle = (Rectangle) cell.getChildren().get(0);
                Text text = (Text) cell.getChildren().get(1);
                int value = jeu.getCase(i,j);
                rectangle.setFill(getColorForValue(value));
                text.setText(value == 0 ? "" : String.valueOf(value));
            }
        }
        if (!jeu.getDebutee()){
            int valeurResultat = jeu.afficherEtatJeu();
            if (valeurResultat == 0) afficherMessageFin("Victoire !");
            else if (valeurResultat == 1) afficherMessageFin("Défaite !");
        }
        else{
            overlayPane.setVisible(false);
        }
    }
}
