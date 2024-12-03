package com.postech.infra.dto.response;

import com.postech.domain.enums.EstadoProducaoEnum;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record FinalizarPreparoResponseDTO(Long id, Long idPedido, EstadoProducaoEnum status,
                                          LocalDateTime inicioPreparo, LocalDateTime fimPreparo,
                                          LocalTime tempoTotalPreparo) {
}
