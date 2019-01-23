package yal.arbre.instructions;

//import yal.arbre.expressions.IDF;

public class Declaration extends Instruction {
    //private IDF idf;
    public Declaration(int ligne)
    {
        super(ligne);
       // this.idf=idf;
    }


    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {

        return null;
    }
}
