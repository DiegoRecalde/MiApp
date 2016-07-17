package edu.galileo.android.androidchat.login.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.androidchat.domain.di.DomainModule;
import edu.galileo.android.androidchat.lib.di.LibsModule;
import edu.galileo.android.androidchat.login.ui.LoginActivity;

/**
 * Created by Dullahan on 4/07/16.
 */
@Singleton
@Component(modules = { LoginModule.class, LibsModule.class, DomainModule.class })
public interface LoginComponent {
    void inject(LoginActivity activity);
}
