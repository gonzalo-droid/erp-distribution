package com.solti.inmobiliaria.feature.distribution

import com.solti.inmobiliaria.data.repository.DistributionRepository
import com.solti.inmobiliaria.data.response.LevelResponse
import com.solti.inmobiliaria.feature.base.UseCase
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetListLevelUseCase (
    executorThread: Scheduler,
    uiThread: Scheduler,
    private var repository: DistributionRepository
) : UseCase<List<LevelResponse>>(executorThread, uiThread) {
    override fun createObservableUseCase(): Observable<List<LevelResponse>> {
        return repository.getLevels()
    }
}