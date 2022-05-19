package com.assembleia.pautams.exception;

public class PautaNotFoundException extends Throwable {
    public PautaNotFoundException() {
        super("Pauta não encontrada.");
    }
}
