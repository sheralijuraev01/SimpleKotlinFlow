package uz.sher.flowsimple.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import uz.sher.flowsimple.api.ApiClient
import uz.sher.flowsimple.api.ApiService
import uz.sher.flowsimple.model.User
import uz.sher.flowsimple.repository.UserRepository

class MainViewModel : ViewModel() {
    private val userInterface = ApiClient().retrofitInstance().create(ApiService::class.java)
    private val repository = UserRepository(userInterface)

    private val stateFlow: MutableStateFlow<Result<List<User>>> = MutableStateFlow(
        Result.success(
            emptyList()
        )
    )

    init {
        fetchUserData()
    }

    private fun fetchUserData() {
        viewModelScope.launch {
            repository.getUsers()
                .catch {
                    stateFlow.emit(Result.failure(it))

                }

//                .map {
//                    val list: MutableList<User> = ArrayList()
//                    it.body()?.forEach { user ->
//                        if (user.id == 5)
//                            list.add(user)
//
//                    }
//                    list
//                }
                .collect {
                    stateFlow.emit(Result.success(it))
                }
        }

    }


    fun getUserDataFlow(): MutableStateFlow<Result<List<User>>> = stateFlow
}






