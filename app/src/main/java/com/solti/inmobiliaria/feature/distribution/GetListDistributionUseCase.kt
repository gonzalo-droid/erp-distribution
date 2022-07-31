package com.solti.inmobiliaria.feature.distribution

import com.solti.inmobiliaria.data.repository.DistributionRepository
import com.solti.inmobiliaria.data.request.FilterDistributionRequest
import com.solti.inmobiliaria.data.response.DistributionResponse
import com.solti.inmobiliaria.feature.base.UseCase
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