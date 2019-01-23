package yal;

import java.io.*;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

import yal.analyse.AnalyseurLexical;
import yal.analyse.AnalyseurSyntaxique;
import yal.arbre.ArbreAbstrait;
import yal.exceptions.AnalyseException;

public class Yal {
    
    public Yal(String nomFichier) {
        try {
            AnalyseurSyntaxique analyseur = new AnalyseurSyntaxique(new AnalyseurLexical(new FileReader(nomFichier)));
            ArbreAbstrait arbre = (ArbreAbstrait) analyseur.parse().value;

            arbre.verifier() ;
            System.out.println("COMPILATION OK") ;

            String nomSortie = nomFichier.replaceAll("[.]yal", ".mips") ;
            PrintWriter flot = new PrintWriter(new BufferedWriter(new FileWriter(nomSortie))) ;
            flot.println(arbre.toMIPS());
            flot.close() ;
        }
        catch (FileNotFoundException ex) {
            System.err.println("Fichier " + nomFichier + " inexistant") ;
        }
        catch (AnalyseException ex) {
            System.err.println(ex.getMessage());
        }
        catch (Exception ex) {
            Logger.getLogger(Yal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new Yal("/home/mobis/IntelliJIDEAProjects/yal0/src/yalfile/test.yal") ;

    }
    
}
