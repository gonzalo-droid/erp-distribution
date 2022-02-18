package com.solti.solti_inmobiliaria.feature.distribution

import com.solti.solti_inmobiliaria.data.repository.DistributionRepository
import com.solti.solti_inmobiliaria.data.response.LevelResponse
import com.solti.solti_inmobiliaria.feature.base.UseCase
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