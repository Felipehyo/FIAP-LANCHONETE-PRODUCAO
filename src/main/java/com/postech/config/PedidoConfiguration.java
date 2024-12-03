package com.postech.config;

import com.postech.infra.client.PedidoClientImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoConfiguration {

    @Value("${backend.pedido.url}")
    private String pedidoUrl;

    @Bean
    PedidoClientImpl pedidoClient() {
        return new PedidoClientImpl(pedidoUrl);
    }

}
