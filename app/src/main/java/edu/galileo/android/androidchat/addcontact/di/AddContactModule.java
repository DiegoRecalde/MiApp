package edu.galileo.android.androidchat.addcontact.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.androidchat.addcontact.AddContactInteractor;
import edu.galileo.android.androidchat.addcontact.AddContactInteractorImpl;
import edu.galileo.android.androidchat.addcontact.AddContactPresenter;
import edu.galileo.android.androidchat.addcontact.AddContactPresenterImpl;
import edu.galileo.android.androidchat.addcontact.AddContactRepository;
import edu.galileo.android.androidchat.addcontact.AddContactRepositoryImpl;
import edu.galileo.android.androidchat.addcontact.ui.AddContactView;
import edu.galileo.android.androidchat.domain.FirebaseHelper;
import edu.galileo.android.androidchat.lib.EventBus;

/**
 * Created by Dullahan on 12/07/16.
 */
@Module
public class AddContactModule {
    private AddContactView addContactView;

    public AddContactModule(AddContactView addContactView) {
        this.addContactView = addContactView;
    }

    @Singleton @Provides
    AddContactView providesAddContactView() {
        return addContactView;
    }

    @Singleton @Provides
    AddContactPresenter providesAddContactPresenter(EventBus eventBus, AddContactView view, AddContactInteractor interactor) {
        return new AddContactPresenterImpl(eventBus, view, interactor);
    }

    @Singleton @Provides
    AddContactInteractor providesAddContactInteractor(AddContactRepository repository) {
        return new AddContactInteractorImpl(repository);
    }

    @Singleton @Provides
    AddContactRepository providesAddContactRepository(EventBus eventBus, FirebaseHelper helper) {
        return new AddContactRepositoryImpl(eventBus, helper);
    }
}
