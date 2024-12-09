package com.example.libraryapp.domain.usecase

import com.example.libraryapp.data.repository.BookRepositoryProvider
import com.example.libraryapp.domain.model.Book
import com.example.libraryapp.domain.repository.BookRepository

class GetBooksUseCase(provideRepository: BookRepository) {
    private val repository = BookRepositoryProvider.provideRepository()

    suspend operator fun invoke(): List<Book> {
        return repository.getBooks()
    }
}