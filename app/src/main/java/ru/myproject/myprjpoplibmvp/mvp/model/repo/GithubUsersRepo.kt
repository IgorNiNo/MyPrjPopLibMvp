package ru.myproject.myprjpoplibmvp.mvp.model.repo

import ru.myproject.myprjpoplibmvp.mvp.model.entity.GithubUser

class GithubUsersRepo {

    private val repositories = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5"),
        GithubUser("login6"),
        GithubUser("login7"),
        GithubUser("login8"),
        GithubUser("login9"),
        GithubUser("login10")
    )

    fun getUsers(): List<GithubUser> {
        return repositories
    }
}