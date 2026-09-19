package com.faculdade.chamados.service;

import com.faculdade.chamados.model.chamado;
import com.faculdade.chamados.repository.chamadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class chamadoService {

    private final chamadoRepository chamadoRepository;

    public chamadoService(chamadoRepository chamadoRepository) {
        this.chamadoRepository = chamadoRepository;
    }

    public chamado cadastrar(chamado chamado) {
        return chamadoRepository.save(chamado);
    }

    public List<chamado> listarTodos() {
        return chamadoRepository.findAll();
    }

    public Optional<chamado> buscarPorId(Integer id) {
        return chamadoRepository.findById(id);
    }

    public Optional<chamado> atualizar(Integer id, chamado novosDados) {

        Optional<chamado> chamadoEncontrado = chamadoRepository.findById(id);

        if (chamadoEncontrado.isEmpty()) {
            return Optional.empty();
        }

        chamado chamado = chamadoEncontrado.get();

        chamado.setTitulo(novosDados.getTitulo());
        chamado.setDescricao(novosDados.getDescricao());
        chamado.setPrioridade(novosDados.getPrioridade());
        chamado.setSolicitante(novosDados.getSolicitante());
        chamado.setStatus(novosDados.getStatus());

        chamado chamadoAtualizado = chamadoRepository.save(chamado);

        return Optional.of(chamadoAtualizado);
    }

    public boolean excluir(Integer id) {

        Optional<chamado> chamado = chamadoRepository.findById(id);

        if (chamado.isEmpty()) {
            return false;
        }

        chamadoRepository.delete(chamado.get());

        return true;
    }
}