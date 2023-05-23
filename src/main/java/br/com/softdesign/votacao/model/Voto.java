package br.com.softdesign.votacao.model;

import br.com.softdesign.votacao.enums.OpcaoVoto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "voto")
@Data
@NoArgsConstructor
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="pauta_id", nullable=false)
    private Pauta pauta;

    @Column
    private String idAssociado;

    @Column(name = "opcao_voto")
    @Enumerated(EnumType.STRING)
    private OpcaoVoto opcaoVoto;

}
