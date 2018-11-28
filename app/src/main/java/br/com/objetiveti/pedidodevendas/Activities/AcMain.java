package br.com.objetiveti.pedidodevendas.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.objetiveti.pedidodevendas.Models.Cliente;
import br.com.objetiveti.pedidodevendas.Fragments.ClientesFragment;
import br.com.objetiveti.pedidodevendas.Dao.DaoMetaDia;
import br.com.objetiveti.pedidodevendas.Fragments.InicioFragment;
import br.com.objetiveti.pedidodevendas.Fragments.PedidosFragment;
import br.com.objetiveti.pedidodevendas.R;
import br.com.objetiveti.pedidodevendas.Fragments.RelatoriosFragment;

import static android.app.PendingIntent.getActivity;

public class AcMain extends AppCompatActivity {
    BottomNavigationView navigation;
    DaoMetaDia daoMetaDia = new DaoMetaDia();
    private List<Cliente> cList = new ArrayList<>();
    int receberezisi = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content,new InicioFragment()).commit();

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null) {
            try {
                receberezisi = (int)bd.get("enviarReczi");
            } catch (Throwable e) {
                e.printStackTrace();
                receberezisi = 2;
            }
            if (receberezisi >= 2){
                receberezisi += receberezisi;
                fragmentManager = getSupportFragmentManager();
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content,new PedidosFragment()).commit();
                navigation.setSelectedItemId(R.id.pedidos);
            }
        }

        BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                if (item == null) {
                    transaction.replace(R.id.content,new InicioFragment()).commit();
                }
                switch (item.getItemId()) {
                    case R.id.inicio:
                        transaction.replace(R.id.content,new InicioFragment()).commit();
                        return true;
                    case R.id.pedidos:
                        transaction.replace(R.id.content,new PedidosFragment()).commit();
                        return true;
                    case R.id.clientes:
                        transaction.replace(R.id.content,new ClientesFragment()).commit();
                        return true;
                    case R.id.relatorios:
                        transaction.replace(R.id.content,new RelatoriosFragment()).commit();
                        return true;
                }
                return  false;
            }
        };

        navigation = (BottomNavigationView) findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //        InicioFragment inicioFragment = new InicioFragment();
//        FragmentManager manager = getSupportFragmentManager();
//        manager.beginTransaction().replace(R.id.listinicio, inicioFragment).commit();


//        navigation.getMenu().findItem(R.id.inicio).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
//        navigation.getMenu().findItem(R.id.pedidos).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
//        navigation.getMenu().findItem(R.id.clientes).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
//        navigation.getMenu().findItem(R.id.relatorios).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
    }
    public void onClick(View view){
         Intent i = new Intent(AcMain.this, AcMaps.class);
            AcMain.this.startActivity(i);
        //Toast.makeText(getApplication(), "Arrocha", Toast.LENGTH_SHORT).show();
    }

    private AlertDialog alerta;
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setTitle("Titulo");
        builder.setMessage("Deseja Fechar o Aplicativo ?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
            }
        });
        alerta = builder.create();
        alerta.show();
    }

}
