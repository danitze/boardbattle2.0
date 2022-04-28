package com.example.boardbattle2_0.app

import com.example.boardbattle2_0.game.data.GameState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideGameState(): GameState = GameState()
}