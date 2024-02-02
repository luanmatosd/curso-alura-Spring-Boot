package br.com.alura.forum.config.validacao;
//Classe criada para representar um erro de fórmulário na chamada do método da Classe ErroDeValicaoHandler
//Essa classe que representará o JSON de devolução quando ocorrer erro 400
//Esse Dto representar o erro de algum campo
public class ErroDeFormularioDto {
    private String campo;
    private String mensagem;

    public ErroDeFormularioDto(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
