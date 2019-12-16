package com.example.hvaquest.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.hvaquest.NavigationGraphDirections
import com.example.hvaquest.R
import com.example.hvaquest.ViewModels.WinViewModel
import kotlinx.android.synthetic.main.fragment_win.*

/**
 * A simple [Fragment] subclass.
 */
class WinFragment : Fragment() {

    private lateinit var viewModel: WinViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_win, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WinViewModel::class.java)
        initViews()
    }
    private fun initViews() {
        btnFinish.setOnClickListener {
            findNavController().navigate(NavigationGraphDirections.actionGlobalStartFragment())
        }
    }
}
