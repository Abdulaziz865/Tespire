package cosade.weinol.gabloin.white

import android.app.Application
import cosade.weinol.gabloin.white.utils.PreferenceHelper

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        PreferenceHelper.units(this)
    }
}