package com.yandex.practicum.middle_homework_4.data.news_service

import com.yandex.practicum.middle_homework_4.data.database.entity.News

data class NewsResponse(
    val nextPage: Int?,
    val news: List<News>
)