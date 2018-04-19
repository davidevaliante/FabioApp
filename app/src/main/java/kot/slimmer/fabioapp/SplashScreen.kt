package kot.slimmer.fabioapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kot.slimmer.fabioapp.auth.Auth

class SplashScreen : AppCompatActivity() {

    private val fireAuth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
    }

    override fun onStart() {
        super.onStart()
        if(fireAuth.currentUser != null){
            goToPage<MainActivity>()
        }else{
            goToPage<Auth>()
        }
    }
}
