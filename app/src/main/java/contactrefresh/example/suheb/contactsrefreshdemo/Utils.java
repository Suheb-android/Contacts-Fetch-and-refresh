package contactrefresh.example.suheb.contactsrefreshdemo;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by suheb on 2/3/16.
 */
public class Utils {
    public static class LoadContactsAyscn extends AsyncTask<Void, Void, ArrayList<ContactsModelClass>> {
        Context context;

        public LoadContactsAyscn(Context context) {
            this.context = context;
        }

//        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            pd = ProgressDialog.show(context, "Loading Contacts",
//                    "Please Wait");

        }

        @Override
        protected ArrayList<ContactsModelClass> doInBackground(Void... params) {
            ArrayList<ContactsModelClass> contacts = new ArrayList<ContactsModelClass>();
            contacts.clear();
            Cursor c = context.getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    null, null, null);
            while (c.moveToNext()) {
//                String id, String userName, String profilePicUrl, String adsCount, boolean isAppUSer, String phoneNumber)
                String id = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                String name = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phNumber = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                phNumber = phNumber.replace("-", "").replace(" ", "").trim();
                contacts.add(new ContactsModelClass(name, phNumber));
            }
            c.close();
            return contacts;
        }

        @Override
        protected void onPostExecute(ArrayList<ContactsModelClass> contacts) {
            super.onPostExecute(contacts);

            /*remove duplicates*/
            List<ContactsModelClass> sorted_list = new ArrayList<ContactsModelClass>();

            for (ContactsModelClass event : contacts) {
                boolean isFound = false;
                // check if the event name exists in noRepeat
                for (ContactsModelClass e : sorted_list) {
                    if (e.getNumber().equals(event.getNumber())) //e.getName().equals(event.getName()) ||
                        isFound = true;
                }

                if (!isFound) sorted_list.add(event);
            }
            /*sort by name*/
            Collections.sort(sorted_list, new Comparator<ContactsModelClass>() {
                public int compare(ContactsModelClass s1, ContactsModelClass s2) {
                    return s1.getName().compareToIgnoreCase(s2.getName());
                }
            });

            MainActivity.setAdapter(sorted_list, context);
        }
    }

    public static String getContactName(Context context, String id) {
        String name = "";
        Cursor cursor = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                new String[]{id}, null);

        while (cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
        }
        cursor.close();
        return name;
    }
}
