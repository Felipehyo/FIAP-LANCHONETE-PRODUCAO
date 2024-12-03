package com.postech.application.gateways;

import com.postech.domain.entities.Producao;

public interface RepositorioDeProducaoGateway {
    Producao salvar(Producao producao);

    Producao buscarPorPedidoId(Long pedidoId);

}
