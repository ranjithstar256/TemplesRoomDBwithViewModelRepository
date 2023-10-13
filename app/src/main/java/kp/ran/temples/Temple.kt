package kp.ran.temples

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "temple")
data class Temple(
    @PrimaryKey(autoGenerate = true)
    var itemId: Int = 0,

    @ColumnInfo(name = "TempleName")
    val TempleName: String,

    @ColumnInfo(name = "Location")
    val Location: String,

    @ColumnInfo(name = "MainGod")
    val MainGod: String
)
