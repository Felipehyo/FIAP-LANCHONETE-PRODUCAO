package com.postech.infra.dto.client.pedido;

public record ProdutoResponseDTO(Long id, String nome, String descricao, String categoria, Double preco) {
}