package com.xh.snlcompiler.model.lexer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xinghao
 * @descreption ´Ê·¨·ÖÎöÊä³ö
 * @date 2018/6/4
 */
public class ResultList {
    private List<Token> result = new ArrayList<>();
    private List<String> errs = new ArrayList<>();

    public List<Token> getResult() {
        return result;
    }

    public void addToken(Token token) {
        getResult().add(token);
    }

    public List<String> getErrs() {
        return errs;
    }

    public void addError(String err) {
        getErrs().add(err);
    }
}
