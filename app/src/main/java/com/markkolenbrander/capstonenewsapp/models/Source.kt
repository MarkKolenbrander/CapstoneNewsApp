package com.markkolenbrander.capstonenewsapp.models


data class Source(
    val id: String? = null,
    val name: String,
    val description: String,
    val url: String,
    val category: Category,
    val language: Language,
    val country: Country,
)
//    : Parcelable{
//    constructor(parcel: Parcel) : this(
//        parcel.readString(),
//        parcel.readString().toString(),
//        parcel.readString().toString(),
//        parcel.readString().toString(),
//        TODO("category"),
//        TODO("language"),
//        TODO("country")
//    )
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeString(id)
//        parcel.writeString(name)
//        parcel.writeString(description)
//        parcel.writeString(url)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<Source> {
//        override fun createFromParcel(parcel: Parcel): Source {
//            return Source(parcel)
//        }
//
//        override fun newArray(size: Int): Array<Source?> {
//            return arrayOfNulls(size)
//        }
//    }
//
//}


