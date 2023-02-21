package com.example.calc123

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.ezylang.evalex.Expression
import java.math.BigDecimal

import com.appsflyer.AppsFlyerLib
import com.appsflyer.attribution.AppsFlyerRequestListener

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //AppsFlyer integration code
        AppsFlyerLib.getInstance().setDebugLog(true)


        AppsFlyerLib.getInstance().init("Z7bAwgthtGfCY3EBXZaZRh", null, this)
        AppsFlyerLib.getInstance().start(this, "Z7bAwgthtGfCY3EBXZaZRh", object :
            AppsFlyerRequestListener {
            override fun onSuccess() {
                Log.d("AppsFlyer", "launch sent successfully")
            }

            override fun onError(errorCode: Int, errorDesc: String) {
                Log.d("AppsFlyer", "Launch failed to be sent:\n" +
                            "Error code: " + errorCode + "\n"
                            + "Error description: " + errorDesc
                )
            }
        })


        val tvLink = findViewById<TextView>(R.id.tvLink)
        tvLink.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            startActivity(intent)
        }


        //numbers
        val zeroBtn = findViewById<TextView>(R.id.btnZero)
        val oneBtn = findViewById<TextView>(R.id.btnOne)
        val twoBtn = findViewById<TextView>(R.id.btnTwo)
        val threeBtn = findViewById<TextView>(R.id.btnThree)
        val fourBtn = findViewById<TextView>(R.id.btnFour)
        val fiveBtn = findViewById<TextView>(R.id.btnFive)
        val sixBtn = findViewById<TextView>(R.id.btnSix)
        val sevenBtn = findViewById<TextView>(R.id.btnSeven)
        val eightBtn = findViewById<TextView>(R.id.btnEight)
        val nineBtn = findViewById<TextView>(R.id.btnNine)

        //operations
        val equalsBtn = findViewById<TextView>(R.id.btnEquals)
        val plusBtn = findViewById<TextView>(R.id.btnPlus)
        val minusBtn = findViewById<TextView>(R.id.btnMinus)
        val divideBtn = findViewById<TextView>(R.id.btnDivide)
        val multiplyBtn = findViewById<TextView>(R.id.btnMultiply)
        val dotBtn = findViewById<TextView>(R.id.btnDot)
        val clearBtn = findViewById<TextView>(R.id.btnClear)
        val backspaceBtn = findViewById<ImageView>(R.id.btnBackspace)
        //text views
        val tvResult = findViewById<TextView>(R.id.tvResult)
        val tvCalculations = findViewById<TextView>(R.id.tvCalculations)

        val calculationsStringBuilder = StringBuilder()


        // adding onClickListeners
        zeroBtn.setOnClickListener {
            val zeroNum = calculationsStringBuilder.append("0")
            tvCalculations.text = zeroNum
        }
        oneBtn.setOnClickListener {
            val oneNum = calculationsStringBuilder.append("1")
            tvCalculations.text = oneNum

        }
        twoBtn.setOnClickListener {
            val twoNum = calculationsStringBuilder.append("2")
            tvCalculations.text = twoNum
        }
        threeBtn.setOnClickListener {
            val threeNum = calculationsStringBuilder.append("3")
            tvCalculations.text = threeNum
        }
        fourBtn.setOnClickListener {
            val fourNum = calculationsStringBuilder.append("4")
            tvCalculations.text = fourNum
        }
        fiveBtn.setOnClickListener {
            val fiveNum = calculationsStringBuilder.append("5")
            tvCalculations.text = fiveNum
        }
        sixBtn.setOnClickListener {
            val sixNum = calculationsStringBuilder.append("6")
            tvCalculations.text = sixNum
        }
        sevenBtn.setOnClickListener {
            val sevenNum = calculationsStringBuilder.append("7")
            tvCalculations.text = sevenNum
        }
        eightBtn.setOnClickListener {
            val zeightNum = calculationsStringBuilder.append("8")
            tvCalculations.text = zeightNum
        }
        nineBtn.setOnClickListener {
            val nineNum = calculationsStringBuilder.append("9")
            tvCalculations.text = nineNum
        }

        plusBtn.setOnClickListener {
            val plusOper = calculationsStringBuilder.append("+")
            tvCalculations.text = plusOper
        }
        minusBtn.setOnClickListener {
            val minusOper = calculationsStringBuilder.append("-")
            tvCalculations.text = minusOper
        }
        divideBtn.setOnClickListener {
            val divideOper = calculationsStringBuilder.append("/")
            tvCalculations.text = divideOper
        }
        multiplyBtn.setOnClickListener {
            val multiplyOper = calculationsStringBuilder.append("*")
            tvCalculations.text = multiplyOper
        }

        dotBtn.setOnClickListener {
            val dotOper = calculationsStringBuilder.append(".")
            tvCalculations.text = dotOper
        }
        clearBtn.setOnClickListener {
            calculationsStringBuilder.clear()
            tvResult.text = "0"
            tvCalculations.text = ""
        }

        backspaceBtn.setOnClickListener {
            val stringBuilderLength = calculationsStringBuilder.length - 1
            if (stringBuilderLength >= 0) {
                calculationsStringBuilder.deleteCharAt(calculationsStringBuilder.length - 1)
                tvCalculations.text = calculationsStringBuilder
                val defValue = calculationsStringBuilder.append("")
                tvCalculations.text = defValue

            } else {
                //do nothing
            }
        }


        equalsBtn.setOnClickListener {
            try {
                //take the expression
                val stringExpression = calculationsStringBuilder.toString()

                //use class Expression and pass it the string
                val expression = Expression(stringExpression)

                //calcilate the result
                val expressionResult = expression.evaluate().numberValue

                //set the calculation result to the text view Result
                tvResult.text = expressionResult.toString()


                //clera the string builder
                calculationsStringBuilder.clear()

                //add the lst number to string builder
                calculationsStringBuilder.append(expressionResult)

            } catch (t: Throwable) {
                Toast.makeText(this@MainActivity, "Exception $t", Toast.LENGTH_LONG)
                    .show()
                calculationsStringBuilder.deleteCharAt(calculationsStringBuilder.length - 1)
                tvCalculations.text = calculationsStringBuilder

            }


        }


    }


}



