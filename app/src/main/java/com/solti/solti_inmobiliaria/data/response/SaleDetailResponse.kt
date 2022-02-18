package com.solti.solti_inmobiliaria.data.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SaleDetailResponse(
    @SerializedName("collection")
    var sale: Sale = Sale()
): Serializable {
    data class Sale(
        @SerializedName("Bono")
        var bono: String = "",
        @SerializedName("Cliente")
        var client: String = "",
        @SerializedName("DNI")
        var dni: String = "",
        @SerializedName("Descuento (S/)")
        var discount: String = "",
        @SerializedName("Dirección")
        var address: String = "",
        @SerializedName("Distribución")
        var distribution: String = "",
        @SerializedName("Estado de distribución")
        var statuDistribution: String = "",
        @SerializedName("Estado de venta")
        var statuSale: String = "",
        @SerializedName("Estado")
        var status: String = "",
        @SerializedName("Fecha")
        var date: String = "",
        @SerializedName("Identificador")
        var indentificator: String = "",
        @SerializedName("Inicial")
        var initial: String = "",
        @SerializedName("Interés")
        var interest: String = "",
        @SerializedName("Monto de Bono")
        var amountBono: String = "",
        @SerializedName("Monto de la cuota")
        var amountQuota: String = "",
        @SerializedName("Método de Pago")
        var mehotdPayment: String = "",
        @SerializedName("N° de cuotas")
        var numberQuotas: Int = 0,
        @SerializedName("Pago Total Dólares")
        var totalDollars: String = "",
        @SerializedName("Pago Total Soles")
        var totalSoles: String = "",
        @SerializedName("Precio Dólares")
        var priceDollars: String = "",
        @SerializedName("Precio Soles")
        var priceSoles: String = "",
        @SerializedName("REGISTRO")
        var register: String = "",
        @SerializedName("REFINANCIAR")
        var refinance: String = "",
        @SerializedName("Tipo de Contrato")
        var typeContract: String = "",
        @SerializedName("Tipo de Moneda")
        var typeCurrency: String = "",
        @SerializedName("Tipo de Pago")
        var typePayment: String = "",
        @SerializedName("Tipo de Venta")
        var typeSale: String = "",
        @SerializedName("Vendedor")
        var seller: String = "",
        @SerializedName("Última actualización")
        var lastUpdate: String = ""
    ): Serializable
}