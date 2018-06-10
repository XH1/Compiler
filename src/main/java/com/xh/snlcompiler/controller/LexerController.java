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
            s = gson.toJson(result.getResult());
            System.out.println(s);
        } else {
            System.err.println("´Ê·¨·ÖÎö´íÎó£º");
            result.getErrs().forEach(System.err::println);
            s = gson.toJson(result.getErrs());
        }
        return s;
    }
}
