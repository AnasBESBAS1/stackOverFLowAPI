package fr.mastersime.stackoverflow.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

//définir un module pour que Hilt fasse le lien entre l’interface et l'impl
@Module
@InstallIn(ViewModelComponent::class)
abstract class QuestionRepositoryModule {
    @Binds
    abstract fun bindQuestionRepository(questionsRepositoryImpl: QuestionsRepositoryDummyImp):
            QuestionsRepository
}