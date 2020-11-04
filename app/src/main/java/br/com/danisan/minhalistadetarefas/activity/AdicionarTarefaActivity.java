package br.com.danisan.minhalistadetarefas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.textfield.TextInputEditText;

import br.com.danisan.minhalistadetarefas.R;
import br.com.danisan.minhalistadetarefas.Tarefas;
import br.com.danisan.minhalistadetarefas.helpers.TarefaDAO;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private TextInputEditText textoAddTarefas;
    private Tarefas tarefaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        textoAddTarefas = findViewById(R.id.text_adicionar_tarefa);
        tarefaAtual = (Tarefas) getIntent().getSerializableExtra("tarefaSelecionada");

        if(!(tarefaAtual == null)){
            textoAddTarefas.setText(tarefaAtual.getTextoTarefas());
        }





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_adicionar_tarefas, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.item_salvar:

                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
                    if(tarefaAtual != null){//edicao

                        Tarefas tarefaAtualizada = new Tarefas();

                        tarefaAtualizada.setTextoTarefas(textoAddTarefas.getText().toString());
                        tarefaAtualizada.setId(tarefaAtual.getId());
                        tarefaDAO.atualizar(tarefaAtualizada);//-- recupera a lista de tarefa e atualiza pelo tarefa dao
                        finish();


                    }else {// salvar


                        if (!(textoAddTarefas ==  null)) {
                            Tarefas tarefas = new Tarefas();
                            tarefas.setTextoTarefas(textoAddTarefas.getText().toString());
                            tarefaDAO.salvar(tarefas); //-- recupera da lista de tarefa
                            finish();
                        }
                        break;
                    }

        }
        return super.onOptionsItemSelected(item);

    }
}