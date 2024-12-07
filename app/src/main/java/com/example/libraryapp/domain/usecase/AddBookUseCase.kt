package com.example.libraryapp.domain.usecase

import com.example.libraryapp.data.repository.BookRepositoryProvider
import com.example.libraryapp.domain.model.Book
import com.example.libraryapp.domain.repository.BookRepository

class AddBookUseCase {
    private val repository: BookRepository = BookRepositoryProvider.provideRepository()
    suspend operator fun invoke(book: Book) {
        return repository.addBook(book)
    }
}