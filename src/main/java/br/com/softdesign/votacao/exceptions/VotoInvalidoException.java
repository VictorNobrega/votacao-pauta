package br.com.softdesign.votacao.exceptions;

public class VotoInvalidoException extends Exception {

    public VotoInvalidoException(String mensagem) {
        super(mensagem);
    }
}