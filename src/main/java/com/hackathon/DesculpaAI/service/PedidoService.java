package com.hackathon.DesculpaAI.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hackathon.DesculpaAI.model.Pedido;
import com.hackathon.DesculpaAI.model.StatusPedido;
import com.hackathon.DesculpaAI.repository.PedidoRepository;

@Service
public class PedidoService {
private final PedidoRepository pedidoRepository;

public PedidoService(PedidoRepository pedidoRepository){
    this.pedidoRepository = pedidoRepository;
}
public Pedido criar(Pedido pedido){
   pedido.setStatus(StatusPedido.ABERTO);    
   pedido.setCreatedAt(LocalDateTime.now());
   return pedidoRepository.save(pedido);
}

public Pedido atualizarStatus(Long id, StatusPedido novoStatus){
    Optional<Pedido> pedidoOptional= pedidoRepository.findById(id);
    if(pedidoOptional.isPresent()){
        Pedido pedido = pedidoOptional.get();
     pedido.setStatus(novoStatus);
     return pedidoRepository.save(pedido);
    }
    return null;
}

}
