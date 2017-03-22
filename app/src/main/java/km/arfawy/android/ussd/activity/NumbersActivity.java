package km.arfawy.android.ussd.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import km.arfawy.android.ussd.R;
import km.arfawy.android.ussd.models.Contact;
import km.arfawy.android.ussd.adapter.NumberAdapter;

public class NumbersActivity extends AppCompatActivity {
    ListView l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();
        Contact ct = i.getExtras().getParcelable("contact");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_photo);
        getSupportActionBar().setTitle(ct + "");
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        setContentView(R.layout.activity_numbers);

        NumberAdapter adapter = new NumberAdapter(this, ct.getArrayNumbres());
        l = (ListView)findViewById(R.id.lv_numbers);
        l.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.num_back, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.btn_num_back){
            onBackPressed();
        }
        return  true;
    }
}