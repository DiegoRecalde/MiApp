package edu.galileo.android.androidchat.login;

import edu.galileo.android.androidchat.login.events.LoginEvent;

/**
 * Created by avalo.
 */
public interface LoginPresenter {
    void onCreate();
    void onDestroy();

    void registerNewUser(String email, String password);
    void validateLogin(String email, String password);

    void onEventMainThread(LoginEvent event);
}
