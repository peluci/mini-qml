package syntatic;

import lexical.Lexeme;
import lexical.LexicalAnalysis;
import lexical.TokenType;

public class SyntaticAnalysis {

    private LexicalAnalysis lex;
    private Lexeme current;

    public SyntaticAnalysis(LexicalAnalysis lex) {
        this.lex = lex;
        this.current = lex.nextToken();
    }

    public void start() {
        procCode();
        eat(TokenType.END_OF_FILE);
    }

    private void advance() {
         // System.out.println("Advanced (\"" + current.token + "\", " +
         // current.type + ")");
         current = lex.nextToken();
    }

    private void eat(TokenType type) {
        //System.out.println("Expected (..., " + type + "), found (\"" +
                //current.token + "\", " + current.type + ")");
        if (type == current.type) {
            current = lex.nextToken();
        } else {
            showError();
        }
    }

    private void showError() {
        System.out.printf("Não");
        System.exit(1);
    }

    // <code> ::= {<object>}
    private void procCode() {
        if (current.type == TokenType.NAME) {
            eat(TokenType.NAME);
            advance();
            procObject();
            eat(TokenType.CLOSE_CUR);
        } else {
            showError();
        }
    }

    // <object> ::= <name> ‘{‘ <object> | <name-value> ‘}’
    private void procObject() {
        while (current.type == TokenType.NAME){
            eat(TokenType.NAME);
            if (current.type == TokenType.OPEN_CUR) {
                advance();
                procObject();
                eat(TokenType.CLOSE_CUR);
            }
            else if (current.type == TokenType.COLON) {
                advance();
                procName_Value();
            }
            else if (current.type == TokenType.COMMA){
            }
            else {
                showError();
            }
        }
    }

    // <name-value> ::= <name> ‘:’ <value>
    private void procName_Value() {
        procValue();
    }

    // <value> ::= <number> | <text> | <name> | <list>
    private void procValue() {
        if (current.type == TokenType.NUMBER) {
            eat(TokenType.NUMBER);
        } else if (current.type == TokenType.NAME) {
            eat(TokenType.NAME);
        } else if (current.type == TokenType.TEXT) {
            eat(TokenType.TEXT);
        } else if (current.type == TokenType.OPEN_BRA) {
            procList();
        } else {
            showError();
        }
    }

    // <list> ::= ‘[‘ [ <list-element> { ‘,’ <list-element> } ] ‘]’
    private void procList() {
        eat(TokenType.OPEN_BRA);
        if (current.type == TokenType.NUMBER ||
                current.type == TokenType.NAME ||
                current.type == TokenType.TEXT) {
            procLElem();
            while (current.type == TokenType.COMMA) {
                eat(TokenType.COMMA);
                procLElem();
            }
        }
        eat(TokenType.CLOSE_BRA);
    }

    // <list-element> ::= <number> | <name> | <object> | <text>
    private void procLElem() {
        if (current.type == TokenType.NUMBER) {
            eat(TokenType.NUMBER);
        } else if (current.type == TokenType.NAME) {
            procObject();
        } else if (current.type == TokenType.TEXT) {
            eat(TokenType.TEXT);
        } else {
            showError();
        }

    }
}
