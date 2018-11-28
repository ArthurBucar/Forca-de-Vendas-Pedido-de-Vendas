package br.com.objetiveti.pedidodevendas.Activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import br.com.objetiveti.pedidodevendas.R;

public class AcNovoPedido extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_pedido);

        ActionBar actionBar = getActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add, menu);
        // return true so that the menu pop up is opened
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    int i = item.getItemId();
        if(i==R.id.menu_add){
            Intent intent = new Intent(this, AcListaClientes.class);
            startActivityForResult(intent, 1);
            //Toast.makeText(NovoPedidoActivity.this, "Linkar a Activity", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(getBaseContext(), ListaClientesActivity.class);
//            startActivity(intent);
        }
        if (android.R.id.home == item.getItemId()){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){

        }
    }
}
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_novo_pedido);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null){
//            if (bundle.getString("some") != null){
//                Toast.makeText(getApplicationContext(), "some" + bundle.getString("some"), Toast.LENGTH_LONG);
//            };
//        }
//    }


