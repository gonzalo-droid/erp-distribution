package com.solti.inmobiliaria.feature.distribution

import com.solti.inmobiliaria.data.repository.DistributionRepository
import com.solti.inmobiliaria.data.response.ProjectResponse
import com.solti.inmobiliaria.feature.base.UseCase
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