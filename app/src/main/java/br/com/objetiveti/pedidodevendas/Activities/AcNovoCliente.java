package br.com.objetiveti.pedidodevendas.Activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import br.com.objetiveti.pedidodevendas.Models.Cliente;
import br.com.objetiveti.pedidodevendas.Database.DBMain;
import br.com.objetiveti.pedidodevendas.R;

public class AcNovoCliente extends AppCompatActivity {
    private Cliente cliente = new Cliente();
    DBMain db;
    private EditText razaosocial;
    private EditText cidade;
    private EditText cnpj;
    private EditText endereco;
    private EditText bairro;
    private EditText cep;
    private EditText uf;
    private EditText telefone_comercial;
    private EditText celular;
    private EditText contato;
    private EditText email;

    private TextWatcher cpfMask;
    private TextWatcher cnpjMask;
    private RadioGroup radioGroup;
    private RadioButton rdCNPJ;
    private String nomeEmpresa;
    private String status;
    private String cpnj_CPF;
    private String razaoSocial_Valor;
    private String enderecoValor;
    private String bairroValor;
    private String cidadeValor;
    private String CEPValor;
    private String UFValor;
    private String uTelefone;
    private String Contato;
    private String Email;
    private String tipoDePessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_cliente);

        db = new DBMain(this);

        ActionBar actionBar = getActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        razaosocial = (EditText) findViewById(R.id.razaosocial);
        cidade = (EditText) findViewById(R.id.cidade);
        cnpj = (EditText) findViewById(R.id.edit_cnpj);
        endereco = (EditText) findViewById(R.id.endereco);
        bairro = (EditText) findViewById(R.id.bairro);
        cep = (EditText) findViewById(R.id.cep);
        uf = (EditText) findViewById(R.id.uf);
        telefone_comercial = (EditText) findViewById(R.id.telefone_comercial);
        celular = (EditText) findViewById(R.id.celular);
        contato = (EditText) findViewById(R.id.contato);
        email = (EditText) findViewById(R.id.email);

        final RadioButton rbPessoaFisica = (RadioButton) findViewById(R.id.radioButton02);

        TextView txtRazaoSocial = (TextView) findViewById(R.id.edit_cnpj);


        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup3);
        radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
        Intent in = getIntent();
        Bundle bd = in.getExtras();
        if(bd != null) {
            String getNome = (String) bd.get("nome");
            nomeEmpresa = (String)bd.get("nome");
            getSupportActionBar().setTitle(getNome);
            status = (String) bd.get("limteDeCredito");
            cpnj_CPF = (String) bd.get("cnpj_cpf");
            razaoSocial_Valor = (String) bd.get("razaoSocial");
            enderecoValor = (String) bd.get("endereco");
            bairroValor = (String) bd.get("bairro");
            cidadeValor = (String) bd.get("cidade");
            CEPValor = (String) bd.get("cep");
            UFValor = (String) bd.get("uf");
            uTelefone = (String) bd.get("telefone");
            Contato = (String) bd.get("contato");
            Email = (String) bd.get("email");
            tipoDePessoa = (String) bd.get("tipoDePessoa");

            endereco.setText(enderecoValor);
            cnpj.setText(cpnj_CPF);
            razaosocial.setText(razaoSocial_Valor);
            bairro.setText(bairroValor);
            cidade.setText(cidadeValor);
            cep.setText(CEPValor);
            uf.setText(UFValor);
            telefone_comercial.setText(uTelefone);
            celular.setText(uTelefone);
            contato.setText(Contato);
            email.setText(Email);
            Log.i("limiteCredito", String.valueOf(status));
            //getSupportActionBar().setTitle(get);
        }

        if ("F".equals(tipoDePessoa)){
            rbPessoaFisica.setChecked(true);
        }


        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            bundle.getString("lista");
        }

//Chama o botão teste de persistir
//        Button button = (Button) findViewById(R.id.bnt_persistir);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //  produto.setCodigo(et_codigo_produto.getText().toString());
//                cliente.setRazaoSocial(razaosocial.getText().toString());
//                cliente.setCidade(cidade.getText().toString());
//
//                DB db = new DB(getApplicationContext());
//
//                Toast.makeText(NovoClienteActivity.this, db.insereDados(cliente), Toast.LENGTH_SHORT).show();
//
//          //      Toast.makeText(getApplicationContext(), "Cliente persistido com sucesso!", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.radioButton:
                    EditText editText = (EditText) findViewById(R.id.edit_cnpj);
                    editText.setHint("00.000.000/0000-00");
                    TextView textView = (TextView) findViewById(R.id.label_cnpj);
                    textView.setText("CNPJ");
                    break;
                case R.id.radioButton02:
                    EditText cpf = (EditText) findViewById(R.id.edit_cnpj);
                    cpf.setHint("000.000.000-00");
                    cpf.setVisibility(View.VISIBLE);
                    TextView textView1 = (TextView) findViewById(R.id.label_cnpj);
                    textView1.setText("CPF");
                    break;
            }
        }
    };

//    public void salvarCliente(View view){
//        cliente.setcNome(razaosocial.getText().toString());
//        cliente.setcStatus(cidade.getText().toString());
//
//        DB db = new DB(this);
//        db.inserir(cliente);
//
//        Toast.makeText(this, "Usuário inserido com sucesso!", Toast.LENGTH_SHORT).show();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_check, menu);
        // return true so that the menu pop up is opened
        return true;
    }
    //(android.R.id.home == item.getItemId())
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()) {

            case android.R.id.home:
                finish();
                break;
            default:
//                DB db = new DB(getBaseContext());
//                EditText nome = (EditText)findViewById(R.id.razaosocial);
//                EditText status = (EditText)findViewById(R.id.cidade);
//                String nomeString = razaosocial.getText().toString();
//                String statusString = cidade.getText().toString();
//
//                String resultado;
//
//                resultado = db.insereDados(cliente);  z

 //               INSERT DO CLIENTE
//                db.openDataBase();
//                boolean resultado = db.insertDataCliente(cnpj.getText().toString(), razaosocial.getText().toString(), endereco.getText().toString(), bairro.getText().toString(),
//                        cidade.getText().toString(), cep.getText().toString(), uf.getText().toString(), celular.getText().toString(), telefone_comercial.getText().toString(),
//                        contato.getText().toString(), email.getText().toString());
//                if (resultado == true){
//                    Toast.makeText(getApplication(), "Cliente Cadrastrado com Sucesso", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getApplication(), "Erro", Toast.LENGTH_SHORT).show();
//                }
                Intent intent = new Intent(this, AcMain.class);
                startActivityForResult(intent, 1);

        }
        return super.onOptionsItemSelected(item);
    }

}

//        if (android.R.id.home == item.getItemId()){
//            finish();
//        }
//        Intent intent = new Intent(this, ListaProdutosActivity.class);
//        startActivityForResult(intent, 1);
//          Toast.makeText(getApplication(), "Arrocha", Toast.LENGTH_SHORT).show();