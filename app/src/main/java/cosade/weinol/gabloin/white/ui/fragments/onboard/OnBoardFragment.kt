package cosade.weinol.gabloin.white.ui.fragments.onboard

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import cosade.weinol.gabloin.R
import cosade.weinol.gabloin.databinding.FragmentOnBoardBinding
import cosade.weinol.gabloin.white.ui.activity.HomeActivity
import cosade.weinol.gabloin.white.utils.PreferenceHelper

class OnBoardFragment : Fragment(R.layout.fragment_on_board) {

    private val binding by viewBinding(FragmentOnBoardBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        checkPreference()
    }

    private fun setupListeners() {
        binding.btnStart.setOnClickListener {
            PreferenceHelper.isStartApp = true

            val intent = Intent(activity, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun checkPreference() {
        if (PreferenceHelper.isStartApp) {
            findNavController().navigate(R.id.homeFragment)
        }
    }
}