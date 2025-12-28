@echo off
echo Compilation du projet...

REM Crée le dossier bin si il n existe pas
if not exist "bin" mkdir bin

REM Compile le code. On pointe vers le fichier Main, Java trouvera les autres fichiers grâce à -sourcepath
REM Remplace "src/com/tonpackage/Main.java" par le bon chemin vers ton fichier Main
javac -d bin -sourcepath src src/eu/telecomnancy/Main.java

if %errorlevel% neq 0 (
    echo Erreur lors de la compilation !
    pause
    exit /b
)

echo Lancement de l'application...

REM Lance l'application en activant les modules JavaFX
java -cp bin --add-modules javafx.controls,javafx.fxml eu.telecomnancy.Main

pause
