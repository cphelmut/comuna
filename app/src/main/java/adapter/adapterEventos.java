package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.comuna.R;

import java.util.ArrayList;
import java.util.List;

import Datos.clsEventos;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class adapterEventos extends RecyclerView.Adapter<adapterEventos.holderEventos> {
    private List<clsEventos> listEventos;
    private Context contextEventos;

    public adapterEventos(Context context){
        this.listEventos = new ArrayList<clsEventos>();
        this.contextEventos = context;
    }


    @NonNull
    @Override
    public holderEventos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapereventos, parent, false);
        return new holderEventos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holderEventos holder, int position) {
        clsEventos objEventos = getListEventos().get(position);
        if(objEventos.getImagen_Evento() != null){
            Glide.with(contextEventos).load(objEventos.getImagen_Evento())
                    .thumbnail(0.5f)
                    .centerCrop()
                    .into(holder.imvImagenEvento);
        }

        holder.txtNombreEvento.setText(objEventos.getNombre_Evento());
        holder.txtDescripcionEvento.setText(objEventos.getDescripcion_Evento().substring(0, Math.min(objEventos.getDescripcion_Evento().length(), 15))+" ...");
        holder.txtEstadoEvento.setText(objEventos.getEstado_Evento());
    }

    @Override
    public int getItemCount() {
        return getListEventos().size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }


    public List<clsEventos> getListEventos() {
        return listEventos;
    }

    public void setListEventos(List<clsEventos> listEventos) {
        this.listEventos = listEventos;
    }

    public Context getContextEventos() {
        return contextEventos;
    }

    public void setContextEventos(Context contextEventos) {
        this.contextEventos = contextEventos;
    }

    public class holderEventos extends RecyclerView.ViewHolder {
        private TextView txtNombreEvento, txtDescripcionEvento, txtFechaEvento, txtEstadoEvento;
        private ImageView imvImagenEvento;
        public holderEventos(@NonNull View itemView) {
            super(itemView);
            txtNombreEvento = itemView.findViewById(R.id.txt_nombreEvento_adapaterEventos);
            txtDescripcionEvento = itemView.findViewById(R.id.txt_descripcionEvento_adapaterEventos);
            txtEstadoEvento = itemView.findViewById(R.id.txt_estadoEvento_adapaterEventos);
            txtFechaEvento = itemView.findViewById(R.id.txt_fechaEvento_adapaterEventos);
            imvImagenEvento = itemView.findViewById(R.id.imv_imagenEvento_adapaterEventos);
        }
    }
}
