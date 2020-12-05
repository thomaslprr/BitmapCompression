# BitmapCompression
Ce module permet de compresser une image PNG carrée avec différentes méthodes toutes basées sur les Quadtrees.

## Utilisation du module

Un fichier ".jar" est intégré dans le projet. Il est possible de le lancer avec différents paramètres selon les besoins de l'utilisateur.

### Exécution Simple
L'exécution simple consiste à se placer dans le répertoire contenant le fichier ".jar" et taper la ligne de commande suivante : 

```java -jar BitmapCompression.jar <sourceDuFichierPNGACompresser> <valeurCompressionDelta> <valeurCompressionPhi> ```

<b>sourceDuFichierPNGACompresser : </b> Adresse du fichier PNG depuis la localisation du fichier BitmapCompression.jar OU adresse du fichier complète depuis la racine de l'ordinateur.

<b>valeurCompressionDelta : </b> Elle doit être comprise entre 0 et 255 

<b>valeurCompressionPhi : </b> Elle doit être plus grande que 0

#### Exemple
```java -jar BitmapCompression.jar images/512-books.png 160 23```

<b> A noter : </b> Pour éviter tout problème, un dossier "images" est déjà présent. Le plus simple est donc de mettre les fichiers que l'on souhaite compresser dedans et ainsi ```<sourceDuFichierPNGACompresser>``` aura pour valeur : ```images/<nomDeVotreFichier>.png``` 

### Menu textuel

Le menu textuel est un menu dans la console qui vous proposera les différentes options possibles. Il vous guidera vers toutes les fonctionnalités possibles du module.
Pour se faire, placez-vous dans le dossier contenant le ```.jar``` et tapez la ligne de commande suivante : 
```java -jar BitmapCompression.jar```

### Interface graphique

L'interface graphique est une Java application intuitive qui permet de bénéficier de toutes les fonctionnalités du module par le biais d'un rendu graphique, clair et facile d'usage. Il s'agit en quelque sorte du <i>Menu textuel</i> en plus développé.
Pour utilisé le module via une interface graphique, placez-vous dans le dossier contenant le ```.jar``` et tapez la ligne de commande suivante :

```java -jar BitmapCompression.jar -a```

