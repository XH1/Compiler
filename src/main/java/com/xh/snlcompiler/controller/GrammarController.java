package com.xh.snlcompiler.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xh.snlcompiler.model.grammar1.GrammarParser;
import com.xh.snlcompiler.model.grammar1.TreeNode;
import com.xh.snlcompiler.model.lexer.Lexer;
import com.xh.snlcompiler.model.lexer.ResultList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GrammarController {

    @ResponseBody
    @PostMapping("/grammar")
    public String postLexer(@RequestParam String program) {
        Lexer lexer = new Lexer();
        ResultList result = lexer.getLexerResult(program);

        String jsonResult = "";
        Gson gson;

        if (result.getErrs().size() > 0) {
            System.err.println("¥ ∑®∑÷Œˆ¥ÌŒÛ£∫");
            result.getErrs().forEach(System.err::println);
            gson = new Gson();
            return gson.toJson(result.getErrs());
        } else {
            GrammarParser parser = new GrammarParser();
            parser.setTokenList(result.getResult());
            TreeNode root = parser.getTree();
            if (root != null) {
                gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
                jsonResult = gson.toJson(root);
            } else {
                System.err.println("”Ô∑®∑÷Œˆ¥ÌŒÛ£∫");
                gson = new Gson();
                return gson.toJson(parser.getError());
            }
        }
        return jsonResult;
    }
}