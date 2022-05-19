package com.assembleia.associadoms.exception;

public class AssociadoNotFoundException extends Throwable{
    public AssociadoNotFoundException() {
        super("Associado não encontrado.");
    }
}
