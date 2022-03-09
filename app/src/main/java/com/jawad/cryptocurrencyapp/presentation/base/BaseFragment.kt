package com.jawad.cryptocurrencyapp.presentation.base

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.jawad.cryptocurrencyapp.presentation.MainActivity
import kotlinx.android.synthetic.main.activity_main.*


abstract class BaseFragment: Fragment(), BaseView {

    internal fun showSnackBar(message: String) =
        Snackbar.make((activity as MainActivity).nav_host_fragment, message, Snackbar.LENGTH_SHORT)
            .show()

    override fun onPause() {
        super.onPause()
        hideKeyboard()
    }

    private fun hideKeyboard() {
        requireActivity().currentFocus?.let {
            val imm: InputMethodManager? =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}