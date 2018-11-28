package br.com.objetiveti.pedidodevendas.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import br.com.objetiveti.pedidodevendas.Database.DBMain;
import br.com.objetiveti.pedidodevendas.R;
import br.com.objetiveti.pedidodevendas.Services.ServiceAPI;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AcLogin extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        final DBMain banco = new DBMain(getApplicationContext());
        final TextView tv_apresenta = findViewById(R.id.tv_usuario);
        final EditText edit_user = findViewById(R.id.user_login);
        final EditText edit_senha = findViewById(R.id.user_senha);

        edit_user.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String usuarioTexto = "Usuário";
                    tv_apresenta.setTextColor(getResources().getColor(R.color.colorPrimary));
                    tv_apresenta.setText(usuarioTexto);
            }
        });
        edit_senha.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String senhaTexto = "Senha";
                tv_apresenta.setTextColor(getResources().getColor(R.color.colorPrimary));
                tv_apresenta.setText(senhaTexto);
            }
        });


        final Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView user_login = findViewById(R.id.user_login);
                TextView user_senha = findViewById(R.id.user_senha);

                String userLogin = user_login.getText().toString();
                String userSenha = user_senha.getText().toString();

                //@TODO aqui inicio o service
                ServiceAPI serviceAPI = ServiceAPI.RETROFIT.create(ServiceAPI.class);

                //@TODO CRIO A CHAMADA
                serviceAPI.getProdutos().enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        //@TODO ESTOU PEGANDO PELO RESPONSE SEU JSON PORQUE TO COM PREGUICA DE MONTAR OBJETO :>3
                        ResponseBody responseBody = response.body();
                        try {
                            //@TODO PEGO O JSON E LANCE NESSE JSON OBJETC
                            JSONObject jsonObject = new JSONObject(responseBody.string().toString());
                            //@TODO AI MANDO PARA O ARRAY
                            JSONArray jsonArray = jsonObject.getJSONArray("PRODUTO");
                            for (int i = 0 ; i < jsonArray.length(); i++){
                                //@TODO E PEGO CADA OBJETO DO SEU ARRAY
                                JSONObject OBJETOS = jsonArray.getJSONObject(i);
                                //@TODO  AQUI ESTASO ELES
                                String codigo = OBJETOS.getString("CODIGO");
                                String descricao = OBJETOS.getString("DESCRICAO");
                                String precoPadrao = OBJETOS.getString("PRECOPADRAO");
                                Log.e("LogJsonURL", codigo);
                                Log.e("LogJsonURL", descricao);
                                Log.e("LogJsonURL", precoPadrao);
                            }

                        } catch (IOException e) {
                            //@TODO CASO DER ERRO
                            Toast.makeText(AcLogin.this, "Erro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        } catch (JSONException e) {
                            Toast.makeText(AcLogin.this, "Erro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(AcLogin.this, "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                if (user_login.getText().toString().length() <= 0) {
                    user_login.setError("Preencha o campo Login!");
                    user_login.requestFocus();

                } else if (user_login.getText().toString().contains(" ")){
                    user_login.setError("Sem espaços por favor !");
                    Toast.makeText(AcLogin.this, "Sem esparços por favor !", 5000).show();

                } else if(user_senha.getText().toString().length() <= 0) {
                    user_senha.setError("Preencha o campo Senha!");
                    user_senha.requestFocus();

                }else{
                    SQLiteDatabase db = banco.getReadableDatabase();
                    Cursor sqlBusca = db.rawQuery("SELECT Count(*) as qtd, CodVend as cod FROM Usuario WHERE login = ? AND senha = ? ", new String[] {userLogin, userSenha});

                   if(sqlBusca != null){
                       Intent it = new Intent(getBaseContext(), AcMain.class);
                       startActivity(it);

                       sqlBusca.moveToFirst();
                       int qtd = Integer.parseInt(sqlBusca.getString(sqlBusca.getColumnIndex("qtd")));
                       String codVend = (sqlBusca.getString(sqlBusca.getColumnIndex("cod")));

                       if (qtd == 1){
                           banco.updateCodUsuario(codVend, "S");

                           SharedPreferences prefs = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
                           SharedPreferences.Editor ed = prefs.edit();

                           ed.putString("codigo", codVend);
                           ed.putString("nome", user_login.getText().toString());
                           ed.commit();
                       } else{
                           String snackText = "Usuário e/ou senha incorreto";
                           Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), snackText, Snackbar.LENGTH_LONG);
                           View sbView = snackbar.getView();
                           sbView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
                           snackbar.show();
                       }
                    } else{
                    }
                }
            }
        });
    }
}