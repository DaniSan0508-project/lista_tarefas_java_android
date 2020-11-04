package br.com.danisan.minhalistadetarefas.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import br.com.danisan.minhalistadetarefas.R;
import br.com.danisan.minhalistadetarefas.Tarefas;
import br.com.danisan.minhalistadetarefas.helpers.MyAdapter;
import br.com.danisan.minhalistadetarefas.helpers.RecyclerItemClickListener;
import br.com.danisan.minhalistadetarefas.helpers.TarefaDAO;

public class MainActivity extends AppCompatActivity {


    private List<Tarefas> listaTarefas = new ArrayList<>();
    private RecyclerView recyclerView ;
    private Tarefas tarefaSelecionada;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(),
                recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                       Intent intent = new Intent(getApplicationContext(),AdicionarTarefaActivity.class);

                        Tarefas atualizaTarefa = listaTarefas.get(position);
                                intent.putExtra("tarefaSelecionada",atualizaTarefa); // muda a activity e envia o serialazable
                       startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                       tarefaSelecionada = listaTarefas.get(position);



                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                        dialog.setTitle("Confirma exclusão ?");
                        dialog.setMessage("Deseja excluir a tarefa :"+ tarefaSelecionada.getTextoTarefas()+  "?");

                        dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                TarefaDAO tarefaDAO = new TarefaDAO(MainActivity.this);

                                tarefaDAO.deletar(tarefaSelecionada);
                                carregarTarefa();
                            }
                        });

                        dialog.setNegativeButton("Não",null);
                        dialog.create();
                        dialog.show();
                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }));


         FloatingActionButton fab = findViewById(R.id.fab);
         fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AdicionarTarefaActivity.class);
                startActivity(intent);


            }
        });
    }
    public void carregarTarefa() {
        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
        listaTarefas = tarefaDAO.listaTarefas(); // recebe as tarefas em vez de staticas do Db

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        MyAdapter adapter = new MyAdapter(listaTarefas);
        recyclerView.setAdapter(adapter);




    }

   @Override
    protected void onStart() {// inicia
        super.onStart();
       carregarTarefa();

     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {



        return super.onOptionsItemSelected(item);
    }
}