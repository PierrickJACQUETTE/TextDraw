/**
 * Cette classe represente une <b>CourbeDeBezier</b> représentée par quatre
 * <b>Point</b> depart, fin, pivot1,pivot2.
 * 
 * @author ELBEZ Samuel & JACQUETTE Pierrick & PERRACHON Quentin
 */
public class CourbeDeBezier {

	public Point depart, fin, pivot1, pivot2;

	/**
	 * Cree une <b>CourbeDeBezier</b> avec les caracteristiques suivantes :
	 * <ul style = "list-style-type:circle">
	 * <li>un <b>Point</b> depart</li>
	 * <li>un <b>Point</b> fin</li>
	 * <li>un <b>Point</b> pivot1</li>
	 * <li>un <b>Point</b> pivot2</li>
	 * </ul>
	 * 
	 * @param a
	 *            un Point representant le <b>Point</b> depart cree.
	 * @param b
	 *            un Point representant le <b>Point</b> pivot1 cree.
	 * @param c
	 *            un Point representant le <b>Point</b> pivot2 cree.
	 * @param d
	 *            un Point representant le <b>Point</b> fin cree.
	 * 
	 * @see FormePrimitive#FormePrimitive(Point, int, int)
	 * 
	 */

	public CourbeDeBezier(Point a, Point b, Point c, Point d) {
		this.depart = a;
		this.pivot1 = b;
		this.pivot2 = c;
		this.fin = d;
	}

	/**
	 * Cree une <b>CourbeDeBezier</b> avec les caracteristiques suivantes :
	 * <ul style = "list-style-type:circle">
	 * <li>un <b>Point</b> depart</li>
	 * <li>un <b>Point</b> fin</li>
	 * <li>un <b>Point</b> pivot1</li>
	 * <li>un <b>Point</b> pivot2</li>
	 * </ul>
	 * 
	 * @param a
	 *            un Point representant le <b>Point</b> depart et le pivot1
	 *            cree.
	 * @param b
	 *            un Point representant le <b>Point</b> fin et le pivot2 cree.
	 * 
	 * 
	 * @see FormePrimitive#FormePrimitive(java.util.ArrayList)
	 * 
	 */

	public CourbeDeBezier(Point a, Point b) {
		depart = a;
		fin = b;
		pivot1 = a;
		pivot2 = b;
	}

	/**
	 * Modifie les coordonnees d'une <b>CourbeDeBezier</b> point par point pour
	 * :
	 * <ul style = "list-style-type:circle">
	 * <li>un <b>Point</b> depart</li>
	 * <li>un <b>Point</b> fin</li>
	 * <li>un <b>Point</b> pivot1</li>
	 * <li>un <b>Point</b> pivot2</li>
	 * </ul>
	 * 
	 * @param x
	 *            un entier representant le x du <b>Point</b> le plus petit
	 *            d'une <>FormePrimitive</b>.
	 * @param y
	 *            un entier representant le y du <b>Point</b> le plus petit
	 *            d'une <>FormePrimitive</b>.
	 * 
	 * 
	 * @see FormePrimitive#translate(int, int)
	 * @see Transformation#translation(Point, int, int)
	 * 
	 */

	public void translate(int x, int y) {
		depart = Transformation.translation(depart, x, y);
		pivot1 = Transformation.translation(pivot1, x, y);
		pivot2 = Transformation.translation(pivot2, x, y);
		fin = Transformation.translation(fin, x, y);
	}
}
