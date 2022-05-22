package com.assembleia.associadoms.exception;

public class AssociadoNotFoundException extends Throwable{
    public AssociadoNotFoundException(String s) {
        super("Associado não encontrado.");
    }
}
