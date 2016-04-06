/**
 * Cette classe permet la lecture d'un fichier et la création de l'image.
 * @author ELBEZ Samuel & JACQUETTE Pierrick & PERRACHON Quentin
 */
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;

public class Main {

	public static String texte;
	public static String texteSVG;
	public static String textePPM;
	public static int hauteur;
	public static int largeur;
	public static String nomFichier;
	public static Pixel[][] tab;
	public static Pixel[][] forme;

	/**
	 * Cette methode lis le fichier d'entree, l'analyse et cree l'image qui
	 * correspond
	 * 
	 * @param args
	 *            [0] elle prend en parametre le nom du fichier.
	 * 
	 * @see Main#lire(String)
	 * @see Main#ecrire()
	 * @see Main#creerFichier(String)
	 */
	public static void main(String[] args) {
		Parser.initDefinitions();

		try {
			if(args.length==0){
				System.err.println("Il faut indiquer en argument le nom du fichier de commande que vous voulez dessiner !");
				return;
			}
			lire(args[0]);
			ecrire();
			creerFichier(nomFichier);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/**
	 * Cette methode lis le fichier d'entree et ajoute chaque instruction dans
	 * une List
	 * 
	 * @param nom
	 *            chaine de caractere qui represente le nom du fichier.
	 * 
	 * @see Main#main(String[])
	 */
	public static void lire(String nom) {

		FileInputStream f;
		texte = "";
		try {
			f = new FileInputStream(nom);
			int c;
			while ((c = f.read()) != -1) {
				if (c != '\n') {
					texte += (char) c;
				}
			}
			f.close();
		} catch (IOException exception) {
			exception.getStackTrace();
			System.out.println("Erreur lors de la lecture : "
					+ exception.getMessage());
		}

	}

	/**
	 * Cette methode initialise un tableau que si l'image est au format ppm, ce
	 * tableau represente l'image final
	 * 
	 * @see Main#ecrireTextePPM(String)
	 */
	public static void initialisationTab() {
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[i].length; j++) {
				tab[i][j] = new Pixel(255, 255, 255);
			}
		}

	}

	/**
	 * Cette methode ecris le fichier de sortie
	 * 
	 * @param nomFichierSortie
	 *            nom du fichier de sortie .
	 * 
	 * @see Main#main(String[])
	 * 
	 */
	public static void creerFichier(String nomFichierSortie) {
		String texteFinal = "";
		if (nomFichierSortie.endsWith(".svg")) {
			texteFinal = texteSVG;
			PrintWriter ecri;
			try {
				ecri = new PrintWriter(new FileWriter(nomFichierSortie));
				ecri.print(texteFinal);
				ecri.flush();
				ecri.close();
			} catch (NullPointerException a) {
				a.getStackTrace();
				System.out.println("Erreur : pointeur null");
			} catch (IOException a) {
				a.getStackTrace();
				System.out.println("Probleme d'IO");
			}
		} else if (nomFichierSortie.endsWith(".ppm")) {
			texteFinal = textePPM;
		} else {
			System.out.println("Le type d'image n'est pas reconnu");
		}
		PrintWriter ecri;

	}

	/**
	 * Cette methode cree l'en tete du fichier PPM
	 * 
	 * @see Main#ecrireTextePPM(String)
	 */
	public static void enTeteDeFichierPPM() {
		textePPM = "P3";
	}

	/**
	 * Cette methode cree tous les elements pour creer l'image final
	 * 
	 * @param titre
	 *            elle prend en parametre le nom du fichier.
	 * @see Main#rechercheTailleImage(LinkedList)
	 * @see Main#enTeteDeFichierPPM()
	 * @see Main#initialisationTab()
	 * @see FormePrimitive#toPPM()
	 * @see Main#PPM()
	 */
	public static void ecrireTextePPM(String titre) {
		enTeteDeFichierPPM();
		rechercheTailleImage(Parser.finalList);
		int tmp = largeur;
		largeur = hauteur;
		hauteur = tmp;
		tab = new Pixel[largeur][hauteur];
		initialisationTab();
		while (!Parser.finalList.isEmpty()) {
			Parser.finalList.poll().toPPM();
		}
		PPM();
	}

	/**
	 * Cette methode cree l'image final on cree l'image a chaque ligne de
	 * l'image pour optimiser le temps de creation
	 * 
	 * @see Main#ecrireTextePPM(String)
	 */
	public static void PPM() {
		textePPM = "P3 ";
		textePPM += " " + hauteur + " " + largeur + " 255 ";
		PrintWriter ecri;
		try {
			ecri = new PrintWriter(new FileWriter(nomFichier));
			ecri.print(textePPM);
			ecri.flush();

			for (Pixel[] t : tab) {
				// temp="";
				for (Pixel p : t) {

					ecri.print(p.getRedGreenBlue());
					ecri.flush();

				}
			}
			ecri.close();
		} catch (NullPointerException a) {
			a.getStackTrace();
			System.out.println("Erreur : pointeur null");
		} catch (IOException a) {
			a.getStackTrace();
			System.out.println("Probleme d'IO");
		}
	}

