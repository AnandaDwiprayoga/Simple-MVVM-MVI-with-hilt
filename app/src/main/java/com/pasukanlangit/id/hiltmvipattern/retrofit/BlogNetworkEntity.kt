package com.pasukanlangit.id.hiltmvipattern.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BlogNetworkEntity(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("pk")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("body")
	val body: String,

	@field:SerializedName("category")
	val category: String
)
