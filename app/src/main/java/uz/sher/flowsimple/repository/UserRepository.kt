package uz.sher.flowsimple.repository


import uz.sher.flowsimple.api.ApiService

class UserRepository(private val apiService: ApiService) {

      fun getUsers() = apiService.getUsers()
}