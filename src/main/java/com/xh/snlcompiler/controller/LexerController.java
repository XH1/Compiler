package com.xh.snlcompiler.controller;

import com.google.gson.Gson;
import com.xh.snlcompiler.model.lexer.Lexer;
import com.xh.snlcompiler.model.lexer.ResultList;
import com.xh.snlcompiler.model.lexer.Token;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LexerController {
    @RequestMapping("/")
    public String root() {
        return "lexer";
    }

    @RequestMapping("/lexer")
    public String lexer() {
        return "lexer";
    }

    @ResponseBody
    @PostMapping("/lexer")
    public String postLexer(@RequestParam String program) {
        Lexer lexer = new Lexer();
        Gson gson = new Gson();
        String s;
        ResultList result = lexer.getLexerResult(program);
        if (result.getErrs().size() == 0) {
            /*List<Token> list = result.getResult();

            if (list.size() > 0) {
                System.out.printf("[ 行:列 ]|【 语义信息 】| 词法信息 \n");
                System.out.printf("---------+--------------+----------\n");
            }
            for (Token t : list) {
                System.out.printf("[%3d:%-3d]|【%10s】|%10s\n", t.getRow(), t.getColumn(), t.getValue(), t.getType().getStr());
            }
            if (list.size() > 0) {
                System.out.printf("---------+--------------+----------\n");
                System.out.printf("[ 行:列 ]|【 语义信息 】| 词法信息 \n");
            }*/


            s = gson.toJson(result.getResult());
            System.out.println(s);
        } else {
            System.err.println("词法分析错误：");
            result.getErrs().forEach(System.err::println);
            s = gson.toJson(result.getErrs());
        }
        return s;
    }
}
