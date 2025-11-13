package es.ies.claudiomoyano.dam2.pmdm.gestor_de_inventario_personal_luengo_gomez_alejandro;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PertenenciaAdapter extends RecyclerView.Adapter<PertenenciaAdapter.> {
    private Context contexto;
    private List<Pertenencia> listaPertenencias;

    public PertenenciaAdapter(Context contexto, List<Pertenencia> listaPertenencias){
        this.contexto = contexto;
        this.listaPertenencias = listaPertenencias;
    }


}

    public static class PertenenciaViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewNombre;
        public TextView textViewCategoria;
        public TextView textViewUnidades;
        public TextView textViewPeso;
        public TextView textViewDimensiones;
        public TextView textViewFragil;
        public TextView textViewPrecio;
        public TextView textViewFechsCompra;

        public PertenenciaViewHolder(@NonNull View itemView, final AdapterView.OnItemClickListener listener){
            super(itemView);

        }
    }
