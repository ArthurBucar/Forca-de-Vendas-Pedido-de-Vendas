package br.com.objetiveti.pedidodevendas.Effects;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import br.com.objetiveti.pedidodevendas.Activities.AcQRCode;
import br.com.objetiveti.pedidodevendas.R;


public class FadeInAnimation extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_fadein_animation);
        ImageView imageView = findViewById(R.id.fade_in_image);
        Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_animation);
        imageView.startAnimation(startAnimation);

        final int MILISEGUNDOS = 3000;
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent i = new Intent(FadeInAnimation.this, AcQRCode.class);
                FadeInAnimation.this.startActivity(i);
            }
        }, MILISEGUNDOS);
    }

}