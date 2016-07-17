package edu.galileo.android.androidchat.contactlist;

/**
 * Created by avalo.
 */
public class ContactListSessionInteractorImpl implements ContactListSessionInteractor {
    ContactListRepository repository;

    /*public ContactListSessionInteractorImpl() {
        repository = new ContactListRepositoryImpl();
    }*/

    public ContactListSessionInteractorImpl(ContactListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void signOff() {
        repository.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return repository.getCurrentUserEmail();
    }

    @Override
    public void changeConnectionStatus(boolean online) {
        repository.changeConnectionStatus(online);
    }
}
