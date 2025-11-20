package es.ies.claudiomoyano.dam2.pmdm.gestor_de_inventario_personal_luengo_gomez_alejandro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class PertenenciaAdapter extends RecyclerView.Adapter<PertenenciaAdapter.PertenenciaViewHolder> {
    private Context contexto;
    private List<Pertenencia> listaPertenencias;

    public PertenenciaAdapter(Context contexto, List<Pertenencia> listaPertenencias) {
        this.contexto = contexto;
        this.listaPertenencias = listaPertenencias;
    }

    @NonNull
    @Override
    public PertenenciaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        View inflate = from.inflate(R.layout.pertenencia_card_layout, parent, false);
        return new PertenenciaViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PertenenciaViewHolder holder, int position) {
        Pertenencia p = this.listaPertenencias.get(position);
        holder.textViewNombre.setText(p.getNombrePertencia());
        holder.textViewCategoria.setText(p.getCategoria());
        holder.textViewUnidades.setText(String.valueOf(p.getUnidades()));
        holder.textViewPeso.setText(String.valueOf(p.getPesoUnidad()));
        holder.textViewDimensiones.setText(p.getDimensiones());
        holder.checkBoxFragil.setChecked(p.isFragil());

        // Controlo posibles excepciones (valor era opcional)
        if(p.getValorUnidad() > 0){
            holder.textViewPrecio.setText(String.valueOf(p.getValorUnidad()));
        }
        else{
            holder.textViewPrecio.setText("-");
        }

        // Debo convertir en LocalDate a String para que funcione bien
        // Controlo las excepciones (fecha también era opcional)
        if(p.getFechaCompra() != null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            holder.textViewFechaCompra.setText(p.getFechaCompra().format(formatter));
        } else {
            holder.textViewFechaCompra.setText("Sin fecha");
        }
    }

    @Override
    public int getItemCount() {
        return listaPertenencias.size();
    }

    public static class PertenenciaViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewNombre;
        public TextView textViewCategoria;
        public TextView textViewUnidades;
        public TextView textViewPeso;
        public TextView textViewDimensiones;
        public CheckBox checkBoxFragil;
        public TextView textViewPrecio;
        public TextView textViewFechaCompra;

        public PertenenciaViewHolder(@NonNull View itemView){
            super(itemView);

            textViewNombre = itemView.findViewById(R.id.tvNombre);
            textViewCategoria = itemView.findViewById(R.id.tvCategoria);
            textViewUnidades = itemView.findViewById(R.id.tvUnidades);
            textViewPeso = itemView.findViewById(R.id.tvPesoUnidad);
            textViewDimensiones = itemView.findViewById(R.id.tvDimensiones);
            checkBoxFragil = itemView.findViewById(R.id.cbFragil);
            textViewPrecio = itemView.findViewById(R.id.tvValorUnidad);
            textViewFechaCompra = itemView.findViewById(R.id.tvFechaSeleccionada);
        }
    }
}