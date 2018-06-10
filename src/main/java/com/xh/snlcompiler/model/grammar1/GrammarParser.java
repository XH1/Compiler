package com.xh.snlcompiler.model.grammar1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xh.snlcompiler.model.grammar1.chart.SNLPredict;
import com.xh.snlcompiler.model.grammar1.chart.SNLProducer;
import com.xh.snlcompiler.model.grammar1.enums.Type;
import com.xh.snlcompiler.model.lexer.Token;
import com.xh.snlcompiler.model.lexer.TokenType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xinghao
 * @descreption �ݹ��½��﷨������
 * @date 2018/6/9
 */
public class GrammarParser {
    private SNLProducer productions;
    private SNLPredict predictions;
    private List<Token> tokenList;
    private int curTokenIndex = 0;
    private String error;

    /**
     * �޲ι��캯��
     */
    public GrammarParser() {
        productions = new SNLProducer();
        predictions = new SNLPredict();
    }

    /**
     * ����﷨��
     *
     * @return ���ڵ�
     */
    public TreeNode getTree() {
        TreeNode root = null;
        if (tokenList != null) {
            root = match(Type.nonTerminals.Program);
        }
        return root;
    }


    /**
     * �ݹ��ƥ��ڵ㣬�ӽڵ����Ƿ��ռ��������ƥ�䣬���ռ�����ֱ�Ӽ����﷨��
     *
     * @param nonTerminal Ҫƥ��ķ��ռ���
     * @return ���ظ��ڵ�
     */
    public TreeNode match(Type.nonTerminals nonTerminal) {
        int i, j, choose = -1;
        TreeNode root = new TreeNode();
        Type.nonTerminals temp;
        Token token = tokenList.get(curTokenIndex);

        root.setflag(1);
        root.setNonTerminal(nonTerminal);
        root.setData(nonTerminal.toString());
        root.setParent(true);

        for (i = 1; i <= 104; i++) {
            int flag = 0;
            temp = productions.product[i].getHead();
            for (j = 0; j < predictions.predict[i].getPredictNum(); j++) {
                if (token.getType() == predictions.predict[i].getPredict(j)) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 1 && temp == nonTerminal) {
                choose = i;
                break;
            }
        }

        if (choose == -1) {
            error = "�﷨���� λ�ã�" + token.getRow() + "�У�" + token.getColumn() + "�С�";
            return null;
        } else {
            for (i = 0; i < productions.product[choose].getproductNum(); i++) {
                if (productions.product[choose].getTer(i)) {
                    TreeNode leaf = new TreeNode();
                    //leaf.setFather(father);
                    leaf.setflag(0);
                    leaf.setTerminal(productions.product[choose].getProductTerminal(i));
                    leaf.setData(tokenList.get(curTokenIndex).getValue());
                    leaf.setParent(false);
                    root.setChild(leaf);
                    System.out.println(tokenList.get(curTokenIndex).getValue());
                    curTokenIndex++;
                } else {
                    TreeNode child;
                    Type.nonTerminals NonTerminals = productions.product[choose].getProductNonterminal(i);
                    child = match(NonTerminals);
                    if (child == null && error != null && error.length() > 0) {
                        return null;
                    }
                    root.setChild(child);
                }
            }
        }
        return root;
    }

    /*public int getProduction(Token token, Type.nonTerminals nonTerminal, SNLProducer producer) {
        SNLPredict predict = new SNLPredict();
        int flag = 0;
        for (int i = 1; i <= 104; i++) {
            Type.nonTerminals temp = producer.product[i].getHead();
            for (int j = 0; j < predict.predict[i].getPredictNum(); j++) {
                if (token.getType() == predict.predict[i].getPredict(j)) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 1 && temp == nonTerminal) {
                return i;
            }

        }
        return -1;
    }*/

    public String getError() {
        return error;
    }

    public void setTokenList(List<Token> tokenList) {
        this.tokenList = tokenList;
    }

    private Token getNextToken() {
        Token token = null;
        if (curTokenIndex < tokenList.size()) {
            token = tokenList.get(curTokenIndex);
        }
        return token;
    }
}
