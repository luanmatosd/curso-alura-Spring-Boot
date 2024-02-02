package br.com.alura.forum.config.validacao;
//Classe criada para ser o tratamento de erro do Bean Validation com respostas personalizadas
//A ideia é que QUALQUER Controller desse projeto possa chamar esse tratamento de erro
//Aqui foi criado uma espécie de Interceptador de nome Controller Advice
//Toda vez que acontecer uma exception, em qualquer método de qualquer Controller,
// o Spring automaticamente vai chamar esse interceptador e nós faremos o tratamento apropriado

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST) //Devolve o erro 400
    @ExceptionHandler(MethodArgumentNotValidException.class) //Método é chamando quando houver uma exceção do tipo "Method..." em algum Controller
    //Em nossa caso a exceção de validação de formulário é = MethodArgumentNotValidException!
    public List<ErroDeFormularioDto> handle(MethodArgumentNotValidException exception){

        //Variável possui a lista de erros de formulário Dto
        List<ErroDeFormularioDto> erroDtos = new ArrayList<>();

        //Variável possui todos os erros de formulário da requisição
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        //1 - Necessário percorrer a lista de erros do "fieldErrors".
        //2 - Para cada "fieldError" encontrado criar um Objeto do tipo erroDtos,
        //3 - e guardar nessa lista representada pela minha variável erroDtos

        fieldErrors.forEach(e -> { //e significa = para cada Erro
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale()); //e - erro, Local.. pegar o erro no Idioma correto
            ErroDeFormularioDto erro = new ErroDeFormularioDto(e.getField(), mensagem); //e,getField = campo
            erroDtos.add(erro); //Pegando a lista de erros do tipo Dto e adicionando a mensagem de texto
        });

        return erroDtos; //Devolvendo uma lista de erro de formulário do tipo Dto
    }
}
