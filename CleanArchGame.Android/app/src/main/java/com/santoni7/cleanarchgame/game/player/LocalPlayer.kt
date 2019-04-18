package com.santoni7.cleanarchgame.game.player

import android.util.Log
import com.santoni7.cleanarchgame.game.GameState
import com.santoni7.cleanarchgame.game.PlayerAction
import io.reactivex.Single

abstract class LocalPlayer<TGameState: GameState, TPlayerAction: PlayerAction>(
    val name: String
) : Player<TGameState, TPlayerAction> {
    init {
        Log.d(LocalPlayer::class.simpleName, "Local player \"$name\" created")
    }
}