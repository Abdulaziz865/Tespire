package cosade.weinol.gabloin.white.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import cosade.weinol.gabloin.R
import cosade.weinol.gabloin.databinding.ActivityMainBinding
import cosade.weinol.gabloin.white.utils.PreferenceHelper

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Tespire)
        Thread.sleep(2000)
        setupListeners()
        checkPreference()
    }

    private fun setupListeners() {
        binding.btnStart.setOnClickListener {
            PreferenceHelper.isStartApp = true

            val intent = Intent(this, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    private fun checkPreference() {
        if (PreferenceHelper.isStartApp) {
            val intent = Intent(this, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}