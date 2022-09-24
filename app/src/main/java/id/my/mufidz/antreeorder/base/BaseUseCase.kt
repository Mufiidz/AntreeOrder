package id.my.mufidz.antreeorder.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseUseCase<RequestParam, ResponseObject, UseCaseResult> {

    protected abstract suspend fun execute(param: RequestParam): ResponseObject

    protected abstract suspend fun ResponseObject.transformToUseCaseResult(): UseCaseResult

    suspend fun getResult(param: RequestParam): UseCaseResult {
        val executionResult = withContext(Dispatchers.IO) {
            execute(param)
        }
        return executionResult.transformToUseCaseResult()
    }
}