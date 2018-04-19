package kot.slimmer.fabioapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kot.slimmer.fabioapp.Booking.Booking
import kot.slimmer.fabioapp.Booking.NewBooking
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            goToPage<SplashScreen>()
            finish()
        }

        booking.setOnClickListener { goToPage<Booking>() }

        newBooking.setOnClickListener { goToPage<NewBooking>() }

    }
}
