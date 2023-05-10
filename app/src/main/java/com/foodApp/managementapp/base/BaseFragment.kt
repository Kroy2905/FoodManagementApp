package com.foodApp.managementapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.foodApp.managementapp.APIservice
import com.foodApp.managementapp.RetrofitHelper


abstract class BaseFragment<VB : ViewBinding, VM : ViewModel>(
    private val viewModelClass: Class<VM>,
    private val layoutInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment() {

    protected lateinit var binding: VB
    protected val viewModel: VM by lazy {
        createViewModel()
    }

    protected val repository: BaseRepository by lazy {
        val retrofitService = RetrofitHelper.getInstance().create(APIservice::class.java)
        BaseRepository(retrofitService)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = layoutInflater.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        })[viewModelClass]
    }
}
