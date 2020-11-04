package br.com.danisan.minhalistadetarefas.helpers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import br.com.danisan.minhalistadetarefas.R;
import br.com.danisan.minhalistadetarefas.Tarefas;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<Tarefas> tarefas;
    public MyAdapter(List<Tarefas> lista) {
        this.tarefas = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout,
                parent,false));
    }

    @Override
    public int getItemCount() {
        return tarefas.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Tarefas listaTarefas = tarefas.get(position);
        holder.textoTarefa.setText(listaTarefas.getTextoTarefas());

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textoTarefa;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            textoTarefa = itemView.findViewById(R.id.item_lista);

        }
    }
}
