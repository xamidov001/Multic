package uz.pdp.multic.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.pdp.multic.R
import uz.pdp.multic.adapters.Adapter
import uz.pdp.multic.databinding.FragmentFirstBinding
import uz.pdp.multic.room.database.MyDatabase
import uz.pdp.multic.room.entity.MultClass
import uz.pdp.multic.utils.PaginationScrollListener
import uz.pdp.multic.viewmodel.SplashViewModel
import uz.pdp.multic.viewmodel.ViewModelFactory

class FirstFragment : Fragment(R.layout.fragment_first) {

    private val binding by viewBinding(FragmentFirstBinding::bind)
    private lateinit var viewModel: SplashViewModel
    private var adapter = Adapter()
    private lateinit var myDatabase: MyDatabase
    private lateinit var list: ArrayList<MultClass>

    private var currentPage
        get() = viewModel.currentPage
        set(value) {
            viewModel.currentPage = value
        }

    private var isLastPage
        get() = viewModel.isLastPage
        set(value) {
            viewModel.isLastPage = value
        }

    private var isLoading
        get() = viewModel.isLoading
        set(value) {
            viewModel.isLoading = value
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myDatabase = MyDatabase.getInstance(requireContext())
        list = ArrayList()

        viewModel = ViewModelProvider(this, ViewModelFactory())[SplashViewModel::class.java]

        viewModel.getMainDataModels(1)

        binding.recycle.adapter = adapter
        binding.recycle.addOnScrollListener(pagination)

        viewModel.mainDataListData.observe(viewLifecycleOwner) {
            if (currentPage == 0) {
                adapter.addData(arrayListOf())
            }
            if (it != null && !isLastPage) {
                adapter.addData(it)

                it.forEachIndexed { index, mainDataModel ->
                    list.add(
                        MultClass(
                            mainDataModel.result.id.toString(),
                            mainDataModel.result.name,
                            "${mainDataModel.result.status} - ${mainDataModel.result.species}",
                            mainDataModel.result.location.name,
                            mainDataModel.episodeClass.name,
                            mainDataModel.result.url
                        )
                    )
                }

                myDatabase.helper().addList(list)

                if (currentPage == viewModel.totalPageData.value) {
                    isLastPage = true
                }
            }
        }

        viewModel.errorData.observe(viewLifecycleOwner) {
            if (it != null) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            binding.progres.visibility = if (it) View.VISIBLE else View.GONE
        }

        adapter.clickIm {
            findNavController().navigate(R.id.secondFragment)
        }

    }

    private val pagination by lazy {
        object : PaginationScrollListener(binding.recycle.layoutManager!!) {
            override fun loadMoreItems() {
                currentPage += 1
                viewModel.getMainDataModels(currentPage)
            }

            override fun isLastPage(): Boolean {
                return this@FirstFragment.isLastPage
            }

            override fun isLoading(): Boolean {
                return this@FirstFragment.isLoading
            }
        }
    }

}