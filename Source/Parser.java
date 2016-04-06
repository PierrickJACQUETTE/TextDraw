import java.awt.Color;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * La classe Parser est utilisée pour analyser syntaxiquement à partir d'une
 * grammaire une liste de jetons
 * 
 * @author ELBEZ Samuel & JACQUETTE Pierrick & PERRACHON Quentin
 *
 */
public class Parser {

	public static LinkedList<FormePrimitive> finalList = new LinkedList<FormePrimitive>();

	public static HashMap<String, String> definitions = new HashMap<String, String>();

	public LookAheadReader reader;

	public String titre;

	/**
	 * Initialise la HashMap de definition
	 * 
	 * @see Parser#definitions
	 */
	public static void initDefinitions() {
		definitions.put("rouge", "color{255,0,0}");
		definitions.put("bleu", "color{0,0,255}");
		definitions.put("vert", "color{0,255,0}");
		definitions.put("jaune", "color {255,255,0}");
		definitions.put("orange", "color {237,127,16}");
		definitions.put("rose", "color {253,108,158}");
		definitions.put("violet", "color {102,0,153}");
		definitions.put("marron", "color {91,60,17}");
		definitions.put("noir", "color {0,0,0}");
		definitions.put("blanc", "color {255,255,255}");
		definitions.put("gris", "color {96,96,96}");

		definitions.put("red", "color{255,0,0}");
		definitions.put("blue", "color{0,0,255}");
		definitions.put("green", "color{0,255,0}");
		definitions.put("pink", "color {237,127,16}");
		definitions.put("purple", "color {102,0,153}");
		definitions.put("yellow", "color {255,255,0}");
		definitions.put("black", "color {0,0,0}");
		definitions.put("white", "color {255,255,255}");
		definitions.put("grey", "color {96,96,96}");
		definitions.put("brown", "color {91,60,17}");
	}

	/**
	 * Construit un Parser à partir d'une liste de Jeton et initialise la liste
	 * jetons à partir de celle ci, doubleArgs et ptsArgs en tant que liste
	 * vide. Et initialise à null fill et stroke;
	 * 
	 * @param l
	 *            La liste de jetons à analyser
	 * 
	 * @see Parser#jetons
	 * @see Parser#doubleArgs
	 * @see Parser#ptsArgs
	 * @see Parser#fill
	 * @see Parser#stroke
	 * @see Main#creationAnalyse()
	 */
	public Parser(LookAheadReader look) {
		this.reader = look;
	}

	/**
	 * Ajoute une forme primitive dans la liste finalList
	 * 
	 * @param formePrimitive
	 * 
	 * @see Parser#finalList
	 * @see FormePrimitive#FormePrimitive(ArrayList)
	 */
	private void addFormePrimitive(FormePrimitive f) {

		finalList.add(f);
	}

	/**
	 * Verifie si le prochain jetons de la liste possède comme Id l'id en
	 * argument
	 * 
	 * @param id
	 *            L'id que le jeton doit avoir
	 * @return true si les deux Ids correspondent, faux sinon.
	 */
	public boolean check(Sym s) throws Exception {

		if (reader.check(s)) {
			return true;
		}
		return false;
	}

	/**
	 * Symbole Terminal de la grammaire.
	 * 
	 * @param id
	 *            L'id du symbole Terminal
	 */
	public void term(Sym s) throws Exception {
		// System.out.println(s+" "+reader.getCurrent().getContenu());
		reader.eat(s);

	}

	/**
	 * Symbole Terminal de la grammaire. Renvoie sa valeur.
	 * 
	 * @param id
	 *            L'id du symbole Terminal
	 * @return s La valeur du jeton représentant le symbole Terminale
	 */
	public String term_value(Sym s) throws Exception {
		String r = reader.getValue();
		reader.eat(s);
		return r;
	}

