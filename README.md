# TextDraw


Aller dans le dossier source
Taper make
Puis aller dans dossier "explication" pour lire le mode d'emploi

1 Introduction
1.1 Objet
Ce document décrit ici les aspects fonctionnels du programme TextDraw réalisé
dans le cadre du projet informatique de la licence Informatique de l'Université
Paris 7 Diderot.
1.2 Vue d'ensemble
TextDraw est un éditeur textuel d'images. Il interprète des instructions placées
dans un fichier texte que l'on place en argument lors de l'exécution du logiciel
dans un terminal de commande.
Les fonctionnalités offertes sont la création de polygones et d'ellipses en courbe
de Bézier auxquelles nous pouvons appliquer des transformations géométriques
telles que :
- la translation
- la symétrie
- la rotation
- l'homothétie
2 Environnement
Ce programme a été développé à partir du langage de programmation JAVA.
Il s'inspire du modèle Jflex, un analyseur syntaxique, avec un système de jetons,
un Parser et un Lexer.
Les instructions sont interprétées afin de créer un image au format SVG ou PPM.
L'image est alors enregistrée directement dans le fichier d’exécution et peut être
affichée par logiciel tiers.
