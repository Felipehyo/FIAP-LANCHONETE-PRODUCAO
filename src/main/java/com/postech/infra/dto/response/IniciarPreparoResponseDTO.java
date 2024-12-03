package com.postech.infra.dto.response;

import com.postech.domain.enums.EstadoProducaoEnum;

import java.time.LocalDateTime;

public record IniciarPreparoResponseDTO(Long id, Long idPedido, EstadoProducaoEnum status,
                                        LocalDateTime inicioPreparo) {
}
