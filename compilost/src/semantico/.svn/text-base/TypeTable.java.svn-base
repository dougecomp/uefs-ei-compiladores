package semantico;

import lexico.TokenClass;

/**
 *
 * @author Ronaldo Ruiz
 */
public class TypeTable {
    
    
    public static SemanticType getTypeFromTokenClass(TokenClass tc) {
        SemanticType type = SemanticType.TYPE_INDEFINIDO;

        switch (tc) {
            case CARACTERE:
            case KEYWORD_CARACTERE:
                type = SemanticType.TYPE_CARACTERE;
                break;
            case NUMERIC:
            case KEYWORD_INTEIRO:                
                type = SemanticType.TYPE_INTEIRO;
                break;
            case NUMERIC_REAL:
            case KEYWORD_REAL:
                type = SemanticType.TYPE_REAL;
                break;
            case KEYWORD_VERDADEIRO:
            case KEYWORD_FALSO:
            case KEYWORD_LOGICO:
                type = SemanticType.TYPE_LOGICO;
                break;
            case KEYWORD_REGISTRO:
                type = SemanticType.TYPE_REGISTRO;
                break;
            case STRING:
            case KEYWORD_STRING:    
                type = SemanticType.TYPE_STRING;
                break;
            case KEYWORD_VAZIO:
                type = SemanticType.TYPE_VAZIO;
                break;

            default:
                type = SemanticType.TYPE_INDEFINIDO;
                break;

        }


        return type;
    }
    
    public static SemanticType typeOperationAWithB(SymbolTableEntry a, SymbolTableEntry b) {
        return typeOperationAWithB(a.getType(), b.getType());
    }
    
    
    public static SemanticType typeOperationAWithB(SemanticType a, SemanticType b) {
        SemanticType type = SemanticType.TYPE_INDEFINIDO;
        
        switch (a){
            case TYPE_INTEIRO:
                switch (b){
                    case TYPE_REAL:
                        type = SemanticType.TYPE_REAL;
                        break;
                    case TYPE_INTEIRO:
                        type = SemanticType.TYPE_INTEIRO;
                        break;
                    default: 
                        type = SemanticType.TYPE_INDEFINIDO;
                        break;
                }
                break;
                
            case TYPE_REAL:
                switch (b){
                    case TYPE_REAL:
                    case TYPE_INTEIRO:
                        type = SemanticType.TYPE_REAL;
                        break;
                    default: 
                        type = SemanticType.TYPE_INDEFINIDO;
                        break;
                }
                break;
                
            case TYPE_LOGICO: 
                switch (b){
                    case TYPE_LOGICO:
                        type = SemanticType.TYPE_LOGICO;
                        break;
                    default: 
                        type = SemanticType.TYPE_INDEFINIDO;
                        break;
                }
                break;
                
            default: type = SemanticType.TYPE_INDEFINIDO;
        }
        
        return type;
    }
    
    
    
    public static boolean anyIsUndefined(SymbolTableEntry a, SymbolTableEntry b){
        return (a.getType() == SemanticType.TYPE_INDEFINIDO)  || (b.getType() == SemanticType.TYPE_INDEFINIDO);
    }
    
    
    public static boolean matchType(SymbolTableEntry a, SymbolTableEntry b, SemanticType st){
        return (a.getType() == st &&  b.getType() == st);
    }
    

    public static boolean canAReceiveB(SemanticType A, SemanticType B) {
        boolean ret = false;
        switch (A) {
            case TYPE_LOGICO:
                switch (B) {
                    case TYPE_LOGICO:
                        ret = true;
                        break;
                    default:
                        ret = false;
                        break;
                }
                break;


            case TYPE_CARACTERE:
                switch (B) {
                    case TYPE_CARACTERE:
                    case TYPE_STRING:
                        ret = true;
                        break;
                    default:
                        ret = false;
                        break;
                }
                break;
            case TYPE_INTEIRO:
                switch (B) {
                    case TYPE_INTEIRO:
                        ret = true;
                        break;
                    default:
                        ret = false;
                        break;
                }
            case TYPE_REAL:
                switch (B) {
                    case TYPE_REAL:
                    case TYPE_INTEIRO:
                        ret = true;
                        break;
                    default:
                        ret = false;
                        break;
                }
            case TYPE_STRING:
                switch (B) {
                    case TYPE_CARACTERE:
                    case TYPE_STRING:
                        ret = true;
                        break;
                    default:
                        ret = false;
                        break;
                }
            case TYPE_INDEFINIDO:
                ret = true;
                break;
            default:
                ret = false;
                break;
        }
        return ret;
    }
}