package uz.sher.flowsimple

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.sher.flowsimple.adapter.UserAdapter
import uz.sher.flowsimple.databinding.ActivityMainBinding
import uz.sher.flowsimple.model.User
import uz.sher.flowsimple.viewmodel.MainViewModel
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        launch {
            mainViewModel.getUserDataFlow()
                .collect {
                    when {
                        it.isSuccess -> {
                            val list = it.getOrNull()
                            val adapter = list?.let { it1 -> UserAdapter(it1) }
                            binding.userRv.adapter = adapter

                        }
                        it.isFailure -> {
                            Toast.makeText(
                                this@MainActivity,
                                it.exceptionOrNull().toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
        }


    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}