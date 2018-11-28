package br.com.objetiveti.pedidodevendas.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import br.com.objetiveti.pedidodevendas.R;

public class AcConfirmaQR extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_confirma_qr);

        final int MILISEGUNDOS = 3000;
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent i = new Intent(AcConfirmaQR.this, AcLogin.class);
                AcConfirmaQR.this.startActivity(i);
            }
        }, MILISEGUNDOS);
    }
}
