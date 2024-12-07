package com.example.libraryapp.data.repository

import com.example.libraryapp.data.datasource.LocalBookDataSource
import com.example.libraryapp.domain.model.Book
import com.example.libraryapp.domain.repository.BookRepository

class BookRepositoryImpl : BookRepository {

    private val localDataSource = LocalBookDataSource()

    override suspend fun getBooks(): List<Book> {
        return try {
            localDataSource.getBooks()
        } catch (e: Exception) {
            throw Exception("Error fetching books", e)
        }
    }

    override suspend fun getBookById(id: Int): Book? {
        return try {
            localDataSource.getBook(id)
        } catch (e: Exception) {
            throw Exception("Error fetching book with ID: $id", e)
        }
    }

    override suspend fun addBook(book: Book) {
        try {
            localDataSource.addBook(book)
        } catch (e: Exception) {
            throw Exception("Error adding book", e)
        }
    }

    override suspend fun updateBook(book: Book) {
        try {
            localDataSource.updateBook(book)
        } catch (e: Exception) {
            throw Exception("Error updating book", e)
        }
    }

    override suspend fun deleteBook(id: Int) {
        try {
            localDataSource.deleteBook(id)
        } catch (e: Exception) {
            throw Exception("Error deleting book with ID: $id", e)
        }
    }
}
