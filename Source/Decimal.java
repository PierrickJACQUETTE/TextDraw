/**
 * Cette classe de creer une expression Decimal
 * @author ELBEZ Samuel & JACQUETTE Pierrick & PERRACHON Quentin
 */

public class Decimal extends Expression{
	
	public double val;
	
	/**
	 * Cree un <b>Decimal</b> avec la caracteristique suivante :
	 * <ul style = "list-style-type:circle">
	 * <li>un double val</li>
	 * </ul>
	 * 
	 * @param n
	 *            un double representant la valeur de <b>Decimal</b>.
  
	 * @see Parser#nonterm_EXPR()
	 */
	public Decimal(double n){
		val=n;
	}
	
	/**
	 * Cree un <b>Decimal</b> avec la caracteristique suivante :
	 * <ul style = "list-style-type:circle">
	 * <li>un double val</li>
	 * </ul>
	 * 
	 * @param n
	 *            une chaine de charactere representant la valeur de <b>Decimal</b>.
  
	 * @see Parser#nonterm_EXPR()
	 */
	public Decimal(String n) {
		val = Double.parseDouble(n);
	}
	
	/**
	 *	Evalue un <b>Decimal</b> avec la caracteristique suivante :
	 * <ul style = "list-style-type:circle">
	 * <li>un Expression val</li>
	 * </ul>
	 * 
	 * @see Parser#nonterm_EXPR()
	 */
	public double eval(){
		return val;
	}
	
}

