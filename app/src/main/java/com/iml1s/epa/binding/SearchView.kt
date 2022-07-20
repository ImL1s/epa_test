package com.iml1s.epa.binding

import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import timber.log.Timber


@BindingAdapter("app:query")
fun setSearchViewQuery(view: SearchView, string: String) {
    view.setQuery(string, false)
}

@BindingAdapter("queryAttrChanged")
fun setSearchViewQueryListener(view: SearchView, listener: InverseBindingListener) {
    view.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener,
        SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            listener.onChange()
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            listener.onChange()
            return true
        }
    })
}

@InverseBindingAdapter(attribute = "app:query")
fun getSearchViewTextValue(view: SearchView): String {
    return view.query.toString()
}

@BindingAdapter("app:onCloseListener")
fun setSearchViewOnCloseListener(view: SearchView, onClose: () -> Unit) {
    view.setOnCloseListener listener@{
        onClose()
        return@listener false
    }
}

@BindingAdapter("app:onSearchClickListener")
fun setOnSearchClickListener(view: SearchView, onSearchClick: () -> Unit) {
    view.setOnSearchClickListener {
        onSearchClick()
    }
}