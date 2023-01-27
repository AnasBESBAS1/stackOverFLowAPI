package fr.mastersime.stackoverflow.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object QuestionRoomDatabaseModule {
    @Provides
    fun  provideQuestionDAO(questionsRoomDatabase: QuestionsRoomDatabase) : QuestionDAO{
        return questionsRoomDatabase.questionDao()

        //!\\ one and only one instance of db for the whole app
    }

    // instance of db provided for the fun above
    @Provides
    @Singleton
    fun provideQuestionRoomDatabase(@ApplicationContext appContext : Context) : QuestionsRoomDatabase{
        return  Room.databaseBuilder(
            appContext.applicationContext,
            QuestionsRoomDatabase::class.java,
            "question_table"
        ).build()
    }


}