	/**
	 * Retourne sous la forme d'une chaine de caratère le reste de la liste de
	 * jetons
	 * 
	 * @return Une chaine caractère représentant le reste de la la liste de
	 *         jetons.
	 */
	public String getDefinition() throws Exception {
		String s = "";
		while (!reader.check(Sym.SEMI)) {
			Jeton j = reader.getCurrent();
			if (j.getId() == Sym.DEF_NAME) {
				s += "$";
			}
			s += term_value(j.getId()) + " ";
		}
		return s;
	}

	/**
	 * Regle S de la grammaire, règle de départ. S->draw DRAW | def DEF | move
	 * MOVE | reflect REFLECT | rotate ROTATE | extend HOMOTHETIE | grow GROWTH
	 * | spin SPIN
	 * 
	 * @see Parser#term(int)
	 * @see Parser#nonterm_DRAW()
	 * @see Parser#nonterm_DEF()
	 * @see Parser#nonterm_WRITE()
	 * @see Parser#nonterm_TRANSLATION()
	 * @see Parser#nonterm_SYMETRIE()
	 * @see Parser#nonterm_ROTATION()
	 * @see Parser#nonterm_HOMOTHETIE()
	 * @see Parser#nonterm_GROWTH()
	 * @see Parser#nonterm_SPIN()
	 * @se Main#creationAnalyse()
	 */
	public void nonterm_S() throws Exception {
		Sym s = reader.getCurrent().getId();
		switch (s) {
		case DRAW:
			term(Sym.DRAW);
			nonterm_DRAW();
			term(Sym.SEMI);
			break;
		case DEF:
			nonterm_DEF();
			term(Sym.SEMI);
			break;
		case WRITE:
			nonterm_WRITE();
			term(Sym.SEMI);
			break;
		case MOVE:
			nonterm_TRANSLATION();
			term(Sym.SEMI);
			break;
		case REFLECT:
			nonterm_SYMETRIE();
			term(Sym.SEMI);
			break;
		case ROTATE:
			nonterm_ROTATION();
			term(Sym.SEMI);
			break;
		case EXTEND:
			nonterm_HOMOTHETIE();
			term(Sym.SEMI);
			break;
		case GROW:
			nonterm_GROWTH();
			term(Sym.SEMI);
			break;
		case SPIN:
			nonterm_SPIN();
			term(Sym.SEMI);
			break;
		case RECOLOR:
			nonterm_RECOLOR();
			term(Sym.SEMI);
			break;
		default:
			throw new Exception("Instruction non reconnu");
		}
		if (!reader.check(Sym.EOF)) {
			nonterm_S();
		} else {
			term(Sym.EOF);
		}

	}

	/**
	 * Regle DRAW de la grammaire DRAW -> circle FORM_CIRCLE | ellipse
	 * FORM_ELLIPSE | polygon FORM_POLYGON
	 * 
	 * @see Parser#term(int)
	 * @see Parser#nonterm_FORM_CIRCLE()
	 * @see Parser#nonterm_FORM_ELLIPSE()
	 * @see Parser#nonterm_FORM_POLYGON()
	 */
	private void nonterm_DRAW() throws Exception {
		if (this.check(Sym.FORM_CIRCLE)) {
			term(Sym.FORM_CIRCLE);
			addFormePrimitive(nonterm_FORM_CIRCLE());
		} else if (this.check(Sym.FORM_ELLIPSE)) {
			term(Sym.FORM_ELLIPSE);
			addFormePrimitive(nonterm_FORM_ELLIPSE());
		} else if (this.check(Sym.FORM_POLYGON)) {
			term(Sym.FORM_POLYGON);
			addFormePrimitive(nonterm_FORM_POLYGON());

		}
	}

