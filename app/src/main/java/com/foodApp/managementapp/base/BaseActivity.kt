package com.foodApp.managementapp.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.foodApp.managementapp.APIservice
import com.foodApp.managementapp.RetrofitHelper





abstract class BaseActivity<VB : ViewBinding, VM : ViewModel>(
    private val viewModelClass: Class<VM>,
    private val layoutInflater: (LayoutInflater) -> VB
) : AppCompatActivity() {

    protected lateinit var binding: VB
    protected val viewModel: VM by lazy {
        createViewModel()
    }

    protected val repository: BaseRepository by lazy {
        val retrofitService = RetrofitHelper.getInstance().create(APIservice::class.java)
        BaseRepository(retrofitService)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = layoutInflater.invoke(LayoutInflater.from(this)) // <- fixed here
        setContentView(binding.root)

        setupViews()
    }

    abstract fun setupViews()

    private fun createViewModel(): VM {
        return ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(viewModelClass)) {
                    @Suppress("UNCHECKED_CAST")
                    return viewModelClass.getConstructor(BaseRepository::class.java)
                        .newInstance(repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }).get(viewModelClass)
    }
}

//
//abstract class BaseActivity<V : ViewDataBinding, VM : ViewModel>(
//    private val viewModelClass: Class<VM>,
//    private val layoutId: Int
//) : AppCompatActivity() {
//
//    protected lateinit var binding: V
//    protected val viewModel: VM by lazy {
//        createViewModel()
//    }
//    protected val repository: BaseRepository by lazy {
//        val retrofitService = RetrofitHelper.getInstance().create(APIservice::class.java)
//         BaseRepository(retrofitService)
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = DataBindingUtil.setContentView(this, layoutId)
//        binding.lifecycleOwner = this
//
//        setupViews()
//    }
//
//    abstract fun setupViews()
//
//    private fun createViewModel(): VM {
//        return ViewModelProvider(this, object : ViewModelProvider.Factory {
//           override fun <T : ViewModel> create(modelClass: Class<T>): T {
//                if (modelClass.isAssignableFrom(viewModelClass)) {
//                    @Suppress("UNCHECKED_CAST")
//                    return viewModelClass.getConstructor(BaseRepository::class.java)
//                        .newInstance(repository) as T
//                }
//                throw IllegalArgumentException("Unknown ViewModel class")
//            }
//        }).get(viewModelClass)
//    }
//}

