package com.example.hvaquest.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.hvaquest.ViewModels.HomeViewModel
import com.example.hvaquest.R
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private val questionIndex: Int = 0
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }

    private fun initViews() {
        btnStartQuest.setOnClickListener { onStartQuestButtonClicked() }
    }

    private fun onStartQuestButtonClicked() {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToQuestionFragment(
            questionIndex + 1
        ))
    }
}
