package com.elve8valley.astutetest.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.IllegalStateException

abstract class   BaseFragment<VB: ViewBinding>(
    private val bindingInflater : ( inflater: LayoutInflater) -> VB
) : Fragment() {
    private var _binding:VB? = null

    val binding : VB
        get() =_binding as VB
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        _binding=bindingInflater.invoke(inflater)

        if(_binding==null)
            throw IllegalStateException("Binding cannot be null")

        return this._binding!!.root
    }

    private var persistingView: View? = null

    private fun persistingView(view: View): View {
        val root = persistingView
        if (root == null) {
            persistingView = view
            return view
        } else {
            (root.parent as? ViewGroup)?.removeView(root)
            return root
        }
    }
}