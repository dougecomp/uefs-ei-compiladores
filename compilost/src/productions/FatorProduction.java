package productions;

import exceptions.ParserException;
import lexico.ErrorList;
import lexico.TokenList;
import lexico.TokenClass;
import semantico.SemanticStructure;
import semantico.SemanticType;
import semantico.SymbolTableEntry;
import semantico.TypeTable;

/**
 * @author MarconeAssis
 * @author caco
 */
public class FatorProduction extends AbstractProductionRule {

    private static FatorProduction instance = new FatorProduction();

    public static FatorProduction getInstance() {
        return instance;
    }

    /**
     * Regra: fator   	 :    ('!' | op_soma)? ('(' expr ')' | 'NUMERIC' |
     * 'VERDADEIRO' | 'FALSO' | (op_inc)* IDENTIFIER (chamada_funcao)? array* op_inc*| cast);
     * @return
     * @throws ParserException
     */
    @Override
    public SymbolTableEntry analyze(TokenList tl, ErrorList el)  {

        SymbolTableEntry a = new SymbolTableEntry();
        
        //opcionais
        if (testEofAndCurrentToken(tl, TokenClass.OPERATOR_NOT) ){
            tl.consumeCurrent();
            a.setType(SemanticType.TYPE_LOGICO);
        }
        else if (testEofAndCurrentToken(tl, TokenClass.OPERATOR_PLUS) ||
                 testEofAndCurrentToken(tl, TokenClass.OPERATOR_MINUS) ){
            tl.consumeCurrent();
            a.setType(SemanticType.TYPE_INTEIRO);
        }
        
        
        
        if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_OPENING_PARENTHESIS)){
            tl.consumeCurrent();
            
            if (!tl.eof() && ExprProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())){
                SymbolTableEntry b = ExprProduction.getInstance().analyze(tl, el);
                if (a.getType() != SemanticType.TYPE_INDEFINIDO && TypeTable.typeOperationAWithB(a, b)==SemanticType.TYPE_INDEFINIDO){
                    el.addSemanticError("Tipos incompatíveis.");
                }else{
                    a = b;
                }
            }
            else{
                return skipUntilSynchronize(tl, el, "Era esperada uma expressão.");
            }
            
            if (testEofAndCurrentToken(tl, TokenClass.DELIMITER_CLOSING_PARENTHESIS)){
                tl.consumeCurrent();
                return a;
            }
            else{
                return skipUntilSynchronize(tl, el, "Era esperado um ')'.");
            }
            
        }
        
        if (testEofAndCurrentToken(tl, TokenClass.NUMERIC)){
            
            //o que resulta se eu operar o tipo em a com o tipo do token atual?
            SemanticType st = TypeTable.typeOperationAWithB(a.getType(), TypeTable.getTypeFromTokenClass(tl.getCurrent().getTokenClass()));
            
            if (a.getType() == SemanticType.TYPE_INDEFINIDO){
                a.setType(SemanticType.TYPE_INTEIRO);
            }
            //se deu tipo
            else if (st == SemanticType.TYPE_INDEFINIDO){
                el.addSemanticError("Tipos incompatíveis");
            }
            
            a.setToken(tl.consumeCurrent());
            return a;
        }
        
        
        if (testEofAndCurrentToken(tl, TokenClass.NUMERIC_REAL)){
            //o que resulta se eu operar o tipo em a com o tipo do token atual?
            SemanticType st = TypeTable.typeOperationAWithB(a.getType(), TypeTable.getTypeFromTokenClass(tl.getCurrent().getTokenClass()));
            
            if (a.getType() == SemanticType.TYPE_INDEFINIDO || a.getType() == SemanticType.TYPE_INTEIRO){
                a.setType(SemanticType.TYPE_REAL);
            }
            //se deu tipo
            else if (st == SemanticType.TYPE_INDEFINIDO){
                el.addSemanticError("Tipos incompatíveis");
            }
            
            a.setToken(tl.consumeCurrent());
            return a;
        }
        
        
        if (testEofAndCurrentToken(tl, TokenClass.KEYWORD_VERDADEIRO) ||
            testEofAndCurrentToken(tl, TokenClass.KEYWORD_FALSO) ){
            
            //o que resulta se eu operar o tipo em a com o tipo do token atual?
            SemanticType st = TypeTable.typeOperationAWithB(a.getType(), TypeTable.getTypeFromTokenClass(tl.getCurrent().getTokenClass()));
            
            if (a.getType() == SemanticType.TYPE_INDEFINIDO){
                a.setType(SemanticType.TYPE_LOGICO);
            }
            //se deu tipo
            else if (st == SemanticType.TYPE_INDEFINIDO){
                el.addSemanticError("Tipos incompatíveis");
            }
                
            a.setToken(tl.consumeCurrent());
            return a;
        }
        
                
        if (!tl.eof() && CastProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())){
            SymbolTableEntry b = CastProduction.getInstance().analyze(tl, el);
            if (a.getType() != SemanticType.TYPE_INDEFINIDO && TypeTable.typeOperationAWithB(a, b)==SemanticType.TYPE_INDEFINIDO){
                    el.addSemanticError("Tipos incompatíveis.");
            }else{
                    a = b;
            }
            return  a;
        }


        //opcionais multiplos
        while (!tl.eof() && OpIncProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            OpIncProduction.getInstance().analyze(tl, el);
        }
        
        //obrigatorio
        if (testEofAndCurrentToken(tl, TokenClass.IDENTIFIER)){
            tl.consumeCurrent();
            a.setStructure(SemanticStructure.VARIABLE);
        }
        else{
            return skipUntilSynchronize(tl, el, "Esperado um identificador.");
        }
        
        //opcional
        if (!tl.eof() && ChamadaFuncaoProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())){
            ChamadaFuncaoProduction.getInstance().analyze(tl, el);
            a.setStructure(SemanticStructure.FUNCAO);
        }

        //opcional
        while (!tl.eof() && ArrayProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            ArrayProduction.getInstance().analyze(tl, el);
        }
        
        //opcional
        while (!tl.eof() && OpIncProduction.getInstance().isFirst(tl.getCurrent().getTokenClass())) {
            OpIncProduction.getInstance().analyze(tl, el);
        }
        
        return new SymbolTableEntry();
    }

    @Override
    protected void populateFirst() {
        //Revisado por André & Salomão
        first.add(TokenClass.OPERATOR_NOT);
        first.add(TokenClass.DELIMITER_OPENING_PARENTHESIS);
        first.add(TokenClass.NUMERIC);
        first.add(TokenClass.NUMERIC_REAL);
        first.add(TokenClass.KEYWORD_VERDADEIRO);
        first.add(TokenClass.KEYWORD_FALSO);
        first.add(TokenClass.IDENTIFIER);

        //from opsoma first
        this.first.add(TokenClass.OPERATOR_PLUS);
        this.first.add(TokenClass.OPERATOR_MINUS);

        //from opinc first
        first.add(TokenClass.OPERATOR_INCREMENT);
        first.add(TokenClass.OPERATOR_DECREMENT);

        //from cast first
        this.first.add(TokenClass.KEYWORD_CAST);
    }

    protected void populateFollow() {


        //from opmult first
        this.follow.add(TokenClass.OPERATOR_MULT);
        this.follow.add(TokenClass.OPERATOR_DIVISION);

        //from termo follow
        follow.add(TokenClass.DELIMITER_CLOSING_SQUARE_BRACKET);
        this.follow.add(TokenClass.OPERATOR_LESS);
        this.follow.add(TokenClass.OPERATOR_LESS_EQUAL);
        this.follow.add(TokenClass.OPERATOR_MORE);
        this.follow.add(TokenClass.OPERATOR_MORE_EQUAL);
        this.follow.add(TokenClass.OPERATOR_DIFFERENT);
        this.follow.add(TokenClass.OPERATOR_EQUAL);
        follow.add(TokenClass.OPERATOR_AND);
        follow.add(TokenClass.DELIMITER_COMMA);
        follow.add(TokenClass.DELIMITER_CLOSING_PARENTHESIS);
        follow.add(TokenClass.DELIMITER_SEMICOLON);
        follow.add(TokenClass.DELIMITER_CLOSING_CURLY_BRACKET);
        follow.add(TokenClass.DELIMITER_CLOSING_PARENTHESIS);
        follow.add(TokenClass.DELIMITER_SEMICOLON);
        follow.add(TokenClass.OPERATOR_OR);
        this.follow.add(TokenClass.OPERATOR_PLUS);
        this.follow.add(TokenClass.OPERATOR_MINUS);
    }
}
