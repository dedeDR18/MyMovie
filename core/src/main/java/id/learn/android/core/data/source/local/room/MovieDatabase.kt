package id.learn.android.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import id.learn.android.core.data.source.local.entity.MovieEntitiy

@Database(entities = [MovieEntitiy::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}