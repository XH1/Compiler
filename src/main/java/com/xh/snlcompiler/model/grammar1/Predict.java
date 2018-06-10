package com.xh.snlcompiler.model.grammar1;

import com.xh.snlcompiler.model.lexer.TokenType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xinghao
 * @descreption predict
 * @date 2018/6/9
 */
public class Predict {
    List<TokenType> predict = new ArrayList<>();

    public void setPredict(TokenType pre) {
        predict.add(pre);
    }

    public int getPredictNum() {
        return predict.size();
    }

    public TokenType getPredict(int number) {
        return predict.get(number);
    }
}
