package com.gonlg.erp_distribution.feature.login

import com.gonlg.erp_distribution.data.repository.LoginRepository
import com.gonlg.erp_distribution.data.request.CodeCompanyRequest
import com.gonlg.erp_distribution.data.request.DistributionIdRequest
import com.gonlg.erp_distribution.data.request.LoginRequest
import com.gonlg.erp_distribution.data.response.CodeCompanyResponse
import com.gonlg.erp_distribution.data.response.LoginResponse
import com.gonlg.erp_distribution.feature.base.UseCase
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