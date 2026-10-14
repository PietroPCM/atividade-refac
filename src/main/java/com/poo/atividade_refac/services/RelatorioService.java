package com.poo.atividade_refac.services;

import com.poo.atividade_refac.entities.Livro;
import com.poo.atividade_refac.repositories.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatorioService {

    private final LivroRepository livroRepository;

    public RelatorioService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public void gerar() {
        List<Livro> livros = livroRepository.findAll();
        System.out.println("Quantidade de livros: " + livros.size());
    }
}
