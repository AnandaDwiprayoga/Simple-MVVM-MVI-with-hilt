package com.pasukanlangit.id.hiltmvipattern.repository

import android.webkit.DateSorter
import com.pasukanlangit.id.hiltmvipattern.model.Blog
import com.pasukanlangit.id.hiltmvipattern.retrofit.BlogRetrofit
import com.pasukanlangit.id.hiltmvipattern.retrofit.NetworkMapper
import com.pasukanlangit.id.hiltmvipattern.room.BlogDao
import com.pasukanlangit.id.hiltmvipattern.room.CacheMapper
import com.pasukanlangit.id.hiltmvipattern.utils.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class MainRepository
constructor(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
){
    suspend fun getBlog(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val networkBlogs = blogRetrofit.get()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            for(blog in blogs){
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cachedBlogs = blogDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        }catch(e: Exception){
            emit(DataState.Error(e))
        }
    }
}