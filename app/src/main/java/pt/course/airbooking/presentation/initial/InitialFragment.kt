package pt.course.airbooking.presentation.initial

import android.os.Bundle
import android.view.View
import pt.course.airbooking.R
import pt.course.airbooking.databinding.FragmentInitialBinding
import pt.course.airbooking.presentation.base.BaseFragment

class InitialFragment : BaseFragment<FragmentInitialBinding, InitialViewModel>(
    R.string.title_initial_screen, InitialViewModel::class.java, FragmentInitialBinding::inflate
) {

    companion object {
        @JvmStatic
        fun newInstance() = InitialFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        with(binding) {
            btnFlightstList.setOnClickListener {
                viewModel.openFlightsListScreen()
            }
            btnPlanesInfo.setOnClickListener {
                viewModel.openPlanesInfoScreen()
            }
        }
    }
}