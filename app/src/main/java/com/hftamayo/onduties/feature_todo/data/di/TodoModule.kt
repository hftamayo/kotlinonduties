package com.hftamayo.onduties.feature_todo.data.di

import android.content.Context
import androidx.room.Room
import com.hftamayo.onduties.feature_todo.data.local.TodoDao
import com.hftamayo.onduties.feature_todo.data.local.TodoDatabase
import com.hftamayo.onduties.feature_todo.data.remote.TodoApi
import com.hftamayo.onduties.feature_todo.data.repo.TodoListRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TodoModule {

    @Provides
    fun providesRetrofitApi(retrofit: Retrofit): TodoApi {
        return retrofit.create(TodoApi::class.java)
    }

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://todo-8ed5c.firebaseio.com/")
            .build()
    }

    @Provides
    fun provideRoomDao(database: TodoDatabase): TodoDao {
        return database.dao
    }

    @Singleton
    @Provides
    fun provideRoomDb(
        @ApplicationContext appContext: Context
    ): TodoDatabase {
        return Room.databaseBuilder(
            appContext.applicationContext,
            TodoDatabase::class.java,
            TodoDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun providesTodoRepo(db: TodoDatabase, api: TodoApi, @IoDispatcher dispatcher: CoroutineDispatcher): TodoListRepoImpl {
        return TodoListRepoImpl(db.dao, api, dispatcher)
    }
}