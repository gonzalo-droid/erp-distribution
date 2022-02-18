package com.solti.solti_inmobiliaria.feature.distribution

import com.solti.solti_inmobiliaria.data.repository.DistributionRepository
import com.solti.solti_inmobiliaria.data.response.AroundResponse
import com.solti.solti_inmobiliaria.feature.base.UseCase
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