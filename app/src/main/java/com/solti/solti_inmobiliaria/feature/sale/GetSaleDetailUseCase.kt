package com.solti.solti_inmobiliaria.feature.sale

import com.solti.solti_inmobiliaria.data.repository.SaleDetailRepository
import com.solti.solti_inmobiliaria.data.request.DistributionIdRequest
import com.solti.solti_inmobiliaria.data.response.SaleDetailResponse
import com.solti.solti_inmobiliaria.feature.base.UseCase
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetSaleDetailUseCase (
    executorThread: Scheduler,
    uiThread: Scheduler,
    private var repository: SaleDetailRepository
) : UseCase<SaleDetailResponse>(executorThread, uiThread) {
    var request = DistributionIdRequest()
    override fun createObservableUseCase(): Observable<SaleDetailResponse> {
        return repository.getSaleDetail(request)
    }
}