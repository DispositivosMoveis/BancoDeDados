package estacio.edu.br.bancodedados;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import estacio.edu.br.bancodedados.PessoasDAO.PessoasDAO;

public class inserir extends AppCompatActivity {

    private Button salvar;
    private Button voltar;
    private EditText nomeEditText;
    private static final String TAG = "INSERIR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir);

        salvar = (Button) findViewById(R.id.inserirInserirButton);
        voltar = (Button) findViewById(R.id.voltarInserirButton);
        nomeEditText = (EditText) findViewById(R.id.nomeEditText);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String resultado;
                PessoasDAO pessoasDAO = new PessoasDAO(getBaseContext());
                String nome = nomeEditText.getText().toString();
                Log.v(TAG,"valor do nome: "+nome);
                if (! nome.equals("")) {
                    resultado = pessoasDAO.inserir(nome);
                    Intent intent = new Intent(inserir.this, principal.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "O nome não pode ser vazio!!",Toast.LENGTH_LONG).show();
                }
            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(inserir.this, principal.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
