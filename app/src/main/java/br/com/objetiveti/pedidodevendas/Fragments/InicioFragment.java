package br.com.objetiveti.pedidodevendas.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import br.com.objetiveti.pedidodevendas.Activities.AcListaRotas;
import br.com.objetiveti.pedidodevendas.Activities.AcLogin;
import br.com.objetiveti.pedidodevendas.Activities.AcMinhasVendas;
import br.com.objetiveti.pedidodevendas.Adapters.RotaAdapter;
import br.com.objetiveti.pedidodevendas.Adapters.VendaAdapter;
import br.com.objetiveti.pedidodevendas.Database.DBMain;
import br.com.objetiveti.pedidodevendas.Dao.DaoMetaDia;
import br.com.objetiveti.pedidodevendas.Dao.DaoPedido;
import br.com.objetiveti.pedidodevendas.Dao.DaoRotas;
import br.com.objetiveti.pedidodevendas.Dao.DaoVenda;
import br.com.objetiveti.pedidodevendas.Models.Cliente;
import br.com.objetiveti.pedidodevendas.Models.Rota;
import br.com.objetiveti.pedidodevendas.Models.Venda;
import br.com.objetiveti.pedidodevendas.R;

import static br.com.objetiveti.pedidodevendas.R.id.bnt_chama_listarota;
import static br.com.objetiveti.pedidodevendas.R.id.bnt_chama_minhasvendas;

public class InicioFragment extends Fragment implements View.OnClickListener, ViewTreeObserver.OnGlobalLayoutListener{

    private RecyclerView mRecyclerView;
    private RecyclerView mmRecyclerView;
    private List<Rota> mList;
    private List<Venda> vList;
    Cliente cliente = new Cliente();
    private Venda venda;
    private View v;
    ImageButton imageButton;
    private Button proximo;
    private String teste;
    private static final int TIMER_RUNTIME = 30000;
    protected boolean mbActive;
    protected ProgressBar mProgressBar;
    public TextView direita;
    public TextView esquerda;
    public String recebeValor = null;

    private ProgressBar progressBar;
    private int progressStatus = 0;
    private TextView textView;
    private Handler handler = new Handler();


//    -- VARIAVEIS DO PROGRESS BAR
//    protected static final int TIMER_RUNTIME = 10000;
//    protected boolean mbActive;
//    protected ProgressBar mProgressBar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private View view;

    public InicioFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static InicioFragment newInstance(String param1, String param2) {
        InicioFragment fragment = new InicioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Início");
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.fragment_inicio, null);
                mRecyclerView = (RecyclerView)view.findViewById(R.id.rvList);
                mmRecyclerView = (RecyclerView)view.findViewById(R.id.rvList02);

        DBMain banco = new DBMain(getContext());
        banco.openDataBase();
        SQLiteDatabase db = banco.getReadableDatabase();
        double recebe = 0;
        Cursor c = db.rawQuery("select TotalPedido from Pedido_Venda_Cabecalho",null);
        if(c.moveToFirst()){
            do{
                double numSub = c.getDouble(c.getColumnIndex("TotalPedido"));
                recebe = recebe + numSub;
            }while(c.moveToNext());
        }
        c.close();
        db.close();

        DaoMetaDia daoMetaDia1 = new DaoMetaDia();
        teste = String.valueOf(daoMetaDia1.getMetaDia(getContext(), 5));
        float recebeTotal = Float.parseFloat(String.valueOf(teste));
        DaoPedido daoPedido = new DaoPedido();
        final double timer = 6000;
        int metaDia = 0;
        metaDia = daoPedido.getSetPedidoLista(getContext(), 0).size();

        String direita1 = " ";
        direita1 = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(recebeTotal);

        String esquerda1 = " ";
        esquerda1 = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(recebe);

        final float valorCorrente = Float.parseFloat((teste));

        direita = (TextView)view.findViewById(R.id.segundoValorProgress);
        esquerda = (TextView)view.findViewById(R.id.primeiroValorProgress);

        esquerda.setText("" + esquerda1);
        direita.setText("" + direita1);

//        recebeValor = getArguments().getString("my_custom_object");

                mProgressBar = (ProgressBar)view.findViewById(R.id.metadia);

        final int progress = (int) (mProgressBar.getMax() * recebe / valorCorrente);
        mProgressBar.setProgress(progress);

                mRecyclerView.setHasFixedSize(true);
                mmRecyclerView.setHasFixedSize(true);
                Venda venda;
                DaoRotas daoRotas = new DaoRotas();
                DaoMetaDia daoMetaDia = new DaoMetaDia();
                DaoVenda daoVenda = new DaoVenda();
                mList = daoRotas.getSetRotaList(getContext(), 3);
                vList = daoVenda.getSetVendaList(getContext(), 3);


                LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(llm);

                LinearLayoutManager llmm = new LinearLayoutManager(getActivity());
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                mmRecyclerView.setLayoutManager(llmm);

                RotaAdapter adapter = new RotaAdapter(getActivity(), mList);
                mRecyclerView.setAdapter(adapter);

                VendaAdapter adapter1 = new VendaAdapter(getActivity(), vList);
                mmRecyclerView.setAdapter(adapter1);

                //View v = inflater.inflate(R.layout.fragment_inicio, container, false);

