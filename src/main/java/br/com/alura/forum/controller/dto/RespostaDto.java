package br.com.alura.forum.controller.dto;


import br.com.alura.forum.modelo.Resposta;

import java.time.LocalDateTime;

//Boas práticas:
//Não é bom devolver a Classe que tem a Entidade do JPA, mas, sim uma DTO
//Classe criada para ser chamada na lista de atributos da Classe DetalhesTopicoDto
public class RespostaDto {
    private Long id;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String nomeAutor;

    //Construtor criado chamando como parâmetro a Classe Resposta
    public RespostaDto(Resposta resposta) {
        this.id = resposta.getId();
        this.mensagem = resposta.getMensagem();
        this.dataCriacao = resposta.getDataCriacao();
        this.nomeAutor = resposta.getAutor().getNome();
    }

    public Long getId() {
        return id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }
}
