package jp.ict.muffin.otasukejuru.ui

import android.graphics.Color
import android.view.View
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import jp.ict.muffin.otasukejuru.`object`.GlobalValue
import jp.ict.muffin.otasukejuru.activity.InputProgressActivity
import jp.ict.muffin.otasukejuru.communication.UpdateTaskInfoAsync
import kotlinx.android.synthetic.main.activity_selection.view.textView
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.relativeLayout
import org.jetbrains.anko.seekBar
import org.jetbrains.anko.margin

class InputProgressActivityUI(private val index: Int) : AnkoComponent<InputProgressActivity> {
    private lateinit var progressSeekBar: SeekBar

    override fun createView(ui: AnkoContext<InputProgressActivity>): View = with(ui) {
        relativeLayout {
            relativeLayout {
                progressSeekBar = seekBar {
                    id = R.id.inputProgressSeek
                    progress = GlobalValue.taskInfoArrayList[index].progress
                }.lparams {
                    width = matchParent
                    height = wrapContent
                    margin = dip(100)
                }

                textView(GlobalValue.taskInfoArrayList[index].progress.toString()) {
                    id = R.id.progressTextView
                    text = progressSeekBar.progress.toString()
                    textSize = 20f
                }.lparams {
                    below(progressSeekBar)
                    centerHorizontally()
                }
            }.lparams {
                width = wrapContent
                height = wrapContent
                centerVertically()
                centerHorizontally()
            }

            button("確定") {
                id = R.id.finishButton
                backgroundColor = Color.argb(0, 0, 0, 0)
                textColor = ContextCompat.getColor(context, R.color.colorPrimary)
                textSize = 20f
                onClick {
                    GlobalValue.taskInfoArrayList[index].progress = progressSeekBar.progress
                    UpdateTaskInfoAsync().execute(GlobalValue.taskInfoArrayList[index])
                }
            }.lparams {
                margin = 30
                alignParentBottom()
                alignParentRight()
            }
        }
    }
}