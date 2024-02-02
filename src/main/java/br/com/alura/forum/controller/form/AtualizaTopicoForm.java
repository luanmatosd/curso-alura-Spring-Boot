package br.com.alura.forum.controller.form;

import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.TopicosRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//Classe criada para ser utilizada na chamada do metodo "atualizar" de "TopicosController"
public class AtualizaTopicoForm {
    @NotNull @NotEmpty @Length(min = 5) //Usando Bean Validation
    private String titulo;
    @NotNull @NotEmpty @Length(min = 10)
    private String mensagem;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    //Atualiza o Tópico
    public Topico atualizar(Long id, TopicosRepository topicosRepository) {
        Topico topico = topicosRepository.getOne(id);

        topico.setTitulo(this.titulo);
        topico.setMensagem(this.mensagem);

        return topico;
    }
}
