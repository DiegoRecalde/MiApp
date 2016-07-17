package edu.galileo.android.androidchat.addcontact.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.androidchat.addcontact.ui.AddContactFragment;
import edu.galileo.android.androidchat.domain.di.DomainModule;
import edu.galileo.android.androidchat.lib.di.LibsModule;

/**
 * Created by Dullahan on 12/07/16.
 */
@Singleton
@Component(modules = { AddContactModule.class, LibsModule.class, DomainModule.class })
public interface AddContactComponent {
    void inject(AddContactFragment fragment);
}
