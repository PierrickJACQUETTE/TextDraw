/**
 * Cette classe permet de creer et de dessiner <b>FormePrimitive</b>, 
 * 
 * @author ELBEZ Samuel & JACQUETTE Pierrick & PERRACHON Quentin
 */

import java.awt.Color;
import java.util.LinkedList;
import java.util.LinkedList;
import java.util.Stack;

public class FormePrimitive {

	private LinkedList<CourbeDeBezier> liste = new LinkedList<CourbeDeBezier>();
	private Color fill = Color.WHITE;
	private Color stroke = Color.BLACK;
	private int maxX;
	private int maxY;
	private int minX;
	private int minY;
	private int ytab;
	private int xtab;
	private int deplacementXMin;
	private int deplacementYMin;
	private int deplacementXMax;
	private int deplacementYMax;

	/**
	 * Cree une <b>FormePrimitive</b> avec la caracteristique suivantes :
	 * <ul style = "list-style-type:circle">
	 * <li>une Liste de <b>Point</b> points</li>
	 * </ul>
	 * 
	 * @param points
	 *            un <b>Point</b> qui est le centre
	 * 
	 * @see Parser#nonterm_EXPR()
	 * @see Point#getX()
	 * @see Point#getY()
	 * @see CourbeDeBezier#CourbeDeBezier(Point, Point)
	 */
	public FormePrimitive(LinkedList<Point> points) {
		int x;
		int y;
		x = maxX = minX = points.get(0).getX();
		y = maxY = minY = points.get(0).getY();
		for (int i = 0; i < points.size() - 1; i++) {
			x = points.get(i).getX();
			y = points.get(i).getY();
			if (x > maxX) {
				maxX = x;
			}
			if (y > maxY) {
				maxY = y;
			}
			if (x < minX) {
				minX = x;
			}
			if (y < minY) {
				minY = y;
			}
			liste.add(new CourbeDeBezier(points.get(i), points.get(i + 1)));
		}// pour le dernier point
		x = points.get(points.size() - 1).getX();
		y = points.get(points.size() - 1).getY();
		if (x > maxX) {
			maxX = x;
		}
		if (y > maxY) {
			maxY = y;
		}
		if (x < minX) {
			minX = x;
		}
		if (y < minY) {
			minY = y;
		}
		liste.add(new CourbeDeBezier(points.get(points.size() - 1), points
				.get(0)));
	}

	/**
	 * Cree une <b>FormePrimitive</b> avec la caracteristique suivantes :
	 * <ul style = "list-style-type:circle">
	 * <li>une Liste de <b>Point</b> points</li>
	 * <li>une couleur fill</li>
	 * </ul>
	 * 
	 * @param points
	 *            un <b>Point</b> qui est le centre
	 * @param fill
	 *            la couleur de remplissage
	 * 
	 * @see Parser#nonterm_EXPR()
	 * @see FormePrimitive#FormePrimitive(LinkedList)
	 */
	public FormePrimitive(LinkedList<Point> points, Color fill) {
		this(points);
		this.fill = fill;
	}

	/**
	 * Cree une <b>FormePrimitive</b> avec la caracteristique suivantes :
	 * <ul style = "list-style-type:circle">
	 * <li>une Liste de <b>Point</b> points</li>
	 * <li>une couleur fill</li>
	 * <li>une couleur stroke</li>
	 * </ul>
	 * 
	 * @param points
	 *            un <b>Point</b> qui est le centre
	 * @param fill
	 *            la couleur de remplissage
	 * @param stroke
	 *            la couleur du trait
	 * 
	 * @see Parser#nonterm_EXPR()
	 * @see FormePrimitive#FormePrimitive(LinkedList, Color)
	 */
	public FormePrimitive(LinkedList<Point> points, Color fill, Color stroke) {
		this(points, fill);
		this.stroke = stroke;
	}

