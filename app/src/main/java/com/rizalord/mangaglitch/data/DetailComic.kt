package com.rizalord.mangaglitch.data

import com.google.gson.annotations.SerializedName

data class DetailComic(

	@field:SerializedName("title_japanese")
	val titleJapanese: String? = null,

	@field:SerializedName("favorites")
	val favorites: Int? = null,

	@field:SerializedName("chapters")
	val chapters: Any? = null,

	@field:SerializedName("scored_by")
	val scoredBy: Int? = null,

	@field:SerializedName("request_cache_expiry")
	val requestCacheExpiry: Int? = null,

	@field:SerializedName("title_synonyms")
	val titleSynonyms: List<Any?>? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("score")
	val score: Double? = null,

	@field:SerializedName("related")
	val related: Related? = null,

	@field:SerializedName("request_hash")
	val requestHash: String? = null,

	@field:SerializedName("genres")
	val genres: List<GenresItem?>? = null,

	@field:SerializedName("popularity")
	val popularity: Int? = null,

	@field:SerializedName("members")
	val members: Int? = null,

	@field:SerializedName("request_cached")
	val requestCached: Boolean? = null,

	@field:SerializedName("title_english")
	val titleEnglish: String? = null,

	@field:SerializedName("rank")
	val rank: Int? = null,

	@field:SerializedName("publishing")
	val publishing: Boolean? = null,

	@field:SerializedName("serializations")
	val serializations: List<SerializationsItem?>? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("volumes")
	val volumes: Any? = null,

	@field:SerializedName("mal_id")
	val malId: Int? = null,

	@field:SerializedName("published")
	val published: Published? = null,

	@field:SerializedName("synopsis")
	val synopsis: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("background")
	val background: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("authors")
	val authors: List<AuthorsItem?>? = null
)

data class AdaptationItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("mal_id")
	val malId: Int? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class Prop(

	@field:SerializedName("from")
	val from: From? = null,

	@field:SerializedName("to")
	val to: To? = null
)

data class GenresItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("mal_id")
	val malId: Int? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class Published(

	@field:SerializedName("string")
	val string: String? = null,

	@field:SerializedName("prop")
	val prop: Prop? = null,

	@field:SerializedName("from")
	val from: String? = null,

	@field:SerializedName("to")
	val to: Any? = null
)

data class From(

	@field:SerializedName("month")
	val month: Int? = null,

	@field:SerializedName("year")
	val year: Int? = null,

	@field:SerializedName("day")
	val day: Int? = null
)

data class SerializationsItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("mal_id")
	val malId: Int? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class Related(

	@field:SerializedName("Adaptation")
	val adaptation: List<AdaptationItem?>? = null
)

data class To(

	@field:SerializedName("month")
	val month: Any? = null,

	@field:SerializedName("year")
	val year: Any? = null,

	@field:SerializedName("day")
	val day: Any? = null
)

data class AuthorsItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("mal_id")
	val malId: Int? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)
