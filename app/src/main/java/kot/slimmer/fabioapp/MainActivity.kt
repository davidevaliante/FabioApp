package kot.slimmer.fabioapp

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.transitionseverywhere.*
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadCardImage()
        cardsSlideIn()
        iconsFadeIn()
        gradientFadeIn()

        cutCard.setOnClickListener {
            goToPage<PicturesActivity>()
        }
        bookingCard.setOnClickListener {

        }
        contactsCard.setOnClickListener {

        }

    }

    private fun loadCardImage(){
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/fabio-2e829.appspot.com/o/Static%2Fthe-old-city-barber-shop-in-black-and-white-greg-mimbs.jpg?alt=media&token=02c488fa-222c-4f30-a135-42a35a055926")
                .into(cutsCardImg)
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/fabio-2e829.appspot.com/o/Static%2F25025811_1924081504507766_9041834734304362496_n.jpg?alt=media&token=c279b47b-52e5-445b-9658-6cea6b491a87")
                .into(aboutImg)


    }

    // tot duration 850ms
    private fun cardsSlideIn(){
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

    private fun gradientFadeIn(){
        Handler().postDelayed({
            var nameAnim : com.transitionseverywhere.Transition = com.transitionseverywhere.AutoTransition()
            //quanto deve durare la transizione
            nameAnim.duration = 2400
            //interpolatore
            var interpolator = FastOutSlowInInterpolator()
            nameAnim.interpolator = interpolator

            val vg = mainActivityRoot as ViewGroup

            com.transitionseverywhere.TransitionManager.beginDelayedTransition(vg,nameAnim)

            icons_bg.visibility = View.VISIBLE
        },500)
    }

    private fun iconsFadeIn(){
        Handler().postDelayed({
            var nameAnim : com.transitionseverywhere.Transition = com.transitionseverywhere.AutoTransition()
            //quanto deve durare la transizione
            nameAnim.duration = 700
            //interpolatore
            var interpolator = FastOutSlowInInterpolator()
            nameAnim.interpolator = interpolator

            val vg = mainActivityRoot as ViewGroup

            com.transitionseverywhere.TransitionManager.beginDelayedTransition(vg,nameAnim)

            appBooking.visibility = View.VISIBLE
        },850)

        Handler().postDelayed({
            var nameAnim : com.transitionseverywhere.Transition = com.transitionseverywhere.AutoTransition()
            //quanto deve durare la transizione
            nameAnim.duration = 700
            //interpolatore
            var interpolator = FastOutSlowInInterpolator()
            nameAnim.interpolator = interpolator

            val vg = mainActivityRoot as ViewGroup

            com.transitionseverywhere.TransitionManager.beginDelayedTransition(vg,nameAnim)

            whatsappBooking.visibility = View.VISIBLE
        },1050)

        Handler().postDelayed({
            var nameAnim : com.transitionseverywhere.Transition = com.transitionseverywhere.AutoTransition()
            //quanto deve durare la transizione
            nameAnim.duration = 700
            //interpolatore
            var interpolator = FastOutSlowInInterpolator()
            nameAnim.interpolator = interpolator

            val vg = mainActivityRoot as ViewGroup

            com.transitionseverywhere.TransitionManager.beginDelayedTransition(vg,nameAnim)

            phoneBooking.visibility = View.VISIBLE
        },1250)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

}
