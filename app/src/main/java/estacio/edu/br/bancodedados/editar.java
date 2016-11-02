package estacio.edu.br.bancodedados;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.Cursor;
import android.widget.Toast;

import estacio.edu.br.bancodedados.PessoasDAO.PessoasDAO;

public class editar extends AppCompatActivity {

    private EditText editarNome;
    private Button voltar;
    private Button excluir;
    private Button atualizar;
    private Cursor cursor;
    private static final String TAG = "editar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        voltar = (Button) findViewById(R.id.editarVoltarButton);
        excluir = (Button) findViewById(R.id.editarExcluirButton);
        atualizar = (Button) findViewById(R.id.editarAtualizarButton);
        editarNome = (EditText) findViewById(R.id.editarNomeEditText);

        String codigo = getIntent().getExtras().getString("_id");
        Log.v(TAG,"codigo: "+codigo);
        PessoasDAO pessoasDAO = new PessoasDAO(getBaseContext());
        cursor = pessoasDAO.findById(Long.parseLong(codigo));
        editarNome.setText(cursor.getString(1));

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(editar.this,listar.class);
                startActivity(intent);
                Log.v(TAG,"Activity foi criada!!");
                finish();
            }
        });

        excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codigo = getIntent().getExtras().getString("_id");
                PessoasDAO pessoasDAO = new PessoasDAO(getBaseContext());
                pessoasDAO.apagarById(Long.parseLong(codigo));
                Intent intent = new Intent(editar.this,listar.class);
                startActivity(intent);
                finish();
            }
        });

        atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codigo = getIntent().getExtras().getString("_id");
                String nome = editarNome.getText().toString();
                if (! nome.equals("")) {
                    PessoasDAO pessoasDAO = new PessoasDAO(getBaseContext());
                    String resultado = pessoasDAO.atualizar(Long.parseLong(codigo), nome);
                    Intent intent = new Intent(editar.this, listar.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Nome não pode ser vazio!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
