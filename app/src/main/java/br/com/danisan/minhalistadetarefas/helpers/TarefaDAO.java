package br.com.danisan.minhalistadetarefas.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.danisan.minhalistadetarefas.Tarefas;



public class TarefaDAO implements ITarefaDAO {
    public SQLiteDatabase escreve;
    public SQLiteDatabase le;

    public TarefaDAO(Context context) {
        DbHelper db = new DbHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }


    @Override
    public boolean salvar(Tarefas tarefas) {
        ContentValues cv = new ContentValues();
        cv.put("nome",tarefas.getTextoTarefas());
        try {
            escreve.insert(DbHelper.LISTA_TABELA,null,cv);
            Log.i("LOG","salvou");
        }catch (Exception e){
            Log.i("LOG","ERRO");
            return false;
        }
        return true;
    }

    @Override
    public boolean atualizar(Tarefas tarefas) {

        ContentValues cv = new ContentValues();
        cv.put("nome",tarefas.getTextoTarefas());
                try{
                    String[] args = {tarefas.getId().toString()};
                    escreve.update(DbHelper.LISTA_TABELA,cv,"id=?",args );
                    Log.i("log","tarefa atualizada");
                }catch (Exception e){
                    e.printStackTrace();
                    Log.i("log",e.getMessage());
                    return false;
                }




        return false;
    }

    @Override
    public boolean deletar(Tarefas tarefas) {

        try {
            String[] args = {tarefas.getId().toString()};
            escreve.delete(DbHelper.LISTA_TABELA, "id=?", args);
        }catch (Exception e){
            Log.i("log","Erro ao deletar");
        }
        return true;
    }

    @Override
    public List<Tarefas> listaTarefas() {
        List<Tarefas> tarefas = new ArrayList<>();

        String sql = "SELECT * FROM  " + DbHelper.LISTA_TABELA + " ; "; // string que comunica com db para selecionar todo ele
        Cursor c = le.rawQuery(sql,null); // usa o getReadable
        while (c.moveToNext()){ //cria lista de tarefas e percorre ela capturando id e nome
            Tarefas tarefasLista = new Tarefas();
            Long id = c.getLong(c.getColumnIndex("id"));
            String nomeTarefa = c.getString(c.getColumnIndex("nome"));
            tarefasLista.setId(id);
            tarefasLista.setTextoTarefas(nomeTarefa);
            tarefas.add(tarefasLista); // add a lista feita do Db


        }

        return tarefas; // retorna ela pra ser usada no metodo retorno
    }
}
