package com.rizalord.mangaglitch.data

import com.google.gson.annotations.SerializedName

data class PopularComics(

	@field:SerializedName("request_hash")
	val requestHash: String? = null,

	@field:SerializedName("last_page")
	val lastPage: Int? = null,

	@field:SerializedName("request_cached")
	val requestCached: Boolean? = null,

	@field:SerializedName("request_cache_expiry")
	val requestCacheExpiry: Int? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem?>? = null
)

data class ResultsItem(

	@field:SerializedName("end_date")
	val endDate: Any? = null,

	@field:SerializedName("chapters")
	val chapters: Int? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("volumes")
	val volumes: Int? = null,

	@field:SerializedName("mal_id")
	val malId: Int? = null,

	@field:SerializedName("synopsis")
	val synopsis: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("score")
	val score: Double? = null,

	@field:SerializedName("members")
	val members: Int? = null,

	@field:SerializedName("publishing")
	val publishing: Boolean? = null,

	@field:SerializedName("start_date")
	val startDate: String? = null
)
