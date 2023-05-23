package br.com.softdesign.votacao.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sessao")
@Getter
@Setter
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(mappedBy = "sessao")
    private Pauta pauta;

    @Column(name = "inicio", columnDefinition = "TIMESTAMP")
    private LocalDateTime inicio;

    @Column(name = "fim", columnDefinition = "TIMESTAMP")
    private LocalDateTime fim;


}