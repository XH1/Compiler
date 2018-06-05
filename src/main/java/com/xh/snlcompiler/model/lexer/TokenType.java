package com.xh.snlcompiler.model.lexer;

/**
 * @author xinghao
 * @descreption Token����
 * @date 2018/6/4
 */
public enum TokenType {
    EOF("."), ERROR("error"), EMPTY(""),

    /*������*/
    PROGRAM("program"), PROCEDURE("procedure"), TYPE("type"), VAR("var"),
    IF("if"), THEN("then"), ELSE("else"), FI("fi"),
    WHILE("while"), DO("do"), ENDWH("endwh"),
    BEGIN("begin"), END("end"), READ("read"), WRITE("write"),
    ARRAY("array"), OF("of"), RECORD("record"), RETURN("return"),
    /*����*/
    INTEGER("integer"), CHAR("char"),

    ID("id"), INTC("intc"), CHARACTER("character"),

    /*�������*/
    ASSIGN(":="), EQ("="), LT("<"),
    PLUS("+"), MINUS("-"), TIMES("*"), OVER("/"),
    LPAREN("("), RPAREN(")"), LMIDPAREN("["), RMIDPAREN("]"),
    UNDERRANGE(".."), SEMI(";"), COMMA(","), DOT(".");

    TokenType(String str) {
        this.str = str;
    }

    private String str;

    public String getStr() {
        return str;
    }
}
