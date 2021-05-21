package adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.comuna.R;

import java.util.ArrayList;
import java.util.List;

import Datos.clsNoticias;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class adapterNoticias extends RecyclerView.Adapter<adapterNoticias.holderNoticias> {
    private List<clsNoticias> listNoticias;
    private Context contextAdapter;

    public adapterNoticias(Context context){
        listNoticias = new ArrayList<clsNoticias>();
        this.contextAdapter = context;
    }

    @NonNull
    @Override
    public holderNoticias onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapternoticias, parent, false);
        return new holderNoticias(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holderNoticias holder, int position) {
        clsNoticias objNoticias = getListNoticias().get(position);
        Log.e("Hola Jhon ", "Descripcion"+objNoticias.getDescripcion_Noticia());
        if(objNoticias.getImagen_Noticia() != null){
            Glide.with(contextAdapter).load(objNoticias.getImagen_Noticia())
                    .thumbnail(0.5f)
                    .centerCrop()
                    .into(holder.imvImagenNoticia);
        }else{
            holder.txtDescripcion.setText(objNoticias.getDescripcion_Noticia().substring(0, Math.min(objNoticias.getDescripcion_Noticia().length(), 5))+" ...");
            holder.imvImagenNoticia.setMaxHeight(0);
        }
        holder.txtNombreNoticia.setText(objNoticias.getNombre_Noticia());
        holder.txtFechaNoticia.setText(objNoticias.getFecha_Noticia());

    }

    @Override
    public int getItemCount() {
        return getListNoticias().size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }


    public List<clsNoticias> getListNoticias() {
        return listNoticias;
    }

    public void setListNoticias(List<clsNoticias> listNoticias) {
        this.listNoticias = listNoticias;
    }

    public class holderNoticias extends RecyclerView.ViewHolder {
        private TextView txtNombreNoticia,txtFechaNoticia, txtDescripcion;
        private ImageView imvImagenNoticia;
        public holderNoticias(@NonNull View itemView) {
            super(itemView);
            txtNombreNoticia = itemView.findViewById(R.id.txt_nombreNoticia_adapterNoticias);
            txtFechaNoticia = itemView.findViewById(R.id.txt_fechaNoticia_adapaterNoticias);
            imvImagenNoticia = itemView.findViewById(R.id.imv_imagenNoticia_adapterNoticias);
            txtDescripcion = itemView.findViewById(R.id.txt_descripcionNoticia_adapterNoticias);

        }
    }
}
