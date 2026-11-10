package com.academiadodesenvolvedor.marketplace.aplicacao.compra.usecase;

import com.academiadodesenvolvedor.marketplace.aplicacao.compra.usecase.dto.request.CadastrarCompraRequest;
import com.academiadodesenvolvedor.marketplace.aplicacao.compra.usecase.dto.request.PagamentoRequest;
import com.academiadodesenvolvedor.marketplace.aplicacao.compra.usecase.dto.response.CadastrarCompraResponse;
import com.academiadodesenvolvedor.marketplace.aplicacao.compra.usecase.dto.request.CompraProdutoRequest;
import com.academiadodesenvolvedor.marketplace.aplicacao.exception.NotFoundException;
import com.academiadodesenvolvedor.marketplace.dominio.compra.Compra;
import com.academiadodesenvolvedor.marketplace.dominio.compra.Pedido;
import com.academiadodesenvolvedor.marketplace.dominio.compra.ProdutoPedido;
import com.academiadodesenvolvedor.marketplace.dominio.compra.repositorio.CompraRepository;
import com.academiadodesenvolvedor.marketplace.dominio.produto.Produto;
import com.academiadodesenvolvedor.marketplace.dominio.produto.repositorios.ProdutoRepository;
import com.academiadodesenvolvedor.marketplace.dominio.usuario.Cliente;
import com.academiadodesenvolvedor.marketplace.dominio.usuario.repositorios.ClienteRepository;
import com.academiadodesenvolvedor.marketplace.infraestrutura.seguranca.dto.Auth;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.cardtoken.CardTokenClient;
import com.mercadopago.client.cardtoken.CardTokenRequest;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.CardToken;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.payment.PaymentMethod;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mercadopago.client.payment.PaymentPayerRequest;

import java.util.*;

@Service
public class CadastrarCompraUseCase {

    private CompraRepository repository;
    private ClienteRepository clienteRepository;
    private ProdutoRepository produtoRepository;

    @Value("${mercadopago.access-token}" )
    private String mercadoPagoAccessToken;

    public CadastrarCompraUseCase(CompraRepository repository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    private List<Produto> getProdutos(List<CompraProdutoRequest> lista) {
        Set<UUID> unique = new HashSet<>(lista.stream().map(CompraProdutoRequest::produtoId).toList());

        List<Produto> produtosDaCompra = this.produtoRepository.findAllById(unique);

        if (produtosDaCompra.size() != unique.size()) {
            throw new NotFoundException("Um dos produtos informados não foi encontrado!" );
        }

        return produtosDaCompra;
    }

    @Transactional
    public CadastrarCompraResponse execute(CadastrarCompraRequest request, Auth auth) {
        Cliente cliente = this.clienteRepository.findById(auth.id()).orElseThrow(() -> new NotFoundException("Cliente não encontrado!" ));

        List<Pedido> pedidos = new ArrayList<>();

        Compra compra = new Compra(cliente.getId(), request.endereco(), pedidos, request.pagamentoRequest().parcela());

        List<Produto> produtos = this.getProdutos(request.produtos());

        Set<UUID> vendUnique = new HashSet<>(produtos.stream().map(Produto::getVendedorID).toList());

        for (UUID vendedor : vendUnique) {
            List<Produto> prd = produtos.stream().filter(produto -> produto.getVendedorID().equals(vendedor)).toList();

            List<ProdutoPedido> produtoPedidos = new ArrayList<>();

            Pedido pedido = new Pedido(vendedor, compra.getId(), produtoPedidos);

            for (Produto produto : prd) {
                CompraProdutoRequest prdCompra = request.produtos().stream().filter(p -> p.produtoId().equals(produto.getId())).findFirst().orElseThrow(() -> new NotFoundException("Produto não encontrado no Pedido!" ));

                ProdutoPedido produtoPedido = new ProdutoPedido(pedido.getId(), produto.getId(), prdCompra.quantidade(), prdCompra.valor());
                produtoPedidos.add(produtoPedido);
            }

            pedido.setProdutos(produtoPedidos);

            pedidos.add(pedido);
        }

        compra.setPedidos(pedidos);

        Payment pagamento = this.pagar(compra, cliente, request.pagamentoRequest());

        compra.setPagamentoId(pagamento.getId().toString());
        compra.setStatus(pagamento.getStatus());

        this.repository.save(compra);

        return new CadastrarCompraResponse(compra.getId(), pagamento.getId().toString(), pagamento.getStatus());
    }

    private Payment pagar(Compra compra, Cliente cliente, PagamentoRequest pagamento) {
        try {
            MercadoPagoConfig.setAccessToken(mercadoPagoAccessToken);

            PaymentPayerRequest payer = PaymentPayerRequest
                    .builder()
                    .firstName(cliente.getNome())
                    .email(cliente.getEmail())
                    .identification(
                            IdentificationRequest.builder()
                                    .type("CPF" )
                                    .number(pagamento.documento())
                                    .build()
                    ).build();

            PaymentCreateRequest paymentRequest = PaymentCreateRequest.builder()
                    .transactionAmount(compra.getTotal())
                    .token(pagamento.token())
                    .description("Marketplace - Compra #" + compra.getId())
                    .installments(compra.getParcelas())
                    .paymentMethodId(pagamento.bandeira())
                    .payer(payer)
                    .externalReference(compra.getId().toString())
                    .notificationUrl("https://marketplace.academiadodesenvolvedor.com/api/webhooks/mercadopago" )
                    .build();

            PaymentClient client = new PaymentClient();

            return client.create(paymentRequest);
        } catch (MPApiException e) {
            throw new RuntimeException("Erro ao processar o cartão de crédito no Mercado Pago:" + e.getApiResponse().getContent(), e);
        } catch (MPException e) {
            throw new RuntimeException("Erro interno na comunicação com o Mercado Pago:" + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
