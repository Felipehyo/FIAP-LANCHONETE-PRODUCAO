package com.postech.infra.dto.client.pedido;

import com.postech.domain.enums.EstadoPedidoEnum;

import java.util.List;

public record PedidoResponseDTO(Long id,
                                ClienteResponseDTO cliente,
                                EstadoPedidoEnum estado,
                                List<PedidoProdutoResponseDTO> pedidosProdutos) {
}
