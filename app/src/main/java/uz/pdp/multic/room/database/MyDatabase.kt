package uz.pdp.multic.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.pdp.multic.models.episode.EpisodeClass
import uz.pdp.multic.room.dao.MulDao
import uz.pdp.multic.room.entity.MultClass

@Database(entities = [MultClass::class], version = 1 )
abstract class MyDatabase: RoomDatabase() {

    abstract fun helper(): MulDao

    companion object {

        private var myDatabase: MyDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MyDatabase {
            if (myDatabase == null) {
                myDatabase = Room.databaseBuilder(context, MyDatabase::class.java, "db")
                    .allowMainThreadQueries()
                    .build()
            }

            return myDatabase!!
        }
    }

}