package com.jpc.flowdemo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.jpc.flowdemo.R
import com.jpc.flowdemo.adapter.ArticleAdapter
import com.jpc.flowdemo.adapter.ArticleLoadMoreAdapter
import com.jpc.flowdemo.databinding.FragmentPagingBinding
import com.jpc.flowdemo.viewmodel.ArticleViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PagingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PagingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val binding: FragmentPagingBinding by lazy { FragmentPagingBinding.inflate(layoutInflater) }
    private val viewModel: ArticleViewModel by viewModels<ArticleViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
         * @return A new instance of fragment PagingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PagingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let { context ->
            val adapter = ArticleAdapter(context)
            binding.recyclerView.adapter = adapter.withLoadStateFooter(ArticleLoadMoreAdapter(this.requireContext()))
            binding.swipeRefreshLayout.setOnRefreshListener {
                adapter.refresh()
            }
            lifecycleScope.launch {
                viewModel.loadArticle().collectLatest {
                    adapter.submitData(it)
                }
            }
            // 两个协程要分开写
            lifecycleScope.launch {
                adapter.loadStateFlow.collectLatest {state ->
                    binding.swipeRefreshLayout.isRefreshing = state.refresh is LoadState.Loading
                }
            }
        }
    }
}