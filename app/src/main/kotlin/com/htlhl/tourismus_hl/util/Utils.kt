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

package com.htlhl.tourismus_hl.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView


object Utils {

    fun <T : RecyclerView.ViewHolder> createViewHolder(viewGroup: ViewGroup, @LayoutRes layoutResId: Int, newViewHolderAction: (View) -> T): T {
        val view = LayoutInflater.from(viewGroup.context).inflate(layoutResId, viewGroup, false)
        return newViewHolderAction(view)
    }

}
