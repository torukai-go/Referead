package com.toru.referead.remote.books

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.toru.referead.model.books.BooksInfo
import com.toru.referead.ui.main.MainState
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class ExamplePagingSource(
    private val api: CurrenciesService,
    private val query: String
) : PagingSource<Int, BooksInfo>() {
    //    override suspend fun load(
//        params: LoadParams<Int>
//    ): LoadResult<Int, User> {
//        try {
//            // Start refresh at page 1 if undefined.
//            val nextPageNumber = params.key ?: 1
//            val response = backend.searchUsers(query, nextPageNumber)
//            return LoadResult.Page(
//                data = response.users,
//                prevKey = null, // Only paging forward.
//                nextKey = response.nextPageNumber
//            )
//        } catch (e: Exception) {
//            // Handle errors in this block and return LoadResult.Error if it is an
//            // expected error (such as a network failure).
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
//        // Try to find the page key of the closest page to anchorPosition, from
//        // either the prevKey or the nextKey, but you need to handle nullability
//        // here:
//        //  * prevKey == null -> anchorPage is the first page.
//        //  * nextKey == null -> anchorPage is the last page.
//        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
//        //    just return null.
//        return state.anchorPosition?.let { anchorPosition ->
//            val anchorPage = state.closestPageToPosition(anchorPosition)
//            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
//        }
//    }


    private val retrofitCoroutinesMoshiConverter = NetworkServiceRetrofitCoroutinesMoshiAdapter()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BooksInfo> {
        val position = params.key ?: STARTING_PAGE_INDEX

        val loadSize = 40//params.loadSize
        Log.d("TAG", loadSize.toString())
        return try{
            val response = api.getCurrencies(query, position, loadSize)
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
