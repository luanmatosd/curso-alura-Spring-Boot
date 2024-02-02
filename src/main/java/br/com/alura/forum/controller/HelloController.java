package br.com.alura.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    //Realizando teste em localhost:8080
    @RequestMapping
    @ResponseBody

    //http://localhost:8080/
    public String mensagem(){
        return "Hello World!";
    }

}
