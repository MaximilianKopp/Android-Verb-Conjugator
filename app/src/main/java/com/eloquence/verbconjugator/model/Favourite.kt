package com.eloquence.verbconjugator.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "favourite_table")
data class Favourite(

    @PrimaryKey(autoGenerate = true)
    val favouriteId: Int? = null,
    val verbOwnerId: Int,
    var isFavourite: Boolean? = null
) : Parcelable