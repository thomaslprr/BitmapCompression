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
