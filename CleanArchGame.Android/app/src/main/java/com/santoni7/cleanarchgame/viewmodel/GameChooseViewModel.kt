package com.santoni7.cleanarchgame.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.santoni7.cleanarchgame.MyApp
import com.santoni7.cleanarchgame.domain.GetGamesUseCase
import com.santoni7.cleanarchgame.model.GameEntity
import com.santoni7.cleanarchgame.viewmodel.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GameChooseViewModel : BaseViewModel() {

    @Inject lateinit var getGamesUseCase: GetGamesUseCase

    init {
        MyApp.component.inject(this)
    }

    val gamesListLiveData = MutableLiveData<List<GameEntity>>()
    val errorLiveData = MutableLiveData<String>()

    fun initGamesList() {
        disposables.add(
            getGamesUseCase.getGames()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    gamesListLiveData.postValue(it)
                }, {
                   handleError(it)
                })
        )
    }

    private fun handleError(t: Throwable) {
        errorLiveData.postValue(t.message)
    }

    fun chooseGame(gameEntity: GameEntity) {

    }
}