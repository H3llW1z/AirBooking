package pt.course.airbooking.presentation.personaldocument

import pt.course.airbooking.R
import pt.course.airbooking.databinding.FragmentPersonalDocumentBinding
import pt.course.airbooking.presentation.base.BaseFragment

class PersonalDocumentFragment:BaseFragment<FragmentPersonalDocumentBinding, PersonalDocumentViewModel> (
    R.string.title_personal_document_screen,
    PersonalDocumentViewModel::class.java,
    FragmentPersonalDocumentBinding::inflate
) {
    companion object {
        @JvmStatic
        fun newInstance() = PersonalDocumentFragment()
    }
}