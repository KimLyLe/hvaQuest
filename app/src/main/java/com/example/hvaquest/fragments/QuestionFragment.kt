package com.example.hvaquest.fragments


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hvaquest.NavigationGraphDirections
import com.example.hvaquest.ViewModels.QuestViewModel
import com.example.hvaquest.R
import com.example.hvaquest.model.Question
import kotlinx.android.synthetic.main.fragment_question.*

/**
 * A simple [Fragment] subclass.
 */
class QuestionFragment : Fragment() {

    private val args: QuestionFragmentArgs by navArgs()
    private var question: Question? = null

    private lateinit var viewModel: QuestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initViews()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(QuestViewModel::class.java)
        question = viewModel.getQuestion(args.questionIndex)
    }

    private fun initViews() {
        btnConfirm.setOnClickListener { onConfirmButtonClicked() }
        tvPageCount.text =
            getString(R.string.tvPageCount, args.questionIndex, viewModel.getQuestSize())
        tvQuestion.text = question?.question ?: getString(R.string.not_found)
        rbAnswer1.text = question?.choices?.get(0) ?: getString(R.string.not_found)
        rbAnswer2.text = question?.choices?.get(1) ?: getString(R.string.not_found)
        rbAnswer3.text = question?.choices?.get(2) ?: getString(R.string.not_found)
        btnConfirm.text = getString(R.string.btnConfirm)
    }

    private fun onConfirmButtonClicked() {
        if (rgQuestion.checkedRadioButtonId > 0) {
            if (view?.findViewById<RadioButton>(rgQuestion.checkedRadioButtonId)?.text == question?.correctAnswer) {
                findNavController().navigate(
                    QuestionFragmentDirections.actionQuestionFragmentToLocationFragment(
                        args.questionIndex
                    )
                )
            } else {
                Toast.makeText(requireContext(), R.string.incorrect, Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), getString(R.string.no_answer), Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun onBackPressed() {
        val dialogClickListener = DialogInterface.OnClickListener { p0, p1 ->
            when (p1) {
                DialogInterface.BUTTON_POSITIVE ->
                    findNavController().navigate(
                        QuestionFragmentDirections.actionQuestionFragmentSelf(
                            args.questionIndex - 1
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
