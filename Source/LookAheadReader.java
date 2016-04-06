import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Stack;


/**
 * Cette classe permet de lire le flot de Jeton avec la possibilite de regarder un Jeton a l'avance
 * @author ELBEZ Samuel & JACQUETTE Pierrick & PERRACHON Quentin
 *
 */
public class LookAheadReader {
	private Jeton cur;
	private Lexer lexer;
	private Stack<Lexer> def=new Stack<Lexer>();
	/**
	 * Cree un LookAheadReader a partir du lexer en argument
	 * @param l Le lexer
	 */
	public LookAheadReader(Lexer l) throws Exception{
		lexer=l;
		cur=lexer.yylex();
	}
	/**
	 * Verifie si le Jeton actuel correspond au bon Symbole
	 * @param Un symbole s
	 */
	public boolean check(Sym s) throws Exception{
		return cur.getId()==s;
	}
	
	/**
	 * Mange le Jeton courant s'il est bien du symbole s, sinon, renvoie une exception
	 * @param Un symbole s
	 */
	public void eat(Sym s) throws Exception{
		if(!check(s)){
			throw new Exception("Impossible de manger "+s+", l'actuel est "+cur.getId());
		}
		//System.out.println(cur.getId()+" "+cur.getContenu());
		if(!def.isEmpty()){
			cur=def.peek().yylex();
			while(cur.getId()==Sym.EOF){
				if(!def.isEmpty()){
					def.pop();
					if(!def.isEmpty()){
						cur=def.peek().yylex();
					}
					else{
						cur=lexer.yylex();
					}
				}
			}
		}else{
			Sym j = cur.getId();
			if(j==Sym.DEF || j==Sym.MOVE || j==Sym.REFLECT || j==Sym.ROTATE || j==Sym.EXTEND || j==Sym.GROW || j==Sym.SPIN || j==Sym.RECOLOR){
				cur=lexer.yylex();
			}else{
				cur=lexer.yylex();
				verDef();
			}
		}
	}
	
	/**
	 * Permet d'avoir la valeur du Jeton actuelle
	 * @return la valeur du Jeton actuelle
	 */
	public String getValue() throws Exception{
		String s = cur.getContenu();
		if(s!=null){
			return s;
		}
		else{
			throw new Exception("Erreur : le token actuel n'est pas un entier");
		}
	}
	
	/**
	 * Retourne le Jeton actuelle
	 * @return Le jeton actuelle;
	 */
	public Jeton getCurrent(){
		return cur;
	}
	
	public void setDef(Lexer def) throws Exception{
		this.def.push(def);
		cur=this.def.peek().yylex();
	}
	
	private void verDef() throws Exception{
		if (check(Sym.DEF_NAME)) {
			String s = cur.getContenu();
			Reader r = new StringReader(Parser.definitions.get(s));
			Lexer def = new Lexer(r);
			setDef(def);
			if(check(Sym.DEF_NAME)){
				verDef();
			}
		}
	}
}
