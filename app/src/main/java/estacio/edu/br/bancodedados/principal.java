package estacio.edu.br.bancodedados;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class principal extends AppCompatActivity {

    private Button inserir;
    private Button listar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        inserir = (Button) findViewById(R.id.principalInserir);
        listar = (Button) findViewById(R.id.principalListar);

        inserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(principal.this,inserir.class);
                startActivity(intent);
                finish();
            }
        });

        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(principal.this, listar.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
