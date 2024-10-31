package com.example.loto.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoteriaViewModel: ViewModel() {
    private val _lotoNumbers = mutableStateOf(emptyList<Int>())
    val lotoNumbers: State<List<Int>> = _lotoNumbers

    private val _isGenerating = mutableStateOf(false)
    val isGenerating: State<Boolean> = _isGenerating

    fun generateLotoNumbers() {
        viewModelScope.launch {
            _isGenerating.value = true
            _lotoNumbers.value = emptyList()
            val generatedNumbers = mutableListOf<Int>()

            repeat(6) {
                var newNumber: Int
                do {
                    newNumber = (1..60).random()
                } while (newNumber in generatedNumbers)
                generatedNumbers.add(newNumber)
                _lotoNumbers.value = generatedNumbers.sorted()
                delay(2000)
            }

            _isGenerating.value = false
        }
    }
}