package alan.passos.app_banco.telas;

import static alan.passos.app_banco.R.layout.activity_tela_login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import alan.passos.app_banco.R;
import alan.passos.app_banco.entidades.UserEntity;
import alan.passos.app_banco.utils.BancoDeDados;

public class TelaLogin extends AppCompatActivity implements View.OnClickListener {

    /* ATRIBUTOS **************************************************************/
    EditText editLogin, editSenha;
    Button btnLogin, btnSair;
    TextView txtCadastrar;
    BancoDeDados bd;


    /* MÉTODOS DO LIFECYRCLE **************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_tela_login);


        capturaComponentes(); // 1ª
        vinculaEventos();     // 2ª
        //cria uma instancia da conexão com o banco
        bd = Room.databaseBuilder(getApplicationContext(), BancoDeDados.class, "banco").allowMainThreadQueries().build();



    }

    /* MÉTODOS ****************************************************************/
    private void capturaComponentes() {
        editLogin = findViewById(R.id.edit_Login);
        editSenha = findViewById(R.id.edit_Senha);
        btnLogin = findViewById(R.id.btnLogin);
        btnSair = findViewById(R.id.btnSair);
        txtCadastrar = findViewById(R.id.txtCadastrar);
    }

    private void vinculaEventos() {
        txtCadastrar.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnSair.setOnClickListener(this);

    }

    private boolean validarCampos() {
        if(editLogin.getText()==null||
        editLogin.getText().length()==0){ // validação do campo Login
            editLogin.setError("Login obrigatório!");
            return false;

        }else if(editSenha.getText()==null||editSenha.getText().length()==0) { // validação do campo senha
            editSenha.setError("Senha obrigatória!");
            return false;


        }
        return true;


    }
      private void fazerLogin(){
          String nome = editLogin.getText().toString();
          String senha = editSenha.getText().toString();

          UserEntity userEntity = bd.getUserDao().getUser(nome,senha);

          if (userEntity ==null) { //não encontrou os dados no banco
              Toast.makeText(this, "Usuário inexistente", Toast.LENGTH_SHORT).show();
          }else{
              Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();

              SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("init",Context.MODE_PRIVATE);
              SharedPreferences.Editor editor = sharedPref.edit();
              editor.putInt("user_id_login", userEntity.id_user);
              editor.commit();

              Intent intent = new Intent(getApplicationContext(), TelaMenu.class);
              startActivity(intent);
          }

      }

    /* MÉTODOS DE INTERFACES DE EVENTOS ******************************************/
    @Override
    public void onClick(View view) { // tratar todos os toques dos componentes
        // filtro dos componentes tocados
        if (view.getId()==R.id.txtCadastrar){
            Intent intent = new Intent(getApplicationContext(), TelaCadastro.class);
            startActivity(intent);
        } else if (view.getId()==R.id.btnLogin) {
            if (validarCampos()==true){
                fazerLogin();
            }



        }else if (view.getId()==R.id.btnSair) {
            finishAndRemoveTask();

        }
    }

}

