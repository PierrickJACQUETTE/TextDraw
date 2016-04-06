/**
 * Cette classe permet d'effectuer un division d'<b>Expression</b> deux le
 * denominateur et le numerateur.
 * 
 * @author ELBEZ Samuel & JACQUETTE Pierrick & PERRACHON Quentin
 */

public class Div extends Expression {

	public Expression e1;
	public Expression e2;

	/**
	 * Cree une <b>Division</b> avec les caracteristiques suivantes :
	 * <ul style = "list-style-type:circle">
	 * <li>une <b>Expression</b> e1</li>
	 * <li>une <b>Expression</b> e2</li>
	 * </ul>
	 * 
	 * @param e1
	 *            une <b>Expression</b> representant le numerateur d'une
	 *            <b>Division</b>.
	 * @param e2
	 *            une <b>Expression</b> representant le denominateur d'une
	 *            <b>Division</b>
	 * 
	 * @see Parser#nontermOP(Expression)
	 */

	public Div(Expression e1, Expression e2) {
		this.e1 = e1;
		this.e2 = e2;
	}

	/**
	 * Evalue un e <b>Division</b> avec les caracteristiques suivantes :
	 * <ul style = "list-style-type:circle">
	 * <li>une <b>Expression</b> e1</li>
	 * <li>une <b>Expression</b> e2</li>
	 * </ul>
	 * 
	 * @see Parser#nonterm_FORM_ELLIPSE()
	 * @see Parser#nonterm_FORM_CIRCLE()
	 * @see Parser#nonterm_Point()
	 * @see Parser#nonterm_ROTATION()
	 * @see Parser#nonterm_SPIN()
	 * @see Parser#nonterm_HOMOP(String)
	 * @see Parser#nonterm_HOMOC(String)
	 * @see Parser#nonterm_GROP(String)
	 * @see Parser#nonterm_GROC(String)
	 */

	public double eval() throws Exception {
		double m = e2.eval();
		if (m == 0) {
			throw new ArithmeticException("Erreur : Division par 0");
		}
		return e1.eval() / m;
	}

}
