package com.postech.infra.client;

import com.postech.application.client.PedidoClient;
import com.postech.domain.enums.EstadoPedidoEnum;
import com.postech.infra.dto.client.pedido.PedidoResponseDTO;
import org.springframework.web.reactive.function.client.WebClient;

public class PedidoClientImpl implements PedidoClient {
    private final String pedidoUrl;

    public PedidoClientImpl(String pedidoUrl) {
        this.pedidoUrl = pedidoUrl;
    }

    @Override
    public EstadoPedidoEnum consultarStatusPedido(Long pedidoId) {
        PedidoResponseDTO response;

        try {
            response = WebClient.builder()
                    .baseUrl(pedidoUrl).build()
                    .get()
                    .uri(pedidoUrl + "/" + pedidoId)
                    .retrieve()
                    .bodyToMono(PedidoResponseDTO.class)
                    .block();

            if (response != null) {
                return response.estado();
            }
        } catch (Exception ignore) {
        }

        return null;
    }

    @Override
    public void atualizarEstadoPorPedidoId(Long pedidoId, EstadoPedidoEnum estadoPedidoEnum) {
        WebClient.builder()
                .baseUrl(pedidoUrl).build()
                .patch()
                .uri(pedidoUrl + "/" + pedidoId + "/estado?estado=" + estadoPedidoEnum.name())
                .retrieve()
                .bodyToMono(PedidoResponseDTO.class)
                .block();
    }

}
