package br.com.softdesign.votacao.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pauta")
@Data
@NoArgsConstructor
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "descricao")
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sessao_id", referencedColumnName = "id")
    private Session session;

    @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY)
    private List<Vote> votes = new ArrayList<>();

}