package pt.course.airbooking.presentation.congratulations

import android.os.Bundle
import android.view.View
import pt.course.airbooking.R
import pt.course.airbooking.databinding.FragmentCongratulationsBinding
import pt.course.airbooking.presentation.base.BaseFragment
import java.util.Locale

class CongratulationsFragment :
    BaseFragment<FragmentCongratulationsBinding, CongratulationsViewModel>(
        R.string.title_congratulations_screen,
        CongratulationsViewModel::class.java,
        FragmentCongratulationsBinding::inflate
    ) {

    private var _bookingCode: String? = null
    private val bookingCode get() = _bookingCode ?: throw RuntimeException("Booking code is null!")

    private fun parseArguments() {
        val args = requireArguments()
        if (args.containsKey(BOOKING_CODE_EXTRA_KEY)) {
            _bookingCode = args.getString(BOOKING_CODE_EXTRA_KEY)
        }
    }

    companion object {
        private const val BOOKING_CODE_EXTRA_KEY = "booking_code_extra_key"

        @JvmStatic
        fun newInstance(bookingCode: String) = CongratulationsFragment().apply {
            arguments = Bundle().apply {
                putString(BOOKING_CODE_EXTRA_KEY, bookingCode)
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
    }

    private fun setupViews() {
        with(binding) {
            textViewBookingCode.text = String.format(
                Locale.getDefault(),
                getString(R.string.booking_code_template),
                bookingCode
            )
            buttonMenu.setOnClickListener {
                viewModel.goBack()
            }
        }

    }

}