package br.com.softdesign.votacao.exceptions;

public class MemberHasVoteException extends Exception {

    public MemberHasVoteException(String message) {
        super(message);
    }
}