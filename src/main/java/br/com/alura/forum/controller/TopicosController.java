package br.com.alura.forum.controller;

//Criando endpoint - irá devolver a lista com todos os tópicos que estão cadastrados no sistema
//Controller criado para mapear o endereço, assim que for chamado, irá devolver essa lista com os tópicos

import br.com.alura.forum.controller.dto.DetalhesTopicoDto;
import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.form.AtualizaTopicoForm;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController //Não preciso mais usar o @ResponseBody
@RequestMapping("/topicos")
public class TopicosController {
    @Autowired
    private TopicosRepository topicosRepository;

    @Autowired
    private CursoRepository cursoRepository;

    //Método lista todos tópicos ou tópico pelo nome específico
    @GetMapping
    public List<TopicoDto> lista (String nomeCurso){
        if(nomeCurso == null){

            //http://localhost:8080/topicos
            List<Topico> topicos = topicosRepository.findAll();
            return TopicoDto.converter(topicos);

        }else{
            //http://localhost:8080/topicos?nomeCurso=Spring+Boot
            List<Topico> topicos = topicosRepository.findByCursoNome(nomeCurso);
            return TopicoDto.converter(topicos);
        }
    }

    //Método cadastra tópicos e retornando o método 201. Usarei o Postman nessa requisação
    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDto> cadastrar (@RequestBody @Valid TopicoForm form, UriComponentsBuilder builder) {
        Topico topico = form.converter(cursoRepository);
        topicosRepository.save(topico); //Salvando tópico no Banco de dados

        //Além de retornar o código 201, preciso retornar mais duas coisas:
        //1 - Cabeçalho HTTP: Location com a URL desse novo recurso criado
        //2 - Corpo da Resposta: Representação do recurso que foi criado

        URI uri = builder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri(); //{} - parâmetro dinâmico

                          //Cabeçalho HTTP //Corpo da Resposta
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    //Método Lista um tópico específico
    @GetMapping("/{id}") //passando id dinâmico
    public ResponseEntity<DetalhesTopicoDto> detalha(@PathVariable Long id){ //@PathVariable indica que a variavel é da URL

        //Anteriormente usávamos o topicosRepository.getOne(id) para encontrar o ID do Tópico no Banco de dados
        //O problema é que se não exister o ID no meu Banco de dados, ele irá devolver uma Exception para o Usuário, isso não é bom!
        //Para resolver isso, usamos o topicosRepository.findById(id) da Classe Optinal. Caso ele não encontre ele não joga Exception
        //Abaixo está o código de tratamento do ID

        Optional<Topico> topico = topicosRepository.findById(id); //Buscando pelo ID do Topico no Banco de Dados

        if(topico.isPresent()){
            return ResponseEntity.ok(new DetalhesTopicoDto(topico.get())); //Converte Topico em DetalhesTopicoDto
        }
        else{
            return ResponseEntity.notFound().build(); //Retorna 404 - Not Found
        }
    }

    //Método atualiza um tópico específico
    @PutMapping("/{id}") //passando id dinâmico
    @Transactional
    public ResponseEntity<TopicoDto> atualizar (@PathVariable Long id, @RequestBody @Valid AtualizaTopicoForm form){

        Optional<Topico> optional = topicosRepository.findById(id); //Buscando pelo ID do Topico no Banco de Dados

        if(optional.isPresent()){
            Topico topico = form.atualizar(id, topicosRepository); //Topico será devolvido com as informações já atualizadas
            return ResponseEntity.ok(new TopicoDto(topico)); //Converte Topico em DetalhesTopicoDto
        }
        else{
            return ResponseEntity.notFound().build(); //Retorna 404 - Not Found
        }
    }

    //Método deleta um tópico específico
    @DeleteMapping("/{id}") //passando id dinâmico
    @Transactional
    //Método remove tópico
    public ResponseEntity<?> remove (@PathVariable Long id){

        Optional<Topico> optional = topicosRepository.findById(id); //Buscando pelo ID do Topico no Banco de Dados

        if(optional.isPresent()){
            topicosRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.notFound().build(); //Retorna 404 - Not Found
        }
    }
}

