/**
 * Cette classe represente un <b>Jeton</b> un element du langage.
 * @author ELBEZ Samuel & JACQUETTE Pierrick & PERRACHON Quentin
 */
public class Jeton {
		
	private final Sym id;
	private final String contenu;
	
	/**
	 * Cree un <b>Jeton</b> avec les caracteristiques suivantes :
	 * <ul style = "list-style-type:circle">
	 * <li>un symbole id</li>
	 * </ul>
	 * 
	 * @param id
	 *            un symbole representant l'instruction du<b>Jeton</b> cree.	 *
	 * @see Jeton#id
	 * @see Jeton#contenu
	 * 
	 */
	
	public Jeton(Sym id){
		this.id=id;
		this.contenu=null;
	}
	
	/**
	 * Cree un <b>Jeton</b> avec les caracteristiques suivantes :
	 * <ul style = "list-style-type:circle">
	 * <li>un  symbole id</li>
	 * <li>une chaine de charactere contenu</li>
	 * </ul>
	 * 
	 * @param id
	 *            un symbole representant l'instruction du<b>Jeton</b> cree.
	 * @param contenu
	 *            une chaine de charactere representant le contenu du <b>Jeton</b> cree.           
	 *
	 * @see Jeton#id
	 * @see Jeton#contenu
	 * 
	 */
	
	public Jeton(Sym id, String contenu){
		this.id=id;
		this.contenu=contenu;
	}
	
	/**
	 * Retourne la valeur d'un <b>Jeton</b>
	 * 
	 * @return Un Symbol : la valeur du <b>Jeton</b>
	 *         courant.
	 * @see Parser#check(int)
	 * @see Parser#eat(int)
	 * @see Parser#getDefinition()
	 * @see Jeton#id
	 */
	public Sym getId(){
		return id;
	}
	/**
	 * Retourne la valeur du contenu du <b>Jeton</b>
	 * 
	 * @return Une chaine de charactere : la valeur du contenu du <b>Jeton</b>
	 *         courant.
	 * 
	 * @see Parser#term_value(int)
	 * @see Jeton#contenu
	 */
	public String getContenu(){
		return contenu;
	}
			
}
