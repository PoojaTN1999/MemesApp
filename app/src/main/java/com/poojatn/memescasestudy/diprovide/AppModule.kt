package com.poojatn.memescasestudy.diprovide

import android.content.Context
import com.poojatn.memescasestudy.database.MemeDaoImpl
import com.poojatn.memescasestudy.database.MemesDatabase
import com.poojatn.memescasestudy.network.iApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // 1
    @Provides
    fun providesBaseUrl() : String = "https://api.imgflip.com/"

    // 2 needs step 1
//    when we call function as provides then we can inject the function whenver we want
  @Provides
    @Singleton
    fun providesRetrofitBuilder( baseUrl:String) : Retrofit =
        Retrofit.Builder()
            .baseUrl( baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    // 3 needs step 2
    @Provides
    fun providesApiPostService( retrofit: Retrofit) : iApiService =
        retrofit.create(iApiService::class.java)


    @Provides
    @Singleton
    fun providesDatabseBuilder(@ApplicationContext context: Context) : MemesDatabase =
        MemesDatabase.getDatabase(context);

    @Provides
    fun providesMemesDataBase(memesDatabase: MemesDatabase): MemeDaoImpl =
        MemeDaoImpl((memesDatabase.memeDao()))


}
