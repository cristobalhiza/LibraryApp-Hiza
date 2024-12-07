package com.example.libraryapp.domain.usecase

import com.example.libraryapp.domain.model.Book
import com.example.libraryapp.domain.repository.BookRepository

class UpdateBookUseCase(private val repository: BookRepository) {

    suspend operator fun invoke(book: Book) {
        repository.updateBook(book)
    }
}
