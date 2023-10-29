package pt.course.airbooking.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import dagger.android.support.AndroidSupportInjection
import pt.course.airbooking.presentation.ViewModelFactory
import javax.inject.Inject

/**
 * This class is basic Fragment class. It has already set up View binding
 * and View model. It also automatically injects all field-injections, so
 * you should only mark dependencies with @Inject. It also sets up
 * title to activity's ActionBar
 */
abstract class BaseFragment<VB : ViewBinding, VM : ViewModel>(
    private val actionBarTitleId: Int,
    private val viewModelClass: Class<VM>,
    private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment() {

    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding ?: throw RuntimeException("Binding is null!")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    protected lateinit var viewModel: VM
        private set

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[viewModelClass]

    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = getString(actionBarTitleId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
