package com.example.erp_distribution.feature.distribution

import com.example.erp_distribution.data.repository.DistributionRepository
import com.example.erp_distribution.data.request.FilterDistributionRequest
import com.example.erp_distribution.data.response.ListDistributionResponse
import com.example.erp_distribution.feature.base.UseCase
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetListDistributionUseCase  (
    executorThread: Scheduler,
    uiThread: Scheduler,
    private var repository: DistributionRepository
) : UseCase<ListDistributionResponse>(executorThread, uiThread) {
    var request = FilterDistributionRequest()
    override fun createObservableUseCase(): Observable<ListDistributionResponse> {
        return repository.getListDistribution(request)
    }
}