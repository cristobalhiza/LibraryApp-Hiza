package com.example.libraryapp.domain.usecase

import com.example.libraryapp.data.repository.BookRepositoryProvider

object UseCaseProvider {
    private var getBooksUseCase: GetBooksUseCase? = null
    private var getBookByIdUseCase: GetBookByIdUseCase? = null
    private var addBookUseCase: AddBookUseCase? = null
    private var deleteBookUseCase: DeleteBookUseCase? = null

    fun provideGetBooksUseCase(): GetBooksUseCase {
        if (getBooksUseCase == null) {
            getBooksUseCase = GetBooksUseCase(BookRepositoryProvider.provideRepository())
        }
        return getBooksUseCase!!
    }

    fun provideGetBookByIdUseCase(): GetBookByIdUseCase {
        if (getBookByIdUseCase == null) {
            getBookByIdUseCase = GetBookByIdUseCase(BookRepositoryProvider.provideRepository())
        }
        return getBookByIdUseCase!!
    }

    fun provideAddBookUseCase(): AddBookUseCase {
        if (addBookUseCase == null) {
            addBookUseCase = AddBookUseCase(BookRepositoryProvider.provideRepository())
        }
        return addBookUseCase!!
    }

    fun provideDeleteBookUseCase(): DeleteBookUseCase {
        if (deleteBookUseCase == null) {
            deleteBookUseCase = DeleteBookUseCase(BookRepositoryProvider.provideRepository())
        }
        return deleteBookUseCase!!
    }
}