                Button bnt_open = (Button) view.findViewById(bnt_chama_listarota);
                bnt_open.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), AcListaRotas.class);
                        intent.putExtra("Lista", "Rotas");
                        startActivity(intent);
                    }
                });
                  // CRIAR UM BOTÃO COM A IMAGEM DO MAPS E CHAMAR A ACTIVITY DO MESMO

                Button bnt_open_vendas = (Button) view.findViewById(bnt_chama_minhasvendas);
                bnt_open_vendas.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), AcMinhasVendas.class);
                        intent.putExtra("Lista", "Vendas");
                        startActivity(intent);
                    }
                });
            return view;
    }

    public void updateProgress(final int timePassed){
        if (null != mProgressBar){
            final int progress = mProgressBar.getMax() * timePassed / TIMER_RUNTIME;
            mProgressBar.setProgress(progress);
        }
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
           // Toast.makeText(context, "Fragment Inicio", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    private AlertDialog alerta;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Deseja Deslogar ?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        DBMain banco = new DBMain(getContext());
                        banco.openDataBase();
                        banco.getWritableDatabase();
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("preferencias", Context.MODE_PRIVATE);
                        String result = sharedPreferences.getString("codigo", "");
                        String nome = sharedPreferences.getString("nome", "");

                        banco.updateCodUsuario(result, "N");

                        Toast.makeText(getContext(), "Deslogado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), AcLogin.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });
                alerta = builder.create();
                alerta.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onGlobalLayout() {

    }
    
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}


//CHAMAR LISTA DE ROTAS
//                    final int MILISEGUNDOS = 3000;
//                    new Handler().postDelayed(new Runnable(){
//                        @Override
//                        public void run() {
//                            Intent intent = new Intent(getActivity(), ListaRotasActivity.class);
//                            intent.putExtra("LISTA", (Serializable) mList);
//                            startActivity(intent);
//                        }
//                    }, MILISEGUNDOS);


//                FloatingActionButton bnt_fab = (FloatingActionButton) view.findViewById(R.id.imageButton);
//                bnt_fab.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent in = new Intent(getActivity(), MapsActivity.class);
//                        startActivity(in);
//                    }
//                });

//


//CHAMAR LISTA MINHAS VENDAS
//                final int MILISEGUNDOS = 3000;
//                new Handler().postDelayed(new Runnable(){
//                    @Override
//                    public void run() {
//                        Intent intent = new Intent(getActivity(), MinhasVendasActivity.class);
//                        getActivity().startActivity(ListaRotasActivity);
//                    }
//                }, MILISEGUNDOS);



//CHAMAR LISTA DE ROTAS
//                final int MILISEGUNDOS = 3000;
//                new Handler().postDelayed(new Runnable(){
//                    @Override
//                    public void run() {
//                        Intent intent = new Intent(getActivity(), ListaRotasActivity.class);
//                        startActivity(intent);
//                    }
//                }, MILISEGUNDOS);

//  CHAMAR TELA DO GOOGLE MAPS
//                final int MILISEGUNDOS = 3000;
//                new Handler().postDelayed(new Runnable(){
//                    @Override
//                    public void run() {
//                        Intent intent = new Intent(getActivity(), MapsActivity.class);
//                        startActivity(intent);
//                    }
//                }, MILISEGUNDOS);


//        ImageButton imageButton = (ImageButton)view.findViewById(R.id.bnt_maps_google);
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent mainIntent = new Intent(getActivity(),MapsActivity.class);
//                startActivity(mainIntent);
//            }
//        });
//                mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
//                    @Override
//                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                        super.onScrollStateChanged(recyclerView, newState);
//                    }
//
//                    @Override
//                    public void onScrolled(RecyclerView recyclerView, int dx, int dy){
//                        super.onScrolled(recyclerView, dx, dy);
//
//                        LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();
//                        RotaAdapter adapter = (RotaAdapter) mRecyclerView.getAdapter();
//
////                        if (mList.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
////                            List<Rota> listAux = ((AcMain) getActivity()).getSetCarList(3);
////
////                            for (int i = 0; i < listAux.size(); i++){
////                                adapter.addListItem(listAux.get(i), mList.size());
////                            }
////                        }
//                    }
//                });

//                mmRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
//                    @Override
//                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                        super.onScrollStateChanged(recyclerView, newState);
//                    }
//
//                    @Override
//                    public void onScrolled(RecyclerView recyclerView, int dxx, int dyy){
//                        super.onScrolled(recyclerView, dxx, dyy);
//
//                        LinearLayoutManager llm = (LinearLayoutManager) mmRecyclerView.getLayoutManager();
//                        RotaAdapter adapter = (RotaAdapter) mmRecyclerView.getAdapter();
//
////                        if (mList.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
////                            List<Rota> listAux = ((AcMain) getActivity()).getSetCarList(3);
////
////                            for (int i = 0; i < listAux.size(); i++){
////                                adapter.addListItem(listAux.get(i), mList.size());
////                            }
////                        }
//                    }
//                });




//    public void chamarMapa(View v){
//        Intent intent = new Intent(getActivity(), MapsActivity.class);
//        startActivity(intent);
//        Log.i("TESTE","cliquei");
//    }

//                final Thread timerTheard = new Thread(){
//                    @Override
//                    public void run(){
//                        mbActive = true;
//                        try {
//                            int waited = 0;
//                            while (mbActive && (waited < valorCorrente)){
//                                sleep(mills);
//                                if (mbActive){
//                                    waited += valorCorrente;
//                                    updateProgress(valorCorrente);
//                                }
//                            }
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        } finally {
//                            onContinue();
//                        }
//                    }
//                };  timerTheard.start();
