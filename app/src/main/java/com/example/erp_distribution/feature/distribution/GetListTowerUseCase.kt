package com.example.erp_distribution.feature.distribution

import com.example.erp_distribution.data.repository.DistributionRepository
import com.example.erp_distribution.data.response.TowerResponse
import com.example.erp_distribution.feature.base.UseCase
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetListTowerUseCase (
    executorThread: Scheduler,
    uiThread: Scheduler,
    private var repository: DistributionRepository
) : UseCase<List<TowerResponse>>(executorThread, uiThread) {
    override fun createObservableUseCase(): Observable<List<TowerResponse>> {
        return repository.getTowers()
    }
}