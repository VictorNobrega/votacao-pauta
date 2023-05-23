package br.com.softdesign.votacao.model;

import br.com.softdesign.votacao.enums.VotingOptions;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "voto")
@Data
@NoArgsConstructor
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pauta_id", nullable=false)
    private Topic topic;

    @Column(name = "id_associado")
    private String associatedId;

    @Column(name = "opcao_voto")
    @Enumerated(EnumType.STRING)
    private VotingOptions votingOptions;

}
