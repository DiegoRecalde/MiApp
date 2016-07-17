package edu.galileo.android.androidchat.login;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import edu.galileo.android.androidchat.AndroidChatApplication;
import edu.galileo.android.androidchat.R;
import edu.galileo.android.androidchat.domain.FirebaseHelper;
import edu.galileo.android.androidchat.entities.User;
import edu.galileo.android.androidchat.lib.EventBus;
import edu.galileo.android.androidchat.login.events.LoginEvent;

/**
 * Created by avalo.
 */
public class LoginRepositoryImpl implements LoginRepository {
    private FirebaseAuth authReference;
    private FirebaseHelper helper;
    private DatabaseReference myUserReference;
    private EventBus eventBus;

    public LoginRepositoryImpl(FirebaseAuth authReference, FirebaseHelper helper, DatabaseReference myUserReference, EventBus eventBus) {
        this.authReference = authReference;
        this.helper = helper;
        this.myUserReference = myUserReference;
        this.eventBus = eventBus;
    }

    @Override
    public void signUp(final String email, final String password) {
        if (!email.isEmpty() && !password.isEmpty()) {
            authReference.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            postEvent(LoginEvent.onSignUpSuccess);
                            signIn(email, password);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            postEvent(LoginEvent.onSignUpError, e.getMessage());
                        }
                    });
        } else {
            postEvent(LoginEvent.onSignUpError, AndroidChatApplication.getAppContext().getString(R.string.login_error_message_empty));
        }
    }

    @Override
    public void signIn(String email, String password) {
        if (email != null && password != null) {
            if (!email.isEmpty() && !password.isEmpty()) {
                authReference.signInWithEmailAndPassword(email, password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                initSignIn();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                postEvent(LoginEvent.onSignInError, e.getMessage());
                            }
                        });
            } else {
                postEvent(LoginEvent.onSignInError, AndroidChatApplication.getAppContext().getString(R.string.login_error_message_empty));
            }
        } else {
            if(authReference.getCurrentUser() != null) {
                initSignIn();
            } else {
                postEvent(LoginEvent.onFailedToRecoverSession);
            }
        }
    }

    private void initSignIn() {
        myUserReference = helper.getMyUserReference();
        myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User currentUser = dataSnapshot.getValue(User.class);

                if(currentUser == null) {
                    registerNewUser();
                }

                helper.changeUserConnectionStatus(User.ONLINE);
                postEvent(LoginEvent.onSignInSuccess);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    private void registerNewUser() {
        String email = helper.getAuthUserEmail();
        if(email != null) {
            User currentUser = new User();
            currentUser.setEmail(email);
            myUserReference.setValue(currentUser);
        }
    }

    private void postEvent(int type, String errorMessage) {
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        if (errorMessage != null) {
            loginEvent.setErrorMessage(errorMessage);
        }
        eventBus.post(loginEvent);
    }

    private void postEvent(int type){
        postEvent(type, null);
    }
}
