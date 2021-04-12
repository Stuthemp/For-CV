package com.example.liststraining

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast
import com.example.liststraining.rolefragments.LeagueFragment

class MainActivity : AppCompatActivity(),GestureDetector.OnGestureListener {

    lateinit var gestureDetector: GestureDetector
    var x2:Float = 0.0f
    var x1:Float = 0.0f
    var y2:Float = 0.0f
    var y1:Float = 0.0f

    companion object{
        const val MIN_DISTANCE=10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gestureDetector = GestureDetector(this,this)


        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LeagueFragment.newInstance())
                .commit()
        }

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        gestureDetector.onTouchEvent(event)

        when(event?.action){
            //Начинаем свайп
            0->
            {
                x1=event.x
                y1=event.x
            }

            //Заканчиваем свайп
            1->
            {
                x2=event.x
                y2=event.x

                val valueX:Float = x2-x1
                val valueY:Float = y2-y1

                if(kotlin.math.abs(valueX) > MIN_DISTANCE)
                {
                    if(x2 > x1){
                        Toast.makeText(this,"Nothing", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        val toplanersIntent: Intent = Intent(this,ToplanersActivity::class.java)
                        startActivity(toplanersIntent)
                        Toast.makeText(this,"Toplaners",Toast.LENGTH_SHORT).show()
                    }
                }

                else if (kotlin.math.abs(valueY) > MIN_DISTANCE){
                    //Сверху вниз
                    if(y2 > y1){
                        Toast.makeText(this,"Bottom",Toast.LENGTH_SHORT).show()
                    }
                    //Снизу вверх
                    else{
                        Toast.makeText(this,"Top",Toast.LENGTH_SHORT).show()
                    }
                }


            }


        }


        return super.onTouchEvent(event)
    }

    override fun onDown(e: MotionEvent?): Boolean {
       //TODO("Not yet implemented")
        return false
    }

    override fun onShowPress(e: MotionEvent?) {
      //TODO("Not yet implemented")
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        //TODO("Not yet implemented")
        return false
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        //TODO("Not yet implemented")
        return false
    }

    override fun onLongPress(e: MotionEvent?) {
        //TODO("Not yet implemented")
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        //TODO("Not yet implemented")
        return false
    }
}
