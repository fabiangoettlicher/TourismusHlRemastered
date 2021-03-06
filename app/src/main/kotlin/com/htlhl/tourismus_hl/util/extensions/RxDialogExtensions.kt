/* Copyright 2017 Tailored Media GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. */

package com.htlhl.tourismus_hl.util.extensions

import android.app.AlertDialog
import android.content.Context
import androidx.annotation.StringRes
import com.htlhl.tourismus_hl.util.exceptions.RxDialogException
import io.reactivex.Completable


fun Context.rxDialog(title: String, text: String,
                     positiveText: String? = null, negativeText: String? = null,
                     cancelable: Boolean = false
): Completable = rxDialogInternal(
        title, text,
        positiveText ?: this.getString(android.R.string.yes), negativeText,
        cancelable
)

fun Context.rxDialog(@StringRes titleId: Int, @StringRes textId: Int,
                     @StringRes positiveId: Int = android.R.string.yes,
                     @StringRes negativeId: Int? = android.R.string.cancel,
                     cancelable: Boolean = false
): Completable = rxDialogInternal(
        this.getString(titleId), this.getString(textId),
        this.getString(positiveId), negativeId?.let { this.getString(it) },
        cancelable
)

private fun Context.rxDialogInternal(title: String, text: String,
                                     positiveText: String, negativeText: String?,
                                     cancelable: Boolean
): Completable = Completable.create { emitter ->
    val alert = AlertDialog.Builder(this).apply {
        setTitle(title)
        setMessage(text)
        setPositiveButton(positiveText, { _, _ -> emitter.onComplete() })
        if (negativeText != null) setNegativeButton(negativeText, { _, _ -> emitter.onError(RxDialogException.negative()) })
        setCancelable(cancelable)
        if (cancelable) setOnCancelListener { emitter.onError(RxDialogException.canceled()) }
    }.create()
    emitter.setCancellable(alert::dismiss)
    alert.show()
}
