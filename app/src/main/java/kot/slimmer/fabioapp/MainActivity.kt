package kot.slimmer.fabioapp

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.view.animation.FastOutLinearInInterpolator
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationSet
import android.view.animation.BounceInterpolator
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.transitionseverywhere.*
import jp.wasabeef.recyclerview.animators.FadeInAnimator
import kot.slimmer.fabioapp.Booking.Booking
import kot.slimmer.fabioapp.Booking.NewBooking
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Glide.with(this).load("http://static.fashionbeans.com/wp-content/uploads/2018/02/dropfadesimg-4.jpg")
                .into(cutsCardImg)
        Glide.with(this).load("http://static.fashionbeans.com/wp-content/uploads/2018/02/dropfadesimg-4.jpg")
                .into(bookingCardImg)
        Glide.with(this).load("http://static.fashionbeans.com/wp-content/uploads/2018/02/dropfadesimg-4.jpg")
                .into(contactsCardImg)

        Handler().postDelayed({
            val slide = Slide(Gravity.RIGHT)
            slide.duration = 250
            slide.interpolator = FastOutSlowInInterpolator()
            val set = TransitionSet()
            set.addTransition(slide)
            TransitionManager.beginDelayedTransition(mainActivityRoot as ViewGroup, set)
            cutCard.visibility = View.VISIBLE

        },200)

        Handler().postDelayed({
            val slide = Slide(Gravity.RIGHT)
            slide.duration = 250
            slide.interpolator = FastOutSlowInInterpolator()
            val set = TransitionSet()
            set.addTransition(slide)
            TransitionManager.beginDelayedTransition(mainActivityRoot as ViewGroup, set)
            bookingCard.visibility = View.VISIBLE

        },400)

        Handler().postDelayed({
            val slide = Slide(Gravity.RIGHT)
            slide.duration = 250
            slide.interpolator = FastOutSlowInInterpolator()

            val set = TransitionSet()
            set.addTransition(slide)
            TransitionManager.beginDelayedTransition(mainActivityRoot as ViewGroup, set)
            contactsCard.visibility = View.VISIBLE

        },600)


    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

}
