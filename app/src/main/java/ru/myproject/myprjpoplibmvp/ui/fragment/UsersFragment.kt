package ru.myproject.myprjpoplibmvp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.myproject.myprjpoplibmvp.databinding.FragmentUsersBinding
import ru.myproject.myprjpoplibmvp.mvp.model.repo.GithubUsersRepo
import ru.myproject.myprjpoplibmvp.mvp.presenter.UsersPresenter
import ru.myproject.myprjpoplibmvp.mvp.view.UsersView
import ru.myproject.myprjpoplibmvp.navigation.AndroidScreens
import ru.myproject.myprjpoplibmvp.ui.App
import ru.myproject.myprjpoplibmvp.ui.BackButtonListener
import ru.myproject.myprjpoplibmvp.ui.adapter.UsersRvAdapter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            GithubUsersRepo(),
            App.instance.router,
            AndroidScreens()
        )
    }

    var adapter: UsersRvAdapter? = null
    private var vb: FragmentUsersBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUsersBinding.inflate(inflater, container, false)
            .also {
                vb = it
            }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRvAdapter(presenter.usersListPresenter)
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}