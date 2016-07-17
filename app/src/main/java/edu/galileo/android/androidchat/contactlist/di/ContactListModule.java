package edu.galileo.android.androidchat.contactlist.di;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.androidchat.contactlist.ContactListInteractor;
import edu.galileo.android.androidchat.contactlist.ContactListInteractorImpl;
import edu.galileo.android.androidchat.contactlist.ContactListPresenter;
import edu.galileo.android.androidchat.contactlist.ContactListPresenterImpl;
import edu.galileo.android.androidchat.contactlist.ContactListRepository;
import edu.galileo.android.androidchat.contactlist.ContactListRepositoryImpl;
import edu.galileo.android.androidchat.contactlist.ContactListSessionInteractor;
import edu.galileo.android.androidchat.contactlist.ContactListSessionInteractorImpl;
import edu.galileo.android.androidchat.contactlist.ui.ContactListView;
import edu.galileo.android.androidchat.contactlist.ui.adapters.ContactListAdapter;
import edu.galileo.android.androidchat.contactlist.ui.adapters.OnItemClickListener;
import edu.galileo.android.androidchat.domain.FirebaseHelper;
import edu.galileo.android.androidchat.entities.User;
import edu.galileo.android.androidchat.lib.EventBus;
import edu.galileo.android.androidchat.lib.ImageLoader;

/**
 * Created by Dullahan on 10/07/16.
 */
@Module
public class ContactListModule {
    private ContactListView contactListView;
    private OnItemClickListener onItemClickListener;

    public ContactListModule(ContactListView contactListView, OnItemClickListener onItemClickListener) {
        this.contactListView = contactListView;
        this.onItemClickListener = onItemClickListener;
    }

    @Singleton @Provides
    ContactListView providesContactListView() {
        return contactListView;
    }

    @Singleton @Provides
    ContactListPresenter providesContactListPresenter(EventBus eventBus, ContactListView view, ContactListInteractor listInteractor, ContactListSessionInteractor sessionInteractor) {
        return new ContactListPresenterImpl(eventBus, view, listInteractor, sessionInteractor);
    }

    @Singleton @Provides
    ContactListInteractor providesContactListInteractor(ContactListRepository repository) {
        return new ContactListInteractorImpl(repository);
    }

    @Singleton @Provides
    ContactListSessionInteractor providesContactListSessionInteractor(ContactListRepository repository) {
        return new ContactListSessionInteractorImpl(repository);
    }

    @Singleton @Provides
    ContactListRepository providesContactListRepository(FirebaseHelper helper, EventBus eventBus) {
        return new ContactListRepositoryImpl(helper, eventBus);
    }

    @Provides @Singleton
    ContactListAdapter provideContactListAdapter(List<User> contactList, ImageLoader imageLoader, OnItemClickListener onItemClickListener) {
        return new ContactListAdapter(contactList, imageLoader, onItemClickListener);
    }

    @Provides @Singleton
    OnItemClickListener provideOnItemClickListener() {
        return onItemClickListener;
    }

    @Provides @Singleton
    List<User> providesEmptyList() {
        return new ArrayList<>();
    }
}
