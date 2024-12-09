package com.example.libraryapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.libraryapp.domain.model.Book
import com.example.libraryapp.domain.usecase.GetBookByIdUseCase
import com.example.libraryapp.domain.usecase.UseCaseProvider
import kotlinx.coroutines.launch
import com.example.libraryapp.domain.usecase.DeleteBookUseCase

class BookDetailViewModel : ViewModel() {

    private val getBookByIdUseCase: GetBookByIdUseCase = UseCaseProvider.provideGetBookByIdUseCase()
    private val deleteBookUseCase: DeleteBookUseCase = UseCaseProvider.provideDeleteBookUseCase()

    private val _book = MutableLiveData<Book?>()
    val book: MutableLiveData<Book?> = _book

    private val _loading = MutableLiveData<Boolean>()

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun loadBook(id: Int) {
        viewModelScope.launch {
            _loading.value = true
            try {
                _book.value = getBookByIdUseCase(id)
                _error.value = null
            } catch (e: Exception) {
                _book.value = null
                _error.value = e.message ?: "Error loading book details"
            } finally {
                _loading.value = false
            }
        }
    }
    fun deleteBook(id: Int) {
        viewModelScope.launch {
            try {
                deleteBookUseCase(id)
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message ?: "Error deleting book"
            }
        }
    }
}