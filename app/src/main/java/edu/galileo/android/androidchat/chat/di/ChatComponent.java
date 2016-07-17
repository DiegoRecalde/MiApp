package edu.galileo.android.androidchat.chat.di;

import javax.inject.Singleton;

import dagger.Component;
import edu.galileo.android.androidchat.chat.ui.ChatActivity;
import edu.galileo.android.androidchat.domain.di.DomainModule;
import edu.galileo.android.androidchat.lib.di.LibsModule;

/**
 * Created by Dullahan on 10/07/16.
 */
@Singleton
@Component(modules = { ChatModule.class, LibsModule.class, DomainModule.class })
public interface ChatComponent {
    void inject(ChatActivity activity);
}
