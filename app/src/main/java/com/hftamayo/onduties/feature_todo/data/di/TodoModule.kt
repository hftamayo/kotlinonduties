package com.hftamayo.onduties.feature_todo.data.di

import android.content.Context
import androidx.room.Room
import com.hftamayo.onduties.feature_todo.data.local.TodoDao
import com.hftamayo.onduties.feature_todo.data.local.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TodoModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
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
    )
}