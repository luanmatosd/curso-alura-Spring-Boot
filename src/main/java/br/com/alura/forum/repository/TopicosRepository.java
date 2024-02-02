package br.com.alura.forum.repository;

import br.com.alura.forum.modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//Interface criada para ser utilizar na conexão com o Banco de dados da Classe TopicosController
//Por ser uma interface não precisa colocar nenhum Annotation
//Essa interface vai herdar de uma interface do Spring Data: JpaRepository

//Topico - Entidade que irá trabalhar, Long - Qual é o tipo do atributo da chave PK dessa Entidade
public interface TopicosRepository extends JpaRepository<Topico, Long> {

    //método criado para filtrar por atributos da entidade, nesse caso, nome do Curso;
    //"findBy..." precisa seguir essa nomenclatura para criar um método de filtro
    //o _ serve para tirar ambiguidade. Ele aponta para a Entidade e o atributo dentro dela
    List<Topico> findByCursoNome(String nomeCurso);
}
