package com.postech.application.usecases;

import com.postech.application.client.PedidoClient;
import com.postech.application.gateways.RepositorioDeProducaoGateway;
import com.postech.domain.entities.Producao;
import com.postech.domain.enums.ErroPedidoEnum;
import com.postech.domain.enums.ErroProducaoEnum;
import com.postech.domain.enums.EstadoPedidoEnum;
import com.postech.domain.enums.EstadoProducaoEnum;
import com.postech.domain.exceptions.PedidoException;
import com.postech.domain.exceptions.ProducaoException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class ProducaoUseCases {

    private final RepositorioDeProducaoGateway repositorio;
    private final PedidoClient pedidoClient;

    public ProducaoUseCases(RepositorioDeProducaoGateway repositorio, PedidoClient pedidoClient) {
        this.repositorio = repositorio;
        this.pedidoClient = pedidoClient;
    }

    public Producao iniciarProducao(Long pedidoId) {

        if (repositorio.buscarPorPedidoId(pedidoId) != null) {
            throw new ProducaoException(ErroProducaoEnum.PREPARO_INVALIDO);
        }

        var producaoRequest = new Producao(pedidoId, LocalDateTime.now().atZone(ZoneId.of("America/Sao_Paulo")).toLocalDateTime());

        var status = pedidoClient.consultarStatusPedido(pedidoId);

        if (status == null) {
            throw new PedidoException(ErroPedidoEnum.PEDIDO_NAO_ENCONTRADO);
        } else if (status != EstadoPedidoEnum.RECEBIDO) {
            throw new ProducaoException(ErroProducaoEnum.PREPARO_INVALIDO);
        }

        try {
            pedidoClient.atualizarEstadoPorPedidoId(pedidoId, EstadoPedidoEnum.PREPARANDO);
        } catch (Exception ignore) {
            throw new PedidoException(ErroPedidoEnum.ESTADO_INVALIDO);
        }

        return repositorio.salvar(producaoRequest);
    }

    public Producao finalizarProducao(Long pedidoId) {

        var producao = repositorio.buscarPorPedidoId(pedidoId);

        if (producao == null) {
            throw new ProducaoException(ErroProducaoEnum.PREPARACAO_NAO_INICIADA);
        }

        var status = pedidoClient.consultarStatusPedido(pedidoId);
        if (status != EstadoPedidoEnum.PREPARANDO) {
            throw new ProducaoException(ErroProducaoEnum.PREPARACAO_JA_FINALIZADA);
        }

        producao.setStatus(EstadoProducaoEnum.PRONTO);
        producao.setFimPreparo(LocalDateTime.now().atZone(ZoneId.of("America/Sao_Paulo")).toLocalDateTime());
        var duration = Duration.between(producao.getInicioPreparo(), producao.getFimPreparo());
        producao.setTempoTotalPreparo(LocalTime.ofSecondOfDay(duration.getSeconds()));

        try {
            pedidoClient.atualizarEstadoPorPedidoId(pedidoId, EstadoPedidoEnum.PRONTO);
        } catch (Exception ignore) {
            throw new PedidoException(ErroPedidoEnum.ESTADO_INVALIDO);
        }

        return repositorio.salvar(producao);

    }

}
