package Contacts;

public class Contact {
    public String contacts;
    public String phoneNumbers;


    public Contact(String contacts, String phoneNumbers) {
        this.contacts = contacts;
        this.phoneNumbers = phoneNumbers;
    }


    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }


}
