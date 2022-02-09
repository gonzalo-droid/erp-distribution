package com.gonlg.erp_distribution.feature.login

import com.gonlg.erp_distribution.data.repository.LoginRepository
import com.gonlg.erp_distribution.data.request.LoginRequest
import com.gonlg.erp_distribution.data.response.LoginResponse
import com.gonlg.erp_distribution.feature.base.UseCase
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