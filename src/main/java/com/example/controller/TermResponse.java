package com.example.controller;


import com.example.tree.Term;
import com.example.tree.compound.CompoundTerm;

import java.util.ArrayList;
import java.util.List;

public class TermResponse{

    private final TermResponse parent;
    private final boolean hasParentheses;
    private final boolean isNegative;
    private final boolean isInverse;
    private final boolean isSimple;
    private final boolean isFraction;
    private List<TermResponse> children = new ArrayList<TermResponse>();
    private String content;
    private int id;
    private String operator;

    public TermResponse(Term term, TermResponse parent) {
        this.parent = parent;
        this.hasParentheses = term.hasParentheses();
        this.isNegative = term.isNegative();
        this.isInverse = term.isInverse();
        this.isSimple = term.isSimple();
        this.isFraction = term.isFraction();
        if (term.isSimple()) {
            content = term.toString();
        } else {
            this.operator = ((CompoundTerm)term).getFunction().toString();
            for (Term child: ((CompoundTerm)term).getChildren()) {
                this.children.add(new TermResponse(child, this));
            }
        }
    }
}