	/**
	 * Cree une <b>FormePrimitive</b> avec la caracteristique suivantes :
	 * <ul style = "list-style-type:circle">
	 * <li>un <b>Point</b> centre</li>
	 * <li>un entier le rayon en x</li>
	 * <li>un entier le rayon en y</li>
	 * </ul>
	 * 
	 * @param centre
	 *            un <b>Point</b> qui est le centre
	 * @param rayonx
	 *            un entier qui represente le rayon en X
	 * @param rayony
	 *            un entier qui represente le rayon en Y
	 * 
	 * @see Parser#nonterm_EXPR()
	 * @see Point#Point(int, int)
	 * @see Point#getX()
	 * @see Point#getY()
	 * @see CourbeDeBezier#CourbeDeBezier(Point, Point, Point, Point)
	 */
	public FormePrimitive(Point centre, int rayonx, int rayony) {
		float p = 0.55228475f;
		// Les 4 points cardinaux du cercle Top Bottom Left Right
		Point pT = new Point(centre.getX(), centre.getY() - rayony);
		Point pL = new Point(centre.getX() - rayonx, centre.getY());
		Point pB = new Point(centre.getX(), centre.getY() + rayony);
		Point pR = new Point(centre.getX() + rayonx, centre.getY());

		maxX = pR.getX();
		maxY = pB.getY();
		minX = pL.getX();
		minY = pT.getY();

		liste.add(new CourbeDeBezier(pL, new Point(pL, 0, -(int) (p * rayony)),
				new Point(pT, -(int) (p * rayonx), 0), pT));
		liste.add(new CourbeDeBezier(pT, new Point(pT, (int) (p * rayonx), 0),
				new Point(pR, 0, -(int) (p * rayony)), pR));
		liste.add(new CourbeDeBezier(pR, new Point(pR, 0, (int) (p * rayony)),
				new Point(pB, (int) (p * rayonx), 0), pB));
		liste.add(new CourbeDeBezier(pB, new Point(pB, -(int) (p * rayonx), 0),
				new Point(pL, 0, (int) (p * rayony)), pL));
	}

	/**
	 * Cree une <b>FormePrimitive</b> avec la caracteristique suivantes :
	 * <ul style = "list-style-type:circle">
	 * <li>un <b>Point</b> centre</li>
	 * <li>un entier le rayon en x</li>
	 * <li>un entier le rayon en y</li>
	 * <li>une couleur fill</li>
	 * </ul>
	 * 
	 * @param centre
	 *            un <b>Point</b> qui est le centre
	 * @param rayonx
	 *            un entier qui represente le rayon en X
	 * @param rayony
	 *            un entier qui represente le rayon en Y
	 * @param fill
	 *            la couleur de remplissage
	 * 
	 * @see Parser#nonterm_EXPR()
	 * @see FormePrimitive#FormePrimitive(Point, int, int)
	 */
	public FormePrimitive(Point centre, int rayonx, int rayony, Color fill) {
		this(centre, rayonx, rayony);
		this.fill = fill;
	}

	/**
	 * Cree une <b>FormePrimitive</b> avec la caracteristique suivantes :
	 * <ul style = "list-style-type:circle">
	 * <li>un <b>Point</b> centre</li>
	 * <li>un entier le rayon en x</li>
	 * <li>un entier le rayon en y</li>
	 * <li>une couleur fill</li>
	 * <li>une couleur stroke</li>
	 * </ul>
	 * 
	 * @param centre
	 *            un <b>Point</b> qui est le centre
	 * @param rayonx
	 *            un entier qui represente le rayon en X
	 * @param rayony
	 *            un entier qui represente le rayon en Y
	 * @param fill
	 *            la couleur de remplissage
	 * @param stroke
	 *            la couleur du trait
	 * 
	 * @see Parser#nonterm_EXPR()
	 * @see FormePrimitive#FormePrimitive(Point, int, int, Color, Color)
	 */
	public FormePrimitive(Point centre, int rayonx, int rayony, Color fill,
			Color stroke) {
		this(centre, rayonx, rayony, fill);
		this.stroke = stroke;
	}

	/**
	 * Cree une <b>FormePrimitive</b> avec la caracteristique suivantes :
	 * <ul style = "list-style-type:circle">
	 * <li>un <b>Point</b> centre</li>
	 * <li>un entier le rayon</li>
	 * </ul>
	 * 
	 * @param centre
	 *            un <b>Point</b> qui est le centre
	 * @param rayon
	 *            un entier qui represente le rayon
	 * 
	 * @see Parser#nonterm_EXPR()
	 * @see FormePrimitive#FormePrimitive(Point, int, int)
	 */
	public FormePrimitive(Point centre, int rayon) {
		this(centre, rayon, rayon);
	}

	/**
	 * Cree une <b>FormePrimitive</b> avec la caracteristique suivantes :
	 * <ul style = "list-style-type:circle">
	 * <li>un <b>Point</b> centre</li>
	 * <li>un entier le rayon</li>
	 * <li>une couleur fill</li>
	 * </ul>
	 * 
	 * @param centre
	 *            un <b>Point</b> qui est le centre
	 * @param rayon
	 *            un entier qui represente le rayon
	 * @param fill
	 *            la couleur de remplissage
	 * 
	 * @see Parser#nonterm_EXPR()
	 * @see FormePrimitive#FormePrimitive(Point, int)
	 */
	public FormePrimitive(Point centre, int rayon, Color fill) {
		this(centre, rayon);
		this.fill = fill;
	}

