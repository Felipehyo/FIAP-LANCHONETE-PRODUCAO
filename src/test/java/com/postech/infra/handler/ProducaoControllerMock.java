package com.postech.infra.handler;

import com.postech.domain.enums.ErroPedidoEnum;
import com.postech.domain.enums.ErroProducaoEnum;
import com.postech.domain.exceptions.PedidoException;
import com.postech.domain.exceptions.ProducaoException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producao")
public class ProducaoControllerMock {

    @GetMapping("/test-error-producao")
    public void throwErrorProducao() {
        throw new ProducaoException(ErroProducaoEnum.PREPARO_INVALIDO);
    }

    @GetMapping("/test-error-pedido")
    public void throwErrorPedido() {
        throw new PedidoException(ErroPedidoEnum.ESTADO_INVALIDO);
    }
}
