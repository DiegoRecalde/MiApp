package edu.galileo.android.androidchat.chat.di;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import edu.galileo.android.androidchat.chat.ChatInteractor;
import edu.galileo.android.androidchat.chat.ChatInteractorImpl;
import edu.galileo.android.androidchat.chat.ChatPresenter;
import edu.galileo.android.androidchat.chat.ChatPresenterImpl;
import edu.galileo.android.androidchat.chat.ChatRepository;
import edu.galileo.android.androidchat.chat.ChatRepositoryImpl;
import edu.galileo.android.androidchat.chat.ChatSessionInteractor;
import edu.galileo.android.androidchat.chat.ChatSessionInteractorImpl;
import edu.galileo.android.androidchat.chat.ui.ChatView;
import edu.galileo.android.androidchat.chat.ui.adapters.ChatAdapter;
import edu.galileo.android.androidchat.domain.FirebaseHelper;
import edu.galileo.android.androidchat.entities.ChatMessage;
import edu.galileo.android.androidchat.lib.EventBus;

/**
 * Created by Dullahan on 10/07/16.
 */
@Module
public class ChatModule {
    private ChatView chatView;

    public ChatModule(ChatView chatView) {
        this.chatView = chatView;
    }

    @Singleton @Provides
    ChatView providesChatView() {
        return chatView;
    }

    @Singleton @Provides
    ChatPresenter providesChatPresenter(EventBus eventBus, ChatView view, ChatInteractor chatInteractor, ChatSessionInteractor sessionInteractor) {
        return new ChatPresenterImpl(eventBus, view, chatInteractor, sessionInteractor);
    }

    @Singleton @Provides
    ChatInteractor providesChatInteractor(ChatRepository repository) {
        return new ChatInteractorImpl(repository);
    }

    @Singleton @Provides
    ChatSessionInteractor providesChatSessionInteractor(ChatRepository repository) {
        return new ChatSessionInteractorImpl(repository);
    }

    @Singleton @Provides
    ChatRepository providesChatRepository(EventBus eventBus, FirebaseHelper helper) {
        return new ChatRepositoryImpl(eventBus, helper);
    }

    @Provides @Singleton
    ChatAdapter providesChatAdapter(List<ChatMessage> chatMessages) {
        return new ChatAdapter(chatMessages);
    }

    @Provides @Singleton
    List<ChatMessage> providesEmptyList() {
        return new ArrayList<>();
    }
}
