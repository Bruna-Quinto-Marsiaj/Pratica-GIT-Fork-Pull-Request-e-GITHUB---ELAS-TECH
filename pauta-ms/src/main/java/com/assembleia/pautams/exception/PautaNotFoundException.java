package com.assembleia.pautams.exception;

public class PautaNotFoundException extends Throwable {
    public PautaNotFoundException(String s) {
        super("Pauta não encontrada");
    }
}
