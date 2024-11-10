# Jeu 2048 - README

Bienvenue dans mon implémentation du jeu 2048 en Java ! Ce projet propose une version personnelle, quoique fidèle à l'original, du célèbre jeu de puzzle, où l'objectif est de combiner des tuiles numérotées pour finalement obtenir une tuile avec la valeur de l'objectif.

## Table des Matières

1. [Description](#description)
2. [Fonctionnalités](#fonctionnalités)
3. [Règles du Jeu](#règles-du-jeu)
4. [Installation et Utilisation](#installation-et-utilisation)
5. [Utilisation du Fichier .jar](#utilisation-du-fichier-jar)
6. [Tests](#tests)
7. [Structure du Projet](#structure-du-projet)

## Description

Ce projet est une version complète du jeu 2048, écrite en Java, avec des fonctionnalités telles que le glissement et la fusion de tuiles, la gestion des scores, et la détection automatique des conditions de victoire ou de défaite. Le projet a été réalisé dans le cadre d'un devoir académique.

## Fonctionnalités

- **Plateau de Jeu Dynamique** : Taille du plateau et objectif configurables.
- **Gestion des Mouvements** : Déplacements des tuiles dans quatre directions avec fusion des tuiles de valeurs identiques.
- **Placement Aléatoire des Tuiles** : Tuiles apparaissant de manière aléatoire après chaque mouvement.
- **Système de Score** : Calcul du score à chaque fusion de tuiles, avec sauvegarde du score maximal.
- **Détection des États du Jeu** : Détection automatique de la victoire (atteinte de la tuile de valeur 'objectif') ou de la défaite (aucun mouvement possible).

## Règles du Jeu

- Le plateau commence avec deux tuiles de valeur 2.
- Vous pouvez glisser les tuiles dans quatre directions : gauche, droite, haut, ou bas à l'aide des boutons ou des touches Z,Q,S,D
- Lorsque deux tuiles de même valeur se rencontrent, elles fusionnent pour former une tuile de valeur double.
- Après chaque mouvement, une nouvelle tuile (2 ou 4) apparaît dans un emplacement vide.
- Le jeu se termine lorsque vous atteignez une tuile de la valeur de l'objectif (victoire) ou lorsqu'il n'y a plus de mouvements possibles (défaite).

## Installation et Utilisation

Pour installer et utiliser le projet, assurez-vous d'avoir Java 21 et JavaFX 21 LTS correctement installés.

### Utilisation avec un IDE

1. **Configurer JavaFX dans votre IDE** :
   - **IntelliJ IDEA** :
     - Allez dans `File > Project Structure > Libraries`.
     - Cliquez sur `+` et ajoutez le chemin vers les fichiers `.jar` de JavaFX (situés dans `javafx-sdk-21/lib`).
     - Configurez les options de VM dans les paramètres de la configuration de lancement :

       ```
       --module-path /chemin/vers/javafx/lib --add-modules=javafx.controls,javafx.fxml
       ```

### Utilisation en Ligne de Commande

#### Prérequis

1. **Java** : Vérifiez que Java est correctement installé :
   ```bash
   java -version
   ```
2. **JavaFX** : Assurez-vous d'avoir téléchargé et extrait JavaFX 21 LTS.

#### Compilation

Ouvrez un terminal (Windows : cmd ou PowerShell, Mac/Linux : Terminal).

1. Naviguez dans le dossier src de votre projet :
    ```bash
    cd chemin/vers/src
    ```

2. Compilez les fichiers .java en ajoutant le module JavaFX au module-path :
    ```bash
    javac --module-path /chemin/vers/javafx/lib --add-modules=javafx.controls,javafx.fxml -d ../bin eu/telecomnancy/*.java
    ```
        Remplacez /chemin/vers/javafx/lib par le chemin réel vers le dossier lib de JavaFX.
        -d ../bin spécifie que les fichiers compilés doivent être placés dans le dossier bin.

#### Exécution

Depuis le même terminal, exécutez le jeu en ajoutant JavaFX au module-path :
```bash
java --module-path /chemin/vers/javafx/lib --add-modules=javafx.controls,javafx.fxml -cp ../bin eu.telecomnancy.Main
```

### Instructions Spécifiques pour Windows

1. Télécharger Java et JavaFX :
    Assurez-vous que Java est bien installé et que JAVA_HOME est configuré dans les variables d'environnement.
    Téléchargez JavaFX 21 LTS, extrayez les fichiers, et notez le chemin.

2. Compiler et Exécuter :
- PowerShell :
    ```bash
        javac --module-path "C:\chemin\vers\javafx\lib" --add-modules=javafx.controls,javafx.fxml -d bin src\eu\telecomnancy\*.java
        java --module-path "C:\chemin\vers\javafx\lib" --add-modules=javafx.controls,javafx.fxml -cp bin eu.telecomnancy.Main
    ```
- CMD : Utilisez des guillemets autour des chemins si ceux-ci contiennent des espaces.

### Instructions Spécifiques pour Mac

1. Installer Java et JavaFX :
    - Installez Java avec Homebrew :
    ```bash
    brew install openjdk
    ```
    - Téléchargez JavaFX, extrayez-le, et notez le chemin.

2. Configurer le Chemin JavaFX :

    - Ajoutez les arguments de VM lors de l'exécution :
    ```bash
        java --module-path /Users/chemin/vers/javafx/lib --add-modules=javafx.controls,javafx.fxml -cp bin eu.telecomnancy.Main
    ```

### Instructions Spécifiques pour Linux

1. Installer Java :
    Installez Java avec votre gestionnaire de paquets (ex. : sudo apt install openjdk-21-jdk).
2. Compiler et Exécuter :
    Suivez les mêmes instructions que pour Mac, en ajustant le chemin de JavaFX.


## Utilisation du Fichier .jar

    Pour lancer le .jar avec JavaFX :
    ```bash
        java --module-path /chemin/vers/javafx/lib --add-modules=javafx.controls,javafx.fxml -jar jeu2048.jar
    ```
    Distribution : Le .jar peut être partagé et exécuté sur n'importe quelle machine disposant de Java et de JavaFX configurés.

## Tests

Ce projet inclut une suite de tests unitaires pour vérifier le bon fonctionnement de la logique du jeu. Les tests utilisent JUnit 5.
Exécuter les Tests

    - Dans l'IDE : Exécutez tous les tests avec JUnit 5.
    - En Ligne de Commande : Utilisez Maven ou Gradle pour lancer les tests.

## Structure du Projet

src/
│
├── eu/telecomnancy/
│   ├── Jeu.java                # Logique principale du jeu
│   ├── Observateur.java        # Interface du patron observateur
│   ├── Main.java               # Point d'entrée du jeu
│   ├── tests/                  # Tests JUnit pour la logique du jeu
│   └── VueXXX.java             # Les différentes vues implémentant le patron Observateur
│
└── README.md                   # Documentation du projet
