/**
 * Cette classe represente la couleur d'un<b>Pixel</b> représentée par trois entiers rgb.
 * @author ELBEZ Samuel & JACQUETTE Pierrick & PERRACHON Quentin
 */

public class Pixel {

	private int red;
	private int green;
	private int blue;
	private String rgb;

	/**
	 * Cree un <b>Pixel</b> avec les caracteristiques suivantes :
	 * <ul style = "list-style-type:circle">
	 * <li>un entier red</li>
	 * <li>un entier green</li>
	 * <li>un entier blue</li>
	 * </ul>
	 * 
	 * @param red
	 *            un entier representant la couleur rouge d'un <b>Pixel</b> cree.
	 * @param green
	 *            un entier representant la couleur verte d'un <b>Pixel</b> cree.
	 * @param blue
	 *            un entier representant la couleur blue d'un <b>Pixel</b> cree.
	 *            
	 * @see Main#initialisationTab()
	 * @see FormePrimitive#initialisation(Pixel[][], java.awt.Color)
	 * @see Pixel#red
	 * @see Pixel#green
	 * @see Pixel#blue
	 * @see Pixel#rgb
	 * 
	 */
	public Pixel(int red, int green, int blue) {
		this.red=red;
		this.green=green;
		this.blue=blue;
		this.rgb= red+" "+green+" "+blue+" ";

	}
	/**
	 * Retourne la valeur de la couleur d'un <b>Pixel</b>
	 * 
	 * @return Une chaine de charactere : les valeurs de la couleur du <b>Pixel</b>
	 *         courant.
	 *         
	 * @see Pixel#rgb
	 */
	public String getRedGreenBlue(){
		return rgb;
	}
	
	/**
	 * Retourne la valeur de la couleur rouge du <b>Pixel</b>
	 * 
	 * @return Un entier : la valeur de la couleur rouge du <b>Pixel</b>
	 *         courant.
	 *         
	 * @see FormePrimitive#compareColor(java.awt.Color, int, int)
	 * @see FormePrimitive#mettreFormeDansImage()
	 * @see FormePrimitive#initialisation(Pixel[][], java.awt.Color)
	 * @see FormePrimitive#tracePixel(int, int, java.awt.Color)
	 * @see Pixel#red
	 */
	public int getRed(){
		return red;
	}	
	
	/**
	 * Retourne la valeur de la couleur verte du <b>Pixel</b>
	 * 
	 * @return Un entier : la valeur de la couleur verte du <b>Pixel</b>
	 *         courant.
	 * @see FormePrimitive#compareColor(java.awt.Color, int, int)
	 * @see FormePrimitive#mettreFormeDansImage()
	 * @see FormePrimitive#initialisation(Pixel[][], java.awt.Color)
	 * @see FormePrimitive#tracePixel(int, int, java.awt.Color)
	 * @see Pixel#green
	 */
	public int getGreen(){
		return green;
	}	
	
	/**
	 * Retourne la valeur de la couleur blue du <b>Pixel</b>
	 * 
	 * @return Un entier : la valeur de la couleur blue du <b>Pixel</b>
	 *         courant.
	 *         
	 * @see FormePrimitive#compareColor(java.awt.Color, int, int)
	 * @see FormePrimitive#mettreFormeDansImage()
	 * @see FormePrimitive#initialisation(Pixel[][], java.awt.Color)
	 * @see FormePrimitive#tracePixel(int, int, java.awt.Color)
	 * @see Pixel#blue
	 */
	public int getBlue(){
		return blue;
	}	
	
	/**
	 * Modifie la valeur de la couleur d'un <b>Pixel</b>
	 * @param r
	 *            un entier representant la couleur rouge d'un <b>Pixel</b> cree.
	 * @param g
	 *            un entier representant la couleur verte d'un <b>Pixel</b> cree.
	 * @param b
	 *            un entier representant la couleur blue d'un <b>Pixel</b> cree
	 *
	 * @see FormePrimitive#remplissage4(int, int)
	 * @see FormePrimitive#tracePixel(int, int, java.awt.Color)
	 * @see Pixel#red
	 * @see Pixel#green
	 * @see Pixel#blue
	 * @see Pixel#rgb
	 */
	public void setRedGreenBlue(int r, int g, int b){
		red=r;
		green=g;
		blue=b;
		rgb= r+" "+g+" "+b+" ";

	}
	
}
