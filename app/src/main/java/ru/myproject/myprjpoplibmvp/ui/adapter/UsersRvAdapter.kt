package ru.myproject.myprjpoplibmvp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.myproject.myprjpoplibmvp.databinding.ItemUserBinding
import ru.myproject.myprjpoplibmvp.mvp.presenter.list.IUserListPresenter
import ru.myproject.myprjpoplibmvp.mvp.view.list.UserItemView

class UsersRvAdapter(private val presenter: IUserListPresenter) :
    RecyclerView.Adapter<UsersRvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(private val vb: ItemUserBinding) : RecyclerView.ViewHolder(vb.root),
        UserItemView {

        override var pos = -1

        override fun setLogin(loginText: String) = with(vb) {
            tvLogin.text = loginText
        }
    }
}