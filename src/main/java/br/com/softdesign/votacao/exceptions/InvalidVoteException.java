package br.com.softdesign.votacao.exceptions;

public class InvalidVoteException extends Exception {

    public InvalidVoteException(String message) {
        super(message);
    }
}