	/**
	 * Regle FORM_ELLIPSE
	 * 
	 * @return
	 * 
	 * @see Parser#term(int)
	 * @see Parser#nonterm_Point()
	 * @see Parser#nonterm_EXPR()
	 * @see Parser#nonterm_Color()
	 */
	private FormePrimitive nonterm_FORM_ELLIPSE() throws Exception {
		term(Sym.OPEN_ACCOLADE);
		Point c = nonterm_Point();
		term(Sym.OPEN_PARENTHESE);
		int r1 = (int) nonterm_EXPR().eval();
		term(Sym.CLOSE_PARENTHESE);
		term(Sym.OPEN_PARENTHESE);
		int r2 = (int) nonterm_EXPR().eval();
		term(Sym.CLOSE_PARENTHESE);
		Color fill = Color.WHITE;
		Color stroke = Color.BLACK;
		if (check(Sym.COLOR) || check(Sym.DEF_NAME)) {
			fill = nonterm_Color();
			if (check(Sym.COLOR) || check(Sym.DEF_NAME)) {
				stroke = nonterm_Color();
			}
		}
		term(Sym.CLOSE_ACCOLADE);
		return new FormePrimitive(c, r1, r2, fill, stroke);

	}

	/**
	 * Regle FORM_CIRCLE
	 * 
	 * @see Parser#term(int)
	 * @see Parser#nonterm_Point()
	 * @see Parser#nonterm_EXPR()
	 * @see Parser#nonterm_Color()
	 */
	private FormePrimitive nonterm_FORM_CIRCLE() throws Exception {
		term(Sym.OPEN_ACCOLADE);
		Point c = nonterm_Point();
		term(Sym.OPEN_PARENTHESE);
		int r = (int) nonterm_EXPR().eval();
		term(Sym.CLOSE_PARENTHESE);
		Color fill = Color.WHITE;
		Color stroke = Color.BLACK;
		if (check(Sym.COLOR) || check(Sym.DEF_NAME)) {
			fill = nonterm_Color();
			if (check(Sym.COLOR) || check(Sym.DEF_NAME)) {
				stroke = nonterm_Color();
			}
		}
		term(Sym.CLOSE_ACCOLADE);
		return new FormePrimitive(c, r, fill, stroke);
	}

	/**
	 * Regle FORM_POLYGON
	 * 
	 * @see Parser#term(int)
	 * @see Parser#nonterm_MultiplePoint()
	 * @see Parser#nonterm_EXPR()
	 * @see Parser#nonterm_Color()
	 */
	private FormePrimitive nonterm_FORM_POLYGON() throws Exception {
		term(Sym.OPEN_ACCOLADE);
		LinkedList<Point> l = new LinkedList<Point>();
		l = nonterm_MultiplePoint(l);
		Color fill = Color.WHITE;
		Color stroke = Color.BLACK;
		if (check(Sym.COLOR) || check(Sym.DEF_NAME)) {
			fill = nonterm_Color();
			if (check(Sym.COLOR) || check(Sym.DEF_NAME)) {
				stroke = nonterm_Color();
			}
		}
		term(Sym.CLOSE_ACCOLADE);
		return new FormePrimitive(l, fill, stroke);

	}

	/**
	 * Regle Point
	 * 
	 * @see Parser#term(int)
	 * @see Parser#nonterm_EXPR()
	 * @see Point
	 * @see Parser#ptsArgs
	 */
	private Point nonterm_Point() throws Exception {
		term(Sym.OPEN_POINT);
		int x = (int) nonterm_EXPR().eval();
		term(Sym.COMMA);
		int y = (int) nonterm_EXPR().eval();
		term(Sym.CLOSE_POINT);
		Point p = new Point(x, y);
		return p;
	}

	/**
	 * Regle MultiplePoint
	 * 
	 * @see Parser#nonterm_Point()
	 */
	private LinkedList<Point> nonterm_MultiplePoint(LinkedList<Point> l)
			throws Exception {
		l.add(nonterm_Point());
		if (check(Sym.OPEN_POINT)) {
			nonterm_MultiplePoint(l);
		}
		return l;

	}

