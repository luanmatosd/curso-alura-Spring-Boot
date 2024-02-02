package br.com.alura.forum.controller.dto;

import br.com.alura.forum.modelo.Topico;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

//Boas práticas:
//Classe criada para não devolver todos os atributos da Classe Tópico, somente os selecionados nessa
//"TopicoDTO" - Dados que saem da API de volta para o Cliente
public class TopicoDto {

    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;

    //Construtor criado chamando como parâmetro a Classe Topico
    public TopicoDto(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
    }

    //Método de converter lista de "Topico" em "TopicoDTO"
    public static List<TopicoDto> converter(List<Topico> topicos) {
        return topicos.stream().map(TopicoDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
