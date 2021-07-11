package ru.myproject.myprjpoplibmvp.mvp.presenter.list

import ru.myproject.myprjpoplibmvp.mvp.view.list.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}