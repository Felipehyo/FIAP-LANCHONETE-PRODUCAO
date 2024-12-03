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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProducaoUseCasesTest {

    @Mock
    private RepositorioDeProducaoGateway repositorio;

    @Mock
    private PedidoClient pedidoClient;

    @InjectMocks
    private ProducaoUseCases producaoUseCases;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void iniciarProducaoComSucesso() {
        Long pedidoId = 1L;

        when(repositorio.buscarPorPedidoId(pedidoId)).thenReturn(null);
        when(pedidoClient.consultarStatusPedido(pedidoId)).thenReturn(EstadoPedidoEnum.RECEBIDO);
        Producao producaoSalva = new Producao(pedidoId, LocalDateTime.now());
        when(repositorio.salvar(any(Producao.class))).thenReturn(producaoSalva);

        Producao resultado = producaoUseCases.iniciarProducao(pedidoId);

        assertNotNull(resultado);
        assertEquals(pedidoId, resultado.getIdPedido());
        verify(pedidoClient).atualizarEstadoPorPedidoId(pedidoId, EstadoPedidoEnum.PREPARANDO);
        verify(repositorio).salvar(any(Producao.class));
    }

    @Test
    void iniciarProducaoQuandoJaExiste() {
        Long pedidoId = 1L;
        Producao producaoExistente = new Producao(pedidoId, LocalDateTime.now());
        when(repositorio.buscarPorPedidoId(pedidoId)).thenReturn(producaoExistente);

        ProducaoException exception = assertThrows(ProducaoException.class, () ->
                producaoUseCases.iniciarProducao(pedidoId));

        assertEquals(ErroProducaoEnum.PREPARO_INVALIDO, exception.getErro());
        verify(repositorio, never()).salvar(any(Producao.class));
    }

    @Test
    void iniciarProducaoQuandoAtualizacaoEstadoFalha() {
        Long pedidoId = 1L;

        when(repositorio.buscarPorPedidoId(pedidoId)).thenReturn(null);
        when(pedidoClient.consultarStatusPedido(pedidoId)).thenReturn(EstadoPedidoEnum.RECEBIDO);
        doThrow(new RuntimeException()).when(pedidoClient).atualizarEstadoPorPedidoId(pedidoId, EstadoPedidoEnum.PREPARANDO);

        PedidoException exception = assertThrows(PedidoException.class, () ->
                producaoUseCases.iniciarProducao(pedidoId));

        assertEquals(ErroPedidoEnum.ESTADO_INVALIDO, exception.getErro());
        verify(repositorio, never()).salvar(any(Producao.class));
    }

    @Test
    void iniciarProducaoQuandoStatusPedidoNaoEncontrado() {
        Long pedidoId = 1L;

        when(repositorio.buscarPorPedidoId(pedidoId)).thenReturn(null);
        when(pedidoClient.consultarStatusPedido(pedidoId)).thenReturn(null);

        PedidoException exception = assertThrows(PedidoException.class, () ->
                producaoUseCases.iniciarProducao(pedidoId));

        assertEquals(ErroPedidoEnum.PEDIDO_NAO_ENCONTRADO, exception.getErro());
        verify(pedidoClient, never()).atualizarEstadoPorPedidoId(anyLong(), any());
        verify(repositorio, never()).salvar(any(Producao.class));
    }

    @Test
    void iniciarProducaoQuandoStatusPedidoNaoRecebido() {
        Long pedidoId = 1L;

        when(repositorio.buscarPorPedidoId(pedidoId)).thenReturn(null);
        when(pedidoClient.consultarStatusPedido(pedidoId)).thenReturn(EstadoPedidoEnum.CANCELADO);

        ProducaoException exception = assertThrows(ProducaoException.class, () ->
                producaoUseCases.iniciarProducao(pedidoId));

        assertEquals(ErroProducaoEnum.PREPARO_INVALIDO, exception.getErro());
        verify(pedidoClient, never()).atualizarEstadoPorPedidoId(anyLong(), any());
        verify(repositorio, never()).salvar(any(Producao.class));
    }


    @Test
    void finalizarProducaoComSucesso() {
        Long pedidoId = 1L;
        Producao producao = new Producao(pedidoId, LocalDateTime.now());
        producao.setStatus(EstadoProducaoEnum.PREPARANDO);

        when(repositorio.buscarPorPedidoId(pedidoId)).thenReturn(producao);
        when(pedidoClient.consultarStatusPedido(pedidoId)).thenReturn(EstadoPedidoEnum.PREPARANDO);
        when(repositorio.salvar(any(Producao.class))).thenReturn(producao);

        Producao resultado = producaoUseCases.finalizarProducao(pedidoId);

        assertNotNull(resultado);
        assertEquals(EstadoProducaoEnum.PRONTO, resultado.getStatus());
        verify(pedidoClient).atualizarEstadoPorPedidoId(pedidoId, EstadoPedidoEnum.PRONTO);
        verify(repositorio).salvar(any(Producao.class));
    }

    @Test
    void finalizarProducaoQuandoAtualizacaoDeEstadoFalha() {
        Long pedidoId = 1L;
        Producao producao = new Producao(pedidoId, LocalDateTime.now());
        producao.setStatus(EstadoProducaoEnum.PREPARANDO);

        when(repositorio.buscarPorPedidoId(pedidoId)).thenReturn(producao);
        when(pedidoClient.consultarStatusPedido(pedidoId)).thenReturn(EstadoPedidoEnum.PREPARANDO);
        doThrow(new RuntimeException()).when(pedidoClient).atualizarEstadoPorPedidoId(pedidoId, EstadoPedidoEnum.PRONTO);

        PedidoException exception = assertThrows(PedidoException.class, () ->
                producaoUseCases.finalizarProducao(pedidoId));

        assertEquals(ErroPedidoEnum.ESTADO_INVALIDO, exception.getErro());
        verify(repositorio, never()).salvar(any(Producao.class));
    }

    @Test
    void finalizarProducaoNaoIniciada() {
        Long pedidoId = 1L;

        when(repositorio.buscarPorPedidoId(pedidoId)).thenReturn(null);

        ProducaoException exception = assertThrows(ProducaoException.class, () ->
                producaoUseCases.finalizarProducao(pedidoId));

        assertEquals(ErroProducaoEnum.PREPARACAO_NAO_INICIADA, exception.getErro());
        verify(repositorio, never()).salvar(any(Producao.class));
    }

    @Test
    void finalizarProducaoJaFinalizada() {
        Long pedidoId = 1L;
        Producao producao = new Producao(pedidoId, LocalDateTime.now());
        producao.setStatus(EstadoProducaoEnum.PRONTO);

        when(repositorio.buscarPorPedidoId(pedidoId)).thenReturn(producao);

        ProducaoException exception = assertThrows(ProducaoException.class, () ->
                producaoUseCases.finalizarProducao(pedidoId));

        assertEquals(ErroProducaoEnum.PREPARACAO_JA_FINALIZADA, exception.getErro());
        verify(pedidoClient, never()).atualizarEstadoPorPedidoId(anyLong(), any());
    }

}
