Jeu 2048 - README

Bienvenue dans mon implémentation du jeu 2048 en Java ! Ce projet propose une version personnelle, quoique fidèle à l'original, du célèbre jeu de puzzle, où l'objectif est de combiner des tuiles numérotées pour finalement obtenir une tuile avec la valeur de l'objectif.
Table des Matières

    Description
    Fonctionnalités
    Installation
    Utilisation
    Règles du Jeu
    Compilation et Exécution
    Utilisation du Fichier .jar
    Tests
    Structure du Projet
    Contribuer
    Licence

Description

Ce projet est une version complète du jeu 2048, écrite en Java, avec des fonctionnalités telles que le glissement et la fusion de tuiles, la gestion des scores, et la détection automatique des conditions de victoire ou de défaite. Le projet a été réalisé dans le cadre d'un devoir académique.
Fonctionnalités

    Plateau de Jeu Dynamique : Taille du plateau et objectif configurables.
    Gestion des Mouvements : Déplacements des tuiles dans quatre directions avec fusion des tuiles de valeurs identiques.
    Placement Aléatoire des Tuiles : Tuiles apparaissant de manière aléatoire après chaque mouvement.
    Système de Score : Calcul du score à chaque fusion de tuiles, avec sauvegarde du score maximal.
    Détection des États du Jeu : Détection automatique de la victoire (atteinte de la tuile de valeur 'objectif') ou de la défaite (aucun mouvement possible).

Compilation et Exécution

Vous pouvez utiliser un IDE tel qu'IntelliJ IDEA, Eclipse ou NetBeans pour compiler et exécuter le projet facilement. Si vous préférez travailler en ligne de commande, voici comment procéder, avec des instructions spécifiques pour Windows, Mac et Linux.
Utilisation avec un IDE

    Configurer JavaFX dans votre IDE :
        IntelliJ IDEA :
            Allez dans File > Project Structure > Libraries.
            Cliquez sur + et ajoutez le chemin vers les fichiers .jar de JavaFX (situés dans javafx-sdk-21/lib).
            Configurez les options de VM dans les paramètres de la configuration de lancement :

    --module-path /chemin/vers/javafx/lib --add-modules=javafx.controls,javafx.fxml

Utilisation en Ligne de Commande
Prérequis

    Java : Vérifiez que Java est correctement installé :

    java -version

    JavaFX : Assurez-vous d'avoir téléchargé et extrait JavaFX 21 LTS.

Compilation

    Ouvrez un terminal (Windows : cmd ou PowerShell, Mac/Linux : Terminal).
    Naviguez dans le dossier src de votre projet :

cd chemin/vers/src

Compilez les fichiers .java en ajoutant le module JavaFX au module-path :

    javac --module-path /chemin/vers/javafx/lib --add-modules=javafx.controls,javafx.fxml -d ../bin eu/telecomnancy/*.java

        Remplacez /chemin/vers/javafx/lib par le chemin réel vers le dossier lib de JavaFX.
        -d ../bin spécifie que les fichiers compilés doivent être placés dans le dossier bin.

Exécution

    Depuis le même terminal, exécutez le jeu en ajoutant JavaFX au module-path :

    java --module-path /chemin/vers/javafx/lib --add-modules=javafx.controls,javafx.fxml -cp ../bin eu.telecomnancy.Main

Instructions Spécifiques pour Windows

    Télécharger Java et JavaFX :
        Assurez-vous que Java est bien installé et que JAVA_HOME est configuré dans les variables d'environnement.
        Téléchargez JavaFX 21 LTS, extrayez les fichiers, et notez le chemin.

    Compiler et Exécuter :
        PowerShell :

        javac --module-path "C:\chemin\vers\javafx\lib" --add-modules=javafx.controls,javafx.fxml -d bin src\eu\telecomnancy\*.java
        java --module-path "C:\chemin\vers\javafx\lib" --add-modules=javafx.controls,javafx.fxml -cp bin eu.telecomnancy.Main

        CMD : Utilisez des guillemets autour des chemins si ceux-ci contiennent des espaces.

Instructions Spécifiques pour Mac

    Installer Java et JavaFX :
        Installez Java avec Homebrew :

    brew install openjdk

    Téléchargez JavaFX, extrayez-le, et notez le chemin.

Configurer le Chemin JavaFX :

    Ajoutez les arguments de VM lors de l'exécution :

        java --module-path /Users/chemin/vers/javafx/lib --add-modules=javafx.controls,javafx.fxml -cp bin eu.telecomnancy.Main

Instructions pour Linux

    Installer Java :
        Installez Java avec votre gestionnaire de paquets (ex. : sudo apt install openjdk-21-jdk).
    Compiler et Exécuter :
        Suivez les mêmes instructions que pour Mac, en ajustant le chemin de JavaFX.

Règles du Jeu

    Le plateau commence avec deux tuiles de valeur 2 (ou parfois 4).
    Vous pouvez glisser les tuiles dans quatre directions : gauche, droite, haut, ou bas.
    Lorsque deux tuiles de même valeur se rencontrent, elles fusionnent pour former une tuile de valeur double.
    Après chaque mouvement, une nouvelle tuile (2 ou 4) apparaît dans un emplacement vide.
    Le jeu se termine lorsque vous atteignez une tuile 2048 (victoire) ou lorsqu'il n'y a plus de mouvements possibles (défaite).

Utilisation du Fichier .jar

    Pour lancer le .jar avec JavaFX :

        java --module-path /chemin/vers/javafx/lib --add-modules=javafx.controls,javafx.fxml -jar jeu2048.jar

    Distribution : Le .jar peut être partagé et exécuté sur n'importe quelle machine disposant de Java et de JavaFX configurés.

Tests

Ce projet inclut une suite de tests unitaires pour vérifier le bon fonctionnement de la logique du jeu. Les tests utilisent JUnit 5.
Exécuter les Tests

    Dans l'IDE : Exécutez tous les tests avec JUnit 5.
    En Ligne de Commande : Utilisez Maven ou Gradle pour lancer les tests.

Structure du Projet

src/
│
├── eu/telecomnancy/
│   ├── Jeu.java                # Logique principale du jeu
│   ├── Observateur.java        # Interface du patron observateur
│   ├── Main.java               # Point d'entrée du jeu
│   ├── tests/                  # Tests JUnit pour la logique du jeu
|   └── VueXXX.java             # Les différentes vue implémentant le patron Observateur
│
└── README.md                   # Documentation du projet
