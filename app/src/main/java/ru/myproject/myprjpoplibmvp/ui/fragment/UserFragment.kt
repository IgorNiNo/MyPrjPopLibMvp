package ru.myproject.myprjpoplibmvp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.myproject.myprjpoplibmvp.databinding.FragmentUserBinding
import ru.myproject.myprjpoplibmvp.mvp.model.entity.GithubUser
import ru.myproject.myprjpoplibmvp.mvp.presenter.UserPresenter
import ru.myproject.myprjpoplibmvp.mvp.view.UserView
import ru.myproject.myprjpoplibmvp.ui.App
import ru.myproject.myprjpoplibmvp.ui.BackButtonListener

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    companion object {
        private const val USER = "USER"
        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER, user)
            }
        }
    }

    private val presenter: UserPresenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER) as GithubUser
        UserPresenter(user, App.instance.router)
    }

    private var vb: FragmentUserBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUserBinding.inflate(inflater, container, false)
            .also {
                vb = it
            }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun setLogin(text: String) {
        vb?.userLogin?.text = text
    }

    override fun backPressed() = presenter.backPressed()
}