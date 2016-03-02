package contactrefresh.example.suheb.contactsrefreshdemo;

/**
 * Created by Suheb on 15/12/15.
 */

import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ContactsService extends Service {
    private boolean isRunning = false;

    @Override
    public void onCreate() {
        isRunning = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                getContentResolver().registerContentObserver(ContactsContract.Contacts.CONTENT_URI, true, new MyObserver());
            }
        }).start();
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onDestroy() {
        isRunning = false;
    }

    class MyObserver extends ContentObserver {
        public MyObserver() {
            super(null);
        }

        @Override
        public void onChange(boolean selfChange) {
            this.onChange(selfChange, null);
            Log.i("change1", "" + selfChange);
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            Log.i("change2", "changed");
            new Utils.LoadContactsAyscn(getApplicationContext()).execute();
        }
    }
}