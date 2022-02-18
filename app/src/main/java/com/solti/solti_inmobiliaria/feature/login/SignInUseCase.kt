package com.solti.solti_inmobiliaria.feature.login

import com.solti.solti_inmobiliaria.data.repository.LoginRepository
import com.solti.solti_inmobiliaria.data.request.LoginRequest
import com.solti.solti_inmobiliaria.data.response.LoginResponse
import com.solti.solti_inmobiliaria.feature.base.UseCase
import io.reactivex.Observable
import io.reactivex.Scheduler

class SignInUseCase (
    executorThread: Scheduler,
    uiThread: Scheduler,
    private var repository: LoginRepository
) : UseCase<LoginResponse>(executorThread, uiThread) {
    var request = LoginRequest()
    override fun createObservableUseCase(): Observable<LoginResponse> {
        return repository.signIn(request)
    }
}