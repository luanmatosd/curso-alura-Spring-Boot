package br.com.alura.forum.repository;

import br.com.alura.forum.modelo.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

//Interface criada para carregar o Curso do meu banco de dados no método "converter" da Classe "TopicoForm"
//Sendo mais específico, está sendo utilizado para pesquisar o nome do Curso na criação do Topico, nessa linha aqui:
//return new Topico(titulo, mensagem, curso);
public interface CursoRepository extends JpaRepository<Curso, Long> {

    //Método para buscar o nome de um Curso no Banco de dados
    Curso findByNome(String nomeCurso);
}
