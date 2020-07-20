package com.eloquence.verbconjugator.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "verb_table")
data class Verb(

    @PrimaryKey(autoGenerate = true)
    val verbId: Int,
    val indicativePresent: String,
    val indicativePast: String,
    val indicativePerfect: String,
    val indicativePluPerfect: String,
    val indicativeFutureOne: String,
    val indicativeFutureTwo: String,
    val subjunctiveOne: String,
    val subjunctiveTwo: String,
    val subjunctivePerfect: String,
    val subjunctivePluPerfect: String,
    val subjunctiveFutureOne: String,
    val subjunctiveFutureTwo: String,
    val imperative: String,
    val infinitivePresent: String,
    val infinitivePerfect: String,
    val participleOne: String,
    val participleTwo: String,
    val verbclass: String,
    val reflexivity: String,
    val complexity: String,
    val separability: String,
    val translation: String,
    val pastform: String,
    val pastparticiple: String,
    var isFavourite: Boolean
) : Parcelable