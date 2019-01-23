package yal.analyse ;

import java_cup.runtime.*;
import yal.exceptions.AnalyseLexicaleException;
      
%%
   
%class AnalyseurLexical
%public

%line
%column
    
%type Symbol
%eofval{
        return symbol(CodesLexicaux.EOF) ;
%eofval}

%cup

%{

  private StringBuilder chaine ;

  private Symbol symbol(int type) {
	return new Symbol(type, yyline, yycolumn) ;
  }

  private Symbol symbol(int type, Object value) {
	return new Symbol(type, yyline, yycolumn, value) ;
  }
%}

idf = [A-Za-z_][A-Za-z_0-9]*

csteE = [0-9]+
guillemet = [\"]

finDeLigne = \r|\n
espace = {finDeLigne}  | [ \t\f]

commentaireSlashSlash=[/][/].*
commentaireSlashEtoile=[/][*]
commentaireEtoileSlash=[*][/]
%state commentaire
%%

<YYINITIAL> "programme"            { return symbol(CodesLexicaux.PROGRAMME); }
<YYINITIAL> "debut"                { return symbol(CodesLexicaux.DEBUT); }
<YYINITIAL> "fin"              	   { return symbol(CodesLexicaux.FIN); }

<YYINITIAL> "ecrire"               { return symbol(CodesLexicaux.ECRIRE); }

<YYINITIAL> ";"                    { return symbol(CodesLexicaux.POINTVIRGULE); }

<YYINITIAL> {csteE}      	       { return symbol(CodesLexicaux.CSTENTIERE, yytext()); }

<YYINITIAL> {idf}      	           { return symbol(CodesLexicaux.IDF, yytext()); }
<YYINITIAL> {espace}               { }

<YYINITIAL>{commentaireSlashSlash} { }
<YYINITIAL> {commentaireSlashEtoile}   {yybegin(commentaire);}
<commentaire> . { }
<commentaire> \n                      { }
<commentaire> {commentaireEtoileSlash} {yybegin(YYINITIAL);}

.                      { throw new AnalyseLexicaleException(yyline, yycolumn, yytext()) ; }
