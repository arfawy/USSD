package km.arfawy.android.ussd.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import km.arfawy.android.ussd.R;
import km.arfawy.android.ussd.metier.PhoneMetier;
import km.arfawy.android.ussd.models.Contact;
import km.arfawy.android.ussd.adapter.ContactAdapter;

public class ContactsActivity extends AppCompatActivity {
    ListView cname_lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_contacts);
        getSupportActionBar().setTitle(R.string.contacts);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        setContentView(R.layout.activity_contacts);
        cname_lv = (ListView)findViewById(R.id.listview_cname);

        ArrayList<Contact> cts = PhoneMetier.getContacts(this);

        ContactAdapter adapter = new ContactAdapter(cts,this);
        cname_lv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ct_back, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.btn_ct_back){
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return  true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
