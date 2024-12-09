package com.example.libraryapp.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.libraryapp.R
import com.example.libraryapp.databinding.FragmentBookingDetailBinding
import com.example.libraryapp.presentation.viewmodel.BookDetailViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class BookingDetailFragment : Fragment(R.layout.fragment_booking_detail) {

    private var _binding: FragmentBookingDetailBinding? = null
    private val binding get() = _binding!!

    private val args: BookingDetailFragmentArgs by navArgs()

    private val viewModel: BookDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookingDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookId = args.bookId
        viewModel.loadBook(bookId)

        viewModel.book.observe(viewLifecycleOwner) { book ->
            if (book != null) {
                binding.apply {
                    titleTextView.text = book.title
                    authorTextView.text = book.author
                    yearTextView.text = book.year.toString()
                    descriptionTextView.text = book.description
                    availabilityTextView.text =
                        if (book.isAvailable) "Available" else "Checked Out"

                    bookImageView.load(book.imageUrl) {
                        crossfade(true)
                        placeholder(R.drawable.baseline_broken_image_24)
                        error(R.drawable.baseline_broken_image_24)
                    }

                    deleteButton.setOnClickListener {
                        showDeleteConfirmationDialog(bookId)
                    }
                }
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                binding.errorTextView.apply {
                    text = it
                    visibility = View.VISIBLE
                }
            }
        }
    }

    private fun showDeleteConfirmationDialog(bookId: Int) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Delete Book")
            .setMessage("Are you sure you want to delete this book?")
            .setPositiveButton("Yes") { _, _ ->
                viewModel.deleteBook(bookId)
                Snackbar.make(binding.root, "Book deleted successfully", Snackbar.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
