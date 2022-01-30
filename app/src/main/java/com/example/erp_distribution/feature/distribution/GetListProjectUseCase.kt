package com.example.erp_distribution.feature.distribution

import com.example.erp_distribution.data.repository.DistributionRepository
import com.example.erp_distribution.data.response.ProjectResponse
import com.example.erp_distribution.feature.base.UseCase
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetListProjectUseCase (
    executorThread: Scheduler,
    uiThread: Scheduler,
    private var repository: DistributionRepository
) : UseCase<List<ProjectResponse>>(executorThread, uiThread) {
    override fun createObservableUseCase(): Observable<List<ProjectResponse>> {
        return repository.getProjects()
    }
}