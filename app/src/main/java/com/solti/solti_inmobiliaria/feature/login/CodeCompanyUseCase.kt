package com.solti.solti_inmobiliaria.feature.login

import com.solti.solti_inmobiliaria.data.repository.LoginRepository
import com.solti.solti_inmobiliaria.data.request.CodeCompanyRequest
import com.solti.solti_inmobiliaria.data.response.CodeCompanyResponse
import com.solti.solti_inmobiliaria.feature.base.UseCase
import io.reactivex.Observable
import io.reactivex.Scheduler

class CodeCompanyUseCase(
    executorThread: Scheduler,
    uiThread: Scheduler,
    private var repository: LoginRepository
) : UseCase<CodeCompanyResponse>(executorThread, uiThread) {
    var request = CodeCompanyRequest()
    override fun createObservableUseCase(): Observable<CodeCompanyResponse> {
        return repository.codeCompany(request)
    }
}