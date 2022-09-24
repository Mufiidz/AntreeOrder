package id.my.mufidz.antreeorder.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.my.mufidz.antreeorder.data.manager.AuthDataManager
import id.my.mufidz.antreeorder.usecase.LoginUseCase
import id.my.mufidz.antreeorder.usecase.RegisterUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideRegisterUseCase(authDataManager: AuthDataManager): RegisterUseCase =
        RegisterUseCase(authDataManager)

    @Provides
    @Singleton
    fun provideLoginUseCase(authDataManager: AuthDataManager): LoginUseCase =
        LoginUseCase(authDataManager)
}