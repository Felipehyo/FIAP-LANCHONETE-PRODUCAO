package com.postech.infra.persistence.entities;

import com.postech.domain.enums.EstadoProducaoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "producao")
public class ProducaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idPedido;

    @Enumerated(EnumType.STRING)
    private EstadoProducaoEnum status;

    private LocalDateTime inicioPreparo;

    private LocalDateTime fimPreparo;

    private LocalTime tempoTotalPreparo;

}