	/**
	 * Cree une <b>FormePrimitive</b> avec la caracteristique suivantes :
	 * <ul style = "list-style-type:circle">
	 * <li>un <b>Point</b> centre</li>
	 * <li>un entier le rayon</li>
	 * <li>une couleur fill</li>
	 * <li>une couleur stroke</li>
	 * </ul>
	 * 
	 * @param centre
	 *            un <b>Point</b> qui est le centre
	 * @param rayon
	 *            un entier qui represente le rayon
	 * @param fill
	 *            la couleur de remplissage
	 * @param stroke
	 *            la couleur du trait
	 * 
	 * @see Parser#nonterm_EXPR()
	 * @see FormePrimitive#FormePrimitive(Point, int, Color)
	 */
	public FormePrimitive(Point centre, int rayon, Color fill, Color stroke) {
		this(centre, rayon, fill);
		this.stroke = stroke;
	}

	/**
	 * Cette methode permet de creer l'image en SVG
	 * 
	 * @see Main#ecrireTexteSVG(String)
	 * @see Point#getX()
	 * @see Point#getY()
	 * @see Pixel#getBlue()
	 * @see Pixel#getGreen()
	 * @see Pixel#getRed()
	 */

	public String toSVG() {
		String s = "<path d=\"M " + liste.get(0).depart.getX() + ","
				+ liste.get(0).depart.getY();
		for (CourbeDeBezier c : liste) {
			s += " C " + c.pivot1.getX() + "," + c.pivot1.getY() + " "
					+ c.pivot2.getX() + "," + c.pivot2.getY() + " "
					+ c.fin.getX() + "," + c.fin.getY();
		}
		s += "\" fill=\"rgb(" + fill.getRed() + "," + fill.getGreen() + ","
				+ fill.getBlue() + ")\" stroke=\"rgb(" + stroke.getRed() + ","
				+ stroke.getGreen() + "," + stroke.getBlue() + ")\"/>";
		return s;
	}

	/**
	 * Cette methode permet de creer un tableau pour une forme (methode utilisee
	 * uniquement pour les images ppm)
	 * 
	 * 
	 * @see Main#ecrireTextePPM(String)
	 * @see Pixel#Pixel(int, int, int)
	 * @see FormePrimitive#initialisation(Pixel[][], Color)
	 * @see FormePrimitive#bezier2(Color)
	 * @see FormePrimitive#remplissage4(int, int)
	 * @see FormePrimitive#mettreFormeDansImage()
	 */

	public void toPPM() {
		deplacementXMin = minX - 1;
		deplacementYMin = minY - 1;
		deplacementXMax = maxX + 1;
		deplacementYMax = maxY + 1;
		xtab = deplacementXMax - deplacementXMin + 1;
		ytab = deplacementYMax - deplacementYMin + 1;
		Main.forme = new Pixel[xtab][ytab];
		initialisation(Main.forme, fill);
		boolean trace = false;
		Color tmp = stroke;
		if (stroke.getRGB() == fill.getRGB()
				&& stroke.getRGB() != Color.black.getRGB()) {
			bezier2(Color.black);
			trace = true;
			stroke = Color.black;
		} else if (stroke.getRGB() == fill.getRGB()
				&& stroke.getRGB() == Color.black.getRGB()) {
			bezier2(Color.blue);
			trace = true;
			stroke = Color.blue;
		} else {
			bezier2(stroke);
			trace = true;
		}
		if (trace) {
			remplissage4(0, 0);
		}
		bezier2(tmp);
		mettreFormeDansImage();
	}

	/**
	 * Cette methode permet de tracer les courbes de chaque courbe de bezier de
	 * chaque forme (methode utilisee uniquement pour les images ppm)
	 * 
	 * @param s
	 *            une couleur representant la couleur du trait de la courbe
	 * 
	 * @see FormePrimitive#bezier(int, int, int, int, int, int, int, int, Color)
	 * @see Point#getX()
	 * @see Point#getY()
	 */
	public void bezier2(Color s) {
		for (CourbeDeBezier c : liste) {
			bezier(c.depart.getX() - deplacementXMin + 1, c.depart.getY()
					- deplacementYMin + 1, c.pivot1.getX() - deplacementXMin
					+ 1, c.pivot1.getY() - deplacementYMin + 1, c.pivot2.getX()
					- deplacementXMin + 1, c.pivot2.getY() - deplacementYMin
					+ 1, c.fin.getX() - deplacementXMin + 1, c.fin.getY()
					- deplacementYMin + 1, s);
		}
	}

