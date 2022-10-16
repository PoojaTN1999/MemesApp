package com.poojatn.memescasestudy.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.poojatn.memescasestudy.modules.Meme
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Meme::class], version = 1,exportSchema = false)
abstract class MemesDatabase : RoomDatabase() {

    // interface better use this as a dependency..
    abstract fun memeDao(): MemeDao?

    companion object {
        @Volatile
        var INSTANCE: MemesDatabase? = null


        fun getDatabase(context: Context): MemesDatabase {

            return  INSTANCE ?: synchronized( this ){

                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    MemesDatabase::class.java, "memes_database"
                )
                    .build()

                INSTANCE=instance
                instance
            }
        }
//        fun getMemeDatabase(
//            @ApplicationContext context: Context,   // MainActivity( Context )  Application
//            scope: CoroutineScope   //  CoroutineScope( SupervisorJob() )
//        ): MemesDatabase {
//            return INSTANCE ?: synchronized(this){
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    MemesDatabase::class.java,
//                    "meme_database"
//                )
//                    .fallbackToDestructiveMigration(    )
//                    .addCallback( MemeDatabaseCallback( scope )  )    // hook
//                    .build()
//                INSTANCE = instance
//                instance
//
//            }
//        }
    }
}
//// lets use the MemeDao here to work upon CRUD opreations
//private class MemeDatabaseCallback(private val scope: CoroutineScope): RoomDatabase.Callback() {
//    override fun onCreate(db: SupportSQLiteDatabase) {
//        super.onCreate(db)

//}
//}



