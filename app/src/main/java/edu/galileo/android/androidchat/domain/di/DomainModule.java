package edu.galileo.android.androidchat.domain.di;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.androidchat.domain.FirebaseHelper;

/**
 * Created by Dullahan on 10/07/16.
 */
@Module
public class DomainModule {
    public DomainModule() { }

    @Singleton @Provides
    FirebaseHelper providesFirebaseHelper(DatabaseReference dataReference, FirebaseAuth firebaseAuth) {
        return new FirebaseHelper(dataReference, firebaseAuth);
    }

    @Singleton @Provides
    DatabaseReference providesDatabaseReference() {
        return FirebaseDatabase.getInstance().getReference();
    }

    @Singleton @Provides
    FirebaseAuth providesfirebaseAuth() {
        return FirebaseAuth.getInstance();
    }
}
