package br.com.softdesign.votacao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OpcaoVoto {

    SIM(1, "Sim"), NAO(2, "Não");

    private int id;
    private String descricao;

}