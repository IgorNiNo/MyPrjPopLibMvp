package ru.myproject.myprjpoplibmvp.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.myproject.myprjpoplibmvp.mvp.model.entity.GithubUser
import ru.myproject.myprjpoplibmvp.mvp.model.repo.GithubUsersRepo
import ru.myproject.myprjpoplibmvp.mvp.presenter.list.IUserListPresenter
import ru.myproject.myprjpoplibmvp.mvp.view.UsersView
import ru.myproject.myprjpoplibmvp.mvp.view.list.UserItemView
import ru.myproject.myprjpoplibmvp.navigation.IScreens

class UsersPresenter(
    private val usersRepo: GithubUsersRepo,
    private val router: Router,
    val screens: IScreens
) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {

        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }

        override fun getCount() = users.size
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        // переход на экран пользователя с помощью router.navigateTo
        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.users[itemView.pos]
            router.navigateTo(screens.user(user))
        }
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.clear()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}