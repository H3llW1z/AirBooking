package pt.course.airbooking.presentation.planeslist

import pt.course.airbooking.R
import pt.course.airbooking.databinding.FragmentPlanesListBinding
import pt.course.airbooking.presentation.base.BaseFragment

class PlanesListFragment : BaseFragment<FragmentPlanesListBinding, PlanesListViewModel>(
    R.string.title_planes_list_screen,
    PlanesListViewModel::class.java,
    FragmentPlanesListBinding::inflate
) {
    companion object {
        @JvmStatic
        fun newInstance() = PlanesListFragment()
    }
}