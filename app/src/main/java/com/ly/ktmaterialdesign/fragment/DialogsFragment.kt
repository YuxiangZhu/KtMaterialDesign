package com.ly.ktmaterialdesign.fragment

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.app.ProgressDialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.TimePicker
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.StringUtils.getStringArray
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ly.ktmaterialdesign.MyApplication
import com.ly.ktmaterialdesign.R
import com.ly.ktmaterialdesign.databinding.FragmentDialogsBinding
import java.text.DateFormat
import java.util.*

/**
 * 作者： Alex
 * 日期： 2020-06-03
 * 签名： 保持学习

 * ----------------------------------------------------------------
 *
 */
class DialogsFragment : Fragment(), View.OnClickListener {

    private val calendar = Calendar.getInstance()
    private lateinit var binding: FragmentDialogsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDialogsBinding.inflate(inflater)
        binding.btnDialog1.setOnClickListener(this)
        binding.btnDialog2.setOnClickListener(this)
        binding.btnDialog3.setOnClickListener(this)
        binding.btnDialog4.setOnClickListener(this)
        binding.btnDialog5.setOnClickListener(this)
        binding.btnDialog6.setOnClickListener(this)
        binding.btnDialog7.setOnClickListener(this)
        binding.btnDialog8.setOnClickListener(this)
        binding.btnDialog9.setOnClickListener(this)
        binding.btnDialog10.setOnClickListener(this)
        binding.btnDialog11.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.btn_dialog_1 -> {

                AlertDialog.Builder(activity!!)
                    .setMessage(getString(R.string.main_dialog_simple_title))
                    .setPositiveButton(getString(R.string.dialog_ok), null)
                    .show()

            }

            R.id.btn_dialog_2 -> {
                AlertDialog.Builder(context!!)
                    .setTitle(getString(R.string.main_dialog_simple_title))
                    .setMessage(getString(R.string.main_dialog_simple_message))
                    .setPositiveButton(getString(R.string.dialog_ok), null)
                    .setNegativeButton(getString(R.string.dialog_cancel), null)
                    .setNeutralButton(getString(R.string.dialog_neutral), null)
                    .show()
            }

            R.id.btn_dialog_3 -> {
                val singleChoiceItems =
                    getStringArray(R.array.dialog_choice_array)
                val itemSelected = 0
                AlertDialog.Builder(context!!)
                    .setTitle(getString(R.string.main_dialog_single_choice))
                    .setSingleChoiceItems(
                        singleChoiceItems,
                        itemSelected
                    ) { dialogInterface, i -> dialogInterface.dismiss() }
                    .setNegativeButton(getString(R.string.dialog_cancel), null)
                    .show()
            }

            R.id.btn_dialog_4 -> {


                val multiChoiceItems =
                    getStringArray(R.array.dialog_choice_array)
                val checkedItems =
                    booleanArrayOf(true, false, false, false, false)
                //监听事件
                AlertDialog.Builder(context!!)
                    .setTitle(getString(R.string.main_dialog_multi_choice))
                    .setMultiChoiceItems(
                        multiChoiceItems, checkedItems
                    ) { _, which, isChecked -> checkedItems[which] = isChecked }
                    .setPositiveButton(
                        getString(R.string.dialog_ok)
                    ) { dialog, which -> LogUtils.e(checkedItems) }
                    .setNegativeButton(getString(R.string.dialog_cancel), null)
                    .show()

            }

            R.id.btn_dialog_5 -> {

                //ProgressDialog废弃推荐使用ProgressBar
                val progressDialog = ProgressDialog(context)
                progressDialog.setMessage(getString(R.string.main_dialog_progress_title))
                progressDialog.setTitle("请稍后")
                progressDialog.setCanceledOnTouchOutside(false)

//                progressDialog.setCancelable(false)
                progressDialog.show()
            }