	/**
	 * Regle Color
	 * 
	 * @see Parser#term(int)
	 * @see Parser#term_value(int)
	 * @see Color
	 */
	private Color nonterm_Color() throws Exception {
		term(Sym.COLOR);
		term(Sym.OPEN_ACCOLADE);
		int red = (int) nonterm_EXPR().eval();
		term(Sym.COMMA);
		int green = (int) nonterm_EXPR().eval();
		term(Sym.COMMA);
		int blue = (int) nonterm_EXPR().eval();
		term(Sym.CLOSE_ACCOLADE);
		return new Color(red, green, blue);

	}

	/**
	 * Regle Def
	 * 
	 * @see Parser#term_value(int)
	 * @see Parser#getDefinition()
	 * @see Parser#definitions
	 */
	private void nonterm_DEF() throws Exception {
		term(Sym.DEF);
		String def_name = term_value(Sym.DEF_NAME);
		String def_value = getDefinition();
		definitions.put(def_name, def_value);

	}

	/**
	 * Regle WRITE
	 * 
	 * @see Parser#term_value(int)
	 * @see Parser#titre
	 */
	private void nonterm_WRITE() throws Exception {
		titre = term_value(Sym.WRITE);
		titre += term_value(Sym.EXTENSION);
	}

	/**
	 * Regle EXPR
	 * 
	 * @see Parser#term(int)
	 * @see Parser#term_value(int)
	 * @see Parser#nontermEXPR2(Expression)
	 * @see Expression
	 * @see Int
	 * @see Decimal
	 * @see Minus
	 */
	private Expression nonterm_EXPR() throws Exception {
		if (check(Sym.MOINS)) {
			term(Sym.MOINS);
			Expression e = nonterm_EXPR();
			return new Minus(new Int(0), e);
		}
		if (check(Sym.INT)) {
			String s = term_value(Sym.INT);
			Expression e = null;
			if (check(Sym.POINT)) {
				term(Sym.POINT);
				s += "." + term_value(Sym.INT);
				e = new Decimal(s);
			} else {
				e = new Int(s);
			}
			return nontermEXPR2(e);
		} else if (check(Sym.OPEN_PARENTHESE)) {
			term(Sym.OPEN_PARENTHESE);
			Expression e = nonterm_EXPR();
			term(Sym.CLOSE_PARENTHESE);
			return nontermEXPR2(e);
		}
		throw new Exception("Erreur : Expression non reconnue");
	}

	/**
	 * Regle EXPR2
	 * 
	 * @see Parser#nontermOP(Expression)
	 * @see Expression
	 */
	private Expression nontermEXPR2(Expression e) throws Exception {
		if (check(Sym.PLUS) || check(Sym.MOINS) || check(Sym.MULT)
				|| check(Sym.DIV)) {
			Expression e2 = nontermOP(e);
			return nontermEXPR2(e2);
		}
		return e;
	}

	/**
	 * Regle OP
	 * 
	 * @see Parser#term(int)
	 * @see Parser#nontermEXPR(Expression)
	 * @see Expression
	 * @see Plus
	 * @see Mult
	 * @see Minus
	 * @see Div
	 */
	private Expression nontermOP(Expression e) throws Exception {
		Expression r = null;
		Sym s = reader.getCurrent().getId();
		switch (s) {
		case PLUS:
			term(Sym.PLUS);
			r = new Plus(e, nonterm_EXPR());
			return r;
		case MOINS:
			term(Sym.MOINS);
			r = new Minus(e, nonterm_EXPR());
			return r;
		case MULT:
			term(Sym.MULT);
			r = new Mult(e, nonterm_EXPR());
			return r;
		case DIV:
			term(Sym.DIV);
			r = new Div(e, nonterm_EXPR());
			return r;
		default:
			throw new Exception("Erreur : Operateur non reconnu");
		}
	}

