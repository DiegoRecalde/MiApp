package edu.galileo.android.androidchat;

import android.app.Application;
import android.content.Context;

import com.google.firebase.database.FirebaseDatabase;

import edu.galileo.android.androidchat.addcontact.di.AddContactComponent;
import edu.galileo.android.androidchat.addcontact.di.AddContactModule;
import edu.galileo.android.androidchat.addcontact.di.DaggerAddContactComponent;
import edu.galileo.android.androidchat.addcontact.ui.AddContactView;
import edu.galileo.android.androidchat.chat.di.ChatComponent;
import edu.galileo.android.androidchat.chat.di.ChatModule;
import edu.galileo.android.androidchat.chat.di.DaggerChatComponent;
import edu.galileo.android.androidchat.chat.ui.ChatActivity;
import edu.galileo.android.androidchat.chat.ui.ChatView;
import edu.galileo.android.androidchat.contactlist.di.ContactListComponent;
import edu.galileo.android.androidchat.contactlist.di.ContactListModule;
import edu.galileo.android.androidchat.contactlist.di.DaggerContactListComponent;
import edu.galileo.android.androidchat.contactlist.ui.ContactListActivity;
import edu.galileo.android.androidchat.contactlist.ui.ContactListView;
import edu.galileo.android.androidchat.contactlist.ui.adapters.OnItemClickListener;
import edu.galileo.android.androidchat.domain.di.DomainModule;
import edu.galileo.android.androidchat.lib.di.LibsModule;
import edu.galileo.android.androidchat.login.di.DaggerLoginComponent;
import edu.galileo.android.androidchat.login.di.LoginComponent;
import edu.galileo.android.androidchat.login.di.LoginModule;
import edu.galileo.android.androidchat.login.ui.LoginActivity;
import edu.galileo.android.androidchat.login.ui.LoginView;
import edu.galileo.android.androidchat.signup.di.DaggerSignUpComponent;
import edu.galileo.android.androidchat.signup.di.SignUpComponent;
import edu.galileo.android.androidchat.signup.ui.SignUpActivity;


/**
 * Created by avalo.
 */
public class AndroidChatApplication extends Application {
    private static Context mContext;
    private DomainModule domainModule;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();

        setupFirebase();
        initDomainModule();
    }

    private void initDomainModule() {
        domainModule = new DomainModule();
    }

    public static Context getAppContext() {
        return mContext;
    }

    private void setupFirebase() {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    public LoginComponent getLoginComponent(LoginActivity activity, LoginView loginView) {
        return DaggerLoginComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .loginModule(new LoginModule(loginView))
                .domainModule(domainModule)
                .build();
    }

    public SignUpComponent getSignUpComponent(SignUpActivity activity, LoginView loginView) {
        return DaggerSignUpComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .loginModule(new LoginModule(loginView))
                .domainModule(domainModule)
                .build();
    }

    public ContactListComponent getContactListComponent(ContactListActivity activity, ContactListView contactListView, OnItemClickListener onItemClickListener) {
        return DaggerContactListComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .contactListModule(new ContactListModule(contactListView, onItemClickListener))
                .domainModule(domainModule)
                .build();
    }

    public ChatComponent getChatComponent(ChatActivity activity, ChatView chatView) {
        return DaggerChatComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .chatModule(new ChatModule(chatView))
                .domainModule(domainModule)
                .build();
    }

    public AddContactComponent getAddContactComponent(AddContactView addContactView) {
        return DaggerAddContactComponent
                .builder()
                .libsModule(new LibsModule())
                .addContactModule(new AddContactModule(addContactView))
                .domainModule(domainModule)
                .build();
    }
}
