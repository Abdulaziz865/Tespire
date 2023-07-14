package cosade.weinol.gabloin.white.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import cosade.weinol.gabloin.R
import cosade.weinol.gabloin.databinding.ActivityHomeBinding
import cosade.weinol.gabloin.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity(R.layout.activity_home) {

    private val binding by viewBinding(ActivityHomeBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}