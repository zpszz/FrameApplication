package com.jpc.flowdemo.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.jpc.flowdemo.R
import com.jpc.flowdemo.adapter.UserAdapter
import com.jpc.flowdemo.databinding.FragmentUserBinding
import com.jpc.flowdemo.viewmodel.UserViewModel
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val binding: FragmentUserBinding by lazy {
        FragmentUserBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModels<UserViewModel>()
    // onCreate方法在什么时候调用？在Fragment创建时调用
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            btnAddUser.setOnClickListener {
                viewModel.insert(
                    etUserId.text.toString(),
                    etUserName.text.toString(),
                    etUserClassName.text.toString()
                )
                Log.d("UserFragment add", "${etUserId.text}, ${etUserName.text}, ${etUserClassName.text}")
            }
        }

        context?.let {
            val userAdapter = UserAdapter(it)
            binding.recyclerView.adapter = userAdapter
            // 在协程里面进行数据库查询耗时操作
            lifecycleScope.launch {
                viewModel.getAllUser().collect{ value ->
                    userAdapter.setData(value)
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
         * @return A new instance of fragment UserFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}