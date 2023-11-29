package pt.course.airbooking.presentation.planeslist

import android.os.Bundle
import android.view.View
import pt.course.airbooking.R
import pt.course.airbooking.databinding.FragmentPlanesListBinding
import pt.course.airbooking.presentation.base.BaseFragment
import pt.course.airbooking.presentation.base.State
import pt.course.airbooking.presentation.planeslist.adapter.PlanesListAdapter

class PlanesListFragment : BaseFragment<FragmentPlanesListBinding, PlanesListViewModel>(
    R.string.title_planes_list_screen,
    PlanesListViewModel::class.java,
    FragmentPlanesListBinding::inflate
) {
    companion object {
        @JvmStatic
        fun newInstance() = PlanesListFragment()
    }

    private val adapter = PlanesListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        observeViewModel()
        viewModel.loadPlanesList()
    }

    private fun setupViews() {
        with(binding) {
            recyclerViewPlanes.adapter = adapter
        }
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Content -> {
                    adapter.submitList(state.data)
                }

                else -> {}
            }

        }
    }
}