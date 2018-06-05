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
            result.addError("Դ������Ϊ��");
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
                    System.out.println("position:" + position + "  ����NORMAL");
                    if (isChar(ch)) {
                        state = State.InId;
                    } else if (isDigit(ch)) {
                        state = State.InNum;
                    } else if (isBlank(ch)) {
                        sb.deleteCharAt(sb.length() - 1);
                        state = State.Normal;
                    } else if (ch == '+') {
                        token = new Token(row, column, TokenType.PLUS, sb.toString());
                        System.out.println("ʶ���Token:" + token);
                        return token;
                    } else if (ch == '-') {
                        token = new Token(row, column, TokenType.MINUS, sb.toString());
                        System.out.println("ʶ���Token:" + token);
                        return token;
                    } else if (ch == '*') {
                        token = new Token(row, column, TokenType.TIMES, sb.toString());
                        System.out.println("ʶ���Token:" + token);
                        return token;
                    } else if (ch == '/') {
                        token = new Token(row, column, TokenType.OVER, sb.toString());
                        System.out.println("ʶ���Token:" + token);
                        return token;
                    } else if (ch == '(') {
                        token = new Token(row, column, TokenType.LPAREN, sb.toString());
                        System.out.println("ʶ���Token:" + token);
                        return token;
                    } else if (ch == ')') {
                        token = new Token(row, column, TokenType.RPAREN, sb.toString());
                        System.out.println("ʶ���Token:" + token);
                        return token;
                    } else if (ch == '[') {
                        token = new Token(row, column, TokenType.LMIDPAREN, sb.toString());
                        System.out.println("ʶ���Token:" + token);
                        return token;
                    } else if (ch == ']') {
                        token = new Token(row, column, TokenType.RMIDPAREN, sb.toString());
                        System.out.println("ʶ���Token:" + token);
                        return token;
                    } else if (ch == ';') {
                        token = new Token(row, column, TokenType.SEMI, sb.toString());
                        System.out.println("ʶ���Token:" + token);
                        return token;
                    } else if (ch == ',') {
                        token = new Token(row, column, TokenType.COMMA, sb.toString());
                        System.out.println("ʶ���Token:" + token);
                        return token;
                    } else if (ch == '=') {
                        token = new Token(row, column, TokenType.EQ, sb.toString());
                        System.out.println("ʶ���Token:" + token);
                        return token;
                    } else if (ch == '<') {
                        token = new Token(row, column, TokenType.LT, sb.toString());
                        System.out.println("ʶ���Token:" + token);
                        return token;
                    } else if (ch == ':') {
                        state = State.InAssign;
                    } else if (ch == '{') {
                        sb.deleteCharAt(sb.length() - 1);//�ѡ�{��ɾ��
                        state = State.InComment;
                    } else if (ch == '.') {
                        state = State.InDot;
                    } else if (ch == '\'') {
                        sb.deleteCharAt(sb.length() - 1);//�ѡ�'��ɾ��
                        state = State.InChar;
                    } else {
                        System.out.println("�޷�ʶ����ַ�");
                        state = State.Error;
                        result.addError("�޷�ʶ����ַ�" + ch + " " + row + ":" + column);
                    }
                    //endregion
                    break;

                case InId://region ʶ���ʶ��
                    System.out.println("����InId״̬");
                    if (!isChar(ch) && !isDigit(ch)) {
                        //��ǰ�ַ������ڱ�ʶ������˻��ˣ��´η����ٶ�
                        goBack();
                        token = new Token(row, column, TokenType.ID, sb.substring(0, sb.length() - 1));
                        System.out.println("��ʶ��Token:" + token);
                        token.checkKeyWords();
                        return token;
                    }
                    break;
                case InNum://region ʶ������
                    System.out.println("��ʼʶ������");
                    if (!isDigit(ch)) {
                        //����ʶ�����
                        goBack();
                        token = new Token(row, column, TokenType.INTC, sb.substring(0, sb.length() - 1));
                        System.out.println("��ʶ��Token:" + token);
                        return token;
                    }
                    //endregion
                    break;
                case InAssign://region ʶ��ֵ����:=
                    if (ch == '=') {
                        token = new Token(row, column, TokenType.ASSIGN, sb.toString());
                        System.out.println("��ʶ��Token:" + token);
                        return token;
                    } else {
                        state = State.Error;
                    }
                    //endregion
                    break;
                case InComment://region����ע��
                    sb.deleteCharAt(sb.length() - 1);//ɾ������ĵ�һ��ע���е��ַ�
                    while (ch != '}' && position < program.length()) {

                        ch = getChar(position);
                    }//һֱ��ע�ͽ�����ע�ͽ��������ʶ��
                    state = State.Normal;
                    if (ch != '}') {
                        System.out.println("�ڴ�ע�ͽ�������}��ȴû��}");
                        state = State.Error;
                        result.addError("ע��ȱ�������� } " + row + ":" + column);
                    }
                    //endregion
                    break;
                case InDot:
                    if (isChar(ch)) {//��ĵ��a.b
                        goBack();
                        sb.deleteCharAt(sb.length() - 1);//�� ��������ĸ ɾ��
                        token = new Token(row, column, TokenType.DOT, sb.toString());
                        System.out.println("��ʶ��Token:" + token);
                        return token;
                    }
                    if (ch == '.') {//�±�..
                        state = State.InRange;
                        break;
                    }
                    while (isBlank(ch) && position < program.length()) {
                        ch = getChar(position);
                    }
                    if (position == program.length()) {
                        token = new Token(row, column, TokenType.EOF, ".");
                        System.out.println("��ʶ��Token:" + token);
                        return token;
                    }
                    System.out.println("����ĵ��");
                    goBack();
                    state = State.Error;
                    result.addError("����ĵ�� " + row + ":" + column);
                    break;
                case InRange://region �±���� ..
                    if (isDigit(ch)) {
                        goBack();
                        sb.deleteCharAt(sb.length() - 1);//�ɶ���������ɾ��
                        token = new Token(row, column, TokenType.UNDERRANGE, sb.toString());
                        System.out.println("��ʶ��Token:" + token);
                        return token;
                    }
                    state = State.Error;
                    result.addError("������±���� " + row + ":" + column);
                    //2016-06-09��ӡ�
//                     �����ϻᵼ��array [1..] of integer a;���Ƶ����..]����Ϊһ���±����
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
                case InChar://region ʶ���������������ַ�
                    if (isChar(ch) || isDigit(ch)) {
                        if (position < program.length()) {
                            ch = getChar(position);
                        }
                        if (ch == '\'') {
                            token = new Token(row, column, TokenType.CHARACTER, sb.toString());
                            System.out.println("��ʶ��Token:" + token);
                            return token;
                        }
                    }
                    state = State.Error;
                    result.addError("char������ " + row + ":" + column);
                    //endregion
                    break;
                case Error://region ������ ���ؿյ�Token ��¼������Ϣ
                    System.out.println("[Error] Unrecognized token. near " + row + ":" + column);

                    token = new Token();
                    return token;
                //endregion
                default:
                    state = State.Error;
                    result.addError("δ֪״̬");
            }
        }
        if (state == State.InDot) {//�ļ��ѽ�������ǰһ�������ǡ�.��˵���������
            token = new Token(row, column, TokenType.EOF, ".");
            System.out.println("��ʶ��Token:" + token);
            return token;
        }
        if (state != State.Normal) {
            result.addError("���������� " + row + ":" + column);
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
