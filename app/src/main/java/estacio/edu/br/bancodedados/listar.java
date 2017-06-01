package estacio.edu.br.bancodedados;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
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
    private SimpleCursorAdapter adaptador;
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

        adaptador = new SimpleCursorAdapter(getBaseContext(),R.layout.pessoas_info,cursor,campos,idViews,0);

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

        listView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                listar.super.onCreateContextMenu(menu,v,menuInfo);
                menu.add(Menu.NONE, 1, Menu.NONE,"Excluir");
                menu.add(Menu.NONE, 2, Menu.NONE,"Alterar");
                menu.add(Menu.NONE, 3, Menu.NONE,"Cancelar");
            }
        });
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        long id = menuInfo.id;
        switch (item.getItemId()) {
            case 1: {
                PessoasDAO pessoasDAO = new PessoasDAO(getBaseContext());
                pessoasDAO.apagarById(id);
                cursor = pessoasDAO.Listar();
                String[] campos = {"_id","nome"};
                int[] idViews = new int[] {R.id.codigo,R.id.nome};
                adaptador = new SimpleCursorAdapter(getBaseContext(),R.layout.pessoas_info,cursor,campos,idViews,0);
                listView.setAdapter(adaptador);
                adaptador.notifyDataSetChanged();
                break;
            }
            case 2: {
                cursor.moveToPosition(menuInfo.position);
                Log.v(TAG,"cursor movimentado corretamente");
                String codigo = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
                Intent intent = new Intent(listar.this, editar.class);
                intent.putExtra("_id", codigo);
                startActivity(intent);
                finish();
                break;
            }
            case 3: {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Você tem certeza!!");
                builder.setCancelable(false);
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(listar.this,principal.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
                break;
            }
        }
        return super.onContextItemSelected(item);
    }
}
