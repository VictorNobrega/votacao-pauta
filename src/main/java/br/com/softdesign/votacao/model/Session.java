package br.com.softdesign.votacao.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sessao")
@Data
@NoArgsConstructor
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "inicio", columnDefinition = "TIMESTAMP")
    private LocalDateTime startTime;

    @Column(name = "fim", columnDefinition = "TIMESTAMP")
    private LocalDateTime endTime;


}