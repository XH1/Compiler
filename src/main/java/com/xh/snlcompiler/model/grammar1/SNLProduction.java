package com.xh.snlcompiler.model.grammar1;

import com.xh.snlcompiler.model.grammar1.enums.Type;
import com.xh.snlcompiler.model.lexer.TokenType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xinghao
 * @descreption ²úÉúÊ½
 * @date 2018/6/9
 */
public class SNLProduction {
    Type.nonTerminals Head;

    class Product {
        boolean isTer;
        Type.nonTerminals nonterminal;
        TokenType terminal;
    }

    List<Product> products = new ArrayList<>();

    public void setHead(Type.nonTerminals head) {
        Head = head;
    }

    public void setProduction(Type.nonTerminals nonterminal) {
        Product p = new Product();
        p.nonterminal = nonterminal;
        p.isTer = false;
        products.add(p);
    }

    public void setProduction(TokenType terminal) {
        Product p = new Product();
        p.terminal = terminal;
        p.isTer = true;
        products.add(p);
    }

    Type.nonTerminals getHead() {
        return Head;
    }

    int getproductNum() {
        return products.size();
    }

    boolean getTer(int number) {
        return products.get(number).isTer;
    }

    Type.nonTerminals getProductNonterminal(int number) {
        return products.get(number).nonterminal;
    }

    TokenType getProductTerminal(int number) {
        return products.get(number).terminal;
    }
}
