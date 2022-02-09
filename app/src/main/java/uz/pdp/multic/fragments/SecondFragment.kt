package uz.pdp.multic.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.pdp.multic.R
import uz.pdp.multic.databinding.FragmentSecondBinding

class SecondFragment : Fragment(R.layout.fragment_second) {

    private val binding by viewBinding(FragmentSecondBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            webview.settings.javaScriptEnabled = true
            webview.loadUrl("https://rickandmortyapi.com/")
        }
    }

}