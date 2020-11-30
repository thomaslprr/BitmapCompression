# BitmapCompression
Ce module permet de compresser une image PNG carrée avec différentes méthodes toutes basées sur les Quadtrees.
## Quadtree
Un quadtree est un arbre spécial qui définit chaque nœud comme ayant quatre enfants. Ils sont très utiles pour subdiviser un espace 2D en le fractionnant récursivement en quatre quadrants. [Plus d'informations ici](https://fr.wikipedia.org/wiki/Quadtree)

<p align="center">
  <img src="https://upload.wikimedia.org/wikipedia/commons/a/a0/Quad_tree_bitmap.svg" width="550" alt="Exemple de Quadtree">
<p align="center"><b>Exemple de Quadtree</b></p>
</p>

## Idée générale de la compression

<p align="center">
  <img src="https://upload.wikimedia.org/wikipedia/commons/d/d7/Quadtree_compression_of_an_image.gif" width="550" alt="Compression Quadtree d'une image étape par étape">
<p align="center"><b>Compression Quadtree d'une image étape par étape</b></p>
</p>

Le module utilise deux types de compressions différentes. 

### Compression delta

On peut définir une dégradation maximale autorisée sous la forme d’un entier <b>Delta</b> compris entre <b> [0:255] </b>
Les feuilles F1, F2, F3, F4 d’un même noeud N sont supprimées ssi l'écart colorimétrique du noeud père est inférieur ou égal à delta.

### Compression phi

On peut également définir la compression en fonction d’un poids maximum souhaité, ce qui peut se traduire par un entier <b>phi</b> > 0 représentant le nombre maximum de feuilles autorisées dans l’arbre.
Les feuilles sont alors élaguées par ordre croissant de l'écart colorimétrique.

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
### Interface graphique
