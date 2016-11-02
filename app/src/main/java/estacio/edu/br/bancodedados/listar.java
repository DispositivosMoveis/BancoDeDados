package estacio.edu.br.bancodedados;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import estacio.edu.br.bancodedados.PessoasDAO.PessoasDAO;

public class listar extends AppCompatActivity {

    private ListView listView;
    private Button voltar;
    private Cursor cursor;
    private static final String TAG = "listar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        listView = (ListView) findViewById(R.id.listView);
        voltar = (Button) findViewById(R.id.listarVoltarButton);

        PessoasDAO pessoasDAO = new PessoasDAO(getBaseContext());

        cursor = pessoasDAO.Listar();

        String[] campos = {"_id","nome"};
        int[] idViews = new int[] {R.id.codigo,R.id.nome};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),R.layout.pessoas_info,cursor,campos,idViews,0);

        listView.setAdapter(adaptador);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                Log.v(TAG,"cursor movimentado corretamente");
                codigo = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
                Intent intent = new Intent(listar.this, editar.class);
                intent.putExtra("_id", codigo);
                startActivity(intent);
                finish();
            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(listar.this, principal.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
