package com.ebanx.circle

import Model.AuthenticationDataResponse

/**
 * Created by gabrielbrancofrizzo on 08/03/18.
 */

class TokenManager private constructor() {

    private object Holder { val INSTANCE = TokenManager() }

    companion object {
        val instance: TokenManager by lazy { Holder.INSTANCE }
    }
    var authenticationData: AuthenticationDataResponse? = null
}
