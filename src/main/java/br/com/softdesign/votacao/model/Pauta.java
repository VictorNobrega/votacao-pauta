package br.com.softdesign.votacao.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pauta")
@Data
@NoArgsConstructor
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

    @OneToMany(mappedBy="pauta", fetch = FetchType.LAZY)
    private List<Voto> votos = new ArrayList<>();

}