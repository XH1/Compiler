package com.xh.snlcompiler.model.lexer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xinghao
 * @descreption LexerCompiler
 * @date 2018/6/4
 */
public class Lexer {
    private int row = 1;
    private int column = 0;
    private static Logger LOG = LoggerFactory.getLogger(Lexer.class);

    private enum State {
        Normal, InId, InNum, InComment, InChar, Error,
        InAssign, InRange, InDot
    }

    private int position = 0;
    private String program;
    private ResultList result;

    public ResultList getLexerResult(String program) {
        this.program = program;
        result = new ResultList();
        if (program == null || program.trim().equals("")) {
            result.addError("源程序不能为空");
            return result;
        }

        Token token = getNextToken();
        while (token != null) {
            result.addToken(token);
            token = getNextToken();
        }
        return result;
    }

    public Token getNextToken() {
        State state = State.Normal;
        StringBuilder sb = new StringBuilder();
        char ch;
        Token token;
        while (position < program.length()) {
            ch = getChar(position);
            sb.append(ch);
            switch (state) {
                case Normal:
                    System.out.println("position:" + position + "  进入NORMAL");
                    if (isChar(ch)) {
                        state = State.InId;
                    } else if (isDigit(ch)) {
                        state = State.InNum;
                    } else if (isBlank(ch)) {
                        sb.deleteCharAt(sb.length() - 1);
                        state = State.Normal;
                    } else if (ch == '+') {
                        token = new Token(row, column, TokenType.PLUS, sb.toString());
                        System.out.println("识别出Token:" + token);
                        return token;
                    } else if (ch == '-') {
                        token = new Token(row, column, TokenType.MINUS, sb.toString());
                        System.out.println("识别出Token:" + token);
                        return token;
                    } else if (ch == '*') {
                        token = new Token(row, column, TokenType.TIMES, sb.toString());
                        System.out.println("识别出Token:" + token);
                        return token;
                    } else if (ch == '/') {
                        token = new Token(row, column, TokenType.OVER, sb.toString());
                        System.out.println("识别出Token:" + token);
                        return token;
                    } else if (ch == '(') {
                        token = new Token(row, column, TokenType.LPAREN, sb.toString());
                        System.out.println("识别出Token:" + token);
                        return token;
                    } else if (ch == ')') {
                        token = new Token(row, column, TokenType.RPAREN, sb.toString());
                        System.out.println("识别出Token:" + token);
                        return token;
                    } else if (ch == '[') {
                        token = new Token(row, column, TokenType.LMIDPAREN, sb.toString());
                        System.out.println("识别出Token:" + token);
                        return token;
                    } else if (ch == ']') {
                        token = new Token(row, column, TokenType.RMIDPAREN, sb.toString());
                        System.out.println("识别出Token:" + token);
                        return token;
                    } else if (ch == ';') {
                        token = new Token(row, column, TokenType.SEMI, sb.toString());
                        System.out.println("识别出Token:" + token);
                        return token;
                    } else if (ch == ',') {
                        token = new Token(row, column, TokenType.COMMA, sb.toString());
                        System.out.println("识别出Token:" + token);
                        return token;
                    } else if (ch == '=') {
                        token = new Token(row, column, TokenType.EQ, sb.toString());
                        System.out.println("识别出Token:" + token);
                        return token;
                    } else if (ch == '<') {
                        token = new Token(row, column, TokenType.LT, sb.toString());
                        System.out.println("识别出Token:" + token);
                        return token;
                    } else if (ch == ':') {
                        state = State.InAssign;
                    } else if (ch == '{') {
                        sb.deleteCharAt(sb.length() - 1);//把【{】删除
                        state = State.InComment;
                    } else if (ch == '.') {
                        state = State.InDot;
                    } else if (ch == '\'') {
                        sb.deleteCharAt(sb.length() - 1);//把【'】删除
                        state = State.InChar;
                    } else {
                        System.out.println("无法识别的字符");
                        state = State.Error;
                        result.addError("无法识别的字符" + ch + " " + row + ":" + column);
                    }
                    //endregion
                    break;

                case InId://region 识别标识符
                    System.out.println("进入InId状态");
                    if (!isChar(ch) && !isDigit(ch)) {
                        //当前字符不属于标识符，因此回退，下次分析再读
                        goBack();
                        token = new Token(row, column, TokenType.ID, sb.substring(0, sb.length() - 1));
                        System.out.println("已识别Token:" + token);
                        token.checkKeyWords();
                        return token;
                    }
                    break;
                case InNum://region 识别数字
                    System.out.println("开始识别数字");
                    if (!isDigit(ch)) {
                        //数字识别完成
                        goBack();
                        token = new Token(row, column, TokenType.INTC, sb.substring(0, sb.length() - 1));
                        System.out.println("已识别Token:" + token);
                        return token;
                    }
                    //endregion
                    break;
                case InAssign://region 识别赋值符号:=
                    if (ch == '=') {
                        token = new Token(row, column, TokenType.ASSIGN, sb.toString());
                        System.out.println("已识别Token:" + token);
                        return token;
                    } else {
                        state = State.Error;
                    }
                    //endregion
                    break;
                case InComment://region处理注释
                    sb.deleteCharAt(sb.length() - 1);//删除读入的第一个注释中的字符
                    while (ch != '}' && position < program.length()) {

                        ch = getChar(position);
                    }//一直到注释结束，注释结束后继续识别
                    state = State.Normal;
                    if (ch != '}') {
                        System.out.println("期待注释结束符【}】却没有}");
                        state = State.Error;
                        result.addError("注释缺少右括号 } " + row + ":" + column);
                    }
                    //endregion
                    break;
                case InDot:
                    if (isChar(ch)) {//域的点号a.b
                        goBack();
                        sb.deleteCharAt(sb.length() - 1);//把 多读入的字母 删除
                        token = new Token(row, column, TokenType.DOT, sb.toString());
                        System.out.println("已识别Token:" + token);
                        return token;
                    }
                    if (ch == '.') {//下标..
                        state = State.InRange;
                        break;
                    }
                    while (isBlank(ch) && position < program.length()) {
                        ch = getChar(position);
                    }
                    if (position == program.length()) {
                        token = new Token(row, column, TokenType.EOF, ".");
                        System.out.println("已识别Token:" + token);
                        return token;
                    }
                    System.out.println("错误的点号");
                    goBack();
                    state = State.Error;
                    result.addError("错误的点号 " + row + ":" + column);
                    break;
                case InRange://region 下标界限 ..
                    if (isDigit(ch)) {
                        goBack();
                        sb.deleteCharAt(sb.length() - 1);//吧多读入的数字删掉
                        token = new Token(row, column, TokenType.UNDERRANGE, sb.toString());
                        System.out.println("已识别Token:" + token);
                        return token;
                    }
                    state = State.Error;
                    result.addError("错误的下标界限 " + row + ":" + column);
                    //2016-06-09添加。
//                     不加上会导致array [1..] of integer a;类似的语句..]都作为一个下标符号
//                    `..] of integer a;
//                    procedure f(integer x,y;var integer z);
//                    begin
//                    z:=x+y+z;
//                    write(x);
//                    write(y);
//                    write(z)
//                    end
//
//                            begin
//                    x:=|UNDERRANGE|14:8`
                    //endregion
                    break;
                case InChar://region 识别引号括起来的字符
                    if (isChar(ch) || isDigit(ch)) {
                        if (position < program.length()) {
                            ch = getChar(position);
                        }
                        if (ch == '\'') {
                            token = new Token(row, column, TokenType.CHARACTER, sb.toString());
                            System.out.println("已识别Token:" + token);
                            return token;
                        }
                    }
                    state = State.Error;
                    result.addError("char型有误 " + row + ":" + column);
                    //endregion
                    break;
                case Error://region 错误处理 返回空的Token 记录错误信息
                    System.out.println("[Error] Unrecognized token. near " + row + ":" + column);

                    token = new Token();
                    return token;
                //endregion
                default:
                    state = State.Error;
                    result.addError("未知状态");
            }
        }
        if (state == State.InDot) {//文件已结束，且前一个符号是【.】说明程序结束
            token = new Token(row, column, TokenType.EOF, ".");
            System.out.println("已识别Token:" + token);
            return token;
        }
        if (state != State.Normal) {
            result.addError("结束符错误 " + row + ":" + column);
        }
        return null;
    }

    private char getChar(int position) {
        char ch = program.charAt(position);
        checkChar(ch);
        return ch;
    }

    private void checkChar(char ch) {
        position++;
        if (ch == '\n') {
            row++;
            column = 0;
        } else {
            column++;
        }
    }

    private boolean isChar(int ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }

    private boolean isDigit(int ch) {
        return (ch >= '0' && ch <= '9');
    }

    private boolean isBlank(int ch) {
        return ((char) ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r');
    }

    private void goBack() {
        column--;
        position--;
    }
}