            R.id.btn_dialog_6 -> {
                var progress = 0
                val horizontalProgressDialog = ProgressDialog(context)
                horizontalProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
                horizontalProgressDialog.setMessage(getString(R.string.main_dialog_progress_title))
                horizontalProgressDialog.setCancelable(false)
                horizontalProgressDialog.max = 100
                horizontalProgressDialog.show()

                MyApplication.application?.runOnBackground(Runnable {
                    while (progress <= 100) {
                        horizontalProgressDialog.progress = progress
                        if (progress == 100) {
                            horizontalProgressDialog.dismiss()
                        }
                        try {
                            Thread.sleep(25)
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }
                        progress++
                    }
                })

            }

            R.id.btn_dialog_7 -> {

                val datePickerDialog = DatePickerDialog(
                    context!!,
                    OnDateSetListener { view1: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                        calendar.set(Calendar.YEAR, year)
                        calendar.set(Calendar.MONTH, monthOfYear)
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        val date =
                            DateFormat.getDateInstance(DateFormat.SHORT)
                                .format(calendar.time)
                        binding.btnDialog7.text = date
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                )
                datePickerDialog.show()
            }

            R.id.btn_dialog_8 -> {

                val timePickerDialog = TimePickerDialog(
                    context,
                    OnTimeSetListener { _: TimePicker?, i: Int, i1: Int ->
                        calendar[Calendar.HOUR_OF_DAY] = i
                        calendar[Calendar.MINUTE] = i1
                        val time =
                            DateFormat.getTimeInstance(DateFormat.SHORT)
                                .format(calendar.time)
                        binding.btnDialog8.text = time
                    },
                    calendar[Calendar.HOUR_OF_DAY],
                    calendar[Calendar.MINUTE],
                    true
                )
                timePickerDialog.show()
            }

            R.id.btn_dialog_9 -> {
                val mBottomSheetDialog = BottomSheetDialog(context!!,R.style.BottomTransparentDialog)
                val dialogView: View =
                    activity!!.layoutInflater.inflate(R.layout.dialog_bottom_sheet, null)
                val btn_dialog_bottom_sheet_ok =
                    dialogView.findViewById<Button>(R.id.btn_dialog_bottom_sheet_ok)
                val btn_dialog_bottom_sheet_cancel =
                    dialogView.findViewById<Button>(R.id.btn_dialog_bottom_sheet_cancel)
                val img_bottom_dialog =
                    dialogView.findViewById<ImageView>(R.id.img_bottom_dialog)
                Glide.with(context!!).load(R.drawable.bottom_dialog).into(img_bottom_dialog)
                mBottomSheetDialog.setContentView(dialogView)
                btn_dialog_bottom_sheet_ok.setOnClickListener { v: View? -> mBottomSheetDialog.dismiss() }
                btn_dialog_bottom_sheet_cancel.setOnClickListener { v: View? -> mBottomSheetDialog.dismiss() }
                mBottomSheetDialog.show()

            }

            R.id.btn_dialog_10 -> {
                val fullscreenDialog =
                    Dialog(context!!, R.style.DialogFullscreen)
                fullscreenDialog.setContentView(R.layout.dialog_fullscreen)
                val img_full_screen_dialog =
                    fullscreenDialog.findViewById<ImageView>(R.id.img_full_screen_dialog)
                Glide.with(context!!).load(R.drawable.google_assistant)
                    .apply(RequestOptions().fitCenter())
                    .into(img_full_screen_dialog)
                val toolbar_full_screen_dialog: Toolbar =
                    fullscreenDialog.findViewById(R.id.toolbar_full_screen_dialog)
                toolbar_full_screen_dialog.setNavigationOnClickListener { v: View? -> fullscreenDialog.dismiss() }
                fullscreenDialog.show()

            }

            R.id.btn_dialog_11 -> {

                val popupMenu =
                    PopupMenu(context!!, binding.btnDialog11)
                popupMenu.menuInflater.inflate(R.menu.popup_menu_main, popupMenu.menu)
                popupMenu.setOnMenuItemClickListener { item: MenuItem? -> false }
                popupMenu.show()
            }


        }

    }

}