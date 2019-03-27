package com.wx.dialogmaster.activity

import android.databinding.ViewDataBinding
import com.wx.dialogmaster.R
import com.wx.dialogmaster.base.BaseActivity

class MaterialDesignActivity : BaseActivity() {

    override val layoutId: Int
        get() = R.layout.activity_materialdesign

    override fun setDataBinding(binding: ViewDataBinding) {

    }

    override fun initData() {
        /*MaterialDialog.Builder(this)
                    .title("标题")
                    .content("我是内容")
                    .positiveText("确定")
                    .negativeText("取消")
                    .onPositive { dialog, which ->
                        showToast(which.name)
                    }
                    .show();*/
        /*MaterialDialog.Builder(this)
                .title("标题")
                .content("我是内容")
                .positiveText("确定")
                .negativeText("取消")
                .icon(resources.getDrawable(R.mipmap.ic_launcher))
                .maxIconSize(48)
                .show()*/
        /*MaterialDialog.Builder(this)
                .title("标题")
                .content("我是内容")
                .positiveText("确定")
                .negativeText("取消")
                .stackingBehavior(StackingBehavior.ADAPTIVE)
                .show()*/
        /*MaterialDialog.Builder(this)
                .title("标题")
                .content("我是内容")
                .positiveText("确定")
                .negativeText("取消")
                .neutralText("中立的信息")
                .show()*/

        /*MaterialDialog.Builder(this)
                .title("标题")
                .items(R.array.items)
                .itemsCallback(MaterialDialog.ListCallback { dialog, view, which, text ->
                    showToast(text.toString()+" "+which)
                })
                .show()*/
        /*MaterialDialog.Builder(this)
                .title("标题")
                .items(R.array.items)
                .itemsCallbackSingleChoice(-1, object : MaterialDialog.ListCallbackSingleChoice {
                    override fun onSelection(dialog: MaterialDialog, view: View, which: Int, text: CharSequence): Boolean {

                        return true
                    }
                })
                .positiveText("确定")
                .show()*/

        /*MaterialDialog.Builder(this)
                .title("标题")
                .customView(R.layout.custom_view, false)
                .positiveText("确定")
                .show()*/

        /*MaterialDialog.Builder(this)
                .content("Hi")
                .theme(Theme.DARK)
                .show()*/

        /*MaterialDialog.Builder(this)
                .title("标题")
                .content("内容")
                .inputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL)
                .input("请输入", "天天向上", MaterialDialog.InputCallback { dialog, input ->
                    // Do something
                })
                .show()*/

        /*MaterialDialog.Builder(this)
                .canceledOnTouchOutside(false)
                .content("请稍等")
                .progress(true, 0)
                .show()*/

        /*val showMinMax = true
        val dialog = MaterialDialog.Builder(this)
                .title("标题")
                .content("请稍等")
                .progress(false, 150, showMinMax)
                .show()

        // Loop until the dialog's progress value reaches the max (150)
        while (dialog.getCurrentProgress() != dialog.getMaxProgress()) {
            // If the progress dialog is cancelled (the user closes it before it's done), break the loop
            if (dialog.isCancelled()) break
            // Wait 50 milliseconds to simulate doing work that requires progress
            try {
                Thread.sleep(50)
            } catch (e: InterruptedException) {
                break
            }

            // Increment the dialog's progress by 1 after sleeping for 50ms
            dialog.incrementProgress(1)
        }

        // When the loop exits, set the dialog content to a string that equals "Done"
        dialog.setContent("完成")*/



    }

}
