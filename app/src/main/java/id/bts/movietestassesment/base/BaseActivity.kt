package id.bts.movietestassesment.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import id.bts.movietestassesment.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

abstract class BaseActivity<VB: ViewBinding, VM: ViewModel> : AppCompatActivity(){

    protected lateinit var binding: VB
    protected lateinit var viewModel: VM

    private lateinit var loadingDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getInflatedLayout())
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

    protected fun showToast(message: String){
        val toast = Toast(this)
        toast.setGravity(Gravity.CENTER,0,0)
        toast.setText(message)
        toast.duration = Toast.LENGTH_SHORT
        toast.show()
    }

    protected fun showLoadingDialog(){

        val inflater = this.layoutInflater
        val builder = AlertDialog.Builder(this)
        builder.setView(inflater.inflate(R.layout.layout_loading, null))
        builder.setCancelable(true)

        loadingDialog = builder.create()
        loadingDialog.show()
    }

    protected fun hideLoadingDialog(){
        if(loadingDialog.isShowing){
            loadingDialog.dismiss()
        }
    }

    protected fun moveToActivity(activity: Activity, delay: Boolean = true){
        val intent = Intent(this, activity::class.java)
        if(delay){
            lifecycleScope.launch {
                while (true){
                    delay(1000)
                    startActivity(intent)
                }
            }
        } else{
            startActivity(intent)
        }
    }
}