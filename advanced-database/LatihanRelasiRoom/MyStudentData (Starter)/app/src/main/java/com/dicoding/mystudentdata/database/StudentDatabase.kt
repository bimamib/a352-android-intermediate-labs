package com.dicoding.mystudentdata.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import kotlinx.coroutines.CoroutineScope

@Database(
    entities = [Student::class, University::class, Course::class, CourseStudentCrossRef::class],
    version = 3,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3, spec = StudentDatabase.MyAutoMigration::class),
    ],
    exportSchema = true
)
abstract class StudentDatabase : RoomDatabase() {

    @RenameColumn(tableName = "Student", fromColumnName = "graduate", toColumnName = "isGraduate")
    class MyAutoMigration : AutoMigrationSpec

    abstract fun studentDao(): StudentDao

    companion object {
        @Volatile
        private var INSTANCE: StudentDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context, applicationScope: CoroutineScope): StudentDatabase {
            if (INSTANCE == null) {
                synchronized(StudentDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        StudentDatabase::class.java, "student_database"
                    )
                        .fallbackToDestructiveMigration()
                        .createFromAsset("student_database.db")
//                        .addCallback(object : Callback() {
//                            override fun onCreate(db: SupportSQLiteDatabase) {
//                                super.onCreate(db)
//                                INSTANCE?.let { database ->
//                                    applicationScope.launch {
//                                        val studentDao = database.studentDao()
//                                        studentDao.insertStudent(InitialDataSource.getStudents())
//                                        studentDao.insertUniversity(InitialDataSource.getUniversities())
//                                        studentDao.insertCourse(InitialDataSource.getCourses())
//                                        studentDao.insertCourseStudentCrossRef(InitialDataSource.getCourseStudentRelation())
//                                    }
//                                }
//                            }
//                        })
                        .build()
                }
            }
            return INSTANCE as StudentDatabase
        }

    }
}