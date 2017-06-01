package estacio.edu.br.bancodedados.PessoasDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import estacio.edu.br.bancodedados.ConnectDB.CriaBancoDados;

/**
 * Created by welder on 31/10/16.
 */

public class PessoasDAO {

    private SQLiteDatabase db;
    private CriaBancoDados criaBanco;
    private static final String TAG = "PessoasDAO";

    /**
     * Construtor para criar o banco com a classe CriaBancoDados
     * @param context
     */
    public PessoasDAO(Context context){
        criaBanco = new CriaBancoDados(context);
    }


    /**
     * Método usado para inserir o nome da pessoa cadastrada
     * @param nome
     */
    public String inserir(String nome){
        ContentValues valores;
        long res;

        valores = new ContentValues();
        valores.put("nome",nome);

        db = criaBanco.getWritableDatabase();
        res = db.insert("pessoa",null,valores);
        /**
         * res retorna a posição do registro inserido,
         * caso contrário, retorna -1 se ocorreu erro.
         */
        Log.v(TAG,"Resultado: "+res);
        db.close();

        if (res == -1){
            return "Registro não inserido!!";
        } else {
            return "Registro inserido com sucesso!!";
        }

    }

    /**
     * Método para carregar todos os dados cadastrados
     * O Sqlite usa o recurso de cursor.
     */
    public Cursor Listar(){
        Cursor cursor;
        String[] campos = {"_id","nome"};
        db = criaBanco.getReadableDatabase();
        cursor = db.query("pessoa",campos,null,null,null,null,null);

        if (cursor != null){
            cursor.moveToFirst();
            Log.v(TAG,"Recupera o primeiro registro!!");
        }
        db.close();
        return cursor;
    }

    /**
     * Método find by id
     */
    public Cursor findById(long id){
        Cursor cursor;
        String[] campos = {"_id","nome"};
        String where = "_id ="+id;
        db = criaBanco.getReadableDatabase();
        cursor = db.query("pessoa",campos,where,null,null,null,null);

        if (cursor != null){
            cursor.moveToFirst();
            Log.v(TAG,"Recupera o registro pelo _id!!");
        }
        db.close();
        return cursor;
    }

    /**
     * Método para apagar
     */
    public void apagarById(long id){
        String where ="_id ="+id;
        db = criaBanco.getReadableDatabase();
        db.delete("pessoa",where,
                null);
        db.close();
    }

    /**
     * Método para atualizar
     */
    public String atualizar(long id,String nome){
        ContentValues valores;
        db = criaBanco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("nome",nome);
        String where = " _id ="+id;
        long res = db.update("pessoa",valores, where, null);
        db.close();
        if (res == -1){
            return "Não foi possível atualizar";
        } else {
            return "Atualizado com sucesso!!";
        }
    }



}
