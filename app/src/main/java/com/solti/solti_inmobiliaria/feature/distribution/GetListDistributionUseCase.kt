package com.solti.solti_inmobiliaria.feature.distribution

import com.solti.solti_inmobiliaria.data.repository.DistributionRepository
import com.solti.solti_inmobiliaria.data.request.FilterDistributionRequest
import com.solti.solti_inmobiliaria.data.response.DistributionResponse
import com.solti.solti_inmobiliaria.feature.base.UseCase
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetListDistributionUseCase  (
    executorThread: Scheduler,
    uiThread: Scheduler,
    private var repository: DistributionRepository
) : UseCase<DistributionResponse>(executorThread, uiThread) {
    var request = FilterDistributionRequest()
    override fun createObservableUseCase(): Observable<DistributionResponse> {
        return repository.getListDistribution(request)
    }
}