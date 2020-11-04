package br.com.danisan.minhalistadetarefas;

import java.io.Serializable;

public class Tarefas implements Serializable {
    private Long id;
    private String textoTarefas;

    public Tarefas() {


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }





    public String getTextoTarefas() {
        return textoTarefas;
    }

    public void setTextoTarefas(String textoTarefas) {
        this.textoTarefas = textoTarefas;
    }
}
