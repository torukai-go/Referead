package com.toru.referead.remote.books

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.toru.referead.model.books.BooksInfo
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class BooksPagingSource(
    private val api: BooksWebService,
    private val query: String,
    private val filter: String? = null,

) : PagingSource<Int, BooksInfo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BooksInfo> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try{
            val response = api.getBooksInfoAsync(query, filter, position, 40)
            val books = response.items

            LoadResult.Page(
                data = books,
                prevKey = if(position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if(books.isEmpty()) null else position +1
            )
        }catch (exception: IOException){
            LoadResult.Error(exception)
        }catch (exception: HttpException){
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, BooksInfo>): Int? {
        TODO("Not yet implemented")
    }
}
