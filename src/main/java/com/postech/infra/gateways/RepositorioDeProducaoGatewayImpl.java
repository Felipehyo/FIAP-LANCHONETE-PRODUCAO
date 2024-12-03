package com.postech.infra.gateways;

import com.postech.application.gateways.RepositorioDeProducaoGateway;
import com.postech.domain.entities.Producao;
import com.postech.infra.mappers.ProducaoMapper;
import com.postech.infra.persistence.entities.ProducaoEntity;
import com.postech.infra.persistence.repositories.ProducaoRepository;

import java.util.Optional;

public class RepositorioDeProducaoGatewayImpl implements RepositorioDeProducaoGateway {

    private final ProducaoRepository repository;
    private final ProducaoMapper mapper;

    public RepositorioDeProducaoGatewayImpl(ProducaoRepository clienteRepository, ProducaoMapper mapper) {
        this.repository = clienteRepository;
        this.mapper = mapper;
    }

    @Override
    public Producao salvar(Producao producao) {
        ProducaoEntity entity = mapper.paraEntidade(producao);
        return mapper.paraDominio(repository.save(entity));
    }

    @Override
    public Producao buscarPorPedidoId(Long idPedido) {
        Optional<ProducaoEntity> entity = repository.getProducaoEntityByIdPedido(idPedido);
        return entity.map(mapper::paraDominio).orElse(null);
    }
}
