package com.postech.application.client;

import com.postech.domain.enums.EstadoPedidoEnum;

public interface PedidoClient {

    EstadoPedidoEnum consultarStatusPedido(Long pedidoId);
    void atualizarEstadoPorPedidoId(Long pedidoId, EstadoPedidoEnum estadoPedidoEnum);
}
