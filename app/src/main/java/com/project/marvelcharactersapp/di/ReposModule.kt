package com.project.marvelcharactersapp.di

import android.content.Context
import com.project.marvelcharactersapp.network.MarvelApi
import com.project.marvelcharactersapp.ui.features.characterDetails.data.repoImpl.CharacterDetailsRepoImpl
import com.project.marvelcharactersapp.ui.features.characterDetails.domain.CharacterDetailsRepo
import com.project.marvelcharactersapp.ui.features.characters.data.repoImpl.CharactersRepoImpl
import com.project.marvelcharactersapp.ui.features.characters.domain.CharactersRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReposModule {

    @Provides
    @Singleton
    fun provideCreateCharactersRepo(
        api: MarvelApi,
        @ApplicationContext context: Context,
    ): CharactersRepo {
        return CharactersRepoImpl(api)
    }

    @Provides
    @Singleton
    fun provideCreateCharacterDetailsRepo(
        api: MarvelApi,
        @ApplicationContext context: Context,
    ): CharacterDetailsRepo {
        return CharacterDetailsRepoImpl(api)
    }
}