	/**
	 * Regle TRANSLATION
	 * 
	 * @see Parser#term(int)
	 * @see Parser#term_value(int)
	 * @see Transformation#translation(Point, int, int)
	 */
	private void nonterm_TRANSLATION() throws Exception {

		term(Sym.MOVE);
		String name = term_value(Sym.DEF_NAME);
		if (definitions.containsKey(name)) {

			term(Sym.AT);
			Point p = nonterm_Point();

			StringBuilder sb = new StringBuilder();
			String str =  defRec(definitions.get(name));

			int x = 0;
			int y = 0;

			String f1 = "";
			String f2 = "";

			for (int i = 0; i < str.length(); i++) {

				f1 = "";
				f2 = "";
				sb.append(str.charAt(i));

				if (str.charAt(i) == '<') {

					i += 1;
					while (str.charAt(i) != ',') {
						f1 += str.charAt(i);
						i += 1;
					}

					i += 1;
					while (str.charAt(i) != '>') {
						f2 += str.charAt(i);
						i += 1;
					}

					f1 = f1.trim();
					f2 = f2.trim();

					x = Integer.parseInt(f1);
					y = Integer.parseInt(f2);

					Point p1 = new Point(x, y);
					Point pt = Transformation.translation(p1, p.getX(),
							p.getY());
					sb.append(pt.getX()).append(',').append(pt.getY())
							.append('>');
				}

			}

			definitions.put(name, sb.toString());

		} else {
			throw new Exception("pomme");
		}

	}

	/**
	 * Regle SYMETRIE
	 * 
	 * @see Parser#term(int)
	 * @see Parser#term_value(int)
	 * @see Transformation#reflection(Point, Point, Point)
	 */
	private void nonterm_SYMETRIE() throws Exception {
		term(Sym.REFLECT);
		String name = term_value(Sym.DEF_NAME);
		if (definitions.containsKey(name)) {

			term(Sym.FROM);
			Point p1 = nonterm_Point();
			Point p2 = nonterm_Point();

			StringBuilder sb = new StringBuilder();
			String str =  defRec(definitions.get(name));

			int x = 0;
			int y = 0;

			String f1 = "";
			String f2 = "";

			for (int i = 0; i < str.length(); i++) {

				f1 = "";
				f2 = "";
				sb.append(str.charAt(i));

				if (str.charAt(i) == '<') {

					i += 1;
					while (str.charAt(i) != ',') {
						f1 += str.charAt(i);
						i += 1;
					}

					i += 1;
					while (str.charAt(i) != '>') {
						f2 += str.charAt(i);
						i += 1;
					}

					f1 = f1.trim();
					f2 = f2.trim();

					x = Integer.parseInt(f1);
					y = Integer.parseInt(f2);

					Point p = new Point(x, y);
					Point pt = Transformation.reflection(p, p1, p2);
					sb.append(pt.getX()).append(',').append(pt.getY())
							.append('>');
				}
			}

			definitions.put(name, sb.toString());

		} else {
			throw new Exception("pomme");
		}

	}

	/**
	 * Regle ROTATION
	 * 
	 * @see Parser#term(int)
	 * @see Parser#term_value(int)
	 * @see Transformation#rotation(Point, Point, int)
	 */
	private void nonterm_ROTATION() throws Exception {
		term(Sym.ROTATE);
		String name = term_value(Sym.DEF_NAME);
		if (definitions.containsKey(name)) {

			term(Sym.OF);
			int d = (int) nonterm_EXPR().eval();

			term(Sym.AROUND);
			Point p1 = nonterm_Point();

			StringBuilder sb = new StringBuilder();
			String str =  defRec(definitions.get(name));

			int x = 0;
			int y = 0;

			String f1 = "";
			String f2 = "";

			for (int i = 0; i < str.length(); i++) {

				f1 = "";
				f2 = "";
				sb.append(str.charAt(i));

				if (str.charAt(i) == '<') {

					i += 1;
					while (str.charAt(i) != ',') {
						f1 += str.charAt(i);
						i += 1;
					}

					i += 1;
					while (str.charAt(i) != '>') {
						f2 += str.charAt(i);
						i += 1;
					}

					f1 = f1.trim();
					f2 = f2.trim();

					x = Integer.parseInt(f1);
					y = Integer.parseInt(f2);

					Point p = new Point(x, y);
					Point pt = Transformation.rotation(p1, p, d);
					sb.append(pt.getX()).append(',').append(pt.getY())
							.append('>');
				}
			}

			definitions.put(name, sb.toString());

		} else {
			throw new Exception("pomme");
		}
	}

