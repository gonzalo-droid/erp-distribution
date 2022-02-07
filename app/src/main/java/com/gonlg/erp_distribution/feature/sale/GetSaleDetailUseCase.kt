package com.gonlg.erp_distribution.feature.sale

import com.gonlg.erp_distribution.data.repository.SaleDetailRepository
import com.gonlg.erp_distribution.data.request.DistributionIdRequest
import com.gonlg.erp_distribution.data.response.SaleDetailResponse
import com.gonlg.erp_distribution.feature.base.UseCase
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