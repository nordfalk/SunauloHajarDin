package com.example.sunmadinepal.database.local

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.converter.Converters
import com.example.sunmadinepal.fragment.child.dao.AddChildDAo
import com.example.sunmadinepal.fragment.child.model.AddChildModel
import com.example.sunmadinepal.fragment.doctor.dao.AppointmentDao
import com.example.sunmadinepal.fragment.doctor.model.AppointmentModel
import com.example.sunmadinepal.model.DoctorAppointment

//Based on: https://developer.android.com/training/data-storage/room

@Database(entities = [DoctorAppointment::class, AddChildModel::class,
                     AppointmentModel::class],version=4)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun DoctorAppointmentDao(): DoctorAppointmentDao
    abstract fun addChildDao() : AddChildDAo
    abstract fun addAppointmentDao() : AppointmentDao


    companion object {
        //Based on codelab: https://developer.android.com/codelabs/android-room-with-a-view-kotlin#7
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "App_database"
                ).addMigrations(MIGRATION_1_2,MIGRATION_2_3,MIGRATION_3_4).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS `add_child` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `child_name` TEXT NOT NULL, `birth_date` TEXT NOT NULL, `gender` TEXT NOT NULL, `allergies` TEXT NOT NULL, `image` TEXT NOT NULL)")
            }
        }

        private val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS `appointment` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `child_name` TEXT NOT NULL, `doctor_name` TEXT NOT NULL, `health_center_location` TEXT NOT NULL, `appointment_date` TEXT NOT NULL)")
            }
        }

        private val MIGRATION_3_4: Migration = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {

                database.execSQL("ALTER TABLE appointment ADD `child_dob` TEXT NOT NULL DEFAULT 1")
                database.execSQL("ALTER TABLE appointment ADD `child_gender` TEXT NOT NULL DEFAULT 1")
            }
        }
    }



}