	/**
	 * Regle SPIN
	 * 
	 * @see Parser#term(int)
	 * @see Parser#term_value(int)
	 * @see Transformation#rotation(Point, Point, Point)
	 */
	private void nonterm_SPIN() throws Exception {
		term(Sym.SPIN);
		String name = term_value(Sym.DEF_NAME);
		if (definitions.containsKey(name)) {

			String str =  defRec(definitions.get(name));
			if (str.charAt(0) == 'p') {
				term(Sym.OF);
				int d = (int) nonterm_EXPR().eval();

				StringBuilder sb = new StringBuilder();

				int x = 0;
				int y = 0;

				String f1 = "";
				String f2 = "";

				LinkedList<Point> Pts = new LinkedList<Point>();

				for (int i = 0; i < str.length(); i++) {

					f1 = "";
					f2 = "";

					if (str.charAt(i) == '<') {

						i += 1;
						while (str.charAt(i) != ',') {
							f1 += str.charAt(i);
							i += 1;
						}

						i += 1;
						while (str.charAt(i) != '>') {
							f2 += str.charAt(i);
							i += 1;
						}

						f1 = f1.trim();
						f2 = f2.trim();

						x = Integer.parseInt(f1);
						y = Integer.parseInt(f2);

						Pts.add(new Point(x, y));
					}
				}

				int bx = 0;
				int by = 0;

				for (Point e : Pts) {
					bx += e.getX();
					by += e.getY();
				}

				bx /= Pts.size();
				by /= Pts.size();

				Point b = new Point(bx, by);

				for (int i = 0; i < str.length(); i++) {

					sb.append(str.charAt(i));

					if (str.charAt(i) == '<') {

						i += 1;
						while (str.charAt(i) != ',') {
							i += 1;
						}

						i += 1;
						while (str.charAt(i) != '>') {
							i += 1;
						}

						Point pt = Transformation.rotation(b, Pts.pop(), d);
						sb.append(pt.getX()).append(',').append(pt.getY())
								.append('>');
					}

				}

				definitions.put(name, sb.toString());

			} else {
				throw new Exception(
						"Vous ne pouvez pas appliquer une rotation de ce type à une ellipse ou un cercle");
			}

		} else {
			throw new Exception("pomme");
		}
	}

	/**
	 * Regle HOMOTHETIE
	 * 
	 * @see Parser#term(int)
	 * @see Parser#term_value(int)
	 * @see Parser#nonterm_HOMOC(String)
	 * @see Parser#nonterm_HOMOP(String)
	 */
	private void nonterm_HOMOTHETIE() throws Exception {
		term(Sym.EXTEND);
		String name = term_value(Sym.DEF_NAME);
		if (definitions.containsKey(name)) {

			String str =  defRec(definitions.get(name));
			if (str.charAt(0) == 'p') {
				nonterm_HOMOP(name);
			} else {
				nonterm_HOMOC(name);
			}

		} else {
			throw new Exception("pomme");
		}
	}

	/**
	 * Regle HOMOP
	 * 
	 * @see Parser#term(int)
	 * @see Parser#term_value(int)
	 * @see Transformation#homothetie(Point, Point, double)
	 */
	private void nonterm_HOMOP(String name) throws Exception {

		term(Sym.OF);
		double k = nonterm_EXPR().eval();

		term(Sym.FROM);
		Point p1 = nonterm_Point();

		StringBuilder sb = new StringBuilder();
		String str =  defRec(definitions.get(name));

		int x = 0;
		int y = 0;

		String f1 = "";
		String f2 = "";

		for (int i = 0; i < str.length(); i++) {

			f1 = "";
			f2 = "";
			sb.append(str.charAt(i));

			if (str.charAt(i) == '<') {

				i++;
				while (str.charAt(i) != ',') {
					f1 += str.charAt(i);
					i++;
				}

				i++;
				while (str.charAt(i) != '>') {
					f2 += str.charAt(i);
					i++;
				}

				f1 = f1.trim();
				f2 = f2.trim();

				x = Integer.parseInt(f1);
				y = Integer.parseInt(f2);

				Point p = new Point(x, y);
				Point pt = Transformation.homothetie(p1, p, k);
				sb.append(pt.getX()).append(',').append(pt.getY()).append('>');
			}
		}
		definitions.put(name, sb.toString());
	}

