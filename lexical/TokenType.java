package lexical;

public enum TokenType {
    // SPECIALS
    UNEXPECTED_EOF,
    INVALID_TOKEN,
    END_OF_FILE,

    // SYMBOLS
    COMMA,         // ,
    COLON,         // :
    OPEN_CUR,      // {
    CLOSE_CUR,     // }
    OPEN_BRA,      // [
    CLOSE_BRA,     // ]

    // OTHERS
    NAME,          // identifier
    NUMBER,        // integer
    TEXT           // string

};
