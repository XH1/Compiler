package com.xh.snlcompiler.model.grammar1.chart;

import com.xh.snlcompiler.model.grammar1.SNLProduction;
import com.xh.snlcompiler.model.grammar1.enums.Type;
import com.xh.snlcompiler.model.lexer.TokenType;

/**
 * @author xinghao
 * @descreption 生成产生式结构
 * @date 2018/6/9
 */
public class SNLProducer {
    public SNLProduction[] product = new SNLProduction[106];

    public SNLProducer() {
        for (int i = 1; i <= 105; i++)
            product[i] = new SNLProduction();
        product[1].setHead(Type.nonTerminals.Program);
        product[1].setProduction(Type.nonTerminals.ProgramHead);
        product[1].setProduction(Type.nonTerminals.DeclarePart);
        product[1].setProduction(Type.nonTerminals.ProgramBody);

        product[2].setHead(Type.nonTerminals.ProgramHead);
        product[2].setProduction(TokenType.PROGRAM);
        product[2].setProduction(Type.nonTerminals.ProgramName);

        product[3].setHead(Type.nonTerminals.ProgramName);
        product[3].setProduction(TokenType.ID);

        product[4].setHead(Type.nonTerminals.DeclarePart);
        product[4].setProduction(Type.nonTerminals.TypeDecpart);
        product[4].setProduction(Type.nonTerminals.VarDecpart);
        product[4].setProduction(Type.nonTerminals.ProcDecpart);

        product[5].setHead(Type.nonTerminals.TypeDecpart);

        product[6].setHead(Type.nonTerminals.TypeDecpart);
        product[6].setProduction(Type.nonTerminals.TypeDec);

        product[7].setHead(Type.nonTerminals.TypeDec);
        product[7].setProduction(TokenType.TYPE);
        product[7].setProduction(Type.nonTerminals.TypeDecList);

        product[8].setHead(Type.nonTerminals.TypeDecList);
        product[8].setProduction(Type.nonTerminals.TypeId);
        product[8].setProduction(TokenType.EQ);
        product[8].setProduction(Type.nonTerminals.TypeDef);
        product[8].setProduction(TokenType.SEMI);
        product[8].setProduction(Type.nonTerminals.TypeDecMore);

        product[9].setHead(Type.nonTerminals.TypeDecMore);

        product[10].setHead(Type.nonTerminals.TypeDecMore);
        product[10].setProduction(Type.nonTerminals.TypeDecList);

        product[11].setHead(Type.nonTerminals.TypeId);
        product[11].setProduction(TokenType.ID);

        product[12].setHead(Type.nonTerminals.TypeDef);
        product[12].setProduction(Type.nonTerminals.BaseType);

        product[13].setHead(Type.nonTerminals.TypeDef);
        product[13].setProduction(Type.nonTerminals.StructureType);

        product[14].setHead(Type.nonTerminals.TypeDef);
        product[14].setProduction(TokenType.ID);

        product[15].setHead(Type.nonTerminals.BaseType);
        product[15].setProduction(TokenType.INTEGER);

        product[16].setHead(Type.nonTerminals.BaseType);
        product[16].setProduction(TokenType.CHAR);

        product[17].setHead(Type.nonTerminals.StructureType);
        product[17].setProduction(Type.nonTerminals.ArrayType);

        product[18].setHead(Type.nonTerminals.StructureType);
        product[18].setProduction(Type.nonTerminals.RecType);

        product[19].setHead(Type.nonTerminals.ArrayType);
        product[19].setProduction(TokenType.ARRAY);
        product[19].setProduction(TokenType.LMIDPAREN);
        product[19].setProduction(Type.nonTerminals.Low);
        product[19].setProduction(TokenType.UNDERRANGE);
        product[19].setProduction(Type.nonTerminals.Top);
        product[19].setProduction(TokenType.RMIDPAREN);
        product[19].setProduction(TokenType.OF);
        product[19].setProduction(Type.nonTerminals.BaseType);

        product[20].setHead(Type.nonTerminals.Low);
        product[20].setProduction(TokenType.INTC);

        product[21].setHead(Type.nonTerminals.Top);
        product[21].setProduction(TokenType.INTC);

        product[22].setHead(Type.nonTerminals.RecType);
        product[22].setProduction(TokenType.RECORD);
        product[22].setProduction(Type.nonTerminals.FieldDecList);
        product[22].setProduction(TokenType.END);

        product[23].setHead(Type.nonTerminals.FieldDecList);
        product[23].setProduction(Type.nonTerminals.BaseType);
        product[23].setProduction(Type.nonTerminals.IdList);
        product[23].setProduction(TokenType.SEMI);
        product[23].setProduction(Type.nonTerminals.FieldDecMore);

        product[24].setHead(Type.nonTerminals.FieldDecList);
        product[24].setProduction(Type.nonTerminals.ArrayType);
        product[24].setProduction(Type.nonTerminals.IdList);
        product[24].setProduction(TokenType.SEMI);
        product[24].setProduction(Type.nonTerminals.FieldDecMore);

        product[25].setHead(Type.nonTerminals.FieldDecMore);

        product[26].setHead(Type.nonTerminals.FieldDecMore);
        product[26].setProduction(Type.nonTerminals.FieldDecList);

        product[27].setHead(Type.nonTerminals.IdList);
        product[27].setProduction(TokenType.ID);
        product[27].setProduction(Type.nonTerminals.IdMore);

        product[28].setHead(Type.nonTerminals.IdMore);

        product[29].setHead(Type.nonTerminals.IdMore);
        product[29].setProduction(TokenType.COMMA);
        product[29].setProduction(Type.nonTerminals.IdList);

        product[30].setHead(Type.nonTerminals.VarDecpart);

        product[31].setHead(Type.nonTerminals.VarDecpart);
        product[31].setProduction(Type.nonTerminals.VarDec);

        product[32].setHead(Type.nonTerminals.VarDec);
        product[32].setProduction(TokenType.VAR);
        product[32].setProduction(Type.nonTerminals.VarDecList);

        product[33].setHead(Type.nonTerminals.VarDecList);
        product[33].setProduction(Type.nonTerminals.TypeDef);
        product[33].setProduction(Type.nonTerminals.VarIdList);
        product[33].setProduction(TokenType.SEMI);
        product[33].setProduction(Type.nonTerminals.VarDecMore);

        product[34].setHead(Type.nonTerminals.VarDecMore);

        product[35].setHead(Type.nonTerminals.VarDecMore);
        product[35].setProduction(Type.nonTerminals.VarDecList);

        product[36].setHead(Type.nonTerminals.VarIdList);
        product[36].setProduction(TokenType.ID);
        product[36].setProduction(Type.nonTerminals.VarIdMore);

        product[37].setHead(Type.nonTerminals.VarIdMore);

        product[38].setHead(Type.nonTerminals.VarIdMore);
        product[38].setProduction(TokenType.COMMA);
        product[38].setProduction(Type.nonTerminals.VarIdList);

        product[39].setHead(Type.nonTerminals.ProcDecpart);

        product[40].setHead(Type.nonTerminals.ProcDecpart);
        product[40].setProduction(Type.nonTerminals.ProcDec);

        product[41].setHead(Type.nonTerminals.ProcDec);
        product[41].setProduction(TokenType.PROCEDURE);
        product[41].setProduction(Type.nonTerminals.ProcName);
        product[41].setProduction(TokenType.LPAREN);
        product[41].setProduction(Type.nonTerminals.ParamList);
        product[41].setProduction(TokenType.RPAREN);
        product[41].setProduction(TokenType.SEMI);
        product[41].setProduction(Type.nonTerminals.ProcDecPart);
        product[41].setProduction(Type.nonTerminals.ProcBody);
        product[41].setProduction(Type.nonTerminals.ProcDecMore);

        product[42].setHead(Type.nonTerminals.ProcDecMore);

        product[43].setHead(Type.nonTerminals.ProcDecMore);
        product[43].setProduction(Type.nonTerminals.ProcDec);

        product[44].setHead(Type.nonTerminals.ProcName);
        product[44].setProduction(TokenType.ID);

        product[45].setHead(Type.nonTerminals.ParamList);

        product[46].setHead(Type.nonTerminals.ParamList);
        product[46].setProduction(Type.nonTerminals.ParamDecList);

        product[47].setHead(Type.nonTerminals.ParamDecList);
        product[47].setProduction(Type.nonTerminals.Param);
        product[47].setProduction(Type.nonTerminals.ParamMore);

        product[48].setHead(Type.nonTerminals.ParamMore);

        product[49].setHead(Type.nonTerminals.ParamMore);
        product[49].setProduction(TokenType.SEMI);
        product[49].setProduction(Type.nonTerminals.ParamDecList);

        product[50].setHead(Type.nonTerminals.Param);
        product[50].setProduction(Type.nonTerminals.TypeDef);
        product[50].setProduction(Type.nonTerminals.FormList);

        product[51].setHead(Type.nonTerminals.Param);
        product[51].setProduction(TokenType.VAR);
        product[51].setProduction(Type.nonTerminals.TypeDef);
        product[51].setProduction(Type.nonTerminals.FormList);

        product[52].setHead(Type.nonTerminals.FormList);
        product[52].setProduction(TokenType.ID);
        product[52].setProduction(Type.nonTerminals.FidMore);

        product[53].setHead(Type.nonTerminals.FidMore);

        product[54].setHead(Type.nonTerminals.FidMore);
        product[54].setProduction(TokenType.COMMA);
        product[54].setProduction(Type.nonTerminals.FormList);

        product[55].setHead(Type.nonTerminals.ProcDecPart);
        product[55].setProduction(Type.nonTerminals.DeclarePart);

        product[56].setHead(Type.nonTerminals.ProcBody);
        product[56].setProduction(Type.nonTerminals.ProgramBody);

        product[57].setHead(Type.nonTerminals.ProgramBody);
        product[57].setProduction(TokenType.BEGIN);
        product[57].setProduction(Type.nonTerminals.StmList);
        product[57].setProduction(TokenType.END);

        product[58].setHead(Type.nonTerminals.StmList);
        product[58].setProduction(Type.nonTerminals.Stm);
        product[58].setProduction(Type.nonTerminals.StmMore);

        product[59].setHead(Type.nonTerminals.StmMore);

        product[60].setHead(Type.nonTerminals.StmMore);
        product[60].setProduction(TokenType.SEMI);
        product[60].setProduction(Type.nonTerminals.StmList);

        product[61].setHead(Type.nonTerminals.Stm);
        product[61].setProduction(Type.nonTerminals.ConditionalStm);

        product[62].setHead(Type.nonTerminals.Stm);
        product[62].setProduction(Type.nonTerminals.LoopStm);

        product[63].setHead(Type.nonTerminals.Stm);
        product[63].setProduction(Type.nonTerminals.InputStm);

        product[64].setHead(Type.nonTerminals.Stm);
        product[64].setProduction(Type.nonTerminals.OutputStm);

        product[65].setHead(Type.nonTerminals.Stm);
        product[65].setProduction(Type.nonTerminals.ReturnStm);

        product[66].setHead(Type.nonTerminals.Stm);
        product[66].setProduction(TokenType.ID);
        product[66].setProduction(Type.nonTerminals.AssCall);

        product[67].setHead(Type.nonTerminals.AssCall);
        product[67].setProduction(Type.nonTerminals.AssignmentRest);

        product[68].setHead(Type.nonTerminals.AssCall);
        product[68].setProduction(Type.nonTerminals.CallStmRest);

        product[69].setHead(Type.nonTerminals.AssignmentRest);
        product[69].setProduction(Type.nonTerminals.VariMore);
        product[69].setProduction(TokenType.ASSIGN);
        product[69].setProduction(Type.nonTerminals.Exp);

        product[70].setHead(Type.nonTerminals.ConditionalStm);
        product[70].setProduction(TokenType.IF);
        product[70].setProduction(Type.nonTerminals.RelExp);
        product[70].setProduction(TokenType.THEN);
        product[70].setProduction(Type.nonTerminals.StmList);
        product[70].setProduction(TokenType.ELSE);
        product[70].setProduction(Type.nonTerminals.StmList);
        product[70].setProduction(TokenType.FI);

        product[71].setHead(Type.nonTerminals.LoopStm);
        product[71].setProduction(TokenType.WHILE);
        product[71].setProduction(Type.nonTerminals.RelExp);
        product[71].setProduction(TokenType.DO);
        product[71].setProduction(Type.nonTerminals.StmList);
        product[71].setProduction(TokenType.ENDWH);

        product[72].setHead(Type.nonTerminals.InputStm);
        product[72].setProduction(TokenType.READ);
        product[72].setProduction(TokenType.LPAREN);
        product[72].setProduction(Type.nonTerminals.Invar);
        product[72].setProduction(TokenType.RPAREN);

        product[73].setHead(Type.nonTerminals.Invar);
        product[73].setProduction(TokenType.ID);

        product[74].setHead(Type.nonTerminals.OutputStm);
        product[74].setProduction(TokenType.WRITE);
        product[74].setProduction(TokenType.LPAREN);
        product[74].setProduction(Type.nonTerminals.Exp);
        product[74].setProduction(TokenType.RPAREN);

        product[75].setHead(Type.nonTerminals.ReturnStm);
        product[75].setProduction(TokenType.RETURN);

        product[76].setHead(Type.nonTerminals.CallStmRest);
        product[76].setProduction(TokenType.LPAREN);
        product[76].setProduction(Type.nonTerminals.ActParamList);
        product[76].setProduction(TokenType.RPAREN);

        product[77].setHead(Type.nonTerminals.ActParamList);

        product[78].setHead(Type.nonTerminals.ActParamList);
        product[78].setProduction(Type.nonTerminals.Exp);
        product[78].setProduction(Type.nonTerminals.ActParamMore);

        product[79].setHead(Type.nonTerminals.ActParamMore);

        product[80].setHead(Type.nonTerminals.ActParamMore);
        product[80].setProduction(TokenType.COMMA);
        product[80].setProduction(Type.nonTerminals.ActParamList);

        product[81].setHead(Type.nonTerminals.RelExp);
        product[81].setProduction(Type.nonTerminals.Exp);
        product[81].setProduction(Type.nonTerminals.OtherRelE);

        product[82].setHead(Type.nonTerminals.OtherRelE);
        product[82].setProduction(Type.nonTerminals.CmpOp);
        product[82].setProduction(Type.nonTerminals.Exp);

        product[83].setHead(Type.nonTerminals.Exp);
        product[83].setProduction(Type.nonTerminals.Term);
        product[83].setProduction(Type.nonTerminals.OtherTerm);

        product[84].setHead(Type.nonTerminals.OtherTerm);

        product[85].setHead(Type.nonTerminals.OtherTerm);
        product[85].setProduction(Type.nonTerminals.AddOp);
        product[85].setProduction(Type.nonTerminals.Exp);

        product[86].setHead(Type.nonTerminals.Term);
        product[86].setProduction(Type.nonTerminals.Factor);
        product[86].setProduction(Type.nonTerminals.OtherFactor);

        product[87].setHead(Type.nonTerminals.OtherFactor);

        product[88].setHead(Type.nonTerminals.OtherFactor);
        product[88].setProduction(Type.nonTerminals.MultOp);
        product[88].setProduction(Type.nonTerminals.Term);

        product[89].setHead(Type.nonTerminals.Factor);
        product[89].setProduction(TokenType.LPAREN);
        product[89].setProduction(Type.nonTerminals.Exp);
        product[89].setProduction(TokenType.RPAREN);

        product[90].setHead(Type.nonTerminals.Factor);
        product[90].setProduction(TokenType.INTC);

        product[91].setHead(Type.nonTerminals.Factor);
        product[91].setProduction(Type.nonTerminals.Variable);

        product[92].setHead(Type.nonTerminals.Variable);
        product[92].setProduction(TokenType.ID);
        product[92].setProduction(Type.nonTerminals.VariMore);

        product[93].setHead(Type.nonTerminals.VariMore);

        product[94].setHead(Type.nonTerminals.VariMore);
        product[94].setProduction(TokenType.LMIDPAREN);
        product[94].setProduction(Type.nonTerminals.Exp);
        product[94].setProduction(TokenType.RMIDPAREN);

        product[95].setHead(Type.nonTerminals.VariMore);
        product[95].setProduction(TokenType.DOT);
        product[95].setProduction(Type.nonTerminals.FieldVar);

        product[96].setHead(Type.nonTerminals.FieldVar);
        product[96].setProduction(TokenType.ID);
        product[96].setProduction(Type.nonTerminals.FieldVarMore);

        product[97].setHead(Type.nonTerminals.FieldVarMore);

        product[98].setHead(Type.nonTerminals.FieldVarMore);
        product[98].setProduction(TokenType.LMIDPAREN);
        product[98].setProduction(Type.nonTerminals.Exp);
        product[98].setProduction(TokenType.RMIDPAREN);

        product[99].setHead(Type.nonTerminals.CmpOp);
        product[99].setProduction(TokenType.LT);

        product[100].setHead(Type.nonTerminals.CmpOp);
        product[100].setProduction(TokenType.EQ);

        product[101].setHead(Type.nonTerminals.AddOp);
        product[101].setProduction(TokenType.PLUS);

        product[102].setHead(Type.nonTerminals.AddOp);
        product[102].setProduction(TokenType.MINUS);

        product[103].setHead(Type.nonTerminals.MultOp);
        product[103].setProduction(TokenType.TIMES);

        product[104].setHead(Type.nonTerminals.MultOp);
        product[104].setProduction(TokenType.OVER);
    }

}