	/**
	 * Cette methode cree le debut de l'image final en SVG
	 * 
	 * @param titre
	 *            elle prend en parametre le nom du fichier.
	 * @see Main#ecrireTexteSVG(String)
	 */
	public static void enTeteDeFichierSVG(String titre) {
		texteSVG = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><svg xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" xmlns:svg=\"http://www.w3.org/2000/svg\" xmlns=\"http://www.w3.org/2000/svg\" width=\""
				+ largeur
				+ "\" height=\""
				+ hauteur
				+ "\"> <title>"
				+ titre
				+ "</title>";
	}

	/**
	 * Cette methode cree la fin de l'image final en SVG
	 * 
	 * @see Main#ecrireTexteSVG(String)
	 */
	public static void piedDePAgeDeFichierSVG() {
		texteSVG += "</svg>";
	}

	/**
	 * Cette methode va creer l'image final en fonction du type d'image
	 * 
	 * @see Main#ecrireTexteSVG(String)
	 * @see Main#ecrireTextePPM(String)
	 * @see Main#creationAnalyse()
	 * 
	 */
	public static void ecrire() throws Exception {
		nomFichier = creationAnalyse();

		if (nomFichier.endsWith(".svg")) {
			ecrireTexteSVG(nomFichier);
		} else if (nomFichier.endsWith(".ppm")) {
			ecrireTextePPM(nomFichier);
		} else {
			System.out.println("Le type d'image n'est pas reconnu");
		}
	}

	/**
	 * Cette methode cree l'image final en SVG
	 * 
	 * @param titre
	 *            elle prend en parametre le nom du fichier.
	 * @see Main#ecrire()
	 * @see Main#enTeteDeFichierSVG(String)
	 * @see Main#piedDePAgeDeFichierSVG()
	 * @see FormePrimitive#toSVG()
	 */
	public static void ecrireTexteSVG(String titre) {
		rechercheTailleImage(Parser.finalList);
		enTeteDeFichierSVG(titre);
		while (!Parser.finalList.isEmpty()) {
			texteSVG += Parser.finalList.poll().toSVG();
		}
		piedDePAgeDeFichierSVG();
	}

	/**
	 * Cette methode recherche la taille de l'image
	 * 
	 * @param forme
	 *            elle prend en parametre la liste des Forme de l'image
	 * @see Main#ecrireTextePPM(String)
	 * @see FormePrimitive#getMaxX()
	 * @see FormePrimitive#getMaxY()
	 * @see FormePrimitive#getMinX()
	 * @see FormePrimitive#getMinY()
	 * @see FormePrimitive#translate(int, int)
	 * @see Main#rechercheTailleImage(LinkedList)
	 */
	public static void rechercheTailleImage(LinkedList<FormePrimitive> forme) {
		int maxX = 0;
		int maxY = 0;
		int minX = 0;
		int minY = 0;
		for (int i = 0; i < forme.size(); i++) {
			if (forme.get(i).getMaxX() > maxX) {
				maxX = forme.get(i).getMaxX();
			}
			if (forme.get(i).getMinX() < minX) {
				minX = forme.get(i).getMinX();
			}
			if (forme.get(i).getMaxY() > maxY) {
				maxY = forme.get(i).getMaxY();
			}
			if (forme.get(i).getMinY() < minY) {
				minY = forme.get(i).getMinY();
			}
		}
		if (minX < 0 || minY < 0) {
			minX = (minX == 0) ? 0 : -minX + 5;
			minY = (minY == 0) ? 0 : -minY + 5;
			for (int i = 0; i < forme.size(); i++) {
				forme.get(i).translate(minX, minY);
			}
		}

		largeur = minX + maxX + 5;
		hauteur = minY + maxY + 5;

	}

	/**
	 * Cette methode analyse les instructions pour pouvoir creer l'image
	 * 
	 * @see Main#ecrire()
	 * @see Lexer#Lexer()
	 * @see LookAheadReader#LookAheadReader(Lexer)
	 * @see Parser#Parser(LookAheadReader)
	 * @see Parser#nonterm_S()
	 * 
	 * @return une chaine de caractere, le titre de image
	 * 
	 * @throws au cas ou dune erreur 
	 */
	public static String creationAnalyse() throws Exception {
		Reader reader = new StringReader(texte);
		Lexer lexer = new Lexer(reader);
		LookAheadReader look =new LookAheadReader(lexer);
		Parser parser = new Parser(look);
		parser.nonterm_S();
		return parser.titre;

	}
}
