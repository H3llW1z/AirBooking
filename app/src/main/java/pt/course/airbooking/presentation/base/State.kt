package pt.course.airbooking.presentation.base

sealed class State<out T> {

    data object Initial: State<Nothing>()

    data object EmptyResult: State<Nothing>()

    data class Content<T>(val data: T): State<T>()
}
