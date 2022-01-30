package com.example.erp_distribution.feature.distribution

import com.example.erp_distribution.data.repository.DistributionRepository
import com.example.erp_distribution.data.request.FilterDistributionRequest
import com.example.erp_distribution.data.response.DistributionResponse
import com.example.erp_distribution.feature.base.UseCase
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