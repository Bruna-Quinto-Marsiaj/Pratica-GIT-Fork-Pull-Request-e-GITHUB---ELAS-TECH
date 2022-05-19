package com.assembleia.associadoms.exception;

public class AssociadoCadastradoException extends Throwable {
    public AssociadoCadastradoException() {
        super("Associado já cadastrado.");
    }
}
