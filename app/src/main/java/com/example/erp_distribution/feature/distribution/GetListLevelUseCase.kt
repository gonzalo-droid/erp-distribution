package com.example.erp_distribution.feature.distribution

import com.example.erp_distribution.data.repository.DistributionRepository
import com.example.erp_distribution.data.response.LevelResponse
import com.example.erp_distribution.feature.base.UseCase
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