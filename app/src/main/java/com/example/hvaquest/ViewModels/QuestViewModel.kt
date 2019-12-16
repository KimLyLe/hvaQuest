package com.example.hvaquest.ViewModels

import androidx.lifecycle.ViewModel
import com.example.hvaquest.data.QuestRepository
import com.example.hvaquest.model.Question

class QuestViewModel : ViewModel() {
    private val repository = QuestRepository()

    fun getQuestion(index: Int): Question? = repository.getHvaQuest(index)

    fun getQuestSize(): Int = repository.getQuestSize()
}