	/**
	 * Regle HOMOC
	 * 
	 * @see Parser#term(int)
	 * @see Parser#term_value(int)
	 * @see Transformation#homothetie(Point, Point, double)
	 */
	private void nonterm_HOMOC(String name) throws Exception {

		term(Sym.OF);
		double k = nonterm_EXPR().eval();

		term(Sym.FROM);
		Point p1 = nonterm_Point();

		StringBuilder sb = new StringBuilder();
		String str =  defRec(definitions.get(name));

		int x = 0;
		int y = 0;
		int r = 0;

		String f1 = "";
		String f2 = "";
		String f3 = "";

		for (int i = 0; i < str.length(); i++) {

			f1 = f2 = f3 = "";
			sb.append(str.charAt(i));

			if (str.charAt(i) == '<') {

				i++;
				while (str.charAt(i) != ',') {
					f1 += str.charAt(i);
					i++;
				}

				i++;
				while (str.charAt(i) != '>') {
					f2 += str.charAt(i);
					i++;
				}

				i += 2;
				if (str.charAt(i) == '(') {
					i++;
					while (str.charAt(i) != ')') {
						f3 += str.charAt(i);
						i++;
					}
				}

				f1 = f1.trim();
				f2 = f2.trim();
				f3 = f3.trim();

				x = Integer.parseInt(f1);
				y = Integer.parseInt(f2);
				r = Integer.parseInt(f3);

				Point p = new Point(x, y);
				Point pt = Transformation.homothetie(p1, p, k);
				sb.append(pt.getX()).append(',').append(pt.getY()).append('>')
						.append('(').append(r * k).append(')');
			}
		}
		definitions.put(name, sb.toString());
	}

	/**
	 * Regle GROWTH
	 * 
	 * @see Parser#term(int)
	 * @see Parser#term_value(int)
	 * @see Parser#nonterm_GROC(String)
	 * @see Parser#nonterm_GROP(String)
	 */
	private void nonterm_GROWTH() throws Exception {
		term(Sym.GROW);
		String name = term_value(Sym.DEF_NAME);
		if (definitions.containsKey(name)) {

			String str =  defRec(definitions.get(name));
			if (str.charAt(0) == 'p') {
				nonterm_GROP(name);
			} else {
				nonterm_GROC(name);
			}

		} else {
			throw new Exception("pomme");
		}
	}

	/**
	 * Regle GROP
	 * 
	 * @see Parser#term(int)
	 * @see Parser#term_value(int)
	 * @see Transformation#homothetie(Point, Point, double)
	 */
	private void nonterm_GROP(String name) throws Exception {

		term(Sym.OF);
		double d = nonterm_EXPR().eval();

		StringBuilder sb = new StringBuilder();
		String str =  defRec(definitions.get(name));

		int x = 0;
		int y = 0;

		String f1 = "";
		String f2 = "";

		LinkedList<Point> Pts = new LinkedList<Point>();

		for (int i = 0; i < str.length(); i++) {

			f1 = "";
			f2 = "";

			if (str.charAt(i) == '<') {

				i += 1;
				while (str.charAt(i) != ',') {
					f1 += str.charAt(i);
					i += 1;
				}

				i += 1;
				while (str.charAt(i) != '>') {
					f2 += str.charAt(i);
					i += 1;
				}

				f1 = f1.trim();
				f2 = f2.trim();

				x = Integer.parseInt(f1);
				y = Integer.parseInt(f2);

				Pts.add(new Point(x, y));
			}
		}

		int bx = 0;
		int by = 0;

		for (Point e : Pts) {
			bx += e.getX();
			by += e.getY();
		}

		bx /= Pts.size();
		by /= Pts.size();

		Point b = new Point(bx, by);

		for (int i = 0; i < str.length(); i++) {

			sb.append(str.charAt(i));

			if (str.charAt(i) == '<') {

				i += 1;
				while (str.charAt(i) != ',') {
					i += 1;
				}

				i += 1;
				while (str.charAt(i) != '>') {
					i += 1;
				}

				Point pt = Transformation.homothetie(b, Pts.pop(), d);
				sb.append(pt.getX()).append(',').append(pt.getY()).append('>');
			}
		}

		definitions.put(name, sb.toString());

	}

