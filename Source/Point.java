/**
 * Cette classe represente un point et ses coordonnees x et y. Ces attributs ne sont pas modifiable.
 * @author ELBEZ Samuel & JACQUETTE Pierrick & PERRACHON Quentin
 */
public class Point {

	private int x, y;

	/**
	 * Cree un <b>Point</b> avec les caracteristiques suivantes :
	 * <ul style = "list-style-type:circle">
	 * <li>un entier x</li>
	 * <li>un entier y</li>
	 * </ul>
	 * 
	 * @param x
	 *            un entier representant la coordonnee x d'un <b>Point</b> cree.
	 * @param y
	 *            un entier representant la coordonnee y d'un <b>Point</b> cree.
	 * @see FormePrimitive#FormePrimitive(Point, int, int)
	 * @see FormePrimitive#remplissage4(int, int)
	 * @see Point#x
	 * @see Point#y
	 * 
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Cree un <b>Point</b> avec les caracteristiques suivantes :
	 * <ul style = "list-style-type:circle">
	 * <li>un entier x qui permet un decalage</li>
	 * <li>un entier y qui permet un decalage</li>
	 * </ul>
	 * 
	 * @param p
	 *            un <b>Point</b> representant l'ancien <b>Point</b> qui va etre
	 *            modifie.
	 * @param x
	 *            un entier representant la coordonnee x d'un <b>Point</b> cree.
	 * @param y
	 *            un entier representant la coordonnee y d'un <b>Point</b> cree.
	 * @see FormePrimitive#FormePrimitive(Point, int, int)
	 */
	public Point(Point p, int x, int y) {
		this.x = p.x + x;
		this.y = p.y + y;
	}

	/**
	 * Retourne la valeur de la coordonnees x du <b>Point</b>
	 * 
	 * @return Un entier : la valeur de la coordonnees x du <b>Point</b>
	 *         courant.
	 *         
	 * @see Point#x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Retourne la valeur de la coordonnees y du <b>Point</b>
	 * @return Un entier : la valeur de la coordonnees y du <b>Point</b>
	 *         courant.
	 *         
	 *  @see Point#y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Retourne une chaine de charactere representant les coordonnes du <b>Point</b>
	 * 
	 * @return Une chaine de charactere : la valeur des coordonnees x,y du
	 *         <b>Point</b> courant.
	 *         
	 * @see Point#x
	 * @see Point#y
	 */
	public String toString() {
		return x + " " + y;
	}
}
