package br.com.softdesign.votacao.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pauta")
@Getter
@Setter
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sessao_id", referencedColumnName = "id")
    private Sessao sessao;

    @OneToMany(mappedBy="pauta")
    private List<Voto> votos;

}