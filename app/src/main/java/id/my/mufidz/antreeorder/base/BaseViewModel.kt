package id.my.mufidz.antreeorder.base

import androidx.annotation.MainThread
import androidx.lifecycle.*

abstract class BaseViewModel<State : ViewState, Action : ViewAction>(
    initState: State
) : ViewModel() {

    private var currentViewState: State = initState

    private val viewAction = MutableLiveData<Action>()

    val viewState: LiveData<State> =
        Transformations.map(Transformations.switchMap(viewAction, ::handleAction)) {
            it.renderViewState().updateCurrentViewState()
        }

    @MainThread
    fun execute(action: Action) { viewAction.value = action }

    protected fun currentViewState(): State = currentViewState

    protected abstract fun ActionResult.renderViewState(): State

    protected abstract fun handleAction(action: Action): LiveData<ActionResult>

    private fun State.updateCurrentViewState(): State = this.also {
        currentViewState = it
    }
}