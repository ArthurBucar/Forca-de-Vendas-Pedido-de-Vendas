package br.com.objetiveti.pedidodevendas.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Arthur Bucar on 10/27/2017.
 */

public class DBMain extends SQLiteOpenHelper {
    private static String DB_NAME = "ForcaVendas.db";
    private static String DB_PATH = "/data/data/br.com.objetiveti.pedidodevebdas/databases/";
    ///data/user/0/br.com.objetiveti.forcadevendas.forcadevendas/databases/ForcaVendas.db
    private static final int DB_VERSION = 2;
    public static final String TABLE_NAME_Pedido_Cabecalho = "Pedido_Venda_Cabecalho";
    public static final String TABLE_NAME_Cliente = "Cliente";
    public static final String TABLE_USUARIO = "Usuario";
    public static final String TABLE_NAME_Pedido_Forma_Pgto = "Pedido_Venda_Forma_Pgto";
    public static final String TABLE_NAME_Pedido_Venda_Itens = "Pedido_Venda_Itens";

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "ForcaVendas.db";

    private SQLiteDatabase mDataBase;
    private final Context mContext;
    private boolean mNeedUpdate = false;

//    public DBMain(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        this.mContext = context;
//    }

    public DBMain(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        if (android.os.Build.VERSION.SDK_INT >= 17)
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        else
            DB_PATH = "/raw/data/" + context.getPackageName() + "/databases/";
        this.mContext = context;

        copyDataBase();

        this.getReadableDatabase();
    }

    public void updateDataBase() throws IOException {
        if (mNeedUpdate) {
            File dbFile = new File(DB_PATH + DB_NAME);
            if (dbFile.exists())
                dbFile.delete();

            copyDataBase();

            mNeedUpdate = false;
        }
    }

    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    private void copyDataBase() {
        if (!checkDataBase()) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDBFile();
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private void copyDBFile() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        //InputStream mInput = mContext.getResources().openRawResource(R.raw.info);
        OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public boolean openDataBase() throws SQLException {
        mDataBase = SQLiteDatabase.openDatabase(DB_PATH+DATABASE_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDataBase != null;
    }

    public boolean insertData(String numeroPV,String loja, String data, String hora, String codigoVendedor, String formaPagamento, double TotalPedido){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NumeroPV", numeroPV);
        contentValues.put("Loja", loja);
        contentValues.put("Emissao", data);
        contentValues.put("Hora", hora);
        contentValues.put("Vendedor", codigoVendedor);
        contentValues.put("CondPagto", formaPagamento);
        contentValues.put("TotalPedido", TotalPedido);

        long result = db.insertOrThrow(TABLE_NAME_Pedido_Cabecalho, null, contentValues);
        if (result == -1){
            return false;
        } else {
            return  true;
        }
    }

    public boolean insertPedidoVendaPagto(String numeroPV, String formarPgto, String valor){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NumeroPV", numeroPV);
        contentValues.put("FormaPgto", formarPgto);
        contentValues.put("Valor", valor);

        long result = db.insertOrThrow(TABLE_NAME_Pedido_Forma_Pgto, null, contentValues);
        if (result == -1){
            return false;
        } else {
            return  true;
        }
    }

    public boolean insertPedidoVendaItens(String numeroPV , String itens, String produto, String descProduto, String UM, String Quantidade, String preco, String valor){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NumeroPV", numeroPV);
        contentValues.put("Item", itens);
        contentValues.put("Produto", produto);
        contentValues.put("DescProduto", descProduto);
        contentValues.put("UM", UM);
        contentValues.put("Quantidade", Quantidade);
        contentValues.put("Preco", preco);
        contentValues.put("Total", valor);

        long result = db.insertOrThrow(TABLE_NAME_Pedido_Venda_Itens, null, contentValues);
        if (result == -1){
            return false;
        } else {
            return  true;
        }
    }

    public boolean updateCodUsuario(String codigo, String valor){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Ativado", valor);
        long result = db.update(TABLE_USUARIO, contentValues, "CodVend = ?", new String[] {codigo});
        if (result == -1){
            return false;
        } else {
            return  true;
        }
    }

    public boolean insertDataCliente(String cnpj, String razao, String endereco, String bairro, String cidade,
                                     String cep, String UF, String celular, String telefoneComercial, String contato, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("CPF_CNPJ", cnpj);
        contentValues.put("RazaoSocial", razao);
        contentValues.put("Endereco", endereco);
        contentValues.put("Bairro", bairro);
        contentValues.put("Cidade", cidade);
        contentValues.put("CEP", cep);
        contentValues.put("UF", UF);
        contentValues.put("Telefone", celular);
        contentValues.put("TelefoneComercial", telefoneComercial);
        contentValues.put("Email", celular);
        contentValues.put("Contato", contato);
        contentValues.put("Email", email);
        long result = db.insertOrThrow(TABLE_NAME_Cliente, null, contentValues);
        if (result == -1){
            return false;
        } else {
            return  true;
        }
    }

    @Override
    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            mNeedUpdate = true;
    }
}