	/**
	 * Regle GROC
	 * 
	 * @see Parser#term(int)
	 * @see Parser#term_value(int)
	 */
	private void nonterm_GROC(String name) throws Exception {

		term(Sym.OF);
		double k = nonterm_EXPR().eval();

		StringBuilder sb = new StringBuilder();
		String str =  defRec(definitions.get(name));

		int r = 0;

		String f = "";

		for (int i = 0; i < str.length(); i++) {

			f = "";
			sb.append(str.charAt(i));

			if (str.charAt(i) == '(') {
				i++;
				while (str.charAt(i) != ')') {
					f += str.charAt(i);
					i++;
				}

				f = f.trim();

				r = Integer.parseInt(f);
				sb.append(r * k).append(')');
			}

		}

		definitions.put(name, sb.toString());
	}

	/**
	 * Permet de modifier les couleurs de remplissage et de contour d'un figure
	 * 
	 * @see Parser#term(int)
	 * @see Parser#term_value(int)
	 * @see Parser#defRec(String)
	 */
	private void nonterm_RECOLOR() throws Exception {
		term(Sym.RECOLOR);
		String name = term_value(Sym.DEF_NAME);
		if (definitions.containsKey(name)) {

			term(Sym.IN);
			Color fill = nonterm_Color();
			term(Sym.AND);
			Color stroke = nonterm_Color();

			StringBuilder sb = new StringBuilder();
			String str =  defRec(definitions.get(name));

			for (int i = 0; i < str.length(); i++) {

				sb.append(str.charAt(i));

				if (str.charAt(i+1) == '}'){
					sb.append("color{"+fill.getRed()+","+fill.getGreen()+","+fill.getBlue()+"} ").append("color{"+stroke.getRed()+","+stroke.getGreen()+","+stroke.getBlue()+"} }");
					break;
				} else if (str.charAt(i+1) == 'c' && str.charAt(i+2) == 'o' ) {
					sb.append("color{"+fill.getRed()+","+fill.getGreen()+","+fill.getBlue()+"} ").append("color{"+stroke.getRed()+","+stroke.getGreen()+","+stroke.getBlue()+"} }");
					break;
				}

			}

			definitions.put(name, sb.toString());

		} else {
			throw new Exception("pomme");
		}

	}
    /**
    * Permet de donner la valeur d'une definition
    *
    * @see String#length()
    * @see Character#isWhitespace(char)
    *
    * return la definition
    */
	private String defRec(String s) throws Exception {
		int c = 0;
		String r = "";
		String fin = "";
		String def = "";
		boolean flag = false;
		while (c != s.length()) {
			if (flag) {
				if (Character.isWhitespace(s.charAt(c))) {
					flag = false;
					if (definitions.containsKey(def)) {
						r += defRec(definitions.get(def)) + " ";
						def = "";
					} else {
						throw new Exception("Variable " + def + " non défini");
					}
				} else {
					def += s.charAt(c);
				}
			} else {
				if (s.charAt(c) == '$') {
					flag = true;
				} else {
					r += s.charAt(c);
				}
			}
			c++;
		}
		return r;
	}

}
