package com.example.erp_distribution.feature.distribution

import com.example.erp_distribution.data.repository.DistributionRepository
import com.example.erp_distribution.data.response.AroundResponse
import com.example.erp_distribution.feature.base.UseCase
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetListAroundUseCase (
    executorThread: Scheduler,
    uiThread: Scheduler,
    private var repository: DistributionRepository
) : UseCase<List<AroundResponse>>(executorThread, uiThread) {
    override fun createObservableUseCase(): Observable<List<AroundResponse>> {
        return repository.getArounds()
    }
}