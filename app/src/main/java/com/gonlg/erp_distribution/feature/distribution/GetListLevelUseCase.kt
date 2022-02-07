package com.gonlg.erp_distribution.feature.distribution

import com.gonlg.erp_distribution.data.repository.DistributionRepository
import com.gonlg.erp_distribution.data.response.LevelResponse
import com.gonlg.erp_distribution.feature.base.UseCase
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