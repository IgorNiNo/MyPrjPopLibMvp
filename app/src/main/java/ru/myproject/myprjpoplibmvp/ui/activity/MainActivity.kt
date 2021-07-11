package ru.myproject.myprjpoplibmvp.ui.activity

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.myproject.myprjpoplibmvp.R
import ru.myproject.myprjpoplibmvp.databinding.ActivityMainBinding
import ru.myproject.myprjpoplibmvp.mvp.presenter.MainPresenter
import ru.myproject.myprjpoplibmvp.mvp.view.MainView
import ru.myproject.myprjpoplibmvp.navigation.AndroidScreens
import ru.myproject.myprjpoplibmvp.ui.App
import ru.myproject.myprjpoplibmvp.ui.BackButtonListener

class MainActivity : MvpAppCompatActivity(), MainView {

    private val navigator = AppNavigator(this, R.id.container)
    private val presenter by moxyPresenter { MainPresenter(App.instance.router, AndroidScreens()) }
    private var vb: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }
}