package pt.course.airbooking.presentation.personaldocument

import android.os.Bundle
import android.view.View
import android.widget.Toast
import pt.course.airbooking.R
import pt.course.airbooking.databinding.FragmentPersonalDocumentBinding
import pt.course.airbooking.domain.entity.BookingResult
import pt.course.airbooking.domain.entity.Passport
import pt.course.airbooking.domain.entity.Sex
import pt.course.airbooking.presentation.base.BaseFragment
import pt.course.airbooking.presentation.base.State

class PersonalDocumentFragment :
    BaseFragment<FragmentPersonalDocumentBinding, PersonalDocumentViewModel>(
        R.string.title_personal_document_screen,
        PersonalDocumentViewModel::class.java,
        FragmentPersonalDocumentBinding::inflate
    ) {

    private var _flightId: Long? = null
    private val flightId get() = _flightId ?: throw RuntimeException("Flight id is null!")

    private fun parseArguments() {
        val args = requireArguments()

        if (args.containsKey(FLIGHT_ID_EXTRA_KEY)) {
            _flightId = args.getLong(FLIGHT_ID_EXTRA_KEY)
        } else {
            throw RuntimeException("Flight id argument is required but not exists.")
        }
    }

    companion object {

        private const val FLIGHT_ID_EXTRA_KEY = "flight_id_extra_key"

        @JvmStatic
        fun newInstance(flightId: Long) = PersonalDocumentFragment().apply {
            arguments = Bundle().apply {
                putLong(FLIGHT_ID_EXTRA_KEY, flightId)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArguments()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        observeViewModel()
    }

    private fun setupViews() {
        with(binding) {
            buttonSave.setOnClickListener {

                val surname = editTextSurname.text.toString().trim()
                val name = editTextName.text.toString().trim()
                val patronymic = editTextPatronymic.text.toString().trim().ifBlank { null }

                val sex = when (buttonGroupSex.checkedButtonId) {
                    R.id.male -> Sex.MALE
                    R.id.female -> Sex.FEMALE
                    else -> throw RuntimeException("Unknown sex")
                }
                val dateOfBirth = editTextDateOfBirth.text.toString().trim()
                val documentNumber = editTextDocumentNumber.text.toString().trim()

                val passport = Passport(sex, surname, name, patronymic, dateOfBirth, documentNumber)

                viewModel.bookTicket(passport, flightId)
            }
        }
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                State.Initial -> {}
                State.EmptyResult -> {}
                is State.Content -> {
                    if (state.data is BookingResult.InvalidInput) {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.fill_all_document_data),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }


}