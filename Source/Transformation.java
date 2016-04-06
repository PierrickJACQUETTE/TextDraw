import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Cette classe contient les transformations geometrique appliquee aux figures.
 * @author ELBEZ Samuel & JACQUETTE Pierrick & PERRACHON Quentin
 */

public class Transformation {

    /**
	 * Calcule la <b>distance</b> entre deux points
	 * 
	 * @param p1
	 *            Le premier Point
	 * @param p2
	 *            Le second Point
	 *            
	 * @see Transformation#arr(double)
	 * @see Math#pow(Number)
	 * @see Math#sqrt(Number)
	 * @see Point#getX()
	 * @see Point#getY()
	 * 
	 * @return La distance entre deux points
	 */
	public static int distance(Point p1, Point p2) {

		double distance = Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2)
				+ Math.pow(p2.getY() - p1.getY(), 2)); // formule mathematique de la distance
		return arr(distance);
	}

    /**
	 * Calcule l'<b>arrondis</b> d'un nombre de type double à l'entier superieur
	 * 
	 * @param a
	 *            Le nombre a arrondir
	 *            
	 * @see DecimalFormat#DecimalFormat(String)
	 * @see DecimalFormat#setRoundingMode(RoundingMode)
	 * @see Math#round(Number)
	 *
	 * @return L'entier superieur du double
	 */
	public static int arr(double a) {

		DecimalFormat df = new DecimalFormat("#"); // definit le partern d'arrondissement
		df.setRoundingMode(RoundingMode.HALF_UP); // definit l'arrondissement a l'entier superieur
		return (int) Math.round(a); // converti le double obtenu en entier
	}

    /**
	 * Calcule la <b>Translation</b> d'un point en fonction d'un vecteur
	 *
	 * @param p
	 *            Le Point a translate
	 * @param x
	 *            La coordonnee x du vecteur de translation
	 * @param y
	 *            La coordonnee y du vecteur de translation
	 *            
	 * @see Point#Point(int,int)
	 * @see Point#getX()
	 * @see Point#getY()
	 *
	 * @return Un point avec des coordonnees suivant la translation
	 */
	public static Point translation(Point p, int x, int y) {

		return new Point(p.getX() + x, p.getY() + y);
	}

    /**
	 * Calcule la <b>Reflextion</b> d'un point en fonction d'un axe de symetrie
	 * forme de deux points
	 *
	 * @param p
	 *            Le Point a transforme
	 * @param p1
	 *            Le premier Point de l'axe de symetrie
	 * @param p2
	 *            Le sconde Point de l'axe de symetrie
	 *            
	 * @see Point#Point(int,int)
	 * @see Point#getX()
	 * @see Point#getY()
	 *
	 * @return Un point avec des coordonnees suivant la reflextion
	 */
	public static Point reflection(Point p, Point p1, Point p2) {

		int dx = p2.getX() - p1.getX();
		int dy = p2.getY() - p1.getY();

		int xi;
		int yi;

		if (dx != 0) {
			if (dy != 0) {
				// axe y=ax+b
				double a = dy / (double) dx;
				double b = p1.getY() - a * p1.getX();

				// normale y=a2x+b2
				double a2 = -1 / a;
				double b2 = ((dx * p.getX()) / (double) dy) + p.getY();

				// point intersection entre axe et normale
				xi = arr((b2 - b) / (a - a2));
				yi = arr(xi * a + b);

			} else {
				xi = p.getX();
				yi = p1.getY();
			}

		} else {
			xi = p1.getX();
			yi = p.getY();
		}

		return rotation(new Point(xi, yi), p, 180);

	}

    /**
	 * Calcule la <b>Rotation</b> d'un point en fonction d'un autre point et d'un angle
	 *
	 * @param p1
	 *            Le Point sur lequel est effectue la rotation
	 * @param p2
	 *            Le Point qui sert d'axe de rotation
	 * @param angDeg
	 *            L'angle de la rotation
	 *            
	 * @see Point#Point(int,int)
	 * @see Point#getX()
	 * @see Point#getY()
	 * @see Math#PI
	 * @see Math#sin(Number)
	 * @see Math#cos(Number)
	 *
	 * @return Un point avec des coordonnees suivant la rotation
	 */
	public static Point rotation(Point p1, Point p2, int angDeg) {

		double angRad = Math.PI * angDeg / 180; // convertion de degre en radian

		double sin = Math.sin(angRad);
		double cos = Math.cos(angRad);

		int x = p1.getX()
				+ arr((p2.getX() - p1.getX()) * cos - (p2.getY() - p1.getY())
						* sin);
		int y = p1.getY()
				+ arr((p2.getX() - p1.getX()) * sin + (p2.getY() - p1.getY())
						* cos);
		return new Point(x, y);
	}

    /**
	 * Calcule l'<b>Homothetie</b> d'un point en fonction d'un autre point et d'un indice d'homothetie
	 *
	 * @param p1
	 *            Le Point sur lequel est effectue l'homothetie
	 * @param p2
	 *            Le Point qui sert d'axe d'homothetie
	 * @param k
	 *            L'indice d'homothetie
	 *            
	 * @see Point#Point(int,int)
	 * @see Point#getX()
	 * @see Point#getY()
	 * @see Transformation#arr(double)
	 *
	 * @return Un point avec des coordonnees suivant l'homothetie
	 */
	public static Point homothetie(Point p1, Point p2, double k) {

		int x = arr((p2.getX() - p1.getX()) * k);
		int y = arr((p2.getY() - p1.getY()) * k);
		
		return new Point(p1.getX()+x, p1.getY()+y);
	}
}
