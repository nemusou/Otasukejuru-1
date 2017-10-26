package jp.ict.muffin.otasukejuru.ui

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.LinearLayout
import jp.ict.muffin.otasukejuru.R
import jp.ict.muffin.otasukejuru.fragment.ScheduleFragment
import org.jetbrains.anko.*
import java.util.*

class ScheduleFragmentUI : AnkoComponent<ScheduleFragment> {
    override fun createView(ui: AnkoContext<ScheduleFragment>): View = with(ui) {
        
        relativeLayout {
            linearLayout {
                id = R.id.taskLinear
                orientation = LinearLayout.HORIZONTAL
            }.lparams {
                width = matchParent
                height = wrapContent
                alignParentTop()
                leftMargin = dip(80)
            }
            scrollView {
                val calendar = Calendar.getInstance()
                var today = (calendar.get(Calendar.MONTH) + 1) * 100 + calendar.get(Calendar.DAY_OF_MONTH)
                relativeLayout {
                    id = R.id.taskRelative
//                    imageView {
//                        backgroundColor = ContextCompat.getColor(context, R.color.mostPriority)
//                    }.lparams {
//                        width = 3
//                        height = 100
//                        leftMargin = 100
//                        topMargin = 20
//                    }
                    (0 until 28).forEach {
                        relativeLayout {
                            textView(when (it % 4) {
                                1 -> "朝"
                                2 -> "昼"
                                3 -> "夜"
                                else -> "${today / 100}月${today % 100}日"
                            }) {
                                id = R.id.hourText
                            }.lparams {
                                width = wrapContent
                                height = wrapContent
                                alignParentStart()
                                centerVertically()
                                leftMargin = dip(when (it % 4) {
                                    0 -> 10
                                    else -> 60
                                })
                            }
                            
                            imageView {
                                backgroundColor = Color.GRAY
                            }.lparams {
                                width = matchParent
                                height = dip(if (it % 4 == 0) {
                                    2
                                } else {
                                    1
                                })
                                leftMargin = dip(80)
                                rightMargin = dip(20)
                                centerVertically()
                                alignParentEnd()
                                
                            }
                        }.lparams {
                            width = matchParent
                            height = dip(50)
                            topMargin = it * 50
                        }
                        today += when {
                            it % 4 == 0 -> 1
                            today == 30 -> 70
                            else -> 0
                        }
                    }
                }.lparams {
                    width = matchParent
                    height = matchParent
                }
            }.lparams {
                width = matchParent
                height = matchParent
                backgroundColor = ContextCompat.getColor(context, R.color.back)
                topMargin = dip(90)
            }
        }
    }
    
}