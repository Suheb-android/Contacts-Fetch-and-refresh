package contactrefresh.example.suheb.contactsrefreshdemo;

/**
 * Created by suheb on 2/3/16.
 */
public class ContactsModelClass {
    String name;
    String number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    ContactsModelClass(String name, String number) {
        this.name = name;
        this.number = number;
    }
}
