package com.example.boardbattle2_0.services

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.boardbattle2_0.utils.GAME_DATA_STORE
import com.example.boardbattle2_0.data.GameState
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameDataStoreService @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson
    ) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = GAME_DATA_STORE
    )

    val savedGameStateFlow: Flow<GameState> = context.dataStore.data.map { prefs ->
        val jsonString = prefs[GAME_STATE_KEY] ?: return@map GameState()
        gson.fromJson(jsonString, GameState::class.java)
    }

    suspend fun saveGameState(gameState: GameState) {
        val jsonString = gson.toJson(gameState)
        context.dataStore.edit { prefs ->
            prefs[GAME_STATE_KEY] = jsonString
        }
    }

    suspend fun clearGameState() {
        context.dataStore.edit { prefs ->
            prefs.remove(GAME_STATE_KEY)
        }
    }

    companion object {
        val GAME_STATE_KEY = stringPreferencesKey(name = "game_state")
    }
}