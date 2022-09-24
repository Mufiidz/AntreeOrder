package id.my.mufidz.antreeorder.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.my.mufidz.antreeorder.data.manager.AuthDataManager
import id.my.mufidz.antreeorder.data.manager.AuthDataManagerImpl
import id.my.mufidz.antreeorder.repository.AuthUseCaseRepositoryImpl
import id.my.mufidz.antreeorder.repository.AuthUsecaseRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideAuthDataManager(
        authDataManagerImpl: AuthDataManagerImpl
    ): AuthDataManager

    @Singleton
    @Binds
    abstract fun provideAuthUseCaseRepository(
        authUseCaseRepositoryImpl: AuthUseCaseRepositoryImpl
    ): AuthUsecaseRepository
}