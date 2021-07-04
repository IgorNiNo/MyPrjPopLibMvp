package ru.myproject.myprjpoplibmvp.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.myproject.myprjpoplibmvp.mvp.model.entity.GithubUser
import ru.myproject.myprjpoplibmvp.ui.fragment.UsersFragment
import ru.myproject.myprjpoplibmvp.ui.fragment.UserFragment

class AndroidScreens : IScreens {

    override fun users() = FragmentScreen {
        UsersFragment.newInstance()
    }

    override fun user(user: GithubUser) = FragmentScreen {
        UserFragment.newInstance(user)
    }
}