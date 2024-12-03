package com.postech.config;

import com.postech.application.client.PedidoClient;
import com.postech.application.gateways.RepositorioDeProducaoGateway;
import com.postech.application.usecases.ProducaoUseCases;
import com.postech.infra.gateways.RepositorioDeProducaoGatewayImpl;
import com.postech.infra.mappers.ProducaoMapper;
import com.postech.infra.persistence.repositories.ProducaoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducaoConfiguration {

    @Bean
    ProducaoUseCases producaoUseCases(RepositorioDeProducaoGateway repositorioDeProducaoGateway, PedidoClient pedidoClient) {
        return new ProducaoUseCases(repositorioDeProducaoGateway, pedidoClient);
    }

    @Bean
    RepositorioDeProducaoGatewayImpl repositorioDeClienteGateway(ProducaoRepository repository, ProducaoMapper mapper) {
        return new RepositorioDeProducaoGatewayImpl(repository, mapper);
    }

}
