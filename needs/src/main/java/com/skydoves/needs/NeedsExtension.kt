/*
 * Copyright (C) 2019 skydoves
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.skydoves.needs

import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment

/** shows the popup menu to the center. */
@MainThread
fun View.showNeeds(needs: Needs) {
  val view = this
  this.viewTreeObserver.addOnGlobalLayoutListener(
    object : ViewTreeObserver.OnGlobalLayoutListener {
      override fun onGlobalLayout() {
        needs.show(view)
        viewTreeObserver.removeOnGlobalLayoutListener(this)
      }
    })
}

/** shows the popup menu to the center. */
@MainThread
fun Needs.showNeeds(view: View) {
  view.viewTreeObserver.addOnGlobalLayoutListener(
    object : ViewTreeObserver.OnGlobalLayoutListener {
      override fun onGlobalLayout() {
        show(view)
        view.viewTreeObserver.removeOnGlobalLayoutListener(this)
      }
    })
}

/** returns a [Lazy] delegate to access the [ComponentActivity]'s Needs. */
@MainThread
inline fun ComponentActivity.needs(
  noinline needsProducer: (() -> Needs)
): Lazy<Needs> {
  return lazy { needsProducer() }
}

/** returns a [Lazy] delegate to access the [Fragment]'s Needs. */
@MainThread
inline fun Fragment.needs(
  noinline needsProducer: (() -> Needs)
): Lazy<Needs> {
  return lazy { needsProducer() }
}
