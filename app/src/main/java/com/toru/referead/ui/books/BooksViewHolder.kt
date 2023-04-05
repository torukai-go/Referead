package com.toru.referead.ui.books

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.toru.referead.R
import com.toru.referead.model.books.BooksInfo

class BooksViewHolder2(bookView: View) : RecyclerView.ViewHolder(bookView) {

    private val bookView: View = bookView.findViewById(R.id.idIVBook)
    //private val chatAvatar: ImageView = bookView.findViewById(R.id.avatar)
    private val bookName: TextView = bookView.findViewById(R.id.idTVBookName)
    private val bookAuthor: TextView = bookView.findViewById(R.id.idTVBookPages)
//    private val chatMessage: TextView = bookView.findViewById(R.id.message)
//    private val chatVerified: ImageView = bookView.findViewById(R.id.verified_status)
//    private val chatSendDate: TextView = bookView.findViewById(R.id.date_time)
//    private val chatNewMessageCount: TextView = bookView.findViewById(R.id.new_messages_count)
//    private val chatMessageStatus: ImageView = bookView.findViewById(R.id.send_status)
//    private val chatMutedStatus: ImageView = bookView.findViewById(R.id.muted_status)


    fun bind(book: BooksInfo?)
    {
        //chatAvatar.setBackgroundResource(book.avatarImage)
        bookView.setBackgroundResource(R.drawable.ic_launcher_background)
        bookName.text = book?.volumeInfo?.title
        bookAuthor.text = book?.volumeInfo?.authors?.get(0) ?: ""
//        chatMessage.book = chat.message
//        chatMessage.visibility = if (chat.message.isNullOrEmpty()) View.GONE else View.VISIBLE
//        chatVerified.visibility = if (chat.verified) View.VISIBLE else View.GONE
//        chatSendDate.text = chat.sendDate
//        chatNewMessageCount.text = chat.newMessageCount.toString()
//        chatNewMessageCount.visibility = if(chat.newMessageCount >0) View.VISIBLE else View.GONE
//        chatMessageStatus.setBackgroundResource(chat.messageStatus.res)
//        chatMutedStatus.visibility = if(chat.mutedStatus) View.VISIBLE else View.GONE

//        bookView.setOnClickListener{
//            listener.onBookClick(
//                book,
//                adapterPosition
//            )
//        }
    }
}