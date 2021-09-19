package com.interview.test.Models

import com.google.gson.annotations.SerializedName

data class Result(
        @SerializedName("data")
        val data: List<Users>,
        @SerializedName("page")
        val page: Int,
        @SerializedName("per_page")
        val per_page: Int,
        @SerializedName("support")
        val support: Support,
        @SerializedName("total")
        val total: Int,
        @SerializedName("total_pages")
        val total_pages: Int
)