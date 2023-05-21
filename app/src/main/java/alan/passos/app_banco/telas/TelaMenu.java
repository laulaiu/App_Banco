package alan.passos.app_banco.telas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

import alan.passos.app_banco.R;
import alan.passos.app_banco.adapter.AdapterLP;
import alan.passos.app_banco.entidades.ProdutoEntity;
import alan.passos.app_banco.utils.BancoDeDados;

public class TelaMenu extends AppCompatActivity {

    RecyclerView lp_recycler;

    ImageView Login_btn_click;
    ImageView add_produto_click;
    BancoDeDados bd;


    @Override
    protected void onResume() {
        super.onResume();
        setList_recyclerView();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_menu);

        lp_recycler = findViewById(R.id.lp_recycler);
        Login_btn_click = findViewById(R.id.Login_btn_click);
        add_produto_click = findViewById(R.id.add_produto_click);

        lp_recycler.setLayoutManager( new LinearLayoutManager(getApplicationContext()));


        Login_btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*                Intent intent = new Intent(getApplicationContext(), TelaLogin.class);
                startActivity(intent);*/
            }
        });


        add_produto_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(), AddProduto.class );
                startActivity(intent);
            }
        });



        setList_recyclerView();

    }


    public void setList_recyclerView(){
        BancoDeDados bd = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class, "banco").allowMainThreadQueries().build();
        List<ProdutoEntity> lista = bd.getProdutoDao().getProdutoAll();
        if(lista != null ){
            lp_recycler.setAdapter( new AdapterLP( getApplicationContext(), lista ));
        }
    }


}
