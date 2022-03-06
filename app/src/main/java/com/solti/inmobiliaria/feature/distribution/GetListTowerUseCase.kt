package com.solti.inmobiliaria.feature.distribution

import com.solti.inmobiliaria.data.repository.DistributionRepository
import com.solti.inmobiliaria.data.response.TowerResponse
import com.solti.inmobiliaria.feature.base.UseCase
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