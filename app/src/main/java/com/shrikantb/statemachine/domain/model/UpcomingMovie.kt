package com.shrikantb.statemachine.domain.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    var page: Int = 0,
    @SerializedName("results")
    var movieList: ArrayList<Movie> = ArrayList(),
    @SerializedName("total_results")
    var totalResults: Int = 0,
    @SerializedName("total_pages")
    var totalPages: Int = 0
)

data class Movie(
    @SerializedName("poster_path")
    var posterPath: String? = null,
    var adult: Boolean = false,
    var overview: String? = null,
    @SerializedName("release_date")
    var releaseDate: String? = null,
    var id: String? = null,
    var title: String? = null,
    @SerializedName("backdrop_path")
    var backdropPath: String? = null,
    @SerializedName("vote_average")
    var voteAverage: Float = 0f
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readFloat()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(posterPath)
        parcel.writeByte(if (adult) 1 else 0)
        parcel.writeString(overview)
        parcel.writeString(releaseDate)
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(backdropPath)
        parcel.writeFloat(voteAverage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }

    fun posterPath() = "https://image.tmdb.org/t/p/w500$posterPath"
}