package com.example.hvaquest.fragments


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hvaquest.NavigationGraphDirections
import com.example.hvaquest.R
import com.example.hvaquest.ViewModels.LocationViewModel
import com.example.hvaquest.model.Question
import kotlinx.android.synthetic.main.fragment_location.*

/**
 * A simple [Fragment] subclass.
 */
class LocationFragment : Fragment() {

    private val args: LocationFragmentArgs by navArgs()
    private var question: Question? = null

    private lateinit var viewModel: LocationViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
        initViewModel()
        initViews()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(LocationViewModel::class.java)
        question = viewModel.getQuestion(args.questionIndex)
    }

    private fun initViews() {
        btnNextQuestion.setOnClickListener { onNextQuestionButtonClicked() }
        question?.let { ivLocation.setImageDrawable(ContextCompat.getDrawable(requireContext(), it.clue)) }
    }

    private fun onNextQuestionButtonClicked() {
        if (args.questionIndex + 1 < viewModel.getQuestSize()) {
            findNavController().navigate(LocationFragmentDirections.actionLocationFragmentToQuestionFragment(
                args.questionIndex + 1
            ))
        } else {
            findNavController().navigate(LocationFragmentDirections.actionLocationFragmentToWinFragment())
        }
    }

    private fun onBackPressed() {
        val dialogClickListener = DialogInterface.OnClickListener { p0, p1 ->
            when (p1) {
                DialogInterface.BUTTON_POSITIVE ->
                    findNavController().navigate(
                        LocationFragmentDirections.actionLocationFragmentToQuestionFragment(
                            args.questionIndex
                        )
                    )
                DialogInterface.BUTTON_NEGATIVE ->
                    findNavController().navigate(
                        NavigationGraphDirections.actionGlobalStartFragment()
                    )
                DialogInterface.BUTTON_NEUTRAL -> p0.cancel()
            }
        }
        val builder = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.dialog_title))
            .setMessage(getString(R.string.dialog_message))
            .setNeutralButton(getString(R.string.cancel), dialogClickListener)
            .setNegativeButton(getString(R.string.stop), dialogClickListener)

        if (args.questionIndex != 1) {
            builder.setPositiveButton(getString(R.string.back), dialogClickListener)
        }
        builder.show()
    }

}
