package com.solti.inmobiliaria.feature.login

import com.solti.inmobiliaria.data.repository.LoginRepository
import com.solti.inmobiliaria.data.request.CodeCompanyRequest
import com.solti.inmobiliaria.data.response.CodeCompanyResponse
import com.solti.inmobiliaria.feature.base.UseCase
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