package com.esgi.promocare_android.data


import com.esgi.promocare_android.network.Retrofit
import com.esgi.promocare_android.network.conversation_services.ConversationRepository
import com.esgi.promocare_android.network.conversation_services.ConversationServices
import com.esgi.promocare_android.viewmodel.conversation.LatestConvViewModel
import com.esgi.promocare_android.viewmodel.conversation.ConvViewModel

object Conversation {
    private val conversationServices: ConversationServices by lazy {
        createServices()
    }

    private val postFirstConvViewModel: ConvViewModel = postFirstConvUserViewModel()

    private fun postFirstConvUserViewModel(): ConvViewModel {
        return ConvViewModel(ConversationRepository(conversationServices))
    }

    fun getPostFirstConvUserViewModel() = postFirstConvViewModel

    private val latestConvViewModel : LatestConvViewModel by lazy {
        latestConvViewModel()
    }

    private fun createServices(): ConversationServices {
        return Retrofit.getRetrofitClient().create(ConversationServices::class.java)
    }

    private fun latestConvViewModel(): LatestConvViewModel {
        return LatestConvViewModel(ConversationRepository(conversationServices))
    }

    fun getLastConvViewModel() = latestConvViewModel

}