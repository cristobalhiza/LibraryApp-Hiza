package com.example.libraryapp.domain.usecase

import com.example.libraryapp.domain.repository.BookRepository

class DeleteBookUseCase(private val repository: BookRepository) {

    suspend operator fun invoke(id: Int) {
        repository.deleteBook(id)
    }
}
