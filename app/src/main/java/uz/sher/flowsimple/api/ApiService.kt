package uz.sher.flowsimple.api

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import uz.sher.flowsimple.model.User

interface ApiService {

    @GET("users")
     fun getUsers(): Flow<List<User>>

}