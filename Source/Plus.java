/**
 * Cette classe permet d'effectuer une <b>Plus</b> entre deux <b>Expression</b>.
 * 
 * @author ELBEZ Samuel & JACQUETTE Pierrick & PERRACHON Quentin
 */
public class Plus extends Expression {

	public Expression e1;
	public Expression e2;

	/**
	 * Cree un <b>Plus</b> avec les caracteristiques suivantes :
	 * <ul style = "list-style-type:circle">
	 * <li>une <b>Expression</b> e1</li>
	 * <li>une <b>Expression</b> e2</li>
	 * </ul>
	 * 
	 * @param e1
	 *            une <b>Expression</b> representant la partie gauche d'une
	 *            <b>Plus</b>.
	 * @param e2
	 *            une <b>Expression</b> representant la partie droite d'une
	 *            <b>Plus</b>
	 * 
	 * @see Parser#nontermOP(Expression)
	 */
	public Plus(Expression e1, Expression e2) {
		this.e1 = e1;
		this.e2 = e2;
	}

	/**
	 * Evalue un e <b>Plus</b> avec les caracteristiques suivantes :
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
		return e1.eval() + e2.eval();
	}

}
