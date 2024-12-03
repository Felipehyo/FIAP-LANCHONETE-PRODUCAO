package com.postech.infra.dto.client.pedido;

public record PedidoProdutoResponseDTO(

        Long id,
        PedidoResponseDTO pedido,
        ProdutoResponseDTO produto,
        Integer quantidade

) {
}
