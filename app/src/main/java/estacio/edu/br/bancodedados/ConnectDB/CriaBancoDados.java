package estacio.edu.br.bancodedados.ConnectDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by welder on 31/10/16.
 */

public class CriaBancoDados extends SQLiteOpenHelper{

    /**
     * Esta classe serve somente para criar e atualizar o banco de dados
     * A classe SQLiteOpenHelper tem dois métodos principais:
     * OnCreate --> Cria o banco de dados;
     * OnUpgrade --> Gerencia a atualização do banco.
     */

    private static final String nome_banco = "pessoas.db";

    public CriaBancoDados(Context context){
        super(context,nome_banco,null,3);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table pessoa(_id integer primary key autoincrement, nome text)";
        sqLiteDatabase.execSQL(sql);
        /**
         * Cria a tabela pessoa para armazenar os dados
         */
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists pessoa");
        onCreate(sqLiteDatabase);
        /**
         * Atualiza o banco de dados de acordo com a versão utilizada na plataforma.
         */
    }
}
