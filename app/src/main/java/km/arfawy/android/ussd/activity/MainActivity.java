package km.arfawy.android.ussd.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import km.arfawy.android.ussd.R;
import km.arfawy.android.ussd.metier.ButtonListener;
import km.arfawy.android.ussd.metier.ItemSelectedListener;

public class MainActivity extends AppCompatActivity {
    private boolean isClickedBack = false;
    ImageButton btn_browse;
    Button btn_send;
    EditText edit_number, edit_code;
    ImageView img;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_phone);
        getSupportActionBar().setTitle(R.string.home);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_main);
        img = (ImageView)findViewById(R.id.imageView2);

        btn_browse = (ImageButton)findViewById(R.id.btn_browse);
        btn_send = (Button)findViewById(R.id.btn_send);
        edit_number = (EditText)findViewById(R.id.edit_num);
        edit_code = (EditText)findViewById(R.id.edit_code);
        spinner = (Spinner)findViewById(R.id.spinner);

        adapter = ArrayAdapter.createFromResource(this, R.array.feedbacklist, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Intent i = getIntent();
        if(i.getExtras()!=null){
            spinner.setSelection(adapter.getPosition(getResources().getString(R.string.feedback2)));
            edit_number.setText(i.getExtras().getString("number"));

        }

        btn_browse.setOnClickListener(new ButtonListener(this));
        btn_send.setOnClickListener(new ButtonListener(this));


        spinner.setOnItemSelectedListener(new ItemSelectedListener(this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.btn_param){
            Intent Intent = new Intent(this, ParamActivity.class);
            startActivity(Intent);
        }
        return  true;
    }

    @Override
    public void onBackPressed() {

        if(!isClickedBack){
            Toast.makeText(this, R.string.backagain, Toast.LENGTH_SHORT).show();
            isClickedBack = true;
        }
        else{
            finish();

        }
        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {}
            @Override
            public void onFinish() { isClickedBack = false; }
        }.start();

    }
}
