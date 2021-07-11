package ru.myproject.myprjpoplibmvp.navigation

import com.github.terrakok.cicerone.Screen
import ru.myproject.myprjpoplibmvp.mvp.model.entity.GithubUser

interface IScreens {
    fun users(): Screen
    fun user(user: GithubUser): Screen
}