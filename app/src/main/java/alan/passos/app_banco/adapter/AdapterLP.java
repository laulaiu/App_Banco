package alan.passos.app_banco.adapter;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;

import java.util.List;

import alan.passos.app_banco.R;
import alan.passos.app_banco.entidades.ProdutoEntity;
import alan.passos.app_banco.modelo.Produto;
import alan.passos.app_banco.telas.InfoProduto;
import alan.passos.app_banco.telas.ProdutoEdit;
import alan.passos.app_banco.utils.BancoDeDados;

public class AdapterLP extends RecyclerView.Adapter<AdapterLP.Holder> {

    Context context;
    List<ProdutoEntity> lista;
    BancoDeDados bd;

    public AdapterLP(Context context_c, List<ProdutoEntity> lista_c){
        lista = lista_c;
        context = context_c;
        bd = Room.databaseBuilder(context.getApplicationContext(), BancoDeDados.class, "banco").allowMainThreadQueries().build();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_lp_adapter,parent, false );
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.titulo.setText(lista.get(position).titulo_produto);

        Glide.with(context).load(lista.get(position).imagem_produto).into(holder.image_pd);

        holder.edit_produto_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ProdutoEdit.class);
                intent.putExtra("id_produto", lista.get(position).id_Produto+"" );
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });


        holder.info_produto_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InfoProduto.class);
                intent.putExtra("id_produto", lista.get(position).id_Produto+"" );
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        ImageView image_pd;
        TextView titulo;
        Button edit_produto_click, info_produto_click;

        public Holder(@NonNull View itemView) {
            super(itemView);

            info_produto_click = itemView.findViewById(R.id.info_produto_click);
            image_pd = itemView.findViewById(R.id.image_pd);

            titulo = itemView.findViewById(R.id.titulo);

            edit_produto_click = itemView.findViewById(R.id.edit_produto_click);

        }
    }

}