	/**
	 * Methode qui teste si la couleur est deja rempli ou pas(methode utilisee
	 * uniquement pour les images ppm)
	 * 
	 * @param c
	 *            une couleur
	 * @param x
	 *            un entier representant la coordonnee x du <b>Point</b>
	 * @param y
	 *            un entier representant la coordonnee y du <b>Point</b>
	 * 
	 * @return boolean pour savoir si la couleur est identique ou pas
	 * 
	 * @see Pixel#getBlue()
	 * @see Pixel#getGreen()
	 * @see Pixel#getRed()
	 */
	public boolean compareColor(Color c, int x, int y) {
		if (c.getRed() == Main.forme[x][y].getRed()
				&& c.getGreen() == Main.forme[x][y].getGreen()
				&& c.getBlue() == Main.forme[x][y].getBlue()) {
			return true;
		}

		return false;
	}

	/**
	 * Methode verifiant si il faut colorier ou pas le <b>Pixel</b> : (methode
	 * utilisee uniquement pour les images ppm)
	 * 
	 * @param x
	 *            un entier x qui correspond au x du <b>Point</b> testé
	 * @param y
	 *            un entier y qui correspond au x du <b>Point</b> testé
	 * 
	 * @return un boolean true si le remplissage est possible sinon false
	 * 
	 * @see FormePrimitive#compareColor(Color, int, int)
	 */
	public boolean possible(int x, int y) {
		if (x < 0 || x >= xtab || y < 0 || y >= ytab
				|| compareColor(stroke, x, y) || !compareColor(fill, x, y)) {
			return false;
		}
		return true;

	}

	/**
	 * Methode permettant de remplir une forme (methode utilisee uniquement pour
	 * les images ppm)
	 * 
	 * @param x
	 *            un entier representant la coordonnee x du premier <Point>
	 * @param y
	 *            un entier representant la coordonnee y du premier <Point>
	 * 
	 * @see FormePrimitive#possible(int, int)
	 * @see Point#Point(int, int)
	 * @see Pixel#setRedGreenBlue(int, int, int)
	 */
	public void remplissage4(int x, int y) {
		Stack<Point> p = new Stack<Point>();
		if (!possible(x, y)) {
			return;
		}
		p.push(new Point(x, y));
		while (!p.isEmpty()) {
			Point n = p.pop();
			if (possible(n.getX(), n.getY())) {
				int x1 = n.getX();
				int x2 = n.getX();
				while (possible(x1 - 1, n.getY())) {
					x1--;
				}
				while (possible(x2 + 1, n.getY())) {
					x2++;
				}
				for (int i = x1; i <= x2; i++) {
					Main.forme[i][n.getY()].setRedGreenBlue(-1, -1, -1);
					if (possible(i, n.getY() - 1)) {
						p.push(new Point(i, n.getY() - 1));
					}
					if (possible(i, n.getY() + 1)) {
						p.push(new Point(i, n.getY() + 1));
					}
				}
			}
		}
	}

	/**
	 * Cette methode permet de mettre chaque forme de l'image dans l'image
	 * (methode utilisee uniquement pour les images ppm)
	 * 
	 * @see Pixel#getRed()
	 * @see Pixel#getGreen()
	 * @see Pixel#getBlue()
	 */
	public void mettreFormeDansImage() {
		for (int i = 0; i < Main.forme.length; i++) {
			for (int j = 0; j < Main.forme[i].length; j++) {
				if (Main.forme[i][j].getRed() != -1
						&& Main.forme[i][j].getGreen() != -1
						&& Main.forme[i][j].getBlue() != -1) {
					Main.tab[j + deplacementYMin][i + deplacementXMin] = Main.forme[i][j];
				}
			}
		}
	}

	/**
	 * Cette methode initialise le tableau placé en argument de la couleur en
	 * argument (methode utilisee uniquement pour les images ppm)
	 * 
	 * @param t
	 *            un tableau de <b>Pixel</b>
	 * @param fil
	 *            une couleur
	 * 
	 * @see Pixel#getRed()
	 * @see Pixel#getGreen()
	 * @see Pixel#getBlue()
	 */

	public void initialisation(Pixel[][] t, Color fil) {
		for (int i = 0; i < t.length; i++) {
			for (int j = 0; j < t[i].length; j++) {
				t[i][j] = new Pixel(fil.getRed(), fil.getGreen(), fil.getBlue());
			}
		}
	}

