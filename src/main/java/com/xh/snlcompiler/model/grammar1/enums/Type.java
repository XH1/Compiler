package com.xh.snlcompiler.model.grammar1.enums;

/**
 * @author xinghao
 * @descreption 终极符、非终极符分类
 * @date 2018/6/9
 */
public class Type {
    /*非终极符*/
    public enum nonTerminals {
        Program, ProgramHead, ProgramName, DeclarePart, TypeDecpart,
        TypeDec, TypeDecList, TypeDecMore, TypeId, TypeDef,
        BaseType, StructureType, ArrayType, Low, Top,
        RecType, FieldDecList, FieldDecMore, IdList, IdMore,
        VarDecpart, VarDec, VarDecList, VarDecMore, VarIdList, VarIdMore,
        ProcDecpart, ProcDec, ProcDecMore, ProcName, ParamList, ProcDeclaration,
        ParamDecList, ParamMore, Param, FormList, FidMore,
        ProcDecPart, ProcBody, ProgramBody, StmList, StmMore, Stm,
        AssCall, AssignmentRest, ConditionalStm, LoopStm, InputStm,
        Invar, OutputStm, ReturnStm, CallStmRest, ActParamList,
        ActParamMore, RelExp, OtherRelE, Exp, OtherTerm,
        Term, OtherFactor, Factor, Variable, VariMore, FieldVar,
        FieldVarMore, CmpOp, AddOp, MultOp;
    }
}
