package edu.galileo.android.androidchat.domain.di;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Dullahan on 10/07/16.
 */
@Singleton
@Component(modules = { DomainModule.class })
public interface DomainComponent { }
