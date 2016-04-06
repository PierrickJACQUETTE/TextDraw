%%
%public
%class Lexer
%unicode
%type Jeton
%line
%column

%{
    
%}

%yylexthrow{
    Exception
%yylexthrow}

blank = "\n" | "\r" | " " | "\t"
nombre   = [1-9][0-9]* | "0"
variable = [a-z][a-zA-Z0-9]*

%state YYDEF
%state YYWRITE

%%

<YYINITIAL> {

   "draw" {return new Jeton(Sym.DRAW,yytext());}
   "polygon" {return new Jeton(Sym.FORM_POLYGON,yytext());}
   "circle" {return new Jeton(Sym.FORM_CIRCLE,yytext());}
   "ellipse" {return new Jeton(Sym.FORM_ELLIPSE,yytext());}
   [{] {return new Jeton(Sym.OPEN_ACCOLADE,yytext());}
   [}] {return new Jeton(Sym.CLOSE_ACCOLADE,yytext());}
   [<] {return new Jeton(Sym.OPEN_POINT,yytext());}
   [>] {return new Jeton(Sym.CLOSE_POINT,yytext());}
   {nombre} {return new Jeton(Sym.INT,yytext());}
   [,] {return new Jeton(Sym.COMMA,yytext());}
   "color" {return new Jeton(Sym.COLOR,yytext());}
   "def" {return new Jeton(Sym.DEF,yytext());}
   [$] {yybegin(YYDEF);}
   "write" {yybegin(YYWRITE);}
   [+] {return new Jeton(Sym.PLUS,yytext());}
   [-] {return new Jeton(Sym.MOINS,yytext());} 
   [*] {return new Jeton(Sym.MULT,yytext());} 
   [/] {return new Jeton(Sym.DIV,yytext());} 
   [(] {return new Jeton(Sym.OPEN_PARENTHESE,yytext());} 
   [)] {return new Jeton(Sym.CLOSE_PARENTHESE,yytext());}    
   "move" {return new Jeton(Sym.MOVE,yytext());}
   "reflect" {return new Jeton(Sym.REFLECT,yytext());}
   "rotate" {return new Jeton(Sym.ROTATE,yytext());}
   "recolor" {return new Jeton(Sym.RECOLOR,yytext());}
   "extend" {return new Jeton(Sym.EXTEND,yytext());}
   "at" {return new Jeton(Sym.AT,yytext());}
   "in" {return new Jeton(Sym.IN,yytext());}
   "and" {return new Jeton(Sym.AND,yytext());}
   "from" {return new Jeton(Sym.FROM,yytext());}
   "around" {return new Jeton(Sym.AROUND,yytext());} 
   "of" {return new Jeton(Sym.OF,yytext());}
   "grow" {return new Jeton(Sym.GROW,yytext());}
   "spin" {return new Jeton(Sym.SPIN,yytext());}
   [.] {return new Jeton(Sym.POINT,yytext());}
   [;] {return new Jeton(Sym.SEMI,yytext());}
   {blank}  {}
   <<EOF>>  {return new Jeton(Sym.EOF);}
   [^]      {throw new Exception("Unexpected character at "+yytext());}
}

<YYDEF> {
    {variable} {yybegin(YYINITIAL); return new Jeton(Sym.DEF_NAME,yytext());}
}

<YYWRITE> {
    {blank}  {}
    [a-zA-Z0-9]+ {return new Jeton(Sym.WRITE,yytext());}
    ".ppm" {yybegin(YYINITIAL);return new Jeton(Sym.EXTENSION,yytext());}
    ".svg" {yybegin(YYINITIAL);return new Jeton(Sym.EXTENSION,yytext());}
    
}
