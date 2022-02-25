package id.bts.movietestassesment.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import java.text.SimpleDateFormat

abstract class BaseFragment<VB: ViewBinding, VM: ViewModel> : Fragment() {

    protected lateinit var binding: VB
    protected lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return getInflatedLayout()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupView()
    }

    @NonNull
    private fun getInflatedLayout(): View {
        this.binding = setLayout(layoutInflater)
        return binding.root
    }

    private fun setupViewModel(){
        this.viewModel = setViewModel()
    }

    protected abstract fun setLayout(inflater: LayoutInflater): VB

    protected abstract fun setViewModel(): VM

    protected abstract fun setupView()

    @SuppressLint("SimpleDateFormat")
    protected fun formatDate(dateStr: String) : String{
        var sdf = SimpleDateFormat("yyyy-MM-dd")
        val releaseDate = sdf.parse(dateStr)
        sdf = SimpleDateFormat("dd MMM yyyy")
        return sdf.format(releaseDate)
    }

}