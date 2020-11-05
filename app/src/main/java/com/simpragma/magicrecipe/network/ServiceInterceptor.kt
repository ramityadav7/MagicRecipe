package com.countries.assignment.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Request.Builder
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServiceInterceptor @Inject constructor() : Interceptor {
    private val HEADER_AUTH = "Authorization"


    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        var builder = request.newBuilder()

        val token: String? = null//provide proper token
        setHeaders(builder, token)

        request = builder.build()

        return chain.proceed(request)
    }

    private fun setHeaders(builder : Builder, token : String?) {
        if(token != null)
            builder.header(HEADER_AUTH, String.format("Bearer %s", token));
    }
}