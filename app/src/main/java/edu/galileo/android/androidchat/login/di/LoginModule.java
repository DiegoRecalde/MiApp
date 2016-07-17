package edu.galileo.android.androidchat.login.di;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.androidchat.domain.FirebaseHelper;
import edu.galileo.android.androidchat.lib.EventBus;
import edu.galileo.android.androidchat.login.LoginInteractor;
import edu.galileo.android.androidchat.login.LoginInteractorImpl;
import edu.galileo.android.androidchat.login.LoginPresenter;
import edu.galileo.android.androidchat.login.LoginPresenterImpl;
import edu.galileo.android.androidchat.login.LoginRepository;
import edu.galileo.android.androidchat.login.LoginRepositoryImpl;
import edu.galileo.android.androidchat.login.ui.LoginView;

/**
 * Created by Dullahan on 4/07/16.
 */
@Module
public class LoginModule {
    private LoginView loginView;

    public LoginModule(LoginView loginView) {
        this.loginView = loginView;
    }

    @Singleton @Provides
    LoginView providesLoginView() {
        return loginView;
    }

    @Singleton @Provides
    LoginPresenter providesLoginPresenter(EventBus eventBus, LoginView loginView, LoginInteractor loginInteractor) {
        return new LoginPresenterImpl(eventBus, loginView, loginInteractor);
    }

    @Singleton @Provides
    LoginInteractor providesLoginInteractor(LoginRepository loginRepository) {
        return new LoginInteractorImpl(loginRepository);
    }

    @Singleton @Provides
    LoginRepository providesLoginRepository(FirebaseAuth authReference, FirebaseHelper helper, DatabaseReference myUserReference, EventBus eventBus) {
        return new LoginRepositoryImpl(authReference, helper, myUserReference, eventBus);
    }
}
