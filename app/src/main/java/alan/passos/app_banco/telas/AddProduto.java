package alan.passos.app_banco.telas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import alan.passos.app_banco.R;
import alan.passos.app_banco.entidades.ProdutoEntity;
import alan.passos.app_banco.utils.BancoDeDados;

public class AddProduto extends AppCompatActivity {


    EditText titulo_edt,descricao_edt,link_img_edt;
    Button salvar_produto_click;
    BancoDeDados bd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_produto);

        titulo_edt = findViewById(R.id.titulo_edt);
        descricao_edt = findViewById(R.id.descricao_edt);
        link_img_edt = findViewById(R.id.link_img_edt);
        salvar_produto_click = findViewById(R.id.salvar_produto_click);


        salvar_produto_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = titulo_edt.getText().toString();
                String descricao = descricao_edt.getText().toString();
                String link = link_img_edt.getText().toString();

                if( !titulo.isEmpty()  | !descricao.isEmpty() & !link.isEmpty()){

                    ProdutoEntity pd = new ProdutoEntity();
                    pd.descricao_produto = descricao;
                    pd.titulo_produto = titulo;
                    if(link.isEmpty()){
                        pd.imagem_produto = "https://a-static.mlcdn.com.br/800x560/pc-positivo-w395vl-c-intel-core-2-duo-24ghz-2gb-250gb-lcd-22-windows-vista-office-2007/magazineluiza/135054100/784b06608c3435d4e2c48c6d3bd35fd3.jpg";
                    }else{
                        pd.imagem_produto = link;
                    }
                    BancoDeDados bd = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class, "banco").allowMainThreadQueries().build();
                    bd.getProdutoDao().insert(pd);

                    Toast.makeText(AddProduto.this, "Salvo", Toast.LENGTH_SHORT).show();
                    finish();

                }

            }
        });





    }
}