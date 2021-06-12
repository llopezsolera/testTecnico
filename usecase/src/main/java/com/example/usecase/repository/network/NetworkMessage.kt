package com.example.usecase.repository.network

data class NetworkMessage(
    val _body: String = "",
    val _httpCode: Int
    //val logError: ErrorObject? = null
) {


    var title: String = ""
    var body: String = ""
    var httpCode: Int = Constants.BAD_REQUEST

    init {


        this.title = "¡Ups!"//mContext.getString(R.string.general_oops)
        this.httpCode = _httpCode

        when (this.httpCode) {
            Constants.INT_SRV_ERROR, Constants.NOT_FOUND ->

                this.body =
                    "Hubo un problema con el servidor. Estamos trabajando para solucionarlo"//mContext.getString(R.string.general_error_400)
            in 400..499 ->
                if (_body.isBlank()) {
                    this.body =
                        "Hubo un problema con el servidor. Estamos trabajando para solucionarlo"//mContext.getString(R.string.general_error_400)
                } else {
                    this.body = _body
                }

            else -> this.body =
                "unknown error"//"Hubo un problema con el servidor. Estamos trabajando para solucionarlo"//mContext.getString(R.string.general_error_400)
        }

    }
}