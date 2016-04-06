/**
 * Cette classe de creer une expression Int
 * @author ELBEZ Samuel & JACQUETTE Pierrick & PERRACHON Quentin
 */

public class Int extends Expression{
	
	public int val;
	
	/**
	 * Cree un <b>Int</b> avec la caracteristique suivante :
	 * <ul style = "list-style-type:circle">
	 * <li>un entier val</li>
	 * </ul>
	 * 
	 * @param n
	 *            un entier representant la valeur de <b>Int</b>.
  
	 * @see Parser#nonterm_EXPR()
	 */
	public Int(int n){
		val=n;
	}
	
	/**
	 * Cree un <b>Int</b> avec la caracteristique suivante :
	 * <ul style = "list-style-type:circle">
	 * <li>un entier val</li>
	 * </ul>
	 * 
	 * @param n
	 *            une chaine de charactere representant la valeur de <b>Int</b>.
  
	 * @see Parser#nonterm_EXPR()
	 */
	public Int(String n) {
		val = Integer.parseInt(n);
	}
	
	/**
	 *	Evalue un <b>Int</b> avec la caracteristique suivante :
	 * <ul style = "list-style-type:circle">
	 * <li>un Expression val</li>
	 * </ul>
	 * 
	 * @see Parser#nonterm_EXPR()
	 * 
	 **/
	public double eval(){
		return val;
	}
	
}
