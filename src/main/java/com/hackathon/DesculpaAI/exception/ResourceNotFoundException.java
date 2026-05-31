package com.hackathon.DesculpaAI.exception;

/**
 * Exceção lançada quando um recurso não é encontrado.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String mensagem) {
        super(mensagem);
    }

    public ResourceNotFoundException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
