package com.pasukanlangit.id.hiltmvipattern.di

import com.pasukanlangit.id.hiltmvipattern.repository.MainRepository
import com.pasukanlangit.id.hiltmvipattern.retrofit.BlogRetrofit
import com.pasukanlangit.id.hiltmvipattern.retrofit.NetworkMapper
import com.pasukanlangit.id.hiltmvipattern.room.BlogDao
import com.pasukanlangit.id.hiltmvipattern.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(blogDao: BlogDao, retrofit: BlogRetrofit, cacheMapper: CacheMapper, networkMapper: NetworkMapper): MainRepository =
        MainRepository(
            blogDao = blogDao,
            blogRetrofit = retrofit,
            cacheMapper =cacheMapper,
            networkMapper = networkMapper
        )
}