	/**
	 * Retourne la valeur de l'entier MaxX de la <b>FormePrimitive</b>
	 * 
	 * @return Un entier : la valeur de la <b>FormePrimitive</b> courante
	 * @see Main#rechercheTailleImage(LinkedList)
	 */
	public int getMaxX() {
		return maxX;
	}

	/**
	 * Retourne la valeur de l'entier MaxY de la <b>FormePrimitive</b>
	 * 
	 * @return Un entier : la valeur MaxY de la <b>FormePrimitive</b> courante
	 * @see Main#rechercheTailleImage(LinkedList)
	 */
	public int getMaxY() {
		return maxY;
	}

	/**
	 * Retourne la valeur de l'entier minY de la <b>FormePrimitive</b>
	 * 
	 * @return Un entier : la valeur minY de la <b>FormePrimitive</b> courante
	 * @see Main#rechercheTailleImage(LinkedList)
	 */
	public int getMinX() {
		return minX;
	}

	/**
	 * Retourne la valeur de l'entier MinY de la <b>FormePrimitive</b>
	 * 
	 * @return Un entier : la valeur MinY de la <b>FormePrimitive</b> courante
	 * @see Main#rechercheTailleImage(LinkedList)
	 */
	public int getMinY() {
		return minY;
	}

	/**
	 * Cette methode trace le <b>Pixel</b> avec les caracteristiques : (methode
	 * utilisee uniquement pour les images ppm)
	 * 
	 * @param x
	 *            entier x : coordoonees x du <b>Pixel</b>
	 * @param y
	 *            : entier y : coordoonees y du <b>Pixel</b>
	 * @param s
	 *            couleur s du <b>Pixel</b>
	 * 
	 * @see FormePrimitive#bezier(int, int, int, int, int, int, int, int, Color)
	 */
	public void tracePixel(int x, int y, Color s) {
		Main.forme[x][y].setRedGreenBlue(s.getRed(), s.getGreen(), s.getBlue());
	}

	/**
	 * Cette methode permet de tracer une courbe de bezier , methode utiliser
	 * pour les images ppm
	 * 
	 * @param xDepart
	 *            le x du <b>Point</b> de depart de la courbe
	 * @param yDepart
	 *            le y du <b>Point</b> de depart de la courbe
	 * @param pivotXDepart
	 *            le x du Pivot du <b>Point</b> de depart de la courbe
	 * @param pivotYDepart
	 *            le y du Pivot du <b>Point</b> de depart de la courbe
	 * @param pivotXArrivee
	 *            le x du Pivot du <b>Point</b> d arrivee de la courbe
	 * @param pivotYArrivee
	 *            le y du Pivot du <b>Point</b> d arrivee de la courbe
	 * @param xArrivee
	 *            le x du <b>Point</b> d arrivee de la courbe
	 * @param yArrivee
	 *            le y du <b>Point</b> d arrivee de la courbe
	 * @param s
	 *            la couleur du trait de la courbe
	 * 
	 * @see FormePrimitive#tracePixel(int, int, Color)
	 */
	public void bezier(int xDepart, int yDepart, int pivotXDepart,
			int pivotYDepart, int pivotXArrivee, int pivotYArrivee,
			int xArrivee, int yArrivee, Color s) {
		for (double t = 0.0; t < 1.0; t += 0.00001) {
			double xt = Math.pow(1 - t, 3) * xDepart + 3 * t
					* Math.pow(1 - t, 2) * pivotXDepart + 3 * Math.pow(t, 2)
					* (1 - t) * pivotXArrivee + Math.pow(t, 3) * xArrivee;

			double yt = Math.pow(1 - t, 3) * yDepart + 3 * t
					* Math.pow(1 - t, 2) * pivotYDepart + 3 * Math.pow(t, 2)
					* (1 - t) * pivotYArrivee + Math.pow(t, 3) * yArrivee;
			int xx = (int) xt;
			int yy = (int) yt;
			if (!(xx == Main.forme.length - 1 || yy == Main.forme[0].length - 1)) {
				tracePixel(xx, yy, s);
			}
		}
	}

	/**
	 * Cette methode permet de faire des translations de <b>Point</b>
	 * 
	 * @param x
	 *            l'entier x : de combien on translate le point en x
	 * @param y
	 *            l'entier y : de combien on translate le point en y
	 * 
	 * 
	 * @see Main#rechercheTailleImage(LinkedList)
	 * @see CourbeDeBezier#translate(int, int)
	 */

	public void translate(int x, int y) {
		for (CourbeDeBezier c : liste) {
			c.translate(x, y);
		}
		minX += x;
		minY += y;
		maxX += x;
		maxY += y;
	}
}
