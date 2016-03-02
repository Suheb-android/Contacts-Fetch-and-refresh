package contactrefresh.example.suheb.contactsrefreshdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static ListView listContacts;
    private static ContactsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listContacts = (ListView) findViewById(R.id.listContacts);
        new Utils.LoadContactsAyscn(getApplicationContext()).execute();
        Intent intent = new Intent(this, ContactsService.class);
        startService(intent);


    }

    public static void setAdapter(List<ContactsModelClass> contacts, Context context) {
        adapter = new ContactsAdapter(contacts,context);
        listContacts.setAdapter(adapter);
    }
}
