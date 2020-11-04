package br.com.danisan.minhalistadetarefas.helpers;

import java.util.List;

import br.com.danisan.minhalistadetarefas.Tarefas;

public interface ITarefaDAO {
    boolean salvar(Tarefas tarefas);
    boolean atualizar(Tarefas tarefas);
    boolean deletar(Tarefas tarefas);
    List<Tarefas> listaTarefas();
}
