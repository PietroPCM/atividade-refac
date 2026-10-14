package com.poo.atividade_refac.services;

import com.poo.atividade_refac.contracts.LivroOperations;
import com.poo.atividade_refac.discount.DescontoStrategy;
import com.poo.atividade_refac.entities.Livro;
import com.poo.atividade_refac.repositories.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService implements LivroOperations {

    private final LivroRepository livroRepository;
    private final List<DescontoStrategy> descontos;
    private final RelatorioService relatorioService;
    private final EmailService emailService;

    public LivroService(
            LivroRepository livroRepository,
            List<DescontoStrategy> descontos,
            RelatorioService relatorioService,
            EmailService emailService) {
        this.livroRepository = livroRepository;
        this.descontos = descontos;
        this.relatorioService = relatorioService;
        this.emailService = emailService;
    }

    @Override
    public List<Livro> listar() {
        System.out.println("Listando livros");
        return livroRepository.findAll();
    }

    @Override
    public Livro cadastrar(Livro livro) {
        validar(livro);

        descontos.stream()
                .filter(desconto -> desconto.aplica(livro.getCategoria()))
                .findFirst()
                .ifPresent(desconto -> livro.setPreco(desconto.aplicar(livro.getPreco())));

        System.out.println("Salvando livro: " + livro.getTitulo());

        Livro livroSalvo = livroRepository.save(livro);
        relatorioService.gerar();
        emailService.enviarPromocional();

        return livroSalvo;
    }

    @Override
    public Livro atualizar(Long id, Livro livro) {
        Livro livroBanco = livroRepository.findById(id).orElseThrow();

        livroBanco.setTitulo(livro.getTitulo());
        livroBanco.setAutor(livro.getAutor());
        livroBanco.setPreco(livro.getPreco());
        livroBanco.setCategoria(livro.getCategoria());

        return livroRepository.save(livroBanco);
    }

    @Override
    public void remover(Long id) {
        Livro livro = livroRepository.findById(id).orElseThrow();

        if ("RARO".equals(livro.getCategoria())) {
            throw new RuntimeException("Livros raros não podem ser removidos");
        }

        livroRepository.delete(livro);
    }

    private void validar(Livro livro) {
        if (livro.getTitulo() == null || livro.getTitulo().isBlank()) {
            throw new RuntimeException("Título obrigatório");
        }

        if (livro.getPreco() == null || livro.getPreco() <= 0) {
            throw new RuntimeException("Preço inválido");
        }
    }
}
