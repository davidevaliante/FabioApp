package kot.slimmer.fabioapp

import android.animation.TimeInterpolator
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.transition.AutoTransition
import android.view.Window
import android.view.WindowManager
import com.google.firebase.auth.FirebaseAuth
import kot.slimmer.fabioapp.auth.Auth
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import kotlinx.android.synthetic.main.activity_splash_screen.*
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.transition.Transition
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import com.bumptech.glide.Glide
import kot.slimmer.fabioapp.R.id.*


class SplashScreen : AppCompatActivity() {

    private val fireAuth : FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var viewGroup : ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash_screen)


        viewGroup = root as ViewGroup

        animateSubname()
        animateIcons()
        animateName()

        Handler().postDelayed({
            if(fireAuth.currentUser != null){
                goToPage<MainActivity>()
                finish()
            }else{
                goToPage<Auth>()
            }
        },2600)

    }

    private fun animateIcons(){
        Handler().postDelayed({
            var animateIcons = com.transitionseverywhere.Fade()
            animateIcons.duration = 600
            var interpolator = LinearOutSlowInInterpolator()
            animateIcons.interpolator = interpolator
            val vg = icons_group as ViewGroup

            com.transitionseverywhere.TransitionManager.beginDelayedTransition(vg,animateIcons)

            instagram_logo.visibility = View.VISIBLE

        },50)

        Handler().postDelayed({
            var animateIcons = com.transitionseverywhere.Fade()
            animateIcons.duration = 600
            var interpolator = LinearOutSlowInInterpolator()
            animateIcons.interpolator = interpolator
            val vg = icons_group as ViewGroup

            com.transitionseverywhere.TransitionManager.beginDelayedTransition(vg,animateIcons)

            ifacebook_logo.visibility = View.VISIBLE

        },250)

        Handler().postDelayed({
            var animateIcons = com.transitionseverywhere.Fade()
            animateIcons.duration = 600
            var interpolator = LinearOutSlowInInterpolator()
            animateIcons.interpolator = interpolator
            val vg = icons_group as ViewGroup

            com.transitionseverywhere.TransitionManager.beginDelayedTransition(vg,animateIcons)

            snapchat_logo.visibility = View.VISIBLE

        },500)
    }

    private fun deactivateIconsAndName(){


        Handler().postDelayed({
            var animateIcons = com.transitionseverywhere.Fade()
            animateIcons.duration = 300
            var interpolator = LinearOutSlowInInterpolator()
            animateIcons.interpolator = interpolator
            val vg = icons_group as ViewGroup

            com.transitionseverywhere.TransitionManager.beginDelayedTransition(vg,animateIcons)

            instagram_logo.visibility = View.GONE

        },150)

        Handler().postDelayed({
            var animateIcons = com.transitionseverywhere.Fade()
            animateIcons.duration = 300
            var interpolator = LinearOutSlowInInterpolator()
            animateIcons.interpolator = interpolator
            val vg = icons_group as ViewGroup

            com.transitionseverywhere.TransitionManager.beginDelayedTransition(vg,animateIcons)

            ifacebook_logo.visibility = View.GONE

        },300)

        Handler().postDelayed({

            var animateIcons = com.transitionseverywhere.AutoTransition()
            animateIcons.duration = 300
            var interpolator = LinearOutSlowInInterpolator()
            animateIcons.interpolator = interpolator
            val vg = icons_group as ViewGroup

            com.transitionseverywhere.TransitionManager.beginDelayedTransition(vg,animateIcons)

            snapchat_logo.visibility = View.GONE

        },450)

        Handler().postDelayed({
            deactivateName()
            deactivateLines()
            deactivateSubName()
        },650)
    }

    private fun animateSubname(){
        Handler().postDelayed({
            var animateSubname = com.transitionseverywhere.AutoTransition()
            animateSubname.duration = 400
            val vg = root as ViewGroup

            com.transitionseverywhere.TransitionManager.beginDelayedTransition(vg,animateSubname)

            logoSubname.visibility = View.VISIBLE
            Handler().postDelayed({
                deactivateIconsAndName()
            },500)
        },800)
    }

    private fun animateName(){
        Handler().postDelayed({
            var nameAnim : com.transitionseverywhere.Transition = com.transitionseverywhere.AutoTransition()
            //quanto deve durare la transizione
            nameAnim.duration = 1400
            //interpolatore
            var interpolator = FastOutSlowInInterpolator()
            nameAnim.interpolator = interpolator

            val vg = logoGroup as ViewGroup

            com.transitionseverywhere.TransitionManager.beginDelayedTransition(vg,nameAnim)

            logoName.visibility = View.VISIBLE
        },50)
    }

    private fun deactivateName(){
        var nameAnim : com.transitionseverywhere.Transition = com.transitionseverywhere.AutoTransition()
        //quanto deve durare la transizione
        nameAnim.duration = 500
        //interpolatore
        var interpolator = FastOutSlowInInterpolator()
        nameAnim.interpolator = interpolator

        val vg = logoGroup as ViewGroup

        com.transitionseverywhere.TransitionManager.beginDelayedTransition(vg,nameAnim)

        logoName.visibility = View.GONE
    }

    private fun deactivateSubName(){
        var nameAnim : com.transitionseverywhere.Transition = com.transitionseverywhere.AutoTransition()
        //quanto deve durare la transizione
        nameAnim.duration = 300
        //interpolatore
        var interpolator = FastOutSlowInInterpolator()
        nameAnim.interpolator = interpolator

        val vg = logoGroup as ViewGroup

        com.transitionseverywhere.TransitionManager.beginDelayedTransition(vg,nameAnim)

        logoSubname.visibility = View.GONE
    }

    private fun deactivateLines(){
        var linesAnim : com.transitionseverywhere.Transition = com.transitionseverywhere.AutoTransition()
        //quanto deve durare la transizione
        linesAnim.duration = 300
        //interpolatore
        var interpolator = FastOutSlowInInterpolator()
        linesAnim.interpolator = interpolator

        val vg = logoGroup as ViewGroup

        com.transitionseverywhere.TransitionManager.beginDelayedTransition(vg,linesAnim)

        topLine.visibility = View.GONE
        bottomLine.visibility = View.GONE
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

}
