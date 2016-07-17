package edu.galileo.android.androidchat.contactlist.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.androidchat.contactlist.ui.ContactListActivity;
import edu.galileo.android.androidchat.domain.di.DomainModule;
import edu.galileo.android.androidchat.lib.di.LibsModule;

/**
 * Created by Dullahan on 10/07/16.
 */
@Singleton
@Component(modules = { ContactListModule.class, LibsModule.class, DomainModule.class })
public interface ContactListComponent {
    void inject(ContactListActivity activity);
}
