package com.faculdade.chamados.controller;

import com.faculdade.chamados.model.chamado;
import com.faculdade.chamados.service.chamadoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chamados")
public class chamadoController {

    private final chamadoService chamadoService;

    public chamadoController(chamadoService chamadoService) {
        this.chamadoService = chamadoService;
    }

    @PostMapping
    public ResponseEntity<chamado> cadastrar(@RequestBody chamado chamado) {

        chamado novochamado = chamadoService.cadastrar(chamado);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(novochamado);
    }

    @GetMapping
    public ResponseEntity<List<chamado>> listarTodos() {

        List<chamado> chamados = chamadoService.listarTodos();

        return ResponseEntity.ok(chamados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<chamado> buscarPorId(@PathVariable Integer id) {

        return chamadoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<chamado> atualizar(
            @PathVariable Integer id,
            @RequestBody chamado chamado) {

        return chamadoService.atualizar(id, chamado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {

        boolean excluido = chamadoService.excluir(id);

        if (!excluido) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}