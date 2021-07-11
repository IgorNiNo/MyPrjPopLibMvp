package ru.myproject.myprjpoplibmvp.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.myproject.myprjpoplibmvp.mvp.view.MainView
import ru.myproject.myprjpoplibmvp.navigation.AndroidScreens

class MainPresenter(private val router: Router, val screens: AndroidScreens) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}