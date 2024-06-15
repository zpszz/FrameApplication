package com.jpc.flowdemo.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.jpc.flowdemo.R
import com.jpc.flowdemo.databinding.FragmentDownloadBinding
import com.jpc.flowdemo.databinding.FragmentHomeBinding
import com.jpc.flowdemo.download.DownloadManager
import com.jpc.flowdemo.download.DownloadStatus
import kotlinx.coroutines.launch
import java.io.File

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DownloadFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DownloadFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val binding: FragmentDownloadBinding by lazy{
        FragmentDownloadBinding.inflate(layoutInflater)
    }
    private val URL = "https://cdn.uukit.com/images/xcx/uukit/xcx-code.jpg"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // lifecycleScope 是一个由 FragmentKtx 扩展函数提供的属性，它可以让我们在 Fragment 中创建一个协程作用域
        // 通过 lifecycleScope.launch{} 启动一个协程，这样我们就可以在 Fragment 中方便地使用协程进行异步操作
        // 当 Fragment 被销毁时，会自动取消在该作用域中启动的协程，从而避免内存泄漏的发生
        lifecycleScope.launch {
            context?.apply {
                val file = File(getExternalFilesDir(null)?.path, "pic.zip")
                DownloadManager.download(URL, file).collect{ status ->
                    when(status){
                        is DownloadStatus.Progress -> {
                            binding.apply {
                                progressBar.progress = status.value
                                tvProgress.text = "${status.value}%"
                            }
                        }
                        is DownloadStatus.Error -> {
                            Toast.makeText(context, "下载错误", Toast.LENGTH_SHORT).show()
                        }
                        is DownloadStatus.Done -> {
                            binding.apply {
                                progressBar.progress = 100
                                tvProgress.text = "100%"
                            }
                            Toast.makeText(context, "下载完成", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Log.d("DownloadFragment", "下载失败")
                        }
                    }
                }
            }
        }

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DownloadFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DownloadFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}