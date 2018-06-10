package com.xh.snlcompiler.model.grammar1;

import com.google.gson.annotations.Expose;
import com.xh.snlcompiler.model.grammar1.enums.Type;
import com.xh.snlcompiler.model.lexer.TokenType;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xinghao
 * @descreption 语法树节点
 * @date 2018/6/9
 */
public class TreeNode {

    @Expose
    private List<TreeNode> child = new ArrayList<>();

    private int flag;     // 0 表示叶子节点


    private Type.nonTerminals NonTerminal;

    private TokenType Terminal;

    @Expose
    private String data;

    //用于ztree判断叶节点
    @Expose
    private boolean isParent;

    private int x;
    private int y;
    private int width;
    private int length;

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }

    void setChild(TreeNode node) {
        child.add(node);
    }


    void setflag(int FLAG) {
        flag = FLAG;
    }

    void setData(String DATA) {
        data = DATA;
    }

    void setNonTerminal(Type.nonTerminals nonTerminal) {
        NonTerminal = nonTerminal;
    }


    void setTerminal(TokenType terminal) {
        Terminal = terminal;
    }

    public int getchildNum() {
        return child.size();
    }

    public TreeNode getChild(int num) {
        return child.get(num);
    }

    public int getflag() {
        return flag;
    }

    public Type.nonTerminals getNonTerminal() {
        return NonTerminal;
    }

    public TokenType getTerminal() {
        return Terminal;
    }

    String getData() {
        return data;
    }

    public void setX(int X) {
        x = X;
    }

    public int getX() {
        return x;
    }

    public void setY(int Y) {
        y = Y;
    }

    public int getY() {
        return y;
    }

    public void setWidth(int Width) {
        width = Width;
    }

    public int getWidth() {
        return width;
    }

    public void setLength(int Length) {
        length = Length;
    }

    public int getLength() {
        return length;
    }
}
