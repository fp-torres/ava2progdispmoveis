package com.example.organizadordeestudos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class EstudoAdapter extends RecyclerView.Adapter<EstudoAdapter.EstudoViewHolder> {

    private List<Estudo> listaEstudos;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onEditClick(Estudo estudo);
        void onDeleteClick(Estudo estudo);
    }

    public EstudoAdapter(List<Estudo> listaEstudos, OnItemClickListener listener) {
        this.listaEstudos = listaEstudos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EstudoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_estudo, parent, false);
        return new EstudoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EstudoViewHolder holder, int position) {
        Estudo estudo = listaEstudos.get(position);
        holder.tvItemDisciplina.setText(estudo.getDisciplina());
        holder.tvItemHorario.setText(estudo.getHorario());

        holder.btnEditar.setOnClickListener(v -> listener.onEditClick(estudo));
        holder.btnExcluir.setOnClickListener(v -> listener.onDeleteClick(estudo));
    }

    @Override
    public int getItemCount() {
        return listaEstudos.size();
    }

    public void atualizarLista(List<Estudo> novaLista) {
        this.listaEstudos = novaLista;
        notifyDataSetChanged();
    }

    static class EstudoViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemDisciplina, tvItemHorario;
        ImageButton btnEditar, btnExcluir;

        public EstudoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemDisciplina = itemView.findViewById(R.id.tvItemDisciplina);
            tvItemHorario = itemView.findViewById(R.id.tvItemHorario);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnExcluir = itemView.findViewById(R.id.btnExcluir);
        }
    }
}