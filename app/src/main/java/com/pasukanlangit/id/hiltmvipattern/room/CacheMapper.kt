package com.pasukanlangit.id.hiltmvipattern.room

import com.pasukanlangit.id.hiltmvipattern.model.Blog
import com.pasukanlangit.id.hiltmvipattern.utils.EntityMapper
import javax.inject.Inject

class CacheMapper
@Inject constructor() : EntityMapper<BlogCacheEntity, Blog> {
    override fun mapFromEntity(entity: BlogCacheEntity): Blog = Blog(
        id = entity.id,
        title = entity.title,
        body =  entity.body,
        image = entity.image,
        category = entity.category
    )

    override fun mapToEntity(domainModel: Blog): BlogCacheEntity = BlogCacheEntity(
        id = domainModel.id,
        title = domainModel.title,
        body =  domainModel.body,
        image = domainModel.image,
        category = domainModel.category
    )

    fun mapFromEntityList(entities: List<BlogCacheEntity>) : List<Blog> = entities.map { mapFromEntity(it) }
}