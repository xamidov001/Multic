package uz.pdp.multic.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MultClass (

    @PrimaryKey
    var id: String,
    var name: String,
    var isAlive: String,
    var location_name: String,
    var episode_name: String,
    